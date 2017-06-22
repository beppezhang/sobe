package com.kpluswebup.web.admin.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.TransConfigDAO;
import com.kpluswebup.web.admin.system.service.TransConfigService;
import com.kpluswebup.web.domain.TransConfigDTO;
import com.kpluswebup.web.vo.TransConfigVO;

@Service
public class TransConfigServiceImpl implements TransConfigService{

	@Autowired
	private TransConfigDAO transConfigDAO;

	@Override
	public TransConfigVO findTransConfig() {
		return transConfigDAO.findTransConfig();
	}

	@Override
	public void addTransConfig(TransConfigDTO transConfigDTO) {
		transConfigDAO.insertTransConfig(transConfigDTO);
	}

	@Override
	public void updateTransConfig(TransConfigDTO transConfigDTO) {
		transConfigDAO.updateTransConfig(transConfigDTO);
	}

	
	
}
