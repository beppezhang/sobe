package com.kpluswebup.web.admin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.SystemCodeDAO;
import com.kpluswebup.web.admin.system.service.SystemCodeService;
import com.kpluswebup.web.domain.CodeConfigDTO;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.SystemCodeVO;
import com.kpuswebup.comom.util.CachedClient;

@Service
public class SystemCodeServiceImpl implements SystemCodeService {

    @Autowired
    private SystemCodeDAO systemCodeDAO;

    @Autowired
    private CachedClient  cachedClient;

    @Override
    public List<SystemCodeVO> findSystemCode(String mainID) {
        return systemCodeDAO.findSystemCode(mainID);
    }

    @Override
    public void addCodeConfig(CodeConfigDTO codeConfigDTO) {
        systemCodeDAO.insertCodeConfig(codeConfigDTO);
    }

    @Override
    public void updateCodeConfig(CodeConfigDTO codeConfigDTO) {
        systemCodeDAO.updateCodeConfig(codeConfigDTO);
    }

    @Override
    public CodeConfigVO findCodeConfigByID(String codeID) {
        CodeConfigVO codeConfigVO = cachedClient.get(codeID);
        if (codeConfigVO == null) {
            codeConfigVO = systemCodeDAO.findCodeConfigByID(codeID);
            cachedClient.set(codeID, 3600, codeConfigVO);
        }
        return codeConfigVO;
    }

    public List<CodeConfigVO> findAllCodeConfig() {

        List<CodeConfigVO> list = systemCodeDAO.findAllCodeConfig();

        return list;
    }

}
