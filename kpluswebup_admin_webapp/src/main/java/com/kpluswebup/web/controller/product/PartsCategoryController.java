package com.kpluswebup.web.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.service.PartsCategoryService;
import com.kpluswebup.web.vo.PartsCategoryVo;

@Controller
@RequestMapping("/admin/product")
public class PartsCategoryController extends BaseController {

	@Autowired
	private PartsCategoryService partsCategoryService;

    /**
     * 配件类目
     * 
     * @date 2014年11月23日
     * @author lby
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/partsCategoryList")
    public ModelAndView findProductCategoryList() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/partsCategory_list");
    	List<PartsCategoryVo> list = partsCategoryService.findPartsCategoryLevel();
        modelAndView.addObject("list", list);
        return modelAndView;
    }

}
