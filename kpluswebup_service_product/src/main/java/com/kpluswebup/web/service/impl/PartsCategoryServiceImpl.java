package com.kpluswebup.web.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.partscategory.dao.PartsCategoryDAO;
import com.kpluswebup.web.service.PartsCategoryService;
import com.kpluswebup.web.vo.PartsCategoryVo;

@Service
public class PartsCategoryServiceImpl implements PartsCategoryService {

	@Autowired
	private PartsCategoryDAO partsCategoryDAO;

	@Override
	public List<PartsCategoryVo> findPartsCategoryLevel() {
		List<PartsCategoryVo> partsCategoryRoots = partsCategoryDAO
				.findPartsCategoryOneLevel();
		for (PartsCategoryVo partsCategoryVo : partsCategoryRoots) {
			List<PartsCategoryVo> partsCategorysTwo = partsCategoryDAO
					.findPartsCategoryByParentID(partsCategoryVo.getMainID());
			partsCategoryVo.setLevelTwo(partsCategorysTwo);
			/*
			for (PartsCategoryVo partsCategoryVo2 : partsCategorysTwo) {
				List<PartsCategoryVo> partsCategorysThird = partsCategoryDAO
						.findPartsCategoryByParentID(partsCategoryVo2
								.getMainID());
				partsCategoryVo2.setLevelThree(partsCategorysThird);
			}
			*/
		}
		return partsCategoryRoots;
	}

	@Override
	public List<PartsCategoryVo> findPartsCategoryByParentId(String mainID) {
		return partsCategoryDAO.findPartsCategoryByParentID(mainID);
	}

	@Override
	public List<PartsCategoryVo> findPartsCategoryLevelOnly() {
		return partsCategoryDAO.findPartsCategoryOneLevel();
	}
	
	@Override
	public List<PartsCategoryVo> findPartsCategoryLevelOnlyNew(String mainID) {
		return partsCategoryDAO.findPartsCategoryOneLevelNew(mainID);
	}

	@Override
	public List<PartsCategoryVo> findPartsCategoryByParentIDAndFLevel(
			Map m) {
		return partsCategoryDAO
				.findPartsCategoryByParentIDAndFLevel(m);
	}

	@Override
	public List<PartsCategoryVo> findPartsCategoryByLowLevel(
			PartsCategoryVo partsCategoryVo) {
		return partsCategoryDAO.findPartsCategoryByLowLevel(partsCategoryVo);
	}

	@Override
	public PartsCategoryVo findPartsCategoryByMainID(String mainID) {
		notNull(mainID, "partsCategory mainID is null");
		return partsCategoryDAO.findPartsCategoryByMainID(mainID);
	}
	@Override
	public List<String> findPartsCategoryBottomForThird(String mainID) {
		return partsCategoryDAO.findPartsCategoryBottomForThird(mainID);
	}
	@Override
	public List<String> findPartsCategoryBottomForTwo(String mainID) {
		return partsCategoryDAO.findPartsCategoryBottomForTwo(mainID);
	}
	@Override
	public List<PartsCategoryVo> findPartsCategoryByProductID(String productID) {
		return partsCategoryDAO.findPartsCategoryByProductID(productID);
	}
}
