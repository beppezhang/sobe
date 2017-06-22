package com.kpluswebup.web.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.SMSDTO;
import com.kpluswebup.web.member.service.SmsSerivce;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.SmsVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.SendSms;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/member")
public class SmsController extends BaseController {

    @Autowired
    private SmsSerivce smsSerivce;

    @RequestMapping("smsList")
    public ModelAndView smsList(SMSDTO smsdto, String searchUserName, String searchMobile, String searchCustomerName,
                                String searchStatus) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/sms_list");
        if (StringUtil.isNotEmpty(searchUserName)) {
            smsdto.setSearchUserName(searchUserName);
        }
        if (StringUtil.isNotEmpty(searchMobile)) {
            smsdto.setMobile(searchMobile);
        }
        if (StringUtil.isNotEmpty(searchCustomerName)) {
            smsdto.setSearchCustomerName(searchCustomerName);
        }
        if (StringUtil.isNotEmpty(searchStatus)) {
            smsdto.setStatus(Integer.valueOf(searchStatus));
        }
        List<SmsVO> list = smsSerivce.findSmsByPagination(smsdto);
        modelAndView.addObject("smsList", list);
        modelAndView.addObject("smsDTO", smsdto);
        return modelAndView;
    }

    @RequestMapping("membersmsList")
    public ModelAndView membersmsList(String customerID, SMSDTO smsdto) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/membersms_list");
        smsdto.setCustomerID(customerID);
        List<SmsVO> list = smsSerivce.findSmsByPagination(smsdto);
        modelAndView.addObject("smsList", list);
        modelAndView.addObject("smsDTO", smsdto);
        CustomerVO customerVO = new CustomerVO();
        customerVO.setMainID(customerID);
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

    @RequestMapping("smsSendPage")
    public ModelAndView smsSendPage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/sms_send");
        return modelAndView;
    }

    @RequestMapping("smsSend")
    public ModelAndView smsSend(HttpServletRequest request, String title, String content) {
        String[] customerIDs = request.getParameterValues("customerID");
        String[] groupIDs = request.getParameterValues("groupID");
        smsSerivce.smsSend(customerIDs, groupIDs, title, content, getCurrentOperator());
        return new ModelAndView("redirect:smsList.htm");
    }

    @RequestMapping("sendSms")
    public @ResponseBody
    JsonResult sendSms(String mainID) {
        SmsVO smsVO = smsSerivce.findSmsByMaminID(mainID);
        if (smsVO != null && StringUtil.isNotEmpty(smsVO.getMobile()) && StringUtil.isNotEmpty(smsVO.getContent())) {
            String[] telphone = { smsVO.getMobile() };
            Integer msg = SendSms.sendSMS(telphone, smsVO.getContent(), 5);
            SMSDTO smsDTO = new SMSDTO();
            smsDTO.setMainID(mainID);
            if (msg == 0) {
                smsDTO.setStatus(1);
                smsSerivce.updateSmsByMainID(smsDTO);
                return JsonResult.create();
            } else {
                smsSerivce.updateSmsByMainID(smsDTO);
                return new JsonResult(ResultCode.ERROR_SYSTEM);
            }
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

}
