package com.kpluswebup.web.controller.report;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.account.service.AccountDetailService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.vo.AccountDetailVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/report")
public class ReportController extends BaseController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private AccountDetailService accountDetailService;
    
    
    // 销售报表
    @RequestMapping("saleReportList")
    public ModelAndView saleReportList(ItemDTO itemDTO,String searchmainID,String searchName,String searchStartModifyTime, String searchEndModifyTime) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/screen/report/salereport_list");
        if(StringUtil.isNotEmpty(searchmainID)){
            itemDTO.setMainID(searchmainID);
        }
        if(StringUtil.isNotEmpty(searchName)){
            itemDTO.setName(searchName);
        }
        if (StringUtil.isNotEmpty(searchStartModifyTime)) {
            try {
            	itemDTO.setStartTime(DateUtil.strintToDatetimeYMDHMS(searchStartModifyTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(searchEndModifyTime)) {
            try {
            	itemDTO.setEndTime(DateUtil.strintToDatetimeYMDHMS(searchEndModifyTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<ItemVO> itemList=itemService.findItemByPagination(itemDTO);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("itemDTO", itemDTO);
        return modelAndView;
    }
    
    @RequestMapping("exportItem")
    public void exportItem(HttpServletResponse response,String searchmainID,String searchName,String searchStartModifyTime, String searchEndModifyTime) {
        try {
        	ItemDTO itemDTO = new ItemDTO();
        	if(StringUtil.isNotEmpty(searchmainID)){
                itemDTO.setMainID(searchmainID);
            }
            if(StringUtil.isNotEmpty(searchName)){
                itemDTO.setName(searchName);
            }
            if (StringUtil.isNotEmpty(searchStartModifyTime)) {
                try {
                	itemDTO.setStartTime(DateUtil.strintToDatetimeYMDHMS(searchStartModifyTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtil.isNotEmpty(searchEndModifyTime)) {
                try {
                	itemDTO.setEndTime(DateUtil.strintToDatetimeYMDHMS(searchEndModifyTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            itemService.exportItem(response,itemDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 采购报表
    @RequestMapping("purchaseReportList")
    public ModelAndView purchaseReportList(ItemDTO itemDTO,String searchmainID,String searchName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/screen/report/purchasereport_list");
        if(StringUtil.isNotEmpty(searchmainID)){
            itemDTO.setMainID(searchmainID);
        }
        if(StringUtil.isNotEmpty(searchName)){
            itemDTO.setName(searchName);
        }
        List<ItemVO> itemList=itemService.findItemByPagination(itemDTO);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("itemDTO", itemDTO);
        return modelAndView;
    }
    
    @RequestMapping("exportPurchaseItem")
    public void exportPurchaseItem(HttpServletResponse response,String searchmainID,String searchName) {
        try {
        	ItemDTO itemDTO = new ItemDTO();
        	if(StringUtil.isNotEmpty(searchmainID)){
        		itemDTO.setMainID(searchmainID);
        	}
        	if(StringUtil.isNotEmpty(searchName)){
        		itemDTO.setName(searchName);
        	}
        	itemService.exportPurchaseItem(response,itemDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 财务报表
    @RequestMapping("financeReportList")
    public ModelAndView financeReportList(AccountDetailDTO accountDetailDTO,String searchmainID,String searchcustomerName,String searchType,String searchStartTime,String searchEndTime) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/screen/report/financereport_list");
        if (StringUtil.isNotEmpty(searchmainID)) {
            accountDetailDTO.setSerialNumber(searchmainID);
        }
        if (StringUtil.isNotEmpty(searchcustomerName)) {
            accountDetailDTO.setCustomerName(searchcustomerName);
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
        if (StringUtil.isNotEmpty(searchType)) {
            accountDetailDTO.setPaymentType(Integer.valueOf(searchType));
        }
        accountDetailDTO.setDetailType(2);
        List<AccountDetailVO> list = accountDetailService.findAccountDetailByPagination(accountDetailDTO);
        modelAndView.addObject("financeList", list);
        modelAndView.addObject("accountDetailDTO", accountDetailDTO);
        return modelAndView;
    }
    
    @RequestMapping("exportFinanceReport")
    public void exportFinanceReport(HttpServletResponse response,String searchmainID,String searchcustomerName,
    										String searchType,String searchStartTime,String searchEndTime) {
        try {
        	AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
        	if (StringUtil.isNotEmpty(searchmainID)) {
                accountDetailDTO.setSerialNumber(searchmainID);
            }
            if (StringUtil.isNotEmpty(searchcustomerName)) {
                accountDetailDTO.setCustomerName(searchcustomerName);
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
            if (StringUtil.isNotEmpty(searchType)) {
                accountDetailDTO.setPaymentType(Integer.valueOf(searchType));
            }
            accountDetailService.exportFinanceReport(response,accountDetailDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
