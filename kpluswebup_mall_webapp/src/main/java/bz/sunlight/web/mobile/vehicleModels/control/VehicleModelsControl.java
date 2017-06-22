package bz.sunlight.web.mobile.vehicleModels.control;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;

import com.kpluswebup.web.vo.VehicleTypeVO;

import bz.sunlight.web.CommonControl;
import bz.sunlight.web.service.PartCategoryService;
import bz.sunlight.web.service.VehicleModelsService;
import bz.sunlight.web.service.VehicleBrandsService;
import bz.sunlight.web.service.VehicleSeriesService;

@Controller
@RequestMapping("/v1/vehicleModels")
public class VehicleModelsControl extends CommonControl {

	@Autowired
	private VehicleModelsService vehicleModelsService;

	@Autowired
	private PartCategoryService partCategoryService;

	/**
	 * @param: id, 
	 * @function:查询指定车型, 
	 * @author:zsl
	 */
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public @ResponseBody void getVehicleModelsByMainId(@PathVariable String id) {

		try {
			String json = vehicleModelsService
					.findVehicleModelsByMainId(id);
			if (json != null) {
				write(json);
			} else {
				write_404("该车型不存在");
			}

		} catch (IOException e) {
			e.printStackTrace();
			write_500(null);
		}

	}

	/**
	 * @param: None, 
	 * @function: 查询最近浏览, 
	 * @author: zsl
	 */
	@RequestMapping("lastVisited")
	public @ResponseBody void lastVisited() {
		try {
			write(vehicleModelsService.findLastVisited());
		} catch (IOException e) {
			e.printStackTrace();
			write_500(null);
		}

	}

	@RequestMapping("{id}/partCategories")
	public @ResponseBody void getFirstLevelPartCategoryByVehicleModelId(
			@PathVariable String id) {
		try {
			String json = partCategoryService.findPartCategoriesByVehicleModel(id);
			if(json==null)
			{
				write_404("该备件分类不存在");
			}else
			{
				write(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
			write_500(null);
		}

	}

}
