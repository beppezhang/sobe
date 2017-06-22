package com.kpluswebup.web.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.customer.dao.ItemReviewDAO;
import com.kpluswebup.web.domain.ItemReviewDTO;
import com.kpluswebup.web.member.service.ItemReviewService;
import com.kpluswebup.web.vo.ItemReviewVO;

@Service
public class ItemReviewSerivceImpl implements ItemReviewService {

    @Autowired
    private ItemReviewDAO dao;
    
    @Override
    public List<ItemReviewVO> findReviews(ItemReviewDTO itemReviewDTO) {
        return dao.findReviews(itemReviewDTO);
    }

    @Override
    public void addtReview(ItemReviewDTO itemReviewDTO) {
        dao.insertReview(itemReviewDTO);
    }

   
}
