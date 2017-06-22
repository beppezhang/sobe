package com.kpluswebup.wap.web.member.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.account.service.AccountDetailService;
import com.kpluswebup.web.admin.system.service.AreaService;
import com.kpluswebup.web.admin.system.service.ExpressService;
import com.kpluswebup.web.content.service.HelpService;
import com.kpluswebup.web.content.service.SuggestionService;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.CmsHelpCenterDTO;
import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.domain.CustomerDeliveryAddressDTO;
import com.kpluswebup.web.domain.SalesOrderAfterSalesAddressDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.domain.SalesOrderReturnApplyDTO;
import com.kpluswebup.web.domain.SalesOrderReturnDTO;
import com.kpluswebup.web.domain.SalesOrderSalesApplyDTO;
import com.kpluswebup.web.domain.SalesOrderTransDTO;
import com.kpluswebup.web.domain.SuggestionDTO;
import com.kpluswebup.web.member.service.CustomerAddressService;
import com.kpluswebup.web.member.service.FavoriteService;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.member.service.ShoppingCartSerivce;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.service.SalesOrderAfterSalesAddressService;
import com.kpluswebup.web.service.SalesOrderDeliveryAddressService;
import com.kpluswebup.web.service.SalesOrderLineService;
import com.kpluswebup.web.service.SalesOrderReturnApplyService;
import com.kpluswebup.web.service.SalesOrderSalesApplyService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.vo.AccountDetailVO;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.CmsHelpCenterVO;
import com.kpluswebup.web.vo.CustomerDeliveryAddressVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.ExpressVO;
import com.kpluswebup.web.vo.FavoriteVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.SalesOrderDeliveryAddressVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderReturnApplyVO;
import com.kpluswebup.web.vo.SalesOrderReturnVO;
import com.kpluswebup.web.vo.SalesOrderSalesApplyVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpluswebup.web.vo.ShoppingCartVO;
import com.kpluswebup.web.vo.SupplierItemIDVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.CookieUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.Md5Algorithm;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/weixin")
public class UserCenterControl extends BaseController {

    @Autowired
    private MemberSerivce                    memberService;

    @Autowired
    private FavoriteService                  favoriteService;

    @Autowired
    private CustomerAddressService           addressService;

    @Autowired
    private AccountDetailService             accountDetailService;

    @Autowired
    private HelpService                      helpService;

    @Autowired
    private SalesOrderService                salesOrderService;

    @Autowired
    private AreaService                      areaService;

    @Autowired
    private SalesOrderLineService            salesOrderLineService;

    @Autowired
    private SalesOrderDeliveryAddressService salesOrderDeliveryAddressService;

    @Autowired
    private ItemService                      itemService;
    
    @Autowired
    private ProductService                      productService;

    @Autowired
    private SalesOrderReturnApplyService     salesOrderReturnApplyService;
    
    @Autowired
    private SalesOrderSalesApplyService     salesOrderSalesApplyService;
    
    @Autowired
    private SalesOrderAfterSalesAddressService     salesOrderAfterSalesAddressService;

    @Autowired
    private ExpressService                   expressService;

    @Autowired
    private CachedClient                     cachedClient;
    @Autowired
    private SuggestionService                suggestionService;
    @Autowired
    private CustomerAddressService customerAddressService;

