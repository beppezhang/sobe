package com.kpluswebup.web.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.ProductTypeDTO;
import com.kpluswebup.web.service.ProductTypeBrandService;
import com.kpluswebup.web.service.ProductTypeService;
import com.kpluswebup.web.vo.ProductTypeBrandVO;
import com.kpluswebup.web.vo.ProductTypeVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/product")
public class ProductTypeController extends BaseController {

    @Autowired
    private ProductTypeService      productTypeService;

    @Autowired
    private ProductTypeBrandService productTypeBrandService;

    /**
     * 产品类型列表
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/productTypeList")
    public ModelAndView getProductTypeList(String pageNo, String pageSize) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/productType_list");
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        if (StringUtil.isNotEmpty(pageNo)) {
            productTypeDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(pageSize)) {
            productTypeDTO.setPageSize(Long.parseLong(pageSize));
        }
        productTypeDTO.setOrderByClause("order by  modifytime desc");
        List<ProductTypeVO> list = productTypeService.findProductTypeByPagination(productTypeDTO);
        modelAndView.addObject("list", list);
        modelAndView.addObject("productTypeDTO", productTypeDTO);
        return modelAndView;
    }

    /**
     * 删除单个产品类型
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("deleteProductType")
    public @ResponseBody
    JsonResult deleteProductType(String mainID) {
        try {
            Boolean isSuccess = productTypeService.isDeleteProductType(mainID);
            if (isSuccess) {
                return JsonResult.create();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * 新增产品类型页面
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/addProductTypePage")
    public ModelAndView addProductTypePage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/productType_add");
        return modelAndView;
    }

    /**
     * 编辑页面
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/editProductTypePage")
    public ModelAndView editProductTypePage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/productType_edit");
        ProductTypeVO productTypeVO = productTypeService.findProductTypeByMainID(mainID);
        modelAndView.addObject("productTypeVO", productTypeVO);
        modelAndView.addObject("productTypeMainID", mainID);
        return modelAndView;
    }

    /**
     * 编辑产品类型
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/editProductType")
    public ModelAndView editProductType(String mainID, String name, String description) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/productType_edit");
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        if (StringUtil.isNotEmpty(name)) {
            productTypeDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(description)) {
            productTypeDTO.setDescription(description);
        }
        if (StringUtil.isNotEmpty(mainID)) {
            productTypeDTO.setmainID(mainID);
        }
        productTypeDTO.setModifier(getCurrentOperator());
        productTypeService.updatProductType(productTypeDTO);

        return new ModelAndView("redirect:productTypeList.htm");
    }

    /**
     * 新增产品类型
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/addProductType")
    public ModelAndView addProductType(String mainID, String name, String description) {
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        if (StringUtil.isNotEmpty(name)) {
            productTypeDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(description)) {
            productTypeDTO.setDescription(description);
        }
        if (StringUtil.isNotEmpty(mainID)) {
            productTypeDTO.setmainID(mainID);
        }
        productTypeDTO.setCreator(getCurrentOperator());
        productTypeService.addProductType(productTypeDTO);
        return new ModelAndView("redirect:productTypeList.htm");
    }

    /**
     * @date 2014年11月10日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("productTypeBrandPage")
    public ModelAndView productTypeBrandPage(String productTypeMainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/productType_brand");
        List<ProductTypeBrandVO> productTypeBrandList = productTypeBrandService.findProductTypeBrandByPtid(productTypeMainID);
        modelAndView.addObject("productTypeBrandList", productTypeBrandList);
        modelAndView.addObject("productTypeMainID", productTypeMainID);
        return modelAndView;
    }

    /**
     * @date 2014年11月24日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("deleteProductTypeBrand")
    public @ResponseBody
    JsonResult deleteProductTypeBrand(String mainID) {
        try {
            Boolean isSuccess = productTypeBrandService.deleteProductTypeBrandByID(mainID);
            if (isSuccess) {
                return JsonResult.create();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * @date 2014年11月24日
     * @author zhuhp
     * @param productTypeMainID
     * @param brandID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("addProductTypeBrand")
    public @ResponseBody
    JsonResult addProductTypeBrand(String productTypeMainID, String brandIDs) {
        try {
            productTypeBrandService.addProductTypeBrand(productTypeMainID, brandIDs,getCurrentOperator());
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }
    
    /**
     * 
     * @date 2014年11月24日
     * @author zhuhp
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
}
