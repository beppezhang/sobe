package com.kpluswebup.web.controller.order;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tempuri.IService1Proxy;

import com.kpluswebup.web.account.service.AccountDetailService;
import com.kpluswebup.web.admin.system.service.AreaService;
import com.kpluswebup.web.admin.system.service.ExpressService;
import com.kpluswebup.web.admin.system.service.SystemCodeService;
import com.kpluswebup.web.commom.util.OneBarcodeUtil;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.domain.SalesOrderDeliveryAddressDTO;
import com.kpluswebup.web.domain.SalesOrderLineDTO;
import com.kpluswebup.web.domain.SalesOrderReturnApplyDTO;
import com.kpluswebup.web.domain.SalesOrderSalesApplyDTO;
import com.kpluswebup.web.domain.SalesOrderShippingAddressDTO;
import com.kpluswebup.web.domain.SalesOrderTransDTO;
import com.kpluswebup.web.domain.SettlementDTO;
import com.kpluswebup.web.domain.SupplierItemDTO;
import com.kpluswebup.web.domain.SupplierItemIDDTO;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.SalesOrderDeliveryAddressService;
import com.kpluswebup.web.service.SalesOrderLineService;
import com.kpluswebup.web.service.SalesOrderReturnApplyService;
import com.kpluswebup.web.service.SalesOrderSalesApplyService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.service.SalesOrderShippingAddressService;
import com.kpluswebup.web.service.SettlementService;
import com.kpluswebup.web.service.SupplierItemService;
import com.kpluswebup.web.vo.AccountDetailVO;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.ExpressVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.SalesOrderDeliveryAddressVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderReturnApplyVO;
import com.kpluswebup.web.vo.SalesOrderSalesApplyVO;
import com.kpluswebup.web.vo.SalesOrderShippingAddressVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpluswebup.web.vo.StatisticsInfoVO;
import com.kpluswebup.web.vo.SupplierItemIDVO;
import com.kpluswebup.web.vo.SupplierItemVO;
import com.kpluswebup.web.vo.SystemCodeVO;
import com.kpuswebup.common.exception.DuplicateSerialiseNoException;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Component
@Controller
@RequestMapping("/admin/order")
public class SalesOrderController extends BaseController {

    @Autowired
    private SalesOrderService                salesOrderService;
    @Autowired
    private SalesOrderLineService            salesOrderLineService;
    @Autowired
    private SalesOrderDeliveryAddressService salesOrderDeliveryAddressService;
    @Autowired
    private SalesOrderShippingAddressService salesOrderShippingAddressService;
    @Autowired
    private AreaService                      areaService;
    @Autowired
    private SalesOrderReturnApplyService     salesOrderReturnApplyService;
    @Autowired
    private SalesOrderSalesApplyService      salesOrderSalesApplyService;
    @Autowired
    private ItemService                      itemService;
    @Autowired
    private ExpressService                   expressService;
    @Autowired
    private AccountDetailService             accountDetailService;
    @Autowired
    private SettlementService                settlementService;
    @Autowired
    private SystemCodeService                systemCodeService;
    @Autowired
    private SupplierItemService              supplierItemService;

