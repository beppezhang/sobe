package com.kpluswebup.wap.web.alipay.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.account.service.AccountDetailService;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.SalesOrderLineService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderVO;

@Controller
@RequestMapping("/weixin")
public class WXApliayControl extends BaseController {
    
    private static final Logger  LOGGER = LogManager.getLogger(WXApliayControl.class);
    private String orderNo; // 订单号
    private int payType = 1; // 付款方式
    private double payAmount; // 付款金额
    
    @Autowired
    private SalesOrderService     salesOrderService;
    @Autowired
    private SalesOrderLineService salesOrderLineService;
    @Autowired
    private AccountDetailService  accountDetailService;
    @Autowired
    private ItemService           itemService;
    
    @RequestMapping("orderAliPay")
    public ModelAndView orderAliPay(String orderID) throws Exception {
        SalesOrderVO order = salesOrderService.getSalesOrderLine(orderID);
        List<SalesOrderLineVO> list = salesOrderLineService.findSalesOrderLine(orderID);
        final StringBuffer sb = new StringBuffer("共有" + list.size() + "件商品:");
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                sb.append((i + 1) + ":" + list.get(i).getItemName());
            } else {
                sb.append(";" + (i + 1) + ":" + list.get(i).getItemName());
            }
        }
        
        //支付宝网关地址
        String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";

        ////////////////////////////////////调用授权接口alipay.wap.trade.create.direct获取授权码token//////////////////////////////////////
        
        //返回格式
        String format = "xml";
        //必填，不需要修改
        
        //返回格式
        String v = "2.0";
        //必填，不需要修改
        
        //请求号
        String req_id = UtilDate.getOrderNum();
        //必填，须保证每次请求都是唯一
        
        String location = domain;
        //服务器异步通知页面路径
        String notify_url = location+"/weixin/alipayNotify.htm?code="+order.getMainID();
        //需http://格式的完整路径，不能加?id=123这类自定义参数

        //页面跳转同步通知页面路径
        String call_back_url = location+ "/weixin/alipayCallBack.htm";
        //需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

        //操作中断返回地址
        String merchant_url = location+"/weixin/index.htm";
        //用户付款中途退出返回商户的地址。需http://格式的完整路径，不允许加?id=123这类自定义参数

        //卖家支付宝帐户
        String seller_email = "3114811311@qq.com";
        //必填

        //商户订单号
        String out_trade_no = order.getMainID();
        //商户网站订单系统中唯一订单号，必填

        //订单名称
        String subject = sb.toString();
        //必填

        //付款金额
        String total_fee = order.getTotalAmount().toString();
        //必填
        
        //请求业务参数详细
        String req_dataToken = "<direct_trade_create_req><notify_url>" 
                                + notify_url + "</notify_url><call_back_url>" 
                                + call_back_url + "</call_back_url><seller_account_name>" 
                                + seller_email + "</seller_account_name><out_trade_no>" 
                                + out_trade_no + "</out_trade_no><subject>" + subject 
                                + "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" 
                                + merchant_url + "</merchant_url></direct_trade_create_req>";
        //必填
        
        //////////////////////////////////////////////////////////////////////////////////
        
        //把请求参数打包成数组
        Map<String, String> sParaTempToken = new HashMap<String, String>();
        sParaTempToken.put("service", "alipay.wap.trade.create.direct");
        sParaTempToken.put("partner","2088712909431264");
        sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
        sParaTempToken.put("sec_id", AlipayConfig.sign_type);
        sParaTempToken.put("format", format);
        sParaTempToken.put("v", v);
        sParaTempToken.put("req_id", req_id);
        sParaTempToken.put("req_data", req_dataToken);
        
        //建立请求
        String sHtmlTextToken = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW,"", "",sParaTempToken);
        //URLDECODE返回的信息
        sHtmlTextToken = URLDecoder.decode(sHtmlTextToken,AlipayConfig.input_charset);
        //获取token
        String request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);
        //out.println(request_token);
        
        ////////////////////////////////////根据授权码token调用交易接口alipay.wap.auth.authAndExecute//////////////////////////////////////
        
        //业务详细
        String req_data = "<auth_and_execute_req><request_token>" + request_token + "</request_token></auth_and_execute_req>";
        //必填
        
        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
        sParaTemp.put("partner", "2088712909431264");
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("sec_id", AlipayConfig.sign_type);
        sParaTemp.put("format", format);
        sParaTemp.put("v", v);
        sParaTemp.put("req_data", req_data);
        
        //建立请求
        String sHtmlText = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW, sParaTemp, "get", "确认");
        System.out.println("weixinAlipay======================"+sHtmlText);
        LOGGER.info("weixinAlipay======================"+sHtmlText);
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
     * 支付宝回调
     * @date 2015年1月15日
     * @author liudanqi
     * @return
     * @throws Exception
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("alipayCallBack")
    public ModelAndView alipayCallBack() throws Exception {
        ModelAndView modelAndView = this.newModelAndView();
        
        System.out.println("OrderPayAction,alipayCallBack() started.");
        LOGGER.info("OrderPayAction,alipayCallBack() started.");
        HttpServletRequest request = getRequest();
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        String trade_no = request.getParameter("trade_no"); // 支付宝交易号
        String order_no = request.getParameter("out_trade_no"); // 获取订单号
        String trade_status = request.getParameter("trade_status"); // 交易状态
        String sWord = "alipayCallBack()同步回调返回参数：" + AlipayCore.createLinkString(params);
        System.out.println("alipay verify log:" + sWord);
        // 计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);

        if (verify_result) {// 验证成功
                // 业务处理.....
                    // 有可能异步先回调,执行过这个方法,所以判断一下,以免出现两次支付记录和状态改变记录
            SalesOrderVO order = salesOrderService.findSalesOrderByMainID(order_no);
            this.orderNo = order.getMainID();
            this.payType = 1; // 支付宝
            this.payAmount =order.getTotalAmount();
            if (order != null && order.getOrderStatus() < 2) {
                handPayover(order_no, trade_no, payAmount+"", order.getDescription(), trade_status,order);
            }

            System.out.println("同步验证成功,时间_" + new Date() + "-->订单号:" + order_no + ",订单金额:" + payAmount + ",支付宝交易号:" + trade_no);
            LOGGER.info("同步验证成功,时间_" + new Date() + "-->订单号:" + order_no + ",订单金额:" + payAmount + ",支付宝交易号:" + trade_no
                        + ",买家支付宝账号:");
            modelAndView.setViewName("redirect:/weixin/usercartOK.htm?orderID="+order_no);
            return modelAndView;
            
        } else {
            System.out.println("同步验证失败,时间_" + new Date() + "-->订单号:" + order_no + ",订单金额:" + payAmount + ",支付宝交易号:" + trade_no
                    + ",买家支付宝账号:");
            LOGGER.info("同步验证失败,时间_" + new Date() + "-->订单号:" + order_no + ",订单金额:" + payAmount + ",支付宝交易号:" + trade_no
                        + ",买家支付宝账号:");
            throw new Exception("alipaycallback verify error.");
        }
    }

    /**
     * 支付宝支付异步回调 不跳转页面,只返回success 或 fail
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("alipayNotify")
    public ModelAndView alipayNotify() {
        System.out.println("OrderPayAction,alipayNotify() started.");
        HttpServletRequest request = getRequest();
        String result = "";
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }

            params.put(name, valueStr);
        }

        String trade_no = request.getParameter("trade_no");
        String order_no = request.getParameter("out_trade_no");
        String total_fee = request.getParameter("total_fee");
        String subject = request.getParameter("subject");
        String body = request.getParameter("body");
        String buyer_email = request.getParameter("buyer_email");
        String trade_status = request.getParameter("trade_status");
        String extra_common_param = request.getParameter("extra_common_param");
        String sWord = "alipayNotify()异步回调返回参数：" + AlipayCore.createLinkString(params);
        System.out.println("alipay verify log:" + sWord);
        System.out.println("/n BuyerEmail:" + buyer_email);
        if (AlipayNotify.verify(params)) {
                // 业务处理......
                    // 有可能异步先回调,执行过这个方法,所以判断一下,以免出现两次支付记录和状态改变记录
                SalesOrderVO order = salesOrderService.findSalesOrderByMainID(order_no);
                this.orderNo = order.getMainID();
                this.payType = 1; // 支付宝
                this.payAmount =order.getTotalAmount();
                    if (order != null && order.getOrderStatus() < 2) {
                        handPayover(order_no, trade_no, payAmount+"", buyer_email, trade_status,order);
                    }
                result = "success";
           
           
        } else {
            
        }
        
        PrintWriter out = null;
        try {
            out = getResponse().getWriter();
            out.print(result);
            out.flush();
            out.close();
        } catch (Exception e) {
            
        }
        return null;
    }

    /**
     * 付款成功后的一些处理
     * 
     * @param trade_no
     *            支付宝交易号
     * @param total_fee
     *            获取总金额
     * @param buyer_email
     *            买家支付宝账号
     * @param trade_status
     *            交易状态
     * @param order_no
     *            订单号
     * @throws Exception
     */
    private void handPayover(String order_no, String trade_no, String total_fee, String buyer_email, String trade_status,SalesOrderVO order) {
        AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
        accountDetailDTO.setSerialNumber(trade_no);
        accountDetailDTO.setDetailType(2);
        accountDetailDTO.setAmount(Double.parseDouble(total_fee));
        accountDetailDTO.setAccountType(1);
        accountDetailDTO.setPaymentType(1);
        accountDetailDTO.setDescription(buyer_email);
        accountDetailDTO.setObjID(order_no);
        accountDetailDTO.setCustomerID(order.getCustomerID());
        accountDetailDTO.setStatus(1);
        accountDetailService.addMemberScore(accountDetailDTO);
    }

}
