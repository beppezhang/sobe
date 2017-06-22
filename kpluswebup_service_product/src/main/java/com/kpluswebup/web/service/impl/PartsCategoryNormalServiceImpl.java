package com.kpluswebup.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.partscategory.dao.PartsCategoryNormalDAO;
import com.kpluswebup.web.service.PartsCategoryNormalService;
import com.kpluswebup.web.vo.PartsCategoryVo;

@Service
public class PartsCategoryNormalServiceImpl implements
		PartsCategoryNormalService {
	
	@Autowired
	private PartsCategoryNormalDAO  partsCategoryNormalDAO;
	
	public PartsCategoryNormalServiceImpl() {
		super();
	}

	public PartsCategoryNormalServiceImpl(
			PartsCategoryNormalDAO partsCategoryNormalDAO) {
		super();
		this.partsCategoryNormalDAO = partsCategoryNormalDAO;
	}

	@Override
	public List<PartsCategoryVo> findPartsCategoryNormalOneLevel() {
		
		return partsCategoryNormalDAO.findPartsCategoryNormalOneLevel();
	}

	@Override
	public List<PartsCategoryVo> findPartsCategoryNormalByAncestorID(
			String mainID) {
		
		return partsCategoryNormalDAO.findPartsCategoryNormalByAncestorID(mainID);
	}

	@Override
	public List<PartsCategoryVo> findPartsCategoryNormalByFlevel(int flevel) {
		
		return partsCategoryNormalDAO.findPartsCategoryNormalByFlevel(flevel);
	}

	@Override
	public PartsCategoryVo findPartsCategoryNormalByMainID(String mainID) {
		
		return partsCategoryNormalDAO.findPartsCategoryNormalByMainId(mainID);
	}
	
	
	
}