    /**
     * 订单列表
     * 
     * @date 2014年10月29日
     * @author lupeng
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("orderList.htm")
    public ModelAndView orderList(SalesOrderDTO salesOrderDTO, String searchOrderID, String searchItemID,
                                  String searchItemName, String searchUsername, String searchPaymentType,
                                  String searchPaymentStatus, String searchOrderStatus, String orderTime,
                                  String provinceID, String cityID, String districtID, String searchStartModifyTime,
                                  String searchEndModifyTime,String searchSupplierName) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/order/order_list");
        if (StringUtils.isNotEmpty(searchOrderID)) salesOrderDTO.setMainID(searchOrderID);
        if (StringUtils.isNotEmpty(searchItemID)) salesOrderDTO.setItemID(searchItemID);
        if (StringUtils.isNotEmpty(searchItemName)) salesOrderDTO.setItemName(searchItemName);
        if (StringUtil.isNotEmpty(searchUsername)) salesOrderDTO.setCustomerUserName(searchUsername);
        if (StringUtil.isNotEmpty(searchPaymentType)) salesOrderDTO.setPaymentType(Integer.valueOf(searchPaymentType));
        if (StringUtil.isNotEmpty(searchPaymentStatus)) salesOrderDTO.setPaymentStatus(Integer.valueOf(searchPaymentStatus));
        if (StringUtil.isNotEmpty(searchOrderStatus)) salesOrderDTO.setOrderStatus(Integer.valueOf(searchOrderStatus));
        if (StringUtil.isNotEmpty(orderTime)) {
            if (orderTime.equals("1")) {
                salesOrderDTO.setOrderByClause(" order by s.createTime asc");
            } else if (orderTime.equals("2")) {
                salesOrderDTO.setOrderByClause(" order by s.createTime desc");
            }
        } else {
            salesOrderDTO.setOrderByClause("ORDER BY s.id DESC");
        }
        if (!"0".equals(districtID)) {
            salesOrderDTO.setDistrictID(districtID);
        } else if (!"0".equals(cityID)) {
            salesOrderDTO.setCityID(cityID);
            salesOrderDTO.setDistrictID(null);
        } else if (!"0".equals(provinceID)) {
            salesOrderDTO.setProvinceID(provinceID);
            salesOrderDTO.setCityID(null);
            salesOrderDTO.setDistrictID(null);
        } else {
            salesOrderDTO.setProvinceID(null);
            salesOrderDTO.setCityID(null);
            salesOrderDTO.setDistrictID(null);
        }
        
        if (StringUtil.isNotEmpty(searchSupplierName)) {
            salesOrderDTO.setSupplierName(searchSupplierName);
        }
        
        if (StringUtil.isNotEmpty(searchStartModifyTime)) {
            try {
                salesOrderDTO.setStartModifyTime(DateUtil.strintToDatetimeYMDHMS(searchStartModifyTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(searchEndModifyTime)) {
            try {
                salesOrderDTO.setEndModifyTime(DateUtil.strintToDatetimeYMDHMS(searchEndModifyTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<AreaVO> provinceList = areaService.getAllProvince();
        modelAndView.addObject("provinceList", provinceList);
        List<AreaVO> cityList = null;
        List<AreaVO> districtList = null;

        if (provinceList != null && provinceList.size() > 0) {
            cityList = areaService.getAreaByParentID(provinceID == "0" ? provinceList.get(0).getMainID() : provinceID);
        }
        modelAndView.addObject("cityList", cityList);

        if (cityList != null && cityList.size() > 0) {
            districtList = areaService.getAreaByParentID(cityID == "0" ? cityList.get(0).getMainID() : cityID);
        }
        modelAndView.addObject("districtList", districtList);
        modelAndView.addObject("districtID", districtID);
        modelAndView.addObject("cityID", cityID);
        modelAndView.addObject("provinceID", provinceID);

        List<SalesOrderVO> list = salesOrderService.getSalesOrderList(salesOrderDTO);
        modelAndView.addObject("salesOrderList", list);
        modelAndView.addObject("salesOrderDTO", salesOrderDTO);

        return modelAndView;
    }

    /**
     * 统计某时间段内的订单总额
     * @date 2015年7月27日
     * @author yuanyuan
     * @param parentID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("ajaxCountAllAmount")
    public @ResponseBody
    JsonResult ajaxCountAllAmount(String fromDate,String endDate,String orderStatus) {
        try {
            double countAllAmount = 0.0;
            SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
            if(StringUtil.isNotEmpty(fromDate)) salesOrderDTO.setFromDate(fromDate);
            if(StringUtil.isNotEmpty(fromDate)) salesOrderDTO.setEndDate(endDate);
            if(StringUtil.isNotEmpty(orderStatus))salesOrderDTO.setOrderStatus(Integer.parseInt(orderStatus));
            StatisticsInfoVO  statisticsInfoVO = salesOrderService.countSalesOrderAllAmount(salesOrderDTO);
            if(statisticsInfoVO != null && statisticsInfoVO.getOrderTotal() != null)  countAllAmount = Double.parseDouble(statisticsInfoVO.getOrderTotal());
            return new JsonResult(countAllAmount);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }
    
    /**
     * 订单详情
     * 
     * @date 2014年10月29日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("orderInfo.htm")
    public ModelAndView orderInfo(String mainIds) {
        List<SalesOrderVO> list = new ArrayList<SalesOrderVO>();
        String ids[] = mainIds.split(",");
        for (String mainId : ids) {
            SalesOrderVO salesOrderVO = salesOrderService.getSalesOrderLine(mainId);
            list.add(salesOrderVO);
        }
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/order/order_detail");
        modelAndView.addObject("salesOrderList", list);
        return modelAndView;
    }

    /**
     * 编辑订单信息
     * 
     * @date 2014年10月29日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("editSalesOrder.htm")
    public ModelAndView editSalesOrder(String mainId) {
        SalesOrderVO salesOrderVO = salesOrderService.getSalesOrderLine(mainId);
        ModelAndView modelAndView = this.newModelAndView();
        StringBuffer sb = new StringBuffer();
        String salesOrderLineIds = null;
        if (salesOrderVO.getSalesOrderLineList().size() > 0) {
            for (SalesOrderLineVO src : salesOrderVO.getSalesOrderLineList()) {
                sb.append(src.getId() + ",");
            }
            salesOrderLineIds = sb.toString().substring(0, sb.toString().length() - 1);
        }
        modelAndView.setViewName("screen/order/order_edit");
        modelAndView.addObject("salesOrder", salesOrderVO);
        modelAndView.addObject("salesOrderLineIds", salesOrderLineIds);
        return modelAndView;
    }

    /**
     * 更新订单
     * 
     * @date 2014年11月13日
     * @author lupeng
     * @param salesOrder
     * @param priceAndItemCountStr
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("updateSalesOrder")
    public ModelAndView updateSalesOrder(SalesOrderVO salesOrder, String priceAndItemCountStr) {
        salesOrderService.updateSalesOrder(salesOrder, priceAndItemCountStr);
        return new ModelAndView("redirect:orderInfo.htm?mainId=" + salesOrder.getMainID());
    }

    /**
     * 更新订单状态
     * 
     * @date 2014年10月29日
     * @author lupeng
     * @param mainId
     * @param orderStatus
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("changeOrderStatus")
    public @ResponseBody
    JsonResult changeOrderStatus(String orderIDs, String orderStatus) {

        int result = 0;
        if (StringUtils.isNotEmpty(orderIDs) && StringUtils.isNotEmpty(orderStatus)) if (result == 0
                                                                                         && "3".equals(orderStatus)) {
            IService1Proxy s = new IService1Proxy();
            StringBuffer sb = new StringBuffer();
            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            sb.append("<OrderRequest>");
            sb.append("<Partners>JK00010</Partners>");
            sb.append("<Md5Key>7549b12432a772f54b739eb6353a8bdb</Md5Key>");
            sb.append("<Orders>");
            try {
                String ids[] = orderIDs.split(",");
                for (int i = 0; i < ids.length; i++) {
                    SalesOrderVO soVO = salesOrderService.getSalesOrderLine(ids[i]);
                    ExpressVO expressVo = expressService.findExpressByName("宏递快运");
                    List<SupplierItemIDVO> itemIDList = salesOrderService.findSupplierItemIDByOrderID(ids[i]);
                    String billNO = s.getExpressNo("主单号");
                    SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
                    if(StringUtil.isEmpty(soVO.getExpressNumber())){	//只有物流单号为空的时候才更新 避免物流单号不统一
                    	salesOrderDTO.setExpressNumber(billNO);
                    }
                    if (null != expressVo && expressVo.getMainID() != null) {
                        salesOrderDTO.setExpressID(expressVo.getMainID());
                    }
                    salesOrderDTO.setMainID(ids[i]);
                    	salesOrderService.updateSalesOrder(salesOrderDTO);
                    sb.append("<order>");
                    sb.append("<DoIds>" + soVO.getMainID() + "</DoIds>");
                    sb.append("<BillNo>" + billNO + "</BillNo>");
                    sb.append("<ChildsNo></ChildsNo>");
                    sb.append("<Product>" + soVO.getProductName() + "</Product>");
                    sb.append("<Count>" + itemIDList.size() + "</Count>");
                    sb.append("<Weight>0</Weight>");
                    sb.append("<Value>" + soVO.getTotalAmount() + "</Value>");
                    sb.append("<CodAmt>" + soVO.getTotalAmount() + "</CodAmt>");
                    sb.append("<TopaymentAmt>" + soVO.getTotalAmount() + "</TopaymentAmt>");
                    sb.append("<ReceiptNo></ReceiptNo>");
                    sb.append("<Remark>" + soVO.getMemo() + "</Remark>");
                    sb.append("<PaymentType>0</PaymentType>");
                    // <!-- 寄件方 -->
                    // AreaVO sAreaVO = soVO.getSpAreaVO();
                    sb.append("<Sender>");
                    sb.append("<Name>" + soVO.getSpName() + "</Name>");
                    sb.append("<Tel>" + soVO.getSpMobile() + "</Tel>");
                    sb.append("<Company />");
                    sb.append("<Addr>" + soVO.getSpAddress() + "</Addr>");
                    sb.append("</Sender>");
                    // <!-- 收件方 -->
                    sb.append("<Receiver>");
                    sb.append("<Name>" + soVO.getDvName() + "</Name>");
                    sb.append("<Tel>" + soVO.getDvMobile() + "</Tel>");
                    sb.append("<Company></Company>");
                    // AreaVO dAreaVO = soVO.getDvAreaVO();
                    sb.append("<Addr>" + soVO.getDvAddress() + "</Addr>");
                    sb.append("</Receiver>");
                    sb.append("<ExtendField1 />");
                    sb.append("<ExtendField2 />");
                    sb.append("<ExtendField3 />");
                    sb.append("</order>");
                }
                sb.append("</Orders>");
                sb.append("</OrderRequest>");
                String sXml = sb.toString();
                String resultStr = s.buildOrder(sXml);
                if (resultStr.indexOf("<Remark>Success</Remark>") != -1) {
                    result = salesOrderService.updateSalesOrderStatus(orderIDs, orderStatus,null);
                } else {
                    result = 10001;
                }
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            result = salesOrderService.updateSalesOrderStatus(orderIDs, orderStatus,null);
        }
        if (result == 0) return JsonResult.create();
        else if (result == 1000) return new JsonResult(ResultCode.ERROR_UNPICKUP);
        else if (result == 1001) return new JsonResult(ResultCode.ERROR_UNWRITESERIALISENO);
        else if (result == 2000) return new JsonResult(ResultCode.ERROR_UNPAY);
        else return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * @date 2015年5月27日
     * @author moo
     * @param mainID
     * @return
     * @return ModelAndView
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping(value = "orderDeliver", method = RequestMethod.GET)
    public ModelAndView orderDeliver(String mainID, String info) {
        SalesOrderVO salesOrderVO = salesOrderService.getSalesOrderLine(mainID);
        ModelAndView modelAndView = this.newModelAndView();

        modelAndView.setViewName("screen/order/order_deliver");
        modelAndView.addObject("salesOrder", salesOrderVO);
        List<SupplierItemIDVO> itemIDList = salesOrderService.findSupplierItemIDByOrderID(mainID);
        modelAndView.addObject("itemIDList", itemIDList);
        modelAndView.addObject("info", info);
        return modelAndView;
    }

    /**
     * @date 2015年5月27日
     * @author moo
     * @param orderID
     * @param supplierItemID
     * @param serialiseNo
     * @return
     * @return ModelAndView
     * @since JDK 1.6
     * @Description 订单发货
     */
    @RequestMapping(value = "orderDeliver", method = RequestMethod.POST)
    public ModelAndView orderDeliver(String orderID, String packageWeight, String packageNum, String[] supplierItemID,
                                     String[] serialiseNo1) {
        try {
            salesOrderService.updateSalesOrderSupplierItemIDSerialiseNO(orderID, packageWeight, packageNum,
                                                                        supplierItemID, serialiseNo1);
        } catch (DuplicateSerialiseNoException e) {

            return new ModelAndView("redirect:orderDeliver.htm?info=1000&mainID=" + orderID);
        }
        return new ModelAndView("redirect:orderList.htm");
    }

