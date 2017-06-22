package com.kpluswebup.web.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.SystemMessageFunctionService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.MessageConfigDTO;
import com.kpluswebup.web.domain.MessageTemplateDTO;
import com.kpluswebup.web.domain.SystemMessageFunctionDTO;
import com.kpluswebup.web.vo.MessageConfigVO;
import com.kpluswebup.web.vo.MessageTemplateVO;
import com.kpluswebup.web.vo.SystemMessageFunctionVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/system")
public class SystemMessageFunctionController extends BaseController {

	@Autowired
	private SystemMessageFunctionService systemMessageFunctionService;

	@RequestMapping("systemMessageFunctionList")
	public ModelAndView systemMessageFunctionList() {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/system/systemmessagefunction_list");
		List<SystemMessageFunctionVO> list = systemMessageFunctionService
				.findSystemMessageFunction();
		modelAndView.addObject("systemmessagefunctionList", list);
		return modelAndView;
	}

	@RequestMapping("setCheckMessageActive")
	public @ResponseBody
	JsonResult setCheckMessageActive(String functionID, String type, String status) {
		try {
			MessageConfigVO messageConfigVO = systemMessageFunctionService
					.findMessageConfigByFunctionID(functionID);
			MessageConfigDTO messageConfigDTO = new MessageConfigDTO();
			messageConfigDTO.setMessageFunctionID(functionID);
			if (messageConfigVO != null) {
				if (StringUtil.isNotEmpty(type)) {
					if (type.equals("1")) {
						messageConfigDTO.setMessageActive(Integer
								.valueOf(status));
						if (messageConfigVO.getEmailActive() == null) {
							messageConfigDTO.setEmailActive(0);
						}
						if (messageConfigVO.getSmsActive() == null) {
							messageConfigDTO.setSmsActive(0);
						}
					} else if (type.equals("2")) {
						messageConfigDTO
								.setEmailActive(Integer.valueOf(status));
						if (messageConfigVO.getMessageActive() == null) {
							messageConfigDTO.setMessageActive(0);
						}
						if (messageConfigVO.getSmsActive() == null) {
							messageConfigDTO.setSmsActive(0);
						}
					} else if (type.equals("3")) {
						messageConfigDTO.setSmsActive(Integer.valueOf(status));
						if (messageConfigVO.getMessageActive() == null) {
							messageConfigDTO.setMessageActive(0);
						}
						if (messageConfigVO.getEmailActive() == null) {
							messageConfigDTO.setEmailActive(0);
						}
					}
				}
				messageConfigDTO.setId(messageConfigVO.getId());
				messageConfigDTO.setModifier(getCurrentOperator());
				systemMessageFunctionService
						.updateMessageConfig(messageConfigDTO);
			} else {
				if (StringUtil.isNotEmpty(type)) {
					if (type.equals("1")) {
						messageConfigDTO.setMessageActive(Integer
								.valueOf(status));
						messageConfigDTO.setEmailActive(0);
						messageConfigDTO.setSmsActive(0);
					} else if (type.equals("2")) {
						messageConfigDTO
								.setEmailActive(Integer.valueOf(status));
						messageConfigDTO.setMessageActive(0);
						messageConfigDTO.setSmsActive(0);
					} else if (type.equals("3")) {
						messageConfigDTO.setSmsActive(Integer.valueOf(status));
						messageConfigDTO.setMessageActive(0);
						messageConfigDTO.setEmailActive(0);
					}
				}
				messageConfigDTO.setCreator(getCurrentOperator());
				systemMessageFunctionService.addMessageConfig(messageConfigDTO);
			}
			SystemMessageFunctionDTO systemMessageFunctionDTO = new SystemMessageFunctionDTO();
			if (StringUtil.isNotEmpty(type)) {
				if (type.equals("1")) {
					systemMessageFunctionDTO.setMessageActive(Integer
							.valueOf(status));
				} else if (type.equals("2")) {
					systemMessageFunctionDTO.setEmailActive(Integer
							.valueOf(status));
				} else if (type.equals("3")) {
					systemMessageFunctionDTO.setSmsActive(Integer
							.valueOf(status));
				}
			}
			systemMessageFunctionDTO.setMainID(functionID);
			systemMessageFunctionDTO.setModifier(getCurrentOperator());
			systemMessageFunctionService
					.updateSystemMessageFunction(systemMessageFunctionDTO);
			return JsonResult.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	@RequestMapping("editMessageTemplatePage")
	public ModelAndView editMessageTemplatePage(String functionID, String type) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/system/messagetemplate_edit");
		SystemMessageFunctionVO systemMessageFunctionVO = systemMessageFunctionService
				.findSystemMessageFunctionByMainID(functionID);
		modelAndView.addObject("systemMessageFunctionVO",
				systemMessageFunctionVO);
		MessageTemplateDTO messageTemplateDTO = new MessageTemplateDTO();
		messageTemplateDTO.setMessageFunctionID(functionID);
		messageTemplateDTO.setTemplateType(Integer.valueOf(type));
		MessageTemplateVO messageTemplateVO = systemMessageFunctionService
				.findMessageTemplateByFunctionIDType(messageTemplateDTO);
		if (messageTemplateVO != null) {
			modelAndView.addObject("messageTemplateVO", messageTemplateVO);
		} else {
			messageTemplateVO = new MessageTemplateVO();
			messageTemplateVO.setTemplateType(Integer.valueOf(type));
			modelAndView.addObject("messageTemplateVO", messageTemplateVO);
		}
		return modelAndView;
	}

	@RequestMapping("editMessageTemplate")
	public ModelAndView editMessageTemplate(String id, String functionID,
			String templateType, String templateContent) {
		MessageTemplateDTO messageTemplateDTO = new MessageTemplateDTO();
		if (StringUtil.isNotEmpty(functionID)) {
			messageTemplateDTO.setMessageFunctionID(functionID);
		}
		if (StringUtil.isNotEmpty(templateType)) {
			messageTemplateDTO.setTemplateType(Integer.valueOf(templateType));
		}
		if (StringUtil.isNotEmpty(templateContent)) {
			messageTemplateDTO.setTemplateContent(templateContent);
		}
		if (StringUtil.isNotEmpty(id)) {
			messageTemplateDTO.setId(Long.valueOf(id));
			messageTemplateDTO.setModifier(getCurrentOperator());
			systemMessageFunctionService
					.updateMessageTemplate(messageTemplateDTO);
		} else {
		    messageTemplateDTO.setCreator(getCurrentOperator());
			systemMessageFunctionService.addMessageTemplate(messageTemplateDTO);
		}
		return new ModelAndView("redirect:systemMessageFunctionList.htm");
	}
}
