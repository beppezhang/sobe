package com.kpluswebup.mall.web.alipay;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

/* *
 * 类名：AlipayService功能：支付宝各接口构造类详细：构造支付宝各接口请求参数版本：3.2修改日期：2011-03-17说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayService {

    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";

    // excashier.alipay.com

    /**
     * excashier.alipay.com 构造即时到帐接口
     * 
     * @param sParaTemp 请求参数集合
     * @return 表单提交HTML信息
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static String create_direct_pay_by_user(Map<String, String> sParaTemp,Integer type) {

        ClassPathResource resource = new ClassPathResource("alipay.properties");
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(resource.getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 支付宝配置
        AlipayConfig.partner = prop.getProperty("partner");
        AlipayConfig.key = prop.getProperty("key");
        AlipayConfig.seller_email = prop.getProperty("seller_email");
        if(type==1){
            AlipayConfig.notify_url = prop.getProperty("notify_url");
            AlipayConfig.return_url = prop.getProperty("return_url");
        }else{
            AlipayConfig.notify_url = prop.getProperty("notify_url_u");
            AlipayConfig.return_url = prop.getProperty("return_url_u");
        }
        AlipayConfig.input_charset = prop.getProperty("input_charset");
        AlipayConfig.sign_type = prop.getProperty("sign_type");
        // 增加基本配置
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("return_url", AlipayConfig.return_url);
        sParaTemp.put("notify_url", AlipayConfig.notify_url);
        sParaTemp.put("seller_email", AlipayConfig.seller_email);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        String strButtonName = "确认";

        return AlipaySubmit.buildForm(sParaTemp, ALIPAY_GATEWAY_NEW, "get", strButtonName);
    }

    // {body=共有1件商品:1:圣诞节织带-丝网油墨印刷-尼龙雪纱带-红色 200*5mm, subject=订单号为:SO00131120105035,
    // notify_url=http://t.codeoem.com:8901/alipayNotify.htm, defaultbank=, out_trade_no=SO00131120105035,
    // return_url=http://t.codeoem.com:8901/alipayCallBack.htm, _input_charset=utf-8, buyer_email=, exter_invoke_ip=,
    // extra_common_param=235, royalty_parameters=, total_fee=0.01, royalty_type=, service=create_direct_pay_by_user,
    // paymethod=, partner=2088801703867282, seller_email=hzicszx@163.com, anti_phishing_key=, payment_type=1,
    // show_url=}
    // {body=共有1件商品:1:圣诞节织带-丝网油墨印刷-尼龙雪纱带-红色 200*5mm, subject=订单号为:SO00131120105035,
    // notify_url=http://t.codeoem.com:8901/alipayNotify.htm, defaultbank=, out_trade_no=SO00131120105035,
    // return_url=http://t.codeoem.com:8901/alipayCallBack.htm, _input_charset=utf-8, buyer_email=, exter_invoke_ip=,
    // extra_common_param=235, royalty_parameters=, total_fee=0.01, royalty_type=, service=create_direct_pay_by_user,
    // paymethod=, partner=2088801703867282, seller_email=hzicszx@163.com, anti_phishing_key=, payment_type=1,
    // show_url=}
    /**
     * 用于防钓鱼，调用接口query_timestamp来获取时间戳的处理函数 注意：远程解析XML出错，与服务器是否支持SSL等配置有关
     * 
     * @return 时间戳字符串
     * @throws IOException
     * @throws DocumentException
     * @throws MalformedURLException
     */
    public static String query_timestamp() throws MalformedURLException, DocumentException, IOException {

        // 构造访问query_timestamp接口的URL串
        String strUrl = ALIPAY_GATEWAY_NEW + "service=query_timestamp&partner=" + AlipayConfig.partner;
        StringBuffer result = new StringBuffer();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(new URL(strUrl).openStream());

        List<Node> nodeList = doc.selectNodes("//alipay/*");

        for (Node node : nodeList) {
            // 截取部分不需要解析的信息
            if (node.getName().equals("is_success") && node.getText().equals("T")) {
                // 判断是否有成功标示
                List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
                for (Node node1 : nodeList1) {
                    result.append(node1.getText());
                }
            }
        }

        return result.toString();
    }
}
