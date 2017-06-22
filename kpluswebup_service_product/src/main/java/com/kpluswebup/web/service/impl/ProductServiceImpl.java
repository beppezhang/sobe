package com.kpluswebup.web.service.impl;

import static org.springframework.util.Assert.notNull;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;

import com.kpluswebup.web.admin.system.dao.SystemCodeDAO;
import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.domain.ProductDetailDTO;
import com.kpluswebup.web.product.dao.ItemDAO;
import com.kpluswebup.web.product.dao.ProductDAO;
import com.kpluswebup.web.product.dao.ProductDetailDAO;
import com.kpluswebup.web.product.dao.ProductPictureDAO;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.ProductDetailVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpuswebup.common.lucene.LuceneField;
import com.kpuswebup.common.lucene.LuceneResult;
import com.kpuswebup.common.lucene.LuceneSearch;
import com.kpuswebup.common.lucene.LuceneWriter;
import com.kpuswebup.common.lucene.LuceneSearch.IndexType;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.GenerationNum;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.TpartsUtils;
import com.kpuswebup.comom.util.UUIDUtil;
import com.swetake.util.Qrcode;

@Service
public class ProductServiceImpl implements ProductService {

    public ProductServiceImpl(ProductDAO productDAO) {
		super();
		this.productDAO = productDAO;
	}


	public ProductServiceImpl() {
		super();
	}


	@Autowired
    private ProductDAO        productDAO;

    @Autowired
    private ItemDAO           itemDAO;

    @Autowired
    private ProductDetailDAO  productDetailDAO;

    @Autowired
    private ProductPictureDAO productPictureDAO;

    @Autowired
    private SystemCodeDAO     systemCodeDAO;

    @Autowired
    private CachedClient      cachedClient;

