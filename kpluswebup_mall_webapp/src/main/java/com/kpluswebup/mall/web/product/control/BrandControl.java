package com.kpluswebup.mall.web.product.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.service.ProductCategoryService;
import com.kpluswebup.web.service.impl.BrandModel;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.VehicleTypeVO;
import com.kpuswebup.comom.util.JsonUtil;
import com.kpuswebup.comom.util.StringUtil;


@Controller
@RequestMapping("/mall/brand")
public class BrandControl extends BaseController {
    @Autowired
    private ProductCategoryService productCategoryService;
	/*this is trunk sxc*/
    @RequestMapping("brand/get")
	public ModelAndView getBrand(String pageNo,String serchName)
	{
    	System.out.println(23);
		List<BrandModel> brands = productCategoryService.findProductOneLevelTparts();
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.addObject("brandsModel", brands);
		modelAndView.setViewName("screen/product/brand_n");
		return modelAndView;
	}

    //lby
    @RequestMapping("/wall")
	public ModelAndView brandWall(String pageNo,String serchName)
	{
    	System.out.println(23);
		List<BrandModel> brands = productCategoryService.findProductOneLevelTparts();
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.addObject("brandsModel", brands);
		modelAndView.setViewName("screen/product/brandWall");
		return modelAndView;
	}
    
    //lby
    @RequestMapping("/wall2")
	public ModelAndView brandWall2(String pageNo,String serchName)
	{
    	List<ProductVO> products = new ArrayList<ProductVO>();
    	ProductVO productVo = new ProductVO();
    	productVo.setName("sxc");
    	products.add(productVo);
    	productVo = new ProductVO();
    	productVo.setName("lej");
    	products.add(productVo);
    	String st = JsonUtil.fromArrayObject(products).toString();
    	System.out.println(st);
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.addObject("parts", st);
		modelAndView.setViewName("screen/product/search_parts");
		return modelAndView;
	}    
}
