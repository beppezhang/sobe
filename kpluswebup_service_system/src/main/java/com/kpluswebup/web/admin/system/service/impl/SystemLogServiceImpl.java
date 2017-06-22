package com.kpluswebup.web.admin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.SystemLogDAO;
import com.kpluswebup.web.admin.system.service.SystemLogService;
import com.kpluswebup.web.domain.SystemLogDTO;
import com.kpluswebup.web.vo.SystemLogVO;

@Service
public class SystemLogServiceImpl implements SystemLogService {

    @Autowired
    private SystemLogDAO systemLogDAO;

    @Override
    public List<SystemLogVO> findSystemLogByPagination(SystemLogDTO systemLogDTO) {
        Long count = systemLogDAO.findSystemLogCount(systemLogDTO);
        systemLogDTO.doPage(count, systemLogDTO.getPageNo(), systemLogDTO.getPageSize());
        List<SystemLogVO> list = systemLogDAO.findSystemLogByPagination(systemLogDTO);
        return list;
    }

    public Boolean insertSystemLog(SystemLogDTO systemLogDTO) {
        try {
            systemLogDAO.insertSystemLog(systemLogDTO);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
