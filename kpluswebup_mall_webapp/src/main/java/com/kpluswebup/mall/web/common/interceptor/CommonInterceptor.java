package com.kpluswebup.mall.web.common.interceptor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import bz.sunlight.web.HttpConstant;
import bz.sunlight.web.HttpUtils;

import com.kpluswebup.wap.web.alipay.control.AlipayCore;
import com.kpluswebup.web.admin.system.service.SystemLogService;
import com.kpluswebup.web.domain.SystemLogDTO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.CookieUtil;
import com.kpuswebup.comom.util.IPRequest;
import com.kpuswebup.comom.util.StringUtil;

public class CommonInterceptor implements HandlerInterceptor {

    private static Logger    log = Logger.getLogger(CommonInterceptor.class);

    @Autowired
    private CachedClient     cachedClient;

    @Autowired
    private SystemLogService systemLogService;

    public CommonInterceptor(){

    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        if("/".equals(uri))
        {
        	response.sendRedirect("/index.htm");
        	return false;
        }
        if(uri.contains("/v1/") && !uri.contains("/v1/auth/login") && !uri.contains("/v1/auth/refresh"))
        {
        	//拦截移动端服务
        	String token = request.getHeader(HttpConstant.ACCESSTOKEN);
        	if(token == null || "".equals(token))
        	{
        		//cookie
                CookieUtil cookie = new CookieUtil(request, response, 0);
                token = cookie.getCookieValue(HttpConstant.COOKIE_NAME);
        	}
        	if(!HttpUtils.validateToken(token))
        	{
        		HttpUtils.write(response, HttpConstant.API_MSG_403, HttpConstant.CODE_403);
        		return false;
        	}
        }
        uri += "?";
        @SuppressWarnings("unchecked")
		Map<String, String[]> zzMap = request.getParameterMap();
		if (zzMap != null) {
			for (String s : zzMap.keySet()) {
				String[] value = zzMap.get(s);
				for (String val : value) {
					uri = uri + s + "=" + val + "&";
				}
			}
		}

        String header = request.getHeader("user-agent").toLowerCase();
        boolean isWeiXin = false; // 是否为微信端
        if (header.indexOf("micromessenger") > 0) {// 是微信浏览器
            isWeiXin = true;
        }
        if (!uri.contains("/weixin")) {
            if (uri.contains("/mall/buyer/")/*  || uri.contains("/mall/helps/")*/) {//index.htm
                CookieUtil cookie1 = new CookieUtil(request, response, -1);
                String cookieInfo1 = cookie1.getCookieValue(Constant.USER_MALL_INFO_COOKIE);
               
                if (cookieInfo1 == null) {
                	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                	cookie1.addCookie(Constant.UUID, uuid);
                    if (Constant.BEFORE_LOGIN_URL.contains("/mall/supplier/login.htm")) {
                        cachedClient.set(Constant.BEFORE_LOGIN_URL + uuid, Constant.EXP, "/index.htm");
                    } else if (Constant.BEFORE_LOGIN_URL.contains("/mall/helps/helpsDetail.htm")) {
                        cachedClient.set(Constant.BEFORE_LOGIN_URL, Constant.EXP, "/index.htm");
                    } else {
                        cachedClient.set(Constant.BEFORE_LOGIN_URL + uuid, Constant.EXP, uri);
                    }
                    response.sendRedirect("/mall/member/toLogin.htm");
                    return false;
                }
                CustomerVO customerVO1 = cachedClient.get(Constant.MALLUSERINFO + cookieInfo1);
                if (customerVO1 == null) {
                	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                	cookie1.addCookie(Constant.UUID, uuid);
                    if (Constant.BEFORE_LOGIN_URL.contains("/mall/supplier/login.htm")) {
                        cachedClient.set(Constant.BEFORE_LOGIN_URL + uuid, Constant.EXP, "/index.htm");
                    } else if (Constant.BEFORE_LOGIN_URL.contains("/mall/helps/helpsDetail.htm")) {
                        cachedClient.set(Constant.BEFORE_LOGIN_URL, Constant.EXP, "/index.htm");
                    } else {
                        cachedClient.set(Constant.BEFORE_LOGIN_URL + uuid, Constant.EXP, uri);
                    }
                    response.sendRedirect("/mall/member/toLogin.htm");
                    return false;
                }else{
                	 SystemLogDTO systemLogDTO = new SystemLogDTO();
                     systemLogDTO.setOperation(request.getRequestURI());
                     systemLogDTO.setIP(IPRequest.getIpAddress(request));
                     systemLogDTO.setStatus("1");
                     systemLogDTO.setUser(customerVO1.getName());
                     systemLogDTO.setMemo(request.getRequestURI());
                     systemLogService.insertSystemLog(systemLogDTO);
                }
                return true;

            }
        }
        if (uri.contains("/weixin/user")) {

            // 获取请求参数
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
            String reqParas = AlipayCore.createLinkString(params);
            // 请求url
            String url = uri + "?" + reqParas;

            CookieUtil cookie = new CookieUtil(request, response, 0);
            String cookieInfo = cookie.getCookieValue(Constant.USER_WEIXIN_INFO_COOKIE);

            if (cookieInfo == null) {
                String beforeUrl = cachedClient.get(Constant.BEFORE_WEIXIN_LOGIN_URL);
                if (StringUtil.isNotEmpty(beforeUrl)) {
                    cachedClient.delete(Constant.BEFORE_WEIXIN_LOGIN_URL);
                    cachedClient.set(Constant.BEFORE_WEIXIN_LOGIN_URL, Constant.EXP, url);
                } else {
                    cachedClient.set(Constant.BEFORE_WEIXIN_LOGIN_URL, Constant.EXP, url);
                }
                if (isWeiXin) {
                    response.sendRedirect("/weixin/bangding.htm");
                } else {
                    response.sendRedirect("/weixin/toLogin.htm");
                }
                return false;
            }
            CustomerVO customerVO = cachedClient.get(Constant.WEIXINUSERINFO + cookieInfo);
            if (customerVO == null) {
                String beforeUrl = cachedClient.get(Constant.BEFORE_WEIXIN_LOGIN_URL);
                if (StringUtil.isNotEmpty(beforeUrl)) {
                    cachedClient.delete(Constant.BEFORE_WEIXIN_LOGIN_URL);
                    cachedClient.set(Constant.BEFORE_WEIXIN_LOGIN_URL, Constant.EXP, url);
                }
                if (isWeiXin) {
                    response.sendRedirect("/weixin/bangding.htm");
                } else {
                    response.sendRedirect("/weixin/toLogin.htm");
                }
                return false;
            }else{
               	 SystemLogDTO systemLogDTO = new SystemLogDTO();
                    systemLogDTO.setOperation(request.getRequestURI());
                    systemLogDTO.setIP(IPRequest.getIpAddress(request));
                    systemLogDTO.setStatus("1");
                    systemLogDTO.setUser(customerVO.getName());
                    systemLogDTO.setMemo(request.getRequestURI());
                    systemLogService.insertSystemLog(systemLogDTO);
            }
            return true;
        }

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
