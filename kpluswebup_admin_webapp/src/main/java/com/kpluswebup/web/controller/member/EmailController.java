package com.kpluswebup.web.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.EmailDTO;
import com.kpluswebup.web.member.service.EmailSerivce;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.EmailVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/member")
public class EmailController extends BaseController {

    @Autowired
    private EmailSerivce emailSerivce;

    @RequestMapping("emailList")
    public ModelAndView emailList(EmailDTO emailDTO, String searchUserName, String searchEmail,
                                  String searchCustomerName, String searchStatus) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/email_list");
        if (StringUtil.isNotEmpty(searchUserName)) {
            emailDTO.setSearchUserName(searchUserName);
        }
        if (StringUtil.isNotEmpty(searchEmail)) {
            emailDTO.setEmail(searchEmail);
        }
        if (StringUtil.isNotEmpty(searchCustomerName)) {
            emailDTO.setSearchCustomerName(searchCustomerName);
        }
        if (StringUtil.isNotEmpty(searchStatus)) {
            emailDTO.setStatus(Integer.valueOf(searchStatus));
        }
        List<EmailVO> list = emailSerivce.findEmailByPagination(emailDTO);
        modelAndView.addObject("emailList", list);
        modelAndView.addObject("emailDTO", emailDTO);
        return modelAndView;
    }

    @RequestMapping("memberemailList")
    public ModelAndView memberemailList(String customerID, EmailDTO emailDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/memberemail_list");
        emailDTO.setCustomerID(customerID);
        List<EmailVO> list = emailSerivce.findEmailByPagination(emailDTO);
        modelAndView.addObject("emailList", list);
        modelAndView.addObject("emailDTO", emailDTO);
        CustomerVO customerVO = new CustomerVO();
        customerVO.setMainID(customerID);
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

    @RequestMapping("sendEmail")
    public @ResponseBody
    JsonResult sendEmail(String mainID) {
        Boolean isSuccess = emailSerivce.findEmailByMainID(mainID);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("emailSendPage")
    public ModelAndView emailSendPage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/email_send");
        return modelAndView;
    }
    
    @RequestMapping("emailSend")
    public ModelAndView emailSend(HttpServletRequest request,String title,String content){
        String [] customerIDs=request.getParameterValues("customerID");
        String [] groupIDs=request.getParameterValues("groupID");
        emailSerivce.emailSend(customerIDs, groupIDs,title, content, getCurrentOperator());
        return new ModelAndView("redirect:emailList.htm");
    }

}
