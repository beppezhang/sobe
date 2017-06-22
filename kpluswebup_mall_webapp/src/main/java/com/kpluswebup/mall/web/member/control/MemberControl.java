package com.kpluswebup.mall.web.member.control;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.admin.system.service.AreaService;
import com.kpluswebup.web.admin.system.service.GeneralService;
import com.kpluswebup.web.admin.system.service.SystemCodeService;
import com.kpluswebup.web.admin.system.service.SystemLogService;
import com.kpluswebup.web.admin.system.service.TransConfigService;
import com.kpluswebup.web.content.service.AdvertService;
import com.kpluswebup.web.domain.CmsAdvertDTO;
import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.domain.CustomerDeliveryAddressDTO;
import com.kpluswebup.web.domain.SystemLogDTO;
import com.kpluswebup.web.member.service.CustomerAddressService;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.member.service.ShoppingCartSerivce;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.CmsAdvertVO;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.GeneralVO;
import com.kpluswebup.web.vo.ShoppingCartVO;
import com.kpluswebup.web.vo.SystemCodeVO;
import com.kpluswebup.web.vo.TransConfigVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.CookieUtil;
import com.kpuswebup.comom.util.IPRequest;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.Md5Algorithm;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.SendSms;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/mall/member")
public class MemberControl extends BaseController {

    @Autowired
    private MemberSerivce          memberSerivce;

    @Autowired
    private HttpSession            httpSession;

    @Autowired
    private HttpServletRequest     request;
    @Autowired
    private SystemLogService systemLogService;
    @Autowired
    private CachedClient           cachedClient;
    @Autowired
    private ShoppingCartSerivce    shoppingCartSerivce;
    @Autowired
    private GeneralService         generalService;
    @Autowired
    private TransConfigService     transConfigService;
    @Autowired
    private AreaService            areaService;
    @Autowired
    private SystemCodeService      systemCodeService;
    @Autowired
    private CustomerAddressService customerAddressService;
    @Autowired
    private AdvertService          advertService;

    /**
     * 跳到注册页
     * 
     * @date 2014年12月22日
     * @author yuanyuan
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("registerPage")
    public ModelAndView addMemberPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/login/register1");

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
     * @date 2014年12月22日
     * @author yuanyuan
     * @param phone
     * @param code
     * @param password
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("register2")
    public ModelAndView register2(String mobile, String address, String companyName, String password, String picURL,
                                  String provinceID, String cityID, String districtID, String name) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/login/register_ok");
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setMainID(customerID());
        if (StringUtil.isNotEmpty(mobile)) {
            customerDTO.setMobile(mobile);
            customerDTO.setUsername(mobile);
        }
        if (StringUtil.isNotEmpty(name)) {
            customerDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(password)) {
            customerDTO.setPassword(Md5Algorithm.getInstance().md5Digest(password.getBytes()));
        }
        /*if (StringUtil.isNotEmpty(address)) {
            customerDTO.setAddress(address);
        }
        if (StringUtil.isNotEmpty(companyName)) {
            customerDTO.setCompanyName(companyName);
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
        }*/
        memberSerivce.addRegister(customerDTO);
        /* if (StringUtil.isNotEmpty(customerDTO.getName())) {
           CustomerDeliveryAddressDTO customerDeliveryAddressDTO = new CustomerDeliveryAddressDTO();
            customerDeliveryAddressDTO.setName(name);
            customerDeliveryAddressDTO.setMobile(mobile);
            customerDeliveryAddressDTO.setProvinceID(provinceID);
            customerDeliveryAddressDTO.setCityID(cityID);
            customerDeliveryAddressDTO.setDisctrictID(districtID);
            customerDeliveryAddressDTO.setAddress(address);
            customerDeliveryAddressDTO.setType(0);
            customerDeliveryAddressDTO.setIsDefault(1);
            customerDeliveryAddressDTO.setCustomerID(customerDTO.getMainID());
            customerAddressService.addAddress(customerDeliveryAddressDTO);
        }*/
       // modelAndView.addObject("mainID", customerDTO.getMainID());
        return modelAndView;
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
     * 注册
     * 
     * @date 2014年5月6日
     * @author meisu
     * @param picURL1
     * @param picURL2
     * @param picURL3
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("registerok")
    public ModelAndView registerok(String picURL2, String picURL3, String invoice, String mainID) {
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
        ModelAndView modelAndView = this.newModelAndView();
        if (StringUtil.isNotEmpty(mainID)) {
            CustomerVO customerVO = memberSerivce.findCustomeByMianId(mainID);
            modelAndView.addObject("username", customerVO.getUsername());
        }
        modelAndView.setViewName("screen/login/register_ok");
        return modelAndView;
    }

