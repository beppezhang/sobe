package com.kpluswebup.web.content.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.content.dao.NewsDAO;
import com.kpluswebup.web.content.service.NewsService;
import com.kpluswebup.web.domain.CmsContentDTO;
import com.kpluswebup.web.vo.CmsContentVO;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDAO newsDAO;

	@Override
	public List<CmsContentVO> findNewsByPagination(
			CmsContentDTO cmsContentDTO) {
		notNull(cmsContentDTO, "cmsContentDTO is null");
		Long count = newsDAO.findNewsCount(cmsContentDTO);
		cmsContentDTO.doPage(count, cmsContentDTO.getPageNo(),
				cmsContentDTO.getPageSize());
		List<CmsContentVO> list = newsDAO
				.findNewsByPagination(cmsContentDTO);
		return list;
	}

	@Override
	public Boolean deleteNewsByMainID(String mainID) {
		try {
			newsDAO.deleteNewsByMainID(mainID);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void addNews(CmsContentDTO cmsContentDTO) {
		notNull(cmsContentDTO, "cmsContentDTO is null");
		newsDAO.insertNews(cmsContentDTO);
	}

	@Override
	public CmsContentVO findNewsByMainID(String mainID) {
		notNull(mainID, "mainID is null");
		return newsDAO.findNewsByMainID(mainID);
	}

	@Override
	public void editNews(CmsContentDTO cmsContentDTO) {
		notNull(cmsContentDTO, "cmsContentDTO is null");
		notNull(cmsContentDTO.getMainID(), "MainID is null");
		newsDAO.updateNewsByMainID(cmsContentDTO);
	}

	@Override
	public List<CmsContentVO> findHotNews(CmsContentDTO cmsContentDTO) {
		notNull(cmsContentDTO, "cmsContentDTO is null");
		List<CmsContentVO> list = newsDAO.findHotNews(cmsContentDTO);
		return list;
	}

    @Override
    public CmsContentVO findNewsPrev(Long id) {
        return newsDAO.findNewsPrev(id);
    }

    @Override
    public CmsContentVO findNewsNext(Long id) {
        return newsDAO.findNewsNext(id);
    }

	@Override
	public List<CmsContentVO> findIndexNewsByNum(int num) {
		if(num < 0)
			return null;
		return newsDAO.findIndexNewsByNum(num);
	}

}
