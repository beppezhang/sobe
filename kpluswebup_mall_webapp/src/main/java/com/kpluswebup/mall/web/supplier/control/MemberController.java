package com.kpluswebup.mall.web.supplier.control;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.admin.system.service.AreaService;
import com.kpluswebup.web.admin.system.service.GeneralService;
import com.kpluswebup.web.admin.system.service.TransConfigService;
import com.kpluswebup.web.domain.SupplierDTO;
import com.kpluswebup.web.supplier.service.SupplierService;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.CookieUtil;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/mall/supplier")
public class MemberController extends BaseController {
	
	@Autowired
	private SupplierService supplierService;
	
    @Autowired
    private HttpServletRequest  request;

    @Autowired
    private CachedClient        cachedClient;

    @Autowired
    private GeneralService      generalService;
    @Autowired
    private TransConfigService      transConfigService;
    @Autowired
    private AreaService areaService;
	/**
	 * 
	 * @date 2015年5月18日
	 * @author moo
	 * @return
	 * @return String
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping(value="/login", method=GET)
	public ModelAndView toLogin(){
	    ModelAndView modelAndView = this.newModelAndView();
	    modelAndView.setViewName("screen/login/login_supplier");
	    return modelAndView;
	}
	
	/**
	 * 
	 * @date 2015年5月18日
	 * @author moo
	 * @param supplier
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping(value="/login", method=POST)
	public ModelAndView doLogin(@ModelAttribute("supplier") SupplierDTO supplier, 
			HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = this.newModelAndView();
		SupplierVO supplierVO=new SupplierVO();
		modelAndView.addObject("logTime", supplierVO.getLastloginTime());		
        modelAndView.setViewName("screen/login/login_supplier");
        Boolean isSucess = supplierService.isLogin(supplier);
        if (isSucess == null) {
            modelAndView.addObject("error", "帐号或密码错误!");
            return modelAndView;
        }
        if (!isSucess) {
            modelAndView.addObject("error", "账号或密码有误!");
            return modelAndView;
        }
        String redirectUrl = "/mall/seller/supplierCenter.htm";
        if (isSucess) {
            CookieUtil cookie = new CookieUtil(request, response, -1);
            String uid = UUID.randomUUID().toString();
            supplierVO = supplierService.findSupplierByUserName(supplier.getUserName());
            String cookieInfo = supplierVO.getMainID() + "_" + uid.replaceAll("-", "");
            cookie.addCookie(Constant.SUPPLIER_MALL_INFO_COOKIE, cookieInfo);
            if(!"e10adc3949ba59abbe56e057f20f883e".equals(supplierVO.getPassWord())){//密碼修改才進去
            	supplierService.updateSupplierLastLogTime(supplier.getUserName(), request.getRemoteAddr());
            }            
//            supplierService.updateSupplierLastLogTime(supplier.getUserName(), request.getRemoteAddr());
            cachedClient.set(Constant.MALLSUPPLIERINFO + cookieInfo, Constant.EXP, supplierVO);
            String before_login_url = cachedClient.get(Constant.SUPPLIER_BEFORE_LOGIN_URL+ randomUUID());
            if (StringUtil.isNotEmpty(before_login_url)) {
                redirectUrl = before_login_url;
                cachedClient.delete(Constant.SUPPLIER_BEFORE_LOGIN_URL+ randomUUID());
            }
        }
        return new ModelAndView("redirect:" + redirectUrl);
	}
	
	/**
	 * 
	 * @date 2015年5月20日
	 * @author moo
	 * @param response
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description 登出
	 */
	@RequestMapping("logout")
	public ModelAndView logout(HttpServletResponse response){
		CookieUtil cookie = new CookieUtil(request, response, 0);
        String cookieInfo = cookie.getCookieValue(Constant.SUPPLIER_MALL_INFO_COOKIE);
        if (cookieInfo != null) {
            cachedClient.delete(Constant.MALLSUPPLIERINFO + cookieInfo);
            cookie.deleteCookie(Constant.SUPPLIER_MALL_INFO_COOKIE);
        }
        return new ModelAndView("redirect:/mall/supplier/login.htm");
		
	}
}
