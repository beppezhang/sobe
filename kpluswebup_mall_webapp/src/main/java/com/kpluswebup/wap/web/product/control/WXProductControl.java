package com.kpluswebup.wap.web.product.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.account.service.AccountDetailService;
import com.kpluswebup.web.content.service.AdvertService;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.CmsAdvertDTO;
import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.domain.FavoriteDTO;
import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.ItemDetailDTO;
import com.kpluswebup.web.domain.ItemScoreDetailDTO;
import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.domain.ShoppingCartDTO;
import com.kpluswebup.web.member.service.FavoriteService;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.member.service.ShoppingCartSerivce;
import com.kpluswebup.web.service.ItemScoreService;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.ProductCategoryService;
import com.kpluswebup.web.service.ProductPictureService;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.vo.CmsAdvertVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.FavoriteVO;
import com.kpluswebup.web.vo.ItemDetailVO;
import com.kpluswebup.web.vo.ItemScoreDetailVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.ProductPictureVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.ShoppingCartVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/weixin")
public class WXProductControl extends BaseController {

    @Autowired
    private CachedClient          cachedClient;

    @Autowired
    ProductService                productService;

    @Autowired
    ProductCategoryService        productCategoryService;

    @Autowired
    private ItemService           itemService;

    @Autowired
    private FavoriteService       favoriteService;

    @Autowired
    private ProductPictureService productPictureService;

    @Autowired
    private ShoppingCartSerivce   shoppingCartSerivce;

    @Autowired
    private AdvertService         advertService;
    @Autowired
    private ItemScoreService      itemScoreService;
    @Autowired
    private MemberSerivce         memberSerivce;
    @Autowired
    private AccountDetailService  accountDetailService;

