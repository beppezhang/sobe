package com.kpluswebup.web.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.AreaService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.domain.CustomerGroupDTO;
import com.kpluswebup.web.member.service.CustomerGradeSerivce;
import com.kpluswebup.web.member.service.CustomerGroupSerivce;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.CityVO;
import com.kpluswebup.web.vo.CustomerGradeVO;
import com.kpluswebup.web.vo.CustomerGroupVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/member")
public class MemberCenterController extends BaseController {

    @Autowired
    private MemberSerivce        memberSerivce;

    @Autowired
    private AreaService          areaService;

    @Autowired
    private CustomerGradeSerivce customerGradeSerivce;
    @Autowired
    private CustomerGroupSerivce customerGroupSerivce;

    /**
     * 会员中心列表
     * 
     * @date 2014年10月24日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("memberList")
    public ModelAndView findMemberList(String pageNo, String pageSize, String searchMobile, String searchName,
                                       String searchUserName, String gradeID, String searchEmail, String searchStatus, 
                                       String provinceID, String cityID, String districtID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/membercenter/member_list");
        CustomerDTO customerDTO = new CustomerDTO();
        if (StringUtil.isNotEmpty(searchMobile)) {
            customerDTO.setMobile(searchMobile);
        }
        if (StringUtil.isNotEmpty(searchName)) {
            customerDTO.setName(searchName);
        }
        if (StringUtil.isNotEmpty(searchUserName)) {
            customerDTO.setUsername(searchUserName);
        }
        if (StringUtil.isNotEmpty(gradeID)) {
            customerDTO.setGradeID(gradeID);
        }
        if (StringUtil.isNotEmpty(searchEmail)) {
            customerDTO.setEmail(searchEmail);
        }
        if (StringUtil.isNotEmpty(searchStatus)) {
            customerDTO.setStatus(Integer.valueOf(searchStatus));
        }
        if (StringUtil.isNumberic(pageSize)) {
            customerDTO.setPageSize(Long.parseLong(pageSize));
        }
        if (StringUtil.isNumberic(pageNo)) {
            customerDTO.setPageNo(Long.parseLong(pageNo));
        }
        if(!"0".equals(districtID)){
        	customerDTO.setDistrictID(districtID);
        }else if(!"0".equals(cityID)){
        	customerDTO.setCityID(cityID);
        }else if(!"0".equals(provinceID)){
        	customerDTO.setProvinceID(provinceID);
        }
        List<CustomerVO> list = memberSerivce.findCustomerByPagination(customerDTO);
        modelAndView.addObject("customers", list);

        modelAndView.addObject("customerDTO", customerDTO);
        List<CustomerGradeVO> gradeList = customerGradeSerivce.findAllCustomerGrade();
        modelAndView.addObject("gradeList", gradeList);
        
        List<AreaVO> provinceList = areaService.getAllProvince();
        modelAndView.addObject("provinceList", provinceList);
        List<AreaVO> cityList = null;
        List<AreaVO> districtList = null;

        if (provinceList != null && provinceList.size() > 0) {
            cityList = areaService.getAreaByParentID(provinceID == "0" ? provinceList.get(0).getMainID() : provinceID);
        }
        modelAndView.addObject("cityList", cityList);

        if (cityList != null && cityList.size() > 0) {
            districtList = areaService.getAreaByParentID(cityID == "0" ? cityList.get(0).getMainID() : cityID);
        }
        modelAndView.addObject("districtList", districtList);
        modelAndView.addObject("districtID", districtID);
        modelAndView.addObject("cityID", cityID);
        modelAndView.addObject("provinceID", provinceID);
        return modelAndView;
    }

    /**
     * 采购商申请列表
     * 
     * @date 2015050月19日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("memberApplyList")
    public ModelAndView findMemberApplyList(String pageNo, String pageSize, String searchMobile, String searchName,
                                            String searchUserName, String gradeID, String searchEmail,
                                            String searchStatus, String provinceID, String cityID, String districtID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/membercenter/memberapply_list");
        CustomerDTO customerDTO = new CustomerDTO();
        if (StringUtil.isNotEmpty(searchMobile)) {
            customerDTO.setMobile(searchMobile);
        }
        if (StringUtil.isNotEmpty(searchName)) {
            customerDTO.setName(searchName);
        }
        if (StringUtil.isNotEmpty(searchUserName)) {
            customerDTO.setUsername(searchUserName);
        }
        if (StringUtil.isNotEmpty(gradeID)) {
            customerDTO.setGradeID(gradeID);
        }
        if (StringUtil.isNotEmpty(searchEmail)) {
            customerDTO.setEmail(searchEmail);
        }
        if (StringUtil.isNotEmpty(searchStatus)) {
            customerDTO.setStatus(Integer.valueOf(searchStatus));
        }
        if (StringUtil.isNumberic(pageSize)) {
            customerDTO.setPageSize(Long.parseLong(pageSize));
        }
        if (StringUtil.isNumberic(pageNo)) {
            customerDTO.setPageNo(Long.parseLong(pageNo));
        }
        if(!"0".equals(districtID)){
        	customerDTO.setDistrictID(districtID);
        }else if(!"0".equals(cityID)){
        	customerDTO.setCityID(cityID);
        }else if(!"0".equals(provinceID)){
        	customerDTO.setProvinceID(provinceID);
        }
        List<CustomerVO> list = memberSerivce.findCustomerApplyByPagination(customerDTO);
        modelAndView.addObject("customers", list);

        modelAndView.addObject("customerDTO", customerDTO);
        List<CustomerGradeVO> gradeList = customerGradeSerivce.findAllCustomerGrade();
        modelAndView.addObject("gradeList", gradeList);
        List<AreaVO> provinceList = areaService.getAllProvince();
        modelAndView.addObject("provinceList", provinceList);
        List<AreaVO> cityList = null;
        List<AreaVO> districtList = null;

        if (provinceList != null && provinceList.size() > 0) {
            cityList = areaService.getAreaByParentID(provinceID == "0" ? provinceList.get(0).getMainID() : provinceID);
        }
        modelAndView.addObject("cityList", cityList);

        if (cityList != null && cityList.size() > 0) {
            districtList = areaService.getAreaByParentID(cityID == "0" ? cityList.get(0).getMainID() : cityID);
        }
        modelAndView.addObject("districtList", districtList);
        modelAndView.addObject("districtID", districtID);
        modelAndView.addObject("cityID", cityID);
        modelAndView.addObject("provinceID", provinceID);
        return modelAndView;
    }

    /**
     * 删除用户
     * 
     * @date 2014年10月24日
     * @author zhuhp
     * @param mainIds
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("deleteCustomer")
    public @ResponseBody
    JsonResult deleteCustomer(String mainIds) {
        try {
            memberSerivce.deleteCustomerBatch(mainIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }

    /**
     * 锁定/解锁
     * 
     * @date 2014年10月24日
     * @author zhuhp
     * @param mainIds
     * @param type
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("isLockCustomer")
    public @ResponseBody
    JsonResult isLockCustomer(String mainIds, String status) {
        try {
            if (StringUtil.isEmpty(status)) {
                return new JsonResult(ResultCode.ERROR_SYSTEM_PARAM_FORMAT);
            }
            if ("1".equals(status)) {
                memberSerivce.lockCustomerBatch(mainIds, status);
            }
            if ("2".equals(status)) {
                memberSerivce.unLockCustomerBatch(mainIds, status);
            }
            if ("5".equals(status)) {
                memberSerivce.lockCustomerBatch(mainIds, status);
            }
            if ("6".equals(status)) {
                memberSerivce.lockCustomerBatch(mainIds, status);
            }
            if ("4".equals(status)) {
                memberSerivce.unLockCustomerBatch(mainIds, status);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }

    /**
     * 会员详情页
     * 
     * @date 2014年10月24日
     * @author zhuhp
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("detailMember")
    public ModelAndView detailMember(String mainId, HttpSession session) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/membercenter/member_detail");
        CustomerVO customerVO = memberSerivce.findCustomeByMianId(mainId);
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

    /**
     * 编辑页面
     * 
     * @date 2014年10月27日
     * @author zhuhp
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("editMemberPage")
    public ModelAndView editMemberPage(String mainId, HttpSession session) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/membercenter/member_edit");
        CustomerVO customerVO = memberSerivce.findCustomeByMianId(mainId);
        modelAndView.addObject("customerVO", customerVO);
        List<AreaVO> provinceList = areaService.getAllProvince();
        modelAndView.addObject("provinceList", provinceList);
        List<AreaVO> cityList = null;
        List<AreaVO> districtList = null;
        if (StringUtil.isNotEmpty(customerVO.getProvinceID())) {
            cityList = areaService.getAreaByParentID(customerVO.getProvinceID());
        } else {
            if (provinceList != null && provinceList.size() > 0) {
                cityList = areaService.getAreaByParentID(provinceList.get(0).getMainID());
            }
        }
        modelAndView.addObject("cityList", cityList);
        if (StringUtil.isNotEmpty(customerVO.getCityID())) {
            districtList = areaService.getAreaByParentID(customerVO.getCityID());
        } else {
            if (cityList != null && cityList.size() > 0) {
                districtList = areaService.getAreaByParentID(cityList.get(0).getMainID());
            }
        }
        modelAndView.addObject("districtList", districtList);
        List<CustomerGradeVO> gradeList = customerGradeSerivce.findAllCustomerGrade();
        modelAndView.addObject("gradeList", gradeList);
        return modelAndView;
    }

    @RequestMapping("getCityByParentID")
    public @ResponseBody
    JsonResult getCityByParentID(String parentID) {
        try {
            List<CityVO> cityList = areaService.getCityByParentID(parentID);
            if (cityList != null && cityList.size() > 0) {
                return new JsonResult(cityList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }
    @RequestMapping("ajaxGetCityByParentID")
    public @ResponseBody
    JsonResult ajaxGetCityByParentID(String parentID) {
    	return this.getCityByParentID(parentID);
    }

    /**
     * 编辑数据
     * 
     * @date 2014年10月27日
     * @author zhuhp
     * @param mainId
     * @param address
     * @param mobile
     * @param telephone
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("editMember")
    public ModelAndView editMember(String mainId, String name, String companyName, String email, String address,
                                   String mobile, String telephone, String provinceID, String cityID,
                                   String districtID, String gradeID, String picURL1, String picURL2, String picURL3) {
        CustomerDTO customerDTO = new CustomerDTO();
        if (StringUtil.isNotEmpty(name)) {
            customerDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(address)) {
            customerDTO.setAddress(address);
        }
        if (StringUtil.isNotEmpty(mobile)) {
            customerDTO.setMobile(mobile);
        }
        if (StringUtil.isNotEmpty(email)) {
            customerDTO.setEmail(email);
        }
        if (StringUtil.isNotEmpty(mainId)) {
            customerDTO.setMainID(mainId);
        }
        if (StringUtil.isNotEmpty(telephone)) {
            customerDTO.setTelephone(telephone);
        }
        if (StringUtil.isNotEmpty(provinceID)) {
            customerDTO.setProvinceID(provinceID);
        }
        if (StringUtil.isNotEmpty(cityID)) {
            customerDTO.setCityID(cityID);
        }
        if (StringUtil.isNotEmpty(districtID)) {
            customerDTO.setDistrictID(districtID);
        }
        if (StringUtil.isNotEmpty(gradeID)) {
            customerDTO.setGradeID(gradeID);
        }
        if (StringUtil.isNotEmpty(companyName)) {
            customerDTO.setCompanyName(companyName);
        }
        if (StringUtil.isNotEmpty(picURL1)) {
            customerDTO.setPicURL1(picURL1);
        }
        if (StringUtil.isNotEmpty(picURL2)) {
            customerDTO.setPicURL2(picURL2);
        }
        if (StringUtil.isNotEmpty(picURL3)) {
            customerDTO.setPicURL3(picURL3);
        }
        /*
         * if (StringUtil.isNotEmpty(birthday)) { try { customerDTO.setBirthday(DateUtil.strintToDatetimeYMD(birthday));
         * } catch (ParseException e) { e.printStackTrace(); } }
         */
        customerDTO.setModifier(getCurrentOperator());
        memberSerivce.updateMember(customerDTO);
        /*
         * ModelAndView modelAndView = this.newModelAndView();
         * modelAndView.setViewName("screen/membercenter/member_detail"); CustomerVO customerVO =
         * memberSerivce.findCustomeByMianId(mainId); modelAndView.addObject("customerVO", customerVO);
         */
        return new ModelAndView("redirect:memberList.htm");
    }

    @RequestMapping("exportMember")
    public void exportMember(HttpServletResponse response, String searchMobile, String searchName, String searchUserName,
            									String gradeID, String searchEmail, Integer searchStatus, 
            											String provinceID, String cityID, String districtID) {
        try {
        	CustomerDTO customerDTO = new CustomerDTO();
        	if (StringUtil.isNotEmpty(searchMobile)) {
                customerDTO.setMobile(searchMobile);
            }
            if (StringUtil.isNotEmpty(searchName)) {
                customerDTO.setName(searchName);
            }
            if (StringUtil.isNotEmpty(searchUserName)) {
                customerDTO.setUsername(searchUserName);
            }
            if (StringUtil.isNotEmpty(gradeID)) {
                customerDTO.setGradeID(gradeID);
            }
            if (StringUtil.isNotEmpty(searchEmail)) {
                customerDTO.setEmail(searchEmail);
            }
            if (null!=searchStatus) {
            	customerDTO.setStatus(searchStatus);
            }
            if(!"0".equals(districtID)){
            	customerDTO.setDistrictID(districtID);
            }else if(!"0".equals(cityID)){
            	customerDTO.setCityID(cityID);
            }else if(!"0".equals(provinceID)){
            	customerDTO.setProvinceID(provinceID);
            }
            memberSerivce.exportMember(response, customerDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("searchCustomerOrGroup")
    public @ResponseBody
    JsonResult searchCustomerOrGroup(String searchType, String pageNo, String pageSize, String keyword) {
        CustomerGroupDTO customerGroupDTO = new CustomerGroupDTO();
        CustomerDTO customerDTO = new CustomerDTO();
        if (StringUtil.isNumberic(pageNo)) {
            customerGroupDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNumberic(pageSize)) {
            customerGroupDTO.setPageSize(Long.parseLong(pageSize));
        }
        JsonResult jsonResult = new JsonResult(ResultCode.NORMAL);
        if (searchType.equals("1")) {
            if (StringUtil.isNotEmpty(keyword)) {
                customerGroupDTO.setName(keyword);
            }
            List<CustomerGroupVO> groupList = customerGroupSerivce.findCustomerGroupByName(customerGroupDTO);
            jsonResult.setResult(groupList);
        } else {
            if (searchType.equals("2")) {
                if (StringUtil.isNotEmpty(keyword)) {
                    customerDTO.setUsername(keyword);
                }
            }
            if (searchType.equals("3")) {
                if (StringUtil.isNotEmpty(keyword)) {
                    customerDTO.setName(keyword);
                }
            }
            if (searchType.equals("4")) {
                if (StringUtil.isNotEmpty(keyword)) {
                    customerDTO.setMobile(keyword);
                }
            }
            List<CustomerVO> customerList = memberSerivce.findCustomerByPagination(customerDTO);
            jsonResult.setResult(customerList);
        }
        return jsonResult;
    }

    /**
     * 删除审核失败的供应商申请
     * 
     * @date 2015年6月10日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("deleteMemberApply")
    public @ResponseBody
    JsonResult deleteMemberApply(String mainID) {
        try {
            memberSerivce.deleteCustomerBatch(mainID);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

}
