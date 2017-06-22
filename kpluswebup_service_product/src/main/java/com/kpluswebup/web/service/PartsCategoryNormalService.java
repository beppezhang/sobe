package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.vo.PartsCategoryVo;

public interface PartsCategoryNormalService {

	public List<PartsCategoryVo> findPartsCategoryNormalOneLevel();
	 
	public List<PartsCategoryVo> findPartsCategoryNormalByAncestorID(String mainID);
	
	public List<PartsCategoryVo> findPartsCategoryNormalByFlevel(int flevel);
	
	//根据mainId查找
	public PartsCategoryVo findPartsCategoryNormalByMainID(String mainID);
}
