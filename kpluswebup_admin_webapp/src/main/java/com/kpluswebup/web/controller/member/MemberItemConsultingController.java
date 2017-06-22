package com.kpluswebup.web.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.ItemConsultingDTO;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.ItemConsultingVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/member")
public class MemberItemConsultingController extends BaseController {

    @Autowired
    private MemberSerivce memberSerivce;

    @RequestMapping("memberconsultingList")
    public ModelAndView memberconsultingList(ItemConsultingDTO itemConsultingDTO, String customerID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/memberconsulting_list");
        itemConsultingDTO.setCustomerID(customerID);
        List<ItemConsultingVO> list = memberSerivce.findCustomerConsulting(itemConsultingDTO);
        modelAndView.addObject("consultingList", list);
        CustomerVO customerVO = new CustomerVO();
        customerVO.setMainID(customerID);
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

    @RequestMapping("replyMemberConsultingPage")
    public ModelAndView replyMemberConsultingPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/memberconsulting_reply");
        ItemConsultingVO itemConsultingVO = memberSerivce.findConsultingByMainID(mainID);
        modelAndView.addObject("itemConsultingVO", itemConsultingVO);
        CustomerVO customerVO = new CustomerVO();
        customerVO.setMainID(itemConsultingVO.getCustomerID());
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

    @RequestMapping("replyMemberConsulting")
    public ModelAndView replyMemberConsulting(String mainID, String customerID, String reply) {
        ItemConsultingDTO itemConsultingDTO = new ItemConsultingDTO();
        if (StringUtil.isNotEmpty(reply)) {
            itemConsultingDTO.setReply(reply);
        }
        itemConsultingDTO.setMainID(mainID);
        itemConsultingDTO.setModifier(getCurrentOperator());
        itemConsultingDTO.setReplier(getCurrentOperator());
        memberSerivce.replyConsulting(itemConsultingDTO);
        return new ModelAndView("redirect:memberconsultingList.do?customerID=" + customerID);
    }

    @RequestMapping("deleteConsulting")
    public @ResponseBody
    JsonResult deleteConsulting(String mainIds) {
        Boolean isSuccess = memberSerivce.deleteConsulting(mainIds);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("consultingList")
    public ModelAndView consultingList(ItemConsultingDTO itemConsultingDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/consulting_list");
        List<ItemConsultingVO> list = memberSerivce.findCustomerConsulting(itemConsultingDTO);
        modelAndView.addObject("consultingList", list);
        modelAndView.addObject("itemConsultingDTO", itemConsultingDTO);
        return modelAndView;
    }

    @RequestMapping("replyConsultingPage")
    public ModelAndView replyConsultingPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/consulting_reply");
        ItemConsultingVO itemConsultingVO = memberSerivce.findConsultingByMainID(mainID);
        modelAndView.addObject("itemConsultingVO", itemConsultingVO);
        return modelAndView;
    }

    @RequestMapping("replyConsulting")
    public ModelAndView replyConsulting(String mainID, String reply) {
        ItemConsultingDTO itemConsultingDTO = new ItemConsultingDTO();
        if (StringUtil.isNotEmpty(reply)) {
            itemConsultingDTO.setReply(reply);
        }
        itemConsultingDTO.setMainID(mainID);
        itemConsultingDTO.setReplier(getCurrentOperator());
        itemConsultingDTO.setModifier(getCurrentOperator());
        memberSerivce.replyConsulting(itemConsultingDTO);
        return new ModelAndView("redirect:consultingList.htm");
    }

}
