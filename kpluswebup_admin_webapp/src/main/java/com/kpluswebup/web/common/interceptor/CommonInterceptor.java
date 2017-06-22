package com.kpluswebup.web.common.interceptor;

import java.io.FileReader;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.SystemLogService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.SystemLogDTO;
import com.kpluswebup.web.vo.OperatorVO;
import com.kpluswebup.web.vo.RoleFunctionVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.CookieUtil;
import com.kpuswebup.comom.util.IPRequest;
import com.kpuswebup.comom.util.StringUtil;

public class CommonInterceptor extends BaseController implements HandlerInterceptor {

    private static Logger    log = Logger.getLogger(CommonInterceptor.class);

    @Autowired
    private CachedClient     cachedClient;

    @Autowired
    private SystemLogService systemLogService;
    
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

    public CommonInterceptor(){

    }

    private String mappingURL;

    public void setMappingURL(String mappingURL) {
        this.mappingURL = mappingURL;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        if (!request.getRequestURI().contains("/admin/")) {
            return true;
        }

        if (StringUtil.isEmpty(mappingURL)) {
            response.sendRedirect(domain+"/admin/index.htm");
            return false;
        }
        String[] wurls = mappingURL.split(",");
        if (wurls == null) {
            response.sendRedirect(domain+"/admin/index.htm");
            return false;
        }

        for (String whiteUrl : wurls) {
            if (uri.trim().endsWith(whiteUrl.trim())) {
                return true;
            }
        }

        if (url.contains("index.htm") || url.contains("doLogin.htm")) {
            return true;
        }
        CookieUtil cookie = new CookieUtil(request, response, 0);
        String cookieInfo = cookie.getCookieValue(Constant.USER_INFO_COOKIE);
        if (cookieInfo == null) {
            response.sendRedirect(domain+"/admin/index.htm");
            return false;
        }
        String adminName = cookieInfo.split("_")[0];
        if (StringUtil.isEmpty(adminName)) {
            response.sendRedirect(domain+"/admin/index.htm");
            return false;
        }
        OperatorVO operatorVO = cachedClient.get(Constant.USERINFO + cookieInfo);
        if (operatorVO == null || StringUtil.isEmpty(operatorVO.getName())) {
            response.sendRedirect(domain+"/admin/index.htm");
            return false;
        } else {
            if (operatorVO.getUsername().equals(adminName)) {
                SystemLogDTO systemLogDTO = new SystemLogDTO();
                systemLogDTO.setOperation(request.getRequestURI());
                systemLogDTO.setIP(IPRequest.getIpAddress(request));
                systemLogDTO.setStatus("1");
                systemLogDTO.setUser(adminName);
                systemLogDTO.setMemo(request.getRequestURI());
                systemLogService.insertSystemLog(systemLogDTO);
                List<RoleFunctionVO> menuList = cachedClient.get(Constant.ALLMENUINFO + adminName);
                if (menuList == null) {
                    response.sendRedirect(domain+"/admin/index.htm");
                    return false;
                }
                
                if(url.indexOf("ajax") != -1){
                	return true;
                }
                
                for (RoleFunctionVO roleFunctionVO : menuList) {

                    if (roleFunctionVO.getFunctionURL() != null) {
                        uri = uri.split("\\.")[0].trim();
                        String puri = roleFunctionVO.getFunctionURL().split("\\.")[0].trim();
                        if (uri.endsWith(puri)) {
                            return true;
                        }
                    }
                }
                System.out.println("no permission url=" + url);
                response.sendRedirect(domain+"/admin/permission.htm");
                return false;
            }
        }
        response.sendRedirect(domain+"/admin/index.htm");
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        CookieUtil cookie = new CookieUtil(request, response, 0);
        String cookieInfo = cookie.getCookieValue(Constant.USER_INFO_COOKIE);
        if (cookieInfo != null && modelAndView != null) {
            String userName = cookieInfo.split("_")[0];
            modelAndView.addObject("userName", userName);
            List<RoleFunctionVO> menuList = cachedClient.get(Constant.MENUINFO + userName);
            modelAndView.addObject("menuList", menuList);
            OperatorVO operatorVO = cachedClient.get(Constant.USERINFO + cookieInfo);
            modelAndView.addObject("operatorVO", operatorVO);
            List<RoleFunctionVO> menuAllList = cachedClient.get(Constant.ALLMENUINFO + userName);
            modelAndView.addObject("menuAllList", menuAllList);
        }

 

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
