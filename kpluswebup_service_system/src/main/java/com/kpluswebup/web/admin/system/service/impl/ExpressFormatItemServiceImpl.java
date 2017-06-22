package com.kpluswebup.web.admin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.ExpressFormatItemDAO;
import com.kpluswebup.web.admin.system.service.ExpressFormatItemService;
import com.kpluswebup.web.domain.ExpressFormatItemDTO;
import com.kpluswebup.web.vo.ExpressFormatItemVO;
import com.kpuswebup.comom.util.StringUtil;

@Service
public class ExpressFormatItemServiceImpl implements ExpressFormatItemService {

	@Autowired
	private ExpressFormatItemDAO expressFormatItemDAO;

	@Override
	public List<ExpressFormatItemVO> findExpressFormatItemByFormatID(
			String formatID) {
		return expressFormatItemDAO.findExpressFormatItemByFormatID(formatID);
	}

	@Override
	public void updateExpressFormatItem(String formatID, String[] formatItems,String currentOperator) {
		expressFormatItemDAO.deleteExpressFormatItemByformatID(formatID);
		for (int i = 0; i < formatItems.length; i++) {
			String str = formatItems[i];
			if (StringUtil.isNotEmpty(str)) {
				str = str.replaceAll("\\|", ",");
				String[] items = str.split(",");
				ExpressFormatItemDTO expressFormatItemDTO = new ExpressFormatItemDTO();
				if (StringUtil.isNotEmpty(formatID)) {
					expressFormatItemDTO.setFormatID(formatID);
				}
				if (StringUtil.isNotEmpty(items[0])) {
					expressFormatItemDTO.setItem(Integer.valueOf(items[0]));
				}
				if (StringUtil.isNotEmpty(items[1])) {
					expressFormatItemDTO.setXray(Double.valueOf(items[1]));
				}
				if (StringUtil.isNotEmpty(items[2])) {
					expressFormatItemDTO.setYray(Double.valueOf(items[2]));
				}
				if (StringUtil.isNotEmpty(items[3])) {
					expressFormatItemDTO.setWidth(Double.valueOf(items[3]));
				}
				if (StringUtil.isNotEmpty(items[4])) {
					expressFormatItemDTO.setHeight(Double.valueOf(items[4]));
				}
				if (StringUtil.isNotEmpty(items[5])) {
					expressFormatItemDTO.setWordsize(Integer.valueOf(items[5]));
				}
				if (StringUtil.isNotEmpty(items[6])) {
					expressFormatItemDTO.setFont(Integer.valueOf(items[6]));
				}
				if (StringUtil.isNotEmpty(items[7])) {
					expressFormatItemDTO
							.setIntervals(Integer.valueOf(items[7]));
				}
				if (StringUtil.isNotEmpty(items[8])) {
					expressFormatItemDTO
							.setLinewidth(Integer.valueOf(items[8]));
				}
				if (StringUtil.isNotEmpty(items[9])) {
					expressFormatItemDTO.setBold(Integer.valueOf(items[9]));
				}
				if (StringUtil.isNotEmpty(items[10])) {
					expressFormatItemDTO.setItalic(Integer.valueOf(items[10]));
				}
				if (StringUtil.isNotEmpty(items[11])) {
					expressFormatItemDTO
							.setPosition(Integer.valueOf(items[11]));
				}
				if (StringUtil.isNotEmpty(items[12])) {
					expressFormatItemDTO.setValue(items[12]);
				}
				expressFormatItemDTO.setCreator(currentOperator);
				expressFormatItemDAO
						.insertExpressFormatItem(expressFormatItemDTO);
			}
		}
	}

	@Override
	public ExpressFormatItemVO findExpressFormatItemByID(Long id) {
		return expressFormatItemDAO.findExpressFormatItemByID(id);
	}

	@Override
	public Boolean deleteExpressFormatItemByformatID(String formatID) {
		try {
			expressFormatItemDAO.deleteExpressFormatItemByformatID(formatID);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
