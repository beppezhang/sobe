package com.kpluswebup.mall.web.member.control;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.account.service.AccountDetailService;
import com.kpluswebup.web.admin.system.service.AreaService;
import com.kpluswebup.web.admin.system.service.ExpressService;
import com.kpluswebup.web.admin.system.service.GeneralService;
import com.kpluswebup.web.admin.system.service.TransConfigService;
import com.kpluswebup.web.customer.dao.CustomerGroupDAO;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.domain.CustomerDeliveryAddressDTO;
import com.kpluswebup.web.domain.FavoriteDTO;
import com.kpluswebup.web.domain.HistroyDTO;
import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.ItemReviewDTO;
import com.kpluswebup.web.domain.MessageDTO;
import com.kpluswebup.web.domain.SalesOrderAfterSalesAddressDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.domain.SalesOrderLineDTO;
import com.kpluswebup.web.domain.SalesOrderReturnApplyDTO;
import com.kpluswebup.web.domain.SalesOrderReturnDTO;
import com.kpluswebup.web.domain.SalesOrderSalesApplyDTO;
import com.kpluswebup.web.domain.SalesOrderTransDTO;
import com.kpluswebup.web.domain.ShoppingCartDTO;
import com.kpluswebup.web.member.service.CustomerAddressService;
import com.kpluswebup.web.member.service.EmailSerivce;
import com.kpluswebup.web.member.service.FavoriteService;
import com.kpluswebup.web.member.service.HistroyService;
import com.kpluswebup.web.member.service.ItemReviewService;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.member.service.MessageSerivce;
import com.kpluswebup.web.member.service.ShoppingCartSerivce;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.SalesOrderAfterSalesAddressService;
import com.kpluswebup.web.service.SalesOrderDeliveryAddressService;
import com.kpluswebup.web.service.SalesOrderLineService;
import com.kpluswebup.web.service.SalesOrderReturnApplyService;
import com.kpluswebup.web.service.SalesOrderSalesApplyService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.vo.AccountDetailVO;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.CustomerDeliveryAddressVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.ExpressVO;
import com.kpluswebup.web.vo.FavoriteVO;
import com.kpluswebup.web.vo.HistroyVO;
import com.kpluswebup.web.vo.ItemReviewVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.MessageVO;
import com.kpluswebup.web.vo.SalesOrderDeliveryAddressVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderReturnApplyVO;
import com.kpluswebup.web.vo.SalesOrderReturnVO;
import com.kpluswebup.web.vo.SalesOrderSalesApplyVO;
import com.kpluswebup.web.vo.SalesOrderTransVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpluswebup.web.vo.ShoppingCartVO;
import com.kpluswebup.web.vo.TransConfigVO;
import com.kpluswebup.web.vo.UserOrderStstusVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.CookieUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.Md5Algorithm;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/mall/buyer")
public class CustomerControl extends BaseController {
	  @Autowired
	    private ItemReviewService				   itemReviewService;

    @Autowired
    private MemberSerivce                      memberSerivce;

    @Autowired
    private SalesOrderService                  salesOrderService;

    @Autowired
    private CustomerAddressService             customerAddressService;

    @Autowired
    private FavoriteService                    favoriteService;

    @Autowired
    private HistroyService                     histroyService;

    @Autowired
    private AccountDetailService               accountDetailService;

    @Autowired
    private AreaService                        areaService;

    @Autowired
    private MessageSerivce                     messageService;

    @Autowired
    private SalesOrderReturnApplyService       salesOrderReturnApplyService;

    @Autowired
    private SalesOrderSalesApplyService        salesOrderSalesApplyService;

    @Autowired
    private EmailSerivce                       emailSerivce;

    @Autowired
    private SalesOrderDeliveryAddressService   salesOrderDeliveryAddressService;

    @Autowired
    private SalesOrderAfterSalesAddressService salesOrderAfterSalesAddressService;

    @Autowired
    private ItemService                        itemService;

    @Autowired
    private GeneralService                     generalService;

    @Autowired
    private TransConfigService                 transConfigService;

    @Autowired
    private ExpressService                     expressService;
    @Autowired
    private CachedClient                       cachedClient;
    @Autowired
    private CustomerGroupDAO                   customerGroupDAO;
    @Autowired
    private ShoppingCartSerivce                shoppingCartSerivce;
    
    @Autowired
    private SalesOrderLineService                salesOrderLineService;

