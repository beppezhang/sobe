package com.kpluswebup.web.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.account.service.AccountDetailService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.vo.AccountDetailVO;
import com.kpluswebup.web.vo.CustomerVO;

@Controller
@RequestMapping("/admin/member")
public class MemberScoreController extends BaseController {

    @Autowired
    private AccountDetailService accountDetailService;
    @Autowired
    private MemberSerivce        memberSerivce;

    @RequestMapping("scoreList")
    public ModelAndView scoreList(AccountDetailDTO accountDetailDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/score_list");
        accountDetailDTO.setDetailType(4);
        List<AccountDetailVO> list = accountDetailService.findAccountDetailByPagination(accountDetailDTO);
        modelAndView.addObject("scoreList", list);
        modelAndView.addObject("accountDetailDTO", accountDetailDTO);
        return modelAndView;
    }

    @RequestMapping("addScorePage")
    public ModelAndView addScorePage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/membercenter/score_add");
        List<CustomerVO> list = memberSerivce.findCustomerAll();
        modelAndView.addObject("customerList", list);
        return modelAndView;
    }

    @RequestMapping("addScore")
    public ModelAndView addScore(String type, String amount, String description) {
        String[] customerIDs = getRequest().getParameterValues("customerID");
        String[] groupIDs = getRequest().getParameterValues("groupID");
        accountDetailService.addScore(customerIDs, groupIDs, type, amount, description, getCurrentOperator());
        return new ModelAndView("redirect:scoreList.do");
    }

    @RequestMapping("memberscoreList")
    public ModelAndView memberscoreList(AccountDetailDTO accountDetailDTO, String customerID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/membersocre_list");
        accountDetailDTO.setCustomerID(customerID);
        accountDetailDTO.setDetailType(4);
        List<AccountDetailVO> list = accountDetailService.findAccountDetailByPagination(accountDetailDTO);
        modelAndView.addObject("memberscoreList", list);
        CustomerVO customerVO = new CustomerVO();
        customerVO.setMainID(customerID);
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

}
