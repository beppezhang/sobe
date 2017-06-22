package com.kpluswebup.web.service.impl;

import static org.springframework.util.Assert.notNull;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.domain.PreSaleDTO;
import com.kpluswebup.web.promotion.dao.PreSaleDAO;
import com.kpluswebup.web.service.PreSaleService;
import com.kpluswebup.web.vo.PreSaleVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Service
public class PreSaleServiceImpl implements PreSaleService {

    private static final Logger LOGGER = LogManager.getLogger(PreSaleServiceImpl.class);

    @Autowired
    private PreSaleDAO          preSaleDAO;

    public List<PreSaleVO> findPreSaleList(PreSaleDTO preSaleDTO) {
        try {
            notNull(preSaleDTO, "preSaleDTO is null");
            preSaleDTO.doPage(preSaleDAO.findPreSaleCount(preSaleDTO), preSaleDTO.getPageNo(), preSaleDTO.getPageSize());
            preSaleDTO.setOrderByClause("ORDER BY ps.id DESC");
            List<PreSaleVO> list = preSaleDAO.findPreSaleByPagination(preSaleDTO);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public Boolean deletePreSale(String mainIds) {
        try {
            notNull(mainIds, "mainIds is null");
            String ids[] = mainIds.split(",");
            for (String mainId : ids) {
                preSaleDAO.deletePreSaleByMainID(mainId);
            }
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    public void addPreSale(PreSaleVO preSale) {
        try {
            notNull(preSale, "preSale is null");
            PreSaleDTO preSaleDTO = saveOrUpdatePreSale(preSale);
            preSaleDTO.setCreateTime(new Date());
            preSaleDTO.setCreator(preSale.getCreator());
            preSaleDAO.addPreSaleSelective(preSaleDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public PreSaleVO findPreSale(String mainID) {
        try {
            notNull(mainID, "mainID is null");
            if (StringUtil.isNotEmpty(mainID)) {
                PreSaleVO preSaleVO = preSaleDAO.findPreSaleByMainID(mainID);
                if (preSaleVO != null) {
                    preSaleVO.setFromDateStr(DateUtil.getDateFormat(preSaleVO.getFromDate(), "yyyy-MM-dd HH:mm:ss"));
                    preSaleVO.setEndDateStr(DateUtil.getDateFormat(preSaleVO.getEndDate(), "yyyy-MM-dd HH:mm:ss"));
                }
                return preSaleVO;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public void updatePreSale(PreSaleVO preSale) {
        try {
            notNull(preSale, "preSale is null");
            PreSaleDTO preSaleDTO = saveOrUpdatePreSale(preSale);
            preSaleDTO.setModifyTime(new Date());
            preSaleDTO.setModifier(preSale.getModifier());
            preSaleDAO.updatePreSaleByMainID(preSaleDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private PreSaleDTO saveOrUpdatePreSale(PreSaleVO preSale) throws ParseException {
        PreSaleDTO preSaleDTO = new PreSaleDTO();
        if (StringUtil.isNotEmpty(preSale.getMainID())) {
            preSaleDTO.setMainID(preSale.getMainID());
        } else {
            preSaleDTO.setMainID(UUIDUtil.getUUID());
        }
        preSaleDTO.setProductID(preSale.getProductID());
        preSaleDTO.setItemID(preSale.getItemID());
        preSaleDTO.setSalesPrice(preSale.getSalesPrice());
        preSaleDTO.setMaxmumQty(preSale.getMaxmumQty());
        if (StringUtil.isNotEmpty(preSale.getFromDateStr())) preSaleDTO.setFromDate(DateUtil.strintToDatetimeYMDHMS(preSale.getFromDateStr()));
        if (StringUtil.isNotEmpty(preSale.getEndDateStr())) preSaleDTO.setEndDate(DateUtil.strintToDatetimeYMDHMS(preSale.getEndDateStr()));
        preSaleDTO.setDescription(preSale.getDescription());
        preSaleDTO.setScore(preSale.getScore());
        preSaleDTO.setScorePrice(preSale.getSalesPrice());
        preSaleDTO.setSalesScore(preSale.getSalesScore());
        preSaleDTO.setPicUrl(preSale.getPicUrl());
        return preSaleDTO;
    }

}
