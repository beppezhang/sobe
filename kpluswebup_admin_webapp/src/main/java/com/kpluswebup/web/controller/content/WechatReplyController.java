package com.kpluswebup.web.controller.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.InterfaceConfigService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.content.service.CmsWechatReplySetService;
import com.kpluswebup.web.domain.CmsWechatReplySetDTO;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.vo.CmsWechatReplySetVO;
import com.kpluswebup.web.vo.InterfaceConfigVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/content")
public class WechatReplyController extends BaseController {

    @Autowired
    private CmsWechatReplySetService cmsWechatReplySetService;
    @Autowired
    private InterfaceConfigService   interfaceConfigService;
    @Autowired
    private ItemService              itemService;

    @RequestMapping("wechatReplyList")
    public ModelAndView wechatReplyList(CmsWechatReplySetDTO cmsWechatReplySetDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/content/wechatreply_list");
        cmsWechatReplySetDTO.setSetType(1);
        List<CmsWechatReplySetVO> list = cmsWechatReplySetService.findWechatReplyByPagination(cmsWechatReplySetDTO);
        modelAndView.addObject("wechatReplyList", list);
        modelAndView.addObject("cmsWechatReplySetDTO", cmsWechatReplySetDTO);
        List<InterfaceConfigVO> inlist = interfaceConfigService.findInterfaceConfigByType(7);
        modelAndView.addObject("interfaceConfigList", inlist);
        return modelAndView;
    }

    @RequestMapping("addWechatReplyPage")
    public ModelAndView addWechatReplyPage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/content/wechatreply_add");
        List<InterfaceConfigVO> list = interfaceConfigService.findInterfaceConfigByType(7);
        modelAndView.addObject("interfaceConfigList", list);
        return modelAndView;
    }

    @RequestMapping("addWechatReply")
    public ModelAndView addWechatReply(String interfaceConfigID, String replyType, String title, String content,
                                       String replyContent, String picURL, String linkType, String replyLink,
                                       String itemID) {
        CmsWechatReplySetDTO cmsWechatReplySetDTO = new CmsWechatReplySetDTO();
        if (StringUtil.isNotEmpty(replyType)) {
            cmsWechatReplySetDTO.setReplyType(Integer.valueOf(replyType));
        }
        if (StringUtil.isNotEmpty(interfaceConfigID)) {
            cmsWechatReplySetDTO.setInterfaceConfigID(interfaceConfigID);
        }
        if (StringUtil.isNotEmpty(replyType)) {
            if (replyType.equals("1")) {
                if (StringUtil.isNotEmpty(replyContent)) cmsWechatReplySetDTO.setContent(replyContent);
            } else {
                if (StringUtil.isNotEmpty(title)) {
                    cmsWechatReplySetDTO.setTitle(title);
                }
                if (StringUtil.isNotEmpty(content)) {
                    cmsWechatReplySetDTO.setContent(content);
                }
                if (StringUtil.isNotEmpty(picURL)) {
                    cmsWechatReplySetDTO.setPicURL(picURL);
                }
                if (StringUtil.isNotEmpty(linkType)) {
                    cmsWechatReplySetDTO.setLinkType(Integer.valueOf(linkType));
                    if (linkType.equals("1")) {
                        if (StringUtil.isNotEmpty(replyLink)) {
                            cmsWechatReplySetDTO.setReplyLink(replyLink);
                        }
                    } else {
                        if (StringUtil.isNotEmpty(itemID)) {
                            cmsWechatReplySetDTO.setReplyLink(itemID);
                        }
                    }
                }
            }
        }
        cmsWechatReplySetDTO.setSortOrder(1);
        cmsWechatReplySetDTO.setMainID(UUIDUtil.getUUID());
        cmsWechatReplySetDTO.setSetType(1);
        cmsWechatReplySetDTO.setCreator(getCurrentOperator());
        cmsWechatReplySetService.addWechatReply(cmsWechatReplySetDTO);
        return new ModelAndView("redirect:wechatReplyList.htm");
    }

    @RequestMapping("editWechatReplyPage")
    public ModelAndView editWechatReplyPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/content/wechatreply_edit");
        CmsWechatReplySetVO cmsWechatReplySetVO = cmsWechatReplySetService.findWechatReplyByMainID(mainID);
        modelAndView.addObject("cmsWechatReplySetVO", cmsWechatReplySetVO);
        List<InterfaceConfigVO> list = interfaceConfigService.findInterfaceConfigByType(7);
        modelAndView.addObject("interfaceConfigList", list);
        if (cmsWechatReplySetVO.getLinkType()!=null&&cmsWechatReplySetVO.getLinkType() == 2) {
            if (StringUtil.isNotEmpty(cmsWechatReplySetVO.getReplyLink())) {
                ItemVO itemVO = itemService.findItemByMainID(cmsWechatReplySetVO.getReplyLink());
                modelAndView.addObject("itemVO", itemVO);
            }
        }
        return modelAndView;
    }

    @RequestMapping("editWechatReply")
    public ModelAndView editWechatReply(String mainID, String interfaceConfigID, String replyType, String title,
                                        String content, String replyContent, String picURL, String linkType,
                                        String replyLink, String itemID) {
        CmsWechatReplySetDTO cmsWechatReplySetDTO = new CmsWechatReplySetDTO();
        if (StringUtil.isNotEmpty(replyType)) {
            cmsWechatReplySetDTO.setReplyType(Integer.valueOf(replyType));
        }
        if (StringUtil.isNotEmpty(interfaceConfigID)) {
            cmsWechatReplySetDTO.setInterfaceConfigID(interfaceConfigID);
        }
        if (StringUtil.isNotEmpty(replyType)) {
            if (replyType.equals("1")) {
                if (StringUtil.isNotEmpty(replyContent)) cmsWechatReplySetDTO.setContent(replyContent);
                cmsWechatReplySetDTO.setTitle("");
                cmsWechatReplySetDTO.setPicURL("");
                cmsWechatReplySetDTO.setLinkType(0);
                cmsWechatReplySetDTO.setReplyLink("");
            } else {
                if (StringUtil.isNotEmpty(title)) {
                    cmsWechatReplySetDTO.setTitle(title);
                }
                if (StringUtil.isNotEmpty(content)) {
                    cmsWechatReplySetDTO.setContent(content);
                }
                if (StringUtil.isNotEmpty(picURL)) {
                    cmsWechatReplySetDTO.setPicURL(picURL);
                }
                if (StringUtil.isNotEmpty(linkType)) {
                    cmsWechatReplySetDTO.setLinkType(Integer.valueOf(linkType));
                    if (linkType.equals("1")) {
                        if (StringUtil.isNotEmpty(replyLink)) {
                            cmsWechatReplySetDTO.setReplyLink(replyLink);
                        }
                    } else {
                        if (StringUtil.isNotEmpty(itemID)) {
                            cmsWechatReplySetDTO.setReplyLink(itemID);
                        }
                    }
                }
            }
        }
        cmsWechatReplySetDTO.setMainID(mainID);
        cmsWechatReplySetDTO.setModifier(getCurrentOperator());
        cmsWechatReplySetService.updateWechatReplyByMainID(cmsWechatReplySetDTO);
        return new ModelAndView("redirect:wechatReplyList.htm");
    }

    @RequestMapping("deleteWechatReply")
    public @ResponseBody
    JsonResult deleteWechatReply(String mainIDs) {
        Boolean isSuccess = cmsWechatReplySetService.deleteWechatReplyByMainID(mainIDs);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

}
