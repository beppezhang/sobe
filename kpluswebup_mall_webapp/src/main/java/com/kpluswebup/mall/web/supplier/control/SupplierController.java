package com.kpluswebup.mall.web.supplier.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.account.service.FinalstatementService;
import com.kpluswebup.web.admin.system.service.AreaService;
import com.kpluswebup.web.admin.system.service.FreightTemplateService;
import com.kpluswebup.web.admin.system.service.SystemCodeService;
import com.kpluswebup.web.admin.system.service.SystemLogService;
import com.kpluswebup.web.content.service.NoticeService;
import com.kpluswebup.web.domain.CarrierNoteDTO;
import com.kpluswebup.web.domain.FinalstatementDTO;
import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.ItemPropDTO;
import com.kpluswebup.web.domain.ProductPropDTO;
import com.kpluswebup.web.domain.ProductTypeDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.domain.SalesOrderSalesApplyDTO;
import com.kpluswebup.web.domain.SalesOrderTransDTO;
import com.kpluswebup.web.domain.SupplierCategoryDTO;
import com.kpluswebup.web.domain.SupplierDTO;
import com.kpluswebup.web.domain.SupplierItemDTO;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.service.BrandService;
import com.kpluswebup.web.service.ItemPropService;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.ProductCategoryService;
import com.kpluswebup.web.service.ProductPictureService;
import com.kpluswebup.web.service.ProductPropService;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.service.ProductTypeBrandService;
import com.kpluswebup.web.service.ProductTypeService;
import com.kpluswebup.web.service.SalesOrderSalesApplyService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.service.SupplierItemService;
import com.kpluswebup.web.service.SupplierOrderStatisticsService;
import com.kpluswebup.web.supplier.service.CarrierNoteService;
import com.kpluswebup.web.supplier.service.SupplierService;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.BrandVO;
import com.kpluswebup.web.vo.CarrierNotePrintGroupVO;
import com.kpluswebup.web.vo.CarrierNoteVO;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.FinalstatementVO;
import com.kpluswebup.web.vo.FirtSecondThirdCategoryVO;
import com.kpluswebup.web.vo.FreightTemplateVO;
import com.kpluswebup.web.vo.ItemDetailVO;
import com.kpluswebup.web.vo.ItemPropVO;
import com.kpluswebup.web.vo.ItemPropValueVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.ProductDetailVO;
import com.kpluswebup.web.vo.ProductPictureVO;
import com.kpluswebup.web.vo.ProductPropVO;
import com.kpluswebup.web.vo.ProductPropValueVO;
import com.kpluswebup.web.vo.ProductTypeBrandVO;
import com.kpluswebup.web.vo.ProductTypeItemPropVO;
import com.kpluswebup.web.vo.ProductTypeProductPropVO;
import com.kpluswebup.web.vo.ProductTypeVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.SalesOrderSalesApplyVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpluswebup.web.vo.SupplierCategoryVO;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.Md5Algorithm;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

/**
 * 
 * @author moo
 *
 */
@Controller
@RequestMapping("/mall/seller")
public class SupplierController extends BaseController {
	
	private static Logger logger = Logger.getLogger(SupplierController.class);
	   @Autowired
	    private ProductTypeBrandService productTypeBrandService;
	   @Autowired
	    private MemberSerivce          memberSerivce;

	@Autowired
	private SupplierService supplierService;
	@Autowired
    private ProductPropService productPropService;
    @Autowired
    private ItemPropService    itemPropService;
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private SalesOrderService	salesOrderService;
	
	@Autowired
	private ProductService productService;
	 @Autowired
	    private ProductTypeService    productTypeService;
	 @Autowired
	    private SystemLogService systemLogService;
	@Autowired
	private SupplierItemService supplierItemService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private CarrierNoteService carrierNoteService;
	
	@Autowired
	private FinalstatementService finalstatementService;
	
	@Autowired
	private CachedClient cachedClient;
	
	@Autowired
	SupplierOrderStatisticsService supplierOrderStatisticsService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private SystemCodeService systemCodeService;
	
	@Autowired
	private ProductPictureService productPictureService;
	
	@Autowired
	private BrandService brandService;

	 @Autowired
    private SalesOrderSalesApplyService      salesOrderSalesApplyService;
	 @Autowired
	private AreaService                      areaService;
	
	 @Autowired
	private FreightTemplateService           freightTemplateService;

	 /**
     * 获取发货商品的产品属性跟销售规格
     * @author zhoulei
     * @param productTypeMainID
     * @return
     */
    @RequestMapping("addProductTypeProductItemPro")
    public ModelAndView addProductTypeProductItemPro(String productTypeMainID,String productID){
    	  ModelAndView modelAndView = this.newModelAndView();
    	  modelAndView.setViewName("screen/supplier/productType_productitemProp");
    	  modelAndView.addObject("productTypeMainID", productTypeMainID);
          ItemPropDTO itemPropDTO = new ItemPropDTO();
          // TODO 查询所有数据，数据不存在100 是否取消分页功能
          itemPropDTO.setPageSize(100l);
          List<ItemPropVO> itemPropList = itemPropService.findItemPropByPagination(itemPropDTO);
          modelAndView.addObject("itemPropList", itemPropList);

          List<ProductTypeItemPropVO> productTypeItemPropList = productPropService.findProductTypeItemProp(productTypeMainID);
          if (productTypeItemPropList != null && productTypeItemPropList.size() > 0) {
              for (ProductTypeItemPropVO productTypeItemPropVO : productTypeItemPropList) {
                  List<ItemPropValueVO> itemPropValues = itemPropService.findAllItemPropValueByItemPropMianID(productTypeItemPropVO.getItemPropID());
                  if (itemPropValues != null && itemPropValues.size() > 0) {
                      for (ItemPropValueVO itemPropValueVO : itemPropValues) {
                          if (productTypeItemPropVO.getItemPropValue().contains(itemPropValueVO.getName())) {
                              itemPropValueVO.setFlag(1);
                          }
                      }
                  }
                  productTypeItemPropVO.setItemPropValueList(itemPropValues);
              }
          }
          modelAndView.addObject("checkProdutTypeList", productTypeService.findProductDetailsByProductMainID(productID));//findProductDetailByID
          List<ItemDetailVO> checkItemTypeList = itemService.findItemDetailSByItemID(productID);
         /* Map<String,ItemDetailVO> checkItemTypeMap = new HashMap<String,ItemDetailVO>();
          for(ItemDetailVO itemDetailVO:checkItemTypeList){
        	  String s = itemDetailVO.getItemPropID()+":"+itemDetailVO.getpTypeIPropID();
        	  if(!checkItemTypeMap.containsKey(s)){
        		  checkItemTypeMap.put(s, itemDetailVO);
        	  }
        		  
          }*/
          modelAndView.addObject("checkItemTypeListGroup", itemService.findItemDetailSByItemID1(productID));
          modelAndView.addObject("checkItemTypeList", checkItemTypeList);//findProductDetailByID
          modelAndView.addObject("productTypeItemPropList", productTypeItemPropList);
          List<ItemVO> itemNewList = itemService.findItemByProductID(productID);
          ProductPropDTO productPropDTO = new ProductPropDTO();
          // TODO 查询所有数据，数据不存在100 是否取消分页功能
          productPropDTO.setPageSize(100l);
          List<ProductPropVO> productPropList = productPropService.findProductPropByPagination(productPropDTO);
          List<ProductTypeProductPropVO> productTypeProductPropList = productPropService.findProductTypeProductProp(productTypeMainID);
          modelAndView.addObject("productPropList", productPropList);
          if (productTypeProductPropList != null && productTypeProductPropList.size() > 0) {
              for (ProductTypeProductPropVO productTypeProductPropVO : productTypeProductPropList) {
                  List<ProductPropValueVO> productPropValues = productPropService.findAllProductPropValueByProductPropMianID(productTypeProductPropVO.getproductPropID());
                  if (productPropValues != null && productPropValues.size() > 0) {
                      for (ProductPropValueVO productPropValueVO : productPropValues) {
                          if (productTypeProductPropVO.getproductPropValue().contains(productPropValueVO.getName())) {
                              productPropValueVO.setFlag(1);
                          }
                      }
                  }
                  productTypeProductPropVO.setProductPropValueList(productPropValues);
              }
          }
          modelAndView.addObject("itemNewList", itemNewList);
          List<ProductDetailVO> detail = productService.findProductDetailList(productID);
	        modelAndView.addObject("productDetailList", detail);
          modelAndView.addObject("productTypeProductPropList", productTypeProductPropList);
    	  return modelAndView;
    }
    
