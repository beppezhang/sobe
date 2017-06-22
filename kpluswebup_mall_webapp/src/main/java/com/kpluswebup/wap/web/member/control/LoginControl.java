package com.kpluswebup.wap.web.member.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.admin.system.service.AreaService;
import com.kpluswebup.web.admin.system.service.GeneralService;
import com.kpluswebup.web.admin.system.service.InterfaceConfigService;
import com.kpluswebup.web.admin.system.service.SystemCodeService;
import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.domain.CustomerDeliveryAddressDTO;
import com.kpluswebup.web.member.service.CustomerAddressService;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.member.service.ShoppingCartSerivce;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.GeneralVO;
import com.kpluswebup.web.vo.ShoppingCartVO;
import com.kpluswebup.web.vo.SystemCodeVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.CookieUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.Md5Algorithm;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.SendSms;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/weixin")
public class LoginControl extends BaseController {

    private static final Logger    LOGGER = LogManager.getLogger(LoginControl.class);

    @Autowired
    private MemberSerivce          memberSerivce;

    @Autowired
    private HttpServletRequest     request;

    @Autowired
    private CachedClient           cachedClient;

    @Autowired
    private InterfaceConfigService interfaceConfigService;
    @Autowired
    private AreaService            areaService;
    @Autowired
    private SystemCodeService      systemCodeService;
    @Autowired
    private CustomerAddressService customerAddressService;
    @Autowired
    private HttpSession            httpSession;
    
    @Autowired
    private GeneralService         generalService;
    
    @Autowired
    private ShoppingCartSerivce         shoppingCartSerivce;

