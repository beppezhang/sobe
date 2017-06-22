package com.kpluswebup.web.service.impl;

import static org.springframework.util.Assert.notNull;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.domain.GroupSaleDTO;
import com.kpluswebup.web.promotion.dao.GroupSaleDAO;
import com.kpluswebup.web.service.GroupSaleService;
import com.kpluswebup.web.vo.GroupSaleVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Service
public class GroupSaleServiceImpl implements GroupSaleService {

    private static final Logger LOGGER = LogManager.getLogger(GroupSaleServiceImpl.class);

    @Autowired
    private GroupSaleDAO        groupSaleDAO;

    public List<GroupSaleVO> findGroupSaleList(GroupSaleDTO groupSaleDTO) {
        try {
            notNull(groupSaleDTO, "preSaleDTO is null");
            groupSaleDTO.doPage(groupSaleDAO.findGroupSaleCount(groupSaleDTO), groupSaleDTO.getPageNo(),
                                groupSaleDTO.getPageSize());
            groupSaleDTO.setOrderByClause("ORDER BY gs.id DESC");
            List<GroupSaleVO> list = groupSaleDAO.findGroupSaleByPagination(groupSaleDTO);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public void addGroupSale(GroupSaleVO groupSale, String gradeIds, String groupIds, String currentOperator) {
        try {
            notNull(groupSale, "groupSale is null");
            GroupSaleDTO groupSaleDTO = saveOrUpdateGroupSale(groupSale, gradeIds, groupIds, currentOperator);
            groupSaleDAO.addGroupSaleSelective(groupSaleDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private GroupSaleDTO saveOrUpdateGroupSale(GroupSaleVO groupSale, String gradeIds, String groupIds,
                                               String currentOperator) throws ParseException {
        GroupSaleDTO groupSaleDTO = new GroupSaleDTO();
        if (StringUtil.isNotEmpty(groupSale.getMainID())) {
            groupSaleDTO.setModifyTime(new Date());
            groupSaleDTO.setModifier(currentOperator);
            groupSaleDTO.setMainID(groupSale.getMainID());
        } else {
            groupSaleDTO.setCreateTime(new Date());
            groupSaleDTO.setCreator(currentOperator);
            groupSaleDTO.setMainID(UUIDUtil.getUUID());
        }
        groupSaleDTO.setProductID(groupSale.getProductID());
        groupSaleDTO.setItemID(groupSale.getItemID());
        groupSaleDTO.setMinimum(groupSale.getMinimum());
        groupSaleDTO.setLimitCount(groupSale.getLimitCount());
        groupSaleDTO.setSalesPrice(groupSale.getSalesPrice());
        groupSaleDTO.setCustomerGrade(gradeIds);
        groupSaleDTO.setCustomerGroup(groupIds);
        groupSaleDTO.setDescription(groupSale.getDescription());
        if (StringUtil.isNotEmpty(groupSale.getFromDateStr())) groupSaleDTO.setFromDate(DateUtil.strintToDatetimeYMDHMS(groupSale.getFromDateStr()));
        if (StringUtil.isNotEmpty(groupSale.getEndDateStr())) groupSaleDTO.setEndDate(DateUtil.strintToDatetimeYMDHMS(groupSale.getEndDateStr()));
        return groupSaleDTO;
    }

    public Boolean deleteGroupSale(String mainIds) {
        try {
            notNull(mainIds, "mainIds is null");
            String ids[] = mainIds.split(",");
            for (String mainId : ids) {
                groupSaleDAO.deleteGroupSaleByMainID(mainId);
            }
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    public GroupSaleVO findGroupSale(String mainID) {
        try {
            notNull(mainID, "mainID is null");
            GroupSaleVO groupSaleVO = groupSaleDAO.findGroupSaleByMainID(mainID);
            if (groupSaleVO != null) {
                groupSaleVO.setFromDateStr(DateUtil.getDateFormat(groupSaleVO.getFromDate(), "yyyy-MM-dd HH:mm:ss"));
                groupSaleVO.setEndDateStr(DateUtil.getDateFormat(groupSaleVO.getEndDate(), "yyyy-MM-dd HH:mm:ss"));
            }
            return groupSaleVO;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public void updateGroupSale(GroupSaleVO groupSale, String gradeIds, String groupIds, String currentOperator) {
        try {
            notNull(groupSale, "groupSale is null");
            GroupSaleDTO groupSaleDTO = saveOrUpdateGroupSale(groupSale, gradeIds, groupIds, currentOperator);
            groupSaleDAO.updateGroupSaleByMainID(groupSaleDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
