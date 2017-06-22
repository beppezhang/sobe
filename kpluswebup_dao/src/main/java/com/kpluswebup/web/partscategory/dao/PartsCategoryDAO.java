package com.kpluswebup.web.partscategory.dao;

import java.util.List;
import java.util.Map;

import com.kpluswebup.web.vo.PartsCategoryVo;

public interface PartsCategoryDAO {

	public List<PartsCategoryVo> findPartsCategoryOneLevel();
	
	public List<PartsCategoryVo> findPartsCategoryOneLevelNew(String mainID);
	
	public List<PartsCategoryVo> findPartsCategoryByParentID(String mainID);
	
	public List<PartsCategoryVo> findPartsCategoryByParentIDAndFLevel(Map m);
	
	public List<PartsCategoryVo> findPartsCategoryByLowLevel(PartsCategoryVo partsCategoryVo);
	
	public PartsCategoryVo findPartsCategoryByMainID(String mainID);
	
	public List<String> findPartsCategoryBottomForTwo(String mainID);
	
	public List<String> findPartsCategoryBottomForThird(String mainID);
	
	public List<PartsCategoryVo> findPartsCategoryByProductID(String productID);
	
}
