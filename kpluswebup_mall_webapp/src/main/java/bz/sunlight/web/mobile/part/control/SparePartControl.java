package bz.sunlight.web.mobile.part.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.web.CommonControl;
import bz.sunlight.web.service.SparePartService;

@Controller
@RequestMapping("/v1/spareParts")
public class SparePartControl extends CommonControl {

	@Autowired
	private SparePartService sparePartService;

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public @ResponseBody void getSparePartByID(@PathVariable String id) {

		try {
			String json = sparePartService.findSparePartByMainID(id);
			if (json == null) {
				write_404("该备件不存在");
			} else {
				write(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
			write_500(null);
		}

	}

	@RequestMapping("{id}/applicableVehicleModel")
	public @ResponseBody void getApplicableVehicleModel(
			@PathVariable String id, @RequestParam("pageIndex") int pageIndex,
			@RequestParam("pageSize") int pageSize) {
		try {
			write(sparePartService.findApplicableVehicleModel(id, pageIndex,
					pageSize));
		} catch (Exception e) {
			e.printStackTrace();
			write_500(null);
		}

	}

	@RequestMapping("lastVisited")
	public @ResponseBody void getLastVisited() {
		try {
			write(sparePartService.findLastVisited());
			// List<Map<String,String>> lastVisitedParts =
			// GsonUtil.fromJson(json,
			// new TypeToken<List<Map<String,String>>>(){});
			// for (Map<String,String> map : lastVisitedParts){
			// System.out.println(map.toString());
			// }
		} catch (Exception e) {
			e.printStackTrace();
			write_500(null);
		}

	}

//	@RequestMapping("spareParttest")
//	public @ResponseBody String sparePartTest() {
//
//		String st = "{\"id\": 123, \"name\" : \"张三\", \"age\": 17}";
//		try {
//			return st;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}

}
