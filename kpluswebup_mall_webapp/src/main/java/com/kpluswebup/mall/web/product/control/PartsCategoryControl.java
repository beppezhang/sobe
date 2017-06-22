package com.kpluswebup.mall.web.product.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.service.PartsCategoryService;
import com.kpluswebup.web.vo.PartsCategoryVo;
import com.kpluswebup.web.vo.VehicleTypeVO;

@Controller
@RequestMapping("/mall/partscategory")
public class PartsCategoryControl extends BaseController {
	
	@Autowired
	private PartsCategoryService partsCategoryService;
	
    @RequestMapping("get")
	public ModelAndView get(String productCategoryId,String name)
	{
    	List<PartsCategoryVo> partsCategorys = partsCategoryService.findPartsCategoryLevel();
    	
    	List<PartsCategoryVo> partsCategorysChild = partsCategoryService.findPartsCategoryByParentId("6aaaf898-a3bc-43a2-9f67-8396dced0d07");
    	
    	List<PartsCategoryVo> partsCategorysOnly = partsCategoryService.findPartsCategoryLevelOnly();
    	
    	ModelAndView modelAndView = this.newModelAndView();
		modelAndView.addObject("brands", partsCategorys);
		modelAndView.setViewName("screen/product/brand-n");
		return modelAndView;
	}		

    @RequestMapping("root")
	public ModelAndView getRoot(String productCategoryId,String name)
	{
    	List<PartsCategoryVo> partsCategorys = partsCategoryService.findPartsCategoryLevel();
    	
    	List<PartsCategoryVo> partsCategorysChild = partsCategoryService.findPartsCategoryByParentId("6aaaf898-a3bc-43a2-9f67-8396dced0d07");
    	
    	List<PartsCategoryVo> partsCategorysOnly = partsCategoryService.findPartsCategoryLevelOnly();
    	
    	ModelAndView modelAndView = this.newModelAndView();
		modelAndView.addObject("brands", partsCategorys);
		modelAndView.setViewName("screen/product/brand-n");
		return modelAndView;
	}    
    
}