    @RequestMapping("findItemByMainID")
    public @ResponseBody
    JsonResult findItemByMainID(String itemMainID){
    	JsonResult  JsonResult=new JsonResult(ResultCode.NORMAL);
        JsonResult.setResult(itemService.findItemById(itemMainID));
        return JsonResult;
    }
    
    /**
     * 
     * @date 2015年7月24日
     * @author yuanyuan
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description 订单上传支付凭证
     */
    @RequestMapping("uploadSalesOrderProofURL")
    public @ResponseBody
    JsonResult uploadSalesOrderProofURL(Long id,String proofURL,String sendOutURL) {
        try {
            SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
            salesOrderDTO.setId(id);
            if(StringUtil.isNotEmpty(proofURL)) salesOrderDTO.setProofURL(proofURL);
            if(StringUtil.isNotEmpty(sendOutURL)) salesOrderDTO.setSendOutURL(sendOutURL);
            salesOrderService.uploadSalesOrderProofURL(salesOrderDTO);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }
    
    /**
     * 
     * @date 2015年07月07日
     * @author zhoulei
     * @param productTypeMainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("findProductTypeBrand")
    public @ResponseBody
    JsonResult findProductTypeBrand(String productTypeMainID) {
        try {
            List<ProductTypeBrandVO> list= productTypeBrandService.findProductTypeBrandByPtid(productTypeMainID);
            JsonResult  JsonResult=new JsonResult(ResultCode.NORMAL);
            JsonResult.setResult(list);
            return JsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
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
    @RequestMapping("/addProduct")
    public ModelAndView addProduct(String name, String virtual, String catalog, String[] picsURL, String unit,
                                   String description, String productTypeMainID, String brandID, String title,
                                   String metaKeywords, String metaDescription, String productDetail,
                                   String isRecommend,String productStandPrice, String subTitle, String saleService,String productProp,String productType,String isBuy,String isLowPrice,String isSales,String supplierID,String supplierCategoryID,String[] items,Integer status,String freightTemplateID) {
        virtual = "0";
        String mainID = "";
        String productID = productService.addProduct(mainID, name, virtual, catalog, null, unit, description,
                                                     productTypeMainID, brandID, title, metaKeywords, metaDescription,
                                                     getCurrentOperator(), productDetail, isRecommend, subTitle,
                                                     saleService,productProp,productType,isBuy,isLowPrice,isSales,supplierID,supplierCategoryID,productStandPrice,status, freightTemplateID);
        itemService.batchAddOrEditItem(productID, productTypeMainID, items, getCurrentOperator(),picsURL);
        return new ModelAndView("redirect:item.htm?type=sale");
    }
    
    
    @RequestMapping("editProduct")
    public ModelAndView editProductPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/supplier/product_category");
        List<ProductCategoryVO> categoryList = productCategoryService.findProductCatOneLevel(); 
		List<ProductCategoryVO> categorychildList = null; 
	        if (categoryList != null && categoryList.size() > 0) {
	            for (ProductCategoryVO categorychild : categoryList) {
	                categorychildList = productCategoryService.findProductCatByParentIDForSupplier(categorychild.getMainID());
	                categorychild.setLevelTwo(categorychildList);
	                if (categorychildList != null && categorychildList.size() > 0) {
	                    for (ProductCategoryVO categorylast : categorychildList) {
	                        List<ProductCategoryVO> categorylastList = productCategoryService.findProductCatByParentIDForSupplier(categorylast.getMainID());
	                        categorylast.setLevelThre(categorylastList);
	                    }
	                }
	            }
	        }
	    List<ProductPictureVO> pictures = productPictureService.findProductPictureByProductID(null,mainID);
	    modelAndView.addObject("pictures", pictures);
        SupplierCategoryDTO supplierCategoryDTO = new SupplierCategoryDTO();
        supplierCategoryDTO.setSupplierID(this.findSupplierInfo().getMainID());
        List<SupplierCategoryVO> businclassList =  supplierService.findSupplierCategory(supplierCategoryDTO);
        modelAndView.addObject("supplierID", this.findSupplierInfo().getMainID());
        modelAndView.addObject("businclassList", businclassList);
        modelAndView.addObject("categoryList", categoryList);
        ProductVO productVO = productService.findProductByMainIDAll(mainID);
        modelAndView.addObject("productVO", productVO);
       // modelAndView.addObject("checkProdutTypeList", productTypeService.findProductDetailsByProductMainID(mainID));//findProductDetailByID
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        productTypeDTO.setPageSize(100000l);
        List<ProductTypeVO> productTypList = productTypeService.findProductTypeByPagination(productTypeDTO);
        modelAndView.addObject("productTypList", productTypList);
        modelAndView.addObject("productID", productVO.getMainID());
        modelAndView.addObject("productTypeMainID", productVO.getProductTypeID());
        modelAndView.addObject("supplierCategoryID", productVO.getSupplierCategoryID());
        modelAndView.addObject("operatorMethod", "edit");
        SupplierVO supplierVO = this.findSupplierInfo();
	    supplierCategoryDTO.setSupplierID(supplierVO.getMainID());
	    modelAndView.addObject("supplierVO", supplierVO);
	    
	    List<FreightTemplateVO> freightTemplateList = freightTemplateService.findFreightTemplateBySupplierID(supplierVO.getMainID());
	    modelAndView.addObject("freightTemplateList", freightTemplateList);
        return modelAndView;
    }
	/**
	 * 
	 * @date 2015年5月13日
	 * @author moo
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description 供应商中心
	 */
	@RequestMapping(value="supplierCenter", method=RequestMethod.GET)
	public ModelAndView supplierCenter(){
		ModelAndView model = this.newModelAndView();
		SupplierVO supplierVO1=this.findSupplierInfo();
		if(supplierVO1.getLastloginTime()==null){
			
        	model.setViewName("screen/supplier/password");
        	
        	return model;
        }		
		model.setViewName("screen/supplier/supplier_center");
		SupplierVO supplierVO = supplierService.findSupplierByID(this.findSupplierInfo().getMainID());
		model.addObject("supplier", supplierVO);
		//订单提醒
		SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
		salesOrderDTO.setSupplierID(supplierVO.getMainID());
		long wartForDealOrderCount =salesOrderService.getWaitForDealOrder(salesOrderDTO);//待处理订单
		model.addObject("wartForDealOrderCount", wartForDealOrderCount);
		//商品提醒
		int waitItemCount = supplierItemService.findItemBySupplierAdnType(supplierVO.getMainID(), 0);//待上架商品
        model.addObject("waitItemCount", waitItemCount);
		//站内消息
		//近期订单
        salesOrderDTO.setPageSize(10l);
		List<SalesOrderVO> salesOrderList = salesOrderService.findOrdersBySupplierID(salesOrderDTO);
		model.addObject("salesOrderList", salesOrderList);
		return model;
		
	}
	/**
	 * 分类管理（supplier_category）
	 * @date 2015年7月7日
	 * @author yuanyuan
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("businClass")
	public ModelAndView businClass() {
	    ModelAndView modelAndView = this.newModelAndView();
	    modelAndView.setViewName("screen/supplier/busin_class");
	    SupplierCategoryDTO supplierCategoryDTO = new SupplierCategoryDTO();
	    supplierCategoryDTO.setSupplierID(this.findSupplierInfo().getMainID());
	    List<SupplierCategoryVO> businclassList =  supplierService.findSupplierCategory(supplierCategoryDTO);
	    modelAndView.addObject("businclassList", businclassList);
	    return modelAndView;
    }
	
	/**
	 * 编辑分类
	 * @date 2015年7月7日
	 * @author yuanyuan
	 * @param mainID
	 * @param name
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("editBusinClass")
	public ModelAndView editBusinClass(String mainID,String name) {
	    ModelAndView modelAndView = this.newModelAndView();
	    modelAndView.setViewName("screen/supplier/busin_class");
	    SupplierCategoryDTO supplierCategoryDTO = new SupplierCategoryDTO();
	    if (StringUtil.isNotEmpty(mainID)) {
	        supplierCategoryDTO.setMainID(mainID);
        }
	    if (StringUtil.isNotEmpty(name)) {
            try {
                if(StringUtil.isNotEmpty(name)&&name.equals(new String(name.getBytes("iso-8859-1"), "iso-8859-1")))
                {
                    name=new String(name.getBytes("iso-8859-1"),"utf-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            supplierCategoryDTO.setName(name);
        }
	    supplierService.editBusinClass(supplierCategoryDTO);
        return new ModelAndView("redirect:businClass.htm");
    }
	/**
	 * 
	 * @date 2015年7月7日
	 * @author yuanyuan
	 * @param mainID
	 * @param name
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("saveBusinClass")
	public ModelAndView saveBusinClass(String name,String isParent,String parentID) {
	    ModelAndView modelAndView = this.newModelAndView();
	    modelAndView.setViewName("screen/supplier/busin_class");
	    SupplierCategoryDTO supplierCategoryDTO = new SupplierCategoryDTO();
	    supplierCategoryDTO.setMainID(UUIDUtil.getUUID());
	    if (StringUtil.isNotEmpty(name)) {
            try {
                if(StringUtil.isNotEmpty(name)&&name.equals(new String(name.getBytes("iso-8859-1"), "iso-8859-1")))
                {
                    name=new String(name.getBytes("iso-8859-1"),"utf-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
	        supplierCategoryDTO.setName(name);
	    }
	    if(StringUtil.isNotEmpty(isParent)){
	        if(isParent.equals("Y")){
	            supplierCategoryDTO.setIsParent("Y");
	        }else {
	            supplierCategoryDTO.setIsParent("N");
            }
	    }
	    if(StringUtil.isNotEmpty(parentID)){
	        supplierCategoryDTO.setParentID(parentID);
	    }
	    supplierCategoryDTO.setSupplierID(this.findSupplierInfo().getMainID());
	    supplierCategoryDTO.setListOrder(0);
	    supplierService.saveBusinClass(supplierCategoryDTO);
	    return new ModelAndView("redirect:businClass.htm");
	}
	/**
	 * 
	 * @date 2015年7月7日
	 * @author yuanyuan
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("deleteBusinClass")
	public ModelAndView deleteBusinClass(String mainID) {
	    ModelAndView modelAndView = this.newModelAndView();
	    modelAndView.setViewName("screen/supplier/busin_class");
	    SupplierCategoryDTO supplierCategoryDTO = new SupplierCategoryDTO();
        supplierCategoryDTO.setMainID(mainID);
        supplierService.deleteBusinClass(mainID);
        return new ModelAndView("redirect:businClass.htm");
	}
	
	
	/**
	 * 
	 * @date 2015年11月16日
	 * @author sxc
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("showSupplier")
	public ModelAndView showSupplierTparts(String catID, String brandID, String startSalePrice, String endSalePrice,
                                     String pageNo, String pTypePPropIDs, String orderByClause,
                                     Integer productType, String name, String cType,String supplierCategoryID,String mainID) {
	    ModelAndView modelAndView = this.newModelAndView();
	    SupplierVO supplierVO = new SupplierVO();
	    SupplierCategoryDTO supplierCategoryDTO = new SupplierCategoryDTO();
	    if(mainID != null && StringUtil.isNotEmpty(mainID)){
	        supplierVO =  supplierService.findSupplierByID(mainID);
	        supplierCategoryDTO.setSupplierID(supplierVO.getMainID());
	    }else{
	        supplierVO = this.findSupplierInfo();
	        supplierVO =  supplierService.findSupplierByID(supplierVO.getMainID());
	        supplierCategoryDTO.setSupplierID(supplierVO.getMainID());
	    }
	    modelAndView.addObject("supplierVO", supplierVO);
        modelAndView.setViewName("screen/supplier/show_supplier");
        
        List<SupplierCategoryVO> businclassList =  supplierService.findSupplierCategory(supplierCategoryDTO);
        modelAndView.addObject("businclassList", businclassList);
        
        List<BrandVO> brandList = new ArrayList<BrandVO>();
        ItemDTO itemDto = new ItemDTO();
        if (StringUtil.isNotEmpty(catID)) {
            brandList = brandService.findAllBrandListBycatID(catID);
            ProductCategoryVO productCategoryVO = productCategoryService.findProductCategoryByMainID(catID);
            modelAndView.addObject("productCategoryVO", productCategoryVO);
            if (StringUtil.isNotEmpty(cType)) {
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
            }
            modelAndView.addObject("catID", catID);
            modelAndView.addObject("cType", cType);
        } else {
            brandList = brandService.findAllBrandList();
        }
        modelAndView.addObject("brandList", brandList);
        if (StringUtil.isNotEmpty(name)) {
            try {
                if(StringUtil.isNotEmpty(name)&&name.equals(new String(name.getBytes("iso-8859-1"), "iso-8859-1")))
                {
                    name=new String(name.getBytes("iso-8859-1"),"utf-8");
                }
            } catch (UnsupportedEncodingException e) {
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
        modelAndView.addObject("itemDto", itemDto);
        //ArrayList<List<ItemVO>> itsList = new ArrayList<List<ItemVO>>();
        Map<String, List<ProductPictureVO>> itemsMap = new HashMap<String, List<ProductPictureVO>>();
        // for (ProductVO p : products) {
        itemDto.setSupplierID(supplierVO.getMainID());
        if(StringUtil.isNotEmpty(supplierCategoryID)){
            itemDto.setSupplierCategoryID(supplierCategoryID);
            modelAndView.addObject("supplierCategoryID", supplierCategoryID);
        }
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


       // List<ProductTypeProductPropVO> productTypeProductPropList = productPropService.seachProductprop(catID, brandID);
       // modelAndView.addObject("productTypeProductPropList", productTypeProductPropList);

       // List<ProductVO> list = productService.searchProductByIsRemmond();//热销推荐
       //  modelAndView.addObject("list", list);
        modelAndView.addObject("catID", catID);
        return modelAndView;
    }	
	
	/**
	 * 
	 * @date 2015年7月8日
	 * @author yuanyuan
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("showSupplier-old")
	public ModelAndView showSupplier(String catID, String brandID, String startSalePrice, String endSalePrice,
                                     String pageNo, String pTypePPropIDs, String orderByClause,
                                     Integer productType, String name, String cType,String supplierCategoryID,String mainID) {
	    ModelAndView modelAndView = this.newModelAndView();
	    SupplierVO supplierVO = new SupplierVO();
	    SupplierCategoryDTO supplierCategoryDTO = new SupplierCategoryDTO();
	    if(mainID != null){
	        supplierVO =  supplierService.findSupplierByID(mainID);
	        supplierCategoryDTO.setSupplierID(supplierVO.getMainID());
	    }else{
	        supplierVO = this.findSupplierInfo();
	        supplierVO =  supplierService.findSupplierByID(supplierVO.getMainID());
	        supplierCategoryDTO.setSupplierID(supplierVO.getMainID());
	    }
	    modelAndView.addObject("supplierVO", supplierVO);
        modelAndView.setViewName("screen/supplier/show_supplier");
        
        List<SupplierCategoryVO> businclassList =  supplierService.findSupplierCategory(supplierCategoryDTO);
        modelAndView.addObject("businclassList", businclassList);
        
        List<BrandVO> brandList = new ArrayList<BrandVO>();
        ItemDTO itemDto = new ItemDTO();
        if (StringUtil.isNotEmpty(catID)) {
            brandList = brandService.findAllBrandListBycatID(catID);
            ProductCategoryVO productCategoryVO = productCategoryService.findProductCategoryByMainID(catID);
            modelAndView.addObject("productCategoryVO", productCategoryVO);
            if (StringUtil.isNotEmpty(cType)) {
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
            }
            modelAndView.addObject("catID", catID);
            modelAndView.addObject("cType", cType);
        } else {
            brandList = brandService.findAllBrandList();
        }
        modelAndView.addObject("brandList", brandList);
        if (StringUtil.isNotEmpty(name)) {
            try {
                if(StringUtil.isNotEmpty(name)&&name.equals(new String(name.getBytes("iso-8859-1"), "iso-8859-1")))
                {
                    name=new String(name.getBytes("iso-8859-1"),"utf-8");
                }
            } catch (UnsupportedEncodingException e) {
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
        modelAndView.addObject("itemDto", itemDto);
        //ArrayList<List<ItemVO>> itsList = new ArrayList<List<ItemVO>>();
        Map<String, List<ProductPictureVO>> itemsMap = new HashMap<String, List<ProductPictureVO>>();
        // for (ProductVO p : products) {
        itemDto.setSupplierID(supplierVO.getMainID());
        if(StringUtil.isNotEmpty(supplierCategoryID)){
            itemDto.setSupplierCategoryID(supplierCategoryID);
            modelAndView.addObject("supplierCategoryID", supplierCategoryID);
        }
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


       // List<ProductTypeProductPropVO> productTypeProductPropList = productPropService.seachProductprop(catID, brandID);
       // modelAndView.addObject("productTypeProductPropList", productTypeProductPropList);

       // List<ProductVO> list = productService.searchProductByIsRemmond();//热销推荐
       //  modelAndView.addObject("list", list);
        modelAndView.addObject("catID", catID);
        return modelAndView;
    }
	
	/**
	 * 店铺退换货列表
	 * @date 2015年7月12日
	 * @author yuanyuan
	 * @param type
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("salesApplyMallList")
	public ModelAndView salesApplyMallList(String type) {
	    ModelAndView modelAndView = this.newModelAndView();
	    SalesOrderSalesApplyDTO salesOrderSalesApplyDTO = new SalesOrderSalesApplyDTO();
	    modelAndView.setViewName("screen/supplier/supplier_salesapplychange");
	    if("return".equals(type)){
	        salesOrderSalesApplyDTO.setSalesType(1);
	        modelAndView.addObject("type", "return");
	    }
	    if("change".equals(type)){
	        salesOrderSalesApplyDTO.setSalesType(2);
	        modelAndView.addObject("type", "change");
	    }
	    salesOrderSalesApplyDTO.setSupplierID(this.findSupplierInfo().getMainID());
        List<SalesOrderSalesApplyVO> list = salesOrderSalesApplyService.findSalesOrderSalesApplyByPagination(salesOrderSalesApplyDTO);
        modelAndView.addObject("salesApplyList", list);
        
	    return modelAndView;
    }
	
	/**
     * 确认售后
     * 
     * @date 2015年1月9日
     * @author wanghehua
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("affirmAfterSale")
    public @ResponseBody
    JsonResult affirmAfterSale(String mainID) {
        try {
            salesOrderSalesApplyService.affirmAfterSale(mainID);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }
	
	/**
	 * 跟新售后状态
	 * @date 2015年7月12日
	 * @author yuanyuan
	 * @param mainId
	 * @param status
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	 @RequestMapping("changeAfterSaleStatus")
	    public @ResponseBody
	    JsonResult changeAfterSaleStatus(String mainId, String status,String memo) {
	        try {
	            if (StringUtils.isBlank(mainId) || StringUtils.isBlank(status)) return new JsonResult(
	                                                                                                  ResultCode.ERROR_SYSTEM_PARAM_FORMAT);
	            salesOrderSalesApplyService.updateApplyStatus(mainId, status, getCurrentOperator(),memo);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new JsonResult(ResultCode.ERROR_SYSTEM);
	        }
	        return JsonResult.create();
	    }
	 
	 /**
	     * 更新售后申请信息
	     * 
	     * @date 2014年11月13日
	     * @author lupeng
	     * @param salesOrderReturnApply
	     * @return
	     * @since JDK 1.6
	     * @Description
	     */
	    @RequestMapping("updateSalesApplyInfo")
	    public ModelAndView updateSalesApplyInfo(SalesOrderSalesApplyVO salesOrderSalesApply) {
	        salesOrderSalesApply.setCreator(getCurrentOperator());
	        salesOrderSalesApplyService.updateSalesApplyInfo(salesOrderSalesApply);
	        return new ModelAndView("redirect:editSalesApply.htm?mainId=" + salesOrderSalesApply.getMainID());
	    }
	 
	 @RequestMapping("/updateProductStatus")
	    public @ResponseBody
	    JsonResult updateProductStatus(String mainIDs, String status) {

	        Integer count = productService.updateProductStatus(mainIDs, status);
	        if (count == 1) {
	            return JsonResult.create();
	        }
	        return new JsonResult(ResultCode.ERROR_SYSTEM);
	    }
	 
	 
	 /**
	     * 售后申请编辑
	     * 
	     * @date 2014年11月3日
	     * @author lupeng
	     * @param salesOrderSalesApplyDTO
	     * @return
	     * @since JDK 1.6
	     * @Description
	     */
	    @RequestMapping("editSalesApply.htm")
	    public ModelAndView editSalesApply(String mainId,String type) {
	        ModelAndView modelAndView = this.newModelAndView();
	        modelAndView.setViewName("screen/supplier/salesApply_edit");
	        SalesOrderSalesApplyVO salesOrderSalesApplyVO = salesOrderSalesApplyService.findSalesOrderSalesApplyByMainID(mainId);
	        modelAndView.addObject("salesOrderSalesApply", salesOrderSalesApplyVO);
	        modelAndView.addObject("type", type);
	        List<AreaVO> provinceList = areaService.getAllProvince();
	        modelAndView.addObject("provinceList", provinceList);
	        List<AreaVO> cityList = null;
	        List<AreaVO> districtList = null;
	        if (salesOrderSalesApplyVO != null) {
	            if (salesOrderSalesApplyVO.getSalesAddressVO() != null
	                && StringUtil.isNotEmpty(salesOrderSalesApplyVO.getSalesAddressVO().getProvinceID())) {
	                cityList = areaService.getAreaByParentID(salesOrderSalesApplyVO.getSalesAddressVO().getProvinceID());
	            } else {
	                if (provinceList != null && provinceList.size() > 0) {
	                    cityList = areaService.getAreaByParentID(provinceList.get(0).getMainID());
	                }
	            }
	            modelAndView.addObject("cityList", cityList);
	            if (salesOrderSalesApplyVO.getSalesAddressVO() != null
	                && StringUtil.isNotEmpty(salesOrderSalesApplyVO.getSalesAddressVO().getCityID())) {
	                districtList = areaService.getAreaByParentID(salesOrderSalesApplyVO.getSalesAddressVO().getCityID());
	            } else {
	                if (cityList != null && cityList.size() > 0) {
	                    districtList = areaService.getAreaByParentID(cityList.get(0).getMainID());
	                }
	            }
	            modelAndView.addObject("districtList", districtList);
	        }
	        return modelAndView;
	    }
	
	/**
	 * 
	 * @date 2015年5月13日
	 * @author moo
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description 商品类目
	 */
	@RequestMapping(value="productCategory", method=RequestMethod.GET)
	public ModelAndView productCategory(){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/supplier/product_category");
//		List<ProductCategoryVO> categoryList = productCategoryService.findProductCatOneLevel(); 
//		List<ProductCategoryVO> categorychildList = null; 
//	        if (categoryList != null && categoryList.size() > 0) {
//	            for (ProductCategoryVO categorychild : categoryList) {
//	                categorychildList = productCategoryService.findProductCatByParentIDForSupplier(categorychild.getMainID());
//	                categorychild.setLevelTwo(categorychildList);
//	                if (categorychildList != null && categorychildList.size() > 0) {
//	                    for (ProductCategoryVO categorylast : categorychildList) {
//	                        List<ProductCategoryVO> categorylastList = productCategoryService.findProductCatByParentIDForSupplier(categorylast.getMainID());
//	                        categorylast.setLevelThre(categorylastList);
//	                    }
//	                }
//	            }
//	        }
	    SupplierCategoryDTO supplierCategoryDTO = new SupplierCategoryDTO();
	    SupplierVO supplierVO = this.findSupplierInfo();
	    supplierCategoryDTO.setSupplierID(supplierVO.getMainID());
        List<SupplierCategoryVO> businclassList =  supplierService.findSupplierCategory(supplierCategoryDTO);
        model.addObject("supplierVO", supplierVO);
        model.addObject("operatorMethod", "add");
        model.addObject("businclassList", businclassList);
		//model.addObject("categoryList", categoryList);		//与之前系统里的产品分类有关的，暂时没用上
		
	    
	    List<FreightTemplateVO> freightTemplateList = freightTemplateService.findFreightTemplateBySupplierID(supplierVO.getMainID());
	    model.addObject("freightTemplateList", freightTemplateList);
		return model;
	}
	
	/**
	 * 更新商品
	 * @author zhoulei
	 * @param mainID
	 * @param name
	 * @param virtual
	 * @param catalog
	 * @param picURL
	 * @param unit
	 * @param description
	 * @param productTypeMainID
	 * @param brandID
	 * @param title
	 * @param metaKeywords
	 * @param metaDescription
	 * @param productDetail
	 * @param isRecommend
	 * @param subTitle
	 * @param saleService
	 * @param productProp
	 * @param productType
	 * @param isBuy
	 * @param isLowPrice
	 * @param isSales
	 * @return
	 */
	 @RequestMapping("/updateProduct")
    public ModelAndView updateProduct(String mainID, String name, String virtual, String catalog, String[] picsURL,
                                      String unit, String description, String productTypeMainID, String brandID,
                                      String title, String metaKeywords, String metaDescription, String productDetail,
                                      String isRecommend, String subTitle, String saleService,String productProp,String productType,String isBuy,String isLowPrice,String isSales,String supplierID,String supplierCategoryID,String[] items,String productStandPrice,Integer status,String freightTemplateID) {

        virtual = "0";
        String productID = productService.updateProduct(mainID, name, virtual, catalog, null, unit, description,
                                                        productTypeMainID, brandID, title, metaKeywords,
                                                        metaDescription, getCurrentOperator(), productDetail,
                                                        isRecommend, subTitle, saleService,productProp,productType,isBuy,isLowPrice,isSales,supplierID,supplierCategoryID,productStandPrice,status, freightTemplateID);
        itemService.batchAddOrEditItem(productID, productTypeMainID, items, getCurrentOperator(),picsURL);
        return new ModelAndView("redirect:item.htm?type=sale");

    }
	/**
	 * 
	 * @date 2015年5月13日
	 * @author moo
	 * @param parentMainID
	 * @param response
	 * @return void
	 * @since JDK 1.6
	 * @Description 下级类目
	 */
	@RequestMapping(value="subProductCategory", method=RequestMethod.GET)
	public void subProductCategory(String parentMainID, HttpServletResponse response){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/supplier/product_category");
		List<ProductCategoryVO> categoryList = productCategoryService.findProductCatByParentIDForSupplier(parentMainID);
		StringBuffer buffer = new StringBuffer();
		buffer.append("{\"categoryList\":[");
		ProductCategoryVO categoryVO = null;
		for (int i = 0; i < categoryList.size(); i++) {
			categoryVO = categoryList.get(i);
			if(i == 0){
				buffer.append("{\"id\": \""+categoryVO.getMainID()+"\", \"name\": \""+categoryVO.getName()+"\"}");
			}else{
				buffer.append(", {\"id\": \""+categoryVO.getMainID()+"\", \"name\": \""+categoryVO.getName()+"\"}");
			}
		}
		buffer.append("]}");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(buffer.toString());
		} catch (IOException e) {
			logger.error("subProductCategory", e);
			e.printStackTrace();
		} finally {
			if(out != null){
				out.close();
			}
		}
		
	}
	
	
	/**
	 * 
	 * @date 2015年5月13日
	 * @author moo
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description 商品搜索
	 */
	@RequestMapping(value="item_old", method=RequestMethod.GET)
	public ModelAndView item_old(String itemName, String type, String categoryMainID, String pageNo){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/supplier/item_list");
		ItemDTO itemDTO = new ItemDTO();
		if(StringUtil.isNotEmpty(pageNo)){
			itemDTO.setPageNo(Long.parseLong(pageNo));
		}
		if(StringUtil.isNotEmpty(itemName)){
			itemDTO.setName(itemName);
			model.addObject("itemName", itemName);
		}
		itemDTO.setPageSize(10L);
		List<ItemVO> itemList = null;
		//supplierItemService.updateSupplierItemStatusWhichStockLess20(this.findSupplierInfo().getMainID());自动下架
		if(!StringUtil.isNotEmpty(type)){
			itemDTO.setCategoryID(categoryMainID);
			itemList = itemService.findItemByCategoryId(itemDTO);
		}else if("sale".equals(type)){
			itemDTO.setSupplierID(this.findSupplierInfo().getMainID());
			itemDTO.setStatus(1);//审核通过
			itemList = itemService.findSupplierItemPass(itemDTO);
		}else if("wait".equals(type)){
			itemDTO.setSupplierID(this.findSupplierInfo().getMainID());
			//itemList = itemService.findSupplierItemWaiting(itemDTO);
			itemDTO.setStatus(2);//正常
            itemList = itemService.findSupplierItemPass(itemDTO);
		}else if("unSale".equals(type)){
			itemDTO.setSupplierID(this.findSupplierInfo().getMainID());
			itemDTO.setStatus(0);//下架
			itemList = itemService.findSupplierItemPass(itemDTO);
		}
		CodeConfigVO saftyStock = systemCodeService.findCodeConfigByID("saftyStock");
		model.addObject("saftyStock", saftyStock.getCodeEx());
		model.addObject("itemList", itemList);
		model.addObject("itemDTO", itemDTO);
		model.addObject("type", type);
		model.addObject("categoryMainID", categoryMainID);
		model.addObject("stringUtil", new StringUtil());
		return model;
		
	}
	