    /**
     * 产品搜索页
     * 
     * @date 2015年1月7日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("productSearch")
    public ModelAndView productList(String pageNo, String categoryID, String name, String order, String style,
                                    String productType, String cType) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/product");
        
        CustomerVO customer = this.findWXUserInfo();
        if(customer!=null){
        	List<ShoppingCartVO> list = shoppingCartSerivce.findShoppingCart(customer.getMainID());
        	customer.setShoppingcartCount(list.size());
        	modelAndView.addObject("user", customer);
        }

        ItemDTO itemDto = new ItemDTO();
        if (StringUtil.isNotEmpty(categoryID)) {
            ProductCategoryVO productCategoryVO = productCategoryService.findProductCategoryByMainID(categoryID);
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
            modelAndView.addObject("categoryID", categoryID);
            modelAndView.addObject("cType", cType);
        }
        if (StringUtil.isNumberic(pageNo)) {
            itemDto.setPageNo(Long.parseLong(pageNo));
        } else {
            itemDto.setPageNo(1l);
        }
        if (StringUtil.isNotEmpty(name)) {
            itemDto.setName(name);
            modelAndView.addObject("name", name);
        }
        String orderby = "";
        if (!(StringUtil.isEmpty(order)) && !(StringUtil.isEmpty(style))) {
            orderby = "order by " + order + " " + style;
        } else {
            orderby = "order by c.sortOrder desc,c.name,c.brandID,c.productID";
        }
        itemDto.setOrderByClause(orderby);
        modelAndView.addObject("order", order);
        modelAndView.addObject("style", style);
        if (StringUtil.isNotEmpty(productType)) {
            itemDto.setProductType(Integer.valueOf(productType));
        }
        itemDto.setPageSize(6L);
        modelAndView.addObject("itemDto", itemDto);
        List<ItemVO> its = itemService.finItemsByItemDto(itemDto);
        modelAndView.addObject("itemList", its);
        List<ProductCategoryVO> list = productCategoryService.findProductOneLevel();
        modelAndView.addObject("list", list);
        modelAndView.addObject("productType", productType);
        modelAndView.addObject("pageNo", itemDto.getPageNo() + 1);
        modelAndView.addObject("pageCount", itemDto.getPageCount());
        return modelAndView;
    }

    @RequestMapping("ajaxgetMoreSearchItem")
    public ModelAndView ajaxgetMoreSearchItem(String pageNo, String categoryID, String name, String order,
                                              String style, String productType, String cType) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/more_product");

        ItemDTO itemDto = new ItemDTO();
        if (StringUtil.isNotEmpty(categoryID)) {
            ProductCategoryVO productCategoryVO = productCategoryService.findProductCategoryByMainID(categoryID);
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
            modelAndView.addObject("categoryID", categoryID);
            modelAndView.addObject("cType", cType);
        }
        if (StringUtil.isNumberic(pageNo)) {
            itemDto.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(name)) {
            itemDto.setName(name);
            modelAndView.addObject("name", name);
        }
        String orderby = "";
        if (!(StringUtil.isEmpty(order)) && !(StringUtil.isEmpty(style))) {
            orderby = "order by " + order + " " + style;
        } else {
            orderby = "order by c.sortOrder desc,c.name,c.brandID,c.productID";
        }
        itemDto.setOrderByClause(orderby);
        modelAndView.addObject("order", order);
        modelAndView.addObject("style", style);
        if (StringUtil.isNotEmpty(productType)) {
            itemDto.setProductType(Integer.valueOf(productType));
        }
        itemDto.setPageSize(6L);
        modelAndView.addObject("itemDto", itemDto);
        List<ItemVO> its = itemService.finItemsByItemDto(itemDto);
        modelAndView.addObject("itemList", its);
        List<ProductCategoryVO> list = productCategoryService.findProductOneLevel();
        modelAndView.addObject("list", list);
        modelAndView.addObject("productType", productType);
        modelAndView.addObject("pageNo", itemDto.getPageNo() + 1);
        return modelAndView;
    }

    /**
     * 产品列表
     * 
     * @date 2015年1月8日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("productList")
    public ModelAndView products(String categoryID, String threeID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/product_list");

        CmsAdvertDTO dto1 = new CmsAdvertDTO();
        dto1.setPosionID("weixin_product_list");
        dto1.setSerachDate(new Date());
        List<CmsAdvertVO> AdvertList1 = advertService.findAdvertByPagination(dto1);
        modelAndView.addObject("ad1", AdvertList1);

        if (!(StringUtil.isEmpty(categoryID))) {

            modelAndView.addObject("categoryID", categoryID);

            List<ProductCategoryVO> list = productCategoryService.findProductCatByParentID(categoryID);
            modelAndView.addObject("list", list);

            ProductDTO dto = new ProductDTO();
            if (!(StringUtil.isEmpty(threeID))) {
                dto.setCatID(threeID);
            } else {
                dto.setCatID(categoryID);
            }
            List<ProductVO> products = productService.searchProducItemCat(dto);
            modelAndView.addObject("products", products);
            modelAndView.addObject("threeID", threeID);
        }

        return modelAndView;
    }

    /**
     * 产品详情
     * 
     * @date 2015年1月8日
     * @author liudanqi
     * @param productID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("productDetail")
    public ModelAndView productDetail(String productID, String value1, String value2,String itemID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/product_detail");

        CustomerVO customer = this.findWXUserInfo();
        ItemDetailDTO itemDetailDTO = new ItemDetailDTO();
        ProductVO product = productService.findProductByMainID(productID);
        if (product != null) {
            if (StringUtil.isNotEmpty(product.getDescription())) {
                product.setDescription(product.getDescription().replaceAll("<img src=\"/", "<img src=\"" + imgurl + "/"));
            }
            if (StringUtil.isNotEmpty(product.getProductProp())) {
                product.setProductProp(product.getProductProp().replaceAll("<img src=\"/", "<img src=\"" + imgurl + "/"));
            }
            if (StringUtil.isNotEmpty(product.getSaleService())) {
                product.setSaleService(product.getSaleService().replaceAll("<img src=\"/", "<img src=\"" + imgurl + "/"));
            }
            modelAndView.addObject("product", product);

            ItemVO itemproduct = itemService.findItemByValues(itemDetailDTO);
            modelAndView.addObject("itemproduct", itemproduct);

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

            if (value1 == null && value2 == null) {
                List<ItemVO> its = itemService.finditemsByProductID(productID); // 默认取得产品下的第一个商品
                if (its != null && its.size() > 0) {
                    ItemVO item = its.get(0);
                    modelAndView.addObject("item", item);
                    List<ItemDetailVO> itemProps = itemService.findItemProps(item.getMainID());
                    modelAndView.addObject("itemProps", itemProps);
                    if (customer != null) {
                        boolean isfavorite = favoriteService.isFavorite(customer.getMainID(), productID);
                        modelAndView.addObject("favorite", isfavorite);
                        List<ShoppingCartVO> sList = shoppingCartSerivce.findShoppingCart(customer.getMainID());
                        modelAndView.addObject("shopCount", sList.size());
                    }
                    List<ProductPictureVO> pictures = productPictureService.findProductPictureByProductID(item.getMainID(),
                                                                                                          productID);
                    modelAndView.addObject("pictures", pictures);
                }
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
                ItemVO item = itemService.findItemByValues(dto);
                if (item != null && item.getMainID() != null) {
                    modelAndView.addObject("item", item);
                    List<ItemDetailVO> itemProps = itemService.findItemProps(item.getMainID());
                    modelAndView.addObject("itemProps", itemProps);
                    if (customer != null) {
                        boolean isfavorite = favoriteService.isFavorite(customer.getMainID(), productID);
                        modelAndView.addObject("favorite", isfavorite);
                        List<ShoppingCartVO> sList = shoppingCartSerivce.findShoppingCart(customer.getMainID());
                        modelAndView.addObject("shopCount", sList.size());
                    }
                    List<ProductPictureVO> pictures = productPictureService.findProductPictureByProductID(item.getMainID(),
                                                                                                          productID);
                    modelAndView.addObject("pictures", pictures);
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
        }

        return modelAndView;
    }

    @RequestMapping("ajaxFavorite")
    public @ResponseBody
    JsonResult ajaxFavorite(String itemID, String productID, String customerID) {
        try {
            FavoriteDTO dto = new FavoriteDTO();
            dto.setProductID(productID);
            dto.setCustomerID(customerID);
            FavoriteVO favoriteVO = favoriteService.findFavoriteByCustomerItem(dto);
            if (favoriteVO != null) {
                return new JsonResult(ResultCode.ERROR_FAVORITE);
            } else {
                dto.setItemID(itemID);
                favoriteService.insertFavorite(dto);
                return new JsonResult(ResultCode.NORMAL);
            }
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }

    }

    @RequestMapping("ajaxAddCat")
    public @ResponseBody
    JsonResult ajaxAddCat(String itemCount, String itemID, String productID, String supplierID) {
        try {
            CustomerVO customer = this.findWXUserInfo();
            ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
            shoppingCartDTO.setProductID(productID);
            shoppingCartDTO.setItemID(itemID);
            shoppingCartDTO.setCustomerID(customer.getMainID());
            shoppingCartDTO.setType(1);
            shoppingCartDTO.setIsPromotion(0);
            shoppingCartDTO.setSupplierID(supplierID);
            ShoppingCartVO shoppingCartVO = shoppingCartSerivce.findShoppingCartByCustomerItem(shoppingCartDTO);
            if (shoppingCartVO != null) {
                shoppingCartDTO.setItemCount(shoppingCartVO.getItemCount() + Integer.parseInt(itemCount));
                shoppingCartDTO.setId(shoppingCartVO.getId());
                shoppingCartSerivce.updateShoppingCart(shoppingCartDTO);
            } else {
                shoppingCartDTO.setItemCount(Integer.parseInt(itemCount));
                shoppingCartSerivce.addShoppingCart(shoppingCartDTO);
            }
            List<ShoppingCartVO> cartList = shoppingCartSerivce.findShoppingCart(customer.getMainID());
            ShoppingCartVO cart = new ShoppingCartVO();
            cart.setItemCount(cartList.size());
            cachedClient.set(Constant.SHOPPINGCARTCOUNT + customer.getMainID(), Constant.EXP, cartList.size());
            return new JsonResult(cart);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }

    /**
     * 扫码获取积分
     * 
     * @date 2015年1月22日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("getPoints")
    public ModelAndView getPoints(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/get_points");
        ItemScoreDetailDTO itemScoreDetailDTO = new ItemScoreDetailDTO();
        itemScoreDetailDTO.setMainID(mainID);
        ItemScoreDetailVO itemScoreDetailVO = itemScoreService.findItemScoreDetailByMainID(itemScoreDetailDTO);
        modelAndView.addObject("itemScoreDetailVO", itemScoreDetailVO);
        return modelAndView;
    }

    @RequestMapping("getItemScore")
    public @ResponseBody
    JsonResult getItemScore(String mainID) {
        CustomerVO customerVO = this.findWXUserInfo();
        if (customerVO == null) {
            cachedClient.set(Constant.BEFORE_WEIXIN_LOGIN_URL, Constant.EXP, "/weixin/getPoints.htm?mainID=" + mainID);
            return new JsonResult(ResultCode.ERROR_LOGIN);
        }
        ItemScoreDetailDTO itemScoreDetailDTO = new ItemScoreDetailDTO();
        itemScoreDetailDTO.setMainID(mainID);
        itemScoreDetailDTO.setIsQrcode(0);
        ItemScoreDetailVO itemScoreDetailVO = itemScoreService.findItemScoreDetailByMainID(itemScoreDetailDTO);
        if (itemScoreDetailVO != null) {
            itemScoreDetailDTO.setIsQrcode(1);
            itemScoreDetailDTO.setMainID(mainID);
            itemScoreDetailDTO.setCustomerID(customerVO.getMainID());
            itemScoreService.updateItemScoreDetail(itemScoreDetailDTO);
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setMainID(customerVO.getMainID());
            customerDTO.setScore(customerVO.getScore() + itemScoreDetailVO.getScore());
            customerDTO.setScoreIntotal(customerVO.getScoreIntotal() + itemScoreDetailVO.getScore());
            memberSerivce.updateMember(customerDTO);
            AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
            accountDetailDTO.setSerialNumber(customerVO.getMainID());
            accountDetailDTO.setDetailType(7);
            accountDetailDTO.setAccountType(2);
            accountDetailDTO.setPaymentType(3);
            accountDetailDTO.setAmount(Double.valueOf(itemScoreDetailVO.getScore()));
            accountDetailDTO.setDescription("扫码送积分");
            accountDetailDTO.setObjID(customerVO.getMainID());
            accountDetailDTO.setCustomerID(customerVO.getMainID());
            accountDetailDTO.setStatus(1);
            accountDetailDTO.setMainID(UUIDUtil.getUUID());
            accountDetailService.insertMemberScore(accountDetailDTO);
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("productBuy")
    public ModelAndView productBuy(String pageNo, String name, String order, String style) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/product_buy");
        CustomerVO customer = this.findWXUserInfo();
        if(customer!=null){
        	List<ShoppingCartVO> list = shoppingCartSerivce.findShoppingCart(customer.getMainID());
        	customer.setShoppingcartCount(list.size());
        	modelAndView.addObject("user", customer);
        }
        ItemDTO itemDto = new ItemDTO();
        if (StringUtil.isNumberic(pageNo)) {
            itemDto.setPageNo(Long.parseLong(pageNo));
        } else {
            itemDto.setPageNo(1l);
        }
        if (StringUtil.isNotEmpty(name)) {
            itemDto.setName(name);
            modelAndView.addObject("name", name);
        }
        itemDto.setPageSize(6l);
        String orderby = "";
        if (!(StringUtil.isEmpty(order)) && !(StringUtil.isEmpty(style))) {
            orderby = "order by " + order + " " + style;
        } else {
            orderby = "order by c.sortOrder desc,c.name,c.brandID,c.productID";
        }
        itemDto.setOrderByClause(orderby);
        modelAndView.addObject("order", order);
        modelAndView.addObject("style", style);
        List<ItemVO> itemList = itemService.finItemsByItemDto(itemDto);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("pageNo", itemDto.getPageNo() + 1);
        modelAndView.addObject("pageCount", itemDto.getPageCount());
        return modelAndView;
    }

    @RequestMapping("ajaxgetMoreItem")
    public ModelAndView ajaxgetMoreItem(String pageNo, String name, String order, String style) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/more_item");
        ItemDTO itemDto = new ItemDTO();
        if (StringUtil.isNumberic(pageNo)) {
            itemDto.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(name)) {
            itemDto.setName(name);
            modelAndView.addObject("name", name);
        }
        itemDto.setPageSize(6l);
        String orderby = "";
        if (!(StringUtil.isEmpty(order)) && !(StringUtil.isEmpty(style))) {
            orderby = "order by " + order + " " + style;
        } else {
            orderby = "order by c.sortOrder desc,c.name,c.brandID,c.productID";
        }
        itemDto.setOrderByClause(orderby);
        modelAndView.addObject("order", order);
        modelAndView.addObject("style", style);
        List<ItemVO> itemList = itemService.finItemsByItemDto(itemDto);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("pageNo", itemDto.getPageNo() + 1);
        return modelAndView;
    }

}
