package com.kpluswebup.web.controller.system;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.AdminSystemService;
import com.kpluswebup.web.admin.system.service.OperatorService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.vo.AdminMainVO;
import com.kpluswebup.web.vo.OperatorVO;
import com.kpluswebup.web.vo.StatisticsInfoVO;
import com.kpluswebup.web.vo.StatisticsOrderStstusVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.CookieUtil;

@Controller
@RequestMapping("/admin/")
public class AdminUserController extends BaseController {

    @Autowired
    private OperatorService    operatorService;

    @Autowired
    private HttpSession        httpSession;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CachedClient       cachedClient;

    @Autowired
    private AdminSystemService adminSystemService;

    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    public ModelAndView login(String userName, String password, String validateCode, HttpServletResponse response) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("login/login");
        String code = (String) httpSession.getAttribute(Constant.RANDOMCODEKEY);
        /*if (!validateCode.toUpperCase().equals(code.toUpperCase())) {
            modelAndView.addObject("error", "验证码错误!");
            return modelAndView;
        }*/
        OperatorVO operatorVO=new OperatorVO();
       
		Boolean isSucess = operatorService.isLogin(userName, password, validateCode );
        if (isSucess == null) {
            modelAndView.addObject("error", "帐号或密码错误!");
            return modelAndView;
        }
        if (!isSucess) {
            modelAndView.addObject("error", "登录失败!");
            return modelAndView;
        }
       
        if (isSucess) {
            CookieUtil cookie = new CookieUtil(request, response, -1);
            String uid = UUID.randomUUID().toString();
            String admininfo = userName + "_" + uid.replaceAll("-", "");
             operatorVO = operatorService.findOperatorByUserName(userName);
            cachedClient.set(Constant.USERINFO + admininfo, Constant.EXP, operatorVO);
            cookie.addCookie(Constant.USER_INFO_COOKIE, admininfo);
            cookie.addCookie(Constant.CURRENT_OPERATOR, operatorVO.getMainID());
        }

        return new ModelAndView("redirect:main.htm");
    }

    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("login/login");
        return modelAndView;
    }

    @RequestMapping("permission")
    public ModelAndView permission() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("errors/permission");
        return modelAndView;
    }

    @RequestMapping("exitLogin")
    public ModelAndView exitLogin(HttpServletResponse response) {
        CookieUtil cookie = new CookieUtil(request, response, 0);
        String cookieInfo = cookie.getCookieValue(Constant.USER_INFO_COOKIE);
        if (cookieInfo != null) {
            String adminName = cookieInfo.split("_")[0];
            cachedClient.delete(Constant.USERINFO + adminName);
            cachedClient.delete(Constant.MENUINFO + adminName);
        }
        cookie.deleteCookie(Constant.USER_INFO_COOKIE);
        return new ModelAndView("redirect:index.htm");
    }

    /**
     * 登陆后的首页
     * 
     * @date 2014年12月1日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("main")
    public ModelAndView main() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/main");
        AdminMainVO adminMainVO = adminSystemService.findAdminMain();
        modelAndView.addObject("adminMainVO", adminMainVO);
        StatisticsInfoVO statisticsInfoVO = adminSystemService.findStatisticsInfo();
        modelAndView.addObject("statisticsInfoVO", statisticsInfoVO);
        StatisticsOrderStstusVO statisticsOrderStstusVO = adminSystemService.findStatisticsOrderStstus();
        modelAndView.addObject("statisticsOrderStstusVO", statisticsOrderStstusVO);
        return modelAndView;
    }
}
