package com.kpluswebup.web.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kpluswebup.web.domain.SettlementDTO;
import com.kpluswebup.web.order.dao.SettlementDAO;
import com.kpluswebup.web.service.SettlementService;
import com.kpluswebup.web.vo.SettlementVO;

@Service
public class SettlementServiceImpl implements SettlementService{

	private static final Logger LOGGER = LogManager.getLogger(SettlementServiceImpl.class);
	
	@Autowired
    private SettlementDAO  settlementDAO;

	@Override
	public void addSettlement(SettlementDTO settlementDTO) {
		settlementDAO.addSettlement(settlementDTO);
	}

	@Override
	public List<SettlementVO> findSettlementByPagination(SettlementDTO settlementDTO) {
		notNull(settlementDTO, "settlementDTO is null");
        List<SettlementVO> list = null;
        Long count = settlementDAO.findSettlementCount(settlementDTO);
        settlementDTO.doPage(count, settlementDTO.getPageNo(), settlementDTO.getPageSize());
        list = settlementDAO.findSettlementByPagination(settlementDTO);
        return list;
	}

	@Override
	public void updateSettlement(SettlementDTO settlementDTO) {
        try {
            notNull(settlementDTO, "settlementDTO is null");
            settlementDAO.updateSettlement(settlementDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
		
	}

	@Transactional
	public void settlementOperation(String mainId, String status) {
		try {
			notNull(mainId, "mainId is null");
            notNull(status, "status is null");
            SettlementVO settlementVO = settlementDAO.findSettlement(mainId);
            SettlementDTO settlementDTO = new SettlementDTO();
            settlementDTO.setMainID(settlementVO.getMainID());
            settlementDTO.setSettlementTime(new Date());
            settlementDTO.setSettlementPeople(settlementVO.getModifier());
            settlementDTO.setStatus(2);
            settlementDAO.updateSettlement(settlementDTO);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