    @Transactional
    public String addProduct(String mainID, String name, String virtual, String catalog, String picURL, String unit,
                             String description, String productTypeID, String brandID, String title,
                             String metaKeywords, String metaDescription, String currentOperator, String productDetail,
                             String isRecommend, String subTitle, String saleService, String productProp,
                             String productType, String isBuy, String isLowPrice,String isSales,String supplierID,String supplierCategoryID,String productStandPrice,Integer status) {

        notNull(name, "name is null");
        notNull(virtual, "virtual is null");
        notNull(mainID, "mainID is null");
        notNull(brandID, "brandID is null");
        notNull(productDetail, "productDetail is null");
        notNull(supplierCategoryID, "supplierCategoryID is null");
        notNull(supplierID, "supplierID is null");
        CodeConfigVO codeConfigVO = systemCodeDAO.findCodeConfigByID(Constant.PRODUCTID);
        if (codeConfigVO != null) {
            mainID = codeConfigVO.getCodeEx() + GenerationNum.getRandomNumber(9);
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setVirtual(Integer.parseInt(virtual));
        productDTO.setCatalog(1);
        productDTO.setPicURL(picURL);
        productDTO.setUnit(unit);
        productDTO.setStatus(status);
        productDTO.setDescription(description);
        productDTO.setProductTypeID(productTypeID);
        productDTO.setBrandID(brandID);
        productDTO.setTitle(title);
        productDTO.setMetaDescription(metaDescription);
        productDTO.setMetaKeywords(metaKeywords);
       // productDTO.setStatus(Constant.product_status_down);
        productDTO.setMainID(mainID);
        productDTO.setCreator(currentOperator);
        productDTO.setSupplierID(supplierID);
        productDTO.setStandPrice(productStandPrice);
        productDTO.setSupplierCategoryID(supplierCategoryID);
        // productDTO.setIsRecommend(Integer.valueOf(isRecommend));
        productDTO.setQrcodeurl(this.qrCodeProduct(mainID));
        productDTO.setSubTitle(subTitle);
        productDTO.setSaleService(saleService);
        productDTO.setProductProp(productProp);
        //productDTO.setProductType(Integer.valueOf(productType));
      //  productDTO.setIsBuy(Integer.valueOf(isBuy));
        //productDTO.setIsLowPrice(Integer.valueOf(isLowPrice));
       // productDTO.setIsSales(Integer.valueOf(isSales));
        Integer count = productDAO.insertProduct(productDTO);
        if (count == 1) {
            if (StringUtil.isNotEmpty(productDetail)) {
                String[] productDetails = productDetail.split("\\|");
                for (String detail : productDetails) {
                	if(!"on".equals(detail)){
                		if(detail.split("_").length>1){
	                    ProductDetailDTO productDetailDTO = new ProductDetailDTO();
	                    productDetailDTO.setproductID(mainID);
	                    productDetailDTO.setproductPropID(detail.split("_")[2]);
	                    productDetailDTO.setpTypePPropID(detail.split("_")[0]);
	                    productDetailDTO.setproductPropValue(detail.split("_")[1]);
	                    productDetailDTO.setCreator(currentOperator);
	                    productDetailDAO.insertProductDetail(productDetailDTO);
                		}
                	}
                }
            }
            return mainID;
        }

        return null;
    }

    /**
     * 销售排行
     * 
     * @return
     */
    public List<ProductVO> getVolumeProducts() {
        return productDAO.getVolumeProducts();
    }

    @SuppressWarnings("static-access")
    public String qrCodeProduct(String mainID) {
        try {
            String dir = "/file/" + DateUtil.getDateAgo(0);
            this.getResponse().setContentType("text/html; charset=UTF-8");
            String uuid = UUIDUtil.getUUID();
            String realPath = this.getRequest().getSession().getServletContext().getRealPath(dir);
            String imgPath = dir + "/" + uuid + ".png";
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            ClassPathResource resource = new ClassPathResource("server.properties");
            Properties prop = new Properties();
            prop.load(new FileReader(resource.getFile()));
            String domain = prop.getProperty("qrurl");
            this.encoderQRCode(domain + "/weixin/productDetail.htm?productID=" + mainID, realPath + "/" + uuid + ".png");
            return imgPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductVO> findProductList(ProductDTO productDTO) {
        productDTO.doPage(productDAO.findProducCount(productDTO), productDTO.getPageNo(), productDTO.getPageSize());
        return productDAO.findProducByPagination(productDTO);
    }

    /**
     * 浏览记录
     * 
     * @param productDTO
     * @return
     */
    public List<ProductVO> findHistoryProductsByPagination(ProductDTO productDTO) {
        productDTO.doPage(productDAO.findHistoryProducCount(productDTO), productDTO.getPageNo(),
                          productDTO.getPageSize());
        return productDAO.findHistoryProductsByPagination(productDTO);
    }

    @Transactional
    public Boolean deleteProduct(String mainID) {
        try {
            productDAO.deleteProductByMainID(mainID);
            itemDAO.deleteItemByProductID(mainID);
            productPictureDAO.deleteProductPictureByProductID(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addProductDetail(String productID, String productPropID, String pTypePPropID,
                                 String[] productPropValue, String currentOperator) {
        notNull(productID, "productID is null");
        notNull(productPropID, "productPropID is null");
        notNull(productPropValue, "productPropValue is null");
        StringBuilder productPropValues = new StringBuilder();
        StringBuilder pTypePPropIDs = new StringBuilder();
        for (String str : productPropValue) {
            pTypePPropIDs.append(str.split("_")[0]);
            pTypePPropIDs.append(",");
            productPropValues.append(str.split("_")[1]);
            productPropValues.append(",");
        }

        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setproductID(productID);
        productDetailDTO.setproductPropID(productPropID);
        productDetailDTO.setpTypePPropID(pTypePPropIDs.toString());
        productDetailDTO.setproductPropValue(productPropValues.toString());
        productDetailDTO.setCreator(currentOperator);
        productDetailDAO.insertProductDetail(productDetailDTO);

    }

    public ProductVO findProductByMainID(String mainID) {
        return productDAO.findProductByMainID(mainID);
    }

    @Transactional
    public String updateProduct(String mainID, String name, String virtual, String catalog, String picURL, String unit,
                                String description, String productTypeID, String brandID, String title,
                                String metaKeywords, String metaDescription, String currentOperator,
                                String productDetail, String isRecommend, String subTitle, String saleService,
                                String productProp, String productType, String isBuy, String isLowPrice,String isSales,String supplierID,String supplierCategoryID,String productStandPrice,Integer status) {
        notNull(name, "name is null");
        notNull(virtual, "virtual is null");
        notNull(brandID, "brandID is null");
        notNull(supplierCategoryID, "supplierCategoryID is null");
        notNull(supplierID, "supplierID is null");
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setVirtual(Integer.parseInt(virtual));
        productDTO.setCatalog(1);
        productDTO.setPicURL(picURL);
        productDTO.setUnit(unit);
        productDTO.setDescription(description);
        productDTO.setProductTypeID(productTypeID);
        productDTO.setBrandID(brandID);
        productDTO.setTitle(title);
        productDTO.setMetaDescription(metaDescription);
        productDTO.setMetaKeywords(metaKeywords);
        productDTO.setSupplierID(supplierID);
        productDTO.setStatus(status);
        productDTO.setSupplierCategoryID(supplierCategoryID);
        //productDTO.setStatus(Constant.product_status_down);
        productDTO.setMainID(mainID);
        productDTO.setModifier(currentOperator);
        // productDTO.setIsRecommend(Integer.valueOf(isRecommend));
        productDTO.setSubTitle(subTitle);
        productDTO.setSupplierID(supplierID);
        productDTO.setStandPrice(productStandPrice);
        productDTO.setSupplierCategoryID(supplierCategoryID);
        productDTO.setSaleService(saleService);
        productDTO.setProductProp(productProp);
        //productDTO.setProductType(Integer.valueOf(productType));
       // productDTO.setIsBuy(Integer.valueOf(isBuy));
       // productDTO.setIsLowPrice(Integer.valueOf(isLowPrice));
        //productDTO.setIsSales(Integer.valueOf(isSales));
        Integer count = productDAO.updateProductByMainID(productDTO);

        if (count == 1) {
            productDetailDAO.deteleProductDetailByProductMainId(mainID);
            if (StringUtil.isNotEmpty(productDetail)) {
                String[] productDetails = productDetail.split("\\|");
                for (String detail : productDetails) {
                	if(!"on".equals(detail)){
                		if(detail.split("_").length>1){
		                    ProductDetailDTO productDetailDTO = new ProductDetailDTO();
		                    productDetailDTO.setproductID(mainID);
		                    productDetailDTO.setproductPropID(detail.split("_")[2]);
		                    productDetailDTO.setpTypePPropID(detail.split("_")[0]);
		                    productDetailDTO.setproductPropValue(detail.split("_")[1]);
		                    productDetailDTO.setCreator(currentOperator);
		                    productDetailDAO.insertProductDetail(productDetailDTO);
                		}
                	}
                }
            }
            return mainID;
        }
        return null;
    }

    public List<ProductDetailVO> findProductDetailList(String productMainID) {
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setproductID(productMainID);
        return productDetailDAO.findProductDetail(productDetailDTO);
    }

    public Integer updateProductStatus(String mainIDs, String status) {
        String ids[] = mainIDs.split(",");
        Integer count=null;
        for (String mainID : ids) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setMainID(mainID);

            if (Constant.product_status_top.toString().equals(status)) {
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setProductID(mainID);
                List<ItemVO> list = itemDAO.findItem(itemDTO);
                if (list == null || list.size() == 0) {
                }
            }

            if (StringUtil.isInteger(status)) {
                productDTO.setStatus(Integer.parseInt(status));
                count = productDAO.updateProductByMainID(productDTO);
            }
        }
        return count;
    }

    public List<ProductVO> searchProducItemCat(ProductDTO productDTO) {
        Long count = productDAO.searchProducItemCatCount(productDTO);
        if (count == null) {
            return null;
        }
        productDTO.doPage(count, productDTO.getPageNo(), productDTO.getPageSize());
        return productDAO.searchProducItemCat(productDTO);
    }

    /**
     * 生成二维码(QRCode)图片
     * 
     * @param content
     * @param imgPath
     */

    public static void encoderQRCode(String content, String imgPath) {

        try {

            Qrcode qrcodeHandler = new Qrcode();

            qrcodeHandler.setQrcodeErrorCorrect('M');

            qrcodeHandler.setQrcodeEncodeMode('B');

            qrcodeHandler.setQrcodeVersion(7);

            System.out.println(content);

            byte[] contentBytes = content.getBytes("gb2312");

            BufferedImage bufImg = new BufferedImage(140, 140,

            BufferedImage.TYPE_INT_RGB);

            Graphics2D gs = bufImg.createGraphics();

            gs.setBackground(Color.WHITE);

            gs.clearRect(0, 0, 140, 140);

            // 设定图像颜色> BLACK

            gs.setColor(Color.BLACK);

            // 设置偏移量 不设置可能导致解析出错

            int pixoff = 2;

            // 输出内容> 二维码

            if (contentBytes.length > 0 && contentBytes.length < 120) {

                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);

                for (int i = 0; i < codeOut.length; i++) {

                    for (int j = 0; j < codeOut.length; j++) {

                        if (codeOut[j][i]) {

                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);

                        }

                    }

                }

            } else {

                System.err.println("QRCode content bytes length = "

                + contentBytes.length + " not in [ 0,120 ]. ");

            }

            gs.dispose();

            bufImg.flush();

            File imgFile = new File(imgPath);

            // 生成二维码QRCode图片

            ImageIO.write(bufImg, "png", imgFile);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public HttpServletResponse getResponse() {
        WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(getRequest());
        ServletWebRequest webRequest = (ServletWebRequest) ReflectionTestUtils.getField(asyncManager, "asyncWebRequest");
        return webRequest.getResponse();
    }

    public HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        return servletRequestAttributes.getRequest();
    }

    @Override
    public List<ProductVO> searchProductByIsRemmond() {
        return productDAO.searchProductByIsRemmond();
    }

    @Override
    public List<ProductVO> findProductYourLove() {
        return productDAO.findProductYourLove();
    }

    @Override
    public ProductVO findProductByMainIDAll(String mainID) {
        return productDAO.findProductByMainIDAll(mainID);
    }

    @Override
    public List<ProductVO> findProductSales(ProductDTO productDTO) {
    //	return productDAO.findProductSales(productDTO);
        return productDAO.findProductSalesOnCat(productDTO);
    }

    @Override
    public List<ProductVO> findProductLowPrice() {
        return productDAO.findProductLowPrice();
    }

    @Override
    public List<ProductVO> findProductBuy() {
        return productDAO.findProductBuy();
    }

    @Override
    public List<ProductDetailVO> findProductDetailPropValue(String productID, String pTypeIPropID) {
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setproductID(productID);
        productDetailDTO.setpTypePPropID(pTypeIPropID);
        return productDAO.findProductDetailPropValue(productDetailDTO);
    }

    @Override
    public List<ProductVO> findProductIsSales() {
        return productDAO.findProductIsSales();
    }

    
    //*************************************************
    StopWatch sw = null;
    @Override
    public List<ProductVO> findProductListTparts(ProductDTO productDTO) {
//    	if(StringUtil.isEmpty(productDTO.getVehicleTypeId()))
//    	{
//    		productDTO.doPage(10000l, productDTO.getPageNo(), productDTO.getPageSize());	
//    	}else
//    	{
//    		productDTO.doPage(productDAO.findProductCountTparts(productDTO), productDTO.getPageNo(), productDTO.getPageSize());
//    	}
    	sw = new StopWatch();

    	List<ProductVO> products = null;    	
    	if(productDTO.getSearchModel().equals(Constant.SEARCHMODEL_PRODUCT))
    	{
    		//正常产品查询
        	if(StringUtil.isNotEmpty(productDTO.getVehicleTypeId()) && StringUtil.isEmpty(productDTO.getPartsCategoryId())
        			&& StringUtil.isEmpty(productDTO.getName()))
        	{
        		// 根据汽车品牌导航
            	sw.start("开始筛选配件数据 -- 计算 count only vehicleType");
        		//productDTO.doPage(productDAO.findProductOnlyVehicleTypeCountTparts(productDTO), productDTO.getPageNo(), productDTO.getPageSize());
        		productDTO.doPage(1000l, productDTO.getPageNo(), productDTO.getPageSize());
        		if(sw.isRunning())
        			sw.stop();
        		sw.start("开始筛选配件数据 -- 获取数据 only vehicleType");
        		products = productDAO.findProductByPaginationVehicleTypeTparts(productDTO);
        		if(sw.isRunning())
        			sw.stop();    	        		
        		
        	}else if(StringUtil.isEmpty(productDTO.getVehicleTypeId()) && StringUtil.isEmpty(productDTO.getPartsCategoryId())
        			&& StringUtil.isNotEmpty(productDTO.getName()))
        	{
        		//根据关键字，无 整车车型 与 配件品牌 ，配件分类
        		productDTO.doPage(200l, productDTO.getPageNo(), productDTO.getPageSize());
        		sw.start("开始筛选配件数据 -- 获取数据 only name");
        		products = productDAO.findProductByPaginationByNameTparts(productDTO);
        		if(sw.isRunning())
        			sw.stop();
        	}else if(StringUtil.isNotEmpty(productDTO.getVehicleTypeId()) && StringUtil.isEmpty(productDTO.getPartsCategoryId())
        			&& StringUtil.isNotEmpty(productDTO.getName()))
        	{
        		// 根据汽车品牌导航/关键字
            	sw.start("开始筛选配件数据 -- 计算 count only vehicleType");
        		//productDTO.doPage(productDAO.findProductOnlyVehicleTypeCountTparts(productDTO), productDTO.getPageNo(), productDTO.getPageSize());
        		productDTO.doPage(1000l, productDTO.getPageNo(), productDTO.getPageSize());
        		if(sw.isRunning())
        			sw.stop();
        		sw.start("开始筛选配件数据 -- 获取数据 only vehicleType");
        		products = productDAO.findProductByPaginationVehicleTypeTparts(productDTO);
        		if(sw.isRunning())
        			sw.stop();           		
        	}else if(StringUtil.isNotEmpty(productDTO.getPartsCategoryId()) && StringUtil.isEmpty(productDTO.getVehicleTypeId()))
        	{
        		// 根据配件分类 / 关键字
            	sw.start("开始筛选配件数据 -- 计算 count only partsCategory");
        		//productDTO.doPage(productDAO.findProductOnlyVehicleTypeCountTparts(productDTO), productDTO.getPageNo(), productDTO.getPageSize());
        		productDTO.doPage(1000l, productDTO.getPageNo(), productDTO.getPageSize());
        		if(sw.isRunning())
        			sw.stop();
        		sw.start("开始筛选配件数据 -- 获取数据 only partsCategory");
        		products = productDAO.findProductByPaginationCategoryTparts(productDTO);
        		if(sw.isRunning())
        			sw.stop();               		
        		
        	}else if(StringUtil.isNotEmpty(productDTO.getPartsCategoryId()) && StringUtil.isNotEmpty(productDTO.getVehicleTypeId()))
        	{
        		// 根据汽车品牌导航/关键字/配件分类
            	sw.start("开始筛选配件数据 -- 计算 count only vehicleType");
        		//productDTO.doPage(productDAO.findProductOnlyVehicleTypeCountTparts(productDTO), productDTO.getPageNo(), productDTO.getPageSize());
        		productDTO.doPage(1000l, productDTO.getPageNo(), productDTO.getPageSize());
        		if(sw.isRunning())
        			sw.stop();
        		sw.start("开始筛选配件数据 -- 获取数据 only vehicleType");
        		products = productDAO.findProductByPaginationVehicleTypeTparts(productDTO);
        		if(sw.isRunning())
        			sw.stop();            		
        	}
    	}else if(productDTO.getSearchModel().equals(Constant.SEARCHMODEL_VIN))
    	{
    		//根据 vin 直接定位到整车车型/整车产品
    		if(StringUtil.isNotEmpty(productDTO.getVehicleTypeId()) && StringUtil.isEmpty(productDTO.getPartsCategoryId())
        			&& StringUtil.isEmpty(productDTO.getName()))
        	{
        		// 根据汽车品牌导航
            	sw.start("开始筛选配件数据 -- 计算 count only vehicleType");
        		//productDTO.doPage(productDAO.findProductOnlyVehicleTypeCountTparts(productDTO), productDTO.getPageNo(), productDTO.getPageSize());
        		productDTO.doPage(1000l, productDTO.getPageNo(), productDTO.getPageSize());
        		if(sw.isRunning())
        			sw.stop();
        		sw.start("开始筛选配件数据 -- 获取数据 only vehicleType");
        		products = productDAO.findProductByPaginationVehicleTypeTparts(productDTO);
        		if(sw.isRunning())
        			sw.stop();    	        		
        		
        	}    		
    		
    	}else if(productDTO.getSearchModel().equals(Constant.SEARCHMODEL_OEM))
    	{
    		//根据oem 直接定位到配件
        	sw.start("开始筛选配件数据 -- 计算 count only oem");
    		//productDTO.doPage(productDAO.findProductOnlyVehicleTypeCountTparts(productDTO), productDTO.getPageNo(), productDTO.getPageSize());
    		productDTO.doPage(1l, productDTO.getPageNo(), productDTO.getPageSize());
    		if(sw.isRunning())
    			sw.stop();
    		sw.start("开始筛选配件数据 -- 获取数据 only oem");
    		products = productDAO.findProductByPaginationByOEMTparts(productDTO);
    		if(sw.isRunning())
    			sw.stop();    	    		
    	}
    	

//    	if(StringUtil.isNotEmpty(productDTO.getVehicleTypeId()) && StringUtil.isEmpty(productDTO.getPartsCategoryId())
//    			&& StringUtil.isEmpty(productDTO.getName()))
//    	{
//	
//    	}else
//    	{
//    		sw.start("开始筛选配件数据 -- 计算 count");
//    		productDTO.doPage(1000l, productDTO.getPageNo(), productDTO.getPageSize());
//    		if(sw.isRunning())
//    			sw.stop();
//    		sw.start("开始筛选配件数据 -- 获取数据");    		
//    		products = productDAO.findProductByPaginationTparts(productDTO);
//    		if(sw.isRunning())
//    			sw.stop();
//    	}
    	
    	System.out.println(sw.prettyPrint());
    	return products;
    }

    
    Logger logger = Logger.getLogger(ProductServiceImpl.class); 
    @Override
    public List<ProductVO> findProductListByLucene(ProductDTO productDTO) {
    	sw = new StopWatch();

    	List<ProductVO> products = null;    
		try {
    	if(productDTO.getSearchModel().equals(Constant.SEARCHMODEL_PRODUCT) || productDTO.getSearchModel().equals(Constant.SEARCHMODEL_VIN))
    	{
    		String [] fields  = null;
    		String [] values  = null;
    		IndexSearcher searcher = null;   
    		LuceneResult rs = null;
    		//正常产品查询
        	if(StringUtil.isNotEmpty(productDTO.getVehicleTypeId()) && StringUtil.isEmpty(productDTO.getPartsCategoryId())
        			&& StringUtil.isEmpty(productDTO.getName()))
        	{
        		//  整车车型    INDEX_PATH_VEHICLETYPE_PRODUCT { "name", "productID", "brandID","vehicleTypeID" };
        		products = queryParts(IndexType.VEHICLETYPE_PRODUCT,productDTO);    	        		
        		
        	}else if(StringUtil.isEmpty(productDTO.getVehicleTypeId()) && StringUtil.isEmpty(productDTO.getPartsCategoryId())
        			&& StringUtil.isNotEmpty(productDTO.getName()))
        	{
        		//根据关键字，无 整车车型 与 配件品牌 ，配件分类   -- 仅产品  INDEX_PATH_PRODUCT  { "name", "productID", "brandID" }
        		products = queryParts(IndexType.PRODUCT,productDTO);
        	}else if(StringUtil.isNotEmpty(productDTO.getVehicleTypeId()) && StringUtil.isEmpty(productDTO.getPartsCategoryId())
        			&& StringUtil.isNotEmpty(productDTO.getName()))
        	{
        	//  整车车型  + name   INDEX_PATH_VEHICLETYPE_PRODUCT
        		products = queryParts(IndexType.VEHICLETYPE_PRODUCT,productDTO);
        	}else if(StringUtil.isNotEmpty(productDTO.getPartsCategoryId()) && StringUtil.isEmpty(productDTO.getVehicleTypeId()))
        	{
        		// 根据配件分类 / 关键字    INDEX_PATH_PARTSCATEGORY_PRODUCT { "name", "productID", "brandID","partsCategoryID" };       
        		products = queryParts(IndexType.PARTSCATEGORY_PRODUCT,productDTO);
        		
        	}else if(StringUtil.isNotEmpty(productDTO.getPartsCategoryId()) && StringUtil.isNotEmpty(productDTO.getVehicleTypeId()))
        	{  		
        		products = queryParts(IndexType.VEHICLETYPE_PARTSCATEGORY_PRODUCT,productDTO);
        	}
    	}else if(productDTO.getSearchModel().equals(Constant.SEARCHMODEL_VIN))
    	{
    		//根据 vin 直接定位到整车车型/整车产品
    		if(StringUtil.isNotEmpty(productDTO.getVehicleTypeId()) && StringUtil.isEmpty(productDTO.getPartsCategoryId())
        			&& StringUtil.isEmpty(productDTO.getName()))
        	{
        		// 根据汽车品牌导航
            	sw.start("开始筛选配件数据 -- 计算 count only vehicleType");
        		//productDTO.doPage(productDAO.findProductOnlyVehicleTypeCountTparts(productDTO), productDTO.getPageNo(), productDTO.getPageSize());
        		productDTO.doPage(1000l, productDTO.getPageNo(), productDTO.getPageSize());
        		if(sw.isRunning())
        			sw.stop();
        		sw.start("开始筛选配件数据 -- 获取数据 only vehicleType");
        		products = productDAO.findProductByPaginationVehicleTypeTparts(productDTO);
        		if(sw.isRunning())
        			sw.stop();    	        		
        		
        	}    		
    		
    	}else if(productDTO.getSearchModel().equals(Constant.SEARCHMODEL_OEM))
    	{
    		//根据oem 直接定位到配件
        	sw.start("开始筛选配件数据 -- 计算 count only oem");
    		//productDTO.doPage(productDAO.findProductOnlyVehicleTypeCountTparts(productDTO), productDTO.getPageNo(), productDTO.getPageSize());
    		productDTO.doPage(1l, productDTO.getPageNo(), productDTO.getPageSize());
    		if(sw.isRunning())
    			sw.stop();
    		sw.start("开始筛选配件数据 -- 获取数据 only oem");
    		products = productDAO.findProductByPaginationByOEMTparts(productDTO);
    		if(sw.isRunning())
    			sw.stop();    	    		
    	}
    	

//    	if(StringUtil.isNotEmpty(productDTO.getVehicleTypeId()) && StringUtil.isEmpty(productDTO.getPartsCategoryId())
//    			&& StringUtil.isEmpty(productDTO.getName()))
//    	{
//	
//    	}else
//    	{
//    		sw.start("开始筛选配件数据 -- 计算 count");
//    		productDTO.doPage(1000l, productDTO.getPageNo(), productDTO.getPageSize());
//    		if(sw.isRunning())
//    			sw.stop();
//    		sw.start("开始筛选配件数据 -- 获取数据");    		
//    		products = productDAO.findProductByPaginationTparts(productDTO);
//    		if(sw.isRunning())
//    			sw.stop();
//    	}
    	
		} catch (Exception e) {
			e.printStackTrace();
		}    	
    	System.out.println(sw.prettyPrint());
    	return products;
    }

	private List<ProductVO> queryParts(IndexType indexType,ProductDTO productDTO) {
		List<ProductVO> products = null;
		String[] fields = null;
		String[] values = null;
		LuceneResult rs;
		switch (indexType) {
		case PRODUCT:
			if(StringUtil.isNotEmpty(productDTO.getBrandID()))
			{
	    		fields = new String[] { LuceneField.FIELD_NAME,LuceneField.FIELD_BRANDID};
	    		values = new String[] { productDTO.getName(),productDTO.getBrandID()};				
			}else
			{
	    		fields = new String[] { LuceneField.FIELD_NAME};
	    		values = new String[] { productDTO.getName()};				
			}

			break;
		case PARTSCATEGORY_PRODUCT:
			if(StringUtil.isNotEmpty(productDTO.getBrandID()))
			{
				fields = new String[] { LuceneField.FIELD_NAME,LuceneField.FIELD_PARTSCATEGORYID,LuceneField.FIELD_BRANDID};
				values = new String[] { StringUtil.isEmpty(productDTO.getName()) ? LuceneField.FIELD_NAME_NONE : productDTO.getName(),productDTO.getPartsCategoryId(),productDTO.getBrandID()};				
			}else
			{
				fields = new String[] { LuceneField.FIELD_NAME,LuceneField.FIELD_PARTSCATEGORYID};
				values = new String[] { StringUtil.isEmpty(productDTO.getName()) ? LuceneField.FIELD_NAME_NONE : productDTO.getName(),productDTO.getPartsCategoryId()};				
			}

			break;
		case VEHICLETYPE_PRODUCT:
			if(StringUtil.isNotEmpty(productDTO.getBrandID()))
			{
				fields = new String[] { LuceneField.FIELD_NAME,LuceneField.FIELD_VEHICLETYPEID,LuceneField.FIELD_BRANDID};
				values = new String[] { StringUtil.isEmpty(productDTO.getName()) ? LuceneField.FIELD_NAME_NONE : productDTO.getName(),productDTO.getVehicleTypeId(),productDTO.getBrandID()};				
			}else
			{
				fields = new String[] { LuceneField.FIELD_NAME,LuceneField.FIELD_VEHICLETYPEID};
				values = new String[] { StringUtil.isEmpty(productDTO.getName()) ? LuceneField.FIELD_NAME_NONE : productDTO.getName(),productDTO.getVehicleTypeId()};				
			}

			break;
		case VEHICLETYPE_PARTSCATEGORY_PRODUCT:
			if(StringUtil.isNotEmpty(productDTO.getBrandID()))
			{
				fields = new String[] { LuceneField.FIELD_NAME,LuceneField.FIELD_VEHICLETYPEID,LuceneField.FIELD_PARTSCATEGORYID,LuceneField.FIELD_BRANDID};
				values = new String[] { StringUtil.isEmpty(productDTO.getName()) ? LuceneField.FIELD_NAME_NONE : productDTO.getName(),productDTO.getVehicleTypeId(),productDTO.getPartsCategoryId(),productDTO.getBrandID()};				
			}else
			{
				fields = new String[] { LuceneField.FIELD_NAME,LuceneField.FIELD_VEHICLETYPEID,LuceneField.FIELD_PARTSCATEGORYID};
				values = new String[] { StringUtil.isEmpty(productDTO.getName()) ? LuceneField.FIELD_NAME_NONE : productDTO.getName(),productDTO.getVehicleTypeId(),productDTO.getPartsCategoryId()};				
			}

			break;
		default:
			break;
		}		
        		
		rs = LuceneSearch.query(indexType, fields, values, new StandardAnalyzer(), 
				Integer.parseInt(productDTO.getPageSize().toString()) * productDTO.getPageNoCountForLuncene(), productDTO.getStartRow(), productDTO.getEndRow(), LuceneField.FIELD_PRODUCTID);
		productDTO.doPage(rs.getHitsPerPage(), productDTO.getPageNo(), productDTO.getPageSize());
		productDTO.setMainIDList(rs.getMainIDList());
		sw.start("开始筛选配件数据 -- findProductByMainIDs  only vehicleType");
		if(productDTO.getMainIDList().size()>0)
		{
			products = productDAO.findProductByMainIDs(productDTO);	
		}
		
		if(sw.isRunning())
			sw.stop();
		return products;
	}
  
	
	private List<String> searchPartsManIDByLucene(IndexSearcher searcher,ProductDTO productDTO,
			String[] fields, String[] values) throws IOException {
		StopWatch stopWatchLucene = new StopWatch();
		stopWatchLucene.start("beging get IndexSearcher");

		TpartsUtils.stopWatchStopRunning(stopWatchLucene);  		
		stopWatchLucene.start("beging query TopDocs");
		TopDocs docs = LuceneSearch.query(searcher, fields, values,
				new StandardAnalyzer(), Integer.parseInt(productDTO.getPageSize().toString()) * productDTO.getPageNoCountForLuncene());
		TpartsUtils.stopWatchStopRunning(stopWatchLucene);  
		ScoreDoc[] hits = docs.scoreDocs;    
		Document d = null;
		long hitsPerPage = hits.length;
		List<String> mainIDList = new ArrayList<String>();
		productDTO.doPage(hitsPerPage, productDTO.getPageNo(), productDTO.getPageSize());
		System.out.println(productDTO.getStartRow());
		System.out.println(productDTO.getEndRow());
		stopWatchLucene.start("ScoreDoc[] hits = docs.scoreDocs for mainIDs");
		for (int i = 0; i < hits.length; ++i) {
			if(productDTO.getStartRow() <= i && i<productDTO.getEndRow())
			{
				int docId = hits[i].doc;
				d = searcher.doc(docId);
				System.out.println((i + 1) + ". " + d.get("name") + "\t"
						+ d.get("productID") + " \t\t\t\t" + d.get("brandID")+ " \t\t\t\t" + d.get("partsCategoryID"));
				// System.out.println((i + 1) + ". " + d.get("title") + "\t"
				// + d.get("isbn") + " \t\t\t\t" + d.get("code"));
				mainIDList.add(d.get("productID"));        				
			}
			if(i == productDTO.getEndRow())
			{
				break;
			}

		}
		TpartsUtils.stopWatchStopRunning(stopWatchLucene);  
		System.out.println(stopWatchLucene.prettyPrint());
		productDTO.setMainIDList(mainIDList);
		return mainIDList;
	}
    
	private List<String> searchPartsManIDByLucene(IndexType indexType,ProductDTO productDTO,
			String[] fields, String[] values) throws IOException {
		StopWatch stopWatchLucene = new StopWatch();
		IndexSearcher searcher;
		stopWatchLucene.start("beging get IndexSearcher");
		searcher = LuceneWriter.getInstance()
				.getSearcherByProduct();
		TpartsUtils.stopWatchStopRunning(stopWatchLucene);  		
		stopWatchLucene.start("beging query TopDocs");
		TopDocs docs = LuceneSearch.query(searcher, fields, values,
				new StandardAnalyzer(), Integer.parseInt(productDTO.getPageSize().toString()) * productDTO.getPageNoCountForLuncene());
		TpartsUtils.stopWatchStopRunning(stopWatchLucene);  
		ScoreDoc[] hits = docs.scoreDocs;    
		Document d = null;
		long hitsPerPage = hits.length;
		List<String> mainIDList = new ArrayList<String>();
		productDTO.doPage(hitsPerPage, productDTO.getPageNo(), productDTO.getPageSize());
		System.out.println(productDTO.getStartRow());
		System.out.println(productDTO.getEndRow());
		stopWatchLucene.start("ScoreDoc[] hits = docs.scoreDocs for mainIDs");
		for (int i = 0; i < hits.length; ++i) {
			if(productDTO.getStartRow() <= i && i<productDTO.getEndRow())
			{
				int docId = hits[i].doc;
				d = searcher.doc(docId);
				System.out.println((i + 1) + ". " + d.get("name") + "\t"
						+ d.get("productID") + " \t\t\t\t" + d.get("brandID")+ " \t\t\t\t" + d.get("partsCategoryID"));
				// System.out.println((i + 1) + ". " + d.get("title") + "\t"
				// + d.get("isbn") + " \t\t\t\t" + d.get("code"));
				mainIDList.add(d.get("productID"));        				
			}
			if(i == productDTO.getEndRow())
			{
				break;
			}

		}
		TpartsUtils.stopWatchStopRunning(stopWatchLucene);  
		System.out.println(stopWatchLucene.prettyPrint());
		productDTO.setMainIDList(mainIDList);
		return mainIDList;
	}	
    
    /**************************************************/
	@Override
	public ProductVO findProductByMainIDNew(String mainID) {
		return productDAO.findProductByMainIDNew(mainID);
	}

	
	@Override
	public ProductVO findProductByCode(String productCode) {
		return productDAO.findProductByCode(productCode);
	}
	
    /**
     * 获取数据库日期
     * @return
     */	
	@Override
	public String findDBDate() {
		return productDAO.findDBDate();
	}

	@Override
	public List<ProductVO> findProductBySupplierItem(ItemDTO itemDTO) {
		long count = productDAO.findProductCountBySupplierItem(itemDTO);
		itemDTO.doPage(count, itemDTO.getPageNo(), itemDTO.getPageSize());
        return productDAO.findProductBySupplierItem(itemDTO);
//		return productDAO.findProductBySupplierItem(itemDTO);
	}

	@Override
	public List<ProductVO> findProductListTpartsAdmin(ProductVO productVO) {
		long count = productDAO.findProductCountTpartsAdmin(productVO);
		productVO.doPage(count, productVO.getPageNo(), productVO.getPageSize());
        return productDAO.findProductByPaginationTpartsAdmin(productVO);
	}

	@Override
	public Integer updateProductStatusTparts(String mainIDs, String status) {
        String ids[] = mainIDs.split(",");
        Integer count=null;
        for (String mainID : ids) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setMainID(mainID);

            if (StringUtil.isInteger(status)) {
                productDTO.setIsDelete(Integer.parseInt(status));	//这里借用原来的方法，所以保留了参数status，底层改为isDelete的修改
                itemDAO.deleteItemByProductID(mainID);				//先改相应商品的isDelete
                count = productDAO.updateProductStatusByMainID(productDTO);
            }
        }
        return count;
	}


	@Override
	public String updateProduct(String mainID, String name, String virtual, String catalog, String picURL, String unit,
            String description, String productTypeID, String brandID, String title,
            String metaKeywords, String metaDescription, String currentOperator,
            String productDetail, String isRecommend, String subTitle, String saleService,
            String productProp, String productType, String isBuy, String isLowPrice,String isSales,String supplierID,String supplierCategoryID,String productStandPrice,Integer status, String freightTemplateID) {
        notNull(name, "name is null");
        notNull(virtual, "virtual is null");
        notNull(brandID, "brandID is null");
        notNull(supplierCategoryID, "supplierCategoryID is null");
        notNull(supplierID, "supplierID is null");
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setVirtual(Integer.parseInt(virtual));
        productDTO.setCatalog(1);
        productDTO.setPicURL(picURL);
        productDTO.setUnit(unit);
        productDTO.setDescription(description);
        productDTO.setProductTypeID(productTypeID);
        productDTO.setBrandID(brandID);
        productDTO.setTitle(title);
        productDTO.setMetaDescription(metaDescription);
        productDTO.setMetaKeywords(metaKeywords);
        productDTO.setSupplierID(supplierID);
        productDTO.setStatus(status);
        productDTO.setSupplierCategoryID(supplierCategoryID);
        //productDTO.setStatus(Constant.product_status_down);
        productDTO.setMainID(mainID);
        productDTO.setModifier(currentOperator);
        // productDTO.setIsRecommend(Integer.valueOf(isRecommend));
        productDTO.setSubTitle(subTitle);
        productDTO.setSupplierID(supplierID);
        productDTO.setStandPrice(productStandPrice);
        productDTO.setSupplierCategoryID(supplierCategoryID);
        productDTO.setSaleService(saleService);
        productDTO.setProductProp(productProp);
        productDTO.setFreightTemplateID(freightTemplateID);
        //productDTO.setProductType(Integer.valueOf(productType));
       // productDTO.setIsBuy(Integer.valueOf(isBuy));
       // productDTO.setIsLowPrice(Integer.valueOf(isLowPrice));
        //productDTO.setIsSales(Integer.valueOf(isSales));
        Integer count = productDAO.updateProductByMainID(productDTO);

        if (count == 1) {
            productDetailDAO.deteleProductDetailByProductMainId(mainID);
            if (StringUtil.isNotEmpty(productDetail)) {
                String[] productDetails = productDetail.split("\\|");
                for (String detail : productDetails) {
                	if(!"on".equals(detail)){
                		if(detail.split("_").length>1){
		                    ProductDetailDTO productDetailDTO = new ProductDetailDTO();
		                    productDetailDTO.setproductID(mainID);
		                    productDetailDTO.setproductPropID(detail.split("_")[2]);
		                    productDetailDTO.setpTypePPropID(detail.split("_")[0]);
		                    productDetailDTO.setproductPropValue(detail.split("_")[1]);
		                    productDetailDTO.setCreator(currentOperator);
		                    productDetailDAO.insertProductDetail(productDetailDTO);
                		}
                	}
                }
            }
            return mainID;
        }
        return null;
    }
	
	
    @Transactional
    public String addProduct(String mainID, String name, String virtual, String catalog, String picURL, String unit,
                             String description, String productTypeID, String brandID, String title,
                             String metaKeywords, String metaDescription, String currentOperator, String productDetail,
                             String isRecommend, String subTitle, String saleService, String productProp,
                             String productType, String isBuy, String isLowPrice,String isSales,String supplierID,String supplierCategoryID,String productStandPrice,Integer status, String freightTemplateID) {

        notNull(name, "name is null");
        notNull(virtual, "virtual is null");
        notNull(mainID, "mainID is null");
        notNull(brandID, "brandID is null");
        notNull(productDetail, "productDetail is null");
        notNull(supplierCategoryID, "supplierCategoryID is null");
        notNull(supplierID, "supplierID is null");
        CodeConfigVO codeConfigVO = systemCodeDAO.findCodeConfigByID(Constant.PRODUCTID);
        if (codeConfigVO != null) {
            mainID = codeConfigVO.getCodeEx() + GenerationNum.getRandomNumber(9);
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setVirtual(Integer.parseInt(virtual));
        productDTO.setCatalog(1);
        productDTO.setPicURL(picURL);
        productDTO.setUnit(unit);
        productDTO.setStatus(status);
        productDTO.setDescription(description);
        productDTO.setProductTypeID(productTypeID);
        productDTO.setBrandID(brandID);
        productDTO.setTitle(title);
        productDTO.setMetaDescription(metaDescription);
        productDTO.setMetaKeywords(metaKeywords);
       // productDTO.setStatus(Constant.product_status_down);
        productDTO.setMainID(mainID);
        productDTO.setCreator(currentOperator);
        productDTO.setSupplierID(supplierID);
        productDTO.setStandPrice(productStandPrice);
        productDTO.setSupplierCategoryID(supplierCategoryID);
        // productDTO.setIsRecommend(Integer.valueOf(isRecommend));
        productDTO.setQrcodeurl(this.qrCodeProduct(mainID));
        productDTO.setSubTitle(subTitle);
        productDTO.setSaleService(saleService);
        productDTO.setProductProp(productProp);
        productDTO.setFreightTemplateID(freightTemplateID);
        //productDTO.setProductType(Integer.valueOf(productType));
      //  productDTO.setIsBuy(Integer.valueOf(isBuy));
        //productDTO.setIsLowPrice(Integer.valueOf(isLowPrice));
       // productDTO.setIsSales(Integer.valueOf(isSales));
        Integer count = productDAO.insertProduct(productDTO);
        if (count == 1) {
            if (StringUtil.isNotEmpty(productDetail)) {
                String[] productDetails = productDetail.split("\\|");
                for (String detail : productDetails) {
                	if(!"on".equals(detail)){
                		if(detail.split("_").length>1){
	                    ProductDetailDTO productDetailDTO = new ProductDetailDTO();
	                    productDetailDTO.setproductID(mainID);
	                    productDetailDTO.setproductPropID(detail.split("_")[2]);
	                    productDetailDTO.setpTypePPropID(detail.split("_")[0]);
	                    productDetailDTO.setproductPropValue(detail.split("_")[1]);
	                    productDetailDTO.setCreator(currentOperator);
	                    productDetailDAO.insertProductDetail(productDetailDTO);
                		}
                	}
                }
            }
            return mainID;
        }

        return null;
    }
    /**
     * 根据prodcutVo.type
     * 1根据原厂件对应的副厂件数据
     * 2根据副厂件对应的数据原厂件
     * @param productVo
     * @return
     */
    @Override
    public List<ProductVO> findProductByOEM(ProductVO productVo) {
    	return productDAO.findProductByOEM(productVo);
    }
}
