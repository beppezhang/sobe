package com.kpluswebup.web.controller.promotion;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.CouponBatchDTO;
import com.kpluswebup.web.domain.CouponDTO;
import com.kpluswebup.web.service.CouponBatchService;
import com.kpluswebup.web.vo.CouponBatchVO;
import com.kpluswebup.web.vo.CouponPromotionVO;
import com.kpluswebup.web.vo.CouponVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/promotion")
public class CouponBatchController extends BaseController {

	@Autowired
	private CouponBatchService couponBatchService;

	@RequestMapping("ajaxCouponBatch")
	public @ResponseBody
	JsonResult ajaxCouponBatch() {
		try {
			List<CouponPromotionVO> list = couponBatchService
					.findAllCouponBatch();
			return new JsonResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(ResultCode.ERROR_SYSTEM);
		}
	}

	@RequestMapping("couponbatchList")
	public ModelAndView couponbatchList(CouponBatchDTO couponBatchDTO) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/promotion/couponbatch_list");
		List<CouponBatchVO> list = couponBatchService
				.findCouponBatchByPagination(couponBatchDTO);
		modelAndView.addObject("couponbatchList", list);
		return modelAndView;
	}

	@RequestMapping("addCouponBatchPage")
	public ModelAndView addCouponBatchPage() {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/promotion/couponbatch_add");
		return modelAndView;
	}

	@RequestMapping("addCouponBatch")
	public ModelAndView addCouponBatch(String mainID, String name, String type,
			String amount, String conditionAmount, String score,
			String fromDate, String endDate, String useFromDate,
			String useEndDate, String description, String couponCount,String couponDay) {
		CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
		if (StringUtil.isNotEmpty(mainID)) {
			couponBatchDTO.setMainID(mainID);
		}
		if (StringUtil.isNotEmpty(name)) {
			couponBatchDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(type)) {
			couponBatchDTO.setType(Integer.valueOf(type));
		}
		if (StringUtil.isNotEmpty(amount)) {
			couponBatchDTO.setAmount(Double.valueOf(amount));
		}
		if (StringUtil.isNotEmpty(conditionAmount)) {
			couponBatchDTO.setConditionAmount(Double.valueOf(conditionAmount));
		}
		if (StringUtil.isNotEmpty(score)) {
			couponBatchDTO.setScore(Double.valueOf(score));
		}
		if (StringUtil.isNotEmpty(fromDate)) {
			try {
				couponBatchDTO.setFromDate(DateUtil
						.strintToDatetimeYMD(fromDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtil.isNotEmpty(endDate)) {
			try {
				couponBatchDTO
						.setEndDate(DateUtil.strintToDatetimeYMD(endDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtil.isNotEmpty(useFromDate)) {
			try {
				couponBatchDTO.setUseFromDate(DateUtil
						.strintToDatetimeYMDHMS(useFromDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtil.isNotEmpty(useEndDate)) {
			try {
				couponBatchDTO.setUseEndDate(DateUtil
						.strintToDatetimeYMDHMS(useEndDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtil.isNotEmpty(description)) {
			couponBatchDTO.setDescription(description);
		}
		if (StringUtil.isNotEmpty(couponCount)) {
			couponBatchDTO.setCouponCount(Integer.valueOf(couponCount));
		}
		if(StringUtil.isNotEmpty(couponDay)){
		    couponBatchDTO.setCouponDay(Integer.valueOf(couponDay));
		}
		couponBatchDTO.setMainID(UUIDUtil.getUUID());
		couponBatchDTO.setCreator(getCurrentOperator());
		couponBatchService.addCouponBatch(couponBatchDTO);
		return new ModelAndView("redirect:couponbatchList.htm");
	}

	@RequestMapping("editCouponBatchPage")
	public ModelAndView editCouponBatchPage(String mainID) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/promotion/couponbatch_edit");
		CouponBatchVO couponBatchVO = couponBatchService
				.findCouponBatchByMainId(mainID);
		modelAndView.addObject("couponBatchVO", couponBatchVO);
		return modelAndView;
	}

	@RequestMapping("editCouponBatch")
	public ModelAndView editCouponBatch(String mainID, String name,
			String description) {
		CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
		if (StringUtil.isNotEmpty(name)) {
			couponBatchDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(description)) {
			couponBatchDTO.setDescription(description);
		}
		couponBatchDTO.setMainID(mainID);
		couponBatchDTO.setModifier(getCurrentOperator());
		couponBatchService.updateCouponBatch(couponBatchDTO);
		return new ModelAndView("redirect:couponbatchList.htm");
	}

	@RequestMapping("deleteCouponBatch")
	public @ResponseBody
	JsonResult deleteCouponBatch(String mainIds) {
		Boolean isSuccess = couponBatchService.deleteCouponBatch(mainIds);
		if (isSuccess) {
			return JsonResult.create();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	@RequestMapping("couponList")
	public ModelAndView couponList(String batchID, CouponDTO couponDTO) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/promotion/coupon_list");
		couponDTO.setBatchID(batchID);
		List<CouponVO> list = couponBatchService.findCouponByBatchID(couponDTO);
		modelAndView.addObject("couponList", list);
		CouponBatchVO couponBatchVO = couponBatchService
				.findCouponBatchByMainId(batchID);
		modelAndView.addObject("couponBatchVO", couponBatchVO);
		return modelAndView;
	}

	@RequestMapping("exportCoupon")
	public void exportCoupon(HttpServletResponse response, String batchID) {
		try {
			couponBatchService.exportCoupon(response, batchID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("changeCouponStatus")
	public @ResponseBody
	JsonResult changeCouponStatus(String mainID, Integer status) {
		try {
			couponBatchService.changeCouponStatus(mainID, status);
			return JsonResult.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	@RequestMapping("changeCouponBatchStatus")
	public @ResponseBody
	JsonResult changeCouponCouponStatus(String mainID, Integer status) {
		try {
			couponBatchService.changeCouponBatchStatus(mainID, status);
			return JsonResult.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }
}
