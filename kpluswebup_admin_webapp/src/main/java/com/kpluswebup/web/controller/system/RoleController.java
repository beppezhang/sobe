package com.kpluswebup.web.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.RoleService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.RoleDTO;
import com.kpluswebup.web.vo.RoleVO;
import com.kpluswebup.web.vo.SystemFunctionVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/system")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	@RequestMapping("roleList")
	public ModelAndView roleList(RoleDTO roleDTO) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/system/role_list");
		List<RoleVO> list = roleService
				.findRoleByPagination(roleDTO);
		modelAndView.addObject("roleList", list);
		modelAndView.addObject("roleDTO", roleDTO);
		return modelAndView;
	}

	@RequestMapping("addRolePage")
	public ModelAndView addRolePage() {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/system/role_add");
		return modelAndView;
	}

	@RequestMapping("addRole")
	public ModelAndView addRole(String name, String description) {
		RoleDTO roleDTO = new RoleDTO();
		if (StringUtil.isNotEmpty(name)) {
			roleDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(description)) {
			roleDTO.setDescription(description);
		}
		roleDTO.setMainID(UUIDUtil.getUUID());
		roleDTO.setCreator(getCurrentOperator());
		roleService.addRole(roleDTO);
		return new ModelAndView("redirect:systemFunctionList.htm?mainID="+roleDTO.getMainID());
	}

	@RequestMapping("editRolePage")
	public ModelAndView editRolePage(String mainID) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/system/role_edit");
		RoleVO roleVO = roleService
				.findRoleByMainID(mainID);
		modelAndView.addObject("roleVO", roleVO);
		return modelAndView;
	}

	@RequestMapping("editRole")
	public ModelAndView editRole(String mainID, String name,
			String description) {
		RoleDTO roleDTO = new RoleDTO();
		if (StringUtil.isNotEmpty(name)) {
			roleDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(description)) {
			roleDTO.setDescription(description);
		}
		roleDTO.setMainID(mainID);
		roleDTO.setModifier(getCurrentOperator());
		roleService.updateRole(roleDTO);
		return new ModelAndView("redirect:roleList.htm");
	}

	@RequestMapping("deleteRole")
	public @ResponseBody
	JsonResult deleteRole(String mainID) {
		Boolean isSuccess = roleService.deleteRoleByMainID(mainID);
		if (isSuccess) {
			return JsonResult.create();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}
	
	@RequestMapping("systemFunctionList")
	public ModelAndView systemFunctionList(String mainID){
		ModelAndView modelAndView=this.newModelAndView();
		modelAndView.setViewName("/screen/system/systemfunction_list");
		List<SystemFunctionVO> list=roleService.findSystemFunction(mainID);
		modelAndView.addObject("systemfunctionList", list);
		RoleVO roleVO=roleService.findRoleByMainID(mainID);
		modelAndView.addObject("roleVO", roleVO);
		return modelAndView;
	}
	
	@RequestMapping("addRoleFunction")
	public ModelAndView addRoleFunction(String roleID,String functionIDs){
		roleService.addRoleFunction(roleID, functionIDs,getCurrentOperator());
		return new ModelAndView("redirect:roleList.htm");
	}

}
