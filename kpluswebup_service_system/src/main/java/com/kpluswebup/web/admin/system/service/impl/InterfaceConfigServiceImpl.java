package com.kpluswebup.web.admin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.InterfaceConfigDAO;
import com.kpluswebup.web.admin.system.service.InterfaceConfigService;
import com.kpluswebup.web.domain.InterfaceConfigDTO;
import com.kpluswebup.web.domain.InterfaceConfigParameterDTO;
import com.kpluswebup.web.vo.InterfaceConfigParameterVO;
import com.kpluswebup.web.vo.InterfaceConfigVO;
import com.kpluswebup.web.vo.SystemInterfaceParameterVO;
import com.kpluswebup.web.vo.SystemInterfaceVO;

@Service
public class InterfaceConfigServiceImpl implements InterfaceConfigService {

    @Autowired
    private InterfaceConfigDAO interfaceConfigDAO;

    @Override
    public List<InterfaceConfigVO> findInterfaceConfigByPagination(InterfaceConfigDTO interfaceConfigDTO) {
        Long count = interfaceConfigDAO.findInterfaceConfigCount(interfaceConfigDTO);
        interfaceConfigDTO.doPage(count, interfaceConfigDTO.getPageNo(), interfaceConfigDTO.getPageSize());
        List<InterfaceConfigVO> list = interfaceConfigDAO.findInterfaceConfigByPagination(interfaceConfigDTO);
        return list;
    }

    @Override
    public List<SystemInterfaceVO> findSystemInterface(Integer interfaceType) {
        return interfaceConfigDAO.findSystemInterface(interfaceType);
    }

    @Override
    public List<SystemInterfaceParameterVO> findSystemInterfaceParameterByInterfaceID(String interfaceID) {
        return interfaceConfigDAO.findSystemInterfaceParameterByInterfaceID(interfaceID);
    }

    @Override
    public void addInterfaceConfig(InterfaceConfigDTO interfaceConfigDTO) {
        interfaceConfigDAO.insertInterfaceConfig(interfaceConfigDTO);
    }

    @Override
    public void addInterfaceConfigParameter(InterfaceConfigParameterDTO interfaceConfigParameterDTO) {
        interfaceConfigDAO.insertInterfaceConfigParameter(interfaceConfigParameterDTO);
    }

    @Override
    public void updateInterfaceConfig(InterfaceConfigDTO interfaceConfigDTO) {
        interfaceConfigDAO.updateInterfaceConfig(interfaceConfigDTO);
    }

    @Override
    public void updateInterfaceConfigParameter(InterfaceConfigParameterDTO interfaceConfigParameterDTO) {
        interfaceConfigDAO.updateInterfaceConfigParameter(interfaceConfigParameterDTO);
    }

    @Override
    public InterfaceConfigVO findInterfaceConfigByMainID(String mainID) {
        return interfaceConfigDAO.findInterfaceConfigByMainID(mainID);
    }

    @Override
    public List<InterfaceConfigParameterVO> findInterfaceConfigParameterByConfigID(String configID) {
        return interfaceConfigDAO.findInterfaceConfigParameterByConfigID(configID);
    }

    @Override
    public Boolean deleteInterfaceConfig(String mainIDs) {
        try {
            String[] ids = mainIDs.split(",");
            for (String mainID : ids) {
                interfaceConfigDAO.deleteInterfaceConfig(mainID);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateInterfaceConfigActive(String mainIDs, Integer active) {
        try {
            String[] ids = mainIDs.split(",");
            for (String mainID : ids) {
                InterfaceConfigDTO interfaceConfigDTO = new InterfaceConfigDTO();
                interfaceConfigDTO.setMainID(mainID);
                interfaceConfigDTO.setActive(active);
                interfaceConfigDAO.updateInterfaceConfigActive(interfaceConfigDTO);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<InterfaceConfigVO> findInterfaceConfigByType(Integer type) {
        return interfaceConfigDAO.findInterfaceConfigByType(type);
    }

    @Override
    public String findInterfaceConfigParameterValue(String configID, String parameterID) {
        InterfaceConfigParameterDTO interfaceConfigParameterDTO = new InterfaceConfigParameterDTO();
        interfaceConfigParameterDTO.setConfigID(configID);
        interfaceConfigParameterDTO.setParameterID(parameterID);
        return interfaceConfigDAO.findInterfaceConfigParameterValue(interfaceConfigParameterDTO);
    }

    @Override
    public String findInterfaceParameterValue(String interfaceID, String parameterID) {
        InterfaceConfigParameterDTO interfaceConfigParameterDTO = new InterfaceConfigParameterDTO();
        interfaceConfigParameterDTO.setInterfaceID(interfaceID);
        interfaceConfigParameterDTO.setParameterID(parameterID);
        return interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
    }

}
