package com.kpluswebup.web.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.ProductCategoryDTO;
import com.kpluswebup.web.domain.ProductTypeDTO;
import com.kpluswebup.web.service.ProductCategoryService;
import com.kpluswebup.web.service.ProductTypeService;
import com.kpluswebup.web.service.VehicleTypeService;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.ProductTypeVO;
import com.kpluswebup.web.vo.VehicleTypeVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/product")
public class ProductCategoryController extends BaseController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductTypeService     productTypeService;

    @Autowired
    private VehicleTypeService vehicleTypeService;
    /**
     * @date 2014年11月11日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/productCategoryList")
    public ModelAndView findProductCategoryList() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/productcat_list");
        //List<ProductCategoryVO> list = productCategoryService.findProductOneLevel();
        List<ProductCategoryVO> list = productCategoryService.findProductOneLevelTpartsAdmin();
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    /**
     * @date 2014年11月11日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/deleteProductCategory")
    public @ResponseBody
    JsonResult deleteProductCategory(String mainID) {
        Boolean isSuccess = productCategoryService.isDeleteProductCategory(mainID);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * @date 2014年11月11日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("addProductCategoryPage")
    public ModelAndView addProductCategoryPage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/productcat_add");
        List<ProductCategoryVO> productCategoryList = productCategoryService.findProductOneLevel();
        modelAndView.addObject("productCategoryList", productCategoryList);
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        // TODO 需要改造
        productTypeDTO.setPageSize(10000l);
        List<ProductTypeVO> productTypList = productTypeService.findProductTypeByPagination(productTypeDTO);
        modelAndView.addObject("productTypList", productTypList);
        return modelAndView;
    }

    /**
     * 修改类目页
     * 
     * @date 2014年11月11日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("editProductCategoryPage")
    public ModelAndView editProductCategoryPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/productcat_edit");

        // TODO 需要改造
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        productTypeDTO.setPageSize(10000l);
        List<ProductTypeVO> productTypList = productTypeService.findProductTypeByPagination(productTypeDTO);
        modelAndView.addObject("productTypList", productTypList);


        ProductCategoryVO productCategoryVO = productCategoryService.findProductCategoryByMainID(mainID);
        String parentID = productCategoryVO.getParentID();
        boolean isone = false;
        boolean istwo = false;
        boolean isthree = false;
        if(parentID.equals("0")){
            isone = true;
            modelAndView.addObject("isone", isone);
            List<ProductCategoryVO> productCategoryList = productCategoryService.findProductOneLevel();
            modelAndView.addObject("productCategoryList", productCategoryList);
        }else {
            ProductCategoryVO parent = productCategoryService.findProductCategoryByMainID(parentID);
            String par = parent.getParentID();
            if (par.equals("0")){
                istwo = true;
                modelAndView.addObject("istwo", istwo);
                modelAndView.addObject("parent", parent);
                List<ProductCategoryVO> productCategoryList = productCategoryService.findProductOneLevel();
                modelAndView.addObject("productCategoryList", productCategoryList);
            }
            else {
                ProductCategoryVO parentthree = productCategoryService.findProductCategoryByMainID(par);
                isthree = true;
                modelAndView.addObject("isthree", isthree);
                modelAndView.addObject("parent", parent);
                modelAndView.addObject("parentthree", parentthree);
                List<ProductCategoryVO> productCategoryList = productCategoryService.findProductOneLevel();
                modelAndView.addObject("productCategoryList", productCategoryList);
                List<ProductCategoryVO> twoList = productCategoryService.findProductCatByParentID(parentthree.getMainID());
                modelAndView.addObject("twoList", twoList);
            }
        }
        modelAndView.addObject("productCatVO", productCategoryVO);
        return modelAndView;
    }

    /**
     * @date 2014年11月11日
     * @author zhuhp
     * @param name
     * @param productTypeID
     * @param parentID
     * @param title
     * @param metaKeywords
     * @param metaDescription
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("addProductCategory")
    public ModelAndView addProductCategory(String name, String productTypeMainID, String parentID, String title,
                                           String metaKeywords, String metaDescription,String picUrl,String isRecommend,String sortOrder) {
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setName(name);
        productCategoryDTO.setProductTypeID(productTypeMainID);
        if (StringUtil.isNotEmpty(parentID)) {
            productCategoryDTO.setParentID(parentID);
        } else {
            productCategoryDTO.setParentID("0");
        }
        productCategoryDTO.setMainID(UUIDUtil.getUUID());
        productCategoryDTO.setTitle(title);
        productCategoryDTO.setMetaDescription(metaDescription);
        productCategoryDTO.setMetaKeywords(metaKeywords);
        productCategoryDTO.setCreator(getCurrentOperator());
        productCategoryDTO.setPicUrl(picUrl);
        productCategoryDTO.setIsRecommend(Integer.valueOf(isRecommend));
        productCategoryDTO.setSortOrder(Integer.valueOf(sortOrder));
        productCategoryService.addProductCategory(productCategoryDTO);
        return new ModelAndView("redirect:productCategoryList.htm");
    }

    /**
     * @date 2014年11月11日
     * @author zhuhp
     * @param mainID
     * @param name
     * @param productTypeID
     * @param parentID
     * @param title
     * @param metaKeywords
     * @param metaDescription
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("editProductCategory")
    public ModelAndView editProductCategory(String mainID, String name, String productTypeMainID, String parentID,
                                            String title, String metaKeywords, String metaDescription,String picUrl,String isRecommend,String sortOrder) {
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setName(name);
        productCategoryDTO.setProductTypeID(productTypeMainID);
        if (StringUtil.isNotEmpty(parentID)) {
            productCategoryDTO.setParentID(parentID);
        } else {
            productCategoryDTO.setParentID("0");
        }
        productCategoryDTO.setMainID(mainID);
        productCategoryDTO.setTitle(title);
        productCategoryDTO.setMetaDescription(metaDescription);
        productCategoryDTO.setMetaKeywords(metaKeywords);
        productCategoryDTO.setModifier(getCurrentOperator());
        productCategoryDTO.setPicUrl(picUrl);
        productCategoryDTO.setIsRecommend(Integer.valueOf(isRecommend));
        productCategoryDTO.setSortOrder(Integer.valueOf(sortOrder));
        productCategoryService.editProductCategory(productCategoryDTO);
        return new ModelAndView("redirect:productCategoryList.htm");
    }

    @RequestMapping("/findProductCatByParentID")
    public @ResponseBody
    JsonResult findProductCatByParentID(String mainID) {
        try {
	        VehicleTypeVO vehicleTypeVO = new VehicleTypeVO();
	        vehicleTypeVO.setOrderByClause("order by  modifytime desc");
	        vehicleTypeVO.setProductCategoryId(mainID);
	    	List<VehicleTypeVO> list = vehicleTypeService.findByPagination(vehicleTypeVO);

	    	JsonResult jsonResult = new JsonResult(ResultCode.NORMAL);
            jsonResult.setResult(list);
            return jsonResult;
        	
//            List<ProductCategoryVO> list = productCategoryService.findProductCatByParentID(mainID);
//            JsonResult jsonResult = new JsonResult(ResultCode.NORMAL);
//            jsonResult.setResult(list);
//            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("/findVehicleTypeList")
    public @ResponseBody
    JsonResult findVehicleTypeList(String mainID) {
        try {
	        VehicleTypeVO vehicleTypeVO = new VehicleTypeVO();
	        vehicleTypeVO.setOrderByClause("order by  modifytime desc");
	        vehicleTypeVO.setProductCategoryId(mainID);
	    	List<VehicleTypeVO> list = vehicleTypeService.findByPagination(vehicleTypeVO);

	    	JsonResult jsonResult = new JsonResult(ResultCode.NORMAL);
            jsonResult.setResult(list);
            return jsonResult;
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }
}
