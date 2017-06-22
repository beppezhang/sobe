package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.domain.GroupSaleDTO;
import com.kpluswebup.web.vo.GroupSaleVO;


public interface GroupSaleService {

    public List<GroupSaleVO> findGroupSaleList(GroupSaleDTO groupSaleDTO);
    
    public void addGroupSale(GroupSaleVO groupSale, String gradeIds, String groupIds,String currentOperator);
    
    public Boolean deleteGroupSale(String mainIds);
    
    public GroupSaleVO findGroupSale(String mainID);
    
    public void updateGroupSale(GroupSaleVO groupSale, String gradeIds, String groupIds,String currentOperator);
    
    
}
