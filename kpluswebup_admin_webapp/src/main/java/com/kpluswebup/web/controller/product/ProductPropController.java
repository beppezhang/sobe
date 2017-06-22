package com.kpluswebup.web.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.ItemPropDTO;
import com.kpluswebup.web.domain.ProductPropDTO;
import com.kpluswebup.web.domain.ProductPropValueDTO;
import com.kpluswebup.web.service.ItemPropService;
import com.kpluswebup.web.service.ProductPropService;
import com.kpluswebup.web.vo.ItemPropVO;
import com.kpluswebup.web.vo.ItemPropValueVO;
import com.kpluswebup.web.vo.ProductPropVO;
import com.kpluswebup.web.vo.ProductPropValueVO;
import com.kpluswebup.web.vo.ProductTypeItemPropVO;
import com.kpluswebup.web.vo.ProductTypeProductPropVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/product")
public class ProductPropController extends BaseController {

    @Autowired
    private ProductPropService productPropService;

    @Autowired
    private ItemPropService    itemPropService;

    /**
     * 产品类型列表
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/productPropList")
    public ModelAndView getProductPropList(String pageNo, String pageSize) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/productprop_list");
        ProductPropDTO productPropDTO = new ProductPropDTO();
        if (StringUtil.isNotEmpty(pageNo)) {
            productPropDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(pageSize)) {
            productPropDTO.setPageSize(Long.parseLong(pageSize));
        }
        productPropDTO.setOrderByClause("order by  modifytime desc");
        List<ProductPropVO> list = productPropService.findProductPropByPagination(productPropDTO);
        modelAndView.addObject("list", list);
        modelAndView.addObject("productPropDTO", productPropDTO);
        return modelAndView;
    }

    /**
     * 添加一个产品属性
     * 
     * @date 2014年10月29日
     * @author zhuhp
     * @param mainID
     * @param name
     * @param propType
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/addProductProp")
    public ModelAndView addProductProp(String mainID, String name, String propType, String productPropValue) {
        ProductPropDTO productPropDTO = new ProductPropDTO();
        if (StringUtil.isNotEmpty(mainID)) {
            productPropDTO.setMainID(mainID);
        }
        if (StringUtil.isNotEmpty(name)) {
            productPropDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(propType)) {
            productPropDTO.setPropType(Integer.parseInt(propType));
        }
        productPropDTO.setCreator(getCurrentOperator());
        productPropService.addProductProp(productPropDTO);

        if (StringUtil.isNotEmpty(productPropValue)) {
            String[] productPropValues = productPropValue.split(",");
            for (String propValue : productPropValues) {
                ProductPropValueDTO productPropValueDTO = new ProductPropValueDTO();
                productPropValueDTO.setName(propValue);
                productPropValueDTO.setmainID(UUIDUtil.getUUID());
                productPropValueDTO.setproductPropID(mainID);
                productPropValueDTO.setCreator(getCurrentOperator());
                productPropService.saveProductPropValue(productPropValueDTO);
            }

        }

        return new ModelAndView("redirect:productPropList.htm");
    }

    /**
     * 添加一个属性值
     * 
     * @date 2014年11月1日
     * @author zhuhp
     * @param mainID
     * @param name
     * @param propType
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/saveProductPropValue")
    public @ResponseBody
    JsonResult saveProductPropValue(String mainID, String productPropValue, String productPropMainID) {
        ProductPropValueDTO productPropValueDTO = new ProductPropValueDTO();
        productPropValueDTO.setName(productPropValue);
        productPropValueDTO.setmainID(UUIDUtil.getUUID());
        productPropValueDTO.setproductPropID(productPropMainID);
        productPropValueDTO.setCreator(getCurrentOperator());
        try {
            productPropService.saveProductPropValue(productPropValueDTO);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * 删除产品类型的某个一个属性
     * 
     * @date 2014年10月30日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/deleteProductPropVale")
    public @ResponseBody
    JsonResult deleteProductPropVale(String mainID) {
        Boolean isSuccess = productPropService.deleteProductPropVale(mainID);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * 删除产品类型
     * 
     * @date 2014年10月30日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/deleteProductProp")
    public @ResponseBody
    JsonResult deleteProductProp(String mainID) {
        Boolean isSuccess = productPropService.isDeleteProductProp(mainID);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * @date 2014年10月31日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/deleteProductTypeProductProp")
    public @ResponseBody
    JsonResult deleteProductTypeProductProp(String mainID) {
        Boolean isSuccess = productPropService.deleteProductTypeProductProp(mainID);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * @date 2014年11月7日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/deleteProductTypeItemProp")
    public @ResponseBody
    JsonResult deleteProductTypeItemProp(String mainID) {
        Boolean isSuccess = productPropService.deleteProductTypeItemtProp(mainID);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * @date 2014年10月30日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/addProductTypeProductPropPage")
    public ModelAndView addProductTypeProductPropPage(String productTypeMainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/productType_productProp_list");
        modelAndView.addObject("productTypeMainID", productTypeMainID);
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
        modelAndView.addObject("productTypeProductPropList", productTypeProductPropList);
        return modelAndView;
    }

    /**
     * @date 2014年10月30日
     * @author zhuhp
     * @param productTypeMainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/addProductTypeProductProp")
    public @ResponseBody
    JsonResult addProductTypeProductProp(String productTypeMainID, String productPropID, String productPropName,
                                         @RequestParam(value = "productPropValueID[]")
                                         String[] productPropValues, String mainID) {
        try {
            StringBuilder productPropValue = new StringBuilder();
            StringBuilder productPropMainID = new StringBuilder();
            if (StringUtil.isNotEmpty(mainID)) {
                for (String value : productPropValues) {
                    productPropValue.append(value.split(":")[0]);
                    productPropValue.append(',');
                    productPropMainID.append(value.split(":")[1]);
                    productPropMainID.append(',');
                }
                productPropService.editProductTypeProductProp(productTypeMainID, productPropID, productPropName,
                                                              productPropValue.toString(),
                                                              productPropMainID.toString(), getCurrentOperator(),
                                                              mainID);
            } else {
                for (String value : productPropValues) {
                    productPropValue.append(value.split("_")[0]);
                    productPropValue.append(',');
                    productPropMainID.append(value.split("_")[1]);
                    productPropMainID.append(',');
                }
                productPropService.addProductTypeProductProp(productTypeMainID, productPropID, productPropName,
                                                             productPropValue.toString(), productPropMainID.toString(),
                                                             getCurrentOperator());
            }
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * @date 2014年11月7日
     * @author zhuhp
     * @param productTypeMainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("addProductTypeItemPropPage")
    public ModelAndView addProductTypeItemPropPage(String productTypeMainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/productType_itemProp_list");
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
        modelAndView.addObject("productTypeItemPropList", productTypeItemPropList);
        return modelAndView;
    }

    /**
     * @date 2014年11月7日
     * @author zhuhp
     * @param productTypeMainID
     * @param itemPropID
     * @param itemPropName
     * @param itemPropValues
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/saveProductTypeItemProp")
    public @ResponseBody
    JsonResult saveProductTypeItemProp(String productTypeMainID, String itemPropID, String itemPropName,
                                       @RequestParam(value = "itemPropValueID[]")
                                       String[] itemPropValues, String mainID) {
        try {
            StringBuilder itemPropValue = new StringBuilder();
            StringBuilder itemPropValueID = new StringBuilder();
            if (StringUtil.isNotEmpty(mainID)) {
                for (String value : itemPropValues) {
                    itemPropValue.append(value.split(":")[0]);
                    itemPropValue.append(',');
                    itemPropValueID.append(value.split(":")[1]);
                    itemPropValueID.append(',');

                }
                productPropService.editProductTypeItemProp(productTypeMainID, itemPropID, itemPropName,
                                                           itemPropValue.toString(), itemPropValueID.toString(),
                                                           mainID, getCurrentOperator());
            } else {
                for (String value : itemPropValues) {
                    itemPropValue.append(value.split("_")[0]);
                    itemPropValue.append(',');
                    itemPropValueID.append(value.split("_")[1]);
                    itemPropValueID.append(',');

                }
                productPropService.addProductTypeItemProp(productTypeMainID, itemPropID, itemPropName,
                                                          itemPropValue.toString(), itemPropValueID.toString(),
                                                          getCurrentOperator());
            }
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }
}
