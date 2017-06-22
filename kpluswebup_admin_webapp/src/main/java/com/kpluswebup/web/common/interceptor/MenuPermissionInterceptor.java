package com.kpluswebup.web.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpuswebup.comom.util.CachedClient;

public class MenuPermissionInterceptor extends BaseController implements HandlerInterceptor {

    private static Logger log = Logger.getLogger(MenuPermissionInterceptor.class);

    @Autowired
    private CachedClient  cachedClient;

    public MenuPermissionInterceptor(){

    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {}

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