    /**
     * 更新支付状态
     * 
     * @date 2014年10月29日
     * @author lupeng
     * @param mainId
     * @param paymentStatus
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("changePaymentStatus")
    public @ResponseBody
    JsonResult changePaymentStatus(String mainIDs) {
        try {
            if (StringUtils.isBlank(mainIDs)) return new JsonResult(ResultCode.ERROR_SYSTEM_PARAM_FORMAT);
            String[] ids = mainIDs.split(",");
            for (String mainID : ids) {
                AccountDetailVO accountDetailVO = accountDetailService.findAccountDetail(mainID);
                if (accountDetailVO.getObjID() != null) {
                    SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
                    salesOrderDTO.setMainID(accountDetailVO.getObjID());
                    salesOrderDTO.setModifyTime(new Date());
                    salesOrderDTO.setPaymentStatus(1);
                    salesOrderService.updateSalesOrder(salesOrderDTO);
                    AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
                    accountDetailDTO.setStatus(1);
                    accountDetailDTO.setMainID(mainID);
                    accountDetailDTO.setSerialNumber(UUIDUtil.getUUID());
                    accountDetailService.updateAccountDetail(accountDetailDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }

    /**
     * 异步重新计算商品总价
     * 
     * @date 2014年11月5日
     * @author lupeng
     * @param argStr
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("computeProductAmount")
    public @ResponseBody
    JsonResult computeProductAmount(String argStr) {
        try {
            if (StringUtils.isNotBlank(argStr)) {
                double productAmount = 0.00;
                String[] ary = argStr.split("#");
                for (String src : ary) {
                    String[] ary2 = src.split(",");
                    BigDecimal bd1 = new BigDecimal(ary2[1]);
                    BigDecimal bd2 = new BigDecimal(ary2[2]);
                    BigDecimal bd3 = new BigDecimal(Double.toString(productAmount));
                    productAmount = bd3.add(bd1.multiply(bd2)).doubleValue();
                }
                return new JsonResult(productAmount);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return null;
    }

    /**
     * 配货单
     * 
     * @date 2014年10月29日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("orderPicking.htm")
    public ModelAndView orderPicking(String mainIds) {
        List<SalesOrderVO> list = new ArrayList<SalesOrderVO>();
        String ids[] = mainIds.split(",");
        for (String mainId : ids) {
            SalesOrderVO salesOrderVO = salesOrderService.getSalesOrderLine(mainId);
            list.add(salesOrderVO);
        }
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/order/order_pick");
        modelAndView.addObject("salesOrderList", list);
        return modelAndView;
    }

    /**
     * 查看物流单
     * 
     * @date 2014年10月29日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("orderLogistics.htm")
    public ModelAndView logisticsOrder(String mainIds) {
        List<SalesOrderVO> list = new ArrayList<SalesOrderVO>();
        ModelAndView modelAndView = this.newModelAndView();
        String ids[] = mainIds.split(",");
        for (String mainId : ids) {
            SalesOrderVO salesOrderVO = salesOrderService.getSalesOrderLine(mainId);
            /*
             * List<AreaVO> provinceList = areaService.getAllProvince(); modelAndView.addObject("provinceList",
             * provinceList); List<AreaVO> dcityList = null; List<AreaVO> ddistrictList = null; if
             * (salesOrderVO.getSalesOrderDeliveryAddressVO() != null && StringUtil.isNotEmpty(salesOrderVO
             * .getSalesOrderDeliveryAddressVO().getProvinceID())) { dcityList =
             * areaService.getAreaByParentID(salesOrderVO .getSalesOrderDeliveryAddressVO().getProvinceID()); } else {
             * if (provinceList != null && provinceList.size() > 0) { dcityList =
             * areaService.getAreaByParentID(provinceList.get( 0).getMainID()); } } modelAndView.addObject("dcityList",
             * dcityList); if (salesOrderVO.getSalesOrderDeliveryAddressVO() != null &&
             * StringUtil.isNotEmpty(salesOrderVO .getSalesOrderDeliveryAddressVO().getCityID())) { ddistrictList =
             * areaService.getAreaByParentID(salesOrderVO .getSalesOrderDeliveryAddressVO().getCityID()); } else { if
             * (dcityList != null && dcityList.size() > 0) { ddistrictList = areaService.getAreaByParentID(dcityList
             * .get(0).getMainID()); } } modelAndView.addObject("ddistrictList", ddistrictList); List<AreaVO> scityList
             * = null; List<AreaVO> sdistrictList = null; if (salesOrderVO.getSalesOrderShippingAddressVO() != null &&
             * StringUtil.isNotEmpty(salesOrderVO .getSalesOrderShippingAddressVO().getProvinceID())) { scityList =
             * areaService.getAreaByParentID(salesOrderVO .getSalesOrderShippingAddressVO().getProvinceID()); } else {
             * if (provinceList != null && provinceList.size() > 0) { scityList =
             * areaService.getAreaByParentID(provinceList.get( 0).getMainID()); } } modelAndView.addObject("scityList",
             * scityList); if (salesOrderVO.getSalesOrderShippingAddressVO() != null &&
             * StringUtil.isNotEmpty(salesOrderVO .getSalesOrderShippingAddressVO().getCityID())) { sdistrictList =
             * areaService.getAreaByParentID(salesOrderVO .getSalesOrderShippingAddressVO().getCityID()); } else { if
             * (scityList != null && scityList.size() > 0) { sdistrictList = areaService.getAreaByParentID(scityList
             * .get(0).getMainID()); } } modelAndView.addObject("sdistrictList", sdistrictList);
             */
            String expressNumber = salesOrderVO.getExpressNumber();
            if (StringUtil.isNotEmpty(expressNumber)) {
                new OneBarcodeUtil().createCode(expressNumber);
                salesOrderVO.setLogisticsPic(expressNumber + ".jpg");
                list.add(salesOrderVO);
            }

        }
        modelAndView.setViewName("screen/order/order_logistics");
        modelAndView.addObject("salesOrderList", list);
        List<ExpressVO> expresslist = expressService.findALlExpress();
        modelAndView.addObject("expressList", expresslist);
        return modelAndView;
    }

    /**
     * 更新物流单
     * 
     * @date 2014年11月13日
     * @author lupeng
     * @param salesOrder
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("updateLogisticsInfo")
    public ModelAndView updateLogisticsInfo(SalesOrderVO salesOrder) {
        salesOrder.setModifier(getCurrentOperator());
        salesOrderService.updateLogisticsInfo(salesOrder);
        changeOrderStatus(salesOrder.getMainID(), "4");
        return new ModelAndView("redirect:orderLogistics.htm?mainIds=" + salesOrder.getMainID());
    }

    /**
     * 退换货申请列表
     * 
     * @date 2014年11月3日
     * @author lupeng
     * @param salesOrderReturnApplyDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("returnApplyList.htm")
    public ModelAndView returnApplyList(SalesOrderReturnApplyDTO salesOrderReturnApplyDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/order/returnApply_list");

        List<SalesOrderReturnApplyVO> list = salesOrderReturnApplyService.findSalesOrderReturnApplyByPagination(salesOrderReturnApplyDTO);
        modelAndView.addObject("salesOrderReturnApplyList", list);
        modelAndView.addObject("salesOrderReturnApply", salesOrderReturnApplyDTO);
        return modelAndView;
    }

    /**
     * 售后申请列表
     * 
     * @date 2014年11月3日
     * @author lupeng
     * @param salesOrderSalesApplyDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("salesApplyList.htm")
    public ModelAndView salesApplyList(SalesOrderSalesApplyDTO salesOrderSalesApplyDTO, String searchSalesApplyID,
                                       String searchCustomerID, String searchSupplierID, String searchNumberIMEI,
                                       String searchSalesType, String searchSaleApplyStatus,
                                       String searchCustomerName, String searchSupplierName) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/order/salesApply_list");
        if (StringUtils.isNotEmpty(searchSalesApplyID)) salesOrderSalesApplyDTO.setMainID(searchSalesApplyID);
        if (StringUtils.isNotEmpty(searchCustomerID)) salesOrderSalesApplyDTO.setCustomerID(searchCustomerID);
        if (StringUtils.isNotEmpty(searchSupplierID)) salesOrderSalesApplyDTO.setSupplierID(searchSupplierID);
        if (StringUtils.isNotEmpty(searchCustomerName)) salesOrderSalesApplyDTO.setCustomerUserName(searchCustomerName);
        if (StringUtils.isNotEmpty(searchSupplierName)) salesOrderSalesApplyDTO.setSupplierName(searchSupplierName);
        if (StringUtils.isNotEmpty(searchNumberIMEI)) salesOrderSalesApplyDTO.setNumberIMEI(searchNumberIMEI);
        if (StringUtils.isNotEmpty(searchSalesType)) salesOrderSalesApplyDTO.setSalesType(Integer.valueOf(searchSalesType));
        if (StringUtils.isNotEmpty(searchSaleApplyStatus)) salesOrderSalesApplyDTO.setStatus(Integer.valueOf(searchSaleApplyStatus));

        salesOrderSalesApplyDTO.setOrderByClause("ORDER BY s.id DESC");
        List<SalesOrderSalesApplyVO> list = salesOrderSalesApplyService.findSalesOrderSalesApplyByPagination(salesOrderSalesApplyDTO);
        modelAndView.addObject("salesOrderSalesApplyList", list);
        modelAndView.addObject("salesOrderSalesApply", salesOrderSalesApplyDTO);
        return modelAndView;
    }

    /**
     * 退换货申请编辑
     * 
     * @date 2014年11月3日
     * @author lupeng
     * @param salesOrderReturnApplyDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("editReturnApply.htm")
    public ModelAndView editReturnApply(String mainId) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/order/returnApply_edit");
        SalesOrderReturnApplyVO salesOrderReturnApplyVO = salesOrderReturnApplyService.findSalesOrderReturnApplyByMainID(mainId);
        modelAndView.addObject("salesOrderReturnApply", salesOrderReturnApplyVO);
        List<AreaVO> provinceList = areaService.getAllProvince();
        modelAndView.addObject("provinceList", provinceList);
        List<AreaVO> cityList = null;
        List<AreaVO> districtList = null;
        if (salesOrderReturnApplyVO != null) {
            if (salesOrderReturnApplyVO.getReturnAddressVO() != null
                && StringUtil.isNotEmpty(salesOrderReturnApplyVO.getReturnAddressVO().getProvinceID())) {
                cityList = areaService.getAreaByParentID(salesOrderReturnApplyVO.getReturnAddressVO().getProvinceID());
            } else if (salesOrderReturnApplyVO.getShippingAddressVO() != null
                       && StringUtil.isNotEmpty(salesOrderReturnApplyVO.getShippingAddressVO().getProvinceID())) {
                cityList = areaService.getAreaByParentID(salesOrderReturnApplyVO.getShippingAddressVO().getProvinceID());
            } else {
                if (provinceList != null && provinceList.size() > 0) {
                    cityList = areaService.getAreaByParentID(provinceList.get(0).getMainID());
                }
            }
            modelAndView.addObject("cityList", cityList);
            if (salesOrderReturnApplyVO.getReturnAddressVO() != null
                && StringUtil.isNotEmpty(salesOrderReturnApplyVO.getReturnAddressVO().getCityID())) {
                districtList = areaService.getAreaByParentID(salesOrderReturnApplyVO.getReturnAddressVO().getCityID());
            } else if (salesOrderReturnApplyVO.getShippingAddressVO() != null
                       && StringUtil.isNotEmpty(salesOrderReturnApplyVO.getShippingAddressVO().getCityID())) {
                districtList = areaService.getAreaByParentID(salesOrderReturnApplyVO.getShippingAddressVO().getCityID());
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
    public ModelAndView editSalesApply(String mainId) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/order/salesApply_edit");
        SalesOrderSalesApplyVO salesOrderSalesApplyVO = salesOrderSalesApplyService.findSalesOrderSalesApplyByMainID(mainId);
        modelAndView.addObject("salesOrderSalesApply", salesOrderSalesApplyVO);
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
     * 更新退换货申请信息
     * 
     * @date 2014年11月13日
     * @author lupeng
     * @param salesOrderReturnApply
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("updateReturnApplyInfo")
    public ModelAndView updateReturnApplyInfo(SalesOrderReturnApplyVO salesOrderReturnApply) {
        salesOrderReturnApply.setCreator(getCurrentOperator());
        salesOrderReturnApplyService.updateReturnApplyInfo(salesOrderReturnApply);
        return new ModelAndView("redirect:editReturnApply.htm?mainId=" + salesOrderReturnApply.getMainID());
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

    /**
     * 更新退货单申请状态
     * 
     * @date 2014年11月13日
     * @author lupeng
     * @param mainId
     * @param status
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("changeApplyStatus")
    public @ResponseBody
    JsonResult changeApplyStatus(String mainId, String status) {
        try {
            if (StringUtils.isBlank(mainId) || StringUtils.isBlank(status)) return new JsonResult(
                                                                                                  ResultCode.ERROR_SYSTEM_PARAM_FORMAT);
            salesOrderReturnApplyService.updateApplyStatus(mainId, status, getCurrentOperator());
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }

    /**
     * 更新售后申请状态
     * 
     * @date 2014年11月13日
     * @author lupeng
     * @param mainId
     * @param status
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("changeAfterSaleStatus")
    public @ResponseBody
    JsonResult changeAfterSaleStatus(String mainId, String status) {
        try {
            if (StringUtils.isBlank(mainId) || StringUtils.isBlank(status)) return new JsonResult(
                                                                                                  ResultCode.ERROR_SYSTEM_PARAM_FORMAT);
            salesOrderSalesApplyService.updateApplyStatus(mainId, status, getCurrentOperator(),null);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }

    /**
     * 退换货申请列表
     * 
     * @date 2014年11月6日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("returnApplyInfo.htm")
    public ModelAndView returnApplyInfo(String mainId) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/order/returnApply_detail");
        SalesOrderReturnApplyVO salesOrderReturnApplyVO = salesOrderReturnApplyService.findSalesOrderReturnApplyInfoByMainID(mainId);
        modelAndView.addObject("salesOrderReturnApply", salesOrderReturnApplyVO);
        return modelAndView;
    }

    /**
     * 售后申请列表
     * 
     * @date 2014年11月6日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("salesApplyInfo.htm")
    public ModelAndView salesApplyInfo(String mainId) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/order/salesApply_detail");
        SalesOrderSalesApplyVO salesOrderSalesApplyVO = salesOrderSalesApplyService.findSalesOrderSalesApplyInfoByMainID(mainId);
        modelAndView.addObject("salesOrderSalesApply", salesOrderSalesApplyVO);
        return modelAndView;
    }

    /**
     * 退款操作
     * 
     * @date 2014年11月5日
     * @author lupeng
     * @param mainId
     * @param status
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("refundmentOperation")
    public @ResponseBody
    JsonResult refundmentOperation(String mainId, String status) {
        try {
            if (StringUtils.isBlank(mainId) || StringUtils.isBlank(status)) return new JsonResult(
                                                                                                  ResultCode.ERROR_SYSTEM_PARAM_FORMAT);
            salesOrderReturnApplyService.refundmentOperation(mainId, status);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }

    /**
     * 售后操作
     * 
     * @date 2014年11月5日
     * @author lupeng
     * @param mainId
     * @param status
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("salesmentOperation")
    public @ResponseBody
    JsonResult salesmentOperation(String mainId, String status) {
        try {
            if (StringUtils.isBlank(mainId) || StringUtils.isBlank(status)) return new JsonResult(
                                                                                                  ResultCode.ERROR_SYSTEM_PARAM_FORMAT);
            salesOrderSalesApplyService.salesmentOperation(mainId, status);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }

    /**
     * 换货操作
     * 
     * @date 2014年11月6日
     * @author lupeng
     * @param mainId
     * @param status
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("barterOperation")
    public @ResponseBody
    JsonResult barterOperation(String mainId, String status) {
        try {
            if (StringUtils.isBlank(mainId) || StringUtils.isBlank(status)) return new JsonResult(
                                                                                                  ResultCode.ERROR_SYSTEM_PARAM_FORMAT);
            salesOrderReturnApplyService.barterOperation(mainId, status);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }

    @RequestMapping("doPrint")
    public @ResponseBody
    JsonResult doPrint(String mainIDs, Integer status) {
        try {
            salesOrderService.doPrint(mainIDs, status);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.create(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("editSalesOrderLine")
    public ModelAndView editSalesOrderLine(HttpServletRequest request) {
        String[] salesOrderLineIDs = request.getParameterValues("salesOrderLineID");
        String[] transactionPrices = request.getParameterValues("transactionPrice");
        String[] itemCounts = request.getParameterValues("itemCount");
        if (salesOrderLineIDs != null) {
            Double totalAmount = 0d;
            String orderID = null;
            for (int i = 0; i < salesOrderLineIDs.length; i++) {
                String salesOrderLineID = salesOrderLineIDs[i];
                String transactionPrice = null;
                if (transactionPrices != null) {
                    transactionPrice = transactionPrices[i];
                }
                String itemCount = itemCounts[i];
                SalesOrderLineVO salesOrderLineVO = salesOrderLineService.findSalesOrderLineByID(Long.valueOf(salesOrderLineID));
                SalesOrderLineDTO salesOrderLineDTO = new SalesOrderLineDTO();
                if (salesOrderLineVO != null) {
                    orderID = salesOrderLineVO.getOrderID();
                    if (StringUtil.isNotEmpty(transactionPrice)
                        && !transactionPrice.equals(salesOrderLineVO.getTransactionPrice())) {
                        salesOrderLineDTO.setTransactionPrice(Double.valueOf(transactionPrice));
                        totalAmount += salesOrderLineDTO.getTransactionPrice() * Integer.valueOf(itemCount);
                        salesOrderLineDTO.setTotalAmount(salesOrderLineDTO.getTransactionPrice()
                                                         * Integer.valueOf(itemCount));
                    }
                    if (StringUtil.isNotEmpty(itemCount) && !itemCount.equals(salesOrderLineVO.getItemCount())) {
                        salesOrderLineDTO.setItemCount(Integer.valueOf(itemCount));
                    }
                    if (salesOrderLineDTO != null) {
                        salesOrderLineDTO.setId(Long.valueOf(salesOrderLineID));
                        salesOrderLineDTO.setModifier(getCurrentOperator());
                        salesOrderLineService.updateSalesOrderLineByID(salesOrderLineDTO);
                        if (!salesOrderLineVO.getItemCount().equals(itemCount)) {
                            salesOrderLineService.deleteSupplierItemIDBySalesOrderLineID(salesOrderLineVO.getMainID());
                            Integer count = Integer.valueOf(itemCount);
                            SupplierItemDTO supplierItemDTO = new SupplierItemDTO();
                            supplierItemDTO.setSupplierID(salesOrderLineVO.getSupplierID());
                            supplierItemDTO.setItemID(salesOrderLineVO.getItemID());
                            SupplierItemVO supplierItemVO = supplierItemService.findSupplierItemByItemIDAndSupplierID(supplierItemDTO);
                            ItemVO itemVO = itemService.findItemById(salesOrderLineVO.getItemID());
                            for (int j = 0; j < count; j++) {
                                SupplierItemIDDTO supplierItemIDDTO = new SupplierItemIDDTO();
                                supplierItemIDDTO.setMainID(randomNumeric());
                                supplierItemIDDTO.setSupplierID(salesOrderLineVO.getSupplierID());
                                supplierItemIDDTO.setOrderID(salesOrderLineVO.getOrderID());
                                if (supplierItemVO != null) {
                                    supplierItemIDDTO.setOrderLineId(supplierItemVO.getMainID());
                                }
                                supplierItemIDDTO.setSalesOrderLineID(salesOrderLineVO.getMainID());
                                supplierItemIDDTO.setItemName(itemVO.getName());
                                salesOrderLineService.addSupplierItemID(supplierItemIDDTO);
                            }
                        }
                    }
                }
            }
            if (StringUtil.isNotEmpty(orderID)) {
                SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
                salesOrderDTO.setMainID(orderID);
                if (totalAmount != 0) {
                    salesOrderDTO.setTotalAmount(totalAmount);
                    salesOrderDTO.setProductAmount(totalAmount);
                    salesOrderDTO.setPayableAmount(totalAmount);
                }
                salesOrderService.updateSalesOrder(salesOrderDTO);
            }
        }
        return new ModelAndView("redirect:orderList.htm");
    }

    public String randomNumeric() {
        CodeConfigVO codeConfigVO = systemCodeService.findCodeConfigByID(Constant.SALESORDERID);
        String flowCode = RandomStringUtils.randomNumeric(7);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String dateFormat = sdf.format(new Date());
        String year = dateFormat.substring(2, 4);
        String merId = "03602";
        String result = merId + flowCode;
        if (codeConfigVO != null) {
            result = codeConfigVO.getCodeEx() + result;
        } else {
            List<SystemCodeVO> codeList = systemCodeService.findSystemCode(Constant.SALESORDERID);
            if (codeList != null && codeList.size() > 0) {
                result = codeList.get(0).getDefaultValue() + result;
            }
        }
        return result;
    }

    @RequestMapping("getSalesOrderDetail")
    public ModelAndView getSalesOrderDetail(String orderId) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/order/order_list_data");
        if (StringUtil.isNotEmpty(orderId)) {
            SalesOrderVO salesOrderVO = salesOrderService.getSalesOrderLine(orderId);
            modelAndView.addObject("salesOrder", salesOrderVO);
            List<ExpressVO> list = expressService.findALlExpress();
            modelAndView.addObject("expressList", list);
            List<AreaVO> provinceList = areaService.getAllProvince();
            modelAndView.addObject("provinceList", provinceList);
            List<AreaVO> dcityList = null;
            List<AreaVO> ddistrictList = null;
            if (salesOrderVO.getSalesOrderDeliveryAddressVO() != null
                && StringUtil.isNotEmpty(salesOrderVO.getSalesOrderDeliveryAddressVO().getProvinceID())) {
                dcityList = areaService.getAreaByParentID(salesOrderVO.getSalesOrderDeliveryAddressVO().getProvinceID());
            } else {
                if (provinceList != null && provinceList.size() > 0) {
                    dcityList = areaService.getAreaByParentID(provinceList.get(0).getMainID());
                }
            }
            modelAndView.addObject("dcityList", dcityList);
            if (salesOrderVO.getSalesOrderDeliveryAddressVO() != null
                && StringUtil.isNotEmpty(salesOrderVO.getSalesOrderDeliveryAddressVO().getCityID())) {
                ddistrictList = areaService.getAreaByParentID(salesOrderVO.getSalesOrderDeliveryAddressVO().getCityID());
            } else {
                if (dcityList != null && dcityList.size() > 0) {
                    ddistrictList = areaService.getAreaByParentID(dcityList.get(0).getMainID());
                }
            }
            modelAndView.addObject("ddistrictList", ddistrictList);
            List<AreaVO> scityList = null;
            List<AreaVO> sdistrictList = null;
            if (salesOrderVO.getSalesOrderShippingAddressVO() != null
                && StringUtil.isNotEmpty(salesOrderVO.getSalesOrderShippingAddressVO().getProvinceID())) {
                scityList = areaService.getAreaByParentID(salesOrderVO.getSalesOrderShippingAddressVO().getProvinceID());
            } else {
                if (provinceList != null && provinceList.size() > 0) {
                    scityList = areaService.getAreaByParentID(provinceList.get(0).getMainID());
                }
            }
            modelAndView.addObject("scityList", scityList);
            if (salesOrderVO.getSalesOrderShippingAddressVO() != null
                && StringUtil.isNotEmpty(salesOrderVO.getSalesOrderShippingAddressVO().getCityID())) {
                sdistrictList = areaService.getAreaByParentID(salesOrderVO.getSalesOrderShippingAddressVO().getCityID());
            } else {
                if (scityList != null && scityList.size() > 0) {
                    sdistrictList = areaService.getAreaByParentID(scityList.get(0).getMainID());
                }
            }
            modelAndView.addObject("sdistrictList", sdistrictList);
            List<SupplierItemIDVO> itemIDList = salesOrderService.findSupplierItemIDByOrderID(orderId);
            modelAndView.addObject("itemIDList", itemIDList);
            SalesOrderTransDTO salesOrderTransDTO = new SalesOrderTransDTO();
            salesOrderTransDTO.setOrderNO(salesOrderVO.getMainID());
            salesOrderTransDTO.setOrderCode(salesOrderVO.getExpressNumber());
            modelAndView.addObject("orderTrans", salesOrderService.getSalesOrderTrans(salesOrderTransDTO));// 物流信息
        }

        return modelAndView;
    }

    @RequestMapping("editSalesOrderInfo")
    public ModelAndView editSalesOrder(String mainID, String expressFee, String expressId, String expressNumber,
                                       String memo, String invoiceType, String invoiceTitle, String description) {
        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        if (StringUtil.isNotEmpty(expressFee)) {
            salesOrderDTO.setExpressFee(Double.valueOf(expressFee));
        }
        if (StringUtil.isNotEmpty(expressId)) {
            salesOrderDTO.setExpressID(expressId);
        }
        if (StringUtil.isNotEmpty(expressNumber)) {
            salesOrderDTO.setExpressNumber(expressNumber);
        }
        if (StringUtil.isNotEmpty(memo)) {
            salesOrderDTO.setMemo(memo);
        }
        if (StringUtil.isNotEmpty(invoiceType)) {
            salesOrderDTO.setInvoiceType(Integer.valueOf(invoiceType));
        }
        if (StringUtil.isNotEmpty(invoiceTitle)) {
            salesOrderDTO.setInvoiceTitle(invoiceTitle);
        }
        if (StringUtil.isNotEmpty(description)) {
            salesOrderDTO.setDescription(description);
        }
        salesOrderDTO.setMainID(mainID);
        salesOrderService.editSalesOrder(salesOrderDTO);
        return new ModelAndView("redirect:orderList.htm");
    }

    @RequestMapping("editSalesOrderDeliveryAddress")
    public ModelAndView editSalesOrderDeliveryAddress(String orderID, String name, String telephone, String mobile,
                                                      String provinceId, String cityId, String districtId,
                                                      String address, String zip) {
        SalesOrderDeliveryAddressDTO salesOrderDeliveryAddressDTO = new SalesOrderDeliveryAddressDTO();
        if (StringUtil.isNotEmpty(name)) {
            salesOrderDeliveryAddressDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(mobile)) {
            salesOrderDeliveryAddressDTO.setMobile(mobile);
        }
        if (StringUtil.isNotEmpty(telephone)) {
            salesOrderDeliveryAddressDTO.setTelephone(telephone);
        }
        if (StringUtil.isNotEmpty(provinceId)) {
            salesOrderDeliveryAddressDTO.setProvinceID(provinceId);
        }
        if (StringUtil.isNotEmpty(cityId)) {
            salesOrderDeliveryAddressDTO.setCityID(cityId);
        }
        if (StringUtil.isNotEmpty(districtId)) {
            salesOrderDeliveryAddressDTO.setDisctrictID(districtId);
        }
        if (StringUtil.isNotEmpty(address)) {
            salesOrderDeliveryAddressDTO.setAddress(address);
        }
        if (StringUtil.isNotEmpty(zip)) {
            salesOrderDeliveryAddressDTO.setZip(zip);
        }
        if (StringUtil.isNotEmpty(orderID)) {
            salesOrderDeliveryAddressDTO.setSalesOrderID(orderID);
        }
        SalesOrderDeliveryAddressVO salesOrderDeliveryAddressVO = salesOrderDeliveryAddressService.findSalesOrderDeliveryAddressByID(orderID);
        if (salesOrderDeliveryAddressVO != null) {
            salesOrderDeliveryAddressDTO.setModifyTime(new Date());
            salesOrderDeliveryAddressService.updateSalesOrderDeliveryAddressByOrderID(salesOrderDeliveryAddressDTO);
        } else {
            salesOrderDeliveryAddressService.addSalesOrderDeliveryAddress(salesOrderDeliveryAddressDTO);
        }
        return new ModelAndView("redirect:orderList.htm");
    }

    @RequestMapping("editSalesOrderShippingAddress")
    public ModelAndView editSalesOrderShippingAddress(String orderID, String expressId, String expressNumber,
                                                      String name, String telephone, String mobile, String provinceId,
                                                      String cityId, String districtId, String address, String zip,
                                                      String[] supplierItemID, String[] serialiseNo) {
        SalesOrderShippingAddressDTO salesOrderShippingAddressDTO = new SalesOrderShippingAddressDTO();
        if (StringUtil.isNotEmpty(name)) {
            salesOrderShippingAddressDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(mobile)) {
            salesOrderShippingAddressDTO.setMobile(mobile);
        }
        if (StringUtil.isNotEmpty(telephone)) {
            salesOrderShippingAddressDTO.setTelephone(telephone);
        }
        if (StringUtil.isNotEmpty(provinceId)) {
            salesOrderShippingAddressDTO.setProvinceID(provinceId);
        }
        if (StringUtil.isNotEmpty(cityId)) {
            salesOrderShippingAddressDTO.setCityID(cityId);
        }
        if (StringUtil.isNotEmpty(districtId)) {
            salesOrderShippingAddressDTO.setDisctrictID(districtId);
        }
        if (StringUtil.isNotEmpty(address)) {
            salesOrderShippingAddressDTO.setAddress(address);
        }
        if (StringUtil.isNotEmpty(zip)) {
            salesOrderShippingAddressDTO.setZip(zip);
        }
        if (StringUtil.isNotEmpty(orderID)) {
            salesOrderShippingAddressDTO.setSalesOrderID(orderID);
        }
        SalesOrderShippingAddressVO salesOrderShippingAddressVO = salesOrderShippingAddressService.findSalesOrderShippingAddressByID(orderID);
        if (salesOrderShippingAddressVO != null) {
            salesOrderShippingAddressDTO.setModifyTime(new Date());
            salesOrderShippingAddressService.updateSalesOrderShippingAddressByOrderID(salesOrderShippingAddressDTO);
        } else {
            salesOrderShippingAddressService.addSalesOrderShippingAddress(salesOrderShippingAddressDTO);
        }
        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        if (StringUtil.isNotEmpty(expressId)) {
            salesOrderDTO.setExpressID(expressId);
        }
        if (StringUtil.isNotEmpty(expressNumber)) {
            salesOrderDTO.setExpressNumber(expressNumber);
        }
        if (StringUtil.isNotEmpty(orderID)) {
            salesOrderDTO.setMainID(orderID);
        }
        salesOrderService.editSalesOrder(salesOrderDTO);

        return new ModelAndView("redirect:orderList.htm");
    }

    @RequestMapping("exportSalesOrder")
    public void exportSalesOrder(HttpServletResponse response, String searchOrderID, String searchItemID,String searchItemName, 
    											String searchUsername, String searchPaymentType,String searchPaymentStatus, 
    										String searchOrderStatus, String provinceID, String cityID, String districtID) {
        try {
        	SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        	if (StringUtils.isNotEmpty(searchOrderID)) salesOrderDTO.setMainID(searchOrderID);
            if (StringUtils.isNotEmpty(searchItemID)) salesOrderDTO.setItemID(searchItemID);
            if (StringUtils.isNotEmpty(searchItemName)) salesOrderDTO.setItemName(searchItemName);
            if (StringUtil.isNotEmpty(searchUsername)) salesOrderDTO.setCustomerUserName(searchUsername);
            if (StringUtil.isNotEmpty(searchPaymentType)) salesOrderDTO.setPaymentType(Integer.valueOf(searchPaymentType));
            if (StringUtil.isNotEmpty(searchPaymentStatus)) salesOrderDTO.setPaymentStatus(Integer.valueOf(searchPaymentStatus));
            if (StringUtil.isNotEmpty(searchOrderStatus)) salesOrderDTO.setOrderStatus(Integer.valueOf(searchOrderStatus));
            if (!"0".equals(districtID)) {
                salesOrderDTO.setDistrictID(districtID);
            } else if (!"0".equals(cityID)) {
                salesOrderDTO.setCityID(cityID);
                salesOrderDTO.setDistrictID(null);
            } else if (!"0".equals(provinceID)) {
                salesOrderDTO.setProvinceID(provinceID);
                salesOrderDTO.setCityID(null);
                salesOrderDTO.setDistrictID(null);
            } else {
                salesOrderDTO.setProvinceID(null);
                salesOrderDTO.setCityID(null);
                salesOrderDTO.setDistrictID(null);
            }
            salesOrderService.exportSalesOrder(response, salesOrderDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 12 * * ? ")
    // @Scheduled(cron="0/20 * *  * * ? ")
    public void taskCycle() {
        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        salesOrderDTO.setOrderStatus(6);
        salesOrderDTO.setPaymentStatus(1);
        List<SalesOrderVO> list = salesOrderService.getSettlementList(salesOrderDTO);
        if (list != null && list.size() > 0) {
            for (SalesOrderVO order : list) {
                SettlementDTO settlementDTO = new SettlementDTO();
                settlementDTO.setMainID(order.getMainID());
                settlementDTO.setSettlementTime(order.getModifyTime());
                settlementDTO.setSettlementPeople(order.getModifier());
                settlementDTO.setSettlementAmount(order.getTotalAmount());
                settlementDTO.setStatus(1);
                settlementService.addSettlement(settlementDTO);
            }
        }
    }

}
