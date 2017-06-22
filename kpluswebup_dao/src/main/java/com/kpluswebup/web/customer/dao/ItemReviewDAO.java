package com.kpluswebup.web.customer.dao;

import java.util.List;

import com.kpluswebup.web.domain.ItemReviewDTO;
import com.kpluswebup.web.vo.ItemReviewVO;


public interface ItemReviewDAO {
    public Integer deleteByPrimaryKey(String mainid);

    public Integer insert(ItemReviewDTO record);

    public Integer insertSelective(ItemReviewDTO record);

    ItemReviewDTO selectByPrimaryKey(String mainid);

    public Integer updateByPrimaryKeySelective(ItemReviewDTO record);

    public Integer updateByPrimaryKey(ItemReviewDTO record);
    
    public List<ItemReviewVO> findReviews(ItemReviewDTO itemReviewDTO);
    
    /**
     * 插入一条记录
     * @date 2015年1月3日
     * @author liudanqi
     * @param itemReviewDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertReview(ItemReviewDTO itemReviewDTO);
}