package com.kpluswebup.web.controller.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.content.service.CmsCategoryService;
import com.kpluswebup.web.content.service.NoticeService;
import com.kpluswebup.web.domain.CmsContentDTO;
import com.kpluswebup.web.vo.CmsCategoryVO;
import com.kpluswebup.web.vo.CmsContentVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/content")
public class NoticeController extends BaseController {

	@Autowired
	private NoticeService noticeService;
	@Autowired
	private CmsCategoryService cmsCategoryService;

	@RequestMapping("noticeList")
	public ModelAndView noticeList(CmsContentDTO cmsContentDTO) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/content/notice_list");
		List<CmsContentVO> list = noticeService
				.findNoticeByPagination(cmsContentDTO);
		modelAndView.addObject("noticeList", list);
		modelAndView.addObject("cmsContentDTO", cmsContentDTO);
		return modelAndView;
	}

	@RequestMapping("/addNoticePage")
	public ModelAndView addNoticePage() {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/content/notice_add");
		List<CmsCategoryVO> list=cmsCategoryService.findCmsCategoryByType(2);
		modelAndView.addObject("cmsCategoryList", list);
		return modelAndView;
	}

	@RequestMapping("addNotice")
	public ModelAndView addNotice(String name,String categoryID,String picUrl,String content,Integer sortOrder) {
		CmsContentDTO cmsContentDTO = new CmsContentDTO();
		if(StringUtil.isNotEmpty(categoryID)){
			cmsContentDTO.setCategoryID(categoryID);
		}
		if (StringUtil.isNotEmpty(name)) {
			cmsContentDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(picUrl)) {
			cmsContentDTO.setPicUrl(picUrl);
		}
		if (StringUtil.isNotEmpty(content)) {
			cmsContentDTO.setContent(content);
		}
		if (sortOrder!=null) {
			cmsContentDTO.setSortOrder(sortOrder);
		}
		cmsContentDTO.setMainID(UUIDUtil.getUUID());
		cmsContentDTO.setCreator(getCurrentOperator());
		noticeService.addNotice(cmsContentDTO);
		return new ModelAndView("redirect:noticeList.htm");
	}

	@RequestMapping("/editNoticePage")
	public ModelAndView editNoticePage(String mainID) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/content/notice_edit");
		CmsContentVO cmsContentVO = noticeService.findNoticeByMainID(mainID);
		modelAndView.addObject("cmsContentVO", cmsContentVO);
		modelAndView.addObject("mainID", mainID);
		List<CmsCategoryVO> list=cmsCategoryService.findCmsCategoryByType(2);
		modelAndView.addObject("cmsCategoryList", list);
		return modelAndView;
	}

	@RequestMapping("editNotice")
	public ModelAndView editNotice(String mainID,String categoryID,String name,String picUrl,String content,Integer sortOrder) {
		CmsContentDTO cmsContentDTO = new CmsContentDTO();
		if(StringUtil.isNotEmpty(categoryID)){
			cmsContentDTO.setCategoryID(categoryID);
		}
		if (StringUtil.isNotEmpty(name)) {
			cmsContentDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(picUrl)) {
			cmsContentDTO.setPicUrl(picUrl);
		}
		if (StringUtil.isNotEmpty(content)) {
			cmsContentDTO.setContent(content);
		}
		if (sortOrder!=null) {
			cmsContentDTO.setSortOrder(sortOrder);
		}
		cmsContentDTO.setMainID(mainID);
		cmsContentDTO.setModifier(getCurrentOperator());
		noticeService.editNotice(cmsContentDTO);
		return new ModelAndView("redirect:noticeList.htm");
	}

	@RequestMapping("deleteNotice")
	public @ResponseBody
	JsonResult deleteNotice(String mainID) {
		Boolean isSuccess = noticeService
				.deleteNoticeByMainID(mainID);
		if (isSuccess) {
			return JsonResult.create();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

}
