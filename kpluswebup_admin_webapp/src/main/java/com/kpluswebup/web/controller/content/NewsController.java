package com.kpluswebup.web.controller.content;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.content.service.CmsCategoryService;
import com.kpluswebup.web.content.service.NewsService;
import com.kpluswebup.web.domain.CmsContentDTO;
import com.kpluswebup.web.vo.CmsCategoryVO;
import com.kpluswebup.web.vo.CmsContentVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/content")
public class NewsController extends BaseController {

	@Autowired
	private NewsService newsService;
	@Autowired
	private CmsCategoryService cmsCategoryService;

	@RequestMapping("newsList")
	public ModelAndView newsList(CmsContentDTO cmsContentDTO) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/content/news_list");
		List<CmsContentVO> list = newsService
				.findNewsByPagination(cmsContentDTO);
		modelAndView.addObject("newsList", list);
		modelAndView.addObject("cmsContentDTO", cmsContentDTO);
		return modelAndView;
	}

	@RequestMapping("/addNewsPage")
	public ModelAndView addNewsPage() {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/content/news_add");
		List<CmsCategoryVO> list=cmsCategoryService.findCmsCategoryByType(1);
		modelAndView.addObject("cmsCategoryList", list);
		return modelAndView;
	}

	@RequestMapping("addNews")
	public ModelAndView addNews(String name,String categoryID, String author, String publishTime,String sourceUrl,String picUrl,String keyword,String content,Integer sortOrder,String subTitle) {
		CmsContentDTO cmsContentDTO = new CmsContentDTO();
		if(StringUtil.isNotEmpty(categoryID)){
			cmsContentDTO.setCategoryID(categoryID);
		}
		if (StringUtil.isNotEmpty(name)) {
			cmsContentDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(author)) {
			cmsContentDTO.setAuthor(author);
		}
		if (publishTime != null) {
			try {
				cmsContentDTO.setPublishTime(DateUtil.strintToDatetimeYMD(publishTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtil.isNotEmpty(sourceUrl)) {
			cmsContentDTO.setSourceUrl(sourceUrl);
		}
		if (StringUtil.isNotEmpty(picUrl)) {
			cmsContentDTO.setPicUrl(picUrl);
		}
		if (StringUtil.isNotEmpty(keyword)) {
			cmsContentDTO.setKeyword(keyword);
		}
		if (StringUtil.isNotEmpty(content)) {
			cmsContentDTO.setContent(content);
		}
		if (StringUtil.isNotEmpty(subTitle)) {
		    cmsContentDTO.setSubTitle(subTitle);
		}
		if (sortOrder!=null) {
			cmsContentDTO.setSortOrder(sortOrder);
		}
		cmsContentDTO.setMainID(UUIDUtil.getUUID());
		cmsContentDTO.setCreator(getCurrentOperator());
		newsService.addNews(cmsContentDTO);
		return new ModelAndView("redirect:newsList.htm");
	}

	@RequestMapping("/editNewsPage")
	public ModelAndView editNewsPage(String mainID) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/content/news_edit");
		CmsContentVO cmsContentVO = newsService.findNewsByMainID(mainID);
		modelAndView.addObject("cmsContentVO", cmsContentVO);
		modelAndView.addObject("mainID", mainID);
		List<CmsCategoryVO> list=cmsCategoryService.findCmsCategoryByType(1);
		modelAndView.addObject("cmsCategoryList", list);
		return modelAndView;
	}

	@RequestMapping("editNews")
	public ModelAndView editNews(String mainID,String categoryID,String name, String author, String publishTime,String sourceUrl,String picUrl,String keyword,String content,Integer sortOrder,String subTitle) {
		CmsContentDTO cmsContentDTO = new CmsContentDTO();
		if(StringUtil.isNotEmpty(categoryID)){
			cmsContentDTO.setCategoryID(categoryID);
		}
		if (StringUtil.isNotEmpty(name)) {
			cmsContentDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(author)) {
			cmsContentDTO.setAuthor(author);
		}
		if (publishTime != null) {
			try {
				cmsContentDTO.setPublishTime(DateUtil.strintToDatetimeYMD(publishTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtil.isNotEmpty(sourceUrl)) {
			cmsContentDTO.setSourceUrl(sourceUrl);
		}
		if (StringUtil.isNotEmpty(picUrl)) {
			cmsContentDTO.setPicUrl(picUrl);
		}
		if (StringUtil.isNotEmpty(keyword)) {
			cmsContentDTO.setKeyword(keyword);
		}
		if (StringUtil.isNotEmpty(content)) {
			cmsContentDTO.setContent(content);
		}
		if (StringUtil.isNotEmpty(subTitle)) {
            cmsContentDTO.setSubTitle(subTitle);
        }
		if (sortOrder!=null) {
			cmsContentDTO.setSortOrder(sortOrder);
		}
		cmsContentDTO.setMainID(mainID);
		cmsContentDTO.setModifier(getCurrentOperator());
		newsService.editNews(cmsContentDTO);
		return new ModelAndView("redirect:newsList.htm");
	}

	@RequestMapping("deleteNews")
	public @ResponseBody
	JsonResult deleteNews(String mainID) {
		Boolean isSuccess = newsService
				.deleteNewsByMainID(mainID);
		if (isSuccess) {
			return JsonResult.create();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

}
