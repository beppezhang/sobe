package com.kpluswebup.web.controller.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.service.VehicleTypeService;
import com.kpluswebup.web.vo.VehicleTypeVO;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/product")
public class VehicleTypeController extends BaseController {

	@Autowired
	private VehicleTypeService vehicleTypeService;

	/**
     * 整车车型
     * 
     * @date 2015年11月23日
     * @author lby
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("/vehicleTypeList")
    public ModelAndView getProductTypeList(String pageNo, String pageSize) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/vehicleType_list");
    	
        VehicleTypeVO vehicleTypeVO = new VehicleTypeVO();
    	if (StringUtil.isNumberic(pageNo)) {
    		vehicleTypeVO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(pageSize)) {
        	vehicleTypeVO.setPageSize(Long.parseLong(pageSize));
        }
        vehicleTypeVO.setOrderByClause("order by  modifytime desc");
    	List<VehicleTypeVO> list = vehicleTypeService.findByPagination(vehicleTypeVO);

        modelAndView.addObject("list", list);
        modelAndView.addObject("vehicleTypeVO", vehicleTypeVO);
        return modelAndView;
    }
}
