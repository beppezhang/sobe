package com.kpluswebup.web.controller.content;

import java.util.List;

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
import com.kpluswebup.web.content.service.CmsWechatMenuService;
import com.kpluswebup.web.domain.CmsWechatMenuDTO;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.vo.CmsWechatMenuVO;
import com.kpluswebup.web.vo.InterfaceConfigVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.wechat.HttpClientConnectionManager;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/content")
public class WechatMenuController extends BaseController {

    @Autowired
    private CmsWechatMenuService    cmsWechatMenuService;
    @Autowired
    private InterfaceConfigService  interfaceConfigService;
    @Autowired
    private ItemService             itemService;

    // http客户端
    public static DefaultHttpClient httpclient;

    static {
        httpclient = new DefaultHttpClient();
        httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient); // 接受任何证书的浏览器客户端
    }

    @RequestMapping("wechatMenuList")
    public ModelAndView wechatMenuList(CmsWechatMenuDTO cmsWechatMenuDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/content/wechatmenu_list");
        List<CmsWechatMenuVO> list = cmsWechatMenuService.findWechatMenuByPagination(cmsWechatMenuDTO);
        modelAndView.addObject("wechatMenuList", list);
        modelAndView.addObject("cmsWechatMenuDTO", cmsWechatMenuDTO);
        List<InterfaceConfigVO> inlist = interfaceConfigService.findInterfaceConfigByType(7);
        modelAndView.addObject("interfaceConfigList", inlist);
        return modelAndView;
    }

    @RequestMapping("addWechatMenuPage")
    public ModelAndView addWechatMenuPage(String parentID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/content/wechatmenu_add");
        List<InterfaceConfigVO> list = interfaceConfigService.findInterfaceConfigByType(7);
        modelAndView.addObject("interfaceConfigList", list);
        if (StringUtil.isNotEmpty(parentID)) {
            List<CmsWechatMenuVO> mlist = cmsWechatMenuService.findCmsWechatMenuParent();
            modelAndView.addObject("wechatMenuList", mlist);
        }
        modelAndView.addObject("parentID", parentID);
        return modelAndView;
    }

    @RequestMapping("addWechatMenu")
    public ModelAndView addWechatMenu(String interfaceConfigID, String parentID, String name, String menuType,
                                      String title, String content, String picURL, String linkType, String menuLink,
                                      String itemID) {
        CmsWechatMenuDTO cmsWechatMenuDTO = new CmsWechatMenuDTO();
        if (StringUtil.isNotEmpty(menuType)) {
            cmsWechatMenuDTO.setMenuType(Integer.valueOf(menuType));
        }
        if (StringUtil.isNotEmpty(interfaceConfigID)) {
            cmsWechatMenuDTO.setInterfaceConfigID(interfaceConfigID);
        }
        if (StringUtil.isNotEmpty(parentID)) {
            cmsWechatMenuDTO.setParentID(parentID);
        }
        if (StringUtil.isNotEmpty(name)) {
            cmsWechatMenuDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(title)) {
            cmsWechatMenuDTO.setTitle(title);
        }
        if (StringUtil.isNotEmpty(content)) {
            cmsWechatMenuDTO.setContent(content);
        }
        if (StringUtil.isNotEmpty(picURL)) {
            cmsWechatMenuDTO.setPicURL(picURL);
        }
        if (StringUtil.isNotEmpty(linkType)) {
            cmsWechatMenuDTO.setLinkType(Integer.valueOf(linkType));
            if (linkType.equals("1")) {
                if (StringUtil.isNotEmpty(menuLink)) {
                    cmsWechatMenuDTO.setMenuLink(menuLink);
                }
            } else {
                if (StringUtil.isNotEmpty(itemID)) {
                    cmsWechatMenuDTO.setMenuLink(itemID);
                }
            }
        }
        cmsWechatMenuDTO.setSortOrder(1);
        cmsWechatMenuDTO.setMainID(UUIDUtil.getUUID());
        cmsWechatMenuDTO.setCreator(getCurrentOperator());
        cmsWechatMenuService.addWechatMenu(cmsWechatMenuDTO);
        try {
            this.createWeixinMenu(interfaceConfigID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:wechatMenuList.htm");
    }

    @RequestMapping("editWechatMenuPage")
    public ModelAndView editWechatMenuPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/content/wechatmenu_edit");
        CmsWechatMenuVO cmsWechatMenuVO = cmsWechatMenuService.findWechatMenuByMainID(mainID);
        modelAndView.addObject("cmsWechatMenuVO", cmsWechatMenuVO);
        List<InterfaceConfigVO> list = interfaceConfigService.findInterfaceConfigByType(7);
        modelAndView.addObject("interfaceConfigList", list);
        if (StringUtil.isNotEmpty(cmsWechatMenuVO.getParentID())) {
            List<CmsWechatMenuVO> mlist = cmsWechatMenuService.findCmsWechatMenuParent();
            modelAndView.addObject("wechatMenuList", mlist);
        }
        if (cmsWechatMenuVO.getLinkType() == 2) {
            if (StringUtil.isNotEmpty(cmsWechatMenuVO.getMenuLink())) {
                ItemVO itemVO = itemService.findItemByMainID(cmsWechatMenuVO.getMenuLink());
                modelAndView.addObject("itemVO", itemVO);
            }
        }
        return modelAndView;
    }

    @RequestMapping("editWechatMenu")
    public ModelAndView editWechatMenu(String mainID, String interfaceConfigID, String parentID, String name,
                                       String menuType, String title, String content, String picURL, String linkType,
                                       String menuLink, String itemID) {
        CmsWechatMenuDTO cmsWechatMenuDTO = new CmsWechatMenuDTO();
        if (StringUtil.isNotEmpty(menuType)) {
            cmsWechatMenuDTO.setMenuType(Integer.valueOf(menuType));
        }
        if (StringUtil.isNotEmpty(interfaceConfigID)) {
            cmsWechatMenuDTO.setInterfaceConfigID(interfaceConfigID);
        }
        if (StringUtil.isNotEmpty(parentID)) {
            cmsWechatMenuDTO.setParentID(parentID);
        }
        if (StringUtil.isNotEmpty(name)) {
            cmsWechatMenuDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(title)) {
            cmsWechatMenuDTO.setTitle(title);
        }
        if (StringUtil.isNotEmpty(content)) {
            cmsWechatMenuDTO.setContent(content);
        }
        if (StringUtil.isNotEmpty(picURL)) {
            cmsWechatMenuDTO.setPicURL(picURL);
        }
        if (StringUtil.isNotEmpty(linkType)) {
            cmsWechatMenuDTO.setLinkType(Integer.valueOf(linkType));
        }
        if (StringUtil.isNotEmpty(menuLink)) {
            cmsWechatMenuDTO.setLinkType(Integer.valueOf(linkType));
            if (linkType.equals("1")) {
                if (StringUtil.isNotEmpty(menuLink)) {
                    cmsWechatMenuDTO.setMenuLink(menuLink);
                }
            } else {
                if (StringUtil.isNotEmpty(itemID)) {
                    cmsWechatMenuDTO.setMenuLink(itemID);
                }
            }
        }
        cmsWechatMenuDTO.setMainID(mainID);
        cmsWechatMenuDTO.setModifier(getCurrentOperator());
        cmsWechatMenuService.updateWechatMenuByMainID(cmsWechatMenuDTO);
        try {
            this.createWeixinMenu(interfaceConfigID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:wechatMenuList.htm");
    }

    @RequestMapping("deleteWechatMenu")
    public @ResponseBody
    JsonResult deleteWechatMenu(String mainIDs) {
        Boolean isSuccess = cmsWechatMenuService.deleteWechatMenuByMainID(mainIDs);
        if (isSuccess) {
            try {
                String[] ids = mainIDs.split(",");
                for (String mainID : ids) {
                    CmsWechatMenuVO cmsWechatMenuVO = cmsWechatMenuService.findWechatMenuByMainID(mainID);
                    this.createWeixinMenu(cmsWechatMenuVO.getInterfaceConfigID());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * 创建微信菜单
     * 
     * @date 2014年12月23日
     * @author wanghehua
     * @param configID
     * @throws Exception
     * @since JDK 1.6
     * @Description
     */
    public void createWeixinMenu(String configID) throws Exception {
        String appid = interfaceConfigService.findInterfaceConfigParameterValue(configID, "AppId");
        String appsecret = interfaceConfigService.findInterfaceConfigParameterValue(configID, "AppSecret");
        String accessToken = getAccessToken(appid, appsecret);
        System.out.println(accessToken);
        List<CmsWechatMenuVO> list = cmsWechatMenuService.findCmsWechatMenuParent();
        StringBuffer jsonmenu = new StringBuffer();
        jsonmenu.append("{\"button\":[").append("");
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                CmsWechatMenuVO cmsWechatMenuVO = list.get(i);
                if (i > 0) {
                    jsonmenu.append(",");
                }
                List<CmsWechatMenuVO> childlist = cmsWechatMenuService.findCmsWechatMenuChildByParentID(cmsWechatMenuVO.getMainID());
                if (childlist != null && childlist.size() > 0) {
                    // 存在子菜单
                    jsonmenu.append("{\"name\":\"").append(cmsWechatMenuVO.getName()).append("\"");
                    jsonmenu.append(",\"sub_button\":[").append("");
                    for (int j = 0; j < childlist.size(); j++) {
                        CmsWechatMenuVO cmsWechatMenuVO2 = childlist.get(j);
                        if (j > 0) {
                            jsonmenu.append(",");
                        }
                        // 链接模式
                        if (cmsWechatMenuVO2.getMenuType() == 1) {
                            jsonmenu.append("{\"type\":\"").append("view").append("\"");
                            jsonmenu.append(",\"name\":\"").append(cmsWechatMenuVO2.getName()).append("\"");
                            if (StringUtil.isNotEmpty(cmsWechatMenuVO2.getMenuLink())) {
                                jsonmenu.append(",\"url\":\"").append(cmsWechatMenuVO2.getMenuLink()).append("\"}");
                            } else {
                                jsonmenu.append(",\"url\":\"").append("\"}");
                            }
                        } else {
                            jsonmenu.append("{\"type\":\"").append("click").append("\"");
                            jsonmenu.append(",\"name\":\"").append(cmsWechatMenuVO2.getName()).append("\"");
                            jsonmenu.append(",\"key\":\"").append(cmsWechatMenuVO2.getMainID()).append("\"}");
                        }
                    }
                    jsonmenu.append("]}");
                    // 没有子菜单
                } else {
                    // 自定义链接跳转
                    if (cmsWechatMenuVO.getMenuType() == 1) {
                        jsonmenu.append("{\"type\":\"").append("view").append("\"");
                        jsonmenu.append(",\"name\":\"").append(cmsWechatMenuVO.getName()).append("\"");
                        if (StringUtil.isNotEmpty(cmsWechatMenuVO.getMenuLink())) {
                            jsonmenu.append(",\"url\":\"").append(cmsWechatMenuVO.getMenuLink()).append("\"}");
                        } else {
                            jsonmenu.append(",\"url\":\"").append("\"}");
                        }
                    } else {
                        jsonmenu.append("{\"type\":\"").append("click").append("\"");
                        jsonmenu.append(",\"name\":\"").append(cmsWechatMenuVO.getName()).append("\"");
                        jsonmenu.append(",\"key\":\"").append(cmsWechatMenuVO.getMainID()).append("\"}");
                    }
                }
            }
            jsonmenu.append("]}");
        }
        // 创建菜单
        String s = jsonmenu.toString();
        System.out.println(s);
        String res = createMenu(s, accessToken);
        System.out.println(res);
        String result = getMenuInfo(accessToken);
        System.out.println(result);
    }

    /**
     * 创建菜单
     */
    public static String createMenu(String params, String accessToken) throws Exception {
        HttpPost httpost = HttpClientConnectionManager.getPostMethod("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
                                                                     + accessToken);
        httpost.setEntity(new StringEntity(params, "UTF-8"));
        HttpResponse response = httpclient.execute(httpost);
        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(jsonStr);
        JSONObject object = JSON.parseObject(jsonStr);
        return object.getString("errmsg");
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
     * 查询菜单
     */
    public static String getMenuInfo(String accessToken) throws Exception {
        HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/get?access_token="
                                                               + accessToken);
        HttpResponse response = httpclient.execute(get);
        String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
        return jsonStr;
    }

}
