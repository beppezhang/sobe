package com.kpluswebup.web.admin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.DepartmentDAO;
import com.kpluswebup.web.admin.system.service.DepartmentService;
import com.kpluswebup.web.domain.DepartmentDTO;
import com.kpluswebup.web.vo.DepartmentVO;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDAO departmentDAO;

	@Override
	public List<DepartmentVO> findDepartmentByPagination(
			DepartmentDTO departmentDTO) {
		Long count = departmentDAO.findDepartmentCount(departmentDTO);
		departmentDTO.doPage(count, departmentDTO.getPageNo(),
				departmentDTO.getPageSize());
		List<DepartmentVO> list = departmentDAO
				.findDepartmentByPagination(departmentDTO);
		return list;
	}

	@Override
	public void addDepartment(DepartmentDTO departmentDTO) {
		departmentDAO.insertDepartment(departmentDTO);
	}

	@Override
	public DepartmentVO findDepartmentByMainID(String mainID) {
		return departmentDAO.findDepartmentByMainID(mainID);
	}

	@Override
	public void updateDepartment(DepartmentDTO departmentDTO) {
		departmentDAO.updateDepartment(departmentDTO);
	}

	@Override
	public Boolean deleteDepartmentByMainID(String mainID) {
		try {
			departmentDAO.deleteDepartmentByMainID(mainID);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<DepartmentVO> findDepartmentAll() {
		return departmentDAO.findDepartmentAll();
	}

}
