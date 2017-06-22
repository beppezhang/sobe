package com.kpluswebup.mall.web.product.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.content.service.AdvertService;
import com.kpluswebup.web.domain.CmsAdvertDTO;
import com.kpluswebup.web.domain.FavoriteDTO;
import com.kpluswebup.web.domain.HistroyDTO;
import com.kpluswebup.web.domain.ItemConsultingDTO;
import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.ItemDetailDTO;
import com.kpluswebup.web.domain.ItemReviewDTO;
import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.domain.ShoppingCartDTO;
import com.kpluswebup.web.domain.SupplierDTO;
import com.kpluswebup.web.member.service.FavoriteService;
import com.kpluswebup.web.member.service.FocusService;
import com.kpluswebup.web.member.service.HistroyService;
import com.kpluswebup.web.member.service.ItemConsultingService;
import com.kpluswebup.web.member.service.ItemReviewService;
import com.kpluswebup.web.member.service.ShoppingCartSerivce;
import com.kpluswebup.web.service.BrandService;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.PartsCategoryNormalService;
import com.kpluswebup.web.service.PartsCategoryService;
import com.kpluswebup.web.service.ProductCategoryService;
import com.kpluswebup.web.service.ProductPictureService;
import com.kpluswebup.web.service.ProductPropService;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.service.SalesOrderLineService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.service.VehicleTypeService;
import com.kpluswebup.web.service.VehicletypePartscategoryService;
import com.kpluswebup.web.supplier.service.SupplierService;
import com.kpluswebup.web.vo.BrandVO;
import com.kpluswebup.web.vo.CmsAdvertVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.FavoriteVO;
import com.kpluswebup.web.vo.FocusEntity;
import com.kpluswebup.web.vo.ItemConsultingVO;
import com.kpluswebup.web.vo.ItemDetailVO;
import com.kpluswebup.web.vo.ItemReviewVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.PartsCategoryVo;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.ProductDetailVO;
import com.kpluswebup.web.vo.ProductPictureVO;
import com.kpluswebup.web.vo.ProductTypeProductPropVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.ShoppingCartVO;
import com.kpluswebup.web.vo.StatisticsInfoVO;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpluswebup.web.vo.VehicleTypeVO;
import com.kpluswebup.web.vo.VehicletypePartscategoryEntity;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.ClassTools;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.GenerationNum;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.TpartsUtils;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/mall/product")
public class ProductControl extends BaseController {

    public ProductControl(VehicleTypeService vehicleTypeService,
			FocusService focusService) {
		super();
		this.vehicleTypeService = vehicleTypeService;
		this.focusService = focusService;
	}
    public ProductControl() {
	}

	@Autowired
    private AdvertService          advertService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private BrandService           brandService;

    @Autowired
    private ProductService         productService;

    @Autowired
    private ProductPictureService  productPictureService;

    @Autowired
    private ItemService            itemService;

    @Autowired
    private ItemReviewService      itemReviewService;

    @Autowired
    private ItemConsultingService  itemConsultingService;

    @Autowired
    private ProductPropService     productPropService;

    @Autowired
    private FavoriteService        favoriteService;

    @Autowired
    private HistroyService         histroyService;

    @Autowired
    private ShoppingCartSerivce    shoppingCartSerivce;

    @Autowired
    private SalesOrderService      salesOrderService;
    @Autowired
    private CachedClient           cachedClient;
    @Autowired
    private SalesOrderLineService  salesOrderLineService;
    @Autowired
    private SupplierService  supplierService;
    
    /**********************sxc****************************/
    @Autowired
    private PartsCategoryService partsCategoryService;
    @Autowired
    private VehicleTypeService vehicleTypeService;
    @Autowired
    private FocusService focusService;
    @Autowired
    private VehicletypePartscategoryService vehicletypePartscategoryService;
    
    @Autowired
    private PartsCategoryNormalService partsCategoryNormalService;
    
    @RequestMapping("productCat")
    public ModelAndView productCat(String catID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/products_cat");

        CmsAdvertDTO cmsAdvertDTO = new CmsAdvertDTO();
        cmsAdvertDTO.setPosionID("index");
        cmsAdvertDTO.setSerachDate(new Date());
        cmsAdvertDTO.setProductCategoryID(catID);
        cmsAdvertDTO.setPageSize(5l);
        List<CmsAdvertVO> cmsAdvertList = advertService.findAdvertByPagination(cmsAdvertDTO);
        modelAndView.addObject("cmsAdvertList", cmsAdvertList);

        ProductCategoryVO productCategoryVO = productCategoryService.findProductCategoryByMainID(catID);
        modelAndView.addObject("productCategoryVO", productCategoryVO);

        List<BrandVO> brandList = new ArrayList<BrandVO>();
        if (catID != null && catID != "") {
            brandList = brandService.findAllBrandListBycatID(catID);
        } else {
            brandList = brandService.findAllBrandList();
        }
        modelAndView.addObject("brandList", brandList);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setCatID(catID);
        productDTO.setOrderByClause("order by  p.modifyTime desc");
        productDTO.setPageSize(4l);
        productDTO.setStatus(Constant.product_status_top);
        productDTO.setIsRecommend(1);
        List<ProductVO> productList = productService.searchProducItemCat(productDTO);
        modelAndView.addObject("productList", productList);

        List<ProductCategoryVO> productCatList = productCategoryService.findProductCatByParentID(catID);
        for (ProductCategoryVO productCatVO : productCatList) {
            ProductDTO product = new ProductDTO();
            product.setCatID(productCatVO.getMainID());
            product.setOrderByClause("order by  p.modifyTime desc");
            product.setPageSize(4l);
            product.setStatus(Constant.product_status_top);
            List<ProductVO> products = productService.searchProducItemCat(product);
            productCatVO.setProducts(products);
        }
        modelAndView.addObject("productCatList", productCatList);
        modelAndView.addObject("productDTO", productDTO);

        return modelAndView;
    }

    @RequestMapping("productList")
    public ModelAndView productList(String catID, String brandID, String startSalePrice, String endSalePrice,
                                    String pageNo, String pTypePPropIDs, String orderByClause, String pname,
                                    String bname) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/products_list");

        List<BrandVO> brandList = brandService.findAllBrandList();
        ItemDTO itemDto = new ItemDTO();
        if (StringUtil.isNotEmpty(catID)) {
            ProductCategoryVO productCategoryVO = productCategoryService.findProductCategoryByMainID(catID);
            brandList = brandService.findAllBrandListBycatID(catID);
            modelAndView.addObject("productCategoryVO", productCategoryVO);
        }
        if (StringUtil.isNotEmpty(brandID)) {
            itemDto.setBrandID(brandID);
            BrandVO brandVO = brandService.findBrandByMainID(brandID);
            modelAndView.addObject("brandVO", brandVO);
        }
        if (StringUtil.isNotEmpty(pname)) {
            itemDto.setName(pname);
            modelAndView.addObject("pname", pname);
        }
        if (StringUtil.isNotEmpty(bname)) {
            itemDto.setBrandName(bname);
            brandList = brandService.findAllBrandListByName(bname);
            modelAndView.addObject("bname", bname);
        }
        modelAndView.addObject("brandList", brandList);
        if (StringUtil.isNumberic(pageNo)) {
            itemDto.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(pTypePPropIDs)) {
            modelAndView.addObject("pTypePPropIDs", pTypePPropIDs);
        }
        if (StringUtil.isNotEmpty(orderByClause)) {
            itemDto.setOrderByClause(orderByClause);
            modelAndView.addObject("orderByClause", orderByClause);
        } else {
            orderByClause = "order by c.sortOrder desc,c.name,c.brandID,c.productID";
            itemDto.setOrderByClause(orderByClause);
        }
        if (StringUtil.isNotEmpty(startSalePrice)) {
            itemDto.setStartSalePrice(startSalePrice);
        }
        if (StringUtil.isNotEmpty(endSalePrice)) {
            itemDto.setEndSalePrice(endSalePrice);
        }
        itemDto.setPageSize(16l);
        modelAndView.addObject("itemDto", itemDto);
        ArrayList<List<ItemVO>> itsList = new ArrayList<List<ItemVO>>();
        List<ItemVO> its = itemService.finItemsByItemDto(itemDto);// itemService.finditemsByProductID(null);
        itsList.add(its);
        modelAndView.addObject("its", itsList);

        List<ProductTypeProductPropVO> productTypeProductPropList = productPropService.seachProductprop(catID, brandID);
        modelAndView.addObject("productTypeProductPropList", productTypeProductPropList);

