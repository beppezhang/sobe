package com.kpluswebup.web.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.DepartmentService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.DepartmentDTO;
import com.kpluswebup.web.vo.DepartmentVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/system")
public class DepartmentController extends BaseController {

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("departmentList")
	public ModelAndView departmentList(DepartmentDTO departmentDTO) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/system/department_list");
		List<DepartmentVO> list = departmentService
				.findDepartmentByPagination(departmentDTO);
		modelAndView.addObject("departmentList", list);
		modelAndView.addObject("departmentDTO", departmentDTO);
		return modelAndView;
	}

	@RequestMapping("addDepartmentPage")
	public ModelAndView addDepartmentPage() {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/system/department_add");
		return modelAndView;
	}

	@RequestMapping("addDepartment")
	public ModelAndView addDepartment(String name, String description) {
		DepartmentDTO departmentDTO = new DepartmentDTO();
		if (StringUtil.isNotEmpty(name)) {
			departmentDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(description)) {
			departmentDTO.setDescription(description);
		}
		departmentDTO.setMainID(UUIDUtil.getUUID());
		departmentDTO.setCreator(getCurrentOperator());
		departmentService.addDepartment(departmentDTO);
		return new ModelAndView("redirect:departmentList.htm");
	}

	@RequestMapping("editDepartmentPage")
	public ModelAndView editDepartmentPage(String mainID) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/system/department_edit");
		DepartmentVO departmentVO = departmentService
				.findDepartmentByMainID(mainID);
		modelAndView.addObject("departmentVO", departmentVO);
		return modelAndView;
	}

	@RequestMapping("editDepartment")
	public ModelAndView editDepartment(String mainID, String name,
			String description) {
		DepartmentDTO departmentDTO = new DepartmentDTO();
		if (StringUtil.isNotEmpty(name)) {
			departmentDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(description)) {
			departmentDTO.setDescription(description);
		}
		departmentDTO.setMainID(mainID);
		departmentDTO.setModifier(getCurrentOperator());
		departmentService.updateDepartment(departmentDTO);
		return new ModelAndView("redirect:departmentList.htm");
	}

	@RequestMapping("deleteDepartment")
	public @ResponseBody
	JsonResult deleteDepartment(String mainID) {
		Boolean isSuccess = departmentService.deleteDepartmentByMainID(mainID);
		if (isSuccess) {
			return JsonResult.create();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

}
