package com.kpluswebup.wap.web.alipay.control;

/* *
 * 类名：AlipayConfig功能：基础配置类详细：设置帐户有关信息及返回路径版本：3.3日期：2012-08-10说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供学习和研究支付宝接口使用，只是提供一个参考。提示：如何获取安全校验码和合作身份者ID
 * 1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 * 3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？解决方法：1、检查浏览器配置，不让浏览器做弹框屏蔽设置2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

    // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner        = "";

    // 交易安全检验码，由数字和字母组成的32位字符串
    // 如果签名方式设置为“MD5”时，请设置该参数
    public static String key            = "";

    // 商户的私钥
    // 如果签名方式设置为“0001”时，请设置该参数
    public static String private_key    = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOzsnyQgenC7wb9u09NkPMHP7SF9XIW+5KyOPVB34iQVzYWSqZ9jZLMWtn8Li2DJZ7SIzdxHr+jL8I9hY516RG/M7yxzYymKZqYtOSPvnPPa81nXo7NMZ33zO8FfHfMPxMVgmEJRVNHJbKuxfC2hWOoblSLo7hLEzKkRIXOnqA9dAgMBAAECgYBxTw0hKwNDzRfxJODbtZG1I2sGXD0WneAhgnlidaNKnL7JBgZnexKa797hzbSf2lLlrZO+qO5BSN/IwsvwcZwubEW0hbEmSKIKoAyfQOTQgAz7oZ7zvxWBJNcFCxIOEXzKoLvahgaziypC1/GYXnUVVeO6gsiwMzYqdDbJxNHlRQJBAP450uYaqMJ4M+zUhxNSAu2RUyHteJI9JwePqsfpewro1yBzpnb5daOFJ5+BDEQqg1ARDsKHeGW6sj1DuyXdtS8CQQDuk+NxnR7cO9EV+/2DRcJ0Rs5OlO9UuHjMIJOrglBgcdCnm37iFOiwzkT9ThIZH9UCkuZZfPMwC7f3R3v80rkzAkEAllzI+kQGUI8b1xWUlr7I8I4lp0l3KLd2oCg/EGJt3fP9WTpN2MyO4Zpd5x9CjAbBB9gu7KuHcFPOHCI1TJ4nDQJAfRJQmT4qcIB3U1QgihInJ/f6rd2MPljbEwA9Chut8XGZVlTBRtp5Rj9MkZK9ECjM7aV/VwiXIOfz0JHMITWDWwJBAIcuRfbQmYm03PpQfUlAuujcJufeMZUavSpL+pkwj0BWGsDW2KaiDoThAgqu7xy7kUcJe8+koslNhpHJi6kofYY=";

    // 支付宝的公钥
    // 如果签名方式设置为“0001”时，请设置该参数
    public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTBLaNovHOkS/5ax7SOgaDv46VJIxXVMUhNyBgXbkvhoTMDSB13QqDlI2tN20uc4n6fMk3i57lniMze6z03hWBRF2cC34zreQGb0ZCnFSWtklPlHX54rNlF7jP9Cqi1OSth5jWEQdGN1opuDVS2T3JJuWTwcW3Jyx+ePyST6OTTQIDAQAB";

    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    // 调试用，创建TXT日志文件夹路径
    public static String log_path       = "D:\\";

    // 字符编码格式 目前支持 utf-8
    public static String input_charset  = "utf-8";

    // 签名方式，选择项：0001(RSA)、MD5
    public static String sign_type      = "0001";
    // 无线的产品中，签名方式为rsa时，sign_type需赋值为0001而不是RSA

}
