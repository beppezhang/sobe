package com.kpluswebup.web.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.customer.dao.WechatMessageDAO;
import com.kpluswebup.web.domain.WechatMessageDTO;
import com.kpluswebup.web.member.service.WechatMessageService;
import com.kpluswebup.web.vo.WechatMessageVO;

@Service
public class WechatMessageServiceImpl implements WechatMessageService {

    @Autowired
    private WechatMessageDAO wechatMessageDAO;

    @Override
    public List<WechatMessageVO> findWechatMessageByPagination(WechatMessageDTO wechatMessageDTO) {
        Long count = wechatMessageDAO.findWechatMessageCount(wechatMessageDTO);
        wechatMessageDTO.doPage(count, wechatMessageDTO.getPageNo(), wechatMessageDTO.getPageSize());
        List<WechatMessageVO> list = wechatMessageDAO.findWechatMessageByPagination(wechatMessageDTO);
        return list;
    }

    @Override
    public WechatMessageVO findWechatMessageByMainID(String mainID) {
        return wechatMessageDAO.findWechatMessageByMainID(mainID);
    }

    @Override
    public void addWechatMessage(WechatMessageDTO wechatMessageDTO) {
        wechatMessageDAO.insertWechatMessage(wechatMessageDTO);
        
    }

    @Override
    public void updateWechatMessage(WechatMessageDTO wechatMessageDTO) {
        wechatMessageDAO.updateWechatMessage(wechatMessageDTO);
    }

    @Override
    public List<WechatMessageVO> findWechatMessageByParentID(String parentID) {
        return wechatMessageDAO.findWechatMessageByParentID(parentID);
    }

}
