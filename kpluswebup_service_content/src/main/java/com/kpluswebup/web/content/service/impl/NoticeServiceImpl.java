package com.kpluswebup.web.content.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.content.dao.NoticeDAO;
import com.kpluswebup.web.content.service.NoticeService;
import com.kpluswebup.web.domain.CmsContentDTO;
import com.kpluswebup.web.vo.CmsContentVO;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDAO noticeDAO;

    @Override
    public List<CmsContentVO> findNoticeByPagination(CmsContentDTO cmsContentDTO) {
        notNull(cmsContentDTO, "cmsContentDTO is null");
        Long count = noticeDAO.findNoticeCount(cmsContentDTO);
        cmsContentDTO.doPage(count, cmsContentDTO.getPageNo(), cmsContentDTO.getPageSize());
        List<CmsContentVO> list = noticeDAO.findNoticeByPagination(cmsContentDTO);
        return list;
    }

    @Override
    public Boolean deleteNoticeByMainID(String mainID) {
        try {
            noticeDAO.deleteNoticeByMainID(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addNotice(CmsContentDTO cmsContentDTO) {
        notNull(cmsContentDTO, "cmsContentDTO is null");
        noticeDAO.insertNotice(cmsContentDTO);
    }

    @Override
    public CmsContentVO findNoticeByMainID(String mainID) {
        notNull(mainID, "mainID is null");
        return noticeDAO.findNoticeByMainID(mainID);
    }

    @Override
    public void editNotice(CmsContentDTO cmsContentDTO) {
        notNull(cmsContentDTO, "cmsContentDTO is null");
        notNull(cmsContentDTO.getMainID(), "MainID is null");
        noticeDAO.updateNoticeByMainID(cmsContentDTO);
    }

	@Override
	public List<CmsContentVO> findSupplierNotice(int i) {
		
		return noticeDAO.findSupplierNotice(i);
	}

}
