package com.kpluswebup.web.controller.account;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.account.service.AccountDetailService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.domain.SalesOrderReturnApplyDTO;
import com.kpluswebup.web.domain.SalesOrderReturnDTO;
import com.kpluswebup.web.service.SalesOrderReturnApplyService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.vo.AccountDetailVO;
import com.kpluswebup.web.vo.SalesOrderReturnApplyVO;
import com.kpluswebup.web.vo.SalesOrderReturnVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/account")
public class AccountDetailController extends BaseController {

    @Autowired
    private AccountDetailService accountDetailService;
    @Autowired
    private SalesOrderService salesOrderService;
    @Autowired
    private SalesOrderReturnApplyService salesOrderReturnApplyService;

    /**
     * 退款列表
     * 
     * @date 2014年11月7日
     * @author wanghehua
     * @param accountDetailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("refundList")
    public ModelAndView refundList(AccountDetailDTO accountDetailDTO, String searchName, String searchStartTime,
                                   String searchEndTime, String searchStartModifyTime, String searchEndModifyTime,
                                   String searchStatus, String searchCustomerName) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/account/accountdetail_list");
        //accountDetailDTO.setAccountType(1);
        accountDetailDTO.setDetailType(3);
        accountDetailDTO.setType(1);
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

    /**
     * @date 2014年11月29日
     * @author zhuhp
     * @param accountID
     * @param status
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("changeAccountDetailStatus")
    public @ResponseBody
    JsonResult changeAccountDetailStatus(String accountIDs) {
        try {
            if (StringUtils.isBlank(accountIDs)) return new JsonResult(ResultCode.ERROR_SYSTEM_PARAM_FORMAT);
            String[] ids = accountIDs.split(",");
            for (String accountID : ids) {
                AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
                accountDetailDTO.setMainID(accountID);
                accountDetailDTO.setModifyTime(new Date());
                accountDetailDTO.setModifier(getCurrentOperator());
                accountDetailDTO.setStatus(1);
                accountDetailService.updateAccountDetail(accountDetailDTO);
                AccountDetailVO accountDetailVO = accountDetailService.findAccountDetail(accountID);
                SalesOrderReturnVO salesOrderReturnVO = accountDetailService.findSalesOrderReturn(accountDetailVO.getObjID());
                SalesOrderReturnDTO salesOrderReturnDTO = new SalesOrderReturnDTO();
                salesOrderReturnDTO.setMainID(salesOrderReturnVO.getMainID());
                salesOrderReturnDTO.setModifyTime(new Date());
                salesOrderReturnDTO.setModifier(getCurrentOperator());
                salesOrderReturnDTO.setStatus(7);
                accountDetailService.updateSalesOrderReturn(salesOrderReturnDTO);
                
                SalesOrderReturnApplyVO salesOrderReturnApplyVO = salesOrderReturnApplyService.findSalesOrderReturnApplyByMainID(salesOrderReturnVO.getRetrunApplyID());
                SalesOrderReturnApplyDTO salesOrderReturnApplyDTO  = new SalesOrderReturnApplyDTO();
                salesOrderReturnApplyDTO.setMainID(salesOrderReturnApplyVO.getMainID());
                salesOrderReturnApplyDTO.setStatus(7);
                salesOrderReturnDTO.setModifier(getCurrentOperator());
                accountDetailService.updateSalesOrderReturnApply(salesOrderReturnApplyDTO);
                
                SalesOrderDTO salesOrderDTO  = new SalesOrderDTO();
                salesOrderDTO.setMainID(salesOrderReturnApplyVO.getSalesOrderID());
                salesOrderDTO.setOrderStatus(8);//已退货
                salesOrderService.updateSalesOrder(salesOrderDTO);
            }
            /*
             * SalesOrderReturnApplyVO salesOrderReturnApplyVO = accountDetailService
             * .findSalesOrderReturnApply(salesOrderReturnVO .getRetrunApplyID()); SalesOrderReturnApplyDTO
             * salesOrderReturnApplyDTO=new SalesOrderReturnApplyDTO();
             * salesOrderReturnApplyDTO.setId(salesOrderReturnApplyVO.getId());
             * salesOrderReturnApplyDTO.setModifyTime(new Date()); if (status.equals("1")) {
             * salesOrderReturnApplyDTO.setStatus(7); } else { salesOrderReturnApplyDTO.setStatus(2); }
             * accountDetailService .updateSalesOrderReturnApply(salesOrderReturnApplyDTO);
             */
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }

}
