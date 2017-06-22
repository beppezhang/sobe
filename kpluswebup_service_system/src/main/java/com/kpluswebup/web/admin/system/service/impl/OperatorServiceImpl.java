package com.kpluswebup.web.admin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.OperatorDAO;
import com.kpluswebup.web.admin.system.dao.RoleDAO;
import com.kpluswebup.web.admin.system.service.OperatorService;
import com.kpluswebup.web.admin.system.service.RoleService;
import com.kpluswebup.web.domain.OperatorDTO;
import com.kpluswebup.web.vo.OperatorVO;
import com.kpluswebup.web.vo.RoleFunctionVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.Md5Algorithm;

@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorDAO  operatorDAO;

    @Autowired
    private CachedClient cachedClient;

    @Autowired
    private RoleDAO      roleDAO;

    @Autowired
    private RoleService  roleService;

    public List<OperatorVO> findOperatorByPagination(OperatorDTO operatorDTO) {
        Long count = operatorDAO.findOperatorCount(operatorDTO);
        operatorDTO.doPage(count, operatorDTO.getPageNo(), operatorDTO.getPageSize());
        List<OperatorVO> list = operatorDAO.findOperatorByPagination(operatorDTO);
        return list;
    }

    public void addOperator(OperatorDTO operatorDTO) {
        operatorDTO.setPassword(Md5Algorithm.getInstance().md5Digest(operatorDTO.getPassword().getBytes()));
        operatorDAO.insertOperator(operatorDTO);
    }

    public OperatorVO findOperatorByMainID(String mainID) {
        return operatorDAO.findOperatorByMainID(mainID);
    }

    public void updateOperator(OperatorDTO operatorDTO) {
        operatorDAO.updateOperator(operatorDTO);
    }

    public Boolean deleteOperatorByMainID(String mainID) {
        try {
            operatorDAO.deleteOperatorByMainID(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void changeOperatorStatus(OperatorDTO operatorDTO) {
        operatorDAO.updateOperator(operatorDTO);
    }

    public Boolean isLogin(String userName, String password, String validateCode) {
        OperatorVO operatorVO = operatorDAO.findOperatorByUserName(userName);
        if (operatorVO == null) {
            return null;
        }
        if (Md5Algorithm.getInstance().md5Digest(password.getBytes()).equals(operatorVO.getPassword())) {
            List<RoleFunctionVO> menuOneLeve = roleService.findRoleFunctionAll(operatorVO.getRoleID());
            if (menuOneLeve != null && menuOneLeve.size() > 0) {
                cachedClient.set(Constant.MENUINFO + userName, Constant.EXP, menuOneLeve);
            }
            List<RoleFunctionVO> roleFunctionList = roleService.findRoleFunctionByRoleID(operatorVO.getRoleID());
            if (roleFunctionList != null && roleFunctionList.size() > 0) {
                cachedClient.set(Constant.ALLMENUINFO + userName, Constant.EXP, roleFunctionList);
            }

            return true;

        }
        return false;
    }

    public OperatorVO findOperatorByUserName(String userName) {
        return operatorDAO.findOperatorByUserName(userName);
    }

    @Override
    public void updateOperatorPwd(OperatorDTO operatorDTO) {
        operatorDTO.setPassword(Md5Algorithm.getInstance().md5Digest(operatorDTO.getPassword().getBytes()));
        operatorDAO.updateOperatorPwd(operatorDTO);
    }
       
}
