package com.kpluswebup.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.domain.FlashSaleDTO;
import com.kpluswebup.web.promotion.dao.FlashSaleDAO;
import com.kpluswebup.web.service.FlashSaleService;
import com.kpluswebup.web.vo.FlashSaleVO;

@Service
public class FlashSaleServiceImpl implements FlashSaleService {

	@Autowired
	private FlashSaleDAO flashSaleDAO;

	@Override
	public List<FlashSaleVO> findFlashSaleByPagination(FlashSaleDTO flashSaleDTO) {
		Long count = flashSaleDAO.findFlashSaleCount(flashSaleDTO);
		flashSaleDTO.doPage(count, flashSaleDTO.getPageNo(),
				flashSaleDTO.getPageSize());
		List<FlashSaleVO> list = flashSaleDAO
				.findFlashSaleByPagination(flashSaleDTO);
		return list;
	}

	@Override
	public void addFlashSale(FlashSaleDTO flashSaleDTO) {
		flashSaleDAO.insertFlashSale(flashSaleDTO);
	}

	@Override
	public void updateFlashSale(FlashSaleDTO flashSaleDTO) {
		flashSaleDAO.updateFlashSale(flashSaleDTO);
	}

	@Override
	public Boolean deleteFlashSale(String mainIds) {
		try {
			String ids[] = mainIds.split(",");
			for (String mainId : ids) {
				flashSaleDAO.deleteFlashSale(mainId);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public FlashSaleVO findFlashSaleByMainID(String mainID) {
		return flashSaleDAO.findFlashSaleByMainID(mainID);
	}

}