    /**
     * 会员中心
     * 
     * @date 2014年12月24日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userCenter")
    public ModelAndView userCenter(String mainId) {
        ModelAndView modelAndView = this.newModelAndView();
        CustomerVO customerVO1 = this.findUserInfo();
        modelAndView.setViewName("screen/customercenter/user_center");
        CustomerVO customerVO = memberSerivce.findCustomeByMianId(customerVO1.getMainID());
        modelAndView.addObject("customerVO", customerVO);
        UserOrderStstusVO userorderStstusvo = salesOrderService.findUserOrderStstusVO(customerVO1.getMainID());
        modelAndView.addObject("userorderStstusvo", userorderStstusvo);
        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        salesOrderDTO.setCustomerID(customerVO1.getMainID());
        salesOrderDTO.setPageSize(1L);
        List<SalesOrderVO> orderList = salesOrderService.getSalesOrderList(salesOrderDTO);
        modelAndView.addObject("orderList", orderList);
        FavoriteDTO favoriteDTO = new FavoriteDTO();
        favoriteDTO.setCustomerID(customerVO1.getMainID());
        List<FavoriteVO> list = favoriteService.findFavoriteByPagination(favoriteDTO);
        modelAndView.addObject("list", list);
        HistroyDTO histroyDTO = new HistroyDTO();
        histroyDTO.setCustomerID(customerVO1.getMainID());
        List<HistroyVO> histroyList = histroyService.findHistroyByPagination(histroyDTO);
        modelAndView.addObject("histroyList", histroyList);
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setCustomerID(customerVO1.getMainID());
        List<ShoppingCartVO> shoppingCartList = shoppingCartSerivce.findShoppingCart(customerVO1.getMainID());
        modelAndView.addObject("shoppingCartList", shoppingCartList);
        return modelAndView;
    }

    /**
     * 我的订单
     * 
     * @date 2014年12月18日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userOrderList")
    public ModelAndView userOrderList(String orderCode, String pageNo, String pageSize, String fromDate, String paymentStatus,
                                      String endDate, String searchOrderStatus) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_order_list");
        CustomerVO customerVO1 = this.findUserInfo();
        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        if (StringUtil.isNotEmpty(orderCode)) {
            salesOrderDTO.setMainID(orderCode);
        }
        if (StringUtil.isNumberic(pageNo)) {
            salesOrderDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(fromDate)) {
            salesOrderDTO.setFromDate(fromDate);
        }
        if (StringUtil.isNotEmpty(endDate)) {
            salesOrderDTO.setEndDate(endDate);
        }
        if (StringUtil.isNotEmpty(searchOrderStatus)) {
            salesOrderDTO.setOrderStatus(Integer.valueOf(searchOrderStatus));
        }
        if (StringUtil.isNotEmpty(paymentStatus)) {
            salesOrderDTO.setPaymentStatus(Integer.valueOf(paymentStatus));
        }

        salesOrderDTO.setPageSize(5L);
        salesOrderDTO.setCustomerID(customerVO1.getMainID());
        List<SalesOrderVO> orderList = salesOrderService.getSalesOrderList(salesOrderDTO);
        List<SalesOrderVO> orderListChange = new ArrayList<SalesOrderVO>();
        for (SalesOrderVO salesOrderVO : orderList) {// 把物流信息加到订单里面
            if (salesOrderVO.getOrderStatus() > 2) {
                SalesOrderTransDTO salesOrderTransDTO = new SalesOrderTransDTO();
                salesOrderTransDTO.setOrderNO(salesOrderVO.getMainID());
                List<SalesOrderTransVO> salesOrderTrans = salesOrderService.getSalesOrderTrans(salesOrderTransDTO);// 产看物流信息
                salesOrderVO.setSalesOrderTrans(salesOrderTrans);
            }
            orderListChange.add(salesOrderVO);
        }
        modelAndView.addObject("salesOrderDTO", salesOrderDTO);
        modelAndView.addObject("orderList", orderList);
        UserOrderStstusVO userorderStstusvo = salesOrderService.findUserOrderStstusVO(customerVO1.getMainID());
        CustomerVO customerVO = memberSerivce.findCustomeByMianId(customerVO1.getMainID());
        modelAndView.addObject("userorderStstusvo", userorderStstusvo);
        modelAndView.addObject("customerVO", customerVO);
        
        
        return modelAndView;
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
    
    @RequestMapping("userOrderDeleteList")
    public ModelAndView userOrderDeleteList(String orderCode, String pageNo, String pageSize, String fromDate,
                                            String endDate, String searchOrderStatus) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_order_delete");
        CustomerVO customerVO = this.findUserInfo();
        SalesOrderDTO salesOrderDeleteDTO = new SalesOrderDTO();
        if (StringUtil.isNotEmpty(orderCode)) {
            salesOrderDeleteDTO.setMainID(orderCode);
        }
        if (StringUtil.isNumberic(pageNo)) {
            salesOrderDeleteDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(fromDate)) {
            salesOrderDeleteDTO.setFromDate(fromDate);
        }
        if (StringUtil.isNotEmpty(endDate)) {
            salesOrderDeleteDTO.setEndDate(endDate);
        }
        if (StringUtil.isNotEmpty(searchOrderStatus)) {
            salesOrderDeleteDTO.setOrderStatus(Integer.valueOf(searchOrderStatus));
        }
        salesOrderDeleteDTO.setPageSize(5L);
        salesOrderDeleteDTO.setCustomerID(customerVO.getMainID());
        List<SalesOrderVO> deleteList = salesOrderService.getDeleteOrderList(salesOrderDeleteDTO);
        modelAndView.addObject("deleteList", deleteList);
        modelAndView.addObject("salesOrderDeleteDTO", salesOrderDeleteDTO);
        UserOrderStstusVO userorderStstusvo = salesOrderService.findUserOrderStstusVO(customerVO.getMainID());
        modelAndView.addObject("userorderStstusvo", userorderStstusvo);
        return modelAndView;
    }

    /**
     * 订单详情
     * 
     * @date 2014年12月25日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("orderDetail")
    public ModelAndView orderDetail(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        CustomerVO customerVO =  memberSerivce.findCustomeByMianId(this.findUserInfo().getMainID());
        modelAndView.setViewName("screen/customercenter/user_order_detail");
        SalesOrderVO salesOrderVO = salesOrderService.getSalesOrderLine(mainID);
        SalesOrderTransDTO salesOrderTransDTO = new SalesOrderTransDTO();
        salesOrderTransDTO.setOrderNO(salesOrderVO.getMainID());
        salesOrderVO.setSalesOrderTrans(salesOrderService.getSalesOrderTrans(salesOrderTransDTO));// 物流信息
        modelAndView.addObject("salesOrderVO", salesOrderVO);
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

    /**
     * 查看物流信息
     * 
     * @param mainID
     * @return
     */
    @RequestMapping("orderTrans")
    public ModelAndView orderTrans(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/order_detail_trans");
        CustomerVO customerVO = this.findUserInfo();
        SalesOrderVO salesOrderVO = salesOrderService.getSalesOrderLine(mainID);
        modelAndView.addObject("salesOrderVO", salesOrderVO);
        modelAndView.addObject("customerVO", customerVO);
        SalesOrderTransDTO salesOrderTransDTO = new SalesOrderTransDTO();
        salesOrderTransDTO.setOrderNO(mainID);
        List<SalesOrderTransVO> salesOrderTrans = salesOrderService.getSalesOrderTrans(salesOrderTransDTO);
        modelAndView.addObject("salesOrderTrans", salesOrderTrans);
        return modelAndView;
    }

