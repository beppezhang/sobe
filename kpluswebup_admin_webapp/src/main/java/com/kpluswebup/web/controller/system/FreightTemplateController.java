package com.kpluswebup.web.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.AreaService;
import com.kpluswebup.web.admin.system.service.ExpressFormatService;
import com.kpluswebup.web.admin.system.service.ExpressService;
import com.kpluswebup.web.admin.system.service.FreightTemplatePriceService;
import com.kpluswebup.web.admin.system.service.FreightTemplateService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.ExpressFormatVO;
import com.kpluswebup.web.vo.ExpressVO;
import com.kpluswebup.web.vo.FreightTemplatePriceVO;
import com.kpluswebup.web.vo.FreightTemplateVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;

@Controller
@RequestMapping("/admin/system")
public class FreightTemplateController extends BaseController {

	@Autowired
	private FreightTemplateService freightTemplateService;
	@Autowired
	private ExpressService expressService;
	@Autowired
	private ExpressFormatService expressFormatService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private FreightTemplatePriceService freightTemplatePriceService;

	@RequestMapping("freightTemplateList")
	public ModelAndView freightTemplateList() {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/system/freighttemplate_list");
		List<FreightTemplateVO> list = freightTemplateService
				.findFreightTemplate();
		modelAndView.addObject("freighttemplateList", list);
		return modelAndView;
	}

	@RequestMapping("addFreightTemplatePage")
	public ModelAndView addFreightTemplatePage() {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/system/freighttemplate_add");
		List<ExpressVO> elist = expressService.findALlExpress();
		modelAndView.addObject("expressList", elist);
		List<ExpressFormatVO> flist = expressFormatService
				.findALLExpressFormat();
		modelAndView.addObject("formatList", flist);
		List<AreaVO> provinceList=areaService.getAllProvince();
		if(provinceList!=null&&provinceList.size()>0){
			for(AreaVO areaVO:provinceList){
				List<AreaVO> cityList=areaService.getAreaByParentID(areaVO.getMainID());
				areaVO.setCityList(cityList);
			}
		}
		modelAndView.addObject("provinceList", provinceList);
		return modelAndView;
	}

	@RequestMapping("addFreightTemplate")
	public ModelAndView addFreightTemplate(HttpServletRequest request,
			String name, String priceType, String expressID, String formatID,
			String firstCondition, String firstPrice, String addUnit,
			String addPrice, String description) {
		String[] fTPricesAreaIDs = request.getParameterValues("fTPricesAreaIDs");
		freightTemplateService.addFreightTemplate(name, priceType, expressID,
				formatID, firstCondition, firstPrice, addUnit, addPrice,
				description, fTPricesAreaIDs,getCurrentOperator());
		return new ModelAndView("redirect:freightTemplateList.htm");
	}

	@RequestMapping("deleteFreightTemplate")
	public @ResponseBody
	JsonResult deleteFreightTemplate(String mainID) {
		Boolean isSuccess = freightTemplateService
				.deleteFreightTemplate(mainID);
		if (isSuccess) {
			return JsonResult.create();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	@RequestMapping("setDefaultFreightTemplate")
	public @ResponseBody
	JsonResult setDefaultFreightTemplate(String mainID) {
		Boolean isSuccess = freightTemplateService
				.updateFreightTemplateIsDefault(mainID);
		if (isSuccess) {
			return JsonResult.create();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	@RequestMapping("editFreightTemplatePage")
	public ModelAndView editFreightTemplatePage(String mainID) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/system/freighttemplate_edit");
		FreightTemplateVO freightTemplateVO = freightTemplateService
				.findFreightTemplateByMainID(mainID);
		modelAndView.addObject("freightTemplateVO", freightTemplateVO);
		List<FreightTemplatePriceVO> ftplist=freightTemplatePriceService.findFreightTemplatePriceByFTID(mainID);
		modelAndView.addObject("ftpList", ftplist);
		List<ExpressVO> elist = expressService.findALlExpress();
		modelAndView.addObject("expressList", elist);
		List<ExpressFormatVO> flist = expressFormatService
				.findALLExpressFormat();
		modelAndView.addObject("formatList", flist);
		List<AreaVO> provinceList=areaService.getAllProvince();
		if(provinceList!=null&&provinceList.size()>0){
			for(AreaVO areaVO:provinceList){
				List<AreaVO> cityList=areaService.getAreaByParentID(areaVO.getMainID());
				areaVO.setCityList(cityList);
			}
		}
		modelAndView.addObject("provinceList", provinceList);
		return modelAndView;
	}

	@RequestMapping("editFreightTemplate")
	public ModelAndView editFreightTemplate(HttpServletRequest request,
			String mainID, String name, String priceType, String expressID,
			String formatID, String firstCondition, String firstPrice,
			String addUnit, String addPrice, String description) {
		String[] fTPricesAreaIDs = request.getParameterValues("fTPricesAreaIDs");
		freightTemplateService.updateFreightTemplate(mainID, name, priceType,
				expressID, formatID, firstCondition, firstPrice, addUnit,
				addPrice, description, fTPricesAreaIDs,getCurrentOperator());
		return new ModelAndView("redirect:freightTemplateList.htm");
	}
	
}
