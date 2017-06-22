package com.kpluswebup.web.promotion.dao;

import java.util.List;

import com.kpluswebup.web.domain.GroupSaleDTO;
import com.kpluswebup.web.vo.GroupSaleVO;


public interface GroupSaleDAO {
    
    /**
     * 分页查询团购列表
     * @date 2014年11月13日
     * @author lupeng
     * @param groupSaleDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public List<GroupSaleVO> findGroupSaleByPagination(GroupSaleDTO groupSaleDTO);
    
    /**
     * 查询团购总记录数
     * @date 2014年11月13日
     * @author lupeng
     * @param groupSaleDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Long findGroupSaleCount(GroupSaleDTO groupSaleDTO);
    
    /**
     * 添加团购
     * @date 2014年11月13日
     * @author lupeng
     * @param groupSaleDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer addGroupSaleSelective(GroupSaleDTO groupSaleDTO);
    
    /**
     * 根据mainID删除团购
     * @date 2014年11月14日
     * @author lupeng
     * @param groupSaleDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer deleteGroupSaleByMainID(String mainId);
    
    /**
     * 根据mainID查询团购
     * @date 2014年11月14日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public GroupSaleVO findGroupSaleByMainID(String mainId);
    
    /**
     * 根据mainID更新团购
     * @date 2014年11月14日
     * @author lupeng
     * @param groupSaleDTO
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public Integer updateGroupSaleByMainID(GroupSaleDTO groupSaleDTO);
    
}