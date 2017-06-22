package com.kpluswebup.web.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.CustomerSalesOrderVO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.vo.CustomerVO;

@Controller
@RequestMapping("/admin/member")
public class MemberSalesOrderController extends BaseController {

    @Autowired
    private MemberSerivce memberSerivce;

    @RequestMapping("salesorderList")
    public ModelAndView salesorderList(SalesOrderDTO salesOrderDTO, String customerID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/salesorder_list");
        salesOrderDTO.setCustomerID(customerID);
        List<CustomerSalesOrderVO> list = memberSerivce.findCustomerSalesOrder(salesOrderDTO);
        modelAndView.addObject("salesorderList", list);
        CustomerVO customerVO = new CustomerVO();
        customerVO.setMainID(customerID);
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

}
