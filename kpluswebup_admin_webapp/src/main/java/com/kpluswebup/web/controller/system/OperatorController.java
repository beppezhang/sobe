package com.kpluswebup.web.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.DepartmentService;
import com.kpluswebup.web.admin.system.service.OperatorService;
import com.kpluswebup.web.admin.system.service.RoleService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.OperatorDTO;
import com.kpluswebup.web.vo.DepartmentVO;
import com.kpluswebup.web.vo.OperatorVO;
import com.kpluswebup.web.vo.RoleVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.Md5Algorithm;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/system")
public class OperatorController extends BaseController {

    @Autowired
    private OperatorService   operatorService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService       roleService;

    @RequestMapping("operatorList")
    public ModelAndView OperatorList(OperatorDTO operatorDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/system/operator_list");
        List<OperatorVO> list = operatorService.findOperatorByPagination(operatorDTO);
        modelAndView.addObject("operatorList", list);
        modelAndView.addObject("operatorDTO", operatorDTO);
        return modelAndView;
    }

    @RequestMapping("addOperatorPage")
    public ModelAndView addOperatorPage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/system/operator_add");
        List<DepartmentVO> list = departmentService.findDepartmentAll();
        modelAndView.addObject("departmentList", list);
        List<RoleVO> rolelist = roleService.findRoleAll();
        modelAndView.addObject("roleList", rolelist);
        return modelAndView;
    }

    @RequestMapping("addOperator")
    public ModelAndView addOperator(String username, String password, String name, String departmentID, String roleID,
                                    String telephone, String mobile, String email) {
        OperatorDTO operatorDTO = new OperatorDTO();
        if (StringUtil.isNotEmpty(username)) {
            operatorDTO.setUsername(username);
        }
        if (StringUtil.isNotEmpty(password)) {
            operatorDTO.setPassword(password);
        }
        if (StringUtil.isNotEmpty(name)) {
            operatorDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(departmentID)) {
            operatorDTO.setDepartmentID(departmentID);
        }
        if (StringUtil.isNotEmpty(roleID)) {
            operatorDTO.setRoleID(roleID);
        }
        if (StringUtil.isNotEmpty(telephone)) {
            operatorDTO.setTelephone(telephone);
        }
        if (StringUtil.isNotEmpty(mobile)) {
            operatorDTO.setMobile(mobile);
        }
        if (StringUtil.isNotEmpty(email)) {
            operatorDTO.setEmail(email);
        }
        operatorDTO.setMainID(UUIDUtil.getUUID());
        operatorDTO.setCreator(getCurrentOperator());
        operatorService.addOperator(operatorDTO);
        return new ModelAndView("redirect:operatorList.htm");
    }

    @RequestMapping("editOperatorPage")
    public ModelAndView editOperatorPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/system/operator_edit");
        OperatorVO operator = operatorService.findOperatorByMainID(mainID);
        modelAndView.addObject("operator", operator);
        List<DepartmentVO> list = departmentService.findDepartmentAll();
        modelAndView.addObject("departmentList", list);
        List<RoleVO> rolelist = roleService.findRoleAll();
        modelAndView.addObject("roleList", rolelist);
        return modelAndView;
    }

    @RequestMapping("editOperator")
    public ModelAndView editOperator(String mainID, String username, String password, String name, String departmentID,
                                     String roleID, String telephone, String mobile, String email) {
        OperatorDTO operatorDTO = new OperatorDTO();
        if (StringUtil.isNotEmpty(name)) {
            operatorDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(departmentID)) {
            operatorDTO.setDepartmentID(departmentID);
        }
        if (StringUtil.isNotEmpty(roleID)) {
            operatorDTO.setRoleID(roleID);
        }
        if (StringUtil.isNotEmpty(telephone)) {
            operatorDTO.setTelephone(telephone);
        }
        if (StringUtil.isNotEmpty(mobile)) {
            operatorDTO.setMobile(mobile);
        }
        if (StringUtil.isNotEmpty(email)) {
            operatorDTO.setEmail(email);
        }
        operatorDTO.setMainID(mainID);
        operatorDTO.setModifier(getCurrentOperator());
        operatorService.updateOperator(operatorDTO);
        return new ModelAndView("redirect:operatorList.htm");
    }

    @RequestMapping("deleteOperator")
    public @ResponseBody JsonResult deleteOperator(String mainID) {
        Boolean isSuccess = operatorService.deleteOperatorByMainID(mainID);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("isLockOperator")
    public @ResponseBody JsonResult isLockOperator(String mainId, String status) {
        try {
            if (StringUtil.isEmpty(status)) {
                return new JsonResult(ResultCode.ERROR_SYSTEM_PARAM_FORMAT);
            }
            OperatorDTO operatorDTO = new OperatorDTO();
            operatorDTO.setStatus(Integer.valueOf(status));
            operatorDTO.setMainID(mainId);
            operatorService.changeOperatorStatus(operatorDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }

    @RequestMapping("editOperatorPwd")
    public ModelAndView editOperatorPwd(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/system/operator_editpwd");
        return modelAndView;
    }
    
    @RequestMapping("saveOperatorPwd")
    public ModelAndView saveOperatorPwd(String mainID,String password,String password0,
                                        String password1,String password2){
        ModelAndView modelAndView = this.newModelAndView();        
        if(!Md5Algorithm.getInstance().md5Digest(password.getBytes()).equals(password0)){
            modelAndView.setViewName("/screen/system/operator_editpwd");
            modelAndView.addObject("error1", "原密码错误!");
            return modelAndView;
        }
        if(!password1.equals(password2)){
            modelAndView.setViewName("/screen/system/operator_editpwd");
            modelAndView.addObject("error2", "两次输入不一致!");
            return modelAndView;
        }
        OperatorDTO operatorDTO = new OperatorDTO();   
        if (StringUtil.isNotEmpty(password2)) {
            operatorDTO.setPassword(password2);
        }
        operatorDTO.setMainID(mainID);
        operatorDTO.setModifier(getCurrentOperator());
        operatorService.updateOperatorPwd(operatorDTO);       
        return new ModelAndView("redirect:/admin/exitLogin.htm");
    }

}
