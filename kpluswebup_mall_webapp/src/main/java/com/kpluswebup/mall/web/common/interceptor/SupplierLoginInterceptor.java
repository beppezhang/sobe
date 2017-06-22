package com.kpluswebup.mall.web.common.interceptor;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.wap.web.alipay.control.AlipayCore;
import com.kpluswebup.web.admin.system.service.SystemLogService;
import com.kpluswebup.web.supplier.service.SupplierService;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.CookieUtil;
import com.kpuswebup.comom.util.StringUtil;

public class SupplierLoginInterceptor implements HandlerInterceptor {

    private static Logger    log = Logger.getLogger(SupplierLoginInterceptor.class);

    @Autowired
    private CachedClient     cachedClient;

    @Autowired
    private SupplierService  supplierService;

    @Autowired
    private SystemLogService systemLogService;

    public SupplierLoginInterceptor(){

    }
    public static String domain = null;
    static {
        ClassPathResource resource = new ClassPathResource("server.properties");
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(resource.getFile()));
            domain = prop.getProperty("domain");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();

        /*
         * String header = request.getHeader("user-agent").toLowerCase(); boolean isWeiXin = false; //是否为微信端 if
         * (header.indexOf("micromessenger") > 0) {// 是微信浏览器 isWeiXin = true; }
         */

        if (uri.contains("/mall/seller/") && !uri.contains("/showSupplier.htm")) {
            CookieUtil cookie = new CookieUtil(request, response, 0);
            String cookieInfo = cookie.getCookieValue(Constant.SUPPLIER_MALL_INFO_COOKIE);
            if (cookieInfo == null) {
                /*
                 * String username = "chuanqi"; cookie = new CookieUtil(request, response, 1000); String uid =
                 * UUID.randomUUID().toString(); cookieInfo = username + "_" + uid.replaceAll("-", "");
                 * cookie.addCookie(Constant.SUPPLIER_MALL_INFO_COOKIE, cookieInfo); SupplierVO supplierVO =
                 * supplierService.findSupplierByUserName(username); cachedClient.set(Constant.MALLSUPPLIERINFO +
                 * cookieInfo, Constant.EXP, supplierVO);
                 */
                if (Constant.BEFORE_LOGIN_URL.contains("/mall/supplier/login.htm")) {
                    cachedClient.set(Constant.SUPPLIER_BEFORE_LOGIN_URL, Constant.EXP, "/mall/seller/supplierCenter.htm");
                } else {
                    cachedClient.set(Constant.SUPPLIER_BEFORE_LOGIN_URL, Constant.EXP, uri);
                }
                response.sendRedirect(domain+"/mall/supplier/login.htm");
                return false;
            }
            SupplierVO supplierVO = cachedClient.get(Constant.MALLSUPPLIERINFO + cookieInfo);
            if (supplierVO == null) {
                if (Constant.BEFORE_LOGIN_URL.contains("/mall/supplier/login.htm")) {
                    cachedClient.set(Constant.SUPPLIER_BEFORE_LOGIN_URL, Constant.EXP, "/mall/seller/supplierCenter.htm");
                } else {
                    cachedClient.set(Constant.SUPPLIER_BEFORE_LOGIN_URL, Constant.EXP, uri);
                }
                response.sendRedirect(domain+"/mall/supplier/login.htm");
                return false;
            }
            return true;

        }
        /*
         * if (uri.contains("/weixin/user")) { //获取请求参数 Map<String, String> params = new HashMap<String, String>(); Map
         * requestParams = request.getParameterMap(); for (Iterator iter = requestParams.keySet().iterator();
         * iter.hasNext();) { String name = (String) iter.next(); String[] values = (String[]) requestParams.get(name);
         * String valueStr = ""; for (int i = 0; i < values.length; i++) { valueStr = (i == values.length - 1) ?
         * valueStr + values[i] : valueStr + values[i] + ","; } params.put(name, valueStr); } String reqParas =
         * AlipayCore.createLinkString(params); //请求url String url = uri + "?"+ reqParas; CookieUtil cookie = new
         * CookieUtil(request, response, 0); String cookieInfo =
         * cookie.getCookieValue(Constant.USER_WEIXIN_INFO_COOKIE); if (cookieInfo == null) { String beforeUrl =
         * cachedClient.get(Constant.BEFORE_WEIXIN_LOGIN_URL); if (StringUtil.isNotEmpty(beforeUrl)){
         * cachedClient.delete(Constant.BEFORE_WEIXIN_LOGIN_URL); cachedClient.set(Constant.BEFORE_WEIXIN_LOGIN_URL,
         * Constant.EXP, url); }else { cachedClient.set(Constant.BEFORE_WEIXIN_LOGIN_URL, Constant.EXP, url); } if
         * (isWeiXin){ response.sendRedirect("/weixin/bangding.htm"); } else {
         * response.sendRedirect("/weixin/toLogin.htm"); } return false; } CustomerVO customerVO =
         * cachedClient.get(Constant.WEIXINUSERINFO + cookieInfo); if (customerVO == null) { String beforeUrl =
         * cachedClient.get(Constant.BEFORE_WEIXIN_LOGIN_URL); if (StringUtil.isNotEmpty(beforeUrl)){
         * cachedClient.delete(Constant.BEFORE_WEIXIN_LOGIN_URL); cachedClient.set(Constant.BEFORE_WEIXIN_LOGIN_URL,
         * Constant.EXP, url); } if (isWeiXin){ response.sendRedirect("/weixin/bangding.htm"); }else {
         * response.sendRedirect("/weixin/toLogin.htm"); } return false; } return true; }
         */

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        // CustomerVO customerVO = null;
        // CookieUtil cookie = new CookieUtil(request, response, 0);
        // String cookieInfo = cookie.getCookieValue(Constant.USER_MALL_INFO_COOKIE);
        // customerVO = cachedClient.get(cookieInfo);
        // customerVO = new CustomerVO();
        // customerVO.setMainID("1002");
        // customerVO.setName("name");
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
                                                                                                                       throws Exception {

        if (ex != null) {
            System.err.print(ex.getMessage());
            log.error("error ", ex);
        }
    }

}
