package bz.sunlight.web.mobile.part.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.web.CommonControl;
import bz.sunlight.web.service.PartCategoryService;

@Controller
@RequestMapping("/v1/partCategories")
public class PartCategoryControl extends CommonControl {

	@Autowired
	private PartCategoryService partCategoryService;

	@RequestMapping("{id}")
	public @ResponseBody void getPartCategory(@PathVariable String id,
			@RequestParam("vehicleModelId") String vehicleModelId) {
		try {
			String json = partCategoryService.findPartCategroyInfo(id,
					vehicleModelId);
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

}