    /**
     * 用户绑定
     * 
     * @date 2015年1月16日
     * @author liudanqi
     * @return
     * @throws UnsupportedEncodingException
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("bangding")
    public ModelAndView bangding(String param) throws IOException {
        HttpSession session = this.getRequest().getSession();
        session.setAttribute("param", param);
        String appid = interfaceConfigService.findInterfaceParameterValue("Wechat", "AppId");
        String location = domain;
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                     + appid
                     + "&redirect_uri="
                     + URLEncoder.encode(location, "UTF-8")
                     + "%2Fweixin%2FgetWeixinCardInfo.htm&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        LOGGER.info("bangdingUrl:" + url);
        this.getResponse().sendRedirect(url);
        return null;
    }

    @RequestMapping("tobang")
    public ModelAndView tobang() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/login/bangding");
        return modelAndView;
    }

    /**
     * 进入登录页面
     * 
     * @date 2015年1月6日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("toLogin")
    public ModelAndView toLogin() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/login/login");
        return modelAndView;
    }

    /**
     * 登录
     * 
     * @date 2015年1月6日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("doLogin")
    public ModelAndView doLogin(String userName, String password, HttpServletResponse response, String age) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/login/login");
        Boolean isSucess = memberSerivce.isLogin(userName, password);
        if (isSucess == null) {
            modelAndView.addObject("error", "帐号不存在或密码错误!");
            return modelAndView;
        }
        if (!isSucess) {
            modelAndView.addObject("error", "登录失败!");
            return modelAndView;
        }
        if (StringUtil.isEmpty(age) || !StringUtil.isInteger(age)) {
            age = "-1";
        }
        String redirectUrl = "/weixin/userCenter.htm";
        if (isSucess) {
            CookieUtil cookie = new CookieUtil(request, response, Integer.parseInt(age));
            String uid = UUID.randomUUID().toString();
            String cookieInfo = userName + "_" + uid.replaceAll("-", "");
            cookie.addCookie(Constant.USER_WEIXIN_INFO_COOKIE, cookieInfo);
            CustomerVO customerVO = memberSerivce.findCustomerByUserName(userName);
            cachedClient.set(Constant.WEIXINUSERINFO + cookieInfo, Constant.EXP, customerVO);
          
            List<ShoppingCartVO> list = shoppingCartSerivce.findShoppingCart(customerVO.getMainID());
            customerVO.setShoppingcartCount(list.size());
            //cachedClient.set(Constant.SHOPPINGCARTCOUNT + customerVO.getMainID(), Constant.EXP, list.size());
            modelAndView.addObject("user", customerVO);
            
            String before_login_url = cachedClient.get(Constant.BEFORE_WEIXIN_LOGIN_URL);
            if (StringUtil.isNotEmpty(before_login_url)) {
                redirectUrl = before_login_url;
                cachedClient.delete(Constant.BEFORE_WEIXIN_LOGIN_URL);
            }
        }
        return new ModelAndView("redirect:" + redirectUrl);
    }

    /**
     * 保存绑定
     * 
     * @date 2015年1月29日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("dobangding")
    public ModelAndView dobangding(String userName, String password, HttpServletResponse response, String age) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/login/bangding");
        Boolean isSucess = memberSerivce.isLogin(userName, password);
        if (isSucess == null) {
            modelAndView.addObject("error", "帐号不存在或密码错误!");
            return modelAndView;
        }
        if (!isSucess) {
            modelAndView.addObject("error", "帐号不存在或密码错误!");
            return modelAndView;
        }
        if (StringUtil.isEmpty(age) || !StringUtil.isInteger(age)) {
            age = "-1";
        }
        String redirectUrl = "/weixin/userCenter.htm";
        if (isSucess) {
            CookieUtil cookie = new CookieUtil(request, response, Integer.parseInt(age));
            String uid = UUID.randomUUID().toString();
            String cookieInfo = userName + "_" + uid.replaceAll("-", "");
            cookie.addCookie(Constant.USER_WEIXIN_INFO_COOKIE, cookieInfo);
            CustomerVO customerVO = memberSerivce.findCustomerByUserName(userName);
            String openid = (String) this.getRequest().getSession().getAttribute("openid");
            if (StringUtil.isNotEmpty(openid) && StringUtil.isEmpty(customerVO.getOpenid())) {
                CustomerDTO dto = new CustomerDTO();
                dto.setMainID(customerVO.getMainID());
                dto.setOpenid(openid);
                memberSerivce.updateMember(dto);
            } else if (StringUtil.isEmpty(openid)) {
                return new ModelAndView("redirect:bangding.htm");
            } else {
                modelAndView.addObject("error", "改账户已绑定过了，请不要重复绑定!");
                return modelAndView;
            }
            cachedClient.set(Constant.WEIXINUSERINFO + cookieInfo, Constant.EXP, customerVO);
            String before_login_url = cachedClient.get(Constant.BEFORE_WEIXIN_LOGIN_URL);
            if (StringUtil.isNotEmpty(before_login_url)) {
                redirectUrl = before_login_url;
                cachedClient.delete(Constant.BEFORE_WEIXIN_LOGIN_URL);
            }
        }
        return new ModelAndView("redirect:" + redirectUrl);
    }

    /**
     * 退出
     * 
     * @date 2015年1月12日
     * @author liudanqi
     * @param response
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("logout")
    public ModelAndView logout(HttpServletResponse response) {
        CookieUtil cookie = new CookieUtil(request, response, 0);
        String cookieInfo = cookie.getCookieValue(Constant.USER_WEIXIN_INFO_COOKIE);
        if (cookieInfo != null) {
            cachedClient.delete(Constant.WEIXINUSERINFO + cookieInfo);
            cookie.deleteCookie(Constant.USER_WEIXIN_INFO_COOKIE);
        }
        return new ModelAndView("redirect:/weixin/toLogin.htm");
    }

    /**
     * 跳转到注册
     * 
     * @date 2015年1月14日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("toRegister")
    public ModelAndView toRegister(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/login/register1");
        List<AreaVO> provinceList = areaService.getAllProvince();
        modelAndView.addObject("provinceList", provinceList);
        List<AreaVO> cityList = null;
        List<AreaVO> districtList = null;

        if (provinceList != null && provinceList.size() > 0) {
            cityList = areaService.getAreaByParentID(provinceList.get(0).getMainID());
        }
        modelAndView.addObject("cityList", cityList);

        if (cityList != null && cityList.size() > 0) {
            districtList = areaService.getAreaByParentID(cityList.get(0).getMainID());
        }
        modelAndView.addObject("districtList", districtList);
        if (mainID != null) {
            CustomerVO customerVO = memberSerivce.findCustomeByMianId(mainID);
            modelAndView.addObject("customerVO", customerVO);
        }
        return modelAndView;
    }

    /**
     * 注册
     * 
     * @date 2015年1月14日
     * @author liudanqi
     * @param phone
     * @param code
     * @param password
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("register")
    public ModelAndView register(String mobile, String address, String password, String picURL, String provinceID,
                                 String cityID, String districtID,String companyName,String name) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/login/register2");
        CustomerDTO customerDTO = new CustomerDTO();
        if (StringUtil.isNotEmpty(mobile)) {
            customerDTO.setMobile(mobile);
            customerDTO.setUsername(mobile);
        }
        if (StringUtil.isNotEmpty(address)) {
            customerDTO.setAddress(address);
        }
        if (StringUtil.isNotEmpty(password)) {
            customerDTO.setPassword(Md5Algorithm.getInstance().md5Digest(password.getBytes()));
        }
        if (StringUtil.isNotEmpty(picURL)) {
            customerDTO.setPicURL1(picURL);
        }
        if (StringUtil.isNotEmpty(provinceID)) {
            customerDTO.setProvinceID(provinceID);
        }
        if (StringUtil.isNotEmpty(cityID)) {
            customerDTO.setCityID(cityID);
        }
        if (StringUtil.isNotEmpty(districtID)) {
            customerDTO.setDistrictID(districtID);
        }
        if (StringUtil.isNotEmpty(name)) {
            customerDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(companyName)) {
            customerDTO.setCompanyName(companyName);
        }
        customerDTO.setMainID(customerID());
        if (StringUtil.isNotEmpty(customerDTO.getUsername())) {
            memberSerivce.addRegister(customerDTO);
            CustomerDeliveryAddressDTO customerDeliveryAddressDTO = new CustomerDeliveryAddressDTO();
            customerDeliveryAddressDTO.setName(mobile);
            customerDeliveryAddressDTO.setMobile(mobile);
            customerDeliveryAddressDTO.setProvinceID(provinceID);
            customerDeliveryAddressDTO.setCityID(cityID);
            customerDeliveryAddressDTO.setDisctrictID(districtID);
            customerDeliveryAddressDTO.setAddress(address);
            customerDeliveryAddressDTO.setType(0);
            customerDeliveryAddressDTO.setIsDefault(1);
            customerDeliveryAddressDTO.setCustomerID(customerDTO.getMainID());
            customerAddressService.addAddress(customerDeliveryAddressDTO);
        }
        modelAndView.addObject("mainID", customerDTO.getMainID());
        return modelAndView;
    }

    @RequestMapping("register2")
    public ModelAndView register2(String picURL2, String picURL3, String invoice, String mainID) {
        // ModelAndView modelAndView = this.newModelAndView();
        // modelAndView.setViewName("wap/screen/login/register_ok");
        CustomerDTO customerDTO = new CustomerDTO();
        if (StringUtil.isNotEmpty(picURL2)) {
            customerDTO.setPicURL2(picURL2);
        }
        if (StringUtil.isNotEmpty(picURL3)) {
            customerDTO.setPicURL3(picURL3);
        }
        if (StringUtil.isNotEmpty(invoice)) {
            customerDTO.setInvoice(invoice);
        }
        if (StringUtil.isNotEmpty(mainID)) {
            customerDTO.setMainID(mainID);
        }
        memberSerivce.editRegister(customerDTO);
        CustomerVO customerVO = memberSerivce.findCustomeByMianId(mainID);
        // modelAndView.addObject("username", customerVO.getUsername());
        return new ModelAndView("redirect:/weixin/index.htm");
    }

    public String customerID() {
        CodeConfigVO codeConfigVO = systemCodeService.findCodeConfigByID(Constant.CUSTOMERID);
        String flowCode = RandomStringUtils.randomNumeric(9);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String dateFormat = sdf.format(new Date());
        String year = dateFormat.substring(2, 4);
        String result = flowCode;
        if (codeConfigVO != null) {
            result = codeConfigVO.getCodeEx() + result;
        } else {
            List<SystemCodeVO> codeList = systemCodeService.findSystemCode(Constant.CUSTOMERID);
            if (codeList != null && codeList.size() > 0) {
                result = codeList.get(0).getDefaultValue() + result;
            }
        }
        return result;
    }

    /**
     * 发送手机验证码
     * 
     * @date 2015年1月14日
     * @author liudanqi
     * @param phone
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("sendRegisterSms")
    public @ResponseBody
    JsonResult sendRegisterSms(String phone) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date nowdate = new Date();
        String todayDate = sdf.format(nowdate);

        if (StringUtil.isNotEmpty(phone)) {
            String ip=getIP();
            Integer smscodeCount = cachedClient.get(Constant.SMSCODECOUNT + ip + todayDate);
            if (smscodeCount == null) {
                smscodeCount = 0;
            }
            if (smscodeCount < 5) {
                String[] telphone = { phone };
                String s = "";
                while (s.length() < 6)
                    s += (int) (Math.random() * 10);
                String ss = "您的验证码为 :" + s + " 。 欢迎您成为吉利电商会员";
                Integer msg = SendSms.sendSMS(telphone, ss, 5);
                if (msg == 0) {
                    cachedClient.set(Constant.SMSCODE + phone, Constant.EXP, s);
                    cachedClient.set(Constant.SMSCODECOUNT + ip + todayDate, Constant.EXP, smscodeCount + 1);
                    return JsonResult.create();
                }
            } else {
                return new JsonResult(ResultCode.ERROR_SMSCODECOUNT);
            }
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }
    
    public String getIP(){
        String ip = request.getHeader("x-forwarded-for"); 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        } 
        return ip;  
    }

    /**
     * 查找用户是否存在
     * 
     * @date 2015年1月14日
     * @author liudanqi
     * @param phone
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("ajaxCheckUsername")
    public @ResponseBody
    JsonResult ajaxCheckUsername(String phone) {
        try {
            Boolean state = memberSerivce.findCustomeByPhone(phone);
            return new JsonResult(state);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }

    /**
     * 检查验证码是否正确
     * 
     * @date 2015年1月14日
     * @author liudanqi
     * @param code
     * @param phone
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("checkSmsCode")
    public @ResponseBody
    JsonResult checkSmsCode(String code, String phone) {
        String smscode = cachedClient.get(Constant.SMSCODE + phone);
        if (code.equals(smscode)) {
            cachedClient.delete(Constant.SMSCODE);
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("checkValidDateCode")
    public @ResponseBody
    JsonResult checkValidDateCode(String validateCode) {
        String code = (String) httpSession.getAttribute(Constant.RANDOMCODEKEY);
        if (!validateCode.toUpperCase().equals(code.toUpperCase())) {
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }

    /**
     * 进入忘记密码1
     * 
     * @date 2015年1月27日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("toForgetPwd")
    public ModelAndView toForgetPwd() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/login/forget_pwd");
        return modelAndView;
    }

    /**
     * 进入忘记密码2
     * 
     * @date 2015年1月27日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("yzForget")
    public ModelAndView yzForget(String phone) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/login/forget_pwd2");
        if (phone != null) {
            modelAndView.addObject("userName", phone);
        }
        return modelAndView;
    }

    /**
     * 保存新密码
     * 
     * @date 2015年1月27日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("savePwd")
    public ModelAndView savePwd(String username, String password) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("redirect:toLogin.htm");
        CustomerDTO customerDTO = new CustomerDTO();
        if (StringUtil.isNotEmpty(username)) {
            CustomerVO customerVO = memberSerivce.findCustomerByUserName(username);
            if (customerVO != null) {
                customerDTO.setMainID(customerVO.getMainID());
            }
        }
        if (StringUtil.isNotEmpty(password)) {
            customerDTO.setPassword(Md5Algorithm.getInstance().md5Digest(password.getBytes()));
        }
        memberSerivce.updateMember(customerDTO);
        return modelAndView;
    }

    @RequestMapping("announcement")
    public ModelAndView announcement() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/login/announcement");
        GeneralVO generalVO = generalService.findGeneral();
        if (generalVO != null) {
            modelAndView.addObject("announcement", generalVO.getUserAgreement());
        }
        return modelAndView;
    }

}
