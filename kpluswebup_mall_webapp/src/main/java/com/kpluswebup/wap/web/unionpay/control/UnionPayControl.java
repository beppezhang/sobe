package com.kpluswebup.wap.web.unionpay.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.Ostermiller.util.MD5;
import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.account.service.AccountDetailService;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.service.SalesOrderLineService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderVO;

@Controller
@RequestMapping("/weixin")
public class UnionPayControl extends BaseController {
    
    @Autowired
    private SalesOrderService     salesOrderService;
    
    @Autowired
    private SalesOrderLineService salesOrderLineService;
    
    @Autowired
    private AccountDetailService accountDetailService;
    
    @RequestMapping("unionPay")
    public ModelAndView unionPay(String orderID){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            Date currentTime = new Date();// 得到当前系统时间
            String pDate = formatter.format(currentTime);

            HttpServletRequest request = this.getRequest();
            String baseDiskPath = request.getSession().getServletContext()
                    .getRealPath("/");
            System.out.println(baseDiskPath);
            /*ConfigReload cr = new ConfigReload("/alipay.properties");
            String location = cr.getProp().getProperty("urlLocation");*/
            SalesOrderVO order = salesOrderService.getSalesOrderLine(orderID);
            List<SalesOrderLineVO> list = salesOrderLineService.findSalesOrderLine(orderID);
            String backUrl = domain + "/weixin/chinaPayPage.htm?orderId="
                    + order.getMainID();
            String frontUrl = domain + "/weixin/toNetPaySuccess.htm?orderId="
                    + order.getMainID();
            Integer amount = Double.valueOf((order.getTotalAmount()) * 100)
                    .intValue();
            StringBuffer orderBody = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    orderBody.append((i + 1) + ":" + list.get(i).getItemName());
                } else {
                    orderBody.append(";" + (i + 1) + ":" + list.get(i).getItemName());
                }
            }
            String mobileNum = "";
            RemoteAccessor remoteAccessor = new RemoteAccessor();

            String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<upbp application=\"MGw.Req\" version =\"1.0.0\" sendTime =\""
                    + pDate
                    + "\" sendSeqId =\"00000888888000\">"
                    + "<frontUrl>"
                    + frontUrl
                    + "</frontUrl><merchantOrderDesc></merchantOrderDesc><misc></misc>"
                    + "<gwType>01</gwType><transTimeout></transTimeout><backUrl>"
                    + backUrl
                    + "</backUrl>"
                    + "<merchantOrderCurrency>156</merchantOrderCurrency><merchantOrderAmt>"
                    + amount
                    + "</merchantOrderAmt>"
                    + "<merchantOrderDesc>"
                    + orderBody
                    + "</merchantOrderDesc>"
                    + "<merchantId>"
                    + /*AlipaySubmit.cr.getProp().getProperty("unionPartner")*/""
                    + "</merchantId><merchantOrderTime>"
                    + pDate
                    + "</merchantOrderTime>"
                    + "<merchantOrderId>"
                    + order.getMainID()
                    + "</merchantOrderId><merchantUserId></merchantUserId>"
                    + "<mobileNum>"
                    + mobileNum
                    + "</mobileNum>"
                    + "<cardNum></cardNum></upbp>";
            BASE64Encoder encoder = new BASE64Encoder();
            BASE64Decoder decoder = new BASE64Decoder();
            String merchantId = encoder.encodeBuffer("".getBytes());
            String mm = MD5.getHashString("654321");

            String keyPath = baseDiskPath+ File.separator + "WEB-INF" + File.separator
                    + "";

            keyPath = keyPath.replaceAll("\\\\", "/");
            String privateKey = EncDecUtil.getCertKey("123456", keyPath);
            String desKey = encoder.encodeBuffer(RSACoder.encryptByPrivateKey(
                    mm.getBytes(), decoder.decodeBuffer(privateKey)));
            byte[] bodyb = DesUtil2.encrypt(data.getBytes("utf-8"), mm
                    .getBytes());
            String miBody = encoder.encode(bodyb);
            String xml = merchantId + "|" + desKey + "|" + miBody;
            String re = remoteAccessor.getResponseByStream(
                    "http://upwap.bypay.cn/gateWay/gate.html", "utf-8", xml);
            String[] strArr = re.split("\\|");
            byte[] de = decoder.decodeBuffer(strArr[1]);
            byte[] b = DesUtil2.decrypt(de, mm.getBytes());
            String content = new String(b, "utf-8");
            System.out.println("weixinUnionPay:"+content);
            Document document = DocumentHelper.parseText(content);
            Element upbp = document.getRootElement();
            String redirectPage = upbp.elementText("gwInvokeCmd");
            System.out.println("unionpay url redirect"+ redirectPage);
            this.getResponse().sendRedirect(redirectPage);
        } catch (Exception e) {
            System.out.println("unionPay:error"+e);
        }
        return null;
    }
    
    @RequestMapping("chinaPayPage")
    public ModelAndView chinaPayPage(String orderId) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("redirect:/weixin/usercartOK.htm?orderID="+orderId);
        try {
            String reqXml = "";
            // 得到报文

            InputStream inputStream = this.getRequest().getInputStream();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream));
            String tmpStr = "";
            while ((tmpStr = bufferedReader.readLine()) != null) {
                reqXml += tmpStr;
            }
            bufferedReader.close();

            BASE64Decoder decoder = new BASE64Decoder();
            String xml[] = reqXml.split("\\|");
            HttpServletRequest request = this.getRequest();
            // 获取证书公匙
            String baseDiskPath = request.getSession().getServletContext()
                    .getRealPath("/");
            //searchMap.clear();
            //searchMap.put("code", Long.valueOf(orderId));
            SalesOrderVO  order = salesOrderService.findSalesOrderByMainID(orderId);
            String cerPath = baseDiskPath + "WEB-INF" + File.separator
                    + "";
            String publicKey = EncDecUtil.getPublicCertKey("000000", cerPath);
            // 对密钥进行RSA解密
            String mm = RSACoder.decryptByPublicKey(xml[1], publicKey);
            // 报文解析
            String wxml = new String(DesUtil2.decrypt(decoder
                    .decodeBuffer(xml[2]), mm.getBytes()), "utf-8");
            System.out.println(wxml);
            Document document = DocumentHelper.parseText(wxml);
            Element upbp = document.getRootElement();
            String merchantOrderId = upbp.elementText("merchantOrderId");
            String merchantOrderAmt = upbp.elementText("merchantOrderAmt");
            String respCode = upbp.elementText("cupsRespCode");
            String cupsQid = upbp.elementText("cupsQid");
            if (respCode.equals("00")) {
                System.out.println(merchantOrderId);
                // …... 数据库更新等相关处理过程
                changeOrder(merchantOrderId, merchantOrderAmt, cupsQid);
            }
        } catch (Exception e) {
            System.out.println("weixin chinaPayPage:error"+e);
        }
        return modelAndView;
    }

    public void changeOrder(String orderId, String amount, String cupsQid) {
        
        SalesOrderVO salesOrderVO = salesOrderService.findSalesOrderByMainID(orderId);
        if (salesOrderVO.getOrderStatus() < 2) {
            AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
            accountDetailDTO.setSerialNumber(salesOrderVO.getMainID());
            accountDetailDTO.setDetailType(2);
            accountDetailDTO.setAmount(Double.parseDouble(amount));
            accountDetailDTO.setAccountType(1);
            accountDetailDTO.setPaymentType(2);
            accountDetailDTO.setDescription(cupsQid);
            accountDetailDTO.setObjID(salesOrderVO.getMainID());
            accountDetailDTO.setCustomerID(salesOrderVO.getCustomerID());
            accountDetailDTO.setStatus(1);
            accountDetailService.addMemberScore(accountDetailDTO);
        }

    }
    
    /**
     * 支付成功
     * @date 2015年1月15日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("toNetPaySuccess")
    public ModelAndView toNetPaySuccess(String orderId) {
        /*try {
            searchMap.clear();
            searchMap.put("code", Long.valueOf(orderId));
            order = (Order) baseManager.getObjectByParams(Order.class,
                    searchMap);
        } catch (Exception e) {
            LOG.error("weixin toNetPaySuccess", e);
        }*/
        return null;
    }

}