        List<ProductVO> list = productService.searchProductByIsRemmond();
        modelAndView.addObject("list", list);
        modelAndView.addObject("catID", catID);
        return modelAndView;
    }

    @RequestMapping("searchProductList-old")
    public ModelAndView searchProductList(String catID, String brandID, String startSalePrice, String endSalePrice,
                                          String pageNo, String pTypePPropIDs, String orderByClause,
                                          Integer productType, String name, String cType,String dataData) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/search_list");
        /**
         * 品牌信息 根据产品类型查询品牌信息
         */
        List<BrandVO> brandList = new ArrayList<BrandVO>();
        ItemDTO itemDto = new ItemDTO();
        if (StringUtil.isNotEmpty(catID)) {
            brandList = brandService.findAllBrandListBycatID(catID);
            ProductCategoryVO productCategoryVO = productCategoryService.findProductCategoryByMainID(catID);
            modelAndView.addObject("productCategoryVO", productCategoryVO);
            /*if (StringUtil.isNotEmpty(cType)) {
                if (cType.equals("1")) {
                    String productTypeIDs = productCategoryVO.getProductTypeID();
                    List<ProductCategoryVO> list2 = productCategoryService.findProductCatByParentID(productCategoryVO.getMainID());
                    for (ProductCategoryVO productCategoryVO2 : list2) {
                        if (!productTypeIDs.contains(productCategoryVO2.getProductTypeID())) {
                            productTypeIDs += " or c.productTypeID=" + productCategoryVO2.getProductTypeID();
                        }
                        List<ProductCategoryVO> list3 = productCategoryService.findProductCatByParentID(productCategoryVO2.getMainID());
                        for (ProductCategoryVO productCategoryVO3 : list3) {
                            if (!productTypeIDs.contains(productCategoryVO3.getProductTypeID())) {
                                productTypeIDs += " or c.productTypeID=" + productCategoryVO3.getProductTypeID();
                            }
                        }
                    }
                    itemDto.setProductTypeID(productTypeIDs);
                } else if (cType.equals("2")) {
                    String productTypeIDs = productCategoryVO.getProductTypeID();
                    List<ProductCategoryVO> list3 = productCategoryService.findProductCatByParentID(productCategoryVO.getMainID());
                    for (ProductCategoryVO productCategoryVO2 : list3) {
                        if (!productTypeIDs.contains(productCategoryVO2.getProductTypeID())) {
                            productTypeIDs += " or c.productTypeID=" + productCategoryVO2.getProductTypeID();
                        }
                    }
                    itemDto.setProductTypeID(productTypeIDs);
                } else if (cType.equals("3")) {
                    String productTypeIDs = productCategoryVO.getProductTypeID();
                    itemDto.setProductTypeID(productTypeIDs);
                }
            }*/
            itemDto.setProductTypeID(catID);
            modelAndView.addObject("catID", catID);
            modelAndView.addObject("cType", cType);
        } else {
            brandList = brandService.findAllBrandList();
        }
        modelAndView.addObject("brandList", brandList);
        /**
         * 关键字查询
         */
        if (StringUtil.isNotEmpty(name)) {
        	try {
				if(StringUtil.isNotEmpty(name)&&name.equals(new String(name.getBytes("iso-8859-1"), "iso-8859-1")))
				{
					name=new String(name.getBytes("iso-8859-1"),"utf-8");
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            itemDto.setName(name);
            modelAndView.addObject("pname", name);
        }
        if (StringUtil.isNotEmpty(brandID)) {
            itemDto.setBrandID(brandID);
            BrandVO brandVO = brandService.findBrandByMainID(brandID);
            modelAndView.addObject("brandVO", brandVO);
        }
        if (StringUtil.isNumberic(pageNo)) {
            itemDto.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(orderByClause)) {
            itemDto.setOrderByClause(orderByClause);
            modelAndView.addObject("orderByClause", orderByClause);
        } else {
            orderByClause = "order by c.sortOrder desc,c.name,c.brandID,c.productID";
            itemDto.setOrderByClause(orderByClause);
        }
        if (StringUtil.isNotEmpty(startSalePrice)) {
            itemDto.setStartSalePrice(startSalePrice);
        }
        if (StringUtil.isNotEmpty(endSalePrice)) {
            itemDto.setEndSalePrice(endSalePrice);
        }
        if (StringUtil.isNotEmpty(productType + "")) {
            itemDto.setProductType(productType);
            modelAndView.addObject("productType", productType);
        }
        // product.setStatus(Constant.product_status_top);
        itemDto.setPageSize(16l);
        // List<ProductVO> products = productService.searchProducItemCat(product);
        // modelAndView.addObject("products", products);
        
        /**
         * 查询 商品 与 小图
         */
        modelAndView.addObject("itemDto", itemDto);
        ArrayList<List<ItemVO>> itsList = new ArrayList<List<ItemVO>>();
        Map<String, List<ProductPictureVO>> itemsMap = new HashMap<String, List<ProductPictureVO>>();
        // for (ProductVO p : products) {
        List<ItemVO> its = itemService.finItemsByItemDto(itemDto);
        if (null != its && its.size() > 0) {
            for (ItemVO item : its) {
                List<ProductPictureVO> pictures = productPictureService.findProductPictureByProductID(item.getMainID(),
                                                                                                      item.getProductID());
                itemsMap.put(item.getProductID(), pictures);
            }
            // itsList.add(its);
        }
        // }
        modelAndView.addObject("itemsMap", itemsMap);
        modelAndView.addObject("its", its);
        /**
         * 商品销售排行
         */
        List<ProductVO> volumeProducts = productService.getVolumeProducts();// 销售排行
        modelAndView.addObject("volumeProducts", volumeProducts);

        /**
         * 根据当前用户查询历史浏览记录
         */
        CustomerVO customer = this.findUserInfo();
        ProductDTO productDto = new ProductDTO();
        if (null != customer) {
            if (StringUtil.isNotEmpty(customer.getMainID())) {
                productDto.setCustomerID(customer.getMainID());
            }
        }

        productDto.setPageSize(4l);
        List<ProductVO> historyProducts = productService.findHistoryProductsByPagination(productDto);// 浏览历史记录
        modelAndView.addObject("historyProducts", historyProducts);

        /**
         * 查询产品类型属性信息
         */
        List<ProductTypeProductPropVO> productTypeProductPropList = productPropService.seachProductprop(catID, brandID);
        modelAndView.addObject("productTypeProductPropList", productTypeProductPropList);

        List<ProductVO> list = productService.searchProductByIsRemmond();
        modelAndView.addObject("list", list);
        modelAndView.addObject("catID", catID);
        if(StringUtil.isNotEmpty(dataData)){
    		modelAndView.addObject("dataData", dataData);
    	}
    	
        return modelAndView;
    }
    public void test()
    {
    	
    }
    /**
     * 查询产品
     * @author sxc
     * @param catID   产品类目
     * @param brandID 品牌
     * @param startSalePrice 开始价格   10000  例如   10000 - 50000
     * @param endSalePrice   结束价格   50000  例如   10000 - 50000
     * @param pageNo         页码    
     * @param pTypePPropIDs  产品类型属性ID
     * @param orderByClause  排序字符串
     * @param productType    产品类型ID
     * @param name           关键字查询
     * @param cType
     * @param dataData       按页码跳转时 排序箭头会恢复原样
     * @param vehicleTypeId   整车车型id（也可叫 整车产品id）
     * @param partsCategoryId 配件分类id
     * @return
     */
    @RequestMapping("searchProductList-tparts-old")
    public ModelAndView searchProductListTparts(String catID, String brandID, String startSalePrice, String endSalePrice,
                                          String pageNo, String pTypePPropIDs, String orderByClause,
                                          Integer productType, String name, String cType,String dataData,String vehicleTypeId,String partsCategoryId,String partsCategoryFLevel,String searchModel,String partsCategoryAncestorId) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/search_list");
        
        StopWatch swAll = new StopWatch();
        swAll.start("start searchProductListTparts");
        System.out.println("数据库时间="+productService.findDBDate());
        /**
         * 配件产品实体类
         */
        ProductDTO productDTO = new ProductDTO();
        
        
        /**
         * 整车车型sssssssssssssssssssssssssssssssssssss
         */        
        if(StringUtil.isEmpty(searchModel))
        	searchModel = "product";
        

        VehicleTypeVO vehicleTypeVOForVin = null;
        if(StringUtil.isNotEmpty(searchModel))
        {
        	
        	if("vin".equals(searchModel))
        	{
            	if(StringUtil.isNotEmpty(name))
            	{
            		try {
        				if(StringUtil.isNotEmpty(name)&&name.equals(new String(name.getBytes("iso-8859-1"), "iso-8859-1")))
        				{
        					name=new String(name.getBytes("iso-8859-1"),"utf-8");
        				}
        			} catch (UnsupportedEncodingException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			} 
            		
            		vehicleTypeVOForVin = vehicleTypeService.findByVin(name);
            		if(vehicleTypeVOForVin!=null)
            		{
                        productDTO.setVehicleTypeId(vehicleTypeVOForVin.getMainID());
                        modelAndView.addObject("vehicleTypeVO", vehicleTypeVOForVin);          			
            		}
            		
            		
       		
            		
            		modelAndView.addObject("vin", name);
                        
            	          
            	}       		
        	}else if("product".equals(searchModel))
        	{
        		if (StringUtil.isNotEmpty(vehicleTypeId)) {
                    /**--Tparts--**/
                    productDTO.setVehicleTypeId(vehicleTypeId);
                    VehicleTypeVO vehicleTypeVO = vehicleTypeService.findByMainID(vehicleTypeId);
                    modelAndView.addObject("vehicleTypeVO", vehicleTypeVO);
                    
                }         		
        	}else if("oem".equals(searchModel))
        	{
        		if(StringUtil.isNotEmpty(name))
            	{
            		try {
        				if(StringUtil.isNotEmpty(name)&&name.equals(new String(name.getBytes("iso-8859-1"), "iso-8859-1")))
        				{
        					name=new String(name.getBytes("iso-8859-1"),"utf-8");
        				}
        			} catch (UnsupportedEncodingException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			} 
            		productDTO.setName(name);
            		productDTO.setMainIDList(vehicleTypeService.findProductIdsByOEM(name));  
                    modelAndView.addObject("pname", name);            		
            	}

        	}
            modelAndView.addObject("searchModel", searchModel.equals("vin") ? "product" : searchModel);
      	
        }else
        {
    		if (StringUtil.isNotEmpty(vehicleTypeId)) {
                /**--Tparts--**/
                productDTO.setVehicleTypeId(vehicleTypeId);
                VehicleTypeVO vehicleTypeVO = vehicleTypeService.findByMainID(vehicleTypeId);
                modelAndView.addObject("vehicleTypeVO", vehicleTypeVO);
            }                   	
        }
      
        /**
         * 配件分类
         */
        if(StringUtil.isEmpty(partsCategoryFLevel))
        {
        	partsCategoryFLevel = "1";
        }
        PartsCategoryVo partsCategoryVo = null;
        Integer staticFLevel = Integer.parseInt(getStaticProp().getProperty("parts.category.maxlevel"));
        if (StringUtil.isNotEmpty(partsCategoryId)) {
        
          if(StringUtil.isEmpty(partsCategoryId))
        	  partsCategoryId = partsCategoryAncestorId;
          /**--Tparts--**/
          productDTO.setPartsCategoryId(partsCategoryId);
          partsCategoryVo = partsCategoryService.findPartsCategoryByMainID(partsCategoryId);
          modelAndView.addObject("partsCategoryVo",partsCategoryVo);
          //查找上层分类
          if(partsCategoryVo.getFlevel() == 2)
          {
        	  modelAndView.addObject("partsCategoryVoAncestor",partsCategoryService.findPartsCategoryByMainID(partsCategoryVo.getAncestorID()));
          }
          
          
          if(2 == staticFLevel)
          {
        	  if(1 == partsCategoryVo.getFlevel())
        	  {
        		  productDTO.setPartsCategoryMainIDList(partsCategoryService.findPartsCategoryBottomForTwo(partsCategoryId));
        	  }else{
        		  productDTO.setPartsCategoryMainIDList(Arrays.asList(partsCategoryId));
        	  }
          }else if(3 == staticFLevel)
          {
        	  if(1 == partsCategoryVo.getFlevel())
        	  {
        		  productDTO.setPartsCategoryMainIDList(partsCategoryService.findPartsCategoryBottomForThird(partsCategoryId));
        	  }else if(2 == partsCategoryVo.getFlevel())
        	  {
        		  productDTO.setPartsCategoryMainIDList(partsCategoryService.findPartsCategoryBottomForTwo(partsCategoryId));
        	  }else
        	  {
        		  productDTO.setPartsCategoryMainIDList(Arrays.asList(partsCategoryId));
        	  }
          }
        }
        /**
         * 
         * 品牌信息 根据产品类型查询品牌信息 
         * 原品牌是指 整车品牌，现在是指配件品牌
         */
        StopWatch sw = new StopWatch();
        sw.start("品牌信息 根据产品类型查询品牌信息 ");
        ItemDTO itemDto = new ItemDTO();
        /** sxc 2015-0928 性能调试
        List<BrandVO> brandList = new ArrayList<BrandVO>();
        ItemDTO itemDto = new ItemDTO();
        if (StringUtil.isNotEmpty(catID)) {
            brandList = brandService.findAllBrandListBycatID(catID);
            ProductCategoryVO productCategoryVO = productCategoryService.findProductCategoryByMainID(catID);
            modelAndView.addObject("productCategoryVO", productCategoryVO);*/
            /*if (StringUtil.isNotEmpty(cType)) {
                if (cType.equals("1")) {
                    String productTypeIDs = productCategoryVO.getProductTypeID();
                    List<ProductCategoryVO> list2 = productCategoryService.findProductCatByParentID(productCategoryVO.getMainID());
                    for (ProductCategoryVO productCategoryVO2 : list2) {
                        if (!productTypeIDs.contains(productCategoryVO2.getProductTypeID())) {
                            productTypeIDs += " or c.productTypeID=" + productCategoryVO2.getProductTypeID();
                        }
                        List<ProductCategoryVO> list3 = productCategoryService.findProductCatByParentID(productCategoryVO2.getMainID());
                        for (ProductCategoryVO productCategoryVO3 : list3) {
                            if (!productTypeIDs.contains(productCategoryVO3.getProductTypeID())) {
                                productTypeIDs += " or c.productTypeID=" + productCategoryVO3.getProductTypeID();
                            }
                        }
                    }
                    itemDto.setProductTypeID(productTypeIDs);
                } else if (cType.equals("2")) {
                    String productTypeIDs = productCategoryVO.getProductTypeID();
                    List<ProductCategoryVO> list3 = productCategoryService.findProductCatByParentID(productCategoryVO.getMainID());
                    for (ProductCategoryVO productCategoryVO2 : list3) {
                        if (!productTypeIDs.contains(productCategoryVO2.getProductTypeID())) {
                            productTypeIDs += " or c.productTypeID=" + productCategoryVO2.getProductTypeID();
                        }
                    }
                    itemDto.setProductTypeID(productTypeIDs);
                } else if (cType.equals("3")) {
                    String productTypeIDs = productCategoryVO.getProductTypeID();
                    itemDto.setProductTypeID(productTypeIDs);
                }
            }*/
        /** sxc 2015-0928 性能调试
            itemDto.setProductTypeID(catID);
            modelAndView.addObject("catID", catID);
            modelAndView.addObject("cType", cType);
        } else {
            brandList = brandService.findAllBrandList();
        }
        modelAndView.addObject("brandList", brandList);
        */
        TpartsUtils.stopWatchStopRunning(sw);
        sw.start("关键字查询下面一系列set");
        /**
         * 关键字查询
         */
        
        if (StringUtil.isNotEmpty(name)) {
        	
        	if("product".equals(searchModel)) // parts 业务
        	{
            	try {
    				if(StringUtil.isNotEmpty(name)&&name.equals(new String(name.getBytes("iso-8859-1"), "iso-8859-1")))
    				{
    					name=new String(name.getBytes("iso-8859-1"),"utf-8");
    				}
    			} catch (UnsupportedEncodingException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
                itemDto.setName(name);
                modelAndView.addObject("pname", name);
                /**--Tparts--**/
                productDTO.setName(name);        		
        	}

        }
        if (StringUtil.isNotEmpty(brandID)) {
            itemDto.setBrandID(brandID);
            BrandVO brandVO = brandService.findBrandByMainID(brandID);
            modelAndView.addObject("brandVO", brandVO);
            /**--Tparts--**/
            productDTO.setBrandID(brandID);
        }
        if (StringUtil.isNumberic(pageNo)) {
            itemDto.setPageNo(Long.parseLong(pageNo));
            /**--Tparts--**/
            productDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(orderByClause)) {
            itemDto.setOrderByClause(orderByClause);
            modelAndView.addObject("orderByClause", orderByClause);
            /**--Tparts--**/
            productDTO.setOrderByClause(orderByClause);
        } else {
            orderByClause = "order by c.sortOrder desc,c.name,c.brandID,c.productID";
            itemDto.setOrderByClause(orderByClause);
            /**--Tparts--**/
            String orderByClauseTprats = "order by p.name desc";
            productDTO.setOrderByClause(orderByClauseTprats);            
        }
        if (StringUtil.isNotEmpty(startSalePrice)) {
            itemDto.setStartSalePrice(startSalePrice);
        }
        if (StringUtil.isNotEmpty(endSalePrice)) {
            itemDto.setEndSalePrice(endSalePrice);
        }
        if (StringUtil.isNotEmpty(productType + "")) {
            itemDto.setProductType(productType);
            modelAndView.addObject("productType", productType);
        }
        // product.setStatus(Constant.product_status_top);
        itemDto.setPageSize(16l);
        // List<ProductVO> products = productService.searchProducItemCat(product);
        // modelAndView.addObject("products", products);
        TpartsUtils.stopWatchStopRunning(sw);
        /**
         * 查询 商品 与 小图
         */
        sw.start("查询 商品 与 小图");
        modelAndView.addObject("itemDto", itemDto);
        /** sxc 2015-0928 性能调试
        ArrayList<List<ItemVO>> itsList = new ArrayList<List<ItemVO>>();
        Map<String, List<ProductPictureVO>> itemsMap = new HashMap<String, List<ProductPictureVO>>();
        // for (ProductVO p : products) {
        List<ItemVO> its = itemService.finItemsByItemDto(itemDto);
        if (null != its && its.size() > 0) {
            for (ItemVO item : its) {
                List<ProductPictureVO> pictures = productPictureService.findProductPictureByProductID(item.getMainID(),
                                                                                                      item.getProductID());
                itemsMap.put(item.getProductID(), pictures);
            }
            // itsList.add(its);
        }
        // }
        modelAndView.addObject("itemsMap", itemsMap);
        modelAndView.addObject("its", its);
        sxc**/
        TpartsUtils.stopWatchStopRunning(sw);
        /**
         * 商品销售排行
         */
        sw.start("商品销售排行");
        /** sxc 2015-0928 性能调试
        List<ProductVO> volumeProducts = productService.getVolumeProducts();// 销售排行
        modelAndView.addObject("volumeProducts", volumeProducts);
        sxc**/
        TpartsUtils.stopWatchStopRunning(sw);
        /**
         * 根据当前用户查询历史浏览记录
         */
        sw.start("根据当前用户查询历史浏览记录");
        /** sxc 2015-0928 性能调试 **/
        CustomerVO customer = this.findUserInfo();
        ProductDTO productDto = new ProductDTO();
        if (null != customer) {
            if (StringUtil.isNotEmpty(customer.getMainID())) {
                productDto.setCustomerID(customer.getMainID());
            }
        }

        productDto.setPageSize(8l);
        List<ProductVO> historyProducts = productService.findHistoryProductsByPagination(productDto);// 浏览历史记录
        modelAndView.addObject("historyProducts", historyProducts);
        
        TpartsUtils.stopWatchStopRunning(sw);
        /**
         * 查询产品类型属性信息
         */
        sw.start("查询产品类型属性信息");
        /** sxc 2015-0928 性能调试
        List<ProductTypeProductPropVO> productTypeProductPropList = productPropService.seachProductprop(catID, brandID);
        modelAndView.addObject("productTypeProductPropList", productTypeProductPropList);
        sxc**/
        TpartsUtils.stopWatchStopRunning(sw);
        sw.start("searchProductByIsRemmond");
        /** sxc 2015-0928 性能调试
        List<ProductVO> list = productService.searchProductByIsRemmond();
        modelAndView.addObject("list", list);
        modelAndView.addObject("catID", catID);
        if(StringUtil.isNotEmpty(dataData)){
    		modelAndView.addObject("dataData", dataData);
    	}
    	sxc**/
        TpartsUtils.stopWatchStopRunning(sw);
        //********************************************************sxc
        /**
         * 配件品牌
         */
        sw.start("My 配件品牌");
        List<Map> brandParts = brandService.findBrandParts();
        modelAndView.addObject("brandPartss",brandParts);
        TpartsUtils.stopWatchStopRunning(sw);
        //productDTO.setPageSize(1l);
        /**
         * 配件产品 
         */
        productDTO.setSearchModel(searchModel);
//        StopWatch sw = new StopWatch();
//        sw.start("开始筛选配件数据");
        Integer lucene = Integer.parseInt(getStaticProp().getProperty("lucene.flag"));
        productDTO.setPageNoCountForLuncene(Integer.parseInt(getStaticProp().getProperty("lucene.pageno.normal")));
        
        List<ProductVO> products = null;
        if(lucene==0)
        {
        	products = productService.findProductListByLucene(productDTO);
        }else
        {
        	products = productService.findProductListTparts(productDTO);	
        }
        
        //stopWatchStopRunning(sw);
        //System.out.println(sw.prettyPrint());
//        if(StringUtil.isNotEmpty(searchModel) && "vin".equals(searchModel) && vehicleTypeVOForVin == null)
//        {
//        	productDTO.setPageCount(1l);
//        	products = new ArrayList<ProductVO>();
//        }
//        if(StringUtil.isNotEmpty(searchModel) && "oem".equals(searchModel) && productDTO.getMainIDList().size()==0)
//        {
//        	productDTO.setPageCount(1l);
//        	products = new ArrayList<ProductVO>();
//        }        
        if(products == null)
        	products = new ArrayList<ProductVO>();
        modelAndView.addObject("productsParts", products);
        modelAndView.addObject("productDTO", productDTO);
        /**
         * 配件分类
         */
        sw.start("My 配件分类");
        List<PartsCategoryVo> partsCategorys = null;
        
        if(StringUtil.isEmpty(partsCategoryId))
        {
        	partsCategorys = partsCategoryService.findPartsCategoryLevelOnly();
        }else if(staticFLevel == Integer.parseInt(partsCategoryFLevel))
        {
//        	partsCategoryVo.setMainID(partsCategoryId);
        	partsCategorys = partsCategoryService.findPartsCategoryByLowLevel(partsCategoryVo);        	
        }
        else
        {
//        	partsCategoryVo.setMainID(partsCategoryId);
//        	partsCategoryVo.setFlevel(partsCategoryVo.getFlevel() + 1);
        	Map m = new HashMap();
        	m.put("flevel", partsCategoryVo.getFlevel() + 1);
        	m.put("mainID", partsCategoryVo.getMainID());
        	partsCategorys = partsCategoryService.findPartsCategoryByParentIDAndFLevel(m);        	
        }
        /* 如果配件分类则清空整车车型 */
        if(StringUtil.isNotEmpty(productDTO.getPartsCategoryId()))
		{
        	//modelAndView.addObject("vehicleTypeVO", null);
		}
        TpartsUtils.stopWatchStopRunning(sw);
        System.out.println(sw.prettyPrint());
        
//        if(Integer.parseInt(partsCategoryFLevel) > 1)
//        {
//
//        }else if(Integer.parseInt(partsCategoryFLevel) == 1)
//        {
//
//        }
//        else
//        {
//        	partsCategoryVo.setMainID(partsCategoryId);
//        	partsCategorys = partsCategoryService.findPartsCategoryByLowLevel(partsCategoryVo);
//        }
        
        modelAndView.addObject("partsCategorys", partsCategorys);
        
        TpartsUtils.stopWatchStopRunning(swAll);
        System.out.println(swAll.prettyPrint());        
        modelAndView.addObject("queryTime", swAll.getTotalTimeSeconds());
        return modelAndView;
    }    
    
    
//	public static enum SearchPageType {
//		CAR,VEHICLETYPE, PARTSCATEGORY_ROOT, PARTSCATEGORY_SECOND
//	}	    
	public static final String CAR = "car";
	public static final String VEHICLETYPE = "vehicleType";
	public static final String PARTSCATEGORY_ROOT = "partsCategory_root";
	public static final String PARTSCATEGORY_SECOND = "partsCategory_second";
    
	
	@RequestMapping("addFocusByvehicleType")
    public @ResponseBody JsonResult ajaxAddFocus(String vehicleTypeId) {
        try {
    		if (StringUtil.isNotEmpty(vehicleTypeId)) {
                /**--Tparts--**/
                VehicleTypeVO vehicleTypeVO = vehicleTypeService.findByMainID(vehicleTypeId);
                /*记录vin查询历史用于我的关注的显示*/
            	if(focusService!=null)
            	{
            		Integer focusType = 1;	//记录关注类型:vehicleType
                	focusService.save(vehicleTypeVO.getName(),vehicleTypeVO.getMainID(), this.findUserInfo(), focusType);        		
            	}            
            }             
            return new JsonResult(ResultCode.NORMAL);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    } 
	
    /**
     * 查询产品
     * @author sxc
     * @param catID   产品类目
     * @param brandID 品牌
     * @param startSalePrice 开始价格   10000  例如   10000 - 50000
     * @param endSalePrice   结束价格   50000  例如   10000 - 50000
     * @param pageNo         页码    
     * @param pTypePPropIDs  产品类型属性ID
     * @param orderByClause  排序字符串
     * @param productType    产品类型ID
     * @param name           关键字查询
     * @param cType
     * @param dataData       按页码跳转时 排序箭头会恢复原样
     * @param vehicleTypeId   整车车型id（也可叫 整车产品id）
     * @param partsCategoryId 配件分类id
     * @param partsCategoryType 配件分类类型参数
     * @return
     */

	public static final String NORMAL_CATEGORY = "normalCategory";		//常用分类
	public static final String ORIGINAL_CATEGORY = "originalCategory";	//原始分类

	@RequestMapping("searchProductList")
    public ModelAndView searchProductListTpartsNew(String catID, String brandID, String startSalePrice, String endSalePrice,
                                          String pageNo, String pTypePPropIDs, String orderByClause,
                                          Integer productType, String name, String cType,String dataData,
                                          String vehicleTypeId,String partsCategoryId,String partsCategoryFLevel,
                                          String searchModel,String partsCategoryAncestorId,String carMainID,
                                          String type,String productCategoryId,String partsCategoryId_parts,
                                          String partsCategoryIdThird,String typeParts,String currentName,
                                          String searchModelVIN,String vehicleModelPanel,String partsCategoryType) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/search_parts");
        
        StopWatch swAll = new StopWatch();
        swAll.start("start searchProductListTparts");
        System.out.println("数据库时间="+productService.findDBDate());
        
        String partsCategoryRootId = null;
        /**
         * 配件产品实体类
         */
        ProductDTO productDTO = new ProductDTO();
        /*品牌加载车系*/
        ProductCategoryVO productCategoryVO = null;
        List<ProductCategoryVO> cars = null;
        if(StringUtil.isNotEmpty(carMainID))
        {
    		productCategoryVO = productCategoryService.findProductCategoryByMainID(carMainID);
    		cars = productCategoryService.findProductCatByParentID(carMainID);        	
    		modelAndView.addObject("cars", cars);
    		modelAndView.addObject("productCategoryVO", productCategoryVO);
    		if(productCategoryVO.getCode().toLowerCase().contains("benz"))
    		{
    			partsCategoryRootId = "00000000-0000-0000-0000-000000000001";
    			modelAndView.addObject("brandType", "BENZ");
    		}else
    		{
    			partsCategoryRootId = "00000000-0000-0000-0000-000000000000";
    			modelAndView.addObject("brandType", "BMW");
    		}
        }
        /*品牌加载车系*/
        
        /*车系加载车型*/
        if(StringUtil.isNotEmpty(productCategoryId))
        {
    		VehicleTypeVO _vehicleTypeVO = new VehicleTypeVO();
    		//查询车型
        	if (StringUtil.isNotEmpty(productCategoryId)) {
        		_vehicleTypeVO.setProductCategoryId(productCategoryId);
        	}
        	List<VehicleTypeVO> vehicleTypeVOs = vehicleTypeService.findVehicleByCategoryId(_vehicleTypeVO);
        	ProductCategoryVO productCategoryVO_vehicle = productCategoryService.findProductCategoryByMainID(productCategoryId);
       		modelAndView.addObject("vehicleTypeVOs", vehicleTypeVOs);
       		modelAndView.addObject("productCategoryVO_vehicle", productCategoryVO_vehicle);		        	
        }

   		
    	/*车系加载车型*/
		VehicletypePartscategoryEntity vpEntity = new VehicletypePartscategoryEntity();        
        /*选定车型*/
		if (StringUtil.isNotEmpty(vehicleTypeId)) {
            /**--Tparts--**/
            productDTO.setVehicleTypeId(vehicleTypeId);
            VehicleTypeVO vehicleTypeVO = vehicleTypeService.findByMainID(vehicleTypeId);
            modelAndView.addObject("vehicleTypeVO", vehicleTypeVO);
            
            vpEntity.setVehicletypeId(vehicleTypeId);
            vpEntity.setFlevel(1);
            /*配件大类*/
//            List<PartsCategoryVo> partsCategoryRoots = partsCategoryService.findPartsCategoryLevelOnlyNew(partsCategoryRootId);
            
            //根据车型加载相应分类,如果分类类型已经确定为原始分类则按原来方式查,否则按常见分类查(刚开始选定车型的时候这里的partsCategory是没有值的,前端会根据需求变化该值
            if (partsCategoryType.equals(ORIGINAL_CATEGORY)){
            	List<VehicletypePartscategoryEntity> partsCategoryRoots = vehicletypePartscategoryService.findByPartscategorysByVehicletype(vpEntity);
            	modelAndView.addObject("partsCategoryRoots", partsCategoryRoots);
            } else {
            	// TODO 目前还没对车型 id 关联，所以无论从哪个车型进入常用备件分类都默认显示第一层
            	List<PartsCategoryVo> partsCategoryRoots = partsCategoryNormalService.findPartsCategoryNormalOneLevel();
            	modelAndView.addObject("partsCategoryRoots", partsCategoryRoots);
            	
            	//选定车后型默认会先进到这，将分类类型设为常见分类(当然，在前端页面进行不同分类筛选之间的切换的时候也会进来这)
            	partsCategoryType = NORMAL_CATEGORY;
            }
            
        }   
		/*选定车型*/

		/*选定配件大类*/
		if (StringUtil.isNotEmpty(partsCategoryId)) {
            productDTO.setPartsCategoryId(partsCategoryId);
            
            /*配件小类*/
//            Map m = new HashMap();
//            m.put("mainID", partsCategoryId);
//            m.put("flevel", partsCategoryVo.getFlevel().intValue()+1);
//            List<PartsCategoryVo> partsCategoryChilds = partsCategoryService.findPartsCategoryByParentIDAndFLevel(m);
            
            /*配件小类-根据车型*/
            if (partsCategoryType.equals(ORIGINAL_CATEGORY)){
                PartsCategoryVo partsCategoryVo = partsCategoryService.findPartsCategoryByMainID(partsCategoryId);
                modelAndView.addObject("partsCategoryVo", partsCategoryVo);

                vpEntity.setFlevel(2);
		        vpEntity.setAncestorID(partsCategoryId);
		        //根据车型加载相应分类
		        List<VehicletypePartscategoryEntity> partsCategoryChilds = vehicletypePartscategoryService.findByPartscategorysByVehicletype(vpEntity);            
		        modelAndView.addObject("partsCategoryChilds", partsCategoryChilds);
            } else {
                modelAndView.addObject("partsCategoryVo", partsCategoryNormalService.findPartsCategoryNormalByMainID(partsCategoryId));

                List<PartsCategoryVo> partsCategoryChilds = partsCategoryNormalService.findPartsCategoryNormalByAncestorID(partsCategoryId);            
		        modelAndView.addObject("partsCategoryChilds", partsCategoryChilds);
            }
            
        }   		
		boolean isProduct = false;
		boolean isThrid = false;
    	/*选定配件小类并查询出配件*/
		if(StringUtil.isNotEmpty(partsCategoryId_parts))
		{
            if (partsCategoryType.equals(ORIGINAL_CATEGORY)){
                vpEntity.setFlevel(3);
                vpEntity.setAncestorID(partsCategoryId_parts);
                //根据车型加载相应分类
                List<VehicletypePartscategoryEntity> partsCategoryThirds = vehicletypePartscategoryService.findByPartscategorysByVehicletype(vpEntity);
                if(partsCategoryThirds!=null && partsCategoryThirds.size()>0)
                {
                	modelAndView.addObject("partsCategoryThirds", partsCategoryThirds);
                	modelAndView.addObject("partsCategoryThirdsSize", partsCategoryThirds.size());
                	
                	if(partsCategoryThirds.size() > 1)
                	{
                    	/*查询第二层*/
                    	modelAndView.addObject("partsCategoryVoParts", partsCategoryService.findPartsCategoryByMainID(partsCategoryId_parts));
                    	isThrid = true;            		
                	}else
                	{
                    	/*查询第二层*/
                    	modelAndView.addObject("partsCategoryVoParts", partsCategoryService.findPartsCategoryByMainID(partsCategoryId_parts));
                		
                		/*如果第三层分类数量为1，直接查询选中第三层分类，显示第三层分类下面的备件*/
                		partsCategoryIdThird = partsCategoryThirds.get(0).getMainID();
                		typeParts = "isTrue";
                		modelAndView.addObject("typeParts", typeParts);
                		modelAndView.addObject("typePartsOne", "typePartsOne");
                	}

                }else
                {
                    productDTO.setPartsCategoryId(partsCategoryId_parts);
                    PartsCategoryVo partsCategoryVoParts = partsCategoryService.findPartsCategoryByMainID(partsCategoryId_parts);
                    modelAndView.addObject("partsCategoryVoParts", partsCategoryVoParts);
                    modelAndView.addObject("partsCategoryChildsParts", null);
                    isProduct = true;
                }
            } else {
                List<PartsCategoryVo> partsCategoryThirds = partsCategoryNormalService.findPartsCategoryNormalByAncestorID(partsCategoryId_parts);
            	
                //对于常见备件分类的三层结构采取和BMW一样的处理方式
                if(partsCategoryThirds.size() > 1){
            		modelAndView.addObject("partsCategoryThirds", partsCategoryThirds);
            		modelAndView.addObject("partsCategoryVoParts", partsCategoryNormalService.findPartsCategoryNormalByMainID(partsCategoryId_parts));
            		isThrid = true;            		
            	} else {
            		modelAndView.addObject("partsCategoryVoParts", partsCategoryNormalService.findPartsCategoryNormalByMainID(partsCategoryId_parts));
            		partsCategoryIdThird = partsCategoryThirds.get(0).getMainID();
            		typeParts = "isTrue";
            		modelAndView.addObject("typeParts", typeParts);
            		modelAndView.addObject("typePartsOne", "typePartsOne");
            	}
            }
		}
		
		/*选定分组大类*/
		if(StringUtil.isNotEmpty(partsCategoryIdThird))
		{
			productDTO.setPartsCategoryId(partsCategoryIdThird);
            if (partsCategoryType.equals(ORIGINAL_CATEGORY)){
            	PartsCategoryVo partsCategoryVoPartsThird = partsCategoryService.findPartsCategoryByMainID(partsCategoryIdThird);
            	modelAndView.addObject("partsCategoryVoPartsThird", partsCategoryVoPartsThird);
            	isProduct = true;
            } else {
            	modelAndView.addObject("partsCategoryVoPartsThird", partsCategoryNormalService.findPartsCategoryNormalByMainID(partsCategoryIdThird));
            	isProduct = true;
            }
		}

		//在分组大类确定之后就可以将 partsCategoryType 回传给页面了,当然,在没选定车型的时候partsCategoryType是没有值的，也就没必要传
		if (partsCategoryType != null) {
			modelAndView.addObject("partsCategoryType", partsCategoryType);
		}
		
		if(isProduct)
		{
			type = "parts_true";
		}else if(StringUtil.isNotEmpty(carMainID) && StringUtil.isEmpty(productCategoryId)
				&& StringUtil.isEmpty(vehicleTypeId)
				&& StringUtil.isEmpty(partsCategoryId) && StringUtil.isEmpty(partsCategoryId_parts))
		{
			type = "car";
		}else if(StringUtil.isNotEmpty(carMainID) && StringUtil.isNotEmpty(productCategoryId)
				&& StringUtil.isEmpty(vehicleTypeId)
				&& StringUtil.isEmpty(partsCategoryId) && StringUtil.isEmpty(partsCategoryId_parts))
		{
			type = "vehicleType";
		}else if(StringUtil.isNotEmpty(carMainID) && StringUtil.isNotEmpty(productCategoryId)
				&& StringUtil.isNotEmpty(vehicleTypeId)
				&& StringUtil.isEmpty(partsCategoryId) && StringUtil.isEmpty(partsCategoryId_parts))
		{
			type = "partsCategory_root";
		}else if(StringUtil.isNotEmpty(carMainID) && StringUtil.isNotEmpty(productCategoryId)
				&& StringUtil.isNotEmpty(vehicleTypeId)
				&& StringUtil.isNotEmpty(partsCategoryId) && StringUtil.isEmpty(partsCategoryId_parts))
		{
			type = "partsCategory_second";
		}else if(isThrid)
		{
			type = "partsCategory_third";
		}
		
        if(StringUtil.isNotEmpty(type))
        {
        	if(type.equals(CAR))
        	{
        		//品牌首次跳转到
        		System.out.println("carMainID="+carMainID);

        		
        		
        	}else if(type.equals(VEHICLETYPE))
        	{

        		
        	}else if(type.equals(PARTSCATEGORY_ROOT))
        	{
        		
        	}else if(type.equals(PARTSCATEGORY_SECOND))
        	{
        		
        	}
        }else
        {
        	// type 空逻辑
        }
		
        modelAndView.addObject("type", type);
        modelAndView.addObject("typeParts", typeParts);
        //控制车型详细信息是否打开
        modelAndView.addObject("vehicleModelPanel", vehicleModelPanel);
        
        /**
         * 整车车型sssssssssssssssssssssssssssssssssssss
         */        
        if(StringUtil.isEmpty(searchModel))
        {
        	searchModel = "product";
        	modelAndView.addObject("searchModel","product");
        }else
        {
        	modelAndView.addObject("searchModel",searchModel);
        }
        
        if(StringUtil.isNotEmpty(name))
        {
        	modelAndView.addObject("pname",name);
        }
        
        if(StringUtil.isNotEmpty(searchModelVIN))
        {
        	searchModel = "vin";
        	modelAndView.addObject("searchModel",searchModel);
        	modelAndView.addObject("pname",searchModelVIN);
        }
        
//
//        VehicleTypeVO vehicleTypeVOForVin = null;
//        if(StringUtil.isNotEmpty(searchModel))
//        {
//        	
//        	if("vin".equals(searchModel))
//        	{
//            	if(StringUtil.isNotEmpty(name))
//            	{
//            		try {
//        				if(StringUtil.isNotEmpty(name)&&name.equals(new String(name.getBytes("iso-8859-1"), "iso-8859-1")))
//        				{
//        					name=new String(name.getBytes("iso-8859-1"),"utf-8");
//        				}
//        			} catch (UnsupportedEncodingException e) {
//        				// TODO Auto-generated catch block
//        				e.printStackTrace();
//        			} 
//            		
//            		vehicleTypeVOForVin = vehicleTypeService.findByVin(name);
//            		if(vehicleTypeVOForVin!=null)
//            		{
//                        productDTO.setVehicleTypeId(vehicleTypeVOForVin.getMainID());
//                        modelAndView.addObject("vehicleTypeVO", vehicleTypeVOForVin);          			
//            		}
//            		modelAndView.addObject("vin", name);
//            	}       		
//        	}else if("product".equals(searchModel))
//        	{
//        		if (StringUtil.isNotEmpty(vehicleTypeId)) {
//                    /**--Tparts--**/
//                    productDTO.setVehicleTypeId(vehicleTypeId);
//                    VehicleTypeVO vehicleTypeVO = vehicleTypeService.findByMainID(vehicleTypeId);
//                    modelAndView.addObject("vehicleTypeVO", vehicleTypeVO);
//                    
//                }         		
//        	}else if("oem".equals(searchModel))
//        	{
//        		if(StringUtil.isNotEmpty(name))
//            	{
//            		try {
//        				if(StringUtil.isNotEmpty(name)&&name.equals(new String(name.getBytes("iso-8859-1"), "iso-8859-1")))
//        				{
//        					name=new String(name.getBytes("iso-8859-1"),"utf-8");
//        				}
//        			} catch (UnsupportedEncodingException e) {
//        				// TODO Auto-generated catch block
//        				e.printStackTrace();
//        			} 
//            		productDTO.setName(name);
//            		productDTO.setMainIDList(vehicleTypeService.findProductIdsByOEM(name));  
//                    modelAndView.addObject("pname", name);            		
//            	}
//
//        	}
//            modelAndView.addObject("searchModel", searchModel.equals("vin") ? "product" : searchModel);
//      	
//        }else
//        {
//    		if (StringUtil.isNotEmpty(vehicleTypeId)) {
//                /**--Tparts--**/
//                productDTO.setVehicleTypeId(vehicleTypeId);
//                VehicleTypeVO vehicleTypeVO = vehicleTypeService.findByMainID(vehicleTypeId);
//                modelAndView.addObject("vehicleTypeVO", vehicleTypeVO);
//            }                   	
//        }
      
        /**
         * 配件分类
         */
//        if(StringUtil.isEmpty(partsCategoryFLevel))
//        {
//        	partsCategoryFLevel = "1";
//        }
//        PartsCategoryVo partsCategoryVo = null;
//        Integer staticFLevel = Integer.parseInt(getStaticProp().getProperty("parts.category.maxlevel"));
//        if (StringUtil.isNotEmpty(partsCategoryId)) {
//        
//          if(StringUtil.isEmpty(partsCategoryId))
//        	  partsCategoryId = partsCategoryAncestorId;
//          /**--Tparts--**/
//          productDTO.setPartsCategoryId(partsCategoryId);
//          partsCategoryVo = partsCategoryService.findPartsCategoryByMainID(partsCategoryId);
//          modelAndView.addObject("partsCategoryVo",partsCategoryVo);
//          //查找上层分类
//          if(partsCategoryVo.getFlevel() == 2)
//          {
//        	  modelAndView.addObject("partsCategoryVoAncestor",partsCategoryService.findPartsCategoryByMainID(partsCategoryVo.getAncestorID()));
//          }
//          
//          
//          if(2 == staticFLevel)
//          {
//        	  if(1 == partsCategoryVo.getFlevel())
//        	  {
//        		  productDTO.setPartsCategoryMainIDList(partsCategoryService.findPartsCategoryBottomForTwo(partsCategoryId));
//        	  }else{
//        		  productDTO.setPartsCategoryMainIDList(Arrays.asList(partsCategoryId));
//        	  }
//          }else if(3 == staticFLevel)
//          {
//        	  if(1 == partsCategoryVo.getFlevel())
//        	  {
//        		  productDTO.setPartsCategoryMainIDList(partsCategoryService.findPartsCategoryBottomForThird(partsCategoryId));
//        	  }else if(2 == partsCategoryVo.getFlevel())
//        	  {
//        		  productDTO.setPartsCategoryMainIDList(partsCategoryService.findPartsCategoryBottomForTwo(partsCategoryId));
//        	  }else
//        	  {
//        		  productDTO.setPartsCategoryMainIDList(Arrays.asList(partsCategoryId));
//        	  }
//          }
//        }
       
        StopWatch sw = new StopWatch();
        sw.start("关键字查询下面一系列set");
        ItemDTO itemDto = new ItemDTO();
        /**
         * 关键字查询
         */
        
        if (StringUtil.isNotEmpty(currentName)) {
        	
//        	if("product".equals(searchModel)) // parts 业务
        	{
            	try {
    				if(StringUtil.isNotEmpty(currentName)&&currentName.equals(new String(currentName.getBytes("iso-8859-1"), "iso-8859-1")))
    				{
    					currentName=new String(currentName.getBytes("iso-8859-1"),"utf-8");
    				}
    			} catch (UnsupportedEncodingException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
                itemDto.setName(currentName);
                modelAndView.addObject("keyName", currentName);
                /**--Tparts--**/
                productDTO.setName(currentName);        		
        	}

        }
        if (StringUtil.isNotEmpty(brandID)) {
            itemDto.setBrandID(brandID);
            BrandVO brandVO = brandService.findBrandByMainID(brandID);
            modelAndView.addObject("brandVO", brandVO);
            /**--Tparts--**/
            productDTO.setBrandID(brandID);
        }
        if (StringUtil.isNumberic(pageNo)) {
            itemDto.setPageNo(Long.parseLong(pageNo));
            /**--Tparts--**/
            productDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(orderByClause)) {
            itemDto.setOrderByClause(orderByClause);
            modelAndView.addObject("orderByClause", orderByClause);
            /**--Tparts--**/
            productDTO.setOrderByClause(orderByClause);
        } else {
            orderByClause = "order by c.sortOrder desc,c.name,c.brandID,c.productID";
            itemDto.setOrderByClause(orderByClause);
            /**--Tparts--**/
            String orderByClauseTprats = "order by p.name desc";
            productDTO.setOrderByClause(orderByClauseTprats);            
        }
        if (StringUtil.isNotEmpty(startSalePrice)) {
            itemDto.setStartSalePrice(startSalePrice);
        }
        if (StringUtil.isNotEmpty(endSalePrice)) {
            itemDto.setEndSalePrice(endSalePrice);
        }
        if (StringUtil.isNotEmpty(productType + "")) {
            itemDto.setProductType(productType);
            modelAndView.addObject("productType", productType);
        }
        // product.setStatus(Constant.product_status_top);
        itemDto.setPageSize(16l);
        // List<ProductVO> products = productService.searchProducItemCat(product);
        // modelAndView.addObject("products", products);
        TpartsUtils.stopWatchStopRunning(sw);
       
       
       
        /**
         * 配件产品 
         */
        /*加载配件*/
        if(StringUtil.isNotEmpty(typeParts) && "isTrue".equals(typeParts))
        {
          productDTO.setSearchModel(searchModel);
          productDTO.setPageSize(30l);
          Integer lucene = Integer.parseInt(getStaticProp().getProperty("lucene.flag"));
          productDTO.setPageNoCountForLuncene(Integer.parseInt(getStaticProp().getProperty("lucene.pageno.normal")));
          
          List<ProductVO> products = null;
          if(lucene==0)
          {
          	products = productService.findProductListByLucene(productDTO);
          }else
          {
          	products = productService.findProductListTparts(productDTO);	
          }
          
       
          if(products == null)
          	products = new ArrayList<ProductVO>();
          modelAndView.addObject("productsParts", products);
          modelAndView.addObject("productDTO", productDTO);        	
        }        

//        /**
//         * 配件分类
//         */
//        sw.start("My 配件分类");
//        List<PartsCategoryVo> partsCategorys = null;
//        
//        if(StringUtil.isEmpty(partsCategoryId))
//        {
//        	partsCategorys = partsCategoryService.findPartsCategoryLevelOnly();
//        }else if(staticFLevel == Integer.parseInt(partsCategoryFLevel))
//        {
//        	partsCategorys = partsCategoryService.findPartsCategoryByLowLevel(partsCategoryVo);        	
//        }
//        else
//        {
//        	Map m = new HashMap();
//        	m.put("flevel", partsCategoryVo.getFlevel() + 1);
//        	m.put("mainID", partsCategoryVo.getMainID());
//        	partsCategorys = partsCategoryService.findPartsCategoryByParentIDAndFLevel(m);        	
//        }
//        /* 如果配件分类则清空整车车型 */
//        if(StringUtil.isNotEmpty(productDTO.getPartsCategoryId()))
//		{
//        	//modelAndView.addObject("vehicleTypeVO", null);
//		}
//        TpartsUtils.stopWatchStopRunning(sw);
//        System.out.println(sw.prettyPrint());
        
//        if(Integer.parseInt(partsCategoryFLevel) > 1)
//        {
//
//        }else if(Integer.parseInt(partsCategoryFLevel) == 1)
//        {
//
//        }
//        else
//        {
//        	partsCategoryVo.setMainID(partsCategoryId);
//        	partsCategorys = partsCategoryService.findPartsCategoryByLowLevel(partsCategoryVo);
//        }
        
//        modelAndView.addObject("partsCategorys", partsCategorys);
		
		//登录用户“我的关注”
		CustomerVO customerVO = this.findUserInfo();
		if (customerVO != null){
			FocusEntity focusEntity = new FocusEntity();
			focusEntity.setCustomerID(customerVO.getMainID());
			focusEntity.setLimitNum(5);
			
			//车型搜索历史
			focusEntity.setFocusType(1);
			List<FocusEntity> vehicleTypeSearchList = focusService.findByFocusEntity(focusEntity);
			modelAndView.addObject("vehicleTypeSearchList", vehicleTypeSearchList);

			//VIN搜索历史
			focusEntity.setFocusType(2);
			List<FocusEntity> VINSearchList = focusService.findByFocusEntity(focusEntity);
			modelAndView.addObject("VINSearchList", VINSearchList);
		
			//OEM搜索历史
			focusEntity.setFocusType(3);
			List<FocusEntity> OEMSearchList = focusService.findByFocusEntity(focusEntity);
			modelAndView.addObject("OEMSearchList", OEMSearchList);
		}
        
        TpartsUtils.stopWatchStopRunning(swAll);
        System.out.println(swAll.prettyPrint());        
        modelAndView.addObject("queryTime", swAll.getTotalTimeSeconds());
        return modelAndView;
    }        

    
    /**
     * 查询产品
     * OEM
     * @return
     */
    @RequestMapping("searchProductListByOEM")
    public ModelAndView searchProductListByOEM(String oem) {
    	CustomerVO customerVO = this.findUserInfo();
    	ProductDTO productDTO = new ProductDTO(); 
    	List<ProductVO> products = null;
    	if(StringUtil.isNotEmpty(oem))
    	{
            productDTO.setSearchModel("oem");
            productDTO.setName(oem);
            productDTO.setPageSize(10l);
            Integer lucene = Integer.parseInt(getStaticProp().getProperty("lucene.flag"));
            productDTO.setPageNoCountForLuncene(Integer.parseInt(getStaticProp().getProperty("lucene.pageno.normal")));
            
            if(lucene==0)
            {
            	products = productService.findProductListByLucene(productDTO);
            }else
            {
            	products = productService.findProductListTparts(productDTO);	
            }
    	}
    	String productId = null;
    	ProductVO _productVO = null;
    	FocusEntity focusEntity = null;
    	if(products!=null && products.size()>0)
    	{
        	for (ProductVO productVO : products) {
    			if(productVO.getCode().equals(oem))
    			{
    				_productVO = productVO;
    				productId = productVO.getMainID();
    				break;
    			}
    		}    	
        	/*记录vin查询历史用于我的关注的显示*/
        	if(focusService!=null)
        	{
        		Integer focusType = 3;	//记录关注类型:OEM
            	//focusService.save(_productVO.getCode(),_productVO.getMainID(), this.findUserInfo(), focusType);        		
            	focusService.save(_productVO.getName(),_productVO.getCode(), this.findUserInfo(), focusType);        		
        	}           	

    	}
    	ModelAndView modelAndView = new ModelAndView("redirect:/mall/item/productInfo.htm?productId="+productId+"&searchModel=oem&oem="+oem);
        if(StringUtil.isNotEmpty(productId))
        {
        	return modelAndView;	
        }else
        {
        	ModelAndView modelAndViewFound = this.newModelAndView();
        	modelAndViewFound.setViewName("errors/nothingFound");
        	modelAndViewFound.addObject("pname", oem);
        	modelAndViewFound.addObject("searchModel", "oem");        	
        	return modelAndViewFound;        	
        }
    }          
    

    /**
     * 查询产品
     * VIN
     * @return
     */
    @RequestMapping("searchProductListByVIN")
    public ModelAndView searchProductListByVIN(String vin) {
    	
    	VehicleTypeVO vehicleTypeVO = vehicleTypeService.findByVin(vin);
    	ProductCategoryVO productCategoryVOChild = null;
    	ProductCategoryVO productCategoryVORoot = null;
        if(vehicleTypeVO!=null)
        {
        	productCategoryVOChild = productCategoryService.findProductCategoryById(vehicleTypeVO.getProductCategoryId());
        	
        	productCategoryVORoot = productCategoryService.findProductCategoryById(productCategoryVOChild.getParentID());
        	
        	/*记录vin查询历史用于我的关注的显示*/
        	if(focusService!=null)
        	{
        		Integer focusType = 2;	//记录关注类型:vin
            	//focusService.save(vehicleTypeVO.getName(),vehicleTypeVO.getMainID(), this.findUserInfo(), focusType);        		
            	focusService.save(vin,vin, this.findUserInfo(), focusType);        		
        	}        	
        }

    	
        if(vehicleTypeVO!=null&&productCategoryVORoot!=null&&productCategoryVOChild!=null)
        {
        	return new ModelAndView("redirect:/mall/product/searchProductList.htm?searchModelVIN="+vin+"&vehicleTypeId="+vehicleTypeVO.getMainID()
        			+"&carMainID="+productCategoryVORoot.getMainID()+"&type=partsCategory_root&productCategoryId="+productCategoryVOChild.getMainID());	
        }else
        {
        	ModelAndView modelAndView = this.newModelAndView();
        	modelAndView.setViewName("errors/nothingFound");
        	modelAndView.addObject("pname", vin);
        	modelAndView.addObject("searchModel", "vin");
        	return modelAndView;
        }
    }             

    /**
     * 查询产品
     * VehicleType
     * @return
     */
    @RequestMapping("searchProductListByVehicleType")
    public ModelAndView searchProductListByVehicleType(String vehicleTypeId) {
    	
    	VehicleTypeVO vehicleTypeVO = vehicleTypeService.findByMainID(vehicleTypeId);
    	ProductCategoryVO productCategoryVOChild = null;
    	ProductCategoryVO productCategoryVORoot = null;
        if(vehicleTypeVO!=null)
        {
        	productCategoryVOChild = productCategoryService.findProductCategoryById(vehicleTypeVO.getProductCategoryId());
        	
        	productCategoryVORoot = productCategoryService.findProductCategoryById(productCategoryVOChild.getParentID());
        	
        	/*记录vin查询历史用于我的关注的显示*/
        	if(focusService!=null)
        	{
        		Integer focusType = 1;	//记录关注类型:vehicleType
            	focusService.save(vehicleTypeVO.getName(),vehicleTypeVO.getMainID(), this.findUserInfo(), focusType);        		
        	}        	
        }

    	
        if(vehicleTypeVO!=null&&productCategoryVORoot!=null&&productCategoryVOChild!=null)
        {
        	return new ModelAndView("redirect:/mall/product/searchProductList.htm?vehicleTypeId="+vehicleTypeVO.getMainID()
        			+"&carMainID="+productCategoryVORoot.getMainID()+"&type=partsCategory_root&productCategoryId="+productCategoryVOChild.getMainID());	
        }else
        {
        	ModelAndView modelAndView = this.newModelAndView();
        	modelAndView.setViewName("errors/nothingFound");
        	return modelAndView;
        }
    }      
    /**
     * 查询产品
     * VehicleType for 我的关注
     * @return
     */
    @RequestMapping("searchProductListByForFocus")
    public ModelAndView searchProductListByForFocus(String referenceID,String type) {
    	ModelAndView modelAndView = checkLogin(null);
    	if(modelAndView!=null)
    	{
    		return modelAndView;
    	}
    	if("vehicleType".equals(type))
    	{
    		modelAndView = searchProductListByVehicleType(referenceID);
    	}else if("vin".equals(type))
    	{
    		modelAndView = searchProductListByVIN(referenceID);
    	}else if("oem".equals(type))
    	{
    		modelAndView = searchProductListByOEM(referenceID);
    	}
    	return modelAndView;
    }     
    
    
    /**
     * 获取配件分类下级节点
     * @param productCategoryId
     * @param pageNo
     * @param serchName
     * @return
     */
    @RequestMapping("getpartscategory.do")
	public @ResponseBody JsonResult get(String mainID)
	{
    	List<PartsCategoryVo> partsCategorysChild = partsCategoryService.findPartsCategoryByParentId(mainID);
		Map data = new HashMap();
		data.put("partsCategorysChild", partsCategorysChild);
		return new JsonResult(data);
	}	
    
    
    
    /**
     * @author  zhoulei
     * 查找店铺
     * @param name
     * @param pageNo
     * @return
     */
    @RequestMapping("searchShopList")
	public ModelAndView searchShopList(String name,String pageNo,String orderByClause,String dataData) {
    	 if (StringUtil.isNotEmpty(name)) {
         	try {
 				if(StringUtil.isNotEmpty(name)&&name.equals(new String(name.getBytes("iso-8859-1"), "iso-8859-1")))
 				{
 					name=new String(name.getBytes("iso-8859-1"),"utf-8");
 				}
 			} catch (UnsupportedEncodingException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
         }
		    ModelAndView modelAndView = this.newModelAndView();
		    modelAndView.setViewName("screen/product/search_shop");
		    List<ProductVO> volumeProducts = productService.getVolumeProducts();// 销售排行
	        modelAndView.addObject("volumeProducts", volumeProducts);

	        CustomerVO customer = this.findUserInfo();
	        ProductDTO productDto = new ProductDTO();
	        if (null != customer) {
	            if (StringUtil.isNotEmpty(customer.getMainID())) {
	                productDto.setCustomerID(customer.getMainID());
	            }
	        }
	        productDto.setPageSize(4l);
	        List<ProductVO> historyProducts = productService.findHistoryProductsByPagination(productDto);// 浏览历史记录
	        modelAndView.addObject("historyProducts", historyProducts);
	        List<SupplierVO> searchShopList = new ArrayList<SupplierVO>();
	    	SupplierDTO supplierDTO = new SupplierDTO();
	    	supplierDTO.setCompanyName(name);
	    	supplierDTO.setPageSize(6l);
        	if (StringUtil.isNumberic(pageNo)) {
        	     supplierDTO.setPageNo(Long.parseLong(pageNo));
            }
        	if (StringUtil.isNotEmpty(orderByClause)) {
        	    supplierDTO.setOrderByClause(orderByClause);
            } else {
                supplierDTO.setOrderByClause("order by item.salesVolume asc");
            }
        	modelAndView.addObject("orderByClause", orderByClause);
        	if(StringUtil.isNotEmpty(dataData)){
        		modelAndView.addObject("dataData", dataData);
        	}
        	
	    	Map<String ,List<ProductVO>> shopListMap = new HashMap<String ,List<ProductVO>>();
	    	List<ProductVO> list = supplierService.findSupplierMap(supplierDTO);
	    	for(ProductVO productVO:list){
	    		List<ProductVO> newList = new ArrayList<ProductVO>();
	    		 String sid1= productVO.getSupplierID();
                 for(ProductVO productVO1:list){
                 	//当前店铺对应的购物车集合
                 	String sid2 = productVO1.getSupplierID();
                 	if(sid1.equals(sid2)){
                 		newList.add(productVO1);
                 	}
                 }
	    		shopListMap.put(productVO.getSupplierID(), newList);
	    	}
	    	//findSupplierMap
	        searchShopList = supplierService.findSupplier(supplierDTO);
	        modelAndView.addObject("searchShopList", searchShopList);
	        modelAndView.addObject("supplierDTO", supplierDTO); 
	        modelAndView.addObject("shopListMap", shopListMap);  
	        modelAndView.addObject("type", "sear1");  
		return modelAndView;
	}
    
    /**
     * 判断是否有过提交记录
     * @param itemID
     * @param productID
     * @return
     */
    @RequestMapping("checkIsSubReviewed")
    public @ResponseBody JsonResult checkIsSubReviewed(String itemID, String productID,String salesOrderID) {
    	ItemReviewDTO itemReviewDTO = new ItemReviewDTO();
        CustomerVO customer = this.findUserInfo();
        if (null != customer) {
        	itemReviewDTO.setProductID(productID);
            itemReviewDTO.setStatus(2);//
            itemReviewDTO.setItemID(itemID);
            itemReviewDTO.setSalesOrderID(salesOrderID);
            itemReviewDTO.setCustomerID(customer.getMainID());
            List<ItemReviewVO> list = itemReviewService.findReviews(itemReviewDTO);
            if(null != list && list.size() > 0){
            	return JsonResult.create(ResultCode.NORMAL);
            }else {
				return JsonResult.create(ResultCode.ERROR_BUY);
			}
        }
        return JsonResult.create(ResultCode.ERROR_LOGIN);
	}
    
    // 提交评价
    @RequestMapping("submitReview")
    public String submitReview(String content, String productID, Integer score,String itemID) {
    	 ItemReviewDTO itemReviewDTO = new ItemReviewDTO();
         CustomerVO customer = this.findUserInfo();
         itemReviewDTO.setScore(score);
         try {
 			if(StringUtil.isNotEmpty(content)&&content.equals(new String(content.getBytes("iso-8859-1"), "iso-8859-1")))
 			{
 			content=new String(content.getBytes("iso-8859-1"),"utf-8");
 			itemReviewDTO.setContent(content);
 			}
 		} catch (UnsupportedEncodingException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         
         //Random a = new  Random(5);
         if (null != customer) {
         	itemReviewDTO.setProductID(productID);
         	itemReviewDTO.setReplytime(new Date());
            itemReviewDTO.setStatus(2);//
            itemReviewDTO.setItemID(itemID);
            itemReviewDTO.setCustomerID(customer.getMainID());
        	itemReviewDTO.setMainID(GenerationNum.getRandomNumber(6)+customer.getMainID());
         } else {
             return "redirect:/mall/member/toLogin.htm";
         }
         itemReviewDTO.setStatus(2);//
         itemReviewService.addtReview(itemReviewDTO);
         return "redirect:/mall/product/productDetail.htm?productID=" + productID+"&itemID="+itemID;
    }    
    
//    // 提交评价
//    @RequestMapping("submitReview")
//    public String submitReview(String content, String productID, Integer score) {
//        ItemReviewDTO itemReviewDTO = new ItemReviewDTO();
//        CustomerVO customer = this.findUserInfo();
//        itemReviewDTO.setScore(score);
//        itemReviewDTO.setContent(content);
//        itemReviewDTO.setProductID(productID);
//        itemReviewDTO.setReplytime(new Date());
//        if (null != customer) {
//            itemReviewDTO.setMainID(customer.getMainID());
//        } else {
//            return "redirect:/mall/member/toLogin.htm";
//        }
//        itemReviewService.addtReview(itemReviewDTO);
//        return "redirect:/mall/product/productDetail.htm?productID=" + productID;
//    }

    @RequestMapping("productDetail")
    public ModelAndView productDetailTparts(String productID, String value1, String value2, String itemID) {

    	try {
			if(StringUtil.isNotEmpty(value2)&&value2.equals(new String(value2.getBytes("iso-8859-1"), "iso-8859-1")))
			{
				value2=new String(value2.getBytes("iso-8859-1"),"utf-8");
			}
			if(StringUtil.isNotEmpty(value1)&&value1.equals(new String(value1.getBytes("iso-8859-1"), "iso-8859-1")))
			{
				value1=new String(value1.getBytes("iso-8859-1"),"utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	
    	
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/products_detail");
        List<ProductVO> volumeProducts = productService.getVolumeProducts();// 销售排行
        modelAndView.addObject("volumeProducts", volumeProducts);
        CustomerVO customer = this.findUserInfo();
        modelAndView.addObject("salesOrders", salesOrderLineService.findSalesOrderLineByProductId(productID));// 销售记录
        modelAndView.addObject("oneItemID", itemID);// 一键订货商品ID
        ProductDTO productDto = new ProductDTO();
        if (null != customer) {
            if (StringUtil.isNotEmpty(customer.getMainID())) {
                productDto.setCustomerID(customer.getMainID());
            }
        }

        productDto.setPageSize(4l);
        
        ProductVO productVO = productService.findProductByMainIDNew(productID);
        modelAndView.addObject("productVO",productVO);
        /*速度慢，有待优化*/
        List<ProductVO> historyProducts = productService.findHistoryProductsByPagination(productDto);// 浏览历史记录
        modelAndView.addObject("historyProducts", historyProducts);

        List<ProductVO> guessLikeProductList = productService.findProductYourLove();// 猜您喜欢的宝贝
        modelAndView.addObject("list", guessLikeProductList);
        
        /***************sxc***********/
        
        ProductVO product = productService.findProductByMainIDNew(productID);
        //ProductVO product = productService.findProductByMainID(productID);
        //目前结构产品上没有供应商
        ItemVO _itemVO = itemService.findItemById(itemID);
        SupplierVO supplierVO =   supplierService.findSupplierByID(_itemVO.getSupplierID());
        modelAndView.addObject("supplierVO", supplierVO);
        List<ProductDetailVO> pProps = productService.findProductDetailList(productID);
        if (pProps != null && pProps.size() > 0) {
            for (ProductDetailVO prop : pProps) {
                List<ProductDetailVO> pDetailList = productService.findProductDetailPropValue(productID,
                                                                                              prop.getpTypePPropID());
                String productPropValue = "";
                if (pDetailList != null && pDetailList.size() > 0) {
                    for (ProductDetailVO productDetailVO : pDetailList) {
                        productPropValue += productDetailVO.getproductPropValue() + ",";
                    }
                    prop.setValues(productPropValue.split(","));
                }
            }
            modelAndView.addObject("productPropTypes", pProps);
        }
        modelAndView.addObject("productID", productID);
        if (product != null) {
            if (StringUtil.isNotEmpty(product.getDescription())) {
                product.setDescription(product.getDescription().replaceAll("<img src=\"/", "<img src=\"" + domainImg + "/"));
            }
            modelAndView.addObject("product", product);

            List<ItemDetailVO> props = itemService.findproductProps(productID);
            if (props != null && props.size() > 0) {
                for (ItemDetailVO prop : props) {
                    List<ItemDetailVO> detailList = itemService.findItemDetailPropValue(productID,
                                                                                        prop.getpTypeIPropID());
                    String itemPropValue = "";
                    if (detailList != null && detailList.size() > 0) {
                        for (ItemDetailVO itemDetailVO : detailList) {
                            itemPropValue += itemDetailVO.getItemPropValue() + ",";
                        }
                    }
                    prop.setValues(itemPropValue.split(","));
                }
            }
            modelAndView.addObject("props", props);

            ItemVO item = null;
            if (value1 == null && value2 == null) {
                ItemDTO itemdto = new ItemDTO();
                itemdto.setMainID(itemID);
                itemdto.setProductID(productID);
                //item = itemService.findItemDetailByProductID(itemdto);
                item = itemService.findItemDetailByProductIDTparts(itemdto);
                //List<ItemVO> its = itemService.finditemsByProductID(productID); // 默认取得产品下的第一个商品
               // if (its != null && its.size() > 0) {
                   // item = its.get(0);
                modelAndView.addObject("item", item);

                //造当月销量和累计销量
        		SimpleDateFormat presentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//当前日期
        		SimpleDateFormat monthStartDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//月起始日期
        		
        		//借助Calendar类造出当月起始时间
        		Calendar cal = Calendar.getInstance();
        		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));

        		SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
                salesOrderDTO.setFromDate(monthStartDate.format(cal.getTime()));
                salesOrderDTO.setEndDate(presentDate.format(new Date()));
                salesOrderDTO.setItemID(item.getMainID());
                StatisticsInfoVO statisticsInfoVO = itemService.countMonthSales(salesOrderDTO);//查出当月销量
                StatisticsInfoVO statisticsInfoVO2 = itemService.countTotalSales(salesOrderDTO);//查出当月销量
                modelAndView.addObject("monthSaleCount", statisticsInfoVO.getMonthSaleCount());
                modelAndView.addObject("totalSaleCount", statisticsInfoVO2.getTotalSaleCount());

                    List<ItemDetailVO> itemProps = itemService.findItemProps(item.getMainID());
                    modelAndView.addObject("itemProps", itemProps);
                    if (customer != null) {
                        boolean isfavorite = favoriteService.isFavorite(customer.getMainID(), item.getProductID());
                        modelAndView.addObject("favorite", isfavorite);
                    }
                    if (customer != null) {
                        boolean isHistroy = histroyService.isHistroy(customer.getMainID(), item.getProductID());
                        if (!isHistroy) {
                            HistroyDTO histroyDTO = new HistroyDTO();
                            histroyDTO.setCustomerID(customer.getMainID());
                            histroyDTO.setProductID(productID);
                            histroyDTO.setItemID(item.getMainID());
                            histroyDTO.setIsDelete(0);
                            histroyService.insertHistroy(histroyDTO);
                        }
                    }
               // }
            } else {
                if (value1.equals("-1")) {
                    value1 = null;
                }
                if (value2.equals("-1")) {
                    value2 = null;
                }
                ItemDetailDTO dto = new ItemDetailDTO();
                dto.setValue1(value1);
                dto.setValue2(value2);
                dto.setProductID(productID);
                item = itemService.findItemByValues(dto);
                if (item != null && item.getMainID() != null) {
                    modelAndView.addObject("item", item);
                    List<ItemDetailVO> itemProps = itemService.findItemProps(item.getMainID());
                    modelAndView.addObject("itemProps", itemProps);
                    if (customer != null) {
                        boolean isfavorite = favoriteService.isFavorite(customer.getMainID(), item.getProductID());
                        modelAndView.addObject("favorite", isfavorite);
                    }
                    if (customer != null) {
                        boolean isHistroy = histroyService.isHistroy(customer.getMainID(), item.getProductID());
                        if (!isHistroy) {
                            HistroyDTO histroyDTO = new HistroyDTO();
                            histroyDTO.setCustomerID(customer.getMainID());
                            histroyDTO.setProductID(productID);
                            histroyDTO.setItemID(item.getMainID());
                            histroyDTO.setIsDelete(0);
                            histroyService.insertHistroy(histroyDTO);
                        }
                    }
                } else {
                    List<ItemDetailVO> itemProps = new ArrayList<ItemDetailVO>();
                    ItemDetailVO d1 = new ItemDetailVO();
                    d1.setItemPropValue(value1);
                    ItemDetailVO d2 = new ItemDetailVO();
                    d2.setItemPropValue(value2);
                    itemProps.add(d1);
                    itemProps.add(d2);
                    modelAndView.addObject("item", null);
                    modelAndView.addObject("itemProps", itemProps);
                }
            }

            if (item != null) {
                List<ProductPictureVO> pictures = productPictureService.findProductPictureByProductID(item.getMainID(),  productID);
                modelAndView.addObject("pictures", pictures);
            }
            List<ProductDetailVO> productProps = productService.findProductDetailList(productID);
            modelAndView.addObject("productProps", productProps);
            // 类别
            BrandVO brand = brandService.findBrandByMainID(product.getBrandID());
            modelAndView.addObject("brand", brand);

            /************************************评价开始******************************************/
            ItemReviewDTO dto = new ItemReviewDTO();
            dto.setProductID(productID);
            dto.setStatus(2);
            List<ItemReviewVO> reviews = itemReviewService.findReviews(dto);
            modelAndView.addObject("reviews", reviews);
            Integer all = reviews.size();
            Integer good = 0; // 好评
            Integer middle = 0; // 中评
            Integer bad = 0; // 差评
            Integer allScore = 0; // 总分
            Double allReviewScore = 5d; // 总评分
            Double goodDegree = 1d; // 好评度
            Double middleDegree = 0d; // 中评度
            Double badDegree = 0d; // 差评度
            if (reviews != null && reviews.size() > 0) {
                for (ItemReviewVO re : reviews) {
                    if (re.getScore() > 3) {
                        good = good + 1;
                    } else if (re.getScore() < 2) {
                        bad = bad + 1;
                    } else {
                        middle = middle + 1;
                    }
                    allScore = allScore + re.getScore();
                }
            }
            modelAndView.addObject("all", all);
            modelAndView.addObject("good", good);
            modelAndView.addObject("middle", middle);
            modelAndView.addObject("bad", bad);
            if (all != 0) {
                allReviewScore = (double) Math.round(Double.valueOf(allScore) / all);
            }
            modelAndView.addObject("allReviewScore", allReviewScore);
            if (all != 0) {
                goodDegree = Double.valueOf(good) / all;
                if (goodDegree.toString().split("\\.")[1].length() == 2) {
                    Float goods = (float) (Math.round(goodDegree * 10)) / 10;
                    goodDegree = goods.doubleValue();
                }
                middleDegree = Double.valueOf(middle) / all;
                badDegree = Double.valueOf(bad) / all;
            }
            modelAndView.addObject("goodDegree", goodDegree * 100);
            modelAndView.addObject("middleDegree", middleDegree * 100);
            modelAndView.addObject("badDegree", badDegree * 100);

            ItemConsultingDTO itemConsultingDTO = new ItemConsultingDTO();
            itemConsultingDTO.setProductID(productID);
            List<ItemConsultingVO> consultings = itemConsultingService.findConsults(itemConsultingDTO);
            modelAndView.addObject("consultings", consultings);
            /************************************评价结束******************************************/
            
            
            FavoriteDTO favoriteDTO = new FavoriteDTO();
            favoriteDTO.setProductID(productID);
            Long fCount = favoriteService.findCountByitemIdOrProductId(favoriteDTO);
            modelAndView.addObject("fCount", fCount);

            List<ItemVO> items = itemService.finditems(productID); // 取得同类型的商品4个
            modelAndView.addObject("items", items);
            modelAndView.addObject("orderType", 0);
            CmsAdvertDTO cms = new CmsAdvertDTO();
            cms.setPosionID("product_detail");
            List<CmsAdvertVO> advertList = advertService.findAdvertByPagination(cms);
            modelAndView.addObject("advertList", advertList);
        } else {
            modelAndView.setViewName("errors/notFound");
        }

        return modelAndView;
    }    
    
    @RequestMapping("productDetail-old")
    public ModelAndView productDetail(String productID, String value1, String value2, String itemID) {

        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/products_detail");
        List<ProductVO> volumeProducts = productService.getVolumeProducts();// 销售排行
        modelAndView.addObject("volumeProducts", volumeProducts);
        CustomerVO customer = this.findUserInfo();
        modelAndView.addObject("salesOrders", salesOrderLineService.findSalesOrderLineByProductId(productID));// 销售记录
        modelAndView.addObject("oneItemID", itemID);// 一键订货商品ID
        ProductDTO productDto = new ProductDTO();
        if (null != customer) {
            if (StringUtil.isNotEmpty(customer.getMainID())) {
                productDto.setCustomerID(customer.getMainID());
            }
        }

        productDto.setPageSize(4l);
        List<ProductVO> historyProducts = productService.findHistoryProductsByPagination(productDto);// 浏览历史记录
        modelAndView.addObject("historyProducts", historyProducts);

        List<ProductVO> guessLikeProductList = productService.findProductYourLove();// 猜您喜欢的宝贝
        modelAndView.addObject("list", guessLikeProductList);
        
        /***************sxc***********/
        
        ProductVO product = productService.findProductByMainIDNew(productID);
        //ProductVO product = productService.findProductByMainID(productID);
        //目前结构产品上没有供应商
//        SupplierVO supplierVO =   supplierService.findSupplierByID(product.getSupplierID());
//        modelAndView.addObject("supplierVO", supplierVO);
        List<ProductDetailVO> pProps = productService.findProductDetailList(productID);
        if (pProps != null && pProps.size() > 0) {
            for (ProductDetailVO prop : pProps) {
                List<ProductDetailVO> pDetailList = productService.findProductDetailPropValue(productID,
                                                                                              prop.getpTypePPropID());
                String productPropValue = "";
                if (pDetailList != null && pDetailList.size() > 0) {
                    for (ProductDetailVO productDetailVO : pDetailList) {
                        productPropValue += productDetailVO.getproductPropValue() + ",";
                    }
                    prop.setValues(productPropValue.split(","));
                }
            }
            modelAndView.addObject("productPropTypes", pProps);
        }
        modelAndView.addObject("productID", productID);
        if (product != null) {
            if (StringUtil.isNotEmpty(product.getDescription())) {
                product.setDescription(product.getDescription().replaceAll("<img src=\"/", "<img src=\"" + domainImg + "/"));
            }
            modelAndView.addObject("product", product);

            List<ItemDetailVO> props = itemService.findproductProps(productID);
            if (props != null && props.size() > 0) {
                for (ItemDetailVO prop : props) {
                    List<ItemDetailVO> detailList = itemService.findItemDetailPropValue(productID,
                                                                                        prop.getpTypeIPropID());
                    String itemPropValue = "";
                    if (detailList != null && detailList.size() > 0) {
                        for (ItemDetailVO itemDetailVO : detailList) {
                            itemPropValue += itemDetailVO.getItemPropValue() + ",";
                        }
                    }
                    prop.setValues(itemPropValue.split(","));
                }
            }
            modelAndView.addObject("props", props);

            ItemVO item = null;
            if (value1 == null && value2 == null) {
                ItemDTO itemdto = new ItemDTO();
                itemdto.setMainID(itemID);
                itemdto.setProductID(productID);
                item = itemService.findItemDetailByProductID(itemdto);
                //List<ItemVO> its = itemService.finditemsByProductID(productID); // 默认取得产品下的第一个商品
               // if (its != null && its.size() > 0) {
                   // item = its.get(0);
                    modelAndView.addObject("item", item);
                    List<ItemDetailVO> itemProps = itemService.findItemProps(item.getMainID());
                    modelAndView.addObject("itemProps", itemProps);
                    if (customer != null) {
                        boolean isfavorite = favoriteService.isFavorite(customer.getMainID(), item.getProductID());
                        modelAndView.addObject("favorite", isfavorite);
                    }
                    if (customer != null) {
                        boolean isHistroy = histroyService.isHistroy(customer.getMainID(), item.getProductID());
                        if (!isHistroy) {
                            HistroyDTO histroyDTO = new HistroyDTO();
                            histroyDTO.setCustomerID(customer.getMainID());
                            histroyDTO.setProductID(productID);
                            histroyDTO.setItemID(item.getMainID());
                            histroyDTO.setIsDelete(0);
                            histroyService.insertHistroy(histroyDTO);
                        }
                    }
               // }
            } else {
                if (value1.equals("-1")) {
                    value1 = null;
                }
                if (value2.equals("-1")) {
                    value2 = null;
                }
                ItemDetailDTO dto = new ItemDetailDTO();
                dto.setValue1(value1);
                dto.setValue2(value2);
                dto.setProductID(productID);
                item = itemService.findItemByValues(dto);
                if (item != null && item.getMainID() != null) {
                    modelAndView.addObject("item", item);
                    List<ItemDetailVO> itemProps = itemService.findItemProps(item.getMainID());
                    modelAndView.addObject("itemProps", itemProps);
                    if (customer != null) {
                        boolean isfavorite = favoriteService.isFavorite(customer.getMainID(), item.getProductID());
                        modelAndView.addObject("favorite", isfavorite);
                    }
                    if (customer != null) {
                        boolean isHistroy = histroyService.isHistroy(customer.getMainID(), item.getProductID());
                        if (!isHistroy) {
                            HistroyDTO histroyDTO = new HistroyDTO();
                            histroyDTO.setCustomerID(customer.getMainID());
                            histroyDTO.setProductID(productID);
                            histroyDTO.setItemID(item.getMainID());
                            histroyDTO.setIsDelete(0);
                            histroyService.insertHistroy(histroyDTO);
                        }
                    }
                } else {
                    List<ItemDetailVO> itemProps = new ArrayList<ItemDetailVO>();
                    ItemDetailVO d1 = new ItemDetailVO();
                    d1.setItemPropValue(value1);
                    ItemDetailVO d2 = new ItemDetailVO();
                    d2.setItemPropValue(value2);
                    itemProps.add(d1);
                    itemProps.add(d2);
                    modelAndView.addObject("item", null);
                    modelAndView.addObject("itemProps", itemProps);
                }
            }

            if (item != null) {
                List<ProductPictureVO> pictures = productPictureService.findProductPictureByProductID(item.getMainID(),  productID);
                modelAndView.addObject("pictures", pictures);
            }
            List<ProductDetailVO> productProps = productService.findProductDetailList(productID);
            modelAndView.addObject("productProps", productProps);
            // 类别
            BrandVO brand = brandService.findBrandByMainID(product.getBrandID());
            modelAndView.addObject("brand", brand);

            FavoriteDTO favoriteDTO = new FavoriteDTO();
            favoriteDTO.setProductID(productID);
            Long fCount = favoriteService.findCountByitemIdOrProductId(favoriteDTO);
            modelAndView.addObject("fCount", fCount);

            List<ItemVO> items = itemService.finditems(productID); // 取得同类型的商品4个
            modelAndView.addObject("items", items);
            modelAndView.addObject("orderType", 0);
            CmsAdvertDTO cms = new CmsAdvertDTO();
            cms.setPosionID("product_detail");
            List<CmsAdvertVO> advertList = advertService.findAdvertByPagination(cms);
            modelAndView.addObject("advertList", advertList);
        } else {
            modelAndView.setViewName("errors/notFound");
        }

        return modelAndView;
    }

    @RequestMapping("ajaxAddCat-old")
    public @ResponseBody
    JsonResult ajaxAddCat(String itemCount, String itemID, String productID, String supplierID,String supplierName) {
        System.out.println(supplierID);
        try {
            CustomerVO customerVO = findUserInfo();
            if (customerVO == null) {
                return new JsonResult(ResultCode.ERROR_LOGIN);
            }
            ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
            shoppingCartDTO.setProductID(productID);
            shoppingCartDTO.setItemID(itemID);
            shoppingCartDTO.setCustomerID(customerVO.getMainID());
            shoppingCartDTO.setType(1);
            shoppingCartDTO.setIsPromotion(0);
            shoppingCartDTO.setSupplierID(supplierID);
            ShoppingCartVO shoppingCartVO = shoppingCartSerivce.findShoppingCartByCustomerItem(shoppingCartDTO);
            if (shoppingCartVO != null) {
                ItemVO itemvo =  itemService.findItemById(itemID);
                if(itemvo.getStock()>=(shoppingCartVO.getItemCount() + Integer.parseInt(itemCount))){
                    shoppingCartDTO.setItemCount(shoppingCartVO.getItemCount() + Integer.parseInt(itemCount));
                    shoppingCartDTO.setId(shoppingCartVO.getId());
                    shoppingCartSerivce.updateShoppingCart(shoppingCartDTO);
                }else{
                    return new JsonResult(ResultCode.ERROR_OUT_SHOPCART);
                }
            } else {
                System.out.println(shoppingCartDTO.getSupplierID());
                SupplierVO supplierVO =   supplierService.findSupplierByID(supplierID);
                shoppingCartDTO.setItemCount(Integer.parseInt(itemCount));
                shoppingCartDTO.setSupplierName(supplierVO.getCompanyName());
                shoppingCartSerivce.addShoppingCart(shoppingCartDTO);
            }
            List<ShoppingCartVO> list = shoppingCartSerivce.findShoppingCart(customerVO.getMainID());
            cachedClient.set(Constant.SHOPPINGCARTCOUNT + customerVO.getMainID(), Constant.EXP, list.size());
            return new JsonResult(ResultCode.NORMAL);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }
    
    
    /**
     * 处理添加购物车时没有跳转到当前页面
     * @param request
     * @param response
     * @return
     * sxc
     */
    @RequestMapping("/toLogin")
    public ModelAndView productDetailDoLogin(HttpServletRequest request, HttpServletResponse response,String productID,String preUrl) {
        	String uuid = this.getCookieUUID(request, response);
        	if(preUrl.contains("---"))
        	{
        		preUrl = preUrl.replace("---", "&");
        	}
            cachedClient.set(Constant.BEFORE_LOGIN_URL + uuid, Constant.EXP, preUrl);
            return new ModelAndView("redirect:/mall/member/toLogin.htm");
    }
    
    /**
     * tparts
     * @param itemCount
     * @param itemID
     * @param productID
     * @param supplierID
     * @param supplierName
     * @return
     */
    @RequestMapping("ajaxAddCat")
    public @ResponseBody
    JsonResult ajaxAddCatTparts(String itemCount, String itemID, String productID, String supplierID,String supplierName) {
        System.out.println(supplierID);
        try {
            CustomerVO customerVO = findUserInfo();
            if (customerVO == null) {
                return new JsonResult(ResultCode.ERROR_LOGIN);
            }
            ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
            shoppingCartDTO.setProductID(productID);
            shoppingCartDTO.setItemID(itemID);
            shoppingCartDTO.setCustomerID(customerVO.getMainID());
            shoppingCartDTO.setType(1);
            shoppingCartDTO.setIsPromotion(0);// 0 false; 非0 true
            shoppingCartDTO.setSupplierID(supplierID);
            ShoppingCartVO shoppingCartVO = shoppingCartSerivce.findShoppingCartByCustomerItem(shoppingCartDTO);
            if (shoppingCartVO != null) {
                ItemVO itemvo =  itemService.findItemById(itemID);
                if(itemvo.getStock()>=(shoppingCartVO.getItemCount() + Integer.parseInt(itemCount))){
                    shoppingCartDTO.setItemCount(shoppingCartVO.getItemCount() + Integer.parseInt(itemCount));
                    shoppingCartDTO.setId(shoppingCartVO.getId());
                    shoppingCartSerivce.updateShoppingCart(shoppingCartDTO);
                }else{
                    return new JsonResult(ResultCode.ERROR_OUT_SHOPCART);
                }
            } else {
                System.out.println(shoppingCartDTO.getSupplierID());
                SupplierVO supplierVO =   supplierService.findSupplierByID(supplierID);
                shoppingCartDTO.setItemCount(Integer.parseInt(itemCount));
                shoppingCartDTO.setSupplierName(supplierVO.getCompanyName());
                shoppingCartSerivce.addShoppingCart(shoppingCartDTO);
            }
            List<ShoppingCartVO> list = shoppingCartSerivce.findShoppingCart(customerVO.getMainID());
            //当前客户购物车里产品种类数量缓存到 memcached
            cachedClient.set(Constant.SHOPPINGCARTCOUNT + customerVO.getMainID(), Constant.EXP, list.size());
            return new JsonResult(ResultCode.NORMAL);
        } catch (Exception e) {
        	e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }    

    @RequestMapping("ajaxZixun")
    public @ResponseBody
    JsonResult ajaxZixun(String content, String itemID, String productID) {
        try {
            CustomerVO customerVO = findUserInfo();
            if (customerVO == null) {
                String url = "";
                if (StringUtil.isNotEmpty(productID)) {
                    url = "/mall/product/productDetail.htm?productID=" + productID +"&itemID" +itemID;
                }
                if (StringUtil.isNotEmpty(itemID)) {
                    url = "/mall/item/itemDetail.htm?itemID=" + itemID;
                }
                cachedClient.set(Constant.BEFORE_LOGIN_URL + randomUUID(), Constant.EXP, url);
                return new JsonResult(ResultCode.ERROR_LOGIN);
            }
            ItemConsultingDTO dto = new ItemConsultingDTO();
            dto.setMainID(UUIDUtil.getUUID());
            dto.setContent(content);
            dto.setItemID(itemID);
            dto.setProductID(productID);
            dto.setCustomerID(customerVO.getMainID());
            itemConsultingService.addItemConsulting(dto);
            return new JsonResult(ResultCode.NORMAL);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping("ajaxReview")
    public @ResponseBody
    JsonResult ajaxReview(String content, String itemID, String productID, String customerID, String score) {
        try {
            ItemReviewDTO dto = new ItemReviewDTO();
            dto.setContent(content);
            dto.setItemID(itemID);
            dto.setProductID(productID);
            dto.setCustomerID(customerID);
            dto.setScore(Integer.parseInt(score));
            dto.setStatus(1);
            dto.setMainID(UUIDUtil.getUUID());
            itemReviewService.addtReview(dto);
            return new JsonResult(ResultCode.NORMAL);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping("ajaxFavorite")
    public @ResponseBody
    JsonResult ajaxFavorite(String itemID, String productID, String customerID) {
        try {
            FavoriteDTO dto = new FavoriteDTO();
            dto.setProductID(productID);
            dto.setItemID(itemID);
            dto.setCustomerID(customerID);
            FavoriteVO favoriteVO = favoriteService.findFavoriteByCustomerItem(dto);
            if (favoriteVO == null) {
                favoriteService.insertFavorite(dto);
                return JsonResult.create();
            } else {
                return new JsonResult(ResultCode.ERROR_FAVORITE);
            }
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }

    }

    @RequestMapping("ajaxJsonp")
    public @ResponseBody void ajaxJsonp() {
        String st = "{\"id\": 123, \"name\" : \"张三\", \"age\": 17}";
        getResponse().setContentType("text/plain"); 
        String callbackFunName =getRequest().getParameter("callbackparam");//得到js函数名称 
        try { 
        	getResponse().getWriter().write(getRequest().getParameter("callback") + "([ { name:\"John\"}])"); //返回jsonp数据 
        } catch (IOException e) { 
            e.printStackTrace(); 
        }

    }    
    
    @RequestMapping("ajaxisBuy")
    public @ResponseBody
    JsonResult ajaxisBuy(String productID) {
        try {
            CustomerVO customerVO = findUserInfo();
            if (customerVO == null) {
                cachedClient.set(Constant.BEFORE_LOGIN_URL + randomUUID(), Constant.EXP,
                                 "/mall/product/productDetail.do?productID=" + productID);
                return new JsonResult(ResultCode.ERROR_LOGIN);
            }
            Boolean isbuy = salesOrderService.isBuyByCustomer(customerVO.getMainID(), productID);
            if (isbuy) {
                return JsonResult.create();
            } else {
                return new JsonResult(ResultCode.ERROR_BUY);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }

    }

    /**
     * 在售分类
     * 
     * @date 2015年2月4日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("onsellCategory")
    public ModelAndView onsellCategory() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/product/onsell_list");
        List<ProductCategoryVO> list = productCategoryService.findProductOneLevel();
        if (list != null && list.size() > 0) {
            for (ProductCategoryVO productCategoryVO : list) {
                List<ProductCategoryVO> list2 = productCategoryService.findProductCatByParentID(productCategoryVO.getMainID());
                productCategoryVO.setLevelTwo(list2);
            }
            modelAndView.addObject("categoryList", list);
        }
        return modelAndView;
    }
    //*****************************************************************sxc
    @RequestMapping("search")
	public ModelAndView get(String productCategoryId,String name)
	{
    	ProductDTO productDTO = new ProductDTO();
    	
    	List<ProductVO> products = productService.findProductListTparts(productDTO);
    	productDTO.setVehicleTypeId("1766eced-6252-4062-b1d4-15495caffe6b");
    	products = productService.findProductListTparts(productDTO);
    	productDTO.setPartsCategoryId("00028001");
    	products = productService.findProductListTparts(productDTO);
    	
    	
    	ModelAndView modelAndView = this.newModelAndView();
		modelAndView.addObject("brands", products);
		modelAndView.setViewName("screen/product/brand-n");
		return modelAndView;
	}	    
    
    /**
     * 关键字搜索结果页
     * 
     * @date 2015年12月29日
     * @author lby
     * @return
     * @Description
     */
	public static final int VEHICLETYPEAMOUNT = 3;//每个产品显示的适用车型的数量
	public static final int BRANDTITLEAMOUNTOFOEMOTHER = 4;//每个产品显示副厂件品牌的数量;

	@RequestMapping("productForKeySearch")
    public ModelAndView productForKeySearch(String keyword,String pageNo) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/product_keySearch");

        //StopWatch性能测试(swAll整体性能测试)
        StopWatch swAll = new StopWatch();
        swAll.start("productForKeySearch 页面数据搜索总计时");
        
    	//分步计时器sw
    	StopWatch sw = new StopWatch();

    	sw.start("lucene 搜索引擎计时");
        ProductDTO productDTO = new ProductDTO();
        if (StringUtil.isNotEmpty(keyword)) {
        	
            	try {
    				if(StringUtil.isNotEmpty(keyword)&&keyword.equals(new String(keyword.getBytes("iso-8859-1"), "iso-8859-1")))
    				{
    					keyword=new String(keyword.getBytes("iso-8859-1"),"utf-8");
    				}
    			} catch (UnsupportedEncodingException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
                //modelAndView.addObject("keyword", keyword);
                modelAndView.addObject("pname", keyword);

                /**--Tparts--**/
                productDTO.setName(keyword);        		

        }
        if (StringUtil.isNumberic(pageNo)) {
            productDTO.setPageNo(Long.parseLong(pageNo));
        }
        
        productDTO.setPageSize(10l);//每页显示10条数据

        productDTO.setSearchModel("product");
        Integer lucene = Integer.parseInt(getStaticProp().getProperty("lucene.flag"));
        
        List<ProductVO> products = null;
        if(lucene==0)
        {
        	products = productService.findProductListByLucene(productDTO);
        }else
        {
        	products = productService.findProductListTparts(productDTO);	
        }
    
        
        
        if(products == null)
        	products = new ArrayList<ProductVO>();

        TpartsUtils.stopWatchStopRunning(sw);
		System.out.println(sw.prettyPrint());		//lucene 用时


        //在各种查询结束之后克隆出一个用于前端显示的productList以解决数据缓存对重建后的产品信息显示的影响
        List<ProductVO> productResults = new ArrayList<ProductVO>();
        
        int countCycle = 1;							//循环计次
        //给每个搜索结果造产品缩略信息
        for(ProductVO productVO : products){
			//给搜索结果中的每个产品添加配件类目
        	sw.start("第"+ countCycle +"次循环计时");
        	//sw.start("findPartsCategoryByProductID 配件类目信息");
    		List<PartsCategoryVo> partsCategoryList = partsCategoryService.findPartsCategoryByProductID(productVO.getMainID());
    		for (PartsCategoryVo partsCategoryVo : partsCategoryList) {
    			if(partsCategoryVo.getFlevel()==1){
    				productVO.setPartsCategoryLevel(partsCategoryVo.getName());
    			}
    			if(partsCategoryVo.getFlevel()==2){
    				productVO.setPartsCategoryLevel2(partsCategoryVo.getName());
    			}
    		}
    		TpartsUtils.stopWatchStopRunning(sw);
    		System.out.println(sw.prettyPrint());
    		
    		//适用车型
    		sw.start("findSuitVehicle 适用车型信息");
    		Map<ProductCategoryVO, List<VehicleTypeVO>> vehicleTypeMap = itemService.findSuitVehicle(productVO);
    		int vehicleTypeCount = 0;
    		String vehicleTypeInfo = "";
    		outterLoop: for (ProductCategoryVO productCategoryVO : vehicleTypeMap.keySet()){
    			List<VehicleTypeVO> vehicleTypeList = vehicleTypeMap.get(productCategoryVO);
    			for (VehicleTypeVO vehicleTypeVO : vehicleTypeList){
    				vehicleTypeInfo += productCategoryVO.getName() + " " + vehicleTypeVO.getName();
    				vehicleTypeCount++;
    				//如果达到显示上限，直接跳出外循环
    				if (vehicleTypeCount == VEHICLETYPEAMOUNT ){
    					vehicleTypeInfo += "...";//最后一个加...
    					break outterLoop;
    				}
    				vehicleTypeInfo += ";  ";//前几个加分号
    			}
    		}
    		productVO.setVehicleTypeInfo(vehicleTypeInfo);
    		TpartsUtils.stopWatchStopRunning(sw);
    		System.out.println(sw.prettyPrint());

    		//副厂件品牌信息
    		//sw.start("findProductByOEM 副厂件品牌信息");
    		productVO.setType(2);//关键字查出来的都是原厂件，所以这里只查副厂件信息
    		List<ProductVO> productOEMOther = productService.findProductByOEM(productVO);
    		int brandTitleCountOfOEMOther = 0;
    		String brandTitleInfoOfOtherOEM = "";
    		for (ProductVO _productVO : productOEMOther){
    			brandTitleInfoOfOtherOEM += _productVO.getBrandTitle();
    			brandTitleCountOfOEMOther++;
    			
    			if (brandTitleCountOfOEMOther == BRANDTITLEAMOUNTOFOEMOTHER){
    				// 如果搜索结果的数据量刚好与显示上限相同，直接跳出循环
    				if (brandTitleCountOfOEMOther == productOEMOther.size()) {
    					break;
    				}
    				// 如果搜索结果中的内容还没显示完就达到副厂件品牌的显示上限，加省略号并跳出循环
    				brandTitleInfoOfOtherOEM += "...";// 最后一个加...
    				break;
				}else {
					//搜索结果数据量没有达到显示上限，则最后一个不加分号直接跳出
					if (brandTitleCountOfOEMOther == productOEMOther.size()) {
						break;
					}
				}
				brandTitleInfoOfOtherOEM += ";  ";//前几个加分号
    		}
    		productVO.setBrandTitleInfoOfOtherOEM(brandTitleInfoOfOtherOEM);
    		//TpartsUtils.stopWatchStopRunning(sw);
    		//System.out.println(sw.prettyPrint());
    		
			// 给每个产品构造带红色关键字的产品名
    		//sw.start("产品名重构");
			String[] splitKey = TpartsUtils.splitStr(keyword);
			String[] splitTarget = TpartsUtils.splitStr(productVO.getName());
			String result = TpartsUtils.addRedFont(splitTarget, splitKey);
			
			ProductVO _productVO = null;
			try {
				_productVO = ClassTools.clone(productVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			_productVO.setName(result);//这里改变的就只是程序中的临时数据了
			
			productResults.add(_productVO);
    		//TpartsUtils.stopWatchStopRunning(sw);
    		//System.out.println(sw.prettyPrint());
			
			//循环结束，记录用时
			TpartsUtils.stopWatchStopRunning(sw);
			countCycle++;
        }
        System.out.println(sw.prettyPrint());//打印每次循环用时
        
		TpartsUtils.stopWatchStopRunning(swAll);
        System.out.println(swAll.prettyPrint());        
        modelAndView.addObject("queryTime", swAll.getTotalTimeSeconds());

        //modelAndView.addObject("productsParts", products);
        modelAndView.addObject("productsParts", productResults);
        modelAndView.addObject("productDTO", productDTO);        
        if(productResults==null || productResults.size()<=0)
        {
        	modelAndView.setViewName("errors/nothingFound");
        }
        return modelAndView;
    }        
}
