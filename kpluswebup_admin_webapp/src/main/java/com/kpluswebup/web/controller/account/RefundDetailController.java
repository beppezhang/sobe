package com.kpluswebup.web.controller.account;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.account.service.AccountDetailService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.service.SalesOrderReturnApplyService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.vo.AccountDetailVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/account")
public class RefundDetailController extends BaseController {

    @Autowired
    private AccountDetailService accountDetailService;
    @Autowired
    private SalesOrderService salesOrderService;
    @Autowired
    private SalesOrderReturnApplyService salesOrderReturnApplyService;

    /**
     * 退款明细
     * 
     * @date 2014年11月7日
     * @author wanghehua
     * @param accountDetailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("refundDetailList")
    public ModelAndView refundDetailList(AccountDetailDTO accountDetailDTO, String searchName, String searchStartTime,
                                   String searchEndTime, String searchStartModifyTime, String searchEndModifyTime,
                                   String searchStatus, String searchCustomerName) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/account/refunddetail_list");
        //accountDetailDTO.setAccountType(1);
        accountDetailDTO.setDetailType(3);
        accountDetailDTO.setType(2);
        if (StringUtil.isNotEmpty(searchName)) {
            accountDetailDTO.setItemName(searchName);
        }
        if (StringUtil.isNotEmpty(searchStartTime)) {
            try {
                accountDetailDTO.setStartTime(DateUtil.strintToDatetimeYMDHMS(searchStartTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(searchEndTime)) {
            try {
                accountDetailDTO.setEndTime(DateUtil.strintToDatetimeYMDHMS(searchEndTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(searchStartModifyTime)) {
            try {
                accountDetailDTO.setStartModifyTime(DateUtil.strintToDatetimeYMDHMS(searchStartModifyTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(searchEndModifyTime)) {
            try {
                accountDetailDTO.setEndModifyTime(DateUtil.strintToDatetimeYMDHMS(searchEndModifyTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(searchStatus)) {
            accountDetailDTO.setStatus(Integer.valueOf(searchStatus));
        }
        if (StringUtil.isNotEmpty(searchCustomerName)) {
            accountDetailDTO.setCustomerName(searchCustomerName);
        }
        List<AccountDetailVO> list = accountDetailService.findAccountDetailByPagination(accountDetailDTO);
        modelAndView.addObject("refundList", list);
        modelAndView.addObject("accountDetailDTO", accountDetailDTO);
        return modelAndView;
    }

}