    @Autowired
    private ShoppingCartSerivce         shoppingCartSerivce;
    /**
     * 进入个人中心
     * 
     * @date 2015年1月5日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userCenter")
    public ModelAndView userCenter() {
        ModelAndView modelAndView = this.newModelAndView();
        CustomerVO customer = this.findWXUserInfo();
        CustomerVO cr = memberService.findCustomeByMianId(customer.getMainID());
        modelAndView.addObject("customer", cr);
        
        List<ShoppingCartVO> list = shoppingCartSerivce.findShoppingCart(customer.getMainID());
        customer.setShoppingcartCount(list.size());
        modelAndView.addObject("user", customer);
        
        modelAndView.setViewName("wap/screen/customercenter/usercenter");
        return modelAndView;
    }

    /**
     * 检查产品是否下架
     * @param productID
     * @return
     */
    @RequestMapping("checkProductByID")
    public @ResponseBody
    JsonResult checkProductByID(String productID) {
        try {
        	 ProductVO product = productService.findProductByMainID(productID);
        	 if(product != null){
        		 return JsonResult.create();
        	 }else {
        		 return JsonResult.create(ResultCode.ERROR_SYSTEM);
			 }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.create(ResultCode.ERROR_SYSTEM);
    }
    
    /**
     * 进入我的订单
     * 
     * @date 2015年1月5日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userOrder")
    public ModelAndView userOrder(String orderStatus, String paymentStatus) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_order");
        CustomerVO customer = this.findWXUserInfo();
        
        if(customer!=null){
        	List<ShoppingCartVO> list = shoppingCartSerivce.findShoppingCart(customer.getMainID());
        	customer.setShoppingcartCount(list.size());
        	modelAndView.addObject("user", customer);
        }
        
        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        if (StringUtil.isNotEmpty(orderStatus)) {
            salesOrderDTO.setOrderStatus(Integer.valueOf(orderStatus));
        }
        if (StringUtil.isNotEmpty(paymentStatus)) {
            salesOrderDTO.setPaymentStatus(Integer.valueOf(paymentStatus));
        }
        salesOrderDTO.setCustomerID(customer.getMainID());
        List<SalesOrderVO> list = salesOrderService.getSalesOrderList(salesOrderDTO);
        modelAndView.addObject("list", list);
        modelAndView.addObject("orderStatus", orderStatus);
        modelAndView.addObject("paymentStatus", paymentStatus);
        return modelAndView;
    }

    /**
     * 进入我的订单详情
     * 
     * @date 2015年1月5日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userOrderDetail")
    public ModelAndView userOrderDetail(String orderID) {
        ModelAndView modelAndView = this.newModelAndView();

        modelAndView.setViewName("wap/screen/customercenter/order_detail");
        SalesOrderVO order = salesOrderService.findSalesOrderByMainID(orderID);
        SalesOrderTransDTO salesOrderTransDTO = new SalesOrderTransDTO();
        salesOrderTransDTO.setOrderNO(order.getMainID());
        order.setSalesOrderTrans(salesOrderService.getSalesOrderTrans(salesOrderTransDTO));// 物流信息
        modelAndView.addObject("order", order);

        List<SalesOrderLineVO> list = salesOrderLineService.findSalesOrderLine(orderID);
        modelAndView.addObject("list", list);

        SalesOrderVO salesOrderVO = salesOrderService.getSalesOrderLine(orderID);
        modelAndView.addObject("salesOrderVO", salesOrderVO);

        return modelAndView;
    }

    /**
     * 取消订单
     * 
     * @date 2015年1月15日
     * @author liudanqi
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("usercancel")
    public ModelAndView usercancel(String orderID) {
        SalesOrderDTO dto = new SalesOrderDTO();
        dto.setOrderStatus(0);
        dto.setMainID(orderID);
        salesOrderService.updateSalesOrder(dto);
        return new ModelAndView("redirect:userOrder.htm");
    }

    /**
     * 删除订单
     * @date 2015年6月9日
     * @author wanghehua
     * @param mainID
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
     * 取消售后
     * @date 2015年6月9日
     * @author wanghehua
     * @param mainID
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
    
    @RequestMapping("userconfirm")
    public ModelAndView userconfirm(String orderID) {
        SalesOrderDTO dto = new SalesOrderDTO();
        dto.setOrderStatus(5);
        dto.setMainID(orderID);
        salesOrderService.updateSalesOrder(dto);
        return new ModelAndView("redirect:userOrder.htm");
    }

    /**
     * 进入我的地址
     * 
     * @date 2015年1月5日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userAddress")
    public ModelAndView userAddress() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_address");
        CustomerVO customer = this.findWXUserInfo();
        List<CustomerDeliveryAddressVO> list = addressService.findAddressByCustomerID(customer.getMainID(),1);
        if (list != null && list.size() > 0) {
            modelAndView.addObject("list", list);
        }
        modelAndView.addObject("customerID", customer.getMainID());
        return modelAndView;
    }
    
    /**
     * 
     * @date 2015年6月16日
     * @author wanghehua
     * @param addressId
     * @param customerId
     * @param type
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("updateDefaultAddress")
    public @ResponseBody
    JsonResult updateDefaultAddress(Long addressId, String customerId, String type) {
        try {
            CustomerDeliveryAddressVO customerDeliveryAddressVO = customerAddressService.findDefaultAddressByCustomerID(customerId,
                                                                                                                        Integer.valueOf(type));
            if (customerDeliveryAddressVO != null) {
                customerAddressService.editDefaultAddressByID(customerDeliveryAddressVO.getId());
            }
            customerAddressService.editIsDefaultAddressByID(addressId);
            return JsonResult.create();
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }

    /**
     * 进入我的充值
     * 
     * @date 2015年1月5日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userDeposit")
    public ModelAndView userDeposit() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_deposit");
        return modelAndView;
    }

    /**
     * 进入我的收藏
     * 
     * @date 2015年1月5日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userFavorite")
    public ModelAndView userFavorite() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_favorite");
        CustomerVO customer = this.findWXUserInfo();
        List<FavoriteVO> favorites = favoriteService.findFavoritesByCustomer(customer.getMainID());
        if (favorites != null && favorites.size() > 0) {
            modelAndView.addObject("count", favorites.size());
            modelAndView.addObject("favorites", favorites);
        }
        return modelAndView;
    }

    /**
     * 取消收藏
     * 
     * @date 2015年1月26日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userCancelFav")
    public ModelAndView userCancelFav(String id) {
        if (StringUtil.isNotEmpty(id)) {
            favoriteService.updateFavoriteByID(id);
        }
        return new ModelAndView("redirect:userFavorite.htm");
    }

    /**
     * 进入帮助中心
     * 
     * @date 2015年1月5日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userHelp")
    public ModelAndView userHelp() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_help");
        List<CmsHelpCenterVO> list = helpService.findHelps();
        modelAndView.addObject("list", list);
        return modelAndView;
    }
    
    @RequestMapping("helpsDetail")
	public ModelAndView helpsDetail(String mainID){
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("wap/screen/customercenter/user_helpdetail");
		CmsHelpCenterVO helpsdetail = helpService.findHelpByMainID(mainID);
		  if (StringUtil.isNotEmpty(helpsdetail.getContent())) {
		      helpsdetail.setContent(helpsdetail.getContent().replaceAll("<img src=\"/", "<img src=\"" + imgurl + "/"));
          }
		modelAndView.addObject("helpsdetail", helpsdetail);		
		return modelAndView;
	}

    /**
     * 进入充值记录
     * 
     * @date 2015年1月5日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userHistory")
    public ModelAndView userHistory() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_history");
        return modelAndView;
    }

    /**
     * 进入我的会员
     * 
     * @date 2015年1月5日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userMember")
    public ModelAndView userMember() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_member");
        CustomerVO customerVO = this.findWXUserInfo();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setReferee(customerVO.getMainID());
        List<CustomerVO> usermemberlist = memberService.getUserMember(customerDTO);
        modelAndView.addObject("usermemberlist", usermemberlist);
        return modelAndView;
    }

    /**
     * 进入我的积分
     * 
     * @date 2015年1月5日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userPoint")
    public ModelAndView userPoint() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_point");
        CustomerVO customer = this.findWXUserInfo();
        AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
        accountDetailDTO.setCustomerID(customer.getMainID());
        accountDetailDTO.setAccountType(2);
        accountDetailDTO.setOrderByClause("order by a.id desc");
        List<AccountDetailVO> list = accountDetailService.findMemberScoreByPagination(accountDetailDTO);
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    /**
     * 进入我的返利
     * 
     * @date 2015年1月5日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userRebate")
    public ModelAndView userRebate() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_rebate");
        CustomerVO customer = this.findWXUserInfo();
        AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
        accountDetailDTO.setCustomerID(customer.getMainID());
        accountDetailDTO.setAccountType(2);
        accountDetailDTO.setDetailType(4);
        accountDetailDTO.setOrderByClause("order by kad.id desc");
        List<AccountDetailVO> list = accountDetailService.findAccountDetailTypeByCustermor(accountDetailDTO);
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    /**
     * 进入我的预售
     * 
     * @date 2015年1月5日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userPresell")
    public ModelAndView userPresell(String pageNo, String pageSize) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_presell");
        CustomerVO customer = this.findWXUserInfo();
        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        salesOrderDTO.setCustomerID(customer.getMainID());
        List<SalesOrderVO> presellList = salesOrderService.getUserPresellList(salesOrderDTO);
        if (presellList != null && presellList.size() > 0) {
            modelAndView.addObject("presellList", presellList);
        }
        return modelAndView;
    }

    /**
     * 删除地址
     * 
     * @date 2015年1月13日
     * @author liudanqi
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userdeleteAddress")
    public ModelAndView userdeleteAddress(String id) {
        addressService.deleteAddressByPrimaryKey(Long.parseLong(id));
        return new ModelAndView("redirect:/weixin/userAddress.htm");
    }

    /**
     * 进入编辑地址
     * 
     * @date 2015年1月13日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("usereditAddress")
    public ModelAndView usereditAddress(String id, String ids, String itemID, String itemType, String paymentType,
                                        String itemCount) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/edit_address");
        if (!StringUtil.isEmpty(id)) {
            List<AreaVO> provinceList = areaService.getAllProvince();
            modelAndView.addObject("provinceList", provinceList);
            List<AreaVO> cityList = null;
            List<AreaVO> districtList = null;
            CustomerDeliveryAddressVO address = addressService.findAddressByID(Long.parseLong(id));
            modelAndView.addObject("address", address);

            if (address != null && address.getProvinceID() != null) {
                cityList = areaService.getAreaByParentID(address.getProvinceID());
            } else {
                cityList = areaService.getAreaByParentID(provinceList.get(0).getMainID());
            }
            modelAndView.addObject("cityList", cityList);

            if (address != null && address.getCityID() != null) {
                districtList = areaService.getAreaByParentID(address.getCityID());
            } else {
                districtList = areaService.getAreaByParentID(cityList.get(0).getMainID());
            }
            modelAndView.addObject("districtList", districtList);
        }
        modelAndView.addObject("ids", ids);
        modelAndView.addObject("itemID", itemID);
        modelAndView.addObject("type", itemType);
        modelAndView.addObject("paymentType", paymentType);
        modelAndView.addObject("itemCount", itemCount);
        return modelAndView;
    }

    /**
     * 进入添加新地址页面
     * 
     * @date 2015年1月13日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("useraddAddress")
    public ModelAndView useraddAddress(String type, String ids, String itemID, String itemType, String paymentType,
                                       String itemCount,String supplierID,String generateType ) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/add_address");
        modelAndView.addObject("type", type);
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
        modelAndView.addObject("ids", ids);
        modelAndView.addObject("itemID", itemID);
        modelAndView.addObject("itemType", itemType);
        modelAndView.addObject("paymentType", paymentType);
        modelAndView.addObject("itemCount", itemCount);
        modelAndView.addObject("supplierID", supplierID);
        modelAndView.addObject("generateType", generateType);
        return modelAndView;
    }

    /**
     * 保存添加地址
     * 
     * @date 2015年1月13日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("usersaveAddress")
    public ModelAndView usersaveAddress(String type, String name, String provinceID, String cityID, String disctrictID,
                                        String address, String zip, String mobile, String ids, String itemID,
                                        String itemType, String paymentType, String itemCount,String supplierID,String generateType) {
        CustomerDeliveryAddressDTO dto = new CustomerDeliveryAddressDTO();
        CustomerVO user = this.findWXUserInfo();
        dto.setCustomerID(user.getMainID());
        dto.setName(name);
        dto.setProvinceID(provinceID);
        dto.setCityID(cityID);
        dto.setDisctrictID(disctrictID);
        dto.setAddress(address);
        dto.setZip(zip);
        dto.setMobile(mobile);
        dto.setIsDefault(0);
        if (StringUtil.isEmpty(type)) {
            dto.setType(0);
        } else {
            dto.setType(Integer.parseInt(type));
        }
        addressService.addAddress(dto);
        if (!StringUtil.isEmpty(ids) || !StringUtil.isEmpty(itemID)) { // 订单页面跳过来的再跳回去
            CustomerDeliveryAddressDTO customerDeliveryAddressDTO = new CustomerDeliveryAddressDTO();
            customerDeliveryAddressDTO.setCustomerID(user.getMainID());
            List<CustomerDeliveryAddressVO> list = addressService.findDeliveryAddressByPagination(customerDeliveryAddressDTO);
            if (list != null && list.size() > 0) {
                CustomerDeliveryAddressVO adres = list.get(0);
                return new ModelAndView("redirect:/weixin/usershopcart2.htm?ids=" + ids + "&itemID=" + itemID
                                         + "&addreID=" + adres.getId() + "&type=" + itemType + "&paymentType="
                                         + paymentType + "&itemCount=" + itemCount+"&supplierID="+supplierID+"&generateType="+generateType);
            }
        }
        return new ModelAndView("redirect:userAddress.htm");
    }

    @RequestMapping("userEditAddress")
    public ModelAndView userEditAddress(String id, String name, String provinceID, String cityID, String disctrictID,
                                        String address, String zip, String mobile, String ids, String itemID,
                                        String type, String paymentType, String itemCount) {
        if (!StringUtil.isEmpty(id)) {
            CustomerDeliveryAddressDTO dto = new CustomerDeliveryAddressDTO();
            dto.setAddress(address);
            dto.setName(name);
            dto.setProvinceID(provinceID);
            dto.setCityID(cityID);
            dto.setDisctrictID(disctrictID);
            dto.setZip(zip);
            dto.setMobile(mobile);
            dto.setId(Long.parseLong(id));
            addressService.editAddress(dto);
        }
        if (StringUtil.isNotEmpty(ids) || StringUtil.isNotEmpty(itemID)) {
           return new ModelAndView("redirect:/weixin/usershopcart2.htm?ids=" + ids + "&itemID=" + itemID
                                     + "&addreID=" + id + "&type=" + type + "&paymentType=" + paymentType
                                     + "&itemCount=" + itemCount);
        }

        return new ModelAndView("redirect:userAddress.htm");
    }

    /**
     * 异步获取市
     * 
     * @date 2014年12月24日
     * @author liudanqi
     * @param provid
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("ajaxGetCitys")
    public @ResponseBody
    JsonResult ajaxGetCitys(String provid) {
        List<AreaVO> citys = null;
        if (StringUtil.isNotEmpty(provid)) {
            citys = areaService.getAreaByParentID(provid);
        }
        return new JsonResult(citys);
    }

    /**
     * 异步获取地区
     * 
     * @date 2014年12月24日
     * @author liudanqi
     * @param cityid
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("ajaxGetDistricts")
    public @ResponseBody
    JsonResult ajaxGetDistricts(String cityid) {
        List<AreaVO> district = null;
        if (StringUtil.isNotEmpty(cityid)) {
            district = areaService.getAreaByParentID(cityid);
        }
        return new JsonResult(district);
    }

    /**
     * 会员邀请
     * 
     * @date 2015年1月15日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("invitation")
    public ModelAndView userInvitation(String customerID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_invitation");
        modelAndView.addObject("customerID", customerID);
        CustomerVO customer = this.findWXUserInfo();
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    /**
     * 进入售后选择
     * 
     * @date 2015年1月18日
     * @author liudanqi
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("changeOrder")
    public ModelAndView changeOrder(String orderID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/change01");
        modelAndView.addObject("orderID", orderID);
        return modelAndView;
    }

    /**
     * 进入申请售后
     * 
     * @date 2015年1月18日
     * @author liudanqi
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("tochange")
    public ModelAndView tochange(String orderID, String type) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/change02");
        if (orderID != null) {
            SalesOrderVO salesOrder = salesOrderService.findSalesOrderByMainID(orderID);
            modelAndView.addObject("salesOrder", salesOrder);
            SalesOrderDeliveryAddressVO orderAddress = salesOrderDeliveryAddressService.findSalesOrderDeliveryAddressByID(orderID);
            modelAndView.addObject("orderAddress", orderAddress);
            List<SalesOrderLineVO> itemList = itemService.findOrderItemByOrderID(orderID);
            modelAndView.addObject("itemList", itemList);
            modelAndView.addObject("type", type);
            modelAndView.addObject("orderID", orderID);
        }
        return modelAndView;
    }

    /**
     * 保存售后申请
     * 
     * @date 2015年1月18日
     * @author liudanqi
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("saveAfterSaleApply")
    public ModelAndView saveAfterSaleApply(String salesType, String numberIMEI, String description) {
        CustomerVO customerVO = this.findWXUserInfo();
        SalesOrderSalesApplyDTO sos = new SalesOrderSalesApplyDTO();
        if (numberIMEI != null) {
            SupplierItemIDVO supplierItemIDVO = salesOrderSalesApplyService.findnumberIMEI(numberIMEI);
            if (supplierItemIDVO != null) {
                SalesOrderVO salesOrderVO = salesOrderService.findSalesOrderByMainID(supplierItemIDVO.getOrderID());
                if (!(salesOrderVO.getCustomerID()).equals(customerVO.getMainID())) {
                    return new ModelAndView("redirect:tochange.htm?error=3");
                } else {
                    SalesOrderSalesApplyVO salesOrderSalesApplyVO = salesOrderSalesApplyService.findSalesApplyByNumberIMEI(numberIMEI);
                    if (salesOrderSalesApplyVO != null) {
                        return new ModelAndView("redirect:tochange.htm?error=2");
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
                            salesOrderService.updateSalesOrderStatus(supplierItemIDVO.getOrderID(), "7",null);
                        }
                        return new ModelAndView("redirect:userService.htm");
                    }
                }
            } else {
                return new ModelAndView("redirect:tochange.htm?error=1");
            }
        }
        return null;

    }

    /**
     * 进入我的退换货
     * 
     * @date 2015年1月20日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userChange")
    public ModelAndView userChange() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_change");
        CustomerVO customerVO = this.findWXUserInfo();
        SalesOrderReturnApplyDTO salesOrderReturnApplyDTO = new SalesOrderReturnApplyDTO();
        salesOrderReturnApplyDTO.setCustomerID(customerVO.getMainID());
        salesOrderReturnApplyDTO.setOrderByClause("order by ksr.createTime desc");
        List<SalesOrderReturnApplyVO> salesOrderReturnApplyList = salesOrderReturnApplyService.findSalesOrderReturnApply(salesOrderReturnApplyDTO);
        modelAndView.addObject("salesOrderReturnApplyList", salesOrderReturnApplyList);
        return modelAndView;
    }

    /**
     * 填写物流单信息
     * 
     * @date 2015年1月20日
     * @author liudanqi
     * @param applyID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userWriteChange")
    public ModelAndView userWriteChange(String applyID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/change03");
        if (!StringUtil.isEmpty(applyID)) {
            List<ExpressVO> expressList = expressService.findALlExpress();
            modelAndView.addObject("expressList", expressList);
            modelAndView.addObject("applyID", applyID);
        }

        return modelAndView;
    }

    /**
     * 保存物流单信息
     * 
     * @date 2015年1月20日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userSaveSalesOrderReturn")
    public ModelAndView userSaveSalesOrderReturn(String expressID, String expressNumber, String orderReturnID,
                                                 String description) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/change04");
        if (!StringUtil.isEmpty(orderReturnID)) {

            SalesOrderReturnVO salesOrderReturnVO = salesOrderReturnApplyService.findSalesOrderReturnByApplyID(orderReturnID);
            SalesOrderReturnDTO salesOrderReturnDTO = new SalesOrderReturnDTO();
            salesOrderReturnDTO.setMainID(salesOrderReturnVO.getMainID());
            salesOrderReturnDTO.setStatus(3);
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
            salesOrderReturnApplyService.updateApplyStatus(orderReturnID, "3");
        }
        return modelAndView;
    }

    /**
     * 修改资料
     * 
     * @date 2015年5月25日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userInfo")
    public ModelAndView userInfo(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_info");
        CustomerVO user = this.findUserInfo();
        CustomerVO member = memberService.findCustomeByMianId(mainID);
        modelAndView.addObject("member", member);
        modelAndView.addObject("user", user);
        List<AreaVO> provinceList = areaService.getAllProvince();
        modelAndView.addObject("provinceList", provinceList);
        List<CustomerDeliveryAddressVO> Addlist = addressService.findAddressByCustomerID(mainID,0);
        if (Addlist != null) {
            for (int i = 0; i < Addlist.size(); i++) {
                CustomerDeliveryAddressVO address = Addlist.get(0);
                modelAndView.addObject("address", address);
                break;
            }
        }
        return modelAndView;
    }

    /**
     * 修改邮箱
     * 
     * @date 2015年5月25日
     * @author wanghehua
     * @param mainID
     * @param email
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("modifyUserEmail")
    public ModelAndView modifyUserEmail(String mainID, String email) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/modify_email");
        modelAndView.addObject("mainID", mainID);
        modelAndView.addObject("email", email);
        return modelAndView;
    }

    /**
     * 保存修改邮箱
     * 
     * @date 2015年5月25日
     * @author wanghehua
     * @param mainID
     * @param email
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("saveUserEmail")
    public ModelAndView saveUserEmail(String mainID, String email) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setEmail(email);
        customerDTO.setMainID(mainID);
        memberService.updateMember(customerDTO);
        return new ModelAndView("redirect:userInfo.htm?mainID=" + mainID);
    }

    /**
     * 修改名称
     * 
     * @date 2015年5月25日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("modifyUserName")
    public ModelAndView modifyUserName(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/modify_name");
        modelAndView.addObject("mainID", mainID);
        return modelAndView;
    }

    /**
     * 保存修改名称
     * 
     * @date 2015年5月25日
     * @author wanghehua
     * @param mainID
     * @param name
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("saveUserName")
    public ModelAndView saveUserName(String mainID, String name) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(name);
        customerDTO.setMainID(mainID);
        memberService.updateMember(customerDTO);
        return new ModelAndView("redirect:userInfo.htm?mainID=" + mainID);
    }

    /**
     * 修改性别
     * 
     * @date 2015年5月25日
     * @author wanghehua
     * @param mainID
     * @param sex
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("modifyUserSex")
    public ModelAndView modifyUserSex(String mainID, String sex) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/modify_sex");
        modelAndView.addObject("mainID", mainID);
        modelAndView.addObject("sex", sex);
        return modelAndView;
    }

    /**
     * 保存修改性别
     * 
     * @date 2015年5月25日
     * @author wanghehua
     * @param mainID
     * @param sex
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("saveUserSex")
    public ModelAndView saveUserSex(String mainID, String sex) {
        CustomerDTO customerDTO = new CustomerDTO();
        if (StringUtil.isNotEmpty(sex)) {
            customerDTO.setSex(Integer.parseInt(sex));
        }
        customerDTO.setMainID(mainID);
        memberService.updateMember(customerDTO);
        return new ModelAndView("redirect:userInfo.htm?mainID=" + mainID);
    }

    /**
     * 修改手机号
     * 
     * @date 2015年5月25日
     * @author wanghehua
     * @param mainID
     * @param mobile
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("modifyUserMobile")
    public ModelAndView modifyUserMobile(String mainID, String mobile) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/modify_mobile");
        modelAndView.addObject("mainID", mainID);
        modelAndView.addObject("mobile", mobile);
        return modelAndView;
    }

    /**
     * 保存修改手机号
     * 
     * @date 2015年5月25日
     * @author wanghehua
     * @param mainID
     * @param mobile
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("saveUserMobile")
    public ModelAndView saveUserMobile(String mainID, String mobile) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setMobile(mobile);
        customerDTO.setMainID(mainID);
        memberService.updateMember(customerDTO);
        return new ModelAndView("redirect:userInfo.htm?mainID=" + mainID);
    }

    /**
     * 修改密码
     * 
     * @date 2015年5月25日
     * @author wanghehua
     * @param mainId
     * @param error
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("resetPassword")
    public ModelAndView resetPassword(String mainId, String error) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_password");
        CustomerVO user = memberService.findCustomeByMianId(mainId);
        modelAndView.addObject("user", user);
        if (StringUtil.isNotEmpty(error)) {
            modelAndView.addObject("error", true);
        }
        return modelAndView;
    }

    /**
     * 保存修改密码
     * 
     * @date 2015年5月25日
     * @author wanghehua
     * @param mainID
     * @param password
     * @param newpassword
     * @param passwordagain
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("updatePassword")
    public ModelAndView updatePassword(String mainID, String password, String newpassword, String passwordagain) {
        CustomerVO user = memberService.findCustomeByMianId(mainID);
        if (user.getPassword().equals(Md5Algorithm.getInstance().md5Digest(password.getBytes()))) {
            CustomerDTO dto = new CustomerDTO();
            dto.setMainID(mainID);
            dto.setPassword(Md5Algorithm.getInstance().md5Digest(newpassword.getBytes()));
            memberService.updateMember(dto);
            // 退出重新登录
            CookieUtil cookie = new CookieUtil(getRequest(), getResponse(), 0);
            String cookieInfo = cookie.getCookieValue(Constant.USER_MALL_INFO_COOKIE);
            if (cookieInfo != null) {
                cachedClient.delete(Constant.MALLUSERINFO + cookieInfo);
                cookie.deleteCookie(Constant.USER_MALL_INFO_COOKIE);
            }
            return new ModelAndView("redirect:toLogin.htm");
        } else {
            return new ModelAndView("redirect:resetPassword.htm?mainId=" + mainID + "&error='error'");
        }
    }

    /**
     * 修改证书
     * 
     * @date 2015年5月25日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("modifyCustomerPicUrl")
    public @ResponseBody
    JsonResult modifyCustomerPicUrl(String mainID,String picUrl1, String picUrl2, String picUrl3) {
        try {

            CustomerDTO customerDTO = new CustomerDTO();
            if (StringUtil.isNotEmpty(picUrl1)) {
                customerDTO.setAvatar(picUrl1);
            }
            if (StringUtil.isNotEmpty(picUrl2)) {
                customerDTO.setPicURL2(picUrl2);
            }
            if (StringUtil.isNotEmpty(picUrl3)) {
                customerDTO.setPicURL3(picUrl3);
            }
            if (StringUtil.isNotEmpty(mainID)) {
                customerDTO.setMainID(mainID);
            }
            memberService.updateMember(customerDTO);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * 进入我的售后
     * 
     * @date 2015年5月25日
     * @author wanghehua
     * @param status
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("userService")
    public ModelAndView userService(String status) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_service");
        CustomerVO customer = this.findWXUserInfo();
        if(customer!=null){
        	List<ShoppingCartVO> list = shoppingCartSerivce.findShoppingCart(customer.getMainID());
        	customer.setShoppingcartCount(list.size());
        	modelAndView.addObject("user", customer);
        }
        modelAndView.addObject("status", status);

        SalesOrderSalesApplyDTO salesOrderSalesApplyDTO = new SalesOrderSalesApplyDTO();

        if (StringUtil.isNotEmpty(status)) {
        	salesOrderSalesApplyDTO.setStatus(Integer.valueOf(status));
        }
        salesOrderSalesApplyDTO.setCustomerID(customer.getMainID());
        List<SalesOrderSalesApplyVO> salesOrderSalesApplyList = salesOrderSalesApplyService.findSalesOrderSalesApplyByPagination(salesOrderSalesApplyDTO);
        modelAndView.addObject("salesOrderSalesApplyList", salesOrderSalesApplyList);
        return modelAndView;
    }

    /**
     * 意见反馈
     * 
     * @date 2015年3月16日
     * @author daiwei
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("opinion")
    public ModelAndView opinion(String mainID) {
        CustomerVO customer = this.findWXUserInfo();
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/customercenter/user_opinion");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    /**
     * 保存意见反馈
     * 
     * @date 2015年3月16日
     * @author daiwei
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("savePpinion")
    public ModelAndView savePpinion(String suggestion, String customerID) {

        SuggestionDTO suggestionDTO = new SuggestionDTO();

        if (StringUtil.isNotEmpty(suggestion)) {
            suggestionDTO.setSuggestion(suggestion);
        }
        if (StringUtil.isNotEmpty(customerID)) {
            suggestionDTO.setCustomerID(customerID);
        }
        suggestionService.insterSuggestion(suggestionDTO);
        return new ModelAndView("redirect:userCenter.htm");
    }

}
