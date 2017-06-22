package com.kpluswebup.wap.web.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.admin.system.service.InterfaceConfigService;
import com.kpluswebup.web.admin.system.service.SystemCodeService;
import com.kpluswebup.web.content.service.CmsWechatMenuService;
import com.kpluswebup.web.content.service.CmsWechatReplySetService;
import com.kpluswebup.web.domain.CmsWechatReplySetDTO;
import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.member.service.CustomerGradeSerivce;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.vo.CmsWechatMenuVO;
import com.kpluswebup.web.vo.CmsWechatReplySetVO;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.CustomerGradeVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.SystemCodeVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.CookieUtil;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/weixin")
public class WeiXinControl extends BaseController {
    
    private static final Logger  LOGGER = LogManager.getLogger(WeiXinControl.class);
    
    @Autowired
    private CmsWechatReplySetService cmsWechatReplySetService;
    
    @Autowired
    private ItemService itemService;
    
    @Autowired
    MemberSerivce memberSerivce;
    
    @Autowired
    private CachedClient       cachedClient;
    
    @Autowired
    private InterfaceConfigService interfaceConfigService;
    
    @Autowired
    private CmsWechatMenuService    cmsWechatMenuService;
    
    @Autowired
    private CustomerGradeSerivce    customerGradeSerivce;
    
    @Autowired
    private SystemCodeService systemCodeService;
    
    final String TOKEN = "weixin";
    
    // http客户端
    public static DefaultHttpClient httpclient;
    static {
        httpclient = new DefaultHttpClient(new ThreadSafeClientConnManager());
        httpclient = (DefaultHttpClient) HttpClientConnectionManager
                .getSSLInstance(httpclient); // 接受任何证书的浏览器客户端
    }
    
