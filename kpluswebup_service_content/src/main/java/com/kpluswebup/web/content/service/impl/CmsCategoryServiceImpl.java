package com.kpluswebup.web.content.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.content.dao.CmsCategoryDAO;
import com.kpluswebup.web.content.service.CmsCategoryService;
import com.kpluswebup.web.domain.CmsCategoryDTO;
import com.kpluswebup.web.vo.CmsCategoryVO;
import com.kpluswebup.web.vo.SystemAdvertPosionDTO;
import com.kpluswebup.web.vo.SystemAdvertPosionVO;

@Service
public class CmsCategoryServiceImpl implements CmsCategoryService {

    @Autowired
    private CmsCategoryDAO cmsCategoryDAO;

    @Override
    public List<CmsCategoryVO> findCmsCategoryByPagination(CmsCategoryDTO cmsCategoryDTO) {
        notNull(cmsCategoryDTO, "cmsCategoryDTO is null");
        Long count = cmsCategoryDAO.findCmsCategoryCount(cmsCategoryDTO);
        cmsCategoryDTO.doPage(count, cmsCategoryDTO.getPageNo(), cmsCategoryDTO.getPageSize());
        List<CmsCategoryVO> list = cmsCategoryDAO.findCmsCategoryByPagination(cmsCategoryDTO);
        return list;
    }

    public List<SystemAdvertPosionVO> findAllSystemAdvertPosion() {

        return cmsCategoryDAO.findAllSystemAdvertPosion();
    }

    @Override
    public Boolean deleteCmsCategoryByMainID(String mainID) {
        try {
            cmsCategoryDAO.deleteCmsCategoryByMainID(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addCmsCategory(CmsCategoryDTO cmsCategoryDTO) {
        notNull(cmsCategoryDTO, "cmsCategoryDTO is null");
        cmsCategoryDAO.insertCmsCategory(cmsCategoryDTO);
    }

    @Override
    public CmsCategoryVO findCmsCategoryByMainID(String mainID) {
        notNull(mainID, "mainID is null");
        return cmsCategoryDAO.findCmsCategoryByMainID(mainID);
    }

    @Override
    public void editCmsCategory(CmsCategoryDTO cmsCategoryDTO) {
        notNull(cmsCategoryDTO, "cmsCategoryDTO is null");
        notNull(cmsCategoryDTO.getMainID(), "MainID is null");
        cmsCategoryDAO.updateCmsCategoryByMainID(cmsCategoryDTO);
    }

    @Override
    public List<CmsCategoryVO> findCmsCategoryByType(Integer type) {
        notNull(type, "type is null");
        List<CmsCategoryVO> list = cmsCategoryDAO.findCmsCategoryByType(type);
        return list;
    }

	@Override
	public void addSystemAdvertPosion(SystemAdvertPosionDTO systemAdvertPosionDTO) {
		 notNull(systemAdvertPosionDTO, "systemAdvertPosionDTO is null");
	     cmsCategoryDAO.addSystemAdvertPosion(systemAdvertPosionDTO);
	}
	/**
     * 修改广告位置
     * 
     * @date 2014年11月10日
     * @author zhoulei
     * @param cmsCategoryDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public void updateSystemAdvertPosion(SystemAdvertPosionDTO systemAdvertPosionDTO) {
    	notNull(systemAdvertPosionDTO, "systemAdvertPosionDTO is null");
	     cmsCategoryDAO.updateSystemAdvertPosion(systemAdvertPosionDTO);
    }

}
