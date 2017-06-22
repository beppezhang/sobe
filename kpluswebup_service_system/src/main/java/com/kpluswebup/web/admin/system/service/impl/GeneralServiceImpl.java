package com.kpluswebup.web.admin.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.GeneralDAO;
import com.kpluswebup.web.admin.system.service.GeneralService;
import com.kpluswebup.web.domain.GeneralDTO;
import com.kpluswebup.web.vo.GeneralVO;

@Service
public class GeneralServiceImpl implements GeneralService{

	@Autowired
	private GeneralDAO generalDAO;

	@Override
	public GeneralVO findGeneral() {
		return generalDAO.findGeneral();
	}

	@Override
	public void addGeneral(GeneralDTO generalDTO) {
		generalDAO.insertGeneral(generalDTO);
	}

	@Override
	public void updateGeneral(GeneralDTO generalDTO) {
		generalDAO.updateGeneral(generalDTO);
	}
	
	
}
