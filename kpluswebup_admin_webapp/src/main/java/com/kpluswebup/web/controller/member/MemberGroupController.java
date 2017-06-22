package com.kpluswebup.web.controller.member;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.CustomerGroupDTO;
import com.kpluswebup.web.domain.CustomerGroupMemberDTO;
import com.kpluswebup.web.domain.CustomerGroupSetDTO;
import com.kpluswebup.web.member.service.CustomerGroupSerivce;
import com.kpluswebup.web.vo.CustomerGroupMemberVO;
import com.kpluswebup.web.vo.CustomerGroupSetVO;
import com.kpluswebup.web.vo.CustomerGroupVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/member")
public class MemberGroupController extends BaseController {

    @Autowired
    private CustomerGroupSerivce customerGroupSerivce;

    @RequestMapping("membergroupList")
    public ModelAndView membergroupList(CustomerGroupDTO customerGroupDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/membergroup_list");
        List<CustomerGroupVO> list = customerGroupSerivce.finCustomerGroupByPagination(customerGroupDTO);
        modelAndView.addObject("membergroupList", list);
        modelAndView.addObject("customerGroupDTO", customerGroupDTO);
        return modelAndView;
    }

    @RequestMapping("/addMemberGroupPage")
    public ModelAndView addMemberGroupPage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/membercenter/membergroup_add");
        return modelAndView;
    }

    @RequestMapping("addMemberGroup")
    public ModelAndView addMemberGroup(String name, String description) {
        CustomerGroupDTO customerGroupDTO = new CustomerGroupDTO();
        if (StringUtil.isNotEmpty(name)) {
            customerGroupDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(description)) {
            customerGroupDTO.setDescription(description);
        }
        customerGroupDTO.setMainID(UUIDUtil.getUUID());
        customerGroupDTO.setCreator(getCurrentOperator());
        customerGroupSerivce.addCustomerGroup(customerGroupDTO);
        return new ModelAndView("redirect:editMemberGroupPage.htm?mainID=" + customerGroupDTO.getMainID());
    }

    @RequestMapping("/editMemberGroupPage")
    public ModelAndView editMemberGroupPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/membercenter/membergroup_edit");
        CustomerGroupVO customerGroupVO = customerGroupSerivce.findCustomerGroupByMainID(mainID);
        modelAndView.addObject("customerGroupVO", customerGroupVO);
        modelAndView.addObject("mainID", mainID);
        List<CustomerGroupSetVO> list = customerGroupSerivce.findCustomerGroupSetByGroupId(mainID);
        modelAndView.addObject("groupsetList", list);
        return modelAndView;
    }

    @RequestMapping("editMemberGroup")
    public ModelAndView editMemberGroup(String mainID, String name, String description) {
        CustomerGroupDTO customerGroupDTO = new CustomerGroupDTO();
        if (StringUtil.isNotEmpty(name)) {
            customerGroupDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(description)) {
            customerGroupDTO.setDescription(description);
        }
        customerGroupDTO.setMainID(mainID);
        customerGroupDTO.setModifier(getCurrentOperator());
        customerGroupSerivce.editCustomerGroup(customerGroupDTO);
        customerGroupSerivce.deleteCustomerGroupMemberByGroupID(mainID);
        List<CustomerGroupSetVO> list = customerGroupSerivce.findCustomerGroupSetByGroupId(mainID);
        if (list != null && list.size() > 0) {
            CustomerVO customerVO = new CustomerVO();
            for (CustomerGroupSetVO customerGroupSetVO : list) {
                if (customerGroupSetVO.getSetType() == 1) {
                    customerVO.setMinage(Integer.valueOf(customerGroupSetVO.getMinimum()));
                    customerVO.setMaxage(Integer.valueOf(customerGroupSetVO.getMaxmum()));
                } else if (customerGroupSetVO.getSetType() == 2) {
                    customerVO.setMinscoreIntotal(Double.valueOf(customerGroupSetVO.getMinimum()));
                    customerVO.setMaxscoreIntotal(Double.valueOf(customerGroupSetVO.getMaxmum()));

                } else if (customerGroupSetVO.getSetType() == 4) {
                    try {
                        customerVO.setMincreateTime(DateUtil.strintToDatetimeYMD(customerGroupSetVO.getMinimum()));
                        customerVO.setMaxcreateTime(DateUtil.strintToDatetimeYMD(customerGroupSetVO.getMaxmum()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else if (customerGroupSetVO.getSetType() == 5) {
                    customerVO.setMinscore(Double.valueOf(customerGroupSetVO.getMinimum()));
                    customerVO.setMaxscore(Double.valueOf(customerGroupSetVO.getMaxmum()));
                } else if (customerGroupSetVO.getSetType() == 6) {
                    customerVO.setSex(Integer.valueOf(customerGroupSetVO.getMinimum()));
                } else if (customerGroupSetVO.getSetType() == 7) {
                    customerVO.setUsername(customerGroupSetVO.getMinimum());
                }
            }
            List<CustomerVO> customerlist = customerGroupSerivce.findCustomerByGroupSet(customerVO);
            if (customerlist != null && customerlist.size() > 0) {
                CustomerGroupMemberDTO customerGroupMemberDTO = new CustomerGroupMemberDTO();
                for (CustomerVO customerVO2 : customerlist) {
                    customerGroupMemberDTO.setCustomerID(customerVO2.getMainID());
                    customerGroupMemberDTO.setGroupID(customerGroupDTO.getMainID());
                    customerGroupMemberDTO.setCreator(getCurrentOperator());
                    customerGroupSerivce.addCustomerGroupMember(customerGroupMemberDTO);
                }
            }
        }
        return new ModelAndView("redirect:membergroupList.htm");
    }

    @RequestMapping("deleteMemberGroup")
    public @ResponseBody
    JsonResult deleteMemberGroup(String mainIds) {
        Boolean isSuccess = customerGroupSerivce.deleteCustomerGroupByMainID(mainIds);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("deleteGroupSet")
    public @ResponseBody
    JsonResult deleteGroupSet(Long id) {
        Boolean isSuccess = customerGroupSerivce.deleteGroupSetByID(id);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("addGroupSet")
    public @ResponseBody
    JsonResult addGroupSet(String groupId, Integer setType, String minimum, String maxmum) {
        CustomerGroupSetDTO customerGroupSetDTO = new CustomerGroupSetDTO();
        if (StringUtil.isNotEmpty(groupId)) {
            customerGroupSetDTO.setGroupID(groupId);
        }
        if (setType != null) {
            customerGroupSetDTO.setSetType(setType);
        }
        if (StringUtil.isNotEmpty(minimum)) {
            customerGroupSetDTO.setMinimum(minimum);
        }
        if (StringUtil.isNotEmpty(maxmum)) {
            customerGroupSetDTO.setMaxmum(maxmum);
        }
        try {
            customerGroupSerivce.addGroupSet(customerGroupSetDTO);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("editGroupSet")
    public @ResponseBody
    JsonResult editGroupSet(Long id, String sexType, String minimum, String maxmum) {
        CustomerGroupSetDTO customerGroupSetDTO = new CustomerGroupSetDTO();
        if (StringUtil.isNotEmpty(sexType)) {
            customerGroupSetDTO.setMinimum(sexType);
        } else {
            if (StringUtil.isNotEmpty(minimum)) {
                customerGroupSetDTO.setMinimum(minimum);
            }
            if (StringUtil.isNotEmpty(maxmum)) {
                customerGroupSetDTO.setMaxmum(maxmum);
            }
        }
        customerGroupSetDTO.setId(id);
        try {
            customerGroupSerivce.editGroupSet(customerGroupSetDTO);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * 
     * @date 2015年4月17日
     * @author yuanyuan
     * @param groupId
     * @param customerGroupMemberDTO
     * @return
     * @since JDK 1.6
     * @Description  组内采购商（组内会员）
     */
    @RequestMapping("memberGroupSetList")
    public ModelAndView memberGroupSetList(String groupId, CustomerGroupMemberDTO customerGroupMemberDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/membergroupset_list");
        customerGroupMemberDTO.setGroupID(groupId);
        List<CustomerGroupMemberVO> list = customerGroupSerivce.findCustomerGroupMemberByPagination(customerGroupMemberDTO);
        modelAndView.addObject("membergroupsetList", list);
        modelAndView.addObject("customerGroupMemberDTO", customerGroupMemberDTO);
        CustomerGroupVO customerGroupVO = new CustomerGroupVO();
        customerGroupVO.setMainID(groupId);
        modelAndView.addObject("customerGroupVO", customerGroupVO);
        return modelAndView;
    }

    /**
     * 
     * @date 2015年4月17日
     * @author yuanyuan
     * @param response
     * @param mainIds
     * @since JDK 1.6
     * @Description  导出
     */
    @RequestMapping("exportCustomerGroupMember")
    public void exportCustomerGroupMember(HttpServletResponse response, String mainIds,String groupID) {
        try {
            customerGroupSerivce.exportCustomerGroupMember(response, mainIds,groupID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
