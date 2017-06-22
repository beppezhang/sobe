package com.kpluswebup.web.common.controller;

import java.io.FileReader;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.vo.OperatorVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.CookieUtil;

public class BaseController {

    @Autowired
    private CachedClient cachedClient;

    public HttpServletResponse getResponse() {
        WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(getRequest());
        ServletWebRequest webRequest = (ServletWebRequest) ReflectionTestUtils.getField(asyncManager, "asyncWebRequest");
        return webRequest.getResponse();
    }

    public HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        return servletRequestAttributes.getRequest();
    }

    public static String domain = null;
    public static String qrurl = null;
    public static String adminStatic = null;//静态资源 图片 css 文件
    public static String mallStatic = null;;//静态资源 图片 css 文件
    static {
        ClassPathResource resource = new ClassPathResource("server.properties");
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(resource.getFile()));
            domain = prop.getProperty("domain");
            qrurl = prop.getProperty("qrurl");
            adminStatic = prop.getProperty("adminStatic");
            mallStatic = prop.getProperty("mallStatic");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public ModelAndView newModelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        String domain=getRequest().getRequestURL().toString();
        domain=domain.substring(0,domain.indexOf("/admin/"));
        modelAndView.addObject("domain", domain);
        modelAndView.addObject("qrurl", qrurl);
        modelAndView.addObject("adminStatic", adminStatic);
        modelAndView.addObject("mallStatic", mallStatic);
        return modelAndView;
    }

    public String getCurrentOperator() {
        CookieUtil cookie = new CookieUtil(this.getRequest(),this.getResponse(), 0);
        String cookieInfo = cookie.getCookieValue(Constant.CURRENT_OPERATOR);
        return cookieInfo;
    }
    
    public OperatorVO getCurrentOperatorInfo(){
    	CookieUtil cookie = new CookieUtil(this.getRequest(),this.getResponse(), 0);
    	String adminInfo = cookie.getCookieValue(Constant.USER_INFO_COOKIE);
    	OperatorVO operator = cachedClient.get(Constant.USERINFO + adminInfo);
    	return operator;
    	
    }
    
}
