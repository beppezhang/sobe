package com.kpluswebup.mall.web.product.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.service.VehicleTypeService;
import com.kpluswebup.web.vo.VehicleTypeVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/mall/vehicle")
public class VehicleTypeControl extends BaseController {

	@Autowired
	private VehicleTypeService vehicleTypeService;
	
    @RequestMapping("get.do")
	public @ResponseBody JsonResult get(String productCategoryId,String pageNo,String serchName)
	{
    	VehicleTypeVO vehicleTypeVO = new VehicleTypeVO();
//    	vehicleTypeVO.setProductCategoryId("2d733f6e-8a38-4d6e-845d-a0d905b16d58");
//    	vehicleTypeVO.setPageNo(1L);
//    	vehicleTypeVO.setPageSize(5L);
//    	vehicleTypeVO.setName("sdf");
    	if (StringUtil.isNumberic(pageNo)) {
    		vehicleTypeVO.setPageNo(Long.parseLong(pageNo));
        }
//    	 if (serchName.indexOf("'") >= 0) { 
//    		 String st = serchName.replaceAll("'", "\\\\'");
//    		 System.out.println(st);
//    	 }
    	if (StringUtil.isNotEmpty(serchName)) {
    		vehicleTypeVO.setName(serchName.replaceAll("'", "\\\\'").replaceAll("\"", "\\\\\""));
    	}
    	if (StringUtil.isNotEmpty(productCategoryId)) {
    		vehicleTypeVO.setProductCategoryId(productCategoryId);
    	}    	
    	//vehicleTypeVO.setPageSize(1l);
    	List<VehicleTypeVO> vehicleTypes = vehicleTypeService.findByPagination(vehicleTypeVO);
    	
    	//VehicleTypeVO vehicleType = vehicleTypeService.findByVin("8800123456");
//    	ModelAndView modelAndView = this.newModelAndView();
//		modelAndView.addObject("vehicleTypes", vehicleTypes);
//		modelAndView.addObject("vehicleTypeVO", vehicleTypeVO);
		Map data = new HashMap();
		data.put("vehicleTypes", vehicleTypes);
		data.put("vehicleTypeVO", vehicleTypeVO);
		return new JsonResult(data);
	}	
}