    /**
     * 取消订单
     * 
     * @date 2015年1月9日
     * @author wanghehua
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("cancelOrder")
    public @ResponseBody
    JsonResult cancelOrder(String mainID) {
        try {
            salesOrderService.updateSalesOrderStatus(mainID, "0",null);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * 删除订单
     * 
     * @date 2015年1月9日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("deleteSalesOrder")
    public @ResponseBody
    JsonResult deleteSalesOrder(String mainID) {
        try {
            salesOrderService.updateSalesOrderByID(mainID);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.create(ResultCode.ERROR_SYSTEM);
    }

    /**
     *买家查看商品详情
     * 
     * @date 2015年7月19日
     * @author zhoulei
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("productDetailBuy")
    public @ResponseBody
    JsonResult productDetailBuy(String productID, String itemID) {
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
            	return new JsonResult(ResultCode.ERROR_SYSTEM_PRODUCNT);
            }
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM_PRODUCNT);
    }
    
    
    @RequestMapping("changeOrderStatus")
    public @ResponseBody
    JsonResult changeOrderStatus(String orderIDs, String orderStatus) {
        int result = 0;
        if (StringUtils.isNotEmpty(orderIDs) && StringUtils.isNotEmpty(orderStatus))
            result = salesOrderService.updateSalesOrderStatus(orderIDs, orderStatus,null);
            if (result == 0) return JsonResult.create();
            // else if (result == 1000) return new JsonResult(ResultCode.ERROR_UNPICKUP);
            // else if (result == 1001) return new JsonResult(ResultCode.ERROR_UNWRITESERIALISENO);//订单含有尚未填写序列号的商品！
            //  else if (result == 2000) return new JsonResult(ResultCode.ERROR_UNPAY);//订单尚未付款！
            else return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    /**
     * 收货地址
     * 
     * @date 2014年12月18日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userAddressList")
    public ModelAndView userAddressList() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_address_list");
       // CustomerVO customerVO = this.findUserInfo();
        CustomerVO customerVO = memberSerivce.findCustomeByMianId(this.findUserInfo().getMainID());
        modelAndView.addObject("customerVO", customerVO);
        List<CustomerDeliveryAddressVO> addressList = customerAddressService.findAddressByCustomerID(customerVO.getMainID(),0);
        modelAndView.addObject("addressList", addressList);
        // List<CustomerDeliveryAddressVO> storeList =
        // customerAddressService.findAddressByCustomerID(customerVO1.getMainID(),1);
        // modelAndView.addObject("storeList", storeList);
        List<AreaVO> provinceList = areaService.getAllProvince();
        modelAndView.addObject("provinceList", provinceList);
        if (provinceList != null && provinceList.size() > 0) {
            List<AreaVO> cityList = areaService.getAreaByParentID(provinceList.get(0).getMainID());
            modelAndView.addObject("cityList", cityList);
            if (cityList != null && cityList.size() > 0) {
                List<AreaVO> districtList = areaService.getAreaByParentID(cityList.get(0).getMainID());
                modelAndView.addObject("districtList", districtList);
            }
        }
        return modelAndView;
    }

    /**
     * 我的收藏
     * 
     * @date 2014年12月18日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userfavoriteList")
    public ModelAndView userfavoritList(String pageNo, String pageSize) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_favorite_list");
        //CustomerVO customerVO = this.findUserInfo();
        CustomerVO customerVO = memberSerivce.findCustomeByMianId(this.findUserInfo().getMainID());
        FavoriteDTO favoriteDTO = new FavoriteDTO();
        if (StringUtil.isNumberic(pageNo)) {
            favoriteDTO.setPageNo(Long.parseLong(pageNo));
        }
        favoriteDTO.setPageSize(4l);
        favoriteDTO.setCustomerID(customerVO.getMainID());
        List<FavoriteVO> list = favoriteService.findFavoriteByPagination(favoriteDTO);
        HistroyDTO histroyDTO = new HistroyDTO();
        histroyDTO.setCustomerID(customerVO.getMainID());
        List<HistroyVO> histroyList = histroyService.findHistroyByPagination(histroyDTO);
        modelAndView.addObject("histroyList", histroyList);
        modelAndView.addObject("list", list);
        modelAndView.addObject("favoriteDTO", favoriteDTO);
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

    /**
     * 我的足迹
     * 
     * @date 2014年12月18日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userHistroyList")
    public ModelAndView userHistroyList(String pageNo, String pageSize) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_histroy_list");
       // CustomerVO customerVO = this.findUserInfo();
        CustomerVO customerVO = memberSerivce.findCustomeByMianId(this.findUserInfo().getMainID());
        HistroyDTO histroyDTO = new HistroyDTO();
        if (StringUtil.isNumberic(pageNo)) {
            histroyDTO.setPageNo(Long.parseLong(pageNo));
        }
        histroyDTO.setPageSize(8l);
        histroyDTO.setCustomerID(customerVO.getMainID());
        List<HistroyVO> histroyList = histroyService.findHistroyByPagination(histroyDTO);
        modelAndView.addObject("histroyList", histroyList);
        modelAndView.addObject("histroyDTO", histroyDTO);
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

    /**
     * 取消收藏
     * 
     * @date 2015年1月9日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("cancelFavorite")
    public @ResponseBody
    JsonResult cancelFavorite(String favoriteIds) {
        try {
            favoriteService.updateFavoriteByID(favoriteIds);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.create(ResultCode.ERROR_SYSTEM);
    }

    /**
     * 取消足迹
     * 
     * @date 2015年1月9日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("cancelHistory")
    public @ResponseBody
    JsonResult cancelHistory(String histroyIds) {
        try {
            histroyService.updateHistroyByID(histroyIds);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.create(ResultCode.ERROR_SYSTEM);
    }

    /***
     * 我的预售
     * 
     * @date 2014年12月19日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userPresell")
    public ModelAndView userPresell(String pageNo, String pageSize) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_presell");
        CustomerVO customerVO = this.findUserInfo();
        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        if (StringUtil.isNotEmpty(pageNo)) {
            salesOrderDTO.setPageNo(Long.parseLong(pageNo));
        }
        /*
         * if (StringUtil.isNotEmpty(pageSize)) { }
         */
        salesOrderDTO.setPageSize(10l);
        salesOrderDTO.setCustomerID(customerVO.getMainID());
        List<SalesOrderVO> presellList = salesOrderService.getUserPresellList(salesOrderDTO);
        modelAndView.addObject("presellList", presellList);
        modelAndView.addObject("salesOrderDTO", salesOrderDTO);
        return modelAndView;
    }

    /***
     * 上线积分返利 账号充值
     * 
     * @date 2014年12月19日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("onlineRebate")
    public ModelAndView onlineRebate() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/online_rebate");
        // GeneralVO generalVO = generalService.findGeneral();
        TransConfigVO transConfigVO = transConfigService.findTransConfig();
        modelAndView.addObject("transConfigVO", transConfigVO);
        return modelAndView;
    }

    /***
     * 线下充值积分
     * 
     * @date 2014年12月19日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("offlineRecharge")
    public ModelAndView offlineRecharge() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/offline_recharge");
        return modelAndView;
    }

    /***
     * 我的积分记录
     * 
     * @date 2014年12月19日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userPoints")
    public ModelAndView userPoints(String pageNo, String pageSize) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_points");
        CustomerVO customerVO = this.findUserInfo();
        AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
        if (StringUtil.isNumberic(pageNo)) {
            accountDetailDTO.setPageNo(Long.valueOf(pageNo));
        }
        accountDetailDTO.setPageSize(15l);
        accountDetailDTO.setCustomerID(customerVO.getMainID());
        accountDetailDTO.setAccountType(2);
        accountDetailDTO.setOrderByClause("order by a.id desc");
        List<AccountDetailVO> list = accountDetailService.findMemberScoreByPagination(accountDetailDTO);
        modelAndView.addObject("list", list);
        modelAndView.addObject("accountDetailDTO", accountDetailDTO);
        return modelAndView;
    }

    /***
     * 我的会员
     * 
     * @date 2014年12月19日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userMenber")
    public ModelAndView userMenber(String pageNo, String pageSize, String username) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_menber");
        CustomerVO customerVO = this.findUserInfo();
        CustomerDTO customerDTO = new CustomerDTO();
        if (StringUtil.isNotEmpty(pageNo)) {
            customerDTO.setPageNo(Long.parseLong(pageNo));
        }
        /*
         * if (StringUtil.isNotEmpty(pageSize)) { }
         */
        customerDTO.setPageSize(15l);
        if (StringUtil.isNotEmpty(username)) {
            customerDTO.setUsername(username);
        }
        // customerDTO.setMainID(customerVO.getMainID());
        customerDTO.setReferee(customerVO.getMainID());
        List<CustomerVO> usermemberlist = memberSerivce.getUserMember(customerDTO);
        modelAndView.addObject("usermemberlist", usermemberlist);
        modelAndView.addObject("customerDTO", customerDTO);
        return modelAndView;
    }

    /**
     * 会员邀请
     * 
     * @date 2014年12月24日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userInvitation")
    public ModelAndView userInvitation() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/invitation");
        CustomerVO customerVO = this.findUserInfo();
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

    @RequestMapping("ajaxSendEmail")
    public @ResponseBody
    JsonResult ajaxSendEmail(String email, String content) {
        try {
            CustomerVO customerVO = this.findUserInfo();
            String[] customerIDs = { customerVO.getMainID() };
            // emailSerivce.emailSend(customerIDs, null, "会员邀请", content, customerVO.getMainID());
            emailSerivce.sendEmail(customerVO.getMainID(), email, "会员邀请", content);
            return new JsonResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }

    /***
     * 我的返利
     * 
     * @date 2014年12月19日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userRebate")
    public ModelAndView userRebate(String pageNo, String pageSize) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_rebate");
        CustomerVO customerVO = this.findUserInfo();
        AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
        if (StringUtil.isNumberic(pageNo)) {
            accountDetailDTO.setPageNo(Long.valueOf(pageNo));
        }
        accountDetailDTO.setPageSize(15l);
        accountDetailDTO.setCustomerID(customerVO.getMainID());
        accountDetailDTO.setAccountType(2);
        accountDetailDTO.setDetailType(4);
        accountDetailDTO.setOrderByClause("order by kad.id desc");
        List<AccountDetailVO> list = accountDetailService.findAccountDetailTypeByCustermor(accountDetailDTO);
        modelAndView.addObject("list", list);
        modelAndView.addObject("accountDetailDTO", accountDetailDTO);

        return modelAndView;
    }

    /***
     * 积分转出
     * 
     * @date 2014年12月19日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("rollOut")
    public ModelAndView rollOut() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/roll_out");
        return modelAndView;
    }

    /***
     * 积分购买
     * 
     * @date 2014年12月19日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("integralBuy")
    public ModelAndView integralBuy(String pageNo, String pageSize) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/integral_buy");
        CustomerVO customerVO = this.findUserInfo();
        AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
        if (StringUtil.isNumberic(pageNo)) {
            accountDetailDTO.setPageNo(Long.valueOf(pageNo));
        }
        accountDetailDTO.setPageSize(5l);
        accountDetailDTO.setCustomerID(customerVO.getMainID());
        List<AccountDetailVO> list = accountDetailService.findAccountDetailByBuy(accountDetailDTO);
        modelAndView.addObject("list", list);
        modelAndView.addObject("accountDetailDTO", accountDetailDTO);
        return modelAndView;
    }

    /***
     * 我的资料
     * 
     * @date 2014年12月19日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userInfo")
    public ModelAndView userInfo() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_info");
        CustomerVO user = this.findUserInfo();
        CustomerVO customerVO = memberSerivce.findCustomeByMianId(user.getMainID());
        modelAndView.addObject("customerVO", customerVO);
        List<AreaVO> provinceList = areaService.getAllProvince();
        modelAndView.addObject("provinceList", provinceList);
        List<AreaVO> cityList = null;
        List<AreaVO> districtList = null;
        if (StringUtil.isNotEmpty(customerVO.getProvinceID())) {
            cityList = areaService.getAreaByParentID(customerVO.getProvinceID());
        } else {
            if (provinceList != null && provinceList.size() > 0) {
                cityList = areaService.getAreaByParentID(provinceList.get(0).getMainID());
            }
        }
        modelAndView.addObject("cityList", cityList);
        if (StringUtil.isNotEmpty(customerVO.getCityID())) {
            districtList = areaService.getAreaByParentID(customerVO.getCityID());
        } else {
            if (cityList != null && cityList.size() > 0) {
                districtList = areaService.getAreaByParentID(cityList.get(0).getMainID());
            }
        }
        modelAndView.addObject("districtList", districtList);
        return modelAndView;
    }

    /***
     * 站内信
     * 
     * @date 2014年12月19日
     * @author liulihui
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userMessage")
    public ModelAndView userMessage(String pageNo, String pageSize) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_message");
        CustomerVO user = this.findUserInfo();
        MessageDTO dto = new MessageDTO();
        dto.setSendee(user.getMainID());
        if (StringUtil.isNumberic(pageNo)) {
            dto.setPageNo(Long.valueOf(pageNo));
        }
        dto.setPageSize(10l);
        List<MessageVO> list = messageService.findMessageByPagination(dto);
        modelAndView.addObject("list", list);
        modelAndView.addObject("dto", dto);
        return modelAndView;
    }

    /**
     * 修改站内信状态
     * 
     * @date 2015年1月20日
     * @author yuanyuan
     * @param messageId
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("updateMessageStatus")
    public ModelAndView updateMessageStatus(String messageId) {
        if (messageId != null) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMainID(messageId);
            messageDTO.setIsReaded(1);
            messageService.updateMessageStatus(messageDTO);
        }
        return new ModelAndView("redirect:userMessage.htm");
    }

    /**
     * 账户安全
     * 
     * @date 2014年12月24日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userSecurity")
    public ModelAndView userSecurity() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_security");
        CustomerVO user = this.findUserInfo();
        CustomerVO customerVO = memberSerivce.findCustomeByMianId(user.getMainID());
        modelAndView.addObject("customerVO", customerVO);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    /**
     * 修改密碼
     * 
     * @date 2014年12月24日
     * @author liudanqi
     * @param customID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("resetPassword")
    public ModelAndView resetPassword(String customID, String error) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/reset_password");
        CustomerVO user = memberSerivce.findCustomeByMianId(customID);
        modelAndView.addObject("user", user);
        if (StringUtil.isNotEmpty(error)) {
            modelAndView.addObject("error", true);
        }
        return modelAndView;
    }

    /**
     * 保存修改密碼
     * 
     * @date 2014年12月24日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("updatePassword")
    public ModelAndView updatePassword(String mainID, String password, String newpassword, String passwordagain) {
        ModelAndView modelAndView = this.newModelAndView();
        CustomerVO user = memberSerivce.findCustomeByMianId(mainID);
        if (user.getPassword().equals(Md5Algorithm.getInstance().md5Digest(password.getBytes()))) {
            CustomerDTO dto = new CustomerDTO();
            dto.setMainID(mainID);
            dto.setPassword(Md5Algorithm.getInstance().md5Digest(newpassword.getBytes()));
            memberSerivce.updateMember(dto);
            // 退出重新登录
            CookieUtil cookie = new CookieUtil(getRequest(), getResponse(), 0);
            String cookieInfo = cookie.getCookieValue(Constant.USER_MALL_INFO_COOKIE);
            if (cookieInfo != null) {
                cachedClient.delete(Constant.MALLUSERINFO + cookieInfo);
                cookie.deleteCookie(Constant.USER_MALL_INFO_COOKIE);
            }
            return new ModelAndView("redirect:/mall/member/toLogin.htm");
        } else {
            return new ModelAndView("redirect:resetPassword.htm?customID=" + mainID + "&error='error'");
        }
    }

    /**
     * 保存会员信息
     * 
     * @date 2014年12月23日
     * @author liudanqi
     * @param customerDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("saveUserInfor")
    public ModelAndView saveUserInfor(String mainID, String name, String birthday, String sex, String provinceID,
                                      String cityID, String districtID, String picURL2, String picURL3,String  address) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(name);
        if (StringUtil.isNotEmpty(sex)) {
            customerDTO.setSex(Integer.parseInt(sex));
        }
        if (StringUtil.isNotEmpty(birthday)) {
            customerDTO.setBirthday(Date.valueOf(birthday));
        }
        if(StringUtil.isNotEmpty(address)){
        	customerDTO.setAddress(address);
        }        
        customerDTO.setProvinceID(provinceID);
        customerDTO.setCityID(cityID);
        customerDTO.setDistrictID(districtID);
        customerDTO.setMainID(mainID);
        customerDTO.setPicURL2(picURL2);
        customerDTO.setPicURL3(picURL3);
        memberSerivce.updateMember(customerDTO);
        return new ModelAndView("redirect:userInfo.htm");
    }

    /**
     * 保存发票信息
     * 
     * @date 2014年12月23日
     * @author liudanqi
     * @param customerDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("saveInvoiceInfo")
    public ModelAndView saveInvoiceInfo(String mainID, String companyName, String address, String mobile,
                                        String openingBank, String accountNumber, String bankAccount, String picURL2) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCompanyName(companyName);
        customerDTO.setAddress(address);
        customerDTO.setMobile(mobile);
        customerDTO.setOpeningBank(openingBank);
        customerDTO.setAccountNumber(accountNumber);
        customerDTO.setBankAccount(bankAccount);
        customerDTO.setPicURL2(picURL2);
        customerDTO.setMainID(mainID);
        memberSerivce.updateMember(customerDTO);
        return new ModelAndView("redirect:invoiceInfo.htm");
    }

    /**
     * 删除地址
     * 
     * @date 2014年12月23日
     * @author liudanqi
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("deleteAddress")
    public ModelAndView deleteAddress(String id) {
        customerAddressService.deleteAddressByPrimaryKey(Long.parseLong(id));
        return new ModelAndView("redirect:userAddressList.htm");
    }

    /**
     * 编辑保存地址
     * 
     * @date 2014年12月24日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("editUserAddress")
    public ModelAndView editUserAddress(String addressID, String name, String provinceId, String cityId,
                                        String districtId, String address, String zip, String mobile, String telephone,
                                        String isDefault, String type) {
        CustomerVO customerVO = this.findUserInfo();
        CustomerDeliveryAddressDTO customerDeliveryAddressDTO = new CustomerDeliveryAddressDTO();
        if (StringUtil.isNotEmpty(name)) {
            customerDeliveryAddressDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(mobile)) {
            customerDeliveryAddressDTO.setMobile(mobile);
        }
        if (StringUtil.isNotEmpty(telephone)) {
            customerDeliveryAddressDTO.setTelephone(telephone);
        }
        if (StringUtil.isNotEmpty(provinceId)) {
            customerDeliveryAddressDTO.setProvinceID(provinceId);
        }
        if (StringUtil.isNotEmpty(cityId)) {
            customerDeliveryAddressDTO.setCityID(cityId);
        }
        if (StringUtil.isNotEmpty(districtId)) {
            customerDeliveryAddressDTO.setDisctrictID(districtId);
        }
        if (StringUtil.isNotEmpty(address)) {
            customerDeliveryAddressDTO.setAddress(address);
        }
        if (StringUtil.isNotEmpty(zip)) {
            customerDeliveryAddressDTO.setZip(zip);
        }
        if (StringUtil.isNotEmpty(type)) {
            customerDeliveryAddressDTO.setType(Integer.valueOf(type));
        }
        if (StringUtil.isNotEmpty(isDefault)) {
            customerDeliveryAddressDTO.setIsDefault(Integer.valueOf(isDefault));
            if (isDefault.equals("0")) {
                CustomerDeliveryAddressVO customerDeliveryAddressVO = customerAddressService.findDefaultAddressByCustomerID(customerVO.getMainID(),
                                                                                                                            Integer.valueOf(type));
                if (customerDeliveryAddressVO != null) {
                    customerAddressService.editDefaultAddressByID(customerDeliveryAddressVO.getId());
                }
            }
        } else {
            customerDeliveryAddressDTO.setIsDefault(1);
        }
        customerDeliveryAddressDTO.setCustomerID(customerVO.getMainID());
        if (StringUtil.isNotEmpty(addressID)) {
            customerDeliveryAddressDTO.setId(Long.valueOf(addressID));
            customerAddressService.editAddress(customerDeliveryAddressDTO);
        } else {
            customerAddressService.addAddress(customerDeliveryAddressDTO);
        }
        customerAddressService.editAddress(customerDeliveryAddressDTO);
        return new ModelAndView("redirect:userAddressList.htm");
    }

    /**
     * 退货申请
     * 
     * @date 2014年12月30日
     * @author liulihui
     * @param mainId
     * @param status
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("salesOperation")
    public @ResponseBody
    JsonResult salesOperation(String mainId, String status) {
        try {
            if (StringUtils.isBlank(mainId) || StringUtils.isBlank(status)) return new JsonResult(
                                                                                                  ResultCode.ERROR_SYSTEM_PARAM_FORMAT);
            salesOrderReturnApplyService.salesOperation(mainId, status);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }

    /**
     * 编辑图像
     * 
     * @date 2015年1月4日
     * @author yuanyuan
     * @param mainID
     * @param picURL
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("saveAvatar")
    public @ResponseBody
    void saveAvatar(String mainID, String picURL) {
        try {
            if (mainID != null && picURL != null) {
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setMainID(mainID);
                customerDTO.setAvatar(picURL);
                memberSerivce.updateMember(customerDTO);
            }
        } catch (Exception e) {
        }
    }

    /**
     * 退换货申请选择page
     * 
     * @date 2015年1月9日
     * @author yuanyuan
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("salesOrderReturnApplyCheck")
    public ModelAndView salesOrderReturnApplyCheck(String orderID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/exchange");
        if (orderID != null) {
            SalesOrderVO salesOrder = salesOrderService.findSalesOrderByMainID(orderID);
            modelAndView.addObject("salesOrder", salesOrder);
            SalesOrderDeliveryAddressVO orderAddress = salesOrderDeliveryAddressService.findSalesOrderDeliveryAddressByID(orderID);
            modelAndView.addObject("orderAddress", orderAddress);
            List<SalesOrderLineVO> itemList = itemService.findOrderItemByOrderID(orderID);
            modelAndView.addObject("itemList", itemList);
            if (salesOrder.getExpressID() != null) {
                ExpressVO expressVO = expressService.findExpressByMainID(salesOrder.getExpressID());
                modelAndView.addObject("expressVO", expressVO);
            }
        }
        return modelAndView;
    }

    /**
     * 售后申请
     * 
     * @date 2015年1月9日
     * @author yuanyuan
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userApplys")
    public ModelAndView userApplys(String orderLineID, String error) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_applys");
        CustomerVO customerVO1 = this.findUserInfo();
        CustomerVO customerVO = memberSerivce.findCustomeByMianId(customerVO1.getMainID());
        modelAndView.addObject("customerVO", customerVO);
        modelAndView.addObject("orderLineID", orderLineID);
        if (StringUtil.isNotEmpty(error)) {
            modelAndView.addObject("error", error);
        }
        return modelAndView;
    }

    /**
     * 保存售后申请
     * 
     * @date 2015年1月9日
     * @author yuanyuan
     * @param orderID
     * @param itemID
     * @param memo
     * @param description
     * @param number
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("saveAfterSaleApply")
    public ModelAndView saveAfterSaleApply(String salesType, String numberIMEI, String description) {
        CustomerVO customerVO = this.findUserInfo();
        SalesOrderSalesApplyDTO sos = new SalesOrderSalesApplyDTO();
        if (numberIMEI != null) {
            SalesOrderLineVO salesOrderLineVO = salesOrderSalesApplyService.findSalesOrderLineByOrderID(numberIMEI);
            if(salesOrderLineVO != null){
                SalesOrderVO salesOrderVO = salesOrderService.findSalesOrderByMainID(salesOrderLineVO.getOrderID());
                SalesOrderSalesApplyVO salesOrderSalesApplyVO = salesOrderSalesApplyService.findSalesApplyByNumberIMEI(numberIMEI);
                if (salesOrderSalesApplyVO != null) {
                    return new ModelAndView("redirect:userApplys.htm?error=2");
                } else {
                    sos.setSalesOrderID(salesOrderVO.getMainID());
                    sos.setNumberIMEI(numberIMEI);//salesorderLineID

                    sos.setProductID(salesOrderLineVO.getProductID());
                    sos.setItemID(salesOrderLineVO.getItemID());
                    sos.setCount(salesOrderLineVO.getItemCount());
                    sos.setSupplierID(salesOrderLineVO.getSupplierID());
                    
                    if (description != null) {
                        sos.setDescription(description);
                    }
                    if (salesType != null) {
                        sos.setSalesType(Integer.parseInt(salesType));
                    }
                    sos.setStatus(0);// 待确认
                    sos.setIsDelete(0);
                    sos.setCustomerID(customerVO.getMainID());
                    salesOrderSalesApplyService.saveSelective(sos);//售后申请

                    SalesOrderDeliveryAddressVO salesOrderDeliveryAddressVO = salesOrderDeliveryAddressService.findSalesOrderDeliveryAddressByID(salesOrderVO.getMainID());
                    if (salesOrderDeliveryAddressVO != null) {
                        SalesOrderAfterSalesAddressDTO salesOrderAfterSalesAddressDTO = new SalesOrderAfterSalesAddressDTO();
                        salesOrderAfterSalesAddressDTO.setAfterSalesID(sos.getMainID());
                        salesOrderAfterSalesAddressDTO.setName(salesOrderDeliveryAddressVO.getName());
                        salesOrderAfterSalesAddressDTO.setCountryID(salesOrderDeliveryAddressVO.getCountryID());
                        salesOrderAfterSalesAddressDTO.setProvinceID(salesOrderDeliveryAddressVO.getProvinceID());
                        salesOrderAfterSalesAddressDTO.setCityID(salesOrderDeliveryAddressVO.getCityID());
                        salesOrderAfterSalesAddressDTO.setDisctrictID(salesOrderDeliveryAddressVO.getDisctrictID());
                        salesOrderAfterSalesAddressDTO.setAddress(salesOrderDeliveryAddressVO.getAddress());
                        salesOrderAfterSalesAddressDTO.setTelephone(salesOrderDeliveryAddressVO.getTelephone());
                        salesOrderAfterSalesAddressDTO.setMobile(salesOrderDeliveryAddressVO.getMobile());
                        salesOrderAfterSalesAddressService.addSalesOrderAfterSalesAddressDTO(salesOrderAfterSalesAddressDTO);
                    }
                      
                    if (salesOrderLineVO.getOrderID() != null) {
                        salesOrderService.updateSalesOrderStatus(salesOrderLineVO.getOrderID(), "7",null);
                        SalesOrderLineDTO salesOrderLineDTO = new SalesOrderLineDTO();
                        salesOrderLineDTO.setStatus(2);
                        salesOrderLineDTO.setId(salesOrderLineVO.getId());
                        salesOrderLineService.updateSalesOrderLineByID(salesOrderLineDTO);
                    }
                    return new ModelAndView("redirect:afterSalesList.htm");
                }
            }else{
                return new ModelAndView("redirect:userApplys.htm?error=1");
            }
            
            
            
            //SupplierItemIDVO supplierItemIDVO = salesOrderSalesApplyService.findnumberIMEI(numberIMEI);
            /*if (supplierItemIDVO != null) {
                SalesOrderVO salesOrderVO = salesOrderService.findSalesOrderByMainID(supplierItemIDVO.getOrderID());
                if (!(salesOrderVO.getCustomerID()).equals(customerVO.getMainID())) {
                    return new ModelAndView("redirect:userApplys.htm?error=3");
                } else {
                    SalesOrderSalesApplyVO salesOrderSalesApplyVO = salesOrderSalesApplyService.findSalesApplyByNumberIMEI(numberIMEI);
                    if (salesOrderSalesApplyVO != null) {
                        return new ModelAndView("redirect:userApplys.htm?error=2");
                    } else {
                        sos.setSalesOrderID(supplierItemIDVO.getOrderID());
                        sos.setNumberIMEI(numberIMEI);

                        SalesOrderLineVO salesOrderLineVO = salesOrderSalesApplyService.findSalesOrderLineByOrderID(supplierItemIDVO.getOrderLineId());
                        if (salesOrderLineVO != null) {
                            sos.setProductID(salesOrderLineVO.getProductID());
                            sos.setItemID(salesOrderLineVO.getItemID());
                            sos.setCount(salesOrderLineVO.getItemCount());
                            sos.setSupplierID(salesOrderLineVO.getSupplierID());
                        }
                        if (description != null) {
                            sos.setDescription(description);
                        }
                        if (salesType != null) {
                            sos.setSalesType(Integer.parseInt(salesType));
                        }
                        sos.setStatus(0);// 待确认
                        sos.setIsDelete(0);
                        sos.setCustomerID(customerVO.getMainID());
                        salesOrderSalesApplyService.saveSelective(sos);

                        SalesOrderDeliveryAddressVO salesOrderDeliveryAddressVO = salesOrderDeliveryAddressService.findSalesOrderDeliveryAddressByID(supplierItemIDVO.getOrderID());
                        if (salesOrderDeliveryAddressVO != null) {
                            SalesOrderAfterSalesAddressDTO salesOrderAfterSalesAddressDTO = new SalesOrderAfterSalesAddressDTO();
                            salesOrderAfterSalesAddressDTO.setAfterSalesID(sos.getMainID());
                            salesOrderAfterSalesAddressDTO.setName(salesOrderDeliveryAddressVO.getName());
                            salesOrderAfterSalesAddressDTO.setCountryID(salesOrderDeliveryAddressVO.getCountryID());
                            salesOrderAfterSalesAddressDTO.setProvinceID(salesOrderDeliveryAddressVO.getProvinceID());
                            salesOrderAfterSalesAddressDTO.setCityID(salesOrderDeliveryAddressVO.getCityID());
                            salesOrderAfterSalesAddressDTO.setDisctrictID(salesOrderDeliveryAddressVO.getDisctrictID());
                            salesOrderAfterSalesAddressDTO.setAddress(salesOrderDeliveryAddressVO.getAddress());
                            salesOrderAfterSalesAddressDTO.setTelephone(salesOrderDeliveryAddressVO.getTelephone());
                            salesOrderAfterSalesAddressDTO.setMobile(salesOrderDeliveryAddressVO.getMobile());
                            salesOrderAfterSalesAddressService.addSalesOrderAfterSalesAddressDTO(salesOrderAfterSalesAddressDTO);
                        }

                        if (supplierItemIDVO.getOrderID() != null) {
                            salesOrderService.updateSalesOrderStatus(supplierItemIDVO.getOrderID(), "7");
                        }
                        return new ModelAndView("redirect:afterSalesList.htm");
                    }
                }
            } else {
                return new ModelAndView("redirect:userApplys.htm?error=1");
            }*/
        }
        return null;

    }

    /**
     * 取消售后
     * 
     * @date 2015年1月9日
     * @author wanghehua
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("cancelAfterSale")
    public @ResponseBody
    JsonResult cancelAfterSale(String mainID) {
        try {
            salesOrderSalesApplyService.updateSalesOrderSalesApplyByMainID(mainID);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
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
     * 保存退换货申请
     * 
     * @date 2015年1月9日
     * @author yuanyuan
     * @param orderID
     * @param itemID
     * @param memo
     * @param description
     * @param number
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("saveSaleOrderReturnApply")
    public ModelAndView saveSaleOrderReturnApply(String orderID, String itemID, String memo, String description,
                                                 String number, String returnType, String type,
                                                 Double transactionPrice, Integer salesScore) {
        // ModelAndView modelAndView = this.newModelAndView();
        // modelAndView.setViewName("screen/customercenter/exchange1");
        // System.out.println(orderID);
        SalesOrderReturnApplyDTO sor = new SalesOrderReturnApplyDTO();
        if (orderID != null) {
            sor.setSalesOrderID(orderID);
            SalesOrderVO salesOrderVO = salesOrderService.findSalesOrderByMainID(orderID);
            sor.setCustomerID(salesOrderVO.getCustomerID());
        }
        if (itemID != null) {
            ItemVO itemvo = itemService.findItemByMainID(itemID);
            sor.setItemID(itemvo.getMainID());
            sor.setProductID(itemvo.getProductID());
        }
        if (memo != null) {
            sor.setMemo(memo);
        }
        if (description != null) {
            sor.setDescription(description);
        }
        if (number != null) {
            sor.setCount(Integer.parseInt(number));
        }
        if (returnType != null) {
            sor.setReturnType(Integer.parseInt(returnType));
        }
        if (transactionPrice != null) {
            sor.setReturnAmount(transactionPrice);
        }
        /*
         * if (transactionPrice != null) { sor.setReturnAmount(transactionPrice); } if (salesScore != null) {
         * sor.setReturnScore(salesScore); }
         */
        sor.setCreateTime(new java.util.Date());
        sor.setStatus(0);// 待确认
        salesOrderReturnApplyService.insertSelective(sor);
        if (orderID != null) {
            salesOrderService.updateSalesOrderStatus(orderID, "7",null);
        }
        return new ModelAndView("redirect:userExchange.htm");
    }

    /**
     * 查找该会员下的订单的某一商品是否已申请退换货
     * 
     * @date 2015年1月12日
     * @author yuanyuan
     * @param itemID
     * @param type
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("ajaxCheckReturnOrder")
    public @ResponseBody
    JsonResult ajaxCheckReturnOrder(String itemID, String orderID) {
        try {
            Boolean status = true;
            SalesOrderReturnApplyDTO salesOrderReturnApplyDTO = new SalesOrderReturnApplyDTO();
            if (itemID != null) {
                salesOrderReturnApplyDTO.setItemID(itemID);
            }
            if (orderID != null) {
                SalesOrderVO salesOrder = salesOrderService.findSalesOrderByMainID(orderID);
                salesOrderReturnApplyDTO.setSalesOrderID(orderID);
                salesOrderReturnApplyDTO.setCustomerID(salesOrder.getCustomerID());
            }
            List<SalesOrderReturnApplyVO> list = salesOrderReturnApplyService.findSalesOrderReturnApply(salesOrderReturnApplyDTO);
            if (list.size() > 0) {
                status = false;
            }
            return new JsonResult(status);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }

    /**
     * 我的退换货申请列表
     * 
     * @date 2015年1月13日
     * @author yuanyuan
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userExchange")
    public ModelAndView userExchange(String pageNo, String pageSize) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_exchange");
        CustomerVO customerVO = this.findUserInfo();
        SalesOrderReturnApplyDTO salesOrderReturnApplyDTO = new SalesOrderReturnApplyDTO();
        if (StringUtil.isNotEmpty(pageNo)) {
            salesOrderReturnApplyDTO.setPageNo(Long.parseLong(pageNo));
        }
        salesOrderReturnApplyDTO.setPageSize(10l);
        salesOrderReturnApplyDTO.setCustomerID(customerVO.getMainID());
        salesOrderReturnApplyDTO.setOrderByClause("order by ksr.createTime desc");
        List<SalesOrderReturnApplyVO> salesOrderReturnApplyList = salesOrderReturnApplyService.findSalesOrderReturnApply(salesOrderReturnApplyDTO);
        modelAndView.addObject("salesOrderReturnApplyList", salesOrderReturnApplyList);
        modelAndView.addObject("salesOrderReturnApplyDTO", salesOrderReturnApplyDTO);
        return modelAndView;
    }

    /**
     * 售后申请列表
     * 
     * @date 2015年1月13日
     * @author yuanyuan
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("afterSalesList")
    public ModelAndView afterSalesList(String orderCode, String pageNo, String pageSize, String fromDate, String endDate) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_afterSale");
        CustomerVO customerVO1 = this.findUserInfo();
        CustomerVO customerVO = memberSerivce.findCustomeByMianId(customerVO1.getMainID());
        modelAndView.addObject("customerVO", customerVO);
        SalesOrderSalesApplyDTO salesOrderSalesApplyDTO = new SalesOrderSalesApplyDTO();
        if (StringUtil.isNotEmpty(orderCode)) {
            salesOrderSalesApplyDTO.setMainID(orderCode);
        }
        if (StringUtil.isNotEmpty(pageNo)) {
            salesOrderSalesApplyDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(fromDate)) {
            salesOrderSalesApplyDTO.setFromDate(fromDate);
        }
        if (StringUtil.isNotEmpty(endDate)) {
            salesOrderSalesApplyDTO.setEndDate(endDate);
        }
        salesOrderSalesApplyDTO.setPageSize(5l);

        salesOrderSalesApplyDTO.setCustomerID(customerVO1.getMainID());
        salesOrderSalesApplyDTO.setOrderByClause("order by ksr.createTime desc");
        List<SalesOrderSalesApplyVO> salesOrderSalesApplyList = salesOrderSalesApplyService.findSalesOrderSalesApplyByPagination(salesOrderSalesApplyDTO);
        modelAndView.addObject("salesOrderSalesApplyList", salesOrderSalesApplyList);
        modelAndView.addObject("salesOrderSalesApplyDTO", salesOrderSalesApplyDTO);
        return modelAndView;
    }

    /**
     * 后台申请通过进行填写物流信息
     * 
     * @date 2015年1月13日
     * @author yuanyuan
     * @param orderApplyID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("addLogistics")
    public ModelAndView addLogistics(String orderApplyID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/exchange1");
        if (orderApplyID != null) {
            SalesOrderReturnApplyVO salesOrderReturnApplyVO = salesOrderReturnApplyService.findSalesOrderReturnApplyByMainID(orderApplyID);
            modelAndView.addObject("salesOrderReturnApplyVO", salesOrderReturnApplyVO);
            SalesOrderVO salesOrderVO = salesOrderService.findSalesOrderByMainID(salesOrderReturnApplyVO.getSalesOrderID());
            modelAndView.addObject("salesOrder", salesOrderVO);
            SalesOrderDeliveryAddressVO orderAddress = salesOrderDeliveryAddressService.findSalesOrderDeliveryAddressByID(salesOrderVO.getMainID());
            modelAndView.addObject("orderAddress", orderAddress);
            List<ExpressVO> expressList = expressService.findALlExpress();
            modelAndView.addObject("expressList", expressList);
        }
        return modelAndView;
    }

    /**
     * 申请通过填写 保存物流信息
     * 
     * @date 2015年1月14日
     * @author yuanyuan
     * @param expressID
     * @param expressNumber
     * @param orderReturnID
     * @param description
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("upSalesOrderReturn")
    public ModelAndView upSalesOrderReturn(String expressID, String expressNumber, String retrunApplyID,
                                           String description) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/exchange2");
        if (retrunApplyID != null) {
            SalesOrderReturnApplyVO salesOrderReturnApplyVO = salesOrderReturnApplyService.findSalesOrderReturnApplyByMainID(retrunApplyID);
            modelAndView.addObject("salesOrderReturnApplyVO", salesOrderReturnApplyVO);
            SalesOrderVO salesOrderVO = salesOrderService.findSalesOrderByMainID(salesOrderReturnApplyVO.getSalesOrderID());
            modelAndView.addObject("salesOrder", salesOrderVO);
            SalesOrderDeliveryAddressVO orderAddress = salesOrderDeliveryAddressService.findSalesOrderDeliveryAddressByID(salesOrderVO.getMainID());
            modelAndView.addObject("orderAddress", orderAddress);
            List<ExpressVO> expressList = expressService.findALlExpress();
            modelAndView.addObject("expressList", expressList);

            SalesOrderReturnVO salesOrderReturnVO = salesOrderReturnApplyService.findSalesOrderReturnByApplyID(retrunApplyID);
            SalesOrderReturnDTO salesOrderReturnDTO = new SalesOrderReturnDTO();
            salesOrderReturnDTO.setMainID(salesOrderReturnVO.getMainID());
            salesOrderReturnDTO.setStatus(3);// 已退货
            /*
             * if (salesOrderReturnVO.getReturnType() == 1) { } else if (salesOrderReturnVO.getReturnType() == 2) {
             * salesOrderReturnDTO.setStatus(4); }
             */
            if (expressID != null) {
                salesOrderReturnDTO.setExpressID(expressID);
            }
            if (expressNumber != null) {
                salesOrderReturnDTO.setExpressNumber(expressNumber);
            }
            if (description != null) {
                salesOrderReturnDTO.setDescription(description);
            }
            salesOrderReturnApplyService.updateReturnOrder(salesOrderReturnDTO);
            salesOrderReturnApplyService.updateApplyStatus(retrunApplyID, "3");
            /*
             * if(salesOrderReturnVO.getReturnType()==1){ }else if(salesOrderReturnVO.getReturnType()==2){
             * salesOrderReturnApplyService.updateApplyStatus(retrunApplyID, "4"); }
             */

        }
        return modelAndView;
    }

    /**
     * 账户充值 银联or支付宝支付
     * 
     * @date 2015年1月14日
     * @author yuanyuan
     * @param amount
     * @param paymentType
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("rechargeTenPay")
    public ModelAndView rechargeTenPay(Double amount, String paymentType) {
        if (StringUtil.isNotEmpty(paymentType)) {
            if (Integer.parseInt(paymentType) == 1) {
                return new ModelAndView("redirect:/mall/tenalipay/tenaliPay.htm?amount=" + amount);
            } else if (Integer.parseInt(paymentType) == 2) {
                return new ModelAndView("redirect:/mall/ten/rechargeNetPay.htm?amount=" + amount);
            }
        }
        return null;
    }

    /**
     * 交易信息
     * 
     * @date 2014年12月24日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userTrade")
    public ModelAndView userTrade(String mainID, String pageNo, String pageSize, String paymentStatus,
                                  String orderStatus) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/user_trade");
        CustomerVO user = this.findUserInfo();
        CustomerVO customerVO = memberSerivce.findCustomeByMianId(user.getMainID());
        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        salesOrderDTO.setOrderStatus(6);
        salesOrderDTO.setPaymentStatus(1);
        salesOrderDTO.setPageSize(10L);
        salesOrderDTO.setCustomerID(customerVO.getMainID());
        List<SalesOrderVO> orderList = salesOrderService.findUserTradeList(salesOrderDTO);
        modelAndView.addObject("salesOrderDTO", salesOrderDTO);
        modelAndView.addObject("customerVO", customerVO);
        modelAndView.addObject("orderList", orderList);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    /**
     * 发票信息
     * 
     * @date 2014年12月24日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("invoiceInfo")
    public ModelAndView invoiceInfo() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/customercenter/invoice_info");
        CustomerVO user = this.findUserInfo();
        CustomerVO customerVO = memberSerivce.findCustomeByMianId(user.getMainID());
        modelAndView.addObject("customerVO", customerVO);
        modelAndView.addObject("user", user);
        return modelAndView;
    }


/**
 * 跳转评价页面
 * @param orderId
 * @return
 */
