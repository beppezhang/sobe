package com.kpluswebup.web.content.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.content.dao.HelpDAO;
import com.kpluswebup.web.content.service.HelpService;
import com.kpluswebup.web.domain.CmsCategoryDTO;
import com.kpluswebup.web.domain.CmsHelpCenterDTO;
import com.kpluswebup.web.vo.CmsCategoryVO;
import com.kpluswebup.web.vo.CmsHelpCenterVO;

@Service
public class HelpServiceImpl implements HelpService {

	@Autowired
	private HelpDAO helpDAO;

	@Override
	public List<CmsHelpCenterVO> findHelpByPagination(
			CmsHelpCenterDTO cmsHelpCenterDTO) {
		notNull(cmsHelpCenterDTO, "cmsHelpCenterDTO is null");
		Long count = helpDAO.findHelpCount(cmsHelpCenterDTO);
		cmsHelpCenterDTO.doPage(count, cmsHelpCenterDTO.getPageNo(),
				cmsHelpCenterDTO.getPageSize());
		List<CmsHelpCenterVO> list = helpDAO
				.findHelpByPagination(cmsHelpCenterDTO);
		return list;
	}

	@Override
	public Boolean deleteHelpByMainID(String mainID) {
		try {
			helpDAO.deleteHelpByMainID(mainID);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void addHelp(CmsHelpCenterDTO cmsHelpCenterDTO) {
		notNull(cmsHelpCenterDTO, "cmsHelpCenterDTO is null");
		helpDAO.insertHelp(cmsHelpCenterDTO);
	}

	@Override
	public CmsHelpCenterVO findHelpByMainID(String mainID) {
		notNull(mainID, "mainID is null");
		return helpDAO.findHelpByMainID(mainID);
	}

	@Override
	public void editHelp(CmsHelpCenterDTO cmsHelpCenterDTO) {
		notNull(cmsHelpCenterDTO, "cmsHelpCenterDTO is null");
		notNull(cmsHelpCenterDTO.getMainID(), "MainID is null");
		helpDAO.updateHelpByMainID(cmsHelpCenterDTO);
	}

	@Override
	public List<CmsCategoryVO> findHelpCategoryNameList(CmsCategoryDTO cmsCategoryDTO) {
		
		List<CmsCategoryVO> list = helpDAO.findHelpCategoryNameList(cmsCategoryDTO);
		if (list != null && list.size() > 0) {
			for(CmsCategoryVO src:list){				
				List<CmsHelpCenterVO> list2 = helpDAO.frinCmsHelpCenterList(src.getMainID());
				src.setCmsHelpCenterVO(list2);
			}
		}
		return list;
	}

    @Override
    public List<CmsHelpCenterVO> findHelps() {
        return helpDAO.findHelps();
    }
	
}
