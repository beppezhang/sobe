package com.kpluswebup.web.admin.system.dao;

import java.util.List;

import com.kpluswebup.web.domain.RoleDTO;
import com.kpluswebup.web.domain.RoleFunctionDTO;
import com.kpluswebup.web.domain.SystemFunctionDTO;
import com.kpluswebup.web.vo.RoleFunctionVO;
import com.kpluswebup.web.vo.RoleVO;
import com.kpluswebup.web.vo.SystemFunctionVO;

public interface RoleDAO {

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
     * 查询总条数
     * 
     * @date 2014年12月5日
     * @author wanghehua
     * @param roleDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findRoleCount(RoleDTO roleDTO);

    /**
     * 添加角色
     * 
     * @date 2014年12月5日
     * @author wanghehua
     * @param roleDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertRole(RoleDTO roleDTO);

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
    public Integer deleteRoleByMainID(String mainID);

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
    public List<SystemFunctionVO> findSystemFunction();

    /**
     * 查询子功能菜单
     * 
     * @date 2014年12月5日
     * @author wanghehua
     * @param parentID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SystemFunctionVO> findSystemFunctionChild(SystemFunctionDTO systemFunctionDTO);

    /**
     * 根据角色查询角色权限
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
     * @param roleFunctionDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertRoleFunction(RoleFunctionDTO roleFunctionDTO);

    /**
     * 根据角色删除角色权限
     * 
     * @date 2014年12月8日
     * @author wanghehua
     * @param roleID
     * @since JDK 1.6
     * @Description
     */
    public void deleteRoleFunctionByRoleID(String roleID);

    /**
     * 查询角色权限对应菜单(一级菜单)
     * 
     * @date 2014年12月8日
     * @author wanghehua
     * @param roleID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<RoleFunctionVO> findRoleFunctionAll(String roleID);

    /**
     * 查询角色权限对应子菜单
     * 
     * @date 2014年12月8日
     * @author wanghehua
     * @param roleFunctionDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<RoleFunctionVO> findRoleFunctionChild(RoleFunctionDTO roleFunctionDTO);

}
