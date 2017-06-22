package com.kpluswebup.mall.web.control;

import java.io.FileReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

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

import com.kpluswebup.web.admin.system.service.GeneralService;
import com.kpluswebup.web.content.service.HelpService;
import com.kpluswebup.web.domain.CmsCategoryDTO;
import com.kpluswebup.web.service.ProductCategoryService;
import com.kpluswebup.web.vo.CmsCategoryVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.GeneralVO;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.CookieUtil;

public class BaseController {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private CachedClient           cachedClient;
    @Autowired
    private HelpService            helpService;
    @Autowired
    private GeneralService         generalService;

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
    public static String mallStatic = null;
    public static String adminStatic = null;
    public static String imgurl = null;
    public static String domainImg=null;
    public static Properties staticProp=null;
    static {
        ClassPathResource resource = new ClassPathResource("server.properties");
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(resource.getFile()));
            domain = prop.getProperty("domain");
            imgurl = prop.getProperty("img.url");
            mallStatic = prop.getProperty("mallStatic");
            adminStatic = prop.getProperty("adminStatic");
            domainImg = prop.getProperty("domain.url");
        } catch (Exception e) {
            e.printStackTrace();
        }
        staticProp = prop;
    }
    /**
     * sxc
     * @return
     */
    public static Properties getStaticProp() {
		return staticProp;
	}

	public ModelAndView newModelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productCategoryList", this.findProductCatList());
        modelAndView.addObject("imgurl", imgurl);
        modelAndView.addObject("domainImg", domainImg);
        modelAndView.addObject("domain", domain);
        modelAndView.addObject("mallStatic", mallStatic);
        modelAndView.addObject("adminStatic", adminStatic);
        modelAndView.addObject("user", findUserInfo());
        modelAndView.addObject("supplier", findSupplierInfo());
        modelAndView.addObject("wxuser", findWXUserInfo());
        modelAndView.addObject("helpslist", this.getHelpsList());
        GeneralVO generalVO = generalService.findGeneral();
        modelAndView.addObject("shopLogo", generalVO.getPicUrl());
        modelAndView.addObject("productCategoryRecommendList", this.findProductCatIsRecommendList());
        return modelAndView;
    }

    public String getCurrentOperator() {
        CookieUtil cookie = new CookieUtil(this.getRequest(), this.getResponse(), 0);
        String cookieInfo = cookie.getCookieValue(Constant.CURRENT_OPERATOR);
        return cookieInfo;
    }

    // TODO 这里要加缓存
    public List<ProductCategoryVO> findProductCatList() {
        List<ProductCategoryVO> productCategoryList = productCategoryService.findProductOneLevel();
        return productCategoryList;
    }

    // 推荐产品类目
    public List<ProductCategoryVO> findProductCatIsRecommendList() {
        List<ProductCategoryVO> productCategoryList = productCategoryService.findProductCategoryOneLevel();
        return productCategoryList;
    }

    /**
     * 获取用户登录后的信息
     * 
     * @date 2014年12月18日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CustomerVO findUserInfo() {
        CustomerVO customerVO = null;
        CookieUtil cookie = new CookieUtil(this.getRequest(), this.getResponse(), 0);
        String cookieInfo = cookie.getCookieValue(Constant.USER_MALL_INFO_COOKIE);
        customerVO = cachedClient.get(Constant.MALLUSERINFO + cookieInfo);
        if (customerVO != null) {
            Integer count = cachedClient.get(Constant.SHOPPINGCARTCOUNT + customerVO.getMainID());
            customerVO.setShoppingcartCount(count);
        }
        return customerVO;
    }

    public String getTimeString(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    	return sdf.format(new Date());
    }
    
    public ModelAndView checkLogin(String before_login_url_)
    {
		CustomerVO customerVO = findUserInfo();
		if (customerVO == null) {
			String uuid = getCookieUUID(this.getRequest(), this.getResponse());
			this.cachedClient.set("before_login_url_" + uuid,
					Constant.EXP.intValue(), before_login_url_==null?"/index.htm":before_login_url_);
			return new ModelAndView("redirect:/mall/member/toLogin.htm");
		}    
		return null;
    }
    
    /**
     * @date 2015年5月13日
     * @author moo
     * @return
     * @return SupplierVO
     * @since JDK 1.6
     * @Description 获取供应商信息
     */
    public SupplierVO findSupplierInfo() {
        SupplierVO supplierVO = null;
        CookieUtil cookie = new CookieUtil(this.getRequest(), this.getResponse(), 0);
        String cookieInfo = cookie.getCookieValue(Constant.SUPPLIER_MALL_INFO_COOKIE);
        supplierVO = cachedClient.get(Constant.MALLSUPPLIERINFO + cookieInfo);
        return supplierVO;
    }

    /**
     * 获取微信用户信息
     * 
     * @date 2015年1月6日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CustomerVO findWXUserInfo() {
        CustomerVO customerVO = null;
        CookieUtil cookie = new CookieUtil(this.getRequest(), this.getResponse(), 0);
        String cookieInfo = cookie.getCookieValue(Constant.USER_WEIXIN_INFO_COOKIE);
        customerVO = cachedClient.get(Constant.WEIXINUSERINFO + cookieInfo);
        return customerVO;
    }

    public List<CmsCategoryVO> getHelpsList() {
        CmsCategoryDTO cmsCategoryDTO = new CmsCategoryDTO();
        List<CmsCategoryVO> helpslist = helpService.findHelpCategoryNameList(cmsCategoryDTO);
        cachedClient.set(Constant.HELPLIST, 3600, helpslist);
        return helpslist;
    }

    /**
     * 获取电脑MAC地址
     * 
     * @date 2015年1月21日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public String randomUUID() {
        InetAddress ia = null;
        String osname = System.getProperty("os.name");
        String MACAddress = "";
        if (osname.toLowerCase().startsWith("win")) {
            try {
                // 获取本地IP对象
                ia = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            try {
                MACAddress = getMACAddress(ia);
                MACAddress = MACAddress.replace("-", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Enumeration<NetworkInterface> netInterfaces;
            try {
                netInterfaces = NetworkInterface.getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                    ia = (InetAddress) ni.getInetAddresses().nextElement();
                    if (!ia.isSiteLocalAddress() && !ia.isLoopbackAddress() && ia.getHostAddress().indexOf(":") == -1) {
                        try {
                            MACAddress = getMACAddress(ia);
                            MACAddress = MACAddress.replace("-", "");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (SocketException e1) {
                e1.printStackTrace();
            }
        }
        return MACAddress;
    }

    // 获取MAC地址的方法
    private static String getMACAddress(InetAddress ia) throws Exception {
        // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        // 下面代码是把mac地址拼装成String
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            // mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);
        }
        // 把字符串所有小写字母改为大写成为正规的mac地址并返回
        return sb.toString().toUpperCase();
    }
    
    public String getCookieUUID(HttpServletRequest request, HttpServletResponse response){
    	CookieUtil cookie = new CookieUtil(request, response, -1);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        cookie.addCookie(Constant.UUID, uuid);
        return uuid;
    }
   
}
