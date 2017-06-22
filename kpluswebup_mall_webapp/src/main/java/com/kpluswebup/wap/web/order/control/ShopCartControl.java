package com.kpluswebup.wap.web.order.control;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.kpluswebup.web.admin.system.service.FreightTemplatePriceService;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.CustomerDeliveryAddressDTO;
import com.kpluswebup.web.domain.PromotionDTO;
import com.kpluswebup.web.domain.ShoppingCartDTO;
import com.kpluswebup.web.member.service.CustomerAddressService;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.member.service.ShoppingCartSerivce;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.PromotionService;
import com.kpluswebup.web.service.SalesOrderLineService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.vo.CustomerDeliveryAddressVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.FreightTemplatePriceAreaVO;
import com.kpluswebup.web.vo.FreightTemplatePriceVO;
import com.kpluswebup.web.vo.ItemDetailVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.PromotionVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpluswebup.web.vo.ShoppingCartVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/weixin")
public class ShopCartControl extends BaseController {

    @Autowired
    private ShoppingCartSerivce         shoppingCartSerivce;

    @Autowired
    private ItemService                 itemService;

    @Autowired
    private CustomerAddressService      addressService;

    @Autowired
    private SalesOrderService           salesOrderService;

    @Autowired
    private SalesOrderLineService       salesOrderLineService;

    @Autowired
    private AccountDetailService        accountDetailService;

    @Autowired
    private MemberSerivce               memberSerivce;

    @Autowired
    private PromotionService            promotionService;

    @Autowired
    private FreightTemplatePriceService freightTemplatePriceService;
    @Autowired
    private CachedClient                cachedClient;