	/**
	 * 
	 * @date 2015年5月14日
	 * @author moo
	 * @param itemMainID
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description 商品详细
	 */
	@RequestMapping(value="itemDetail", method=RequestMethod.GET)
	public ModelAndView itemDetail(String itemMainID, String type){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/supplier/item_detail");
		ItemVO itemVO = null;
		if(StringUtil.isEmpty(type)){
			itemVO = itemService.findItemByMainID(itemMainID);
		}else{
			itemVO = itemService.findItemSupplieredByItemMainIDAndSupplierID(itemMainID, this.findSupplierInfo().getMainID());
		}
		ProductVO productVO = productService.findProductByMainIDAll(itemVO.getProductID());// 产品信息
		model.addObject("productVO", productVO);
        List<ProductDetailVO> productDetails = productService.findProductDetailList(itemVO.getProductID());
        model.addObject("productDetails", productDetails);
        List<FirtSecondThirdCategoryVO>  firtSecondThirdCategoryList = productCategoryService.findFirtSecondThirdCategoryVOByProductID(itemVO.getProductID());
		model.addObject("firtSecondThirdCategoryList", firtSecondThirdCategoryList);
		CodeConfigVO saftyStock = systemCodeService.findCodeConfigByID("saftyStock");
		model.addObject("saftyStock", saftyStock.getCodeEx());
		model.addObject("itemVO", itemVO);
		return model;
	}
	
