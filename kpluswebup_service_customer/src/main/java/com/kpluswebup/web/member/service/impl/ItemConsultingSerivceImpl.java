package com.kpluswebup.web.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.customer.dao.ItemConsultingDAO;
import com.kpluswebup.web.domain.ItemConsultingDTO;
import com.kpluswebup.web.member.service.ItemConsultingService;
import com.kpluswebup.web.vo.ItemConsultingVO;

@Service
public class ItemConsultingSerivceImpl implements ItemConsultingService {

    @Autowired
    private ItemConsultingDAO dao;
    
    @Override
    public List<ItemConsultingVO> findConsults(ItemConsultingDTO itemConsultingDTO) {
        return dao.findConsults(itemConsultingDTO);
    }

    @Override
    public void addItemConsulting(ItemConsultingDTO itemConsultingDTO) {
        dao.insertConsulting(itemConsultingDTO);
    }
    
   
   
}
