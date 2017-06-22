package com.kpluswebup.web.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kpluswebup.web.admin.system.service.InterfaceConfigService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.WechatMessageDTO;
import com.kpluswebup.web.member.service.CustomerGroupSerivce;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.member.service.WechatMessageService;
import com.kpluswebup.web.member.service.WechatUserService;
import com.kpluswebup.web.vo.InterfaceConfigVO;
import com.kpluswebup.web.vo.WechatMessageVO;
import com.kpluswebup.web.vo.WechatUserVO;
import com.kpluswebup.web.wechat.HttpClientConnectionManager;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/member")
public class WechatMessageController extends BaseController {

    @Autowired
    private WechatMessageService    wechatMessageService;

    @Autowired
    private InterfaceConfigService  interfaceConfigService;

    @Autowired
    private CustomerGroupSerivce    customerGroupSerivce;
    @Autowired
    private MemberSerivce           memberSerivce;
    @Autowired
    private WechatUserService       wechatUserService;

    // http客户端
    public static DefaultHttpClient httpclient;

    static {
        httpclient = new DefaultHttpClient();
        httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient); // 接受任何证书的浏览器客户端
    }

    @RequestMapping("wechatMessageList")
    public ModelAndView wechatMessageList(WechatMessageDTO wechatMessageDTO, String searchCustomerName,
                                          String searchWechatNick, String searchSendType, String searchStatus) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/wechatmessage_list");
        if (StringUtil.isNotEmpty(searchCustomerName)) {
            wechatMessageDTO.setCustomerName(searchCustomerName);
        }
        if (StringUtil.isNotEmpty(searchWechatNick)) {
            wechatMessageDTO.setWechatNick(searchWechatNick);
        }
        if (StringUtil.isNotEmpty(searchSendType)) {
            wechatMessageDTO.setSendType(Integer.valueOf(searchSendType));
        }
        if (StringUtil.isNotEmpty(searchStatus)) {
            wechatMessageDTO.setStatus(Integer.valueOf(searchStatus));
        }
        List<WechatMessageVO> list = wechatMessageService.findWechatMessageByPagination(wechatMessageDTO);
        modelAndView.addObject("wechatMessageList", list);
        modelAndView.addObject("wechatMessageDTO", wechatMessageDTO);
        return modelAndView;
    }

    @RequestMapping("addWechatMessagePage")
    public ModelAndView addWechatMessagePage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/wechatmessage_add");
        List<InterfaceConfigVO> list = interfaceConfigService.findInterfaceConfigByType(7);
        modelAndView.addObject("interfaceConfigList", list);
        return modelAndView;
    }

    @RequestMapping("addWechatMessage")
    public ModelAndView addWechatMessage(HttpServletRequest request, String mainID, String interfaceConfigID,
                                         String content) {
        WechatMessageDTO wechatMessageDTO = new WechatMessageDTO();
        if (StringUtil.isNotEmpty(mainID)) {
            WechatMessageVO wechatMessageVO = wechatMessageService.findWechatMessageByMainID(mainID);
            wechatMessageDTO.setCustomerID(wechatMessageVO.getCustomerID());
            wechatMessageDTO.setInterfaceConfigID(wechatMessageVO.getInterfaceConfigID());
            wechatMessageDTO.setContent(content);
            wechatMessageDTO.setSendType(2);
            wechatMessageDTO.setWechatMessageID(mainID);
            wechatMessageDTO.setMainID(UUIDUtil.getUUID());
            wechatMessageDTO.setOpenID(wechatMessageVO.getOpenID());
            try {
                InterfaceConfigVO interfaceConfigVO = interfaceConfigService.findInterfaceConfigByMainID(wechatMessageVO.getInterfaceConfigID());
                String appid = interfaceConfigService.findInterfaceConfigParameterValue(interfaceConfigVO.getMainID(),
                                                                                        "AppId");
                String appsecret = interfaceConfigService.findInterfaceConfigParameterValue(interfaceConfigVO.getMainID(),
                                                                                            "AppSecret");
                String accessToken = getAccessToken(appid, appsecret);
                String ss = "{\"touser\":\"" + wechatMessageVO.getOpenID()
                            + "\",\"msgtype\":\"text\",\"text\":{\"content\":\"" + wechatMessageDTO.getContent()
                            + "\"}}";
                String s = ss.toString();
                String res = getSendMessage(s, accessToken);
                System.out.println(res);
                if (res.equals("ok")) {
                    wechatMessageDTO.setStatus(1);
                } else {
                    wechatMessageDTO.setStatus(2);
                }
                wechatMessageService.addWechatMessage(wechatMessageDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String[] wechatIDs = request.getParameterValues("wechatID");
            if (wechatIDs != null) {
                for (int i = 0; i < wechatIDs.length; i++) {
                    String wechatID = wechatIDs[i];
                    WechatUserVO wechatUserVO = wechatUserService.findWechatUserByID(Long.valueOf(wechatID));
                    if (wechatUserVO != null) {
                        wechatMessageDTO.setCustomerID(wechatUserVO.getCustomerID());
                        if (StringUtil.isNotEmpty(interfaceConfigID)) {
                            wechatMessageDTO.setInterfaceConfigID(interfaceConfigID);
                        }
                        if (StringUtil.isNotEmpty(content)) {
                            wechatMessageDTO.setContent(content);
                        }
                        wechatMessageDTO.setSendType(2);
                        wechatMessageDTO.setCreator(getCurrentOperator());
                        wechatMessageDTO.setMainID(UUIDUtil.getUUID());
                        try {
                            InterfaceConfigVO interfaceConfigVO = interfaceConfigService.findInterfaceConfigByMainID(interfaceConfigID);
                            String appid = interfaceConfigService.findInterfaceConfigParameterValue(interfaceConfigVO.getMainID(),
                                                                                                    "AppId");
                            String appsecret = interfaceConfigService.findInterfaceConfigParameterValue(interfaceConfigVO.getMainID(),
                                                                                                        "AppSecret");
                            String accessToken = getAccessToken(appid, appsecret);
                            String ss = "{\"touser\":\"" + wechatUserVO.getOpenID()
                                        + "\",\"msgtype\":\"text\",\"text\":{\"content\":\""
                                        + wechatMessageDTO.getContent() + "\"}}";
                            String s = ss.toString();
                            String res = getSendMessage(s, accessToken);
                            System.out.println(res);
                            wechatMessageDTO.setOpenID(wechatUserVO.getOpenID());
                            if (res.equals("ok")) {
                                wechatMessageDTO.setStatus(1);
                            } else {
                                wechatMessageDTO.setStatus(2);
                            }
                            wechatMessageService.addWechatMessage(wechatMessageDTO);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return new ModelAndView("redirect:wechatMessageList.htm");
    }

    @RequestMapping("sendWechatMessage")
    public @ResponseBody
    JsonResult sendWechatMessage(String mainID) {
        try {
            WechatMessageVO wechatMessageVO = wechatMessageService.findWechatMessageByMainID(mainID);
            String appid = interfaceConfigService.findInterfaceConfigParameterValue(wechatMessageVO.getInterfaceConfigID(),
                                                                                    "AppId");
            String appsecret = interfaceConfigService.findInterfaceConfigParameterValue(wechatMessageVO.getInterfaceConfigID(),
                                                                                        "AppSecret");
            String accessToken = getAccessToken(appid, appsecret);
            String ss = "{\"touser\":\"" + wechatMessageVO.getOpenID()
                        + "\",\"msgtype\":\"text\",\"text\":{\"content\":\"" + wechatMessageVO.getContent() + "\"}}";
            String s = ss.toString();
            String res = getSendMessage(s, accessToken);
            System.out.println(res);
            WechatMessageDTO wechatMessageDTO = new WechatMessageDTO();
            wechatMessageDTO.setMainID(mainID);
            if (res.equals("ok")) {
                wechatMessageDTO.setStatus(1);
                wechatMessageService.updateWechatMessage(wechatMessageDTO);
                return JsonResult.create();
            } else {
                wechatMessageDTO.setStatus(2);
                wechatMessageService.updateWechatMessage(wechatMessageDTO);
                return JsonResult.create(ResultCode.ERROR_SYSTEM);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.create(ResultCode.ERROR_SYSTEM);

    }

    @RequestMapping("replyWechatMessage")
    public ModelAndView replyWechatMessage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/wechatmessage_reply");
        WechatMessageVO wechatMessageVO = wechatMessageService.findWechatMessageByMainID(mainID);
        modelAndView.addObject("wechatMessageVO", wechatMessageVO);
        List<WechatMessageVO> list = wechatMessageService.findWechatMessageByParentID(mainID);
        modelAndView.addObject("replyList", list);
        return modelAndView;
    }

    /**
     * 获取accessToken
     */
    public static String getAccessToken(String appid, String secret) throws Exception {
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                                                               + appid + "&secret=" + secret);
        HttpResponse response = httpclient.execute(get);
        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject object = JSON.parseObject(jsonStr);
        return object.getString("access_token");
    }

    /**
     * 发送客服消息
     */
    public static String getSendMessage(String params, String accessToken) throws Exception {
        HttpPost httpost = HttpClientConnectionManager.getPostMethod("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
                                                                     + accessToken);
        httpost.setEntity(new StringEntity(params, "UTF-8"));
        HttpResponse response = httpclient.execute(httpost);
        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(jsonStr);
        JSONObject object = JSON.parseObject(jsonStr);
        return object.getString("errmsg");
    }

}
