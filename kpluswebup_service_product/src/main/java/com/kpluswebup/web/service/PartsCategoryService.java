package com.kpluswebup.web.service;

import java.util.List;
import java.util.Map;

import com.kpluswebup.web.vo.PartsCategoryVo;

public interface PartsCategoryService {
	public List<PartsCategoryVo> findPartsCategoryLevel();
	
	public List<PartsCategoryVo> findPartsCategoryLevelOnly();
	
	public List<PartsCategoryVo> findPartsCategoryLevelOnlyNew(String mainID);
	
	public List<PartsCategoryVo> findPartsCategoryByParentId(String mainID);
	
	public List<PartsCategoryVo> findPartsCategoryByParentIDAndFLevel(Map m);
	
	public List<PartsCategoryVo> findPartsCategoryByLowLevel(PartsCategoryVo partsCategoryVo);
	
	public PartsCategoryVo findPartsCategoryByMainID(String mainID);
	
	public List<String> findPartsCategoryBottomForTwo(String mainID);
	
	public List<String> findPartsCategoryBottomForThird(String mainID);	
	
	public List<PartsCategoryVo> findPartsCategoryByProductID(String productID);
	
}
