package com.kpluswebup.web.member.service;

import java.util.List;

import com.kpluswebup.web.domain.ItemReviewDTO;
import com.kpluswebup.web.vo.ItemReviewVO;

public interface ItemReviewService {
    
    /**
     * 根据条件查询评论
     * @date 2014年12月29日
     * @author liudanqi
     * @param itemReviewDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
	public List<ItemReviewVO> findReviews(ItemReviewDTO itemReviewDTO);
	
	/**
     * 插入一条记录
     * @date 2015年1月3日
     * @author liudanqi
     * @param itemReviewDTO
     * @since JDK 1.6
     * @Description
     */
    public void addtReview(ItemReviewDTO itemReviewDTO);
}
	
