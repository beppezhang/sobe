package com.kpluswebup.web.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.InterfaceConfigService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.InterfaceConfigDTO;
import com.kpluswebup.web.domain.InterfaceConfigParameterDTO;
import com.kpluswebup.web.vo.InterfaceConfigParameterVO;
import com.kpluswebup.web.vo.InterfaceConfigVO;
import com.kpluswebup.web.vo.SystemInterfaceParameterVO;
import com.kpluswebup.web.vo.SystemInterfaceVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/system")
public class EmailConfigController extends BaseController {

    @Autowired
    private InterfaceConfigService interfaceConfigService;

    @RequestMapping("emailConfigList")
    public ModelAndView emailConfigList(InterfaceConfigDTO interfaceConfigDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/system/emailconfig_list");
        interfaceConfigDTO.setInterfaceType(6);
        List<InterfaceConfigVO> list = interfaceConfigService.findInterfaceConfigByPagination(interfaceConfigDTO);
        modelAndView.addObject("emailconfigList", list);
        return modelAndView;
    }

    @RequestMapping("addEmailConfigPage")
    public ModelAndView addEmailConfigPage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/system/emailconfig_add");
        List<SystemInterfaceVO> list = interfaceConfigService.findSystemInterface(6);
        modelAndView.addObject("systeminterfaceList", list);
        if (list != null && list.size() > 0) {
            List<SystemInterfaceParameterVO> paramlist = interfaceConfigService.findSystemInterfaceParameterByInterfaceID(list.get(0).getMainID());
            modelAndView.addObject("systeminterfaceparameterList", paramlist);
        }
        return modelAndView;
    }

    @RequestMapping("addEmailConfig")
    public ModelAndView addEmailConfig(HttpServletRequest request, String name, String systeminterfaceID, String picURL) {
        String[] parameterIDs = request.getParameterValues("parameterID");
        String[] parameterValues = request.getParameterValues("parameterValue");
        String[] parameterNames = request.getParameterValues("parameterName");
        InterfaceConfigDTO interfaceConfigDTO = new InterfaceConfigDTO();
        InterfaceConfigParameterDTO interfaceConfigParameterDTO = new InterfaceConfigParameterDTO();
        if (StringUtil.isNotEmpty(name)) {
            interfaceConfigDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(systeminterfaceID)) {
            interfaceConfigDTO.setInterfaceID(systeminterfaceID);
        }
        if (StringUtil.isNotEmpty(picURL)) {
            interfaceConfigDTO.setPicURL(picURL);
        }
        interfaceConfigDTO.setInterfaceType(6);
        interfaceConfigDTO.setMainID(UUIDUtil.getUUID());
        interfaceConfigDTO.setCreator(getCurrentOperator());
        interfaceConfigService.addInterfaceConfig(interfaceConfigDTO);
        if (parameterValues != null) {
            for (int i = 0; i < parameterValues.length; i++) {
                String paramValue = parameterValues[i];
                String parameterID = parameterIDs[i];
                String parameterName = parameterNames[i];
                if (StringUtil.isNotEmpty(paramValue)) {
                    interfaceConfigParameterDTO.setParameter(parameterName);
                    interfaceConfigParameterDTO.setParamValue(paramValue);
                    interfaceConfigParameterDTO.setParameterID(parameterID);
                    interfaceConfigParameterDTO.setInterfaceID(systeminterfaceID);
                    interfaceConfigParameterDTO.setConfigID(interfaceConfigDTO.getMainID());
                    interfaceConfigParameterDTO.setCreator(getCurrentOperator());
                    interfaceConfigService.addInterfaceConfigParameter(interfaceConfigParameterDTO);
                }
            }
        }
        return new ModelAndView("redirect:emailConfigList.htm");
    }

    @RequestMapping("editEmailConfigPage")
    public ModelAndView editEmailConfigPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/system/emailconfig_edit");
        List<SystemInterfaceVO> list = interfaceConfigService.findSystemInterface(6);
        modelAndView.addObject("systeminterfaceList", list);
        InterfaceConfigVO interfaceConfigVO = interfaceConfigService.findInterfaceConfigByMainID(mainID);
        modelAndView.addObject("interfaceConfigVO", interfaceConfigVO);
        List<InterfaceConfigParameterVO> plist = interfaceConfigService.findInterfaceConfigParameterByConfigID(mainID);
        if (plist != null && plist.size() > 0) {
            modelAndView.addObject("interfaceparameterList", plist);
        } else {
            List<SystemInterfaceParameterVO> paramlist = interfaceConfigService.findSystemInterfaceParameterByInterfaceID(mainID);
            modelAndView.addObject("systeminterfaceparameterList", paramlist);
        }
        return modelAndView;
    }

    @RequestMapping("editEmailConfig")
    public ModelAndView editEmailConfig(HttpServletRequest request, String mainID, String name,
                                        String systeminterfaceID, String picURL) {
        String[] parameterIDs = request.getParameterValues("parameterID");
        String[] parameterValues = request.getParameterValues("parameterValue");
        String[] parameterNames = request.getParameterValues("parameterName");
        String[] interParameterIDs = request.getParameterValues("interParameterID");
        InterfaceConfigDTO interfaceConfigDTO = new InterfaceConfigDTO();
        InterfaceConfigParameterDTO interfaceConfigParameterDTO = new InterfaceConfigParameterDTO();
        if (StringUtil.isNotEmpty(name)) {
            interfaceConfigDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(systeminterfaceID)) {
            interfaceConfigDTO.setInterfaceID(systeminterfaceID);
        }
        if (StringUtil.isNotEmpty(picURL)) {
            interfaceConfigDTO.setPicURL(picURL);
        }
        interfaceConfigDTO.setMainID(mainID);
        interfaceConfigDTO.setModifier(getCurrentOperator());
        interfaceConfigService.updateInterfaceConfig(interfaceConfigDTO);
        if (parameterValues != null) {
            for (int i = 0; i < parameterValues.length; i++) {
                String paramValue = parameterValues[i];
                String parameterID = "";
                if (parameterIDs != null) {
                    parameterID = parameterIDs[i];
                }
                String parameterName = parameterNames[i];
                String interParameterID = interParameterIDs[i];
                if (StringUtil.isNotEmpty(paramValue)) {
                    interfaceConfigParameterDTO.setParameter(parameterName);
                    interfaceConfigParameterDTO.setParamValue(paramValue);
                    if (StringUtil.isNotEmpty(interParameterID)) {
                        interfaceConfigParameterDTO.setId(Long.valueOf(interParameterID));
                        interfaceConfigService.updateInterfaceConfigParameter(interfaceConfigParameterDTO);
                    } else {
                        if (StringUtil.isNotEmpty(parameterID)) {
                            interfaceConfigParameterDTO.setParameterID(parameterID);
                        }
                        interfaceConfigParameterDTO.setInterfaceID(systeminterfaceID);
                        interfaceConfigService.addInterfaceConfigParameter(interfaceConfigParameterDTO);
                    }
                }
            }
        }
        return new ModelAndView("redirect:emailConfigList.htm");
    }

    @RequestMapping("deleteEmailConfig")
    public @ResponseBody
    JsonResult deleteEmailConfig(String mainIDs) {
        Boolean isSuccess = interfaceConfigService.deleteInterfaceConfig(mainIDs);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("changeEmailConfigActive")
    public @ResponseBody
    JsonResult changeEmailConfigActive(String mainIDs, Integer active) {
        try {
            interfaceConfigService.updateInterfaceConfigActive(mainIDs, active);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }
}
