package com.kpluswebup.web.controller.account;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.AreaService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/receivable")
public class AccountReceivableController extends BaseController {

    @Autowired
    private SalesOrderService salesOrderService;
    
    @Autowired
	private AreaService areaService;

    /**
     * 应收账款
     * 
     * @date 2015年5月30日
     * @author wanghehua
     * @param accountDetailDTO
     * @param searchName
     * @param searchStartTime
     * @param searchEndTime
     * @param searchStartModifyTime
     * @param searchEndModifyTime
     * @param searchStatus
     * @param searchCustomerName
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("receivableList")
    public ModelAndView receivableList(String pageNo, String pageSize, String searchmainID, String searchStartTime,
                                       String searchEndTime, String searchStartModifyTime, String searchEndModifyTime,
                                       String searchType,String searchStatus, String searchexpressNumber,String provinceID, String cityID, String districtID) {

        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/account/accountreceivable_list");
        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        if (StringUtil.isNotEmpty(searchmainID)) {
            salesOrderDTO.setMainID(searchmainID);
        }
        if (StringUtil.isNotEmpty(searchexpressNumber)) {
            salesOrderDTO.setExpressNumber(searchexpressNumber);
        }
        if (StringUtil.isNotEmpty(searchStartTime)) {
            try {
                salesOrderDTO.setStartTime(DateUtil.strintToDatetimeYMDHMS(searchStartTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(searchEndTime)) {
            try {
                salesOrderDTO.setEndTime(DateUtil.strintToDatetimeYMDHMS(searchEndTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(searchStartModifyTime)) {
            try {
                salesOrderDTO.setStartModifyTime(DateUtil.strintToDatetimeYMDHMS(searchStartModifyTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(searchEndModifyTime)) {
            try {
                salesOrderDTO.setEndModifyTime(DateUtil.strintToDatetimeYMDHMS(searchEndModifyTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(searchType)) {
            salesOrderDTO.setPaymentType(Integer.valueOf(searchType));
        }
        if (StringUtil.isNotEmpty(searchStatus)) {
            salesOrderDTO.setPaymentStatus(Integer.valueOf(searchStatus));
        }
        if (StringUtil.isNotEmpty(pageNo)) {
            salesOrderDTO.setPageNo(Long.valueOf(pageNo));
        }
        if (StringUtil.isNotEmpty(pageSize)) {
            salesOrderDTO.setPageSize(Long.valueOf(pageSize));
        }
        if(!"0".equals(districtID)){
			salesOrderDTO.setDistrictID(districtID);
        }else if(!"0".equals(cityID)){
        	salesOrderDTO.setCityID(cityID);
        	salesOrderDTO.setDistrictID(null);
        }else if(!"0".equals(provinceID)){
        	salesOrderDTO.setProvinceID(provinceID);
        	salesOrderDTO.setCityID(null);
        	salesOrderDTO.setDistrictID(null);
        }
        List<AreaVO> provinceList = areaService.getAllProvince();
        modelAndView.addObject("provinceList", provinceList);
        List<AreaVO> cityList = null;
        List<AreaVO> districtList = null;

        if (provinceList != null && provinceList.size() > 0) {
            cityList = areaService.getAreaByParentID(provinceID == "0" ? provinceList.get(0).getMainID() : provinceID);
        }
        modelAndView.addObject("cityList", cityList);

        if (cityList != null && cityList.size() > 0) {
            districtList = areaService.getAreaByParentID(cityID == "0" ? cityList.get(0).getMainID() : cityID);
        }
        modelAndView.addObject("districtList", districtList);
        modelAndView.addObject("districtID", districtID);
        modelAndView.addObject("cityID", cityID);
        modelAndView.addObject("provinceID", provinceID);
		
        salesOrderDTO.setOrderStatus(null);
        List<SalesOrderVO> list = salesOrderService.getSalesOrderList(salesOrderDTO);
        modelAndView.addObject("receivableList", list);
        modelAndView.addObject("receivableDTO", salesOrderDTO);
        return modelAndView;
    }

    @RequestMapping("confirmReceivable")
    public @ResponseBody
    JsonResult confirmReceivable(String mainIds) {
        Boolean isSuccess = salesOrderService.updatePaymentStatus(mainIds);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("cancelReceivable")
    public @ResponseBody
    JsonResult cancelReceivable(String mainIds) {
        Boolean isSuccess = salesOrderService.updateReceiveStatus(mainIds);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("exportReceivable")
    public void exportReceivable(HttpServletResponse response, String searchmainID, String searchStartTime,String searchEndTime,
    										String searchStartModifyTime, String searchEndModifyTime,String searchType,String searchStatus,
    												String searchexpressNumber,String provinceID, String cityID, String districtID) {
        try {
        	SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        	if (StringUtil.isNotEmpty(searchmainID)) {
                salesOrderDTO.setMainID(searchmainID);
            }
            if (StringUtil.isNotEmpty(searchexpressNumber)) {
                salesOrderDTO.setExpressNumber(searchexpressNumber);
            }
            if (StringUtil.isNotEmpty(searchStartTime)) {
                try {
                    salesOrderDTO.setStartTime(DateUtil.strintToDatetimeYMDHMS(searchStartTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtil.isNotEmpty(searchEndTime)) {
                try {
                    salesOrderDTO.setEndTime(DateUtil.strintToDatetimeYMDHMS(searchEndTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtil.isNotEmpty(searchStartModifyTime)) {
                try {
                    salesOrderDTO.setStartModifyTime(DateUtil.strintToDatetimeYMDHMS(searchStartModifyTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtil.isNotEmpty(searchEndModifyTime)) {
                try {
                    salesOrderDTO.setEndModifyTime(DateUtil.strintToDatetimeYMDHMS(searchEndModifyTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (StringUtil.isNotEmpty(searchType)) {
                salesOrderDTO.setPaymentType(Integer.valueOf(searchType));
            }
            if (StringUtil.isNotEmpty(searchStatus)) {
                salesOrderDTO.setPaymentStatus(Integer.valueOf(searchStatus));
            }
            if(!"0".equals(districtID)){
    			salesOrderDTO.setDistrictID(districtID);
            }else if(!"0".equals(cityID)){
            	salesOrderDTO.setCityID(cityID);
            	salesOrderDTO.setDistrictID(null);
            }else if(!"0".equals(provinceID)){
            	salesOrderDTO.setProvinceID(provinceID);
            	salesOrderDTO.setCityID(null);
            	salesOrderDTO.setDistrictID(null);
            }
            salesOrderService.exportReceivable(response, salesOrderDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
