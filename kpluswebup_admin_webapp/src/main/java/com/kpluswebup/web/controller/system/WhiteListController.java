package com.kpluswebup.web.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.TransConfigService;
import com.kpluswebup.web.admin.system.service.WhiteListService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.TransConfigDTO;
import com.kpluswebup.web.domain.WhiteListDTO;
import com.kpluswebup.web.vo.TransConfigVO;
import com.kpluswebup.web.vo.WhiteListVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/system")
public class WhiteListController extends BaseController {

    @Autowired
    private WhiteListService   whiteListService;
    @Autowired
    private TransConfigService transConfigService;

    @RequestMapping("whiteList")
    public ModelAndView whiteList() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/system/white_list");
        List<WhiteListVO> list = whiteListService.findWhileList();
        modelAndView.addObject("whiteList", list);
        TransConfigVO transConfigVO = transConfigService.findTransConfig();
        modelAndView.addObject("transConfigVO", transConfigVO);
        return modelAndView;
    }

    @RequestMapping("addWhiteIP")
    public ModelAndView addWhiteIP(String whiteIP) {
        WhiteListDTO whiteListDTO = new WhiteListDTO();
        if (StringUtil.isNotEmpty(whiteIP)) {
            whiteListDTO.setIP(whiteIP);
        }
        whiteListService.addWhiteIP(whiteListDTO);
        return new ModelAndView("redirect:whiteList.htm");
    }

    @RequestMapping("setIpLimiteActive")
    public ModelAndView setIpLimiteActive(String ipLimitedActive) {
        TransConfigVO transConfigVO = transConfigService.findTransConfig();
        TransConfigDTO transConfigDTO = new TransConfigDTO();
        if (transConfigVO == null) {
            if (StringUtil.isNotEmpty(ipLimitedActive)) {
                transConfigDTO.setIpLimitedActive(Integer.valueOf(ipLimitedActive));
            }
            transConfigDTO.setCreator(getCurrentOperator());
            transConfigService.addTransConfig(transConfigDTO);
        } else {
            if (StringUtil.isNotEmpty(ipLimitedActive)) {
                transConfigDTO.setIpLimitedActive(Integer.valueOf(ipLimitedActive));
            }
            transConfigDTO.setMainID(transConfigVO.getMainID());
            transConfigDTO.setModifier(getCurrentOperator());
            transConfigService.updateTransConfig(transConfigDTO);
        }
        return new ModelAndView("redirect:whiteList.htm");
    }

    @RequestMapping("deleteWhiteIP")
    public @ResponseBody
    JsonResult deleteWhiteIP(Long id) {
        Boolean isSuccess=whiteListService.deleteWhiteIP(id);
        if(isSuccess){
            return JsonResult.create();
        }
        return JsonResult.create(ResultCode.ERROR_SYSTEM);
    }
}
