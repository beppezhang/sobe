package com.kpluswebup.web.controller.member;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.CustomerGradeDTO;
import com.kpluswebup.web.member.service.CustomerGradeSerivce;
import com.kpluswebup.web.vo.CustomerGradeVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/member")
public class MemberGradeController extends BaseController {

    @Autowired
    private CustomerGradeSerivce customerGradeSerivce;

    @RequestMapping("membergradeList")
    public ModelAndView membergradeList(String pageNo, String pageSize, String searchName, String gradeSet, String GradeAmount) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/membergrade_list");
        CustomerGradeDTO customerGradeDTO = new CustomerGradeDTO();
        if (StringUtil.isNotEmpty(searchName)) {
            customerGradeDTO.setName(searchName);
        }
        if (StringUtil.isNotEmpty(gradeSet)) {
            customerGradeDTO.setGradeSet(Integer.getInteger(gradeSet));
        }
        if (StringUtil.isNotEmpty(pageNo)) {
            customerGradeDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(pageSize)) {
            customerGradeDTO.setPageSize(Long.parseLong(pageSize));
        }
        if (StringUtil.isNotEmpty(GradeAmount)) {
            customerGradeDTO.setGradeAmount(Double.parseDouble(GradeAmount));
        }
        List<CustomerGradeVO> list = customerGradeSerivce.findCustomerGradeByPagination(customerGradeDTO);
        modelAndView.addObject("customergradeList", list);
        modelAndView.addObject("customerGradeDTO", customerGradeDTO);
        return modelAndView;
    }

    @RequestMapping("/addMemberGradePage")
    public ModelAndView addMemberGradePage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/membercenter/membergrade_add");
        return modelAndView;
    }

    @RequestMapping("addMemberGrade")
    public ModelAndView addMemberGrade(String name, String picURL, String gradeSet, String GradeAmount,
                                      Double shoppingIntegral,Double lineShoppingIntegral, String sortOrder, String description,String startTime,String endTime) {
        CustomerGradeDTO customerGradeDTO = new CustomerGradeDTO();
        if (StringUtil.isNotEmpty(name)) {
            customerGradeDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(picURL)) {
            customerGradeDTO.setPicURL(picURL);
        }
        if (StringUtil.isNotEmpty(picURL)) {
            customerGradeDTO.setPicURL(picURL);
        }
        if (StringUtil.isNotEmpty(gradeSet)) {
            customerGradeDTO.setGradeSet(Integer.valueOf(gradeSet));
        }
        if (StringUtil.isNotEmpty(GradeAmount)) {
            customerGradeDTO.setGradeAmount(Double.parseDouble(GradeAmount));
        }
        if (StringUtil.isNotEmpty(sortOrder)) {
            customerGradeDTO.setSortOrder(Integer.valueOf(sortOrder));
        }
        if (StringUtil.isNotEmpty(description)) {
            customerGradeDTO.setDescription(description);
        }
        if(StringUtil.isNotEmpty(startTime)){
            try {
                customerGradeDTO.setStartTime(DateUtil.strintToDatetimeYMDHMS(startTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(StringUtil.isNotEmpty(endTime)){
            try {
                customerGradeDTO.setEndTime(DateUtil.strintToDatetimeYMDHMS(endTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        customerGradeDTO.setMainID(UUIDUtil.getUUID());
        customerGradeDTO.setCreator(getCurrentOperator());
        customerGradeSerivce.addCustomerGrade(customerGradeDTO);
        return new ModelAndView("redirect:membergradeList.htm");
    }

    @RequestMapping("/editMemberGradePage")
    public ModelAndView editMemberGradePage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/membercenter/membergrade_edit");
        CustomerGradeVO customerGradeVO = customerGradeSerivce.findCustomerGradeByMainID(mainID);
        modelAndView.addObject("customerGradeVO", customerGradeVO);
        modelAndView.addObject("mainID", mainID);
        return modelAndView;
    }

    @RequestMapping("editMemberGrade")
    public ModelAndView editMemberGrade(String mainID, String name, String picURL, String gradeSet, String GradeAmount,
                                        Double shoppingIntegral,Double lineShoppingIntegral,String sortOrder, String description,String startTime,String endTime) {
        CustomerGradeDTO customerGradeDTO = new CustomerGradeDTO();
        if (StringUtil.isNotEmpty(name)) {
            customerGradeDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(picURL)) {
            customerGradeDTO.setPicURL(picURL);
        }
        if (StringUtil.isNotEmpty(gradeSet)) {
            customerGradeDTO.setGradeSet(Integer.valueOf(gradeSet));
        }
        if (StringUtil.isNotEmpty(GradeAmount)) {
            customerGradeDTO.setGradeAmount(Double.parseDouble(GradeAmount));
        } 
        if (StringUtil.isNotEmpty(sortOrder)) {
            customerGradeDTO.setSortOrder(Integer.valueOf(sortOrder));
        }
        if (StringUtil.isNotEmpty(description)) {
            customerGradeDTO.setDescription(description);
        }
        if(StringUtil.isNotEmpty(startTime)){
            try {
                customerGradeDTO.setStartTime(DateUtil.strintToDatetimeYMDHMS(startTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(StringUtil.isNotEmpty(endTime)){
            try {
                customerGradeDTO.setEndTime(DateUtil.strintToDatetimeYMDHMS(endTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        customerGradeDTO.setMainID(mainID);
        customerGradeDTO.setModifier(getCurrentOperator());
        customerGradeSerivce.editCustomerGrade(customerGradeDTO);
        return new ModelAndView("redirect:membergradeList.htm");
    }

    @RequestMapping("deleteMemberGrade")
    public @ResponseBody
    JsonResult deleteMemberGrade(String mainIds) {
        Boolean isSuccess = customerGradeSerivce.deleteCustomerGradeByMainID(mainIds);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

}
