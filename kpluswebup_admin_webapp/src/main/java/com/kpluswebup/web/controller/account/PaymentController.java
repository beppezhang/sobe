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
import com.kpluswebup.web.vo.AccountDetailVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/account")
public class PaymentController extends BaseController {

    @Autowired
    private AccountDetailService accountDetailService;

    /**
     * 支付列表
     * 
     * @date 2014年11月27日
     * @author wanghehua
     * @param accountDetailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("paymentList")
    public ModelAndView paymentList(AccountDetailDTO accountDetailDTO, String searchMainID, String searchStartTime,
                                    String searchEndTime, String searchStartModifyTime, String searchEndModifyTime,
                                    String searchPaymentType, String searchSerialNumber, String searchCustomerName) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/account/payment_list");
        accountDetailDTO.setDetailType(2);
        if (StringUtil.isNotEmpty(searchMainID)) {
            accountDetailDTO.setMainID(searchMainID);
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
        if (StringUtil.isNotEmpty(searchPaymentType)) {
            accountDetailDTO.setPaymentType(Integer.valueOf(searchPaymentType));
        }
        if (StringUtil.isNotEmpty(searchSerialNumber)) {
            accountDetailDTO.setSerialNumber(searchSerialNumber);
        }
        if (StringUtil.isNotEmpty(searchCustomerName)) {
            accountDetailDTO.setCustomerName(searchCustomerName);
        }
        List<AccountDetailVO> list = accountDetailService.findAccountDetailByPagination(accountDetailDTO);
        modelAndView.addObject("paymentList", list);
        modelAndView.addObject("accountDetailDTO", accountDetailDTO);
        return modelAndView;
    }

}
