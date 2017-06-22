package com.kpluswebup.wap.web.alipay.control;


/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”
 */

public class WXAlipayConfig {
    
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner = "";
    
    // 交易安全检验码，由数字和字母组成的32位字符串
    public static String key = "";
    
    // 签约支付宝账号或卖家收款支付宝帐户
    public static String seller_email = "";
    
    // 支付宝服务器通知的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
    // 必须保证其地址能够在互联网中访问的到
    public static String notify_url = "";
    
    // 当前页面跳转后的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
    // 域名不能写成http://localhost/create_direct_pay_by_user_jsp_utf8/return_url.jsp ，否则会导致return_url执行无效
    public static String return_url = "";

    public static String notify_url0 = "";
    public static String return_url0 = "";
    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    

    // 调试用，创建TXT日志路径
    public static String log_path = "";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";
    
    // 签名方式 不需修改
    public static String sign_type = "MD5";
    
    //访问模式,根据自己的服务器是否支持ssl访问，若支持请选择https；若不支持请选择http
    public static String transport = "";

    ///////////////////////////////////
    //是否支付宝测试
    public static  String ALIPAY_TEST="false";
    //是否支付宝代付测试
    public static  String ALIPAY_DAIFU="false";
}