    @RequestMapping("getSendWeixinInfo")
    public ModelAndView getSendWeixinInfo(){
        HttpServletRequest request = this.getRequest();
        String echostr = request.getParameter("echostr");// 随机字符串
        LOGGER.info("echostr:"+echostr);
        try {
            //如果随机字符串为空即为用户发送的请求
            if(echostr == null || echostr.isEmpty()){
                responseMsg();
            }else{
                if(this.checkSignature()){
                    this.print(echostr);
                }else{
                    this.print("error");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    /**
     * 自动回复内容
     * @date 2015年1月4日
     * @author liudanqi
     * @since JDK 1.6
     * @Description
     */
    @SuppressWarnings("static-access")
    public void responseMsg(){
        HttpServletRequest request = this.getRequest();
        String postStr = null;
        try {
            // 获得客户端此次请求的参数
            request.setCharacterEncoding("UTF-8");
            postStr = this.readStreamParameter(request.getInputStream());
            System.out.println(postStr+"----postStr");
            //postStr="<data><item><ToUserName><![CDATA[gh_6bb5a3498fb6]]></ToUserName><FromUserName><![CDATA[o0onGjo3VFJarCmrh6vVqTo0P2So]]></FromUserName><CreateTime>1392022488</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[佳能]]></Content><MsgId>5978691061456574112</MsgId></item></data>";
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("postStr:"+postStr);
        if(postStr != null && !(postStr.isEmpty())){
            Document document = null;
            try {
                document = DocumentHelper.parseText(postStr);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            if (document == null){
                this.print("");
                return;
            }
            Element root = document.getRootElement();
            String fromUsername = root.elementText("FromUserName");// 发送方帐号（一个OpenID）
            String toUsername = root.elementText("ToUserName");// 开发者微信号
            String keyword = root.elementTextTrim("Content");// 文本消息内容
            String createTime = root.elementTextTrim("CreateTime");// 消息创建时间
            // （整型）
            String msgid = root.elementTextTrim("MsgId");// 消息id，64位整型
            String time = new Date().getTime() + "";
            String event = root.elementTextTrim("Event");// 事件类型
            String eventKey = root.elementTextTrim("EventKey");// 事件KEY值，设置的跳转URL
            LOGGER.info("fromUsername:"+fromUsername);
            LOGGER.info("ToUserName:"+toUsername);
            LOGGER.info("keyword:"+keyword);
            LOGGER.info("event:"+event);
            LOGGER.info("eventKey:"+eventKey);
            if (event != null){
                //关注
                if(event.equals("subscribe")){
                    LOGGER.info("subscribe===============================start");
                    CmsWechatReplySetDTO dto = new CmsWechatReplySetDTO();
                    dto.setSetType(1);
                    List<CmsWechatReplySetVO> list = cmsWechatReplySetService.findWechatReplyByPagination(dto);
                    if (list != null && list.size() > 0){
                        CmsWechatReplySetVO reply = list.get(0);
                        
                        if(reply.getReplyType().equals(1)){     //文本回复
                            String msgType = "text";
                            String textTpl = "<xml>"
                                    + "<ToUserName><![CDATA[%1$s]]></ToUserName>"
                                    + "<FromUserName><![CDATA[%2$s]]></FromUserName>"
                                    + "<CreateTime>%3$s</CreateTime>"
                                    + "<MsgType><![CDATA[%4$s]]></MsgType>"
                                    + "<Content><![CDATA[%5$s]]></Content>"
                                    + "<FuncFlag>0</FuncFlag>" + "</xml>";
                            String resultStr = textTpl.format(textTpl,
                                    fromUsername, toUsername, time, msgType,
                                    reply.getContent());
                            LOGGER.info("resultStr:"+resultStr);
                            this.print(resultStr);
                        }else {         //图文回复
                            String msgType = "news";
                            String items = "", resultStr = "";
                            String title = reply.getTitle();
                            String description = reply.getContent();
                            String picUrl = reply.getPicURL();  //图片
                            String url = "";
                            //1自定义连接 2商品连接
                            if (reply.getLinkType().equals(1)){ 
                                url = reply.getReplyLink();
                            }
                            if (reply.getLinkType().equals(2)){
                                ItemVO item = itemService.findItemByMainID(reply.getReplyLink());
                                if (item != null){
                                    url = domain +"/weixin/productDetail?productID="+item.getProductID();
                                }
                            }
                            items += "<item>" + "<Title><![CDATA[" + title
                                    + "]]></Title>"
                                    + "<Description><![CDATA["
                                    + description + "]]></Description>"
                                    + "<PicUrl><![CDATA[" + picUrl
                                    + "]]></PicUrl>" + "<Url><![CDATA["
                                    + url + "]]></Url>" + "</item>";
                            resultStr = "<xml>" + "<ToUserName><![CDATA["
                                    + fromUsername + "]]></ToUserName>"
                                    + "<FromUserName><![CDATA[" + toUsername
                                    + "]]></FromUserName>" + "<CreateTime>"
                                    + time + "</CreateTime>"
                                    + "<MsgType><![CDATA[" + msgType
                                    + "]]></MsgType>" + "<ArticleCount>"
                                    + 1 + "</ArticleCount>" + "<Articles>"
                                    + items + "</Articles>"
                                    + "<FuncFlag>0</FuncFlag>" + "</xml> ";
                            LOGGER.info("resultStr:"+resultStr);
                            this.print(resultStr);
                            LOGGER.info("subscribe===============================end");
                        }
                    }
                }else if(event.equals("CLICK")){    //图文菜单点击
                    LOGGER.info("CLICK===============================start");
                    CmsWechatMenuVO  menu = cmsWechatMenuService.findWechatMenuByMainID(eventKey);
                    LOGGER.info("menu:"+menu);
                    if (menu != null){
                        String msgType = "news";
                        String items = "", resultStr = "";
                        String title = menu.getTitle();
                        String description = menu.getContent();
                        String picUrl = imgurl + menu.getPicURL();  //图片
                        String url = "";
                        if (menu.getLinkType().equals(1)){ 
                            url = menu.getMenuLink();
                        }
                        if (menu.getLinkType().equals(2)){
                            ItemVO item = itemService.findItemByMainID(menu.getMenuLink());
                            if (item != null){
                                url = domain +"/weixin/productDetail?productID="+item.getProductID();
                            }
                        }
                        items += "<item>" + "<Title><![CDATA["
                                + title + "]]></Title>"
                                + "<Description><![CDATA["
                                + description + "]]></Description>"
                                + "<PicUrl><![CDATA[" + picUrl
                                + "]]></PicUrl>" + "<Url><![CDATA["
                                + url + "]]></Url>" + "</item>";
                        resultStr = "<xml>" + "<ToUserName><![CDATA["
                                + fromUsername + "]]></ToUserName>"
                                + "<FromUserName><![CDATA["
                                + toUsername + "]]></FromUserName>"
                                + "<CreateTime>" + time
                                + "</CreateTime>"
                                + "<MsgType><![CDATA[" + msgType
                                + "]]></MsgType>" + "<ArticleCount>"
                                + 1 + "</ArticleCount>"
                                + "<Articles>" + items + "</Articles>"
                                + "<FuncFlag>0</FuncFlag>" + "</xml> ";
                        LOGGER.info("resultStr:"+resultStr);
                        this.print(resultStr);
                        LOGGER.info("CLICK===============================end");
                    }
                }
              }
                else {//关键词回复
                    if (keyword != null && !(keyword.equals(""))){
                        LOGGER.info("keyword===============================start");
                       CmsWechatReplySetVO weixinKeyword = cmsWechatReplySetService.findWechatByKeyword(keyword);
                       if (weixinKeyword != null){
                           if (weixinKeyword.getReplyType().equals(1)){ //文本回复
                               String textTpl = "<xml>"
                                       + "<ToUserName><![CDATA[%1$s]]></ToUserName>"
                                       + "<FromUserName><![CDATA[%2$s]]></FromUserName>"
                                       + "<CreateTime>%3$s</CreateTime>"
                                       + "<MsgType><![CDATA[%4$s]]></MsgType>"
                                       + "<Content><![CDATA[%5$s]]></Content>"
                                       + "<FuncFlag>0</FuncFlag>" + "</xml>";
                               String msgType = "text";
                               String contentStr = weixinKeyword.getContent();
                               // 使用指定的语言环境、格式字符串和参数返回一个格式化字符串。
                               String resultStr = textTpl.format(textTpl,
                                       fromUsername, toUsername, time, msgType,
                                       contentStr);
                               LOGGER.info("contentStr:"+contentStr);
                               this.print(resultStr);
                           }else{       //图文回复
                               String msgType = "news";
                               String items = "", resultStr = "";
                               String title = weixinKeyword.getTitle();
                               String description = weixinKeyword.getContent();
                               String picUrl = weixinKeyword.getPicURL();  //图片
                               String url = "";
                               //1自定义连接 2商品连接
                               if (weixinKeyword.getLinkType().equals(1)){ 
                                   url = weixinKeyword.getReplyLink();
                               }
                               if (weixinKeyword.getLinkType().equals(2)){
                                   ItemVO item = itemService.findItemByMainID(weixinKeyword.getReplyLink());
                                   if (item != null){
                                       url = domain +"/weixin/productDetail?productID="+item.getProductID();
                                   }
                               }
                               items += "<item>" + "<Title><![CDATA["
                                       + title + "]]></Title>"
                                       + "<Description><![CDATA["
                                       + description + "]]></Description>"
                                       + "<PicUrl><![CDATA[" + picUrl
                                       + "]]></PicUrl>" + "<Url><![CDATA["
                                       + url + "]]></Url>" + "</item>";
                               resultStr = "<xml>" + "<ToUserName><![CDATA["
                                       + fromUsername + "]]></ToUserName>"
                                       + "<FromUserName><![CDATA["
                                       + toUsername + "]]></FromUserName>"
                                       + "<CreateTime>" + time
                                       + "</CreateTime>"
                                       + "<MsgType><![CDATA[" + msgType
                                       + "]]></MsgType>" + "<ArticleCount>"
                                       + 1 + "</ArticleCount>"
                                       + "<Articles>" + items + "</Articles>"
                                       + "<FuncFlag>0</FuncFlag>" + "</xml> ";
                               LOGGER.info("resultStr:"+resultStr);
                               this.print(resultStr);
                               LOGGER.info("keyword===============================end");
                           }
                       }
                    }
                }
            }
        }
    
    
    /**
     * 微信接口验证
     * @date 2015年1月4日
     * @author liudanqi
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     * @since JDK 1.6
     * @Description
     */
    public boolean checkSignature(){
        HttpServletRequest request = this.getRequest();
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String token = TOKEN;
        String[] tmpArr = {token, timestamp, nonce};
        Arrays.sort(tmpArr);
        String tmpStr = this.ArrayToString(tmpArr);
        tmpStr = this.SHA1Encode(tmpStr);
        LOGGER.info("tmpStr:"+tmpStr);
        LOGGER.info("signature:"+signature);
        if (tmpStr.equalsIgnoreCase(signature)){
            return true;
        }else {
            return false;
        }
    }
    
    /**
     * 向请求端发送返回数据
     * @date 2015年1月4日
     * @author liudanqi
     * @param content
     * @since JDK 1.6
     * @Description
     */
    public void print(String content){
        HttpServletResponse response = getResponse();
        try {
            response.getWriter().print(content);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 // 数组转字符串
    public String ArrayToString(String[] arr) {
        StringBuffer bf = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            bf.append(arr[i]);
        }
        return bf.toString();
    }
    
 // sha1加密
    public String SHA1Encode(String sourceString) {
        String resultString = null;
        try {
            resultString = new String(sourceString);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            resultString = byte2hexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }
    
    public final String byte2hexString(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString().toUpperCase();
    }
    
    /**
     * 从输入流读取参数
     * @date 2015年1月4日
     * @author liudanqi
     * @param in
     * @return
     * @since JDK 1.6
     * @Description
     */
    public String readStreamParameter(ServletInputStream in) {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }
    
    
 // 授权微信
    @RequestMapping("getWeixinCardInfo")
    public ModelAndView getWeixinCardInfo(HttpServletRequest request) {
        ModelAndView modelAndView = this.newModelAndView();
        try {
            String code = this.getRequest().getParameter("code");
            LOGGER.info("code:"+code);
            System.out.println(code + ".......code");
            if (code.equals("authdeny")) {
                modelAndView.setViewName("redirect:/weixin/toLogin.htm");
                return modelAndView;
            } else {
                String appid = interfaceConfigService.findInterfaceParameterValue("Wechat", "AppId");
                String appsecret = interfaceConfigService.findInterfaceParameterValue("Wechat", "AppSecret");
                LOGGER.info("appid:"+appid);
                LOGGER.info("appsecret:"+appsecret);
                HttpGet get = HttpClientConnectionManager
                        .getGetMethod("https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                                + appid
                                + "&secret="
                                + appsecret
                                + "&code="
                                + code + "&grant_type=authorization_code");
                HttpResponse response = httpclient.execute(get);
                String jsonStr = EntityUtils.toString(response.getEntity(),
                        "utf-8");
                LOGGER.info("jsonStr:"+jsonStr);
                JSONObject object = JSONObject.fromObject(jsonStr);
                LOGGER.info("object:"+object);
                String refresh_token = object.getString("refresh_token");
                LOGGER.info("refresh_token:"+refresh_token);
                HttpGet get1 = HttpClientConnectionManager
                        .getGetMethod("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="
                                + appid
                                + "&grant_type=refresh_token&refresh_token="
                                + refresh_token);
                HttpResponse response1 = httpclient.execute(get1);
                String jsonStr1 = EntityUtils.toString(response1.getEntity(),
                        "utf-8");
                LOGGER.info("jsonStr1:"+jsonStr1);
                JSONObject object1 = JSONObject.fromObject(jsonStr1);
                String access_token = object1.getString("access_token");
                LOGGER.info("access_token:"+access_token);
                String openid = object1.getString("openid");
                LOGGER.info("openid:"+openid);
                /*Cookie mycookie = new Cookie(Constants.openid, openid);
                this.getResponse().addCookie(mycookie);*/
                HttpGet get2 = HttpClientConnectionManager
                        .getGetMethod("https://api.weixin.qq.com/sns/userinfo?access_token="
                                + access_token
                                + "&openid="
                                + openid
                                + "&lang=zh_CN");
                HttpResponse response2 = httpclient.execute(get2);
                String jsonStr2 = EntityUtils.toString(response2.getEntity(),
                        "utf-8");
                JSONObject object2 = JSONObject.fromObject(jsonStr2);
                String newopenid = object2.getString("openid");
                String nickname = object2.getString("nickname");
                String headimgurl = object2.getString("headimgurl");
                CustomerVO customer = memberSerivce.findCustomerByOpenID(openid);
                LOGGER.info("customer======="+customer);
                HttpSession session = this.getRequest().getSession();
                if (session.getAttribute("openid") == null){
                    session.setAttribute("openid", openid);
                }
                LOGGER.info("session:openid:"+session.getAttribute("openid"));
                System.out.println("openid======="+openid);
                String param = (String) session.getAttribute("param");
                LOGGER.info("param:"+param);
                if (StringUtil.isNotEmpty(param)){         //微信登录
                    if (customer == null){      //没绑定注册个新账号
                        CustomerDTO dto = new CustomerDTO();
                        dto.setMainID(customerID());
                        dto.setUsername(nickname);
                        CustomerGradeVO customerGradeVO = customerGradeSerivce.findCustomerGradeTypedefault();
                        if (customerGradeVO != null) {
                            dto.setGradeID(customerGradeVO.getMainID());
                        }
                        dto.setOpenid(newopenid);
                        dto.setNick(nickname);
                        dto.setLastLoginTime(new Date());
                        memberSerivce.insertCustomer(dto);
                        CustomerVO newcustomer = memberSerivce.findCustomerByOpenID(openid);
                        LOGGER.info("newcustomer:"+newcustomer);
                        CookieUtil cookie = new CookieUtil(this.getRequest(), this.getResponse(), -1);
                        String uid = UUID.randomUUID().toString();
                        String cookieInfo = openid + "_" + uid.replaceAll("-", "");
                        cookie.addCookie(Constant.USER_WEIXIN_INFO_COOKIE, cookieInfo);
                        cachedClient.set(Constant.WEIXINUSERINFO + cookieInfo, Constant.EXP, newcustomer);
                        String before_login_url = cachedClient.get(Constant.BEFORE_WEIXIN_LOGIN_URL);
                        cachedClient.delete(Constant.BEFORE_WEIXIN_LOGIN_URL);
                        if (StringUtil.isNotEmpty(before_login_url)){
                            modelAndView.setViewName("redirect:"+before_login_url);
                        }else {
                            modelAndView.setViewName("redirect:/weixin/userCenter.htm");
                        }
                    }
                    else {
                        CookieUtil cookie = new CookieUtil(this.getRequest(), this.getResponse(), -1);
                        String uid = UUID.randomUUID().toString();
                        String cookieInfo = customer.getUsername() + "_" + uid.replaceAll("-", "");
                        cookie.addCookie(Constant.USER_WEIXIN_INFO_COOKIE, cookieInfo);
                        cachedClient.set(Constant.WEIXINUSERINFO + cookieInfo, Constant.EXP, customer);
                        String before_login_url = cachedClient.get(Constant.BEFORE_WEIXIN_LOGIN_URL);
                        cachedClient.delete(Constant.BEFORE_WEIXIN_LOGIN_URL);
                        if (StringUtil.isNotEmpty(before_login_url)){
                            modelAndView.setViewName("redirect:"+before_login_url);
                        }else {
                            modelAndView.setViewName("redirect:/weixin/userCenter.htm");
                        }
                    }
                } else {
                    if (customer == null) { //为空则为没绑定，跳转绑定
                        modelAndView.setViewName("redirect:/weixin/tobang.htm");
                        return modelAndView;
                    } else {
                        CookieUtil cookie = new CookieUtil(this.getRequest(), this.getResponse(), 1209600);//两周免登录
                        String uid = UUID.randomUUID().toString();
                        String cookieInfo = customer.getUsername() + "_" + uid.replaceAll("-", "");
                        cookie.addCookie(Constant.USER_WEIXIN_INFO_COOKIE, cookieInfo);
                        cachedClient.set(Constant.WEIXINUSERINFO + cookieInfo, Constant.EXP, customer);
                        String before_login_url = cachedClient.get(Constant.BEFORE_WEIXIN_LOGIN_URL);
                        cachedClient.delete(Constant.BEFORE_WEIXIN_LOGIN_URL);
                        if (StringUtil.isNotEmpty(before_login_url)){
                            modelAndView.setViewName("redirect:"+before_login_url);
                        }else {
                            modelAndView.setViewName("redirect:/weixin/userCenter.htm");
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.info("getWeixinCardInfo================"+e);
        }
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
    
    private void downloadImage(String url, String savePath) throws ClientProtocolException, IOException {
        HttpGet httpget = new HttpGet();
        httpget.setURI(URI.create(url));
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            byte[] byteArray = EntityUtils.toByteArray(entity);
            File file = new File(savePath);
            System.out.println(savePath);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(byteArray);
            fos.flush();
            fos.close();
        }
    }


}
