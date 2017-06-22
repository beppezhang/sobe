package com.kpluswebup.web.controller.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.content.service.CmsCategoryService;
import com.kpluswebup.web.content.service.HelpService;
import com.kpluswebup.web.domain.CmsHelpCenterDTO;
import com.kpluswebup.web.vo.CmsCategoryVO;
import com.kpluswebup.web.vo.CmsHelpCenterVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/content")
public class HelpController extends BaseController {

	@Autowired
	private HelpService helpService;
	@Autowired
	private CmsCategoryService cmsCategoryService;

	@RequestMapping("helpList")
	public ModelAndView helpList(CmsHelpCenterDTO cmsHelpCenterDTO) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/content/help_list");
		List<CmsHelpCenterVO> list = helpService
				.findHelpByPagination(cmsHelpCenterDTO);
		modelAndView.addObject("helpList", list);
		modelAndView.addObject("cmsHelpCenterDTO", cmsHelpCenterDTO);
		return modelAndView;
	}

	@RequestMapping("/addHelpPage")
	public ModelAndView addHelpPage() {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/content/help_add");
		List<CmsCategoryVO> list=cmsCategoryService.findCmsCategoryByType(4);
		modelAndView.addObject("cmsCategoryList", list);
		return modelAndView;
	}

	@RequestMapping("addHelp")
	public ModelAndView addHelp(String name,String categoryID,String content,Integer sortOrder) {
		CmsHelpCenterDTO cmsHelpCenterDTO = new CmsHelpCenterDTO();
		if(StringUtil.isNotEmpty(categoryID)){
			cmsHelpCenterDTO.setCategoryID(categoryID);
		}
		if (StringUtil.isNotEmpty(name)) {
			cmsHelpCenterDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(content)) {
			cmsHelpCenterDTO.setContent(content);
		}
		if (sortOrder!=null) {
			cmsHelpCenterDTO.setSortOrder(sortOrder);
		}
		cmsHelpCenterDTO.setMainID(UUIDUtil.getUUID());
		cmsHelpCenterDTO.setCreator(getCurrentOperator());
		helpService.addHelp(cmsHelpCenterDTO);
		return new ModelAndView("redirect:helpList.htm");
	}

	@RequestMapping("/editHelpPage")
	public ModelAndView editHelpPage(String mainID) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/content/help_edit");
		CmsHelpCenterVO cmsHelpCenterVO = helpService.findHelpByMainID(mainID);
		modelAndView.addObject("cmsHelpCenterVO", cmsHelpCenterVO);
		modelAndView.addObject("mainID", mainID);
		List<CmsCategoryVO> list=cmsCategoryService.findCmsCategoryByType(4);
		modelAndView.addObject("cmsCategoryList", list);
		return modelAndView;
	}

	@RequestMapping("editHelp")
	public ModelAndView editHelp(String mainID,String categoryID,String name,String content,Integer sortOrder) {
		CmsHelpCenterDTO cmsHelpCenterDTO = new CmsHelpCenterDTO();
		if(StringUtil.isNotEmpty(categoryID)){
			cmsHelpCenterDTO.setCategoryID(categoryID);
		}
		if (StringUtil.isNotEmpty(name)) {
			cmsHelpCenterDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(content)) {
			cmsHelpCenterDTO.setContent(content);
		}
		if (sortOrder!=null) {
			cmsHelpCenterDTO.setSortOrder(sortOrder);
		}
		cmsHelpCenterDTO.setMainID(mainID);
		cmsHelpCenterDTO.setModifier(getCurrentOperator());
		helpService.editHelp(cmsHelpCenterDTO);
		return new ModelAndView("redirect:helpList.htm");
	}

	@RequestMapping("deleteHelp")
	public @ResponseBody
	JsonResult deleteHelp(String mainID) {
		Boolean isSuccess = helpService
				.deleteHelpByMainID(mainID);
		if (isSuccess) {
			return JsonResult.create();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

}
