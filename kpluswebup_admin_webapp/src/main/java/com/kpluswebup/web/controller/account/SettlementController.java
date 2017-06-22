package com.kpluswebup.web.controller.account;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.account.dao.FinalstatementDAO;
import com.kpluswebup.web.account.service.FinalstatementService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.FinalstatementDTO;
import com.kpluswebup.web.domain.SalesOrderLineDTO;
import com.kpluswebup.web.domain.SettlementDTO;
import com.kpluswebup.web.service.SalesOrderLineService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.service.SettlementService;
import com.kpluswebup.web.vo.FinalstatementVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpluswebup.web.vo.SettlementVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/account")
public class SettlementController extends BaseController {

    @Autowired
    private SettlementService settlementService;

    @Autowired
    private FinalstatementService finalstatementService;
    
    @Autowired
    private SalesOrderService salesOrderService;
    
    @Autowired
    private SalesOrderLineService salesOrderLineService;
    
    /**
     * 结算单列表
     * 
     * @date 2014年11月7日
     * @author wanghehua
     * @param settlementDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("settlementList")
    public ModelAndView settlementList(FinalstatementDTO finalstatementDTO,String finalstatementMainID, String supplierName,
																				String modifier, String status) {
       // FinalstatementDTO finalstatementDTO = new  FinalstatementDTO();
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/account/settlement_list");
        if(StringUtil.isNotEmpty(finalstatementMainID)){
			finalstatementDTO.setMainID(finalstatementMainID);
        }
		if(StringUtil.isNotEmpty(supplierName)){
			finalstatementDTO.setSupplierName(supplierName);
        }
		if(StringUtil.isNotEmpty(modifier)){
			finalstatementDTO.setModifier(modifier);
        }
		if(StringUtil.isNotEmpty(status)){
			finalstatementDTO.setStatus(Integer.valueOf(status));
        }
        List<FinalstatementVO> list = finalstatementService.getFinalStatementListByPaginateion(finalstatementDTO);
        modelAndView.addObject("settlementList", list);
        modelAndView.addObject("finalstatementDTO", finalstatementDTO);
        return modelAndView;
    }
    
    /**
     * 结算操作
     * 
     * @date 2014年10月29日
     * @author lupeng
     * @param mainId
     * @param status
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("changeSettlementStatus")
    public @ResponseBody
    JsonResult changeSettlementStatus(String mainId, String status) {
        try {
        	if (StringUtils.isBlank(mainId) || StringUtils.isBlank(status)) return new JsonResult(
                    																			ResultCode.ERROR_SYSTEM_PARAM_FORMAT);
                //settlementService.settlementOperation(mainId, status);
        	 String[] ids = mainId.split(",");
             for (String mainID : ids) {
                 finalstatementService.confirmFinalstatement(mainID, this.getCurrentOperatorInfo().getMainID());
             }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }
    
    /**
     * 结算明细
     * 
     * @date 2014年11月7日
     * @author wanghehua
     * @param settlementDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("settlementDetail.htm")
    public ModelAndView settlementDetail(String finalStatementID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/account/settlementDetail_list");
        FinalstatementVO finalstatement = finalstatementService.getFinalStatementByMainID(finalStatementID);
        modelAndView.addObject("finalstatement", finalstatement);
        return modelAndView;
    }
    
    @RequestMapping("exportSettlementDetail")
	public void exportSettlementDetail(HttpServletResponse response,String finalStatementID) {
		try {
			finalstatementService.exportSettlementDetail(response, finalStatementID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @RequestMapping("exportSettlement")
	public void exportSettlement(HttpServletResponse response, String finalstatementMainID, String supplierName,
																	String modifier, String status) {
		try {
			FinalstatementDTO finalstatementDTO = new FinalstatementDTO();
			if(StringUtil.isNotEmpty(finalstatementMainID)){
				finalstatementDTO.setMainID(finalstatementMainID);
	        }
			if(StringUtil.isNotEmpty(supplierName)){
				finalstatementDTO.setSupplierName(supplierName);
	        }
			if(StringUtil.isNotEmpty(modifier)){
				finalstatementDTO.setModifier(modifier);
	        }
			if(StringUtil.isNotEmpty(status)){
				finalstatementDTO.setStatus(Integer.valueOf(status));
            }
			finalstatementService.exportSettlement(response, finalstatementDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
