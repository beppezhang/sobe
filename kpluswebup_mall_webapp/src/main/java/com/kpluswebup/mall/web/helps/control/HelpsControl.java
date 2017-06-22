package com.kpluswebup.mall.web.helps.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.content.service.HelpService;
import com.kpluswebup.web.domain.CmsCategoryDTO;
import com.kpluswebup.web.domain.CmsHelpCenterDTO;
import com.kpluswebup.web.vo.CmsCategoryVO;
import com.kpluswebup.web.vo.CmsHelpCenterVO;
import com.kpuswebup.comom.util.StringUtil;


@Controller
@RequestMapping("/mall/helps")
public class HelpsControl extends BaseController {
	
	@Autowired
	private HelpService helpService;
	
	@RequestMapping("helpsCenter")
	public ModelAndView helpsCenter(){
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/helps/helps");		
		CmsHelpCenterDTO cmsHelpCenterDTO=new CmsHelpCenterDTO();
		cmsHelpCenterDTO.setPageSize(8l);
		List<CmsHelpCenterVO> list=helpService.findHelpByPagination(cmsHelpCenterDTO);
		modelAndView.addObject("helpList", list);
		cmsHelpCenterDTO.setPageSize(5l);
        List<CmsHelpCenterVO> pList=helpService.findHelpByPagination(cmsHelpCenterDTO);
        modelAndView.addObject("pList", pList);
		return modelAndView;
		
	}
	
	@RequestMapping("helpsDetail")
	public ModelAndView helpsDetail(String mainID){
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/helps/helps_detail");
		CmsHelpCenterVO helpsdetail = helpService.findHelpByMainID(mainID);
		
		  if (StringUtil.isNotEmpty(helpsdetail.getContent())&& helpsdetail.getContent() != "") {
			  
		      helpsdetail.setContent(helpsdetail.getContent().replaceAll("<img src=\"/", "<img src=\"" + imgurl + "/"));
          }
		modelAndView.addObject("helpsdetail", helpsdetail);		
		CmsHelpCenterDTO cmsHelpCenterDTO=new CmsHelpCenterDTO();
		cmsHelpCenterDTO.setPageSize(5l);
        List<CmsHelpCenterVO> pList=helpService.findHelpByPagination(cmsHelpCenterDTO);
        modelAndView.addObject("pList", pList);
		return modelAndView;
	}
	
	@RequestMapping("helpList")
	public ModelAndView helpList(String name,String pageNo,String pageSize){
		ModelAndView modleAndView = this.newModelAndView();
		modleAndView.setViewName("screen/helps/helps_list");
		CmsHelpCenterDTO cmsHelpCenterDTO = new CmsHelpCenterDTO();
		if(StringUtil.isNotEmpty(pageNo)){
			cmsHelpCenterDTO.setPageNo(Long.parseLong(pageNo));
		}
		if(StringUtil.isNotEmpty(pageSize)){
			cmsHelpCenterDTO.setPageSize(Long.parseLong(pageSize));
		}
		List<CmsHelpCenterVO> helpList = helpService.findHelpByPagination(cmsHelpCenterDTO);
		modleAndView.addObject("helpList", helpList);		
		if(StringUtil.isNotEmpty(name)){
			cmsHelpCenterDTO.setName(name);
		}				
        cmsHelpCenterDTO.setPageSize(10l);
        List<CmsHelpCenterVO> pList=helpService.findHelpByPagination(cmsHelpCenterDTO);
        modleAndView.addObject("pList", pList);
        modleAndView.addObject("searchname", name);
        modleAndView.addObject("cmsHelpCenterDTO", cmsHelpCenterDTO);
		return modleAndView;
		
	}

}
