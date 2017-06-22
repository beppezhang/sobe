package com.kpluswebup.web.customer.dao;

import java.util.List;

import com.kpluswebup.web.domain.ItemConsultingDTO;
import com.kpluswebup.web.vo.ItemConsultingVO;


public interface ItemConsultingDAO {
    public Integer deleteByPrimaryKey(String mainid);

    public Integer insert(ItemConsultingDTO record);

    public Integer insertSelective(ItemConsultingDTO record);

    ItemConsultingDTO selectByPrimaryKey(String mainid);

    public Integer updateByPrimaryKeySelective(ItemConsultingDTO record);

    public Integer updateByPrimaryKey(ItemConsultingDTO record);
    
    /**
     * 根据条件查询咨询
     * @date 2014年12月29日
     * @author liudanqi
     * @param itemConsultingDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemConsultingVO> findConsults(ItemConsultingDTO itemConsultingDTO);
    
    /**
     * 添加咨询
     * @date 2014年12月30日
     * @author liudanqi
     * @param dto
     * @since JDK 1.6
     * @Description
     */
    public void insertConsulting(ItemConsultingDTO dto);
}