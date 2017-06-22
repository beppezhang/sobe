package com.kpluswebup.web.admin.system.service;

import java.util.List;

import com.kpluswebup.web.domain.RoleDTO;
import com.kpluswebup.web.vo.RoleFunctionVO;
import com.kpluswebup.web.vo.RoleVO;
import com.kpluswebup.web.vo.SystemFunctionVO;

public interface RoleService {

    /**
     * 分页查询角色
     * 
     * @date 2014年12月5日
     * @author wanghehua
     * @param roleDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<RoleVO> findRoleByPagination(RoleDTO roleDTO);

    /**
     * 添加角色
     * 
     * @date 2014年12月5日
     * @author wanghehua
     * @param roleDTO
     * @since JDK 1.6
     * @Description
     */
    public void addRole(RoleDTO roleDTO);

    /**
     * 修改角色
     * 
     * @date 2014年12月5日
     * @author wanghehua
     * @param roleDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateRole(RoleDTO roleDTO);

    /**
     * 根据id查找角色
     * 
     * @date 2014年12月5日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public RoleVO findRoleByMainID(String mainID);

    /**
     * 删除角色
     * 
     * @date 2014年12月5日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteRoleByMainID(String mainID);

    /**
     * 查询所有角色
     * 
     * @date 2014年12月5日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<RoleVO> findRoleAll();

    /**
     * 查询功能菜单
     * 
     * @date 2014年12月5日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SystemFunctionVO> findSystemFunction(String roleID);

    /**
     * 根据角色id查询角色权限
     * 
     * @date 2014年12月5日
     * @author wanghehua
     * @param roleID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<RoleFunctionVO> findRoleFunctionByRoleID(String roleID);

    /**
     * 添加菜单权限
     * 
     * @date 2014年12月8日
     * @author wanghehua
     * @param roleID
     * @param functionIDs
     * @since JDK 1.6
     * @Description
     */
    public void addRoleFunction(String roleID, String functionIDs,String currentOperator);

    /**
     * 查询角色权限对应菜单
     * 
     * @date 2014年12月8日
     * @author wanghehua
     * @param roleID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<RoleFunctionVO> findRoleFunctionAll(String roleID);

}
