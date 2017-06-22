package com.kpluswebup.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.GeneralService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.GeneralDTO;
import com.kpluswebup.web.vo.GeneralVO;

@Controller
@RequestMapping("/admin/system")
public class GeneralController extends BaseController{

	@Autowired
	private GeneralService generalService;
	
	@RequestMapping("generalList")
	public ModelAndView generalList(){
		//ModelAndView modelAndView=new ModelAndView();
		ModelAndView modelAndView= this.newModelAndView();
		modelAndView.setViewName("screen/system/general_list");
		GeneralVO generalVO=generalService.findGeneral();
		modelAndView.addObject("generalVO", generalVO);
		return modelAndView;
	}
	
	@RequestMapping("addGeneral")
	public ModelAndView addGeneral(String name,String picUrl,String domain,String pageFoot){
		GeneralDTO generalDTO=new GeneralDTO();
		GeneralVO generalVO=generalService.findGeneral();
		if(generalVO==null){
			generalDTO.setName(name);
			generalDTO.setPicUrl(picUrl);
			generalDTO.setDomain(domain);
			generalDTO.setPageFoot(pageFoot);
			generalDTO.setCreator(getCurrentOperator());
			generalService.addGeneral(generalDTO);
		}else{
			generalDTO.setName(name);
			generalDTO.setPicUrl(picUrl);
			generalDTO.setDomain(domain);
			generalDTO.setPageFoot(pageFoot);
			generalDTO.setId(generalVO.getId());
			generalDTO.setModifier(getCurrentOperator());
			generalService.updateGeneral(generalDTO);
		}
		return new ModelAndView("redirect:generalList.htm");
	}
	
	@RequestMapping("seoList")
	public ModelAndView seoList(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("screen/system/seo_list");
		GeneralVO generalVO=generalService.findGeneral();
		modelAndView.addObject("generalVO", generalVO);
		return modelAndView;
	}
	
	@RequestMapping("addSeo")
	public ModelAndView addSeo(String title,String metaKeywords,String metaDescription){
		GeneralDTO generalDTO=new GeneralDTO();
		GeneralVO generalVO=generalService.findGeneral();
		if(generalVO==null){
			generalDTO.setTitle(title);
			generalDTO.setMetaKeywords(metaKeywords);
			generalDTO.setMetaDescription(metaDescription);
			generalDTO.setCreator(getCurrentOperator());
			generalService.addGeneral(generalDTO);
		}else{
			generalDTO.setTitle(title);
			generalDTO.setMetaKeywords(metaKeywords);
			generalDTO.setMetaDescription(metaDescription);
			generalDTO.setId(generalVO.getId());
			generalDTO.setModifier(getCurrentOperator());
			generalService.updateGeneral(generalDTO);
		}
		return new ModelAndView("redirect:seoList.htm");
	}
	
	@RequestMapping("useragreementList")
	public ModelAndView useragreementList(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("screen/system/useragreement_list");
		GeneralVO generalVO=generalService.findGeneral();
		modelAndView.addObject("generalVO", generalVO);
		return modelAndView;
	}
	
	@RequestMapping("addUserAgreement")
	public ModelAndView addUserAgreement(String userAgreement){
		GeneralDTO generalDTO=new GeneralDTO();
		GeneralVO generalVO=generalService.findGeneral();
		if(generalVO==null){
			generalDTO.setUserAgreement(userAgreement);
			generalDTO.setCreator(getCurrentOperator());
			generalService.addGeneral(generalDTO);
		}else{
			generalDTO.setUserAgreement(userAgreement);
			generalDTO.setId(generalVO.getId());
			generalDTO.setModifier(getCurrentOperator());
			generalService.updateGeneral(generalDTO);
		}
		return new ModelAndView("redirect:useragreementList.htm");
	}
	
	@RequestMapping("serviceList")
	public ModelAndView serviceList(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("screen/system/service_list");
		GeneralVO generalVO=generalService.findGeneral();
		modelAndView.addObject("generalVO", generalVO);
		return modelAndView;
	}
	
	@RequestMapping("addService")
	public ModelAndView addService(String serviceConfig){
		GeneralDTO generalDTO=new GeneralDTO();
		GeneralVO generalVO=generalService.findGeneral();
		if(generalVO==null){
			generalDTO.setServiceConfig(serviceConfig);
			generalDTO.setCreator(getCurrentOperator());
			generalService.addGeneral(generalDTO);
		}else{
			generalDTO.setServiceConfig(serviceConfig);
			generalDTO.setId(generalVO.getId());
			generalDTO.setModifier(getCurrentOperator());
			generalService.updateGeneral(generalDTO);
		}
		return new ModelAndView("redirect:serviceList.htm");
	}
	
}
