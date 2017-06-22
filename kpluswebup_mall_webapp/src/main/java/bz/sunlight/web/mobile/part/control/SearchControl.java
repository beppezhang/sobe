package bz.sunlight.web.mobile.part.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bz.sunlight.web.CommonControl;
import bz.sunlight.web.service.SearchService;

@Controller
@RequestMapping("/v1/search")
public class SearchControl extends CommonControl {

	@Autowired
	SearchService searchService;
	
	@RequestMapping("")
	public @ResponseBody void getPartCategory(
			@RequestParam("type") String type,
			@RequestParam("str") String str,
			@RequestParam("pageIndex") int pageIndex,
			@RequestParam("pageSize") int pageSize ){
		
		try {
			write(searchService.findSearchResult(type, str, pageIndex, pageSize));
		} catch (Exception e) {
			e.printStackTrace();
			write_500(null);
		}
		
	}

	@RequestMapping("history")
	public @ResponseBody void getSearchHistory(
			@RequestParam("scenario") String scenario,
			@RequestParam("type") String type ) {

		try {
			String[] accessToken = getAuthInfo(getRequest(), getResponse());	//从token中拿customerID
			String customerId = accessToken[0];
			write(searchService.findSearchHistory(customerId, scenario, type));
		} catch (Exception e) {
			e.printStackTrace();
			write_500(null);
		}

	}

	@RequestMapping(method = RequestMethod.POST,value = "history/clear")
	public @ResponseBody void deleteSearchHistory(
			@RequestParam("scenario") String scenario,
			@RequestParam("type") String type ) {

		try {
			write("");
		} catch (Exception e) {
			e.printStackTrace();
			write_500(null);
		}

	}

}
