package com.kpluswebup.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kpluswebup.web.domain.ItemPropDTO;
import com.kpluswebup.web.domain.ItemPropValueDTO;
import com.kpluswebup.web.product.dao.ItemPropDAO;
import com.kpluswebup.web.service.ItemPropService;
import com.kpluswebup.web.vo.ItemPropVO;
import com.kpluswebup.web.vo.ItemPropValueVO;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Service
public class ItemPropServiceImpl implements ItemPropService {

	@Autowired
	/**商品属性DAO*/
	private ItemPropDAO itemPropDAO;

	/**
	 * 
	 * TODO 分页查询规格. 
	 * @author lei.zhou@kata.com.cn
	 * 
	 * @see com.kpluswebup.web.service.ItemPropService#findItemPropByPagination(com.kpluswebup.web.domain.ItemPropDTO)
	 */
	public List<ItemPropVO> findItemPropByPagination(ItemPropDTO itemPropDTO) {
		itemPropDTO.doPage(itemPropDAO.findItemPropCount(itemPropDTO),
				itemPropDTO.getPageNo(), itemPropDTO.getPageSize());
		List<ItemPropVO> list = itemPropDAO
				.findItemPropByPagination(itemPropDTO);
		for (ItemPropVO itemPropVO : list) {
			List<ItemPropValueVO> itemPropValues = itemPropDAO
					.findAllItemPropValueByItemPropMianID(itemPropVO
							.getMainID());
			itemPropVO.setItemPropValues(itemPropValues);
			itemPropVO.setItemPropValue(itemPropValues);
		}
		return list;
	}

	/**
	 * 
	 * TODO 添加商品属性（可选）.
	 * 
	 * @see com.kpluswebup.web.service.ItemPropService#addItemProp(com.kpluswebup.web.domain.ItemPropDTO,
	 *      java.lang.String)
	 */
	public void addItemProp(ItemPropDTO itemPropDTO, String itemPropValue) {
		String itemPropMainID = UUIDUtil.getUUID();
		itemPropDTO.setMainID(itemPropMainID);
		if (StringUtil.isNotEmpty(itemPropValue)) {
			String[] value = itemPropValue.split(",");
			for (String name : value) {
				ItemPropValueDTO itemPropValueDTO = new ItemPropValueDTO();
				itemPropValueDTO.setName(name);
				itemPropValueDTO.setmainID(UUIDUtil.getUUID());
				itemPropValueDTO.setitemPropID(itemPropMainID);
				itemPropDAO.insertItemPropValue(itemPropValueDTO);
			}
		}
		itemPropDAO.insertItemProp(itemPropDTO);

	}

	/**
	 * 
	 * TODO 删除商品属性. 
	 * user lei.zhou@kata.com.cn
	 * 
	 * @see com.kpluswebup.web.service.ItemPropService#deleteItemtPropVale(java.lang.String)
	 */
	public Boolean deleteItemtPropVale(String mainID) {
		try {
			itemPropDAO.deleteItemtPropValeByMainID(mainID);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * TODO 删除商品属性（可选）. user lei.zhou@kata.com.cn
	 * 
	 * @see com.kpluswebup.web.service.ItemPropService#deleteItemPropByMainID(java.lang.String)
	 */
	@Transactional
	public Boolean deleteItemPropByMainID(String mainID) {
		try {
			itemPropDAO.deleteItemPropByMainID(mainID);
			itemPropDAO.deleteItemtPropValeByMainID(mainID);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * TODO 简单描述该方法的实现功能（可选）. user lei.zhou@kata.com.cn
	 * 
	 * @see com.kpluswebup.web.service.ItemPropService#insertItemPropValue(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public Boolean insertItemPropValue(String propValue, String itemPropMainID,
			String currentOperator) {
		try {
			ItemPropValueDTO itemPropValueDTO = new ItemPropValueDTO();
			itemPropValueDTO.setName(propValue);
			itemPropValueDTO.setmainID(UUIDUtil.getUUID());
			itemPropValueDTO.setitemPropID(itemPropMainID);
			itemPropValueDTO.setCreator(currentOperator);
			itemPropDAO.insertItemPropValue(itemPropValueDTO);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * user lei.zhou@kata.com.cn
	 * @see com.kpluswebup.web.service.ItemPropService#findAllItemPropValueByItemPropMianID(java.lang.String)
	 */
	public List<ItemPropValueVO> findAllItemPropValueByItemPropMianID(
			String itemPropID) {
		return itemPropDAO.findAllItemPropValueByItemPropMianID(itemPropID);
	}

}
