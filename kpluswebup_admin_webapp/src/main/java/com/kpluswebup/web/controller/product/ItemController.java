package com.kpluswebup.web.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.ItemPropDTO;
import com.kpluswebup.web.service.ItemPropService;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.ProductPropService;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.vo.ItemPropVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.ProductTypeItemPropVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/product")
public class ItemController extends BaseController {

    @Autowired
    private ItemPropService    itemPropService;

    @Autowired
    private ItemService        itemService;

    @Autowired
    private ProductPropService productPropService;

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private ProductService     productService;

    /**
     * @date 2014年10月31日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/itemPropList")
    public ModelAndView getItemPropList(String pageNo, String pageSize) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/itemprop_list");

        ItemPropDTO itemPropDTO = new ItemPropDTO();
        if (StringUtil.isNotEmpty(pageNo)) {
            itemPropDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(pageSize)) {
            itemPropDTO.setPageSize(Long.parseLong(pageSize));
        }
        itemPropDTO.setOrderByClause("order by  modifytime desc");
        List<ItemPropVO> list = itemPropService.findItemPropByPagination(itemPropDTO);
        modelAndView.addObject("list", list);
        modelAndView.addObject("itemPropDTO", itemPropDTO);
        return modelAndView;
    }

    /**
     * @date 2014年10月31日
     * @author zhuhp
     * @param name
     * @param description
     * @return
     * @since JDK 1.6
     * @Description
     */

    @RequestMapping("/addItemProp")
    public ModelAndView addItemProp(String name, String description, String itemPropValue) {
        ItemPropDTO itemPropDTO = new ItemPropDTO();
        itemPropDTO.setName(name);
        itemPropDTO.setDescription(description);
        itemPropService.addItemProp(itemPropDTO, itemPropValue);
        return new ModelAndView("redirect:itemPropList.htm");
    }

    /**
     * 删除一个属性值
     * 
     * @date 2014年11月1日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/deleteItemtPropVale")
    public @ResponseBody
    JsonResult deleteItemtPropVale(String mainID) {
        Boolean isSuccess = itemPropService.deleteItemtPropVale(mainID);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * @date 2014年11月1日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/deleteItemProp")
    public @ResponseBody
    JsonResult deleteItemProp(String mainID) {
        Boolean isSuccess = itemPropService.deleteItemPropByMainID(mainID);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * @date 2014年12月5日
     * @author zhuhp
     * @param propValue
     * @param itemPropMainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/addItemPropValue")
    public @ResponseBody
    JsonResult addItemPropValue(String propValue, String itemPropMainID) {
        Boolean isSuccess = itemPropService.insertItemPropValue(propValue, itemPropMainID, getCurrentOperator());
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("/deleteItem")
    public @ResponseBody
    JsonResult deleteItem(String mainID) {
        try {
            itemService.deleteItemByMainID(mainID);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * @date 2014年11月24日
     * @author zhuhp
     * @param productID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/addItemPage")
    public ModelAndView addItemPage(String productID, String productTypeMainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/item_add");
        List<ProductTypeItemPropVO> productTypeItemPropList = productPropService.findProductTypeItemProp(productTypeMainID);
        modelAndView.addObject("productTypeItemPropList", productTypeItemPropList);
        List<ItemVO> itemList = itemService.findItemByProductID(productID);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("productID", productID);
        modelAndView.addObject("productTypeMainID", productTypeMainID);
        ProductVO productVO=productService.findProductByMainIDAll(productID);
        modelAndView.addObject("productVO",productVO);
        return modelAndView;
    }

    @RequestMapping("/addItem")
    public ModelAndView addItem(@RequestParam(value = "items")
    String[] items, String productID, String productTypeMainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/item_add");
        itemService.batchAddOrEditItem(productID, productTypeMainID, items, getCurrentOperator(),null);
        return new ModelAndView("redirect:addItemPage.htm?productID=" + productID + "&productTypeMainID="
                                + productTypeMainID);
    }

    @RequestMapping("itemDetail")
    public ModelAndView itemDetail(String productID, String productTypeMainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/item_detail");
        List<ProductTypeItemPropVO> productTypeItemPropList = productPropService.findProductTypeItemProp(productTypeMainID);
        modelAndView.addObject("productTypeItemPropList", productTypeItemPropList);
        List<ItemVO> itemList = itemService.findItemByProductID(productID);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("productID", productID);
        modelAndView.addObject("productTypeMainID", productTypeMainID);
        return modelAndView;
    }
}
