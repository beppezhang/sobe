package com.kpluswebup.web.admin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.WhiteListDAO;
import com.kpluswebup.web.admin.system.service.WhiteListService;
import com.kpluswebup.web.domain.WhiteListDTO;
import com.kpluswebup.web.vo.WhiteListVO;

@Service
public class WhiteListServiceImpl implements WhiteListService {

    @Autowired
    private WhiteListDAO whiteListDAO;

    @Override
    public List<WhiteListVO> findWhileList() {
        return whiteListDAO.findWhiteList();
    }

    @Override
    public void addWhiteIP(WhiteListDTO whiteListDTO) {
        whiteListDAO.insertWhiteIP(whiteListDTO);
    }

    @Override
    public Boolean deleteWhiteIP(Long id) {
        try {
            whiteListDAO.deleteWhiteIP(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
