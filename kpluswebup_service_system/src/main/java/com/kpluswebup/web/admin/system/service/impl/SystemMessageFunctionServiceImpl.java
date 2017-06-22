package com.kpluswebup.web.admin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.SystemMessageFunctionDAO;
import com.kpluswebup.web.admin.system.service.SystemMessageFunctionService;
import com.kpluswebup.web.domain.MessageConfigDTO;
import com.kpluswebup.web.domain.MessageTemplateDTO;
import com.kpluswebup.web.domain.SystemMessageFunctionDTO;
import com.kpluswebup.web.vo.MessageConfigVO;
import com.kpluswebup.web.vo.MessageTemplateVO;
import com.kpluswebup.web.vo.SystemMessageFunctionVO;

@Service
public class SystemMessageFunctionServiceImpl implements
		SystemMessageFunctionService {

	@Autowired
	private SystemMessageFunctionDAO systemMessageFunctionDAO;

	@Override
	public List<SystemMessageFunctionVO> findSystemMessageFunction() {
		return systemMessageFunctionDAO.findSystemMessageFunction();
	}

	@Override
	public void addMessageConfig(MessageConfigDTO messageConfigDTO) {
		systemMessageFunctionDAO.insertMessageConfig(messageConfigDTO);
	}

	@Override
	public void updateMessageConfig(MessageConfigDTO messageConfigDTO) {
		systemMessageFunctionDAO.updateMessageConfig(messageConfigDTO);
	}

	@Override
	public MessageConfigVO findMessageConfigByFunctionID(String functionID) {
		return systemMessageFunctionDAO
				.findMessageConfigByFunctionID(functionID);
	}

	@Override
	public void updateSystemMessageFunction(
			SystemMessageFunctionDTO systemMessageFunctionDTO) {
		systemMessageFunctionDAO
				.updateSystemMessageFunction(systemMessageFunctionDTO);
	}

	@Override
	public void addMessageTemplate(MessageTemplateDTO messageTemplateDTO) {
		systemMessageFunctionDAO.insertMessageTemplate(messageTemplateDTO);
	}

	@Override
	public void updateMessageTemplate(MessageTemplateDTO messageTemplateDTO) {
		systemMessageFunctionDAO.updateMessageTemplate(messageTemplateDTO);
	}

	@Override
	public SystemMessageFunctionVO findSystemMessageFunctionByMainID(
			String mainID) {
		return systemMessageFunctionDAO.findSystemMessageFunctionByMainID(mainID);
	}

	@Override
	public MessageTemplateVO findMessageTemplateByFunctionIDType(
			MessageTemplateDTO messageTemplateDTO) {
		return systemMessageFunctionDAO.findMessageTemplateByFunctionIDType(messageTemplateDTO);
	}

}
