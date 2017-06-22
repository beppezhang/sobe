package bz.sunlight.web.mobile.brand.control;

import java.io.IOException;
import java.io.WriteAbortedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;

import com.kpluswebup.web.vo.VehicleTypeVO;

import bz.sunlight.web.CommonControl;
import bz.sunlight.web.service.VehicleBrandsService;
import bz.sunlight.web.service.VehicleSeriesService;

//用于测试业务代码的controller
@Controller
@RequestMapping("/v1/vehicleBrands")
public class VehicleBrandsControl extends CommonControl {

	@Autowired
	private VehicleBrandsService vehicleBrandsService;

	/**
	 * param: None, 查询热门汽车品牌, author:zsl
	 */
	@RequestMapping("hot")
	public @ResponseBody void hotBrands() {

		try {
			write(vehicleBrandsService.findHotBrands());
		} catch (IOException e) {
			e.printStackTrace();
			write_500(null);
		}

	}

	/**
	 * param: None, 查询所有汽车品牌, author:zsl
	 */

	@RequestMapping()
	public @ResponseBody void allBrands() {

		try {
			write(vehicleBrandsService.findAllBrands());
		} catch (IOException e) {
			e.printStackTrace();
			write_500(null);
		}

	}

	/**
	 * param id: 汽车品牌mainID, 查询指定汽车品牌, author:zsl
	 */
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public @ResponseBody void sparePartInfo(@PathVariable String id) {

		try {
			String json = vehicleBrandsService.findBrandById(id);
			if (json != null) {
				write(json);
			} else {
				write_404("该汽车品牌不存在");
			}
		} catch (IOException e) {
			e.printStackTrace();
			write_500(null);
		}

	}

}
