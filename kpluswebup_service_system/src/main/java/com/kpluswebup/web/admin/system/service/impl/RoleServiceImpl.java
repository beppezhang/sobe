package com.kpluswebup.web.admin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.RoleDAO;
import com.kpluswebup.web.admin.system.service.RoleService;
import com.kpluswebup.web.domain.RoleDTO;
import com.kpluswebup.web.domain.RoleFunctionDTO;
import com.kpluswebup.web.domain.SystemFunctionDTO;
import com.kpluswebup.web.vo.RoleFunctionVO;
import com.kpluswebup.web.vo.RoleVO;
import com.kpluswebup.web.vo.SystemFunctionVO;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public List<RoleVO> findRoleByPagination(RoleDTO roleDTO) {
        Long count = roleDAO.findRoleCount(roleDTO);
        roleDTO.doPage(count, roleDTO.getPageNo(), roleDTO.getPageSize());
        List<RoleVO> list = roleDAO.findRoleByPagination(roleDTO);
        return list;
    }

    @Override
    public void addRole(RoleDTO roleDTO) {
        roleDAO.insertRole(roleDTO);
    }

    @Override
    public void updateRole(RoleDTO roleDTO) {
        roleDAO.updateRole(roleDTO);
    }

    @Override
    public RoleVO findRoleByMainID(String mainID) {
        return roleDAO.findRoleByMainID(mainID);
    }

    @Override
    public Boolean deleteRoleByMainID(String mainID) {
        try {
            roleDAO.deleteRoleByMainID(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<RoleVO> findRoleAll() {
        return roleDAO.findRoleAll();
    }

    @Override
    public List<SystemFunctionVO> findSystemFunction(String roleID) {
        List<SystemFunctionVO> list = roleDAO.findSystemFunction();
        List<RoleFunctionVO> rfList = roleDAO.findRoleFunctionByRoleID(roleID);
        if (list != null && list.size() > 0) {
            for (SystemFunctionVO systemFunctionVO : list) {
                SystemFunctionDTO systemFunctionDTO = new SystemFunctionDTO();
                systemFunctionDTO.setParentID(systemFunctionVO.getMainID());
                systemFunctionDTO.setDepth(2);
                List<SystemFunctionVO> childList = roleDAO.findSystemFunctionChild(systemFunctionDTO);
                systemFunctionVO.setChildList(childList);
                if (rfList != null && rfList.size() > 0) {
                    for (RoleFunctionVO roleFunctionVO : rfList) {
                        if (systemFunctionVO.getMainID().equals(roleFunctionVO.getFunctionID())) {
                            systemFunctionVO.setIsCheck(true);
                        }
                    }
                }
                if (childList != null && childList.size() > 0) {
                    for (SystemFunctionVO childSystemFunctionVO : childList) {
                        if (rfList != null && rfList.size() > 0) {
                            for (RoleFunctionVO roleFunctionVO : rfList) {
                                if (childSystemFunctionVO.getMainID().equals(roleFunctionVO.getFunctionID())) {
                                    childSystemFunctionVO.setIsCheck(true);
                                }
                            }
                        }
                        SystemFunctionDTO childSystemFunctionDTO = new SystemFunctionDTO();
                        childSystemFunctionDTO.setParentID(childSystemFunctionVO.getMainID());
                        childSystemFunctionDTO.setDepth(3);
                        List<SystemFunctionVO> threechildList = roleDAO.findSystemFunctionChild(childSystemFunctionDTO);
                        childSystemFunctionVO.setChildList(threechildList);
                        if (threechildList != null && threechildList.size() > 0) {
                            for (SystemFunctionVO threeSystemFunctionVO : threechildList) {
                                if (rfList != null && rfList.size() > 0) {
                                    for (RoleFunctionVO roleFunctionVO : rfList) {
                                        if (threeSystemFunctionVO.getMainID().equals(roleFunctionVO.getFunctionID())) {
                                            threeSystemFunctionVO.setIsCheck(true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    @Override
    public List<RoleFunctionVO> findRoleFunctionByRoleID(String roleID) {
        return roleDAO.findRoleFunctionByRoleID(roleID);
    }

    @Override
    public void addRoleFunction(String roleID, String functionIDs,String currentOperator) {
        roleDAO.deleteRoleFunctionByRoleID(roleID);
        if (functionIDs != null) {
            String[] ids = functionIDs.split(",");
            for (String functionID : ids) {
                RoleFunctionDTO roleFunctionDTO = new RoleFunctionDTO();
                roleFunctionDTO.setRoleID(roleID);
                roleFunctionDTO.setFunctionID(functionID);
                roleFunctionDTO.setCreator(currentOperator);
                roleDAO.insertRoleFunction(roleFunctionDTO);
            }
        }
    }

    @Override
    public List<RoleFunctionVO> findRoleFunctionAll(String roleID) {
        List<RoleFunctionVO> list = roleDAO.findRoleFunctionAll(roleID);
        if (list != null && list.size() > 0) {
            for (RoleFunctionVO roleFunctionVO : list) {
                RoleFunctionDTO roleFunctionDTO = new RoleFunctionDTO();
                roleFunctionDTO.setDepth(2);
                roleFunctionDTO.setParentID(roleFunctionVO.getFunctionID());
                roleFunctionDTO.setRoleID(roleID);
                List<RoleFunctionVO> childlist = roleDAO.findRoleFunctionChild(roleFunctionDTO);
                if (childlist != null && childlist.size() > 0) {
                    roleFunctionVO.setChildList(childlist);
                }
            }
        }
        return list;
    }

}