    /**
     * 进入购物车
     * 
     * @date 2015年1月9日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("usershopcart1")
    public ModelAndView shopCart1() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/shopcart1");
        CustomerVO customer = this.findWXUserInfo();
        List<ShoppingCartVO> list = shoppingCartSerivce.findShoppingCart(customer.getMainID());
        if (list != null && list.size() > 0) {
            for (ShoppingCartVO shoppingCartVO : list) {
               /* ItemVO itemVO = itemService.findSupplierItemById(shoppingCartVO.getItemID());
                if (itemVO != null) {*/
                    shoppingCartVO.setAllamount(shoppingCartVO.getItemCount() * shoppingCartVO.getSalesPrice());
                /* } */
            }
            modelAndView.addObject("list", list);
        }

        return modelAndView;
    }

    /**
     * 删除购物车
     * 
     * @date 2015年1月26日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("delCart")
    public ModelAndView delCart(String ids) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("redirect:/weixin/usershopcart1.htm");
        if (StringUtil.isNotEmpty(ids)) {
            String[] id = ids.split(",");
            for (String cartid : id) {
                shoppingCartSerivce.delShoppingCartByID(Long.valueOf(cartid));
            }
        }
        return modelAndView;
    }

    /**
     * 删除购物车
     * 
     * @date 2015年5月25日
     * @author wanghehua
     * @param ids
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("delShopCart")
    public @ResponseBody
    JsonResult delShopCart(String ids) {
        if (StringUtil.isNotEmpty(ids)) {
            String[] id = ids.split(",");
            for (String cartid : id) {
                shoppingCartSerivce.delShoppingCartByID(Long.valueOf(cartid));
            }
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * 获取促销条件
     * 
     * @date 2015年1月13日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<PromotionVO> getPromotionList() {
        PromotionDTO promotionDTO = new PromotionDTO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String nowdate = sdf.format(date);
        try {
            promotionDTO.setFromDate(DateUtil.strintToDatetimeYMD(nowdate));
            promotionDTO.setEndDate(DateUtil.strintToDatetimeYMD(nowdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<PromotionVO> pList = promotionService.findPromotionList(promotionDTO);
        return pList;
    }

    @RequestMapping("usershopcart2")
    public ModelAndView shopCart2(String ids, String itemID, String addreID, String itemCount, String paymentType,
                                  String supplierID,String generateType) {

        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/shopcart2");
        CustomerVO customer = this.findWXUserInfo();
        customer = memberSerivce.findCustomeByMianId(customer.getMainID());
        modelAndView.addObject("customer", customer);
        if (StringUtil.isNotEmpty(paymentType)) {
            modelAndView.addObject("paymentType", paymentType);
        }
        Double allTotal = 0.00;
        if (customer == null) {
            return new ModelAndView("redirect:/mall/member/toLogin.htm");
        }
        List<ShoppingCartVO> list = new ArrayList<ShoppingCartVO>();
        if (!StringUtil.isEmpty(ids)) {
            String[] id = ids.split(",");
            for (String i : id) {
                ShoppingCartVO cart = shoppingCartSerivce.findShoppingCartByID(Long.parseLong(i));
                cart.setAllamount(cart.getItemCount() * cart.getSalesPrice());
                list.add(cart);
                if (cart.getAllamount() != null) {
                    allTotal = allTotal + cart.getAllamount();
                }

            }
            modelAndView.addObject("list", list);
            modelAndView.addObject("ids", ids);
            modelAndView.addObject("allTotal", allTotal);
            modelAndView.addObject("itemCount", list.size());
            modelAndView.addObject("generateType", "shoppingcart");
        } else {
            ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
            ItemVO itemVO = itemService.findItemSupplieredByItemMainIDAndSupplierID(itemID, supplierID);
            List<ItemDetailVO> props = itemService.findItemDetailByItemID(itemVO.getMainID());
            if (props != null && props.size() > 0) {
                String itemProp = "";
                for (ItemDetailVO prop : props) {
                    itemProp += prop.getItemPropName() + ":" + prop.getItemPropValue() + " ";
                }
                shoppingCartVO.setItemProp(itemProp);
            }
            shoppingCartVO.setTempCartID(itemID);
            shoppingCartVO.setItemID(itemID);
            shoppingCartVO.setProductID(itemVO.getProductID());
            shoppingCartVO.setItemName(itemVO.getName());
            shoppingCartVO.setItemPicUrl(itemVO.getPicURL());
            shoppingCartVO.setStandrardPrice(itemVO.getStandrardPrice());
            shoppingCartVO.setAllamount(itemVO.getSalesPrice()*Integer.valueOf(itemCount));
            shoppingCartVO.setSupplierID(supplierID);
            shoppingCartVO.setType(1);
            shoppingCartVO.setSalesPrice(itemVO.getSalesPrice());
            shoppingCartVO.setItemCount(Integer.valueOf(itemCount));
            list.add(shoppingCartVO);
            allTotal=shoppingCartVO.getAllamount();
            modelAndView.addObject("allTotal", allTotal);
            modelAndView.addObject("itemID", itemID);
            modelAndView.addObject("itemCount", list.size());
            modelAndView.addObject("generateType", generateType);
            cachedClient.add(customer.getMainID(), Constant.EXP, list);
            modelAndView.addObject("list", list);
        }
        modelAndView.addObject("supplierID", supplierID);
        CustomerDeliveryAddressVO address = null;
        if (StringUtil.isEmpty(addreID)) {
            List<CustomerDeliveryAddressVO> addresses = addressService.findAddressByCustomerID(customer.getMainID(),0);
            if (addresses!=null&&addresses.size()>0) {
                address = addresses.get(0);
            }
        } else {
            address = addressService.findValidAddressByID(Long.valueOf(addreID));
        }
        modelAndView.addObject("address", address);

        /*** 促销 **/
        List<PromotionVO> pList = getPromotionList();
        Double freight = 10d;
        if (pList != null && pList.size() > 0) {
            for (PromotionVO promotionVO : pList) {
                Date endDate = promotionVO.getEndDate();
                if (endDate.getTime() < new Date().getTime()) continue;
                boolean hasPromotionProduct_0 = false;
                if (promotionVO.getType() == 3) {
                    for (ShoppingCartVO shoppingCartVO : list) {
                        boolean hasPromotionProduct_1 = promotionService.hasPromotionProduct(promotionVO.getMainID(),
                                                                                             shoppingCartVO.getItemID());
                        if (hasPromotionProduct_1) {
                            freight = 0d;
                            hasPromotionProduct_0 = true;
                            break;
                        }
                    }
                }
                if (hasPromotionProduct_0) break;
            }
        }
        Double realAllAmount = allTotal + freight;
        modelAndView.addObject("realAllAmount", realAllAmount);
        modelAndView.addObject("freight", freight);
        modelAndView.addObject("orderType", 0);
        SalesOrderVO tempOrder = new SalesOrderVO();
        tempOrder.setMainID(this.getTimeString());
        tempOrder.setExpressFee(freight);
        tempOrder.setTotalAmount(realAllAmount);
        cachedClient.add(customer.getMainID() + tempOrder.getMainID(), Constant.EXP, tempOrder);
        modelAndView.addObject("orderID", tempOrder.getMainID());
        return modelAndView;
    }

    /**
     * 查看商品列表
     * 
     * @date 2015年2月2日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("showItems")
    public ModelAndView showItems(String ids, String itemID, String itemCount) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/viewList");
        if (StringUtil.isNotEmpty(ids)) {
            List<ShoppingCartVO> list = new ArrayList<ShoppingCartVO>();
            String[] id = ids.split(",");
            for (String i : id) {
                ShoppingCartVO cart = shoppingCartSerivce.findShoppingCartByID(Long.parseLong(i));
                if (cart.getType() == 1) {
                    cart.setAllamount(cart.getItemCount() * cart.getSalesPrice());
                } else if (cart.getType() == 2) {
                    cart.setAllscore(cart.getItemCount() * cart.getScore());
                } else if (cart.getType() == 3) {
                    cart.setAllamount(cart.getItemCount() * cart.getScorePrice());
                    cart.setAllscore(cart.getItemCount() * cart.getSalesScore());
                }
                list.add(cart);
            }
            modelAndView.addObject("list", list);
        } else {
            ItemVO item = itemService.findItemById(itemID);
            modelAndView.addObject("item", item);
            modelAndView.addObject("itemCount", itemCount);
        }
        return modelAndView;
    }

    /**
     * 选择支付方式
     * 
     * @date 2015年2月2日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("selPays")
    public ModelAndView selPays(String ids, String itemID, String addreID, String type, String itemCount) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/payList");
        modelAndView.addObject("ids", ids);
        modelAndView.addObject("itemID", itemID);
        modelAndView.addObject("addreID", addreID);
        modelAndView.addObject("type", type);
        modelAndView.addObject("itemCount", itemCount);
        return modelAndView;
    }

    /**
     * 选择地址
     * 
     * @date 2015年1月13日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userselectAddr")
    public ModelAndView userselectAddr(String ids, String itemID, String type, String paymentType, String itemCount,String supplierID,String generateType) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/select_address");
        CustomerVO customer = this.findWXUserInfo();
        List<CustomerDeliveryAddressVO> addresses = addressService.findAddressByCustomerID(customer.getMainID(), 0);
        modelAndView.addObject("addresses", addresses);
        modelAndView.addObject("ids", ids);
        modelAndView.addObject("itemID", itemID);
        modelAndView.addObject("type", type);
        modelAndView.addObject("paymentType", paymentType);
        modelAndView.addObject("itemCount", itemCount);
        modelAndView.addObject("supplierID", supplierID);
        modelAndView.addObject("generateType", generateType);
        return modelAndView;
    }

    /**
     * 保存订单
     * 
     * @date 2015年1月14日
     * @author liudanqi
     * @return
     * @throws Exception
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userSaveOrder")
    public ModelAndView userSaveOrder(String cartIDs, String addressID, String paymentType, String orderAmount,
                                      String scoreAll, String memo, String itemCount, String itemID, String supplierID,
                                      String itemType, String orderType, String objID, String invoiceType,
                                      String invoiceTitle, String generateType, String tempOrderID, String allFreight, String supplierFreightStr) throws Exception {
        CustomerVO customer = this.findWXUserInfo();
        StringBuffer orderID = salesOrderService.addSalesOrder(customer.getMainID(), cartIDs, addressID, paymentType,
                                                         orderAmount, scoreAll, memo, itemCount, itemID, supplierID,
                                                         itemType, orderType, objID, invoiceType, invoiceTitle,
                                                         generateType, tempOrderID, allFreight, supplierFreightStr);
        return new ModelAndView("redirect:userOrderOK.htm?orderID=" + orderID);
    }

    @RequestMapping("userOrderOK")
    public ModelAndView userOrderOK(String orderID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/order_ok");
        SalesOrderVO order = salesOrderService.findSalesOrderByMainID(orderID);
        modelAndView.addObject("order", order);
        return modelAndView;
    }

    @RequestMapping("usercartOK")
    public ModelAndView usercartOK(String orderID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/cart_ok");
        SalesOrderVO order = salesOrderService.findSalesOrderByMainID(orderID);
        modelAndView.addObject("order", order);
        return modelAndView;
    }

    @RequestMapping("updateShoppingCart")
    public @ResponseBody
    JsonResult updateShoppingCart(String id, String number) {
        try {
            ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
            shoppingCartDTO.setItemCount(Integer.valueOf(number));
            shoppingCartDTO.setId(Long.valueOf(id));
            shoppingCartSerivce.updateShoppingCart(shoppingCartDTO);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * 获取微信支付的数据
     * 
     * @date 2015年1月19日
     * @author liudanqi
     * @param orderID
     * @return
     * @throws UnknownHostException
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("ajaxGetData")
    public @ResponseBody
    JsonResult ajaxGetData(String orderID) throws UnknownHostException {
        StringBuffer json = new StringBuffer();
        if (!StringUtil.isEmpty(orderID)) {
            SalesOrderVO order = salesOrderService.findSalesOrderByMainID(orderID);
            List<SalesOrderLineVO> list = salesOrderLineService.findSalesOrderLine(orderID);
            String appid = "";
            String partner = /* cr.getProp().getProperty("partner") */"";
            String partnerKey = /* cr.getProp().getProperty("partnerKey") */"";
            String appKey = /* cr.getProp().getProperty("appKey") */"";
            Double totalFee = order.getTotalAmount();
            String orderBody = "";
            for (SalesOrderLineVO li : list) {
                orderBody += li.getItemName() + " ";
            }
            String notifyUrl = domain + "/weixin/getPayCallBack.htm?orderId=" + order.getMainID();
            String salesorderId = order.getMainID();
            String spbill_create_ip = InetAddress.getLocalHost().getHostAddress();
            json.append("{\"appid\":\"").append(appid).append("\"");
            json.append(",\"partner\":\"").append(partner).append("\"");
            json.append(",\"partnerKey\":\"").append(partnerKey).append("\"");
            json.append(",\"appKey\":\"").append(appKey).append("\"");
            json.append(",\"totalFee\":\"").append(totalFee).append("\"");
            json.append(",\"orderBody\":\"").append(orderBody).append("\"");
            json.append(",\"notifyUrl\":\"").append(notifyUrl).append("\"");
            json.append(",\"salesorderId\":\"").append(salesorderId).append("\"");
            json.append(",\"spbill_create_ip\":\"").append(spbill_create_ip).append("\"");
            json.append("}");
        }

        return new JsonResult(json);
    }

    @RequestMapping("getPayCallBack")
    public ModelAndView getPayCallBack(String orderId) {
        ModelAndView modelAndView = this.newModelAndView();
        SalesOrderVO salesOrderVO = salesOrderService.findSalesOrderByMainID(orderId);
        if (salesOrderVO.getOrderStatus() < 2) {
            AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
            accountDetailDTO.setSerialNumber(salesOrderVO.getMainID());
            accountDetailDTO.setDetailType(2);
            accountDetailDTO.setAmount(salesOrderVO.getTotalAmount() / 100);
            accountDetailDTO.setAccountType(1);
            accountDetailDTO.setPaymentType(2);
            accountDetailDTO.setObjID(salesOrderVO.getMainID());
            accountDetailDTO.setCustomerID(salesOrderVO.getCustomerID());
            accountDetailDTO.setStatus(1);
            accountDetailService.addMemberScore(accountDetailDTO);
            modelAndView.setViewName("redirect:/weixin/usercartOK.htm?orderID=" + orderId);
        }
        return modelAndView;

    }
}