	/**
	 * 
	 * @date 2015年5月14日
	 * @author moo
	 * @param itemMainID
	 * @return
	 * @return ModelAndView
	 * @throws Exception 
	 * @since JDK 1.6
	 * @Description 保存商品
	 */
	@RequestMapping(value="item_old", method=RequestMethod.POST)
	public void addSupplierItem(String itemMainID, String salePrice, String stock, HttpServletResponse response) throws Exception{
		ItemVO itemVO = itemService.findItemByMainID(itemMainID);
		SupplierItemDTO supplierItemDTO = new SupplierItemDTO();
		supplierItemDTO.setSupplierID(this.findSupplierInfo().getMainID());
		supplierItemDTO.setItemID(itemMainID);
		supplierItemDTO.setProductID(itemVO.getProductID());
		supplierItemDTO.setStock(Integer.parseInt(stock));
		supplierItemDTO.setSalesPrice(Double.parseDouble(salePrice));
		supplierItemDTO.setSupplierName(this.findSupplierInfo().getCompanyName());
		int result = supplierItemService.addOrEditSupplierItem(supplierItemDTO);
		if(result != 0)
			throw new Exception("addSupplierItem,result:"+result);
		else{
			PrintWriter out = response.getWriter();
			out.print(0);
			out.close();
		}
	}
	@RequestMapping(value="itemRemove", method=RequestMethod.POST)
	public void deleteSupplierItem(String itemMainID, HttpServletResponse response) throws Exception{
//		SupplierItemDTO supplierItemDTO = new SupplierItemDTO();
//		supplierItemDTO.setSupplierID(this.findSupplierInfo().getMainID());
//		supplierItemDTO.setItemID(itemMainID);
//		supplierItemDTO.setIsDelete(1);
//		int result = supplierItemService.deleteSupplierItem(supplierItemDTO);
		itemService.deleteItemByMainID(itemMainID);			//更改kplus_item中的isDelete,这里最好做一个检查
//		if(result != 1)
//			throw new Exception("deleteSupplierItem   result:"+result);
//		else{
//			PrintWriter out = response.getWriter();
//			out.print(0);
//			out.close();
//		}
	}
	/**
	 * 
	 * @date 2015年5月15日
	 * @author moo
	 * @param pageNO
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description 查看订单
	 */
	@RequestMapping(value="order", method=RequestMethod.GET)
	public ModelAndView getSupplierSaleOrder(String pageNO, String fromDate, String endDate, 
			String customerName, String orderCode,String orderStatus){
		ModelAndView model = this.newModelAndView();
		SalesOrderDTO saleOrderDTO = new SalesOrderDTO();
		saleOrderDTO.setSupplierID(this.findSupplierInfo().getMainID());
		saleOrderDTO.setPageSize(10L);
		if(StringUtil.isNotEmpty(pageNO)){
			saleOrderDTO.setPageNo(Long.parseLong(pageNO));
		}
		if(StringUtil.isNotEmpty(fromDate)){
			saleOrderDTO.setFromDate(fromDate);
		}
		if(StringUtil.isNotEmpty(endDate)){
			saleOrderDTO.setEndDate(endDate);
		}
		if(StringUtil.isNotEmpty(customerName)){
			saleOrderDTO.setCustomerUserName(customerName);
		}
		if(StringUtil.isNotEmpty(orderCode)){
			saleOrderDTO.setMainID(orderCode);
		}
		if(StringUtil.isNotEmpty(orderStatus)){
		    if(Integer.parseInt(orderStatus)==2){
		        saleOrderDTO.setPaymentStatus(1);
		        saleOrderDTO.setOrderStatus(1);
		    }else{
			    saleOrderDTO.setOrderStatus(Integer.parseInt(orderStatus));;
		    }
		}
		
		List<SalesOrderVO> orderList = salesOrderService.findOrdersBySupplierID(saleOrderDTO);
		model.setViewName("screen/supplier/order_list");
		model.addObject("orderList", orderList);
		model.addObject("saleOrderDTO", saleOrderDTO);
		
		
		return model;
		
	}
	
