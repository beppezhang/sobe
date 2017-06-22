package com.kpluswebup.web.admin.system.service;

import java.util.List;

import com.kpluswebup.web.domain.DepartmentDTO;
import com.kpluswebup.web.vo.DepartmentVO;

public interface DepartmentService {

	/**
	 * 分页查找部门
	 * @date 2014年11月20日
	 * @author wanghehua
	 * @param departmentDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<DepartmentVO> findDepartmentByPagination(DepartmentDTO departmentDTO);
	
	/**
	 * 添加部门
	 * @date 2014年11月20日
	 * @author wanghehua
	 * @param departmentDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void addDepartment(DepartmentDTO departmentDTO);
	
	/**
	 * 根据id查找部门
	 * @date 2014年11月20日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public DepartmentVO findDepartmentByMainID(String mainID);
	/**
	 * 修改部门
	 * @date 2014年11月20日
	 * @author wanghehua
	 * @param departmentDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateDepartment(DepartmentDTO departmentDTO);
	
	/**
	 * 删除部门
	 * @date 2014年11月20日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public Boolean deleteDepartmentByMainID(String mainID);
	
	/**
	 * 查询所有部门
	 * @date 2014年11月27日
	 * @author wanghehua
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<DepartmentVO> findDepartmentAll();
}