    @RequestMapping("sendRegisterSms")
    public @ResponseBody
    JsonResult sendRegisterSms(String phone) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date nowdate = new Date();
        String todayDate = sdf.format(nowdate);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long smscodeMobile = cachedClient.get(Constant.SMSCODEMOBILE + phone);
        String nDate = df.format(nowdate);
        long startT = fromDateStringToLong(nDate);
        if (smscodeMobile == null) {
            cachedClient.set(Constant.SMSCODEMOBILE + phone, Constant.EXP, startT);
        } else {
            long endT = fromDateStringToLong(nDate);
            long ss = (endT - startT) / 1000;
            if (ss > 0) {
                return new JsonResult(ResultCode.ERROR_SMSCODEMOBILE);
            }
        }
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy年MM月dd日");
        if (StringUtil.isNotEmpty(phone)) {
            String ip = getIP();
           /* Integer smscodeCount = cachedClient.get(Constant.SMSCODECOUNT + ip + todayDate);
            if (smscodeCount == null) {
                smscodeCount = 0;
            }*/
           // if (smscodeCount < 5) {
                String[] telphone = { phone };
                String s = "";
                while (s.length() < 6)
                    s += (int) (Math.random() * 10);
                String ss = "您于"+df1.format(new Date())+"申请了黑虎网手机号码注册校验码是" + s;
                Integer msg = SMS.EmsSendTest.sendMsg(ss, phone);
                if (msg == 0) {
                    cachedClient.set(Constant.SMSCODE + phone, Constant.EXP, s);
                    //cachedClient.set(Constant.SMSCODECOUNT + ip + todayDate, Constant.EXP, smscodeCount + 1);
                    return JsonResult.create();
                }
           // } else {
                return new JsonResult(ResultCode.ERROR_SMSCODECOUNT);
            //}
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    public String getIP() {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public long fromDateStringToLong(String inVal) { // 此方法计算时间毫秒
        Date date = null; // 定义时间类型
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = inputFormat.parse(inVal); // 将字符型转换成日期型
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date.getTime(); // 返回毫秒数
    }

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
        if (StringUtil.isNotEmpty(validateCode) && StringUtil.isNotEmpty(code)) {
            if (!validateCode.toUpperCase().equals(code.toUpperCase())) {
                return new JsonResult(ResultCode.ERROR_SYSTEM);
            }
        }
        return JsonResult.create();
    }

    /**
     * 查找会员名是否存在
     * 
     * @date 2014年12月22日
     * @author yuanyuan
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
    @RequestMapping("ajaxCheckName")
    public @ResponseBody
    JsonResult ajaxCheckName(String name) {
        try {
            Boolean state = memberSerivce.findCustomeByName(name);
            return new JsonResult(state);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }

    /**
     * 去登录
     * 
     * @date 2014年12月25日
     * @author yuanyuan
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("toLogin")
    public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response, String errorInfo) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/login/login");
        if ("1000".equals(errorInfo)) {
            modelAndView.addObject("error", "账号或密码有误");
        }
        if ("2000".equals(errorInfo)) {
            modelAndView.addObject("error", "当前账户被锁定");
        }
        if("3000".equals(errorInfo)){
        	  modelAndView.addObject("error", "帐号已被锁定");
        }
        CmsAdvertDTO cms = new CmsAdvertDTO();
        cms.setPosionID("login");
        cms.setPageSize(1L);
        List<CmsAdvertVO> loginAdvert = advertService.findAdvertByPagination(cms);
        if (loginAdvert != null && loginAdvert.size() > 0) {
            modelAndView.addObject("advertPicUrl", loginAdvert.get(0).getPicURL());
        }
        return modelAndView;
    }

    /**
     * 退出
     * 
     * @date 2014年12月30日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("logout")
    public ModelAndView logout(HttpServletResponse response) {
        CookieUtil cookie = new CookieUtil(request, response, 0);
        String cookieInfo = cookie.getCookieValue(Constant.USER_MALL_INFO_COOKIE);
        if (cookieInfo != null) {
            cachedClient.delete(Constant.MALLUSERINFO + cookieInfo);
            cookie.deleteCookie(Constant.USER_MALL_INFO_COOKIE);
        }
        return new ModelAndView("redirect:/mall/member/toLogin.htm");
    }

    /**
     * 登录
     * 
     * @date 2014年12月25日
     * @author yuanyuan
     * @param userName
     * @param password
     * @param response
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping(value = "doLogin")
    public ModelAndView login(String userName, String password, HttpServletResponse response, String age) {
        if (StringUtil.isNotEmpty(userName)) {
            try {
                if(StringUtil.isNotEmpty(userName)&&userName.equals(new String(userName.getBytes("iso-8859-1"), "iso-8859-1")))
                {
                    userName=new String(userName.getBytes("iso-8859-1"),"utf-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        Boolean isSucess = memberSerivce.isLogin(userName, password);
        CustomerVO customerVO1 = memberSerivce.findCustomerByUserName(userName);
        if(null!=customerVO1&&(isSucess == null||isSucess==false)){//存在帐号但是密码不对记录一次错误
        	CustomerDTO customerDTO = new CustomerDTO();
        	Integer loginTimes = customerVO1.getLoginTimes();
        	customerDTO.setLoginTimes(loginTimes+1);
        	if(customerVO1.getLoginTimes()==4){
        		customerDTO.setStatus(2);
        	}
        	customerDTO.setMainID(customerVO1.getMainID());
        	if(loginTimes<=4){
        		memberSerivce.updateMember(customerDTO);
        		return new ModelAndView("redirect:toLogin.htm?errorInfo=1000");
        	}else if(customerVO1.getStatus()==2){
        		return new ModelAndView("redirect:toLogin.htm?errorInfo=3000");
        	}
        }
        if (isSucess == null) {
            return new ModelAndView("redirect:toLogin.htm?errorInfo=1000");
        }
        if (!isSucess) {
            return new ModelAndView("redirect:toLogin.htm?errorInfo=2000");
        }
        if (StringUtil.isEmpty(age) || !StringUtil.isInteger(age)) {
            age = "-1";
        }
        String redirectUrl = "/index.htm";
        if (isSucess) {
            CookieUtil cookie = new CookieUtil(request, response, Integer.parseInt(age));
            String uid = UUID.randomUUID().toString();
            String cookieInfo = customerVO1.getUsername() + "_" + uid.replaceAll("-", "");
            String uuid = cookie.getCookieValue(Constant.UUID);
            cookie.addCookie(Constant.USER_MALL_INFO_COOKIE, cookieInfo);
            CustomerVO customerVO = memberSerivce.findCustomerByUserName(userName);
            cachedClient.set(Constant.MALLUSERINFO + cookieInfo, Constant.EXP, customerVO);
            List<ShoppingCartVO> list = shoppingCartSerivce.findShoppingCart(customerVO.getMainID());
            cachedClient.set(Constant.SHOPPINGCARTCOUNT + customerVO.getMainID(), Constant.EXP, list.size());
            String before_login_url = cachedClient.get(Constant.BEFORE_LOGIN_URL + uuid);
            if (StringUtil.isNotEmpty(before_login_url) && !before_login_url.contains("/mall/supplier/login.htm")) {
                redirectUrl = before_login_url;
                cachedClient.delete(Constant.BEFORE_LOGIN_URL + uuid);
                cookie.deleteCookie(Constant.UUID);
            }
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setMainID(customerVO.getMainID());
        	customerDTO.setLoginTimes(0);
            SystemLogDTO systemLogDTO = new SystemLogDTO();
            systemLogDTO.setOperation(request.getRequestURI());
            systemLogDTO.setIP(IPRequest.getIpAddress(request));
            systemLogDTO.setStatus("1");
            systemLogDTO.setUser(customerVO.getName());
            systemLogDTO.setMemo("登录系统");
            systemLogService.insertSystemLog(systemLogDTO);
        }
        return new ModelAndView("redirect:" + redirectUrl);

    }

    /**
     * 找回密码
     * 
     * @date 2015年1月19日
     * @author yuanyuan
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("forgetPasswd")
    public ModelAndView forgetPasswd() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/login/forget_password");
        return modelAndView;
    }

    /**
     * 获取短信验证码
     * 
     * @date 2015年1月19日
     * @author yuanyuan
     * @param userName
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("forgetPassword1")
    public ModelAndView forgetPassword1(String userName) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/login/forget_password1");
        if (userName != null) {
            modelAndView.addObject("userName", userName);
        }
        return modelAndView;
    }

    /**
     * 发送验证码短信
     * 
     * @date 2015年1月19日
     * @author yuanyuan
     * @param phone
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("sendForgetPasswordSms")
    public @ResponseBody
    JsonResult sendForgetPasswordSms(String phone) {
        if (StringUtil.isNotEmpty(phone)) {
        	SimpleDateFormat df1 = new SimpleDateFormat("yyyy年MM月dd日");
            String s = "";
            while (s.length() < 6)
                s += (int) (Math.random() * 10);
            String ss = "您于"+df1.format(new Date())+"申请了黑虎网找回密码的手机校验码是" + s;
            Integer msg = SMS.EmsSendTest.sendMsg(ss, phone);;
            if (msg == 0) {
                cachedClient.set(Constant.SMSCODE + phone, Constant.EXP, s);
                return JsonResult.create();
            }
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * 修改密码
     * 
     * @date 2015年1月19日
     * @author yuanyuan
     * @param userName
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("forgetPassword2")
    public ModelAndView forgetPassword2(String userName) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/login/forget_password2");
        if (userName != null) {
            modelAndView.addObject("userName", userName);
        }
        return modelAndView;
    }

    /**
     * 保存密码
     * 
     * @date 2015年1月19日
     * @author yuanyuan
     * @param userName
     * @param password
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("forgetPassword3")
    public ModelAndView forgetPassword3(String userName, String password) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/login/forget_password3");
        CustomerDTO customerDTO = new CustomerDTO();
        if (StringUtil.isNotEmpty(userName)) {
            CustomerVO customerVO = memberSerivce.findCustomerByUserName(userName);
            if (customerVO != null) {
                customerDTO.setMainID(customerVO.getMainID());
            }
            SystemLogDTO systemLogDTO = new SystemLogDTO();
            systemLogDTO.setOperation(request.getRequestURI());
            systemLogDTO.setIP(IPRequest.getIpAddress(request));
            systemLogDTO.setStatus("1");
            systemLogDTO.setUser(customerVO.getName());
            systemLogDTO.setMemo("修改密码");
            systemLogService.insertSystemLog(systemLogDTO);
        }
        if (StringUtil.isNotEmpty(password)) {
            customerDTO.setPassword(Md5Algorithm.getInstance().md5Digest(password.getBytes()));
        }
        memberSerivce.updateMember(customerDTO);
        return modelAndView;
    }

    
    
    /**
     * 会员注册协议
     * 
     * @date 2015年1月20日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("announcement")
    public ModelAndView announcement() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/login/announcement");
        GeneralVO generalVO = generalService.findGeneral();
        if (generalVO != null) {
            modelAndView.addObject("announcement", generalVO.getUserAgreement());
        }
        return modelAndView;
    }

}
