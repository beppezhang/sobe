package com.kpluswebup.mall.web.member.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import chinapay.PrivateKey;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.mall.web.netpay.NetPayRequest;
import com.kpluswebup.mall.web.netpay.NetPayResponse;
import com.kpluswebup.web.account.service.AccountDetailService;
import com.kpluswebup.web.admin.system.service.TransConfigService;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.TransConfigVO;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/mall/ten")
public class TenNetPayControl extends BaseController {

    private static final Logger  LOGGER = LogManager.getLogger(TenNetPayControl.class);
    @Autowired
    private AccountDetailService accountDetailService;
    @Autowired
    private SalesOrderService    salesOrderService;
    @Autowired
    private MemberSerivce           memberSerivce;
    @Autowired
    private TransConfigService           transConfigService;

    @RequestMapping("rechargeNetPay")
    public ModelAndView rechargeNetPay(Double amount) {
         HttpServletRequest request = this.getRequest();
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
                          + "/";
        String baseDiskPath = request.getSession().getServletContext().getRealPath("/");
        String BgRetUrl = basePath + "mall/ten/getTenChinapayRet.htm";
        String PageRetUrl = basePath + "mall/ten/tenChinaPayPage.htm";
        String MerId = "808080201303602";
        String Priv1 = "lsts.com";
        NetPayRequest pay = new NetPayRequest();
        pay.setMerId(MerId);
        String ordersID = salesOrderService.randomNumeric();
        pay.setOrdId(ordersID);
        pay.setCuryId("156");
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String datestr = df.format(date);
        pay.setTransDate(datestr);
        pay.setTransAmt(this.paseAmount(amount.toString()));
        pay.setTransType("0001");
        pay.setVersion("20040916");
        pay.setPriv1(Priv1);
        pay.setBgRetUrl(BgRetUrl);
        pay.setPageRetUrl(PageRetUrl);
        /* …… 给以上变量分别赋值 */

        PrivateKey key = new PrivateKey();
        chinapay.SecureLink t;
        boolean flag;
        LOGGER.info("开始付款--------" + baseDiskPath + File.separator + "WEB-INF" + File.separator + "MerPrK.key");
        flag = key.buildKey(pay.getMerId().trim(), 0, baseDiskPath + File.separator + "WEB-INF" + File.separator
                                                      + "MerPrK.key");
        if (flag == false) {
            LOGGER.info("build key error!");
        } else {
            t = new chinapay.SecureLink(key);
            LOGGER.info("开始--------");
            pay.setChkValue(t.signOrder(pay.getMerId(), pay.getOrdId(), pay.getTransAmt(), pay.getCuryId(),
                                        pay.getTransDate(), pay.getTransType()));
            StringBuffer sbHtml = new StringBuffer();
            sbHtml.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN'>"
                          + "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /></head>");
            sbHtml.append("<form id=\"chinapaysubmit\" name=\"chinapaysubmit\" action=\"https://payment.ChinaPay.com/pay/TransGet \" method=\"POST\">");
            sbHtml.append("<input type=\"hidden\" name=\"MerId\" value=\"" + pay.getMerId() + "\"/>");
            sbHtml.append("<input type=\"hidden\" name=\"OrdId\" value=\"" + pay.getOrdId() + "\"/>");
            sbHtml.append("<input type=\"hidden\" name=\"TransAmt\" value=\"" + pay.getTransAmt() + "\"/>");
            sbHtml.append("<input type=\"hidden\" name=\"CuryId\" value=\"" + pay.getCuryId() + "\"/>");
            sbHtml.append("<input type=\"hidden\" name=\"TransDate\" value=\"" + pay.getTransDate() + "\"/>");
            sbHtml.append("<input type=\"hidden\" name=\"TransType\" value=\"" + pay.getTransType() + "\"/>");
            sbHtml.append("<input type=\"hidden\" name=\"Version\" value=\"" + pay.getVersion() + "\"/>");
            sbHtml.append("<input type=\"hidden\" name=\"BgRetUrl\" value=\"" + pay.getBgRetUrl() + "\"/>");
            sbHtml.append("<input type=\"hidden\" name=\"PageRetUrl\" value=\"" + pay.getPageRetUrl() + "\"/>");
            // sbHtml.append("<input type=\"hidden\" name=\"GateId\" value=\"" + pay.getGateId() + "\"/>");
            sbHtml.append("<input type=\"hidden\" name=\"Priv1\" value=\"" + pay.getPriv1() + "\"/>");
            sbHtml.append("<input type=\"hidden\" name=\"ChkValue\" value=\"" + pay.getChkValue() + "\"/>");
            sbHtml.append("</form>");
            sbHtml.append("</html>");
            sbHtml.append("<script>document.chinapaysubmit.submit();</script>");
            PrintWriter out = null;
            try {
                out = getResponse().getWriter();
                LOGGER.info(sbHtml);
                out.println(sbHtml);
                System.out.println(sbHtml);
                out.close();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @RequestMapping("getTenChinapayRet")
    public ModelAndView getTenChinapayRet() {
        HttpServletRequest request = this.getRequest();
        NetPayResponse pay = new NetPayResponse();
        pay.init(request);
        try {
            chinapay.PrivateKey key = new chinapay.PrivateKey();
            chinapay.SecureLink t;
            boolean flag;
            boolean flag1;
            System.out.println("----加载密钥----");
            String baseDiskPath = request.getSession().getServletContext().getRealPath("/");
            flag = key.buildKey("999999999999999", 0, baseDiskPath + "WEB-INF" + File.separator + "PgPubK.key");
            if (flag == false) {
                return null;
            }
            t = new chinapay.SecureLink(key);
            flag1 = t.verifyTransResponse(pay.getMerid(), pay.getOrderno(), pay.getTransamt(), pay.getCurrencycode(),
                                          pay.getTransdate(), pay.getTranstype(), pay.getStatus(), pay.getCheckvalue());
            if (flag1 == false) {
                return null;
            } else {
                /* …... 数据库更新等相关处理过程 */
                if (pay.getStatus().equals("1001")) {
                    changeOrder(pay);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return new ModelAndView("redirect:/mall/buyer/userCenter.htm");
    }

    @RequestMapping("tenChinaPayPage")
    public ModelAndView tenChinaPayPage() {
        HttpServletRequest request = this.getRequest();
        NetPayResponse pay = new NetPayResponse();
        pay.init(request);
        try {
            chinapay.PrivateKey key = new chinapay.PrivateKey();
            chinapay.SecureLink t;
            boolean flag;
            boolean flag1;
            System.out.println("----加载密钥----");
            String baseDiskPath = request.getSession().getServletContext().getRealPath("/");
            flag = key.buildKey("999999999999999", 0, baseDiskPath + File.separator + "WEB-INF" + File.separator + "PgPubK.key");
            if (flag == false) {
                return null;
            }
            t = new chinapay.SecureLink(key);
            flag1 = t.verifyTransResponse(pay.getMerid(), pay.getOrderno(), pay.getTransamt(), pay.getCurrencycode(),
                                          pay.getTransdate(), pay.getTranstype(), pay.getStatus(), pay.getCheckvalue());
            if (flag1 == false) {
                return null;
            } else {
                if (pay.getStatus().equals("1001")) {
                    // …... 数据库更新等相关处理过程
                    changeOrder(pay);
                    /*
                     * payType=3; orderNo=pay.getOrderno(); Order order=orderManager.getOrderByCode(pay.getOrderno());
                     * payAmount=order.getAmount().toString(); orderId=order.getId();
                     */
                } else {
                    return new ModelAndView("error");
                }
            }
        } catch (Exception e) {
            return null;
        }
       return new ModelAndView("redirect:/mall/buyer/userCenter.htm");
    }

    public void changeOrder(NetPayResponse pay) {
          // 充值记录
           /* CustomerVO customerVO = this.findUserInfo();
            AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
            accountDetailDTO.setMainID(UUIDUtil.getUUID());
            accountDetailDTO.setSerialNumber(pay.getOrderno());
            accountDetailDTO.setDetailType(1);//充值
            accountDetailDTO.setAmount(Double.parseDouble(pay.getTransamt())/100);
            accountDetailDTO.setAccountType(1);
            accountDetailDTO.setPaymentType(2);//银联
            accountDetailDTO.setDescription(pay.getMerid());
            accountDetailDTO.setObjID(pay.getOrderno());
            accountDetailDTO.setCustomerID(customerVO.getMainID());
            accountDetailDTO.setStatus(1);
            accountDetailService.insertMemberScore(accountDetailDTO);*/
            
            TransConfigVO transConfigVO = transConfigService.findTransConfig();
            CustomerVO customerVO = this.findUserInfo();
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setMainID(customerVO.getMainID());
            customerDTO.setScore(customerVO.getScore()+(Double.parseDouble(pay.getTransamt())/transConfigVO.getScorePrice()));
            memberSerivce.updateMember(customerDTO);
            AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
            accountDetailDTO.setMainID(UUIDUtil.getUUID());
            accountDetailDTO.setSerialNumber(pay.getOrderno());
            accountDetailDTO.setDetailType(1);// 充值
            accountDetailDTO.setAmount((Double.parseDouble(pay.getTransamt())/transConfigVO.getScorePrice()));//积分
            accountDetailDTO.setBlance(Double.parseDouble(pay.getTransamt()));//金额
            accountDetailDTO.setAccountType(2);//积分
            accountDetailDTO.setPaymentType(2);// 支付宝
            accountDetailDTO.setDescription(pay.getMerid());
            accountDetailDTO.setObjID(pay.getOrderno());
            accountDetailDTO.setCustomerID(customerVO.getMainID());
            accountDetailDTO.setStatus(1);
            accountDetailService.insertMemberScore(accountDetailDTO);
    }

    public String paseAmount(String stramout) {
        stramout = stramout.replace(".", "-");
        String str[] = stramout.split("-");
        String amountstr = "";
        if (str.length == 1) {
            str[0] = str[0] + "00";
        } else {
            if (str[1].length() > 2) {
                str[1] = str[1].substring(0, 2);
            } else {
                int size = 2 - str[1].length();
                for (int i = 0; i < size; i++) {
                    str[1] = str[1] + "0";
                }
            }
        }
        if (str.length == 1) {
            amountstr = str[0];
        } else {
            amountstr = str[0] + str[1];
        }
        if (amountstr.length() < 12) {
            int size = 12 - amountstr.length();
            for (int i = 0; i < size; i++) {
                amountstr = "0" + amountstr;
            }
        }
        return amountstr;
    }

    public void print(String url) {
        File file = new File(url);
        StringBuffer bufer = new StringBuffer();
        Reader reader = null;
        try {
            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    char countent = (char) tempchar;
                    bufer.append(countent);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("签名====" + bufer.toString());
    }
    
   

}
