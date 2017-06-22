package com.kpluswebup.web.controller.product;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.domain.ProductTypeDTO;
import com.kpluswebup.web.domain.SupplierItemDTO;
import com.kpluswebup.web.service.BrandService;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.ProductPictureService;
import com.kpluswebup.web.service.ProductPropService;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.service.ProductTypeService;
import com.kpluswebup.web.service.SupplierItemService;
import com.kpluswebup.web.service.VehicleTypeService;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.ProductDetailVO;
import com.kpluswebup.web.vo.ProductPictureVO;
import com.kpluswebup.web.vo.ProductTypeProductPropVO;
import com.kpluswebup.web.vo.ProductTypeVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.SupplierItemVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/product")
public class ProductContoller extends BaseController {

    @Autowired
    private ProductService        productService;
    
    @Autowired
    private SupplierItemService       supplierItemService;

    @Autowired
    private ProductTypeService    productTypeService;

    @Autowired
    private ProductPropService    productPropService;

    @Autowired
    private BrandService          brandService;

    @Autowired
    private ItemService           itemService;

    @Autowired
    private ProductPictureService productPictureService;

    @Autowired
    private VehicleTypeService vehicleTypeService;
    
    @RequestMapping("/addProductPage")
    public ModelAndView addProductPage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/product_add");
        return modelAndView;
    }

    @RequestMapping("/addProduct")
    public ModelAndView addProduct(String name, String virtual, String catalog, String picURL, String unit,
                                   String description, String productTypeMainID, String brandID, String title,
                                   String metaKeywords, String metaDescription, String productDetail,
                                   String isRecommend, String subTitle, String saleService,String productProp,String productType,String isBuy,String isLowPrice,String isSales,String supplierID,String supplierCategoryID) {
        virtual = "0";
        String mainID = "";
        String productID = productService.addProduct(mainID, name, virtual, catalog, picURL, unit, description,
                                                     productTypeMainID, brandID, title, metaKeywords, metaDescription,
                                                     getCurrentOperator(), productDetail, isRecommend, subTitle,
                                                     saleService,productProp,productType,isBuy,isLowPrice,isSales,supplierID,supplierCategoryID,null,null);
        return new ModelAndView("redirect:addItemPage.htm?productID=" + productID + "&productTypeMainID="
                                + productTypeMainID);
    }

    @RequestMapping("/updateProduct")
    public ModelAndView updateProduct(String mainID, String name, String virtual, String catalog, String picURL,
                                      String unit, String description, String productTypeMainID, String brandID,
                                      String title, String metaKeywords, String metaDescription, String productDetail,
                                      String isRecommend, String subTitle, String saleService,String productProp,String productType,String isBuy,String isLowPrice,String isSales,String supplierID,String supplierCategoryID) {

        virtual = "0";
        String productID = productService.updateProduct(mainID, name, virtual, catalog, picURL, unit, description,
                                                        productTypeMainID, brandID, title, metaKeywords,
                                                        metaDescription, getCurrentOperator(), productDetail,
                                                        isRecommend, subTitle, saleService,productProp,productType,isBuy,isLowPrice,isSales,supplierID,supplierCategoryID,null,null);
        return new ModelAndView("redirect:addItemPage.htm?productID=" + productID + "&productTypeMainID="
                                + productTypeMainID);

    }

    @Deprecated
    @RequestMapping("/editProductTypeProductPropPage")
    public ModelAndView editProductTypeProductPropPage(String productID, String productTypeMainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/product_prop_edit");
        List<ProductTypeProductPropVO> productTypeProductPropList = productPropService.findProductTypeProductProp(productTypeMainID);
        modelAndView.addObject("productTypeProductPropList", productTypeProductPropList);
        modelAndView.addObject("productID", productID);
        modelAndView.addObject("productTypeMainID", productTypeMainID);
        // List<ProductDetailVO> ProductDetailList = productService.findProductDetailList(productID);
        return modelAndView;
    }

    @RequestMapping("productTypeProductProp")
    public @ResponseBody
    JsonResult findProductTypeProductProp(String productID, String productTypeMainID) {
        try {
            List<ProductTypeProductPropVO> productTypeProductPropList = productPropService.findProductTypeProductProp(productTypeMainID);
            List<ProductDetailVO> detail = productService.findProductDetailList(productID);
            if (detail != null) {
                for (ProductTypeProductPropVO productTypeProductPropVO : productTypeProductPropList) {
                    productTypeProductPropVO.setPropValues(detail);
                }
            }
            JsonResult JsonResult = new JsonResult(ResultCode.NORMAL);
            JsonResult.setResult(productTypeProductPropList);
            return JsonResult;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    
    @RequestMapping("itemListUnderProduct")
    public ModelAndView itemListUnderProduct(String pageNo, String pageSize,String searchProductId,String searchItemName,String searchItemCode,String supplierName){
    	ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/supplier/supplierproduct_list");
        SupplierItemDTO supplierItemDTO = new SupplierItemDTO();
        if (StringUtil.isNumberic(pageSize)) {
        	supplierItemDTO.setPageSize(Long.parseLong(pageSize));
        }
        if (StringUtil.isNotEmpty(searchProductId)) {
        	supplierItemDTO.setProductID(searchProductId);
        }
        if (StringUtil.isNotEmpty(searchItemCode)) {
        	supplierItemDTO.setItemCode(searchItemCode);
        }
        if (StringUtil.isNotEmpty(searchItemName)) {
        	supplierItemDTO.setItemName(searchItemName);
        }
        if (StringUtil.isNotEmpty(supplierName)) {
        	supplierItemDTO.setSupplierName(supplierName);
        }
        if (StringUtil.isNumberic(pageNo)) {
        	supplierItemDTO.setPageNo(Long.parseLong(pageNo));
        }
        supplierItemDTO.setOrderByClause("order by item.status asc,item.modifyTime desc");
        List<SupplierItemVO> supplierItemList = supplierItemService.findSupplierItem(supplierItemDTO);
        modelAndView.addObject("supplierItemList", supplierItemList);
        modelAndView.addObject("supplierItemDTO", supplierItemDTO);
        modelAndView.addObject("searchProductId", searchProductId);
        return modelAndView;
    }
    
    @RequestMapping("deleteSupplierItem")
    public @ResponseBody
    JsonResult deleteSupplierItem(String mainID) {
        Boolean isSuccess = supplierItemService.deleteSupplierItemBymainID(mainID);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }
    
    @RequestMapping("/ajaxBatchConfirmSupplierItem")
    public void batchConfirmSupplierItem(String ids, HttpServletResponse response) throws Exception {
    	int result = supplierItemService.batchConfirmSupplierItem(ids);
		if(result != 0)
			throw new Exception("ajaxBatchConfirmSupplierItem,result:"+result);
		else{
			PrintWriter out = response.getWriter();
			out.print(0);
			out.close();
		}
    }
    
    @RequestMapping("/productList")
    public ModelAndView findProductList(String OEMCode,String pageNo, String pageSize, String serchname, String serchstatus,
                                        String serchbrandID, String serchcatalog, String searchProductCode,String type) {
    	
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/product_list");
        ProductVO productVO = new ProductVO();
        if (StringUtil.isNumberic(pageNo)) {
        	productVO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNumberic(pageSize)) {
        	productVO.setPageSize(Long.parseLong(pageSize));
        }
        if (StringUtil.isNotEmpty(serchname)) {
        	productVO.setName(serchname);
        }
        if (StringUtil.isInteger(serchstatus)) {
            //productDTO.setStatus(Integer.parseInt(serchstatus));
        	productVO.setIsDelete(Integer.parseInt(serchstatus));	//产品的状态调整改为设置是否已删除
        }
        if (StringUtil.isNotEmpty(serchbrandID)) {
        	productVO.setBrandID(serchbrandID);
        }
        if (StringUtil.isInteger(serchcatalog)) {
        	productVO.setCatalog(Integer.parseInt(serchcatalog));
        }
        if (StringUtil.isNotEmpty(searchProductCode)) {
            //productDTO.setMainID(searchProductId);
        	productVO.setCode(searchProductCode);
        }

		if(StringUtil.isNotEmpty(OEMCode))
    	{
    		try {
				if(StringUtil.isNotEmpty(OEMCode)&&OEMCode.equals(new String(OEMCode.getBytes("iso-8859-1"), "iso-8859-1")))
				{
					OEMCode=new String(OEMCode.getBytes("iso-8859-1"),"utf-8");
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
    		productVO.setMainIDList(vehicleTypeService.findProductIdsByOEM(OEMCode));  
    	}
        
        //productDTO.setCatalog(1);
        //productVO.setOrderByClause("order by  modifyTime desc ");		//暂去掉按更改时间排序，现用的是根据 mainID排
        //List<ProductVO> productList = productService.findProductList(productDTO);
        List<ProductVO> productList = productService.findProductListTpartsAdmin(productVO);
        modelAndView.addObject("productList", productList);
        modelAndView.addObject("productVO", productVO);
        modelAndView.addObject("OEMCode", OEMCode);
        return modelAndView;
    }

    @RequestMapping("/deleteProduct")
    public @ResponseBody
    JsonResult deleteProduct(String mainID) {
        Boolean isSuccess = productService.deleteProduct(mainID);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("addProductDetail")
    public @ResponseBody
    JsonResult addProductDetail(String productID, String productPropID, String pTypePPropID,
                                @RequestParam(value = "productPropValue[]")
                                String[] productPropValue) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/product_list");
        productService.addProductDetail(productID, productPropID, pTypePPropID, productPropValue, getCurrentOperator());
        return JsonResult.create();

    }

    @RequestMapping("editProductPage")
    public ModelAndView editProductPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/product_edit");
        ProductVO productVO = productService.findProductByMainIDAll(mainID);
        modelAndView.addObject("productVO", productVO);
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        productTypeDTO.setPageSize(100000l);
        List<ProductTypeVO> productTypList = productTypeService.findProductTypeByPagination(productTypeDTO);
        modelAndView.addObject("productTypList", productTypList);
        modelAndView.addObject("productID", productVO.getMainID());
        modelAndView.addObject("productTypeMainID", productVO.getProductTypeID());
        return modelAndView;
    }

    @RequestMapping("productImgPic")
    public ModelAndView productImgPic(String productID, String productTypeMainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/product_img");
        List<ItemVO> itemList = itemService.findItemByProductID(productID);
        modelAndView.addObject("itemList", itemList);
        List<ProductPictureVO> picList = productPictureService.findProductPictureByProductID(null,productID);
        modelAndView.addObject("picList", picList);
        modelAndView.addObject("productID", productID);
        modelAndView.addObject("productTypeMainID", productTypeMainID);
        return modelAndView;
    }

    @RequestMapping("productImgPicDetail")
    public ModelAndView productImgPicDetail(String productID, String productTypeMainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/product_img_detail");
        List<ItemVO> itemList = itemService.findItemByProductID(productID);
        modelAndView.addObject("itemList", itemList);
        List<ProductPictureVO> picList = productPictureService.findProductPictureByProductID(null,productID);
        modelAndView.addObject("picList", picList);
        modelAndView.addObject("productID", productID);
        modelAndView.addObject("productTypeMainID", productTypeMainID);
        return modelAndView;
    }

    @RequestMapping("addProductImgPic")
    public ModelAndView addProductImgPic(String productTypeMainID, String name, String productID, String itemID,
                                         String picURL) {
        productPictureService.insertProductPicture(name, productID, itemID, picURL, getCurrentOperator());
        return new ModelAndView("redirect:productImgPic.htm?productID=" + productID + "&productTypeMainID="
                                + productTypeMainID);
    }

    @RequestMapping("/deleteProductPicture")
    public @ResponseBody
    JsonResult deleteProductPicture(String id) {
        Integer count = productPictureService.deleteProductPictureById(id);
        if (count == 1) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("/updateProductStatus")
    public @ResponseBody
    JsonResult updateProductStatus(String mainIDs, String status) {

        //Integer count = productService.updateProductStatus(mainIDs, status);
        Integer count = productService.updateProductStatusTparts(mainIDs, status); 	//改isDelete
        if (count == 1) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }
    
    @RequestMapping("/updateSupplierItemStatus")
    public @ResponseBody
    JsonResult updateSupplierItemStatus(String mainID, String status) {

        Integer count = supplierItemService.updateSupplierItemStatus(mainID, status);
        if (count == 1) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }
    
    
    /**
     * 产品详情
     * 
     * @date 2014年12月4日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("productDetail_old")
    public ModelAndView productDetail(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/product_detail");

        //ProductVO productVO = productService.findProductByMainIDAll(mainID);// 产品信息(检索不出code)
        ProductVO productVO = productService.findProductByMainIDNew(mainID);// 产品信息
        modelAndView.addObject("productVO", productVO);

        List<ProductDetailVO> productDetails = productService.findProductDetailList(mainID);
        modelAndView.addObject("productDetails", productDetails);

        modelAndView.addObject("productID", mainID);
        modelAndView.addObject("productTypeMainID", productVO.getProductTypeID());
        return modelAndView;
    }

    @RequestMapping("/searchProductType")
    public @ResponseBody
    JsonResult searchProductType(String name, String pageNo, String pageSize) {
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        if (StringUtil.isNumberic(pageNo)) {
            productTypeDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNumberic(pageSize)) {
            productTypeDTO.setPageSize(Long.parseLong(pageSize));
        }
        if (StringUtil.isNotEmpty(name)) {
            productTypeDTO.setName(name);
        }
        List<ProductTypeVO> productTypeList = productTypeService.findProductTypeByPagination(productTypeDTO);
        JsonResult jsonResult = new JsonResult(ResultCode.NORMAL);
        jsonResult.setResult(productTypeList);
        return jsonResult;
    }

    @RequestMapping("/searchItem")
    public @ResponseBody
    JsonResult searchItem(String name, String pageNo, String pageSize, String type,String catalog) {
        ProductDTO productDTO = new ProductDTO();
        if (StringUtil.isNumberic(pageNo)) {
            productDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNumberic(pageSize)) {
            productDTO.setPageSize(Long.parseLong(pageSize));
        }
        if (StringUtil.isNotEmpty(name)) {
            productDTO.setName(name);
        }
        List<ItemVO> itemList = itemService.searchItem(name, type,catalog);
        JsonResult jsonResult = new JsonResult(ResultCode.NORMAL);
        jsonResult.setResult(itemList);
        return jsonResult;
    }

}
