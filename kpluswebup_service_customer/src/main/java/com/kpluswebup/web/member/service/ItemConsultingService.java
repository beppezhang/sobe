package com.kpluswebup.web.member.service;

import java.util.List;

import com.kpluswebup.web.domain.ItemConsultingDTO;
import com.kpluswebup.web.vo.ItemConsultingVO;

public interface ItemConsultingService {
    
    /**
     * 根据条件查询咨询
     * @date 2014年12月29日
     * @author liudanqi
     * @param itemReviewDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemConsultingVO> findConsults(ItemConsultingDTO itemConsultingDTO);
    
    /**
     * 添加咨询
     * @date 2014年12月30日
     * @author liudanqi
     * @param itemConsultingDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public void addItemConsulting(ItemConsultingDTO itemConsultingDTO);
}
	
