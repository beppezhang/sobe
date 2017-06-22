package com.kpluswebup.web.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.MessageDTO;
import com.kpluswebup.web.member.service.MessageSerivce;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.MessageVO;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/member")
public class MessageController extends BaseController {

    @Autowired
    private MessageSerivce messageSerivce;

    @RequestMapping("messageList")
    public ModelAndView messageList(MessageDTO messageDTO, String searchUserName, String searchOperatorName,
                                    String searchCustomerName) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/message_list");
        if (StringUtil.isNotEmpty(searchUserName)) {
            messageDTO.setSearchUserName(searchUserName);
        }
        if (StringUtil.isNotEmpty(searchOperatorName)) {
            messageDTO.setSearchOperatorName(searchOperatorName);
        }
        if (StringUtil.isNotEmpty(searchCustomerName)) {
            messageDTO.setSearchCustomerName(searchCustomerName);
        }
        List<MessageVO> list = messageSerivce.findMessageByPagination(messageDTO);
        modelAndView.addObject("messageList", list);
        modelAndView.addObject("messageDTO", messageDTO);
        return modelAndView;
    }

    @RequestMapping("membermessageList")
    public ModelAndView membermessageList(String customerID, MessageDTO messageDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/membermessage_list");
        messageDTO.setSendee(customerID);
        List<MessageVO> list = messageSerivce.findMessageByPagination(messageDTO);
        modelAndView.addObject("messageList", list);
        modelAndView.addObject("messageDTO", messageDTO);
        CustomerVO customerVO = new CustomerVO();
        customerVO.setMainID(customerID);
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }
    
    @RequestMapping("messageSendPage")
    public ModelAndView messageSendPage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/message_send");
        return modelAndView;
    }
    
    @RequestMapping("messageSend")
    public ModelAndView messageSend(HttpServletRequest request,String title,String content){
        String [] customerIDs=request.getParameterValues("customerID");
        String [] groupIDs=request.getParameterValues("groupID");
        messageSerivce.messageSend(customerIDs, groupIDs,title, content, getCurrentOperator());
        return new ModelAndView("redirect:messageList.htm");
    }

}