@RequestMapping("toreview")
public ModelAndView toReview(String orderId) {
	ModelAndView modelAndView = this.newModelAndView();
    CustomerVO customerVO = this.findUserInfo();
    SalesOrderVO salesOrderVO = salesOrderService.findSalesOrderByMainID(orderId);
    if(null != salesOrderVO){
        List<SalesOrderLineVO> listVO = salesOrderLineService.findSalesOrderLine(salesOrderVO.getMainID());
    	for (SalesOrderLineVO vo : listVO) {
			ItemReviewDTO itemReviewDTO = new ItemReviewDTO();
			itemReviewDTO.setProductID(vo.getProductID());
            itemReviewDTO.setStatus(2);//
            itemReviewDTO.setItemID(vo.getItemID());
            itemReviewDTO.setSalesOrderID(orderId);
            itemReviewDTO.setCustomerID(customerVO.getMainID());
           
            List<ItemReviewVO> list = itemReviewService.findReviews(itemReviewDTO);
            /*
             * 判断是否有过评价记录
             * */
            vo.setIsSubmit(0);
            if(null != list && list.size() > 0){
            	vo.setIsSubmit(1);
            	vo.setContent(list.get(0).getContent());
            	vo.setScore_(list.get(0).getScore());
            }
		}
        modelAndView.addObject("itemlist", listVO);
    }
    modelAndView.setViewName("screen/customercenter/user_itemreview");
    return modelAndView;
}

}
