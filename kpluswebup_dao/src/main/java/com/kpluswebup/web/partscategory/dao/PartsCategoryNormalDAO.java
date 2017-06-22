package com.kpluswebup.web.partscategory.dao;

import java.util.List;
import java.util.Map;

import com.kpluswebup.web.vo.FocusEntity;
import com.kpluswebup.web.vo.PartsCategoryVo;

public interface PartsCategoryNormalDAO {

	
	public List<PartsCategoryVo> findPartsCategoryNormalOneLevel();
	
	public List<PartsCategoryVo> findPartsCategoryNormalByAncestorID(String mainID);
	
	public List<PartsCategoryVo> findPartsCategoryNormalByFlevel(int flevel);
	
	//根据mainId查找
	public PartsCategoryVo findPartsCategoryNormalByMainId(String mainID);
	
}
