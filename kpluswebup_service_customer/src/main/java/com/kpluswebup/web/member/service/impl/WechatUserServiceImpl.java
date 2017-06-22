package com.kpluswebup.web.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.customer.dao.WechatUserDAO;
import com.kpluswebup.web.domain.WechatUserDTO;
import com.kpluswebup.web.member.service.WechatUserService;
import com.kpluswebup.web.vo.WechatUserVO;

@Service
public class WechatUserServiceImpl implements WechatUserService {

    @Autowired
    private WechatUserDAO wechatUserDAO;

    @Override
    public List<WechatUserVO> findWechatUserByPagination(WechatUserDTO wechatUserDTO) {
        Long count = wechatUserDAO.findWechatUserCount(wechatUserDTO);
        wechatUserDTO.doPage(count, wechatUserDTO.getPageNo(), wechatUserDTO.getPageSize());
        List<WechatUserVO> list = wechatUserDAO.findWechatUserByPagination(wechatUserDTO);
        return list;
    }

    @Override
    public WechatUserVO findWechatUserByID(Long id) {
        return wechatUserDAO.findWechatUserByID(id);
    }

}
