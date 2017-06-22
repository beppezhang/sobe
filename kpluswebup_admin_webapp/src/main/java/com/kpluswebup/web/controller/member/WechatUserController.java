package com.kpluswebup.web.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.InterfaceConfigService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.WechatUserDTO;
import com.kpluswebup.web.member.service.WechatUserService;
import com.kpluswebup.web.vo.InterfaceConfigVO;
import com.kpluswebup.web.vo.WechatUserVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/member")
public class WechatUserController extends BaseController {

    @Autowired
    private WechatUserService      wechatUserService;
    @Autowired
    private InterfaceConfigService interfaceConfigService;

    @RequestMapping("wechatUserList")
    public ModelAndView wechatUserList(WechatUserDTO wechatUserDTO, String searchWechatNick,
                                       String searchInterfaceConfigID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/wechatuser_list");
        if (StringUtil.isNotEmpty(searchWechatNick)) {
            wechatUserDTO.setWechatNick(searchWechatNick);
        }
        if (StringUtil.isNotEmpty(searchInterfaceConfigID)) {
            wechatUserDTO.setInterfaceConfigID(searchInterfaceConfigID);
        }
        List<WechatUserVO> list = wechatUserService.findWechatUserByPagination(wechatUserDTO);
        modelAndView.addObject("wechatUserList", list);
        modelAndView.addObject("wechatUserDTO", wechatUserDTO);
        List<InterfaceConfigVO> inlist = interfaceConfigService.findInterfaceConfigByType(7);
        modelAndView.addObject("interfaceConfigList", inlist);
        return modelAndView;
    }

    @RequestMapping("searchWechatUser")
    public @ResponseBody
    JsonResult searchWechatUser(String pageNo, String pageSize, String keyword) {
        WechatUserDTO wechatUserDTO = new WechatUserDTO();
        if (StringUtil.isNumberic(pageNo)) {
            wechatUserDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNumberic(pageSize)) {
            wechatUserDTO.setPageSize(Long.parseLong(pageSize));
        }
        JsonResult jsonResult = new JsonResult(ResultCode.NORMAL);
        if (StringUtil.isNotEmpty(keyword)) {
            wechatUserDTO.setWechatNick(keyword);
        }
        List<WechatUserVO> list = wechatUserService.findWechatUserByPagination(wechatUserDTO);
        jsonResult.setResult(list);
        return jsonResult;
    }
}
