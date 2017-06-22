package com.kpluswebup.mall.web.member.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.alipay.AlipayCore;
import com.kpluswebup.mall.web.alipay.AlipayNotify;
import com.kpluswebup.mall.web.alipay.AlipayService;
import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.account.service.AccountDetailService;
import com.kpluswebup.web.admin.system.service.TransConfigService;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.SalesOrderLineService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.TransConfigVO;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/mall/tenalipay")
public class TenAlipayControl extends BaseController {

    private static final Logger   LOGGER = LogManager.getLogger(TenAlipayControl.class);
    @Autowired
    private SalesOrderService     salesOrderService;
    @Autowired
    private SalesOrderLineService salesOrderLineService;
    @Autowired
    private AccountDetailService  accountDetailService;
    @Autowired
    private ItemService           itemService;
    @Autowired
    private MemberSerivce           memberSerivce;
    @Autowired
    private TransConfigService           transConfigService;

    @RequestMapping("tenaliPay")
    public String tenaliPay(Double amount) {
        // 必填参数//
        final String out_trade_no = salesOrderService.randomNumeric();// 订单号
        final String subject = "订单号为:" + salesOrderService.randomNumeric();// 订单名称
        // 订单描述、订单详细、订单备注，显示在支付宝收银台里的“商品描述”里
        final String body = "蓝血天使会员充值，充值金额：" + amount;
        // DecimalFormat 是 NumberFormat 的一个具体子类，用于格式化十进制数字
        // 使用给定的模式和默认语言环境的符号创建一个 DecimalFormat
        DecimalFormat formater = new DecimalFormat("0.00");
        String orderAmount = formater.format(amount);
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("payment_type", "1");// 支付类型
        sParaTemp.put("out_trade_no", out_trade_no);// 订单编号
        sParaTemp.put("subject", subject);// 商品名称
        sParaTemp.put("body", body);// 订单描述
        sParaTemp.put("total_fee", orderAmount);// 总金额
        sParaTemp.put("show_url", "");// 商品展示网址
        sParaTemp.put("paymethod", "");// 默讣支付方式
        sParaTemp.put("defaultbank", "");// 默认银行
        sParaTemp.put("anti_phishing_key", "");
        sParaTemp.put("exter_invoke_ip", "");
        sParaTemp.put("extra_common_param", "");
        sParaTemp.put("buyer_email", "");// 买家Email
        sParaTemp.put("royalty_type", "");
        sParaTemp.put("royalty_parameters", "");

        final String sWord = "aliPay()中支付宝请求参数：" + AlipayCore.createLinkString(sParaTemp);// aliPay()中支付宝请求参数：anti_phishing_key=&body=共有1件商品:1:圣诞节织带-丝网油墨印刷-尼龙雪纱带-红色
                                                                                          // 200*5mm&buyer_email=&defaultbank=&exter_invoke_ip=&extra_common_param=234&out_trade_no=SO00131120103703&payment_type=1&paymethod=&royalty_parameters=&royalty_type=&show_url=&subject=订单号为:SO00131120103703&total_fee=0.01
        LOGGER.info("alipay verify log:" + sWord);
        // 构造函数，生成请求URL----------------
        final String sHtmlText = AlipayService.create_direct_pay_by_user(sParaTemp,2);
        PrintWriter out = null;
        try {
            out = getResponse().getWriter();
            out.println(sHtmlText);
            out.close();
            return null;
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 支付宝支付回调
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("alipayCallBack")
    public ModelAndView alipayCallBack() throws Exception {
        LOGGER.info("OrderPayAction,alipayCallBack() started.");
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = getRequest().getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        String trade_no = getRequest().getParameter("trade_no"); // 支付宝交易号
        String order_no = getRequest().getParameter("out_trade_no"); // 获取订单号
        String total_fee = getRequest().getParameter("total_fee"); // 获取总金额
        String subject = getRequest().getParameter("subject");// 商品名称、订单名称
        String body = getRequest().getParameter("body");
        String buyer_email = getRequest().getParameter("buyer_email"); // 买家支付宝账号
        String trade_status = getRequest().getParameter("trade_status"); // 交易状态
        String sWord = "alipayCallBack()同步回调返回参数：" + AlipayCore.createLinkString(params);
        LOGGER.info("alipay verify log:" + sWord);
        LOGGER.info("/n BuyerEmail:" + buyer_email);
        // 计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);

        if (verify_result) {// 验证成功
            String extra_common_param = getRequest().getParameter("extra_common_param");
            // ////////////////////////////////////////////////////////////////////////////////////////
            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                // 业务处理.....
                // 有可能异步先回调,执行过这个方法,所以判断一下,以免出现两次支付记录和状态改变记录
                // SalesOrderVO temporder = salesOrderService.findSalesOrderByMainID(order_no);
                if (order_no != null) {
                    handPayover(order_no, trade_no, total_fee, buyer_email, trade_status);
                }
            }

            LOGGER.info("同步验证成功,时间_" + new Date() + "-->订单号:" + order_no + ",订单金额:" + total_fee + ",商品名称:" + subject
                        + ",支付宝交易号:" + trade_no + ",买家支付宝账号:" + buyer_email);
            return new ModelAndView("redirect:/mall/buyer/userCenter.htm");

        } else {
            LOGGER.info("同步验证失败,时间_" + new Date() + "-->订单号:" + order_no + ",订单金额:" + total_fee + ",商品名称:" + subject
                        + ",支付宝交易号:" + trade_no + ",买家支付宝账号:" + buyer_email);
            throw new Exception("alipaycallback verify error.");
        }
    }

    /**
     * 支付宝支付异步回调 不跳转页面,只返回success 或 fail
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("alipayNotify")
    public String alipayNotify() {
        LOGGER.info("OrderPayAction,alipayNotify() started.");
        String result = "";
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = getRequest().getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }

            params.put(name, valueStr);
        }

        String trade_no = getRequest().getParameter("trade_no");
        String order_no = getRequest().getParameter("out_trade_no");
        String total_fee = getRequest().getParameter("total_fee");
        String subject = getRequest().getParameter("subject");
        String body = getRequest().getParameter("body");
        String buyer_email = getRequest().getParameter("buyer_email");
        String trade_status = getRequest().getParameter("trade_status");
        String extra_common_param = getRequest().getParameter("extra_common_param");
        String sWord = "alipayNotify()异步回调返回参数：" + AlipayCore.createLinkString(params);
        LOGGER.info("alipay verify log:" + sWord);
        LOGGER.info("/n BuyerEmail:" + buyer_email);
        if (AlipayNotify.verify(params)) {
            if ((trade_status.equals("TRADE_FINISHED")) || (trade_status.equals("TRADE_SUCCESS"))) {
                // 业务处理......
                // 有可能异步先回调,执行过这个方法,所以判断一下,以免出现两次支付记录和状态改变记录
                // SalesOrderVO temporder = salesOrderService.findSalesOrderByMainID(order_no);
                if (order_no != null) {
                    handPayover(order_no, trade_no, total_fee, buyer_email, trade_status);
                }
                result = "success";
            } else {
                result = "success";
            }
            LOGGER.info("异步验证成功,时间_" + new Date() + "-->订单号:" + order_no + ",订单金额:" + total_fee + ",商品名称:" + subject
                        + ",支付宝交易号:" + trade_no + ",买家支付宝账号:" + buyer_email);
        } else {
            LOGGER.info("异步验证失败,时间_" + new Date() + "-->订单号:" + order_no + ",订单金额:" + total_fee + ",商品名称:" + subject
                        + ",支付宝交易号:" + trade_no + ",买家支付宝账号:" + buyer_email);
            result = "fail";
        }
        LOGGER.info("支付宝异步返回的结果:" + result);
        PrintWriter out = null;
        try {
            out = getResponse().getWriter();
            out.print(result);
            out.flush();
            out.close();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
        return null;
    }

    /**
     * 付款成功后的一些处理
     * 
     * @param trade_no 支付宝交易号
     * @param total_fee 获取总金额
     * @param buyer_email 买家支付宝账号
     * @param trade_status 交易状态
     * @param order_no 订单号
     * @throws Exception
     */
    private void handPayover(String order_no, String trade_no, String total_fee, String buyer_email, String trade_status) {
        LOGGER.info("支付成功后的业务处理开始handPayover().");
        // 充值记录
        TransConfigVO transConfigVO = transConfigService.findTransConfig();
        CustomerVO customerVO = this.findUserInfo();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setMainID(customerVO.getMainID());
        customerDTO.setScore(customerVO.getScore()+(Double.parseDouble(total_fee)/transConfigVO.getScorePrice()));
        memberSerivce.updateMember(customerDTO);
        AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
        accountDetailDTO.setMainID(UUIDUtil.getUUID());
        accountDetailDTO.setSerialNumber(trade_no);
        accountDetailDTO.setDetailType(1);// 充值
        accountDetailDTO.setAmount((Double.parseDouble(total_fee)/transConfigVO.getScorePrice()));//积分
        accountDetailDTO.setBlance(Double.parseDouble(total_fee));//金额
        accountDetailDTO.setAccountType(2);//积分
        accountDetailDTO.setPaymentType(1);// 支付宝
        accountDetailDTO.setDescription(buyer_email);
        accountDetailDTO.setObjID(order_no);
        accountDetailDTO.setCustomerID(customerVO.getMainID());
        accountDetailDTO.setStatus(1);
        accountDetailService.insertMemberScore(accountDetailDTO);
    }

}
