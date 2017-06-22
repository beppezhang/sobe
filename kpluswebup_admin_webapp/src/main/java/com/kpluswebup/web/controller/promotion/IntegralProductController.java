package com.kpluswebup.web.controller.promotion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.domain.ProductTypeDTO;
import com.kpluswebup.web.service.BrandService;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.ProductPictureService;
import com.kpluswebup.web.service.ProductPropService;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.service.ProductTypeService;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.ProductPictureVO;
import com.kpluswebup.web.vo.ProductTypeItemPropVO;
import com.kpluswebup.web.vo.ProductTypeProductPropVO;
import com.kpluswebup.web.vo.ProductTypeVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/product")
public class IntegralProductController extends BaseController {

    @Autowired
    private ProductService        productService;

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

    @RequestMapping("integralProductList")
    public ModelAndView findIntegralProductList(String pageNo, String pageSize, String serchname, String serchstatus,
                                                String serchbrandID, String searchProductId) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/integralProduct_list");
        ProductDTO productDTO = new ProductDTO();
        if (StringUtil.isNumberic(pageNo)) {
            productDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNumberic(pageSize)) {
            productDTO.setPageSize(Long.parseLong(pageSize));
        }
        if (StringUtil.isNotEmpty(serchname)) {
            productDTO.setName(serchname);
        }
        if (StringUtil.isInteger(serchstatus)) {
            productDTO.setStatus(Integer.parseInt(serchstatus));
        }
        if (StringUtil.isNotEmpty(serchbrandID)) {
            productDTO.setBrandID(serchbrandID);
        }

        if (StringUtil.isNotEmpty(searchProductId)) {
            productDTO.setMainID(searchProductId);
        }
        productDTO.setCatalog(3);// 积分礼品
        productDTO.setOrderByClause("order by  modifyTime desc ");
        List<ProductVO> productList = productService.findProductList(productDTO);
        modelAndView.addObject("productList", productList);
        modelAndView.addObject("productDTO", productDTO);
        return modelAndView;
    }

    @RequestMapping("addIntegralProductPage")
    public ModelAndView addIntegralProductPage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/integralProduct_add");
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        productTypeDTO.setPageSize(10000l);
        List<ProductTypeVO> productTypList = productTypeService.findProductTypeByPagination(productTypeDTO);
        modelAndView.addObject("productTypList", productTypList);
        return modelAndView;
    }

    @RequestMapping("/addIntegralProduct")
    public ModelAndView addProduct(String name, String virtual, String catalog, String picURL, String unit,
                                   String description, String productTypeMainID, String brandID, String title,
                                   String metaKeywords, String metaDescription, String productDetail,String supplierID,String supplierCategoryID) {
        virtual = "0";
        String mainID = "";
        String productID = productService.addProduct(mainID, name, virtual, catalog, picURL, unit, description,
                                                     productTypeMainID, brandID, title, metaKeywords, metaDescription,
                                                     getCurrentOperator(), productDetail, null, null, null,null,null,null,null,null,null,null,null,null);
        return new ModelAndView("redirect:addIntegralItemPage.htm?productID=" + productID + "&productTypeMainID="
                                + productTypeMainID);
    }

    @RequestMapping("editIntegralProductPage")
    public ModelAndView editProductPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/integralProduct_edit");
        ProductVO productVO = productService.findProductByMainID(mainID);
        modelAndView.addObject("productVO", productVO);
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        productTypeDTO.setPageSize(10000l);
        List<ProductTypeVO> productTypList = productTypeService.findProductTypeByPagination(productTypeDTO);
        List<ProductTypeProductPropVO> productTypeProductPropList = productPropService.findProductTypeProductProp(productVO.getProductTypeID());
        modelAndView.addObject("productTypeProductPropList", productTypeProductPropList);
        modelAndView.addObject("productTypList", productTypList);
        modelAndView.addObject("productID", productVO.getMainID());
        modelAndView.addObject("productTypeMainID", productVO.getProductTypeID());
        return modelAndView;
    }

    @RequestMapping("/updateIntegralProduct")
    public ModelAndView updateIntegralProduct(String mainID, String name, String virtual, String catalog,
                                              String picURL, String unit, String description, String productTypeMainID,
                                              String brandID, String title, String metaKeywords,
                                              String metaDescription, String productDetail) {

        virtual = "0";
        String productID = productService.updateProduct(mainID, name, virtual, catalog, picURL, unit, description,
                                                        productTypeMainID, brandID, title, metaKeywords,
                                                        metaDescription, getCurrentOperator(), productDetail, null,
                                                        null, null,null,null,null,null,null,null,null,null,null);
        return new ModelAndView("redirect:addIntegralItemPage.htm?productID=" + productID + "&productTypeMainID="
                                + productTypeMainID);

    }

    @Deprecated
    @RequestMapping("/editProductTypeIntegralProductPropPage")
    public ModelAndView editProductTypeIntegralProductPropPage(String productID, String productTypeMainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/integralProduct_prop_edit");
        List<ProductTypeProductPropVO> productTypeProductPropList = productPropService.findProductTypeProductProp(productTypeMainID);
        modelAndView.addObject("productTypeProductPropList", productTypeProductPropList);
        modelAndView.addObject("productID", productID);
        modelAndView.addObject("productTypeMainID", productTypeMainID);
        return modelAndView;
    }

    @RequestMapping("/addIntegralItemPage")
    public ModelAndView addIntegralItemPage(String productID, String productTypeMainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/integralProduct_item_add");
        List<ProductTypeItemPropVO> productTypeItemPropList = productPropService.findProductTypeItemProp(productTypeMainID);
        modelAndView.addObject("productTypeItemPropList", productTypeItemPropList);
        List<ItemVO> itemList = itemService.findItemByProductID(productID);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("productID", productID);
        modelAndView.addObject("productTypeMainID", productTypeMainID);
        return modelAndView;
    }

    @RequestMapping("/addIntegralItem")
    public ModelAndView addIntegralItem(@RequestParam(value = "items")
    String[] items, String productID, String productTypeMainID) {
        itemService.batchAddOrEditItem(productID, productTypeMainID, items, getCurrentOperator(),null);
        return new ModelAndView("redirect:addIntegralItemPage.htm?productID=" + productID + "&productTypeMainID="
                                + productTypeMainID);
    }

    @RequestMapping("IntegralProductImgPic")
    public ModelAndView integralProductImgPic(String productID, String productTypeMainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/integralProduct_img");
        List<ItemVO> itemList = itemService.findItemByProductID(productID);
        modelAndView.addObject("itemList", itemList);
        List<ProductPictureVO> picList = productPictureService.findProductPictureByProductID(productID,null);
        modelAndView.addObject("picList", picList);
        modelAndView.addObject("productID", productID);
        modelAndView.addObject("productTypeMainID", productTypeMainID);
        return modelAndView;
    }

    @RequestMapping("addIntegralProductImgPic")
    public ModelAndView addIntegralProductImgPic(String productTypeMainID, String name, String productID,
                                                 String itemID, String picURL) {
        productPictureService.insertProductPicture(name, productID, itemID, picURL, getCurrentOperator());
        return new ModelAndView("redirect:IntegralProductImgPic.htm?productID=" + productID + "&productTypeMainID="
                                + productTypeMainID);
    }
}
