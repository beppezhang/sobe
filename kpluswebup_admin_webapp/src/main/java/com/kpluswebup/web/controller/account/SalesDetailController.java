package com.kpluswebup.web.controller.account;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/account")
public class SalesDetailController extends BaseController {

    @Autowired
    private SalesOrderService salesOrderService;

    /**
     * 销售明细
     * 
     * @date 2014年11月7日
     * @author wanghehua
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("salesList")
    public ModelAndView salesList(AccountDetailDTO accountDetailDTO, String searchName, String searchStartTime,
            String searchEndTime, String searchStartModifyTime, String searchEndModifyTime,
            String searchStatus, String searchCustomerName) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/account/salesdetail_list");
        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        salesOrderDTO.setOrderStatus(6);
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
        List<SalesOrderVO> list = salesOrderService.getSalesOrderList(salesOrderDTO);
        modelAndView.addObject("salesList", list);
        modelAndView.addObject("SalesOrderDTO", salesOrderDTO);
        return modelAndView;
    }
}