	/**
	 * 
	 * @date 2015年7月22日
	 * @author yuanyuan
	 * @param response
	 * @since JDK 1.6
	 * @Description  导出订单
	 */
	 @RequestMapping("exportSupplierSalesOrder")
	    public void exportSupplierSalesOrder(HttpServletResponse response,String fromDate,String endDate,Integer orderStatus,String customerName,String orderCode) {
	        try {
	            SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
	            if (StringUtils.isNotEmpty(fromDate)) salesOrderDTO.setFromDate(fromDate);
	            if (StringUtils.isNotEmpty(endDate)) salesOrderDTO.setEndDate(endDate);
	            if (StringUtils.isNotEmpty(orderStatus+"")) salesOrderDTO.setOrderStatus(orderStatus);
	            if (StringUtil.isNotEmpty(customerName)) salesOrderDTO.setCustomerUserName(customerName);
	            if (StringUtil.isNotEmpty(orderCode)) salesOrderDTO.setMainID(orderCode);
	            salesOrderDTO.setSupplierID(this.findSupplierInfo().getMainID());
	            salesOrderDTO.setPageSize(1000L);
	            salesOrderService.exportSalesOrderBySupplier(response, salesOrderDTO);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	
	@RequestMapping("changeOrderStatus")
    public @ResponseBody
    JsonResult changeOrderStatus(String orderIDs, String orderStatus,String expressNumber) {
        int result = 0;
        if (StringUtils.isNotEmpty(orderIDs) && StringUtils.isNotEmpty(orderStatus))
            result = salesOrderService.updateSalesOrderStatus(orderIDs, orderStatus,expressNumber);
            if (result == 0) return JsonResult.create();
            // else if (result == 1000) return new JsonResult(ResultCode.ERROR_UNPICKUP);
            // else if (result == 1001) return new JsonResult(ResultCode.ERROR_UNWRITESERIALISENO);//订单含有尚未填写序列号的商品！
            //  else if (result == 2000) return new JsonResult(ResultCode.ERROR_UNPAY);//订单尚未付款！
            else return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
	
	
	@RequestMapping("seeItem")
    public @ResponseBody
    JsonResult seeItem(String productID, String itemID) {
        if (StringUtils.isNotEmpty(productID) && StringUtils.isNotEmpty(itemID)){
        	ItemDTO itemDTO = new ItemDTO();
        	itemDTO.setMainID(itemID);
        	itemDTO.setProductID(productID);
        	itemDTO.setStatus(1);
        	ItemVO itemVO = itemService.seeItem(itemDTO);
        	if(itemVO != null){
        		return JsonResult.create();
        	} 
            else{
            	return new JsonResult(ResultCode.ERROR_SYSTEM);
            }
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }
	
    /**
     * 修改订单价格
     * @param orderIDs
     * @return
     */
	@RequestMapping("editOrderAmount")
    public @ResponseBody
    JsonResult editOrderAmount(String orderID,Double totalAmount) {
        SalesOrderDTO saleOrderDTO = new SalesOrderDTO();
        if (StringUtils.isNotEmpty(orderID))
        	saleOrderDTO.setMainID(orderID);
            saleOrderDTO.setTotalAmount(totalAmount);
            saleOrderDTO.setPayableAmount(totalAmount);
            String ss = "您的订单"+orderID+"商家已完成价格修改，请到我的订单中完成支持。";
            SMS.EmsSendTest.sendMsg(ss, memberSerivce.findCustomeByMianId(salesOrderService.findSalesOrderByMainID(orderID).getCustomerID()).getUsername());
            try {
            	 salesOrderService.updateSalesOrder(saleOrderDTO);
            	 return JsonResult.create();
			} catch (Exception e) {
				 return new JsonResult(ResultCode.ERROR_SYSTEM);
			}
           
   }
	
	/**
	 * 
	 * @date 2015年6月16日
	 * @author moo
	 * @param orderID
	 * @param response
	 * @throws IOException
	 * @return void
	 * @since JDK 1.6
	 * @Description 确认取消订单（收到退货）
	 */
	@RequestMapping(value="confirmCancelOrder", method=RequestMethod.POST)
	public void confirmCancelOrder(String orderID, HttpServletResponse response) throws IOException{
		salesOrderService.supplierConfirmOrderCancel(this.findSupplierInfo().getMainID(), orderID);
		PrintWriter out = response.getWriter();
		out.print(0);
		out.close();
	}
	
	/**
	 * 
	 * @date 2015年5月15日
	 * @author moo
	 * @param orderMainID
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description 订单详情
	 */
	@RequestMapping(value="orderDetail", method=RequestMethod.GET)
	public ModelAndView saleOrderDetail(String orderMainID){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/supplier/order_detail");
		SalesOrderVO salesOrder = salesOrderService.getSupplierSalesOrderLine(orderMainID, this.findSupplierInfo().getMainID());
		 SalesOrderTransDTO salesOrderTransDTO = new SalesOrderTransDTO();
        salesOrderTransDTO.setOrderNO(salesOrder.getMainID());
        salesOrderTransDTO.setOrderCode(salesOrder.getExpressNumber());
        salesOrder.setSalesOrderTrans(salesOrderService.getSalesOrderTrans(salesOrderTransDTO));// 物流信息
		model.addObject("salesOrderVO", salesOrder);
		return model;
		
	}
	/**
	 * 
	 * @date 2015年5月15日
	 * @author moo
	 * @param pageNo
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping(value="carrienotes", method=RequestMethod.GET)
	public ModelAndView carriesNote(String pageNo, String status){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/supplier/carriernote_list");
		CarrierNoteDTO carrierNoteDTO = new CarrierNoteDTO();
		if(StringUtil.isNotEmpty(pageNo)){
			carrierNoteDTO.setPageNo(Long.parseLong(pageNo));
		}
		if(StringUtil.isNotEmpty(status)){
			carrierNoteDTO.setStatus(Integer.parseInt(status));
		}
		carrierNoteDTO.setPageSize(10L);
		carrierNoteDTO.setSupplierID(this.findSupplierInfo().getMainID());
		List<CarrierNoteVO> carrierNoteList = carrierNoteService.findCarrierNote(carrierNoteDTO);
		model.addObject("carrierNoteList", carrierNoteList);
		model.addObject("carrierNoteDTO", carrierNoteDTO);
		return model;
		
	}
	/**
	 * 
	 * @date 2015年5月19日
	 * @author moo
	 * @param fromDate
	 * @param endDate
	 * @return void
	 * @throws Exception 
	 * @since JDK 1.6
	 * @Description 生成取货单
	 */
	@RequestMapping(value="carriernote", method=RequestMethod.POST)
	public void addCarrierNote(String fromDate, String endDate, HttpServletResponse response) throws Exception{
		int result = carrierNoteService.addCarrierNoteBySupplierIDAndDate(fromDate, endDate,
				this.findSupplierInfo().getMainID());
		if(result != 0){
			logger.info("生成取货单失败：result:"+result);
		}
		response.sendRedirect("/mall/seller/carrienotes.htm");
	}
	/**
	 * 
	 * @date 2015年5月19日
	 * @author moo
	 * @param carriesNoteIDs
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description 打印取货单
	 */
	@RequestMapping(value="carriernote", method=RequestMethod.GET)
	public ModelAndView carriesNotePrint(String carriesNoteIDs){
		
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/supplier/carriernote");
		List<CarrierNotePrintGroupVO> groupList = carrierNoteService.findCarrierNoteBySupplierIDAndCarrierNoteIDs(this.findSupplierInfo().getMainID(), carriesNoteIDs);
		model.addObject("groupList", groupList);
		model.addObject("carriesNoteIDs", carriesNoteIDs);
		
		return model;
	}
	/**
	 * 
	 * @date 2015年5月20日
	 * @author moo
	 * @param carriesNoteIDs
	 * @param response
	 * @throws IOException
	 * @return void
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping(value="updateCarriernote", method=RequestMethod.POST)
	public void carriesNotePrinted(String carriesNoteIDs, String pickupMan, HttpServletResponse response) throws IOException{
		carrierNoteService.updateCarrierNotePrintedBySupplierIDAndCarrierNoteIDs(this.findSupplierInfo().getMainID(), pickupMan, carriesNoteIDs);
		PrintWriter out = response.getWriter();
		out.print(0);
		out.close();

		
	}
	
	/**
	 * 
	 * @date 2015年5月26日
	 * @author moo
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description 结算单查看
	 */
	@RequestMapping(value="finalstatement", method=RequestMethod.GET)
	public ModelAndView getFinalStatementList(
			@ModelAttribute(value="finalstatementDTO") FinalstatementDTO finalstatementDTO){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/supplier/finalstatement_list");
		if("".equals(finalstatementDTO.getMainID())){
			finalstatementDTO.setMainID(null);
		}
		finalstatementDTO.setPageSize(15L);
		finalstatementDTO.setSupplierID(this.findSupplierInfo().getMainID());
		List<FinalstatementVO> finalstatementList = finalstatementService.getFinalStatementListByPaginateion(finalstatementDTO);
		model.addObject("finalstatementList", finalstatementList);
		model.addObject("finalstatementDTO", finalstatementDTO);
		
		return model;
	}
	
	/**
	 * 
	 * @date 2015年5月26日
	 * @author moo
	 * @param finalStatementID
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description 结算单详细
	 */
	@RequestMapping(value="finalstatementDetail", method=RequestMethod.GET)
	public ModelAndView getFinalStatementDetail(String finalStatementID){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/supplier/finalstatement_detail");
		FinalstatementVO finalstatement = finalstatementService.getFinalStatementByMainID(finalStatementID);
		model.addObject("finalstatement", finalstatement);
		return model;
	}
	
	/**
	 * 
	 * @date 2015年6月2日
	 * @author moo
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping(value="accountInfo", method=RequestMethod.GET)
	public ModelAndView getAccountInfo(){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/supplier/account_info");
		SupplierVO supplier = this.findSupplierInfo();
		supplier = supplierService.findSupplierByID(supplier.getMainID());
		model.addObject("supplier", supplier);
		return model;
	}
	
	/**
	 * 
	 * @date 2015年6月2日
	 * @author moo
	 * @param supplierDTO
	 * @param supplierCookie
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping(value="accountInfo", method=RequestMethod.POST)
	public ModelAndView updateAccountInfo(
			@ModelAttribute(value="supplierDTO") SupplierDTO supplierDTO, 
			@CookieValue(value=Constant.SUPPLIER_MALL_INFO_COOKIE) String supplierCookie){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/supplier/account_info");
		supplierDTO.setMainID(this.findSupplierInfo().getMainID());
		supplierService.updateSupplierInfo(supplierDTO);
		SupplierVO supplier = supplierService.findSupplierByID(this.findSupplierInfo().getMainID());
		cachedClient.add(Constant.MALLSUPPLIERINFO + supplierCookie, Constant.EXP, supplier);
		model.addObject("supplier", supplier);
		return model;
	}
	
	/**
	 * 
	 * @date 2015年6月3日
	 * @author moo
	 * @param salesOrderDTO
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description 销售统计
	 */
	@RequestMapping(value="statistics", method=RequestMethod.GET)
	public ModelAndView getSaleStatistics(
			@ModelAttribute(value="salesOrderDTO") SalesOrderDTO salesOrderDTO){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/supplier/statistics");
		salesOrderDTO.setPageSize(30L);
		salesOrderDTO.setSupplierID(this.findSupplierInfo().getMainID());
		if("".equals(salesOrderDTO.getFromDate())){
			salesOrderDTO.setFromDate(null);
		}
		if("".equals(salesOrderDTO.getEndDate())){
			salesOrderDTO.setEndDate(null);
		}
		List<SalesOrderVO> salesOrderList = salesOrderService.findFinishedOrderBySupplierID(salesOrderDTO);
		model.addObject("salesOrderList", salesOrderList);
		model.addObject("salesOrderDTO", salesOrderDTO);
		return model;
	}
	
	@RequestMapping(value="statisticsExcel", method=RequestMethod.GET)
	public void exportStatistics(String mainIDs, HttpServletResponse response){
		salesOrderService.exportSupplierSalesOrder(response, mainIDs, this.findSupplierInfo().getMainID());
	}
	/**
	 * 
	 * @date 2015年6月15日
	 * @author moo
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description 修改密码页
	 */
	@RequestMapping(value="password", method=RequestMethod.GET)
	public ModelAndView toChangePwd(String info){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/supplier/password");
		model.addObject("info", info);
		return model;
	}
	/**
	 * 
	 * @date 2015年6月15日
	 * @author moo
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description 修改密码
	 */
	@RequestMapping(value="password", method=RequestMethod.POST)
	public ModelAndView changePwd(String oldPwd, String newPwd){
		ModelAndView model = this.newModelAndView();
		String md5Pwd = Md5Algorithm.getInstance().md5Digest(oldPwd.getBytes());
		if(md5Pwd.equals(this.findSupplierInfo().getPassWord())){
			SupplierDTO supplierDTO = new SupplierDTO();
			supplierDTO.setMainID(this.findSupplierInfo().getMainID());
			supplierDTO.setPassWord(Md5Algorithm.getInstance().md5Digest(newPwd.getBytes()));
			supplierService.updateSupplierPwd(supplierDTO);
			model.setViewName("redirect:supplierCenter.htm");
		}else{
			model.setViewName("redirect:password.htm?info=1000");
		}
		
		return model;
	}
	
	/********************Tparts**********************/
	/**
	 * @date 2015年10月30日
	 * @author lby
	 * @param itemMainID
	 * @return
	 * @return JsonResult
	 * @since JDK 1.6
	 * @Description 通过用户输入的产品编号查找对应产品
	 * 
	 * */
    @RequestMapping("findProductByCode")
    public @ResponseBody
    JsonResult findProductByCode(String productCode){
    	JsonResult  JsonResult=new JsonResult(ResultCode.NORMAL);
        JsonResult.setResult(productService.findProductByCode(productCode));
        return JsonResult;
    }
    
	/**
	 * @date 2015年11月3日
	 * @author lby
	 * @param ...
	 * @return
	 * @return JsonResult
	 * @since JDK 1.6
	 * @Description 商品发布
	 * 
	 * */
    @RequestMapping("/addItem")
    public ModelAndView addItem(String supplierID,String supplierName,String supplierCategoryID, String name,String standrardPrice, String salesPrice,
    		String weight,String cubage,String description,String picURL,String productID,String freightTemplateID){
    	String currentOperator = getCurrentOperator();
        String mainID = "";
    	itemService.addOrUpdateItem(mainID,productID, supplierCategoryID, name, standrardPrice, salesPrice,
    			weight, cubage, description, picURL, supplierName, supplierID,
    			currentOperator,freightTemplateID);
    	
        return new ModelAndView("redirect:item.htm?type=sale");
    }

    @RequestMapping("editItem")
    public ModelAndView editItem(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/supplier/product_category");
	    
        ItemVO itemVO = itemService.findItemById(mainID);
        ProductVO productVO = productService.findProductByMainIDNew(itemVO.getProductID());
        modelAndView.addObject("itemVO", itemVO);
        modelAndView.addObject("productVO", productVO);

        SupplierCategoryDTO supplierCategoryDTO = new SupplierCategoryDTO();
	    SupplierVO supplierVO = this.findSupplierInfo();
	    supplierCategoryDTO.setSupplierID(supplierVO.getMainID());
        List<SupplierCategoryVO> businclassList =  supplierService.findSupplierCategory(supplierCategoryDTO);
        modelAndView.addObject("supplierVO", supplierVO);
        modelAndView.addObject("businclassList", businclassList);

        List<FreightTemplateVO> freightTemplateList = freightTemplateService.findFreightTemplateBySupplierID(supplierVO.getMainID());
	    modelAndView.addObject("freightTemplateList", freightTemplateList);

        FreightTemplateVO freightTemplateVO = freightTemplateService.findFreightTemplateByMainID(itemVO.getFreightTemplateID());
        modelAndView.addObject("freightTemplateVO", freightTemplateVO);
        
        modelAndView.addObject("operatorMethod", "edit");
        
        return modelAndView;
    }

    @RequestMapping("/viewItem")
    public ModelAndView viewItem(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/supplier/product_category_look");
	    
        ItemVO itemVO = itemService.findItemById(mainID);
        ProductVO productVO = productService.findProductByMainIDNew(itemVO.getProductID());
        modelAndView.addObject("itemVO", itemVO);
        modelAndView.addObject("productVO", productVO);

        SupplierCategoryDTO supplierCategoryDTO = new SupplierCategoryDTO();
	    supplierCategoryDTO.setMainID(itemVO.getSupplierCategoryID());
	    SupplierCategoryVO childSupplierCategoryVO =  supplierService.findSupplierCategoryByMainID(supplierCategoryDTO);
	    SupplierCategoryVO supplierCategoryVO = null;
	    if (childSupplierCategoryVO != null){
	    	supplierCategoryDTO.setMainID(childSupplierCategoryVO.getParentID());
	    	supplierCategoryVO =  supplierService.findSupplierCategoryByMainID(supplierCategoryDTO);
	    }
        modelAndView.addObject("childSupplierCategoryVO", childSupplierCategoryVO);
        modelAndView.addObject("supplierCategoryVO", supplierCategoryVO);

        FreightTemplateVO freightTemplateVO = freightTemplateService.findFreightTemplateByMainID(itemVO.getFreightTemplateID());
        modelAndView.addObject("freightTemplateVO", freightTemplateVO);

        return modelAndView;
    }

    /**
	 * 更新商品
	 * @author zhoulei
	 * @return
	 */
	 @RequestMapping("/updateItem")
    public ModelAndView updateItem(String itemID,String supplierID,String supplierName,String supplierCategoryID, String name, String standrardPrice,String salesPrice,
    		String weight,String cubage,String description,String picURL,String productID,String freightTemplateID){
    	String currentOperator = getCurrentOperator();
    	itemService.addOrUpdateItem(itemID,productID, supplierCategoryID, name, standrardPrice, salesPrice,
    			weight, cubage, description, picURL, supplierName, supplierID,
    			currentOperator,freightTemplateID);
    	
        return new ModelAndView("redirect:item.htm?type=sale");

    }

	/**
	 * 
	 * @date 2015年11月6日
	 * @author lby
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description 商品搜索
	 */
	@RequestMapping(value="item", method=RequestMethod.GET)
	public ModelAndView item(String itemName, String type, String categoryMainID, String pageNo,String itemCode){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/supplier/item_list");
		ItemDTO itemDTO = new ItemDTO();

		if(StringUtil.isNotEmpty(pageNo)){
			itemDTO.setPageNo(Long.parseLong(pageNo));
		}
		//所以现在以name和mainID都能进行查询
		if(StringUtil.isNotEmpty(itemName)){
			itemDTO.setName(itemName);
			model.addObject("itemName", itemName);
		}
		if(StringUtil.isNotEmpty(itemCode)){
			itemDTO.setSkuCode(itemCode);
			model.addObject("itemCode", itemCode);
		}

		itemDTO.setPageSize(10L);
		itemDTO.setSupplierID(this.findSupplierInfo().getMainID());

//		Map<String, List<ItemVO>> itemMap = new HashMap<String, List<ItemVO>>();
		Map<String, List<ItemVO>> itemMap = new LinkedHashMap<String, List<ItemVO>>();
		//supplierItemService.updateSupplierItemStatusWhichStockLess20(this.findSupplierInfo().getMainID());自动下架

		if(!StringUtil.isNotEmpty(type)){
			itemDTO.setCategoryID(categoryMainID);
			//itemList = itemService.findItemByCategoryId(itemDTO);
		}else if("sale".equals(type)){
			itemDTO.setStatus(1);//审核通过

			List<ItemVO> itemList = null;
			List<ProductVO> productList = productService.findProductBySupplierItem(itemDTO);
			for(ProductVO productVO:productList){
				itemDTO.setProductID(productVO.getMainID());
	        	itemList = itemService.findSupplierPassItems(itemDTO);
	        	itemMap.put(productVO.getCode(), itemList);
	        }
			//itemList = itemService.findSupplierItemPass(itemDTO);
		}else if("wait".equals(type)){
			//itemList = itemService.findSupplierItemWaiting(itemDTO);
			itemDTO.setStatus(2);//正常
	        //itemList = itemService.findSupplierItemPass(itemDTO);
		}else if("unSale".equals(type)){
			itemDTO.setStatus(0);//下架

			List<ItemVO> itemList = null;
			List<ProductVO> productList = productService.findProductBySupplierItem(itemDTO);
			for(ProductVO productVO:productList){
				itemDTO.setProductID(productVO.getMainID());
	        	itemList = itemService.findSupplierPassItems(itemDTO);
	        	itemMap.put(productVO.getCode(), itemList);
	        }
			//itemList = itemService.findSupplierItemPass(itemDTO);
		}

		//CodeConfigVO saftyStock = systemCodeService.findCodeConfigByID("saftyStock");
		//model.addObject("saftyStock", saftyStock.getCodeEx());		//并没有什么卵用
		
		//model.addObject("itemList", itemList);
		model.addObject("itemMap", itemMap);
		model.addObject("itemDTO", itemDTO);
		model.addObject("type", type);
		model.addObject("categoryMainID", categoryMainID);
		model.addObject("stringUtil", new StringUtil());
		return model;
	}

	/**
	 * 
	 * @date 2015年11月9日
	 * @author lby
	 * @return
	 * @since JDK 1.6
	 * @Description 商品状态更新(同时更新kplus_zy_supplier_item的状态)
	 */
	@RequestMapping("/updateItemStatus")
	    public @ResponseBody
	    JsonResult updateItemStatus(String mainIDs,String status) {
		Integer count = itemService.updateItemStatus(mainIDs, status);
        if (count == 1) {
        	JsonResult  jsonResult=new JsonResult(ResultCode.NORMAL);
        	jsonResult.setResult(count);
            return jsonResult;
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
	}
	@RequestMapping(value="itemfreight", method=RequestMethod.GET)
	public ModelAndView itemfreight(){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/supplier/item_freight");
		SupplierVO supplier = this.findSupplierInfo();
		supplier = supplierService.findSupplierByID(supplier.getMainID());
		model.addObject("supplier", supplier);
		return model;
	}
	
}


