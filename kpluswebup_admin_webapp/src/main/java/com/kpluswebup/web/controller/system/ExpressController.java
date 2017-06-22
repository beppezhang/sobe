package com.kpluswebup.web.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.ExpressFormatItemService;
import com.kpluswebup.web.admin.system.service.ExpressFormatService;
import com.kpluswebup.web.admin.system.service.ExpressService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.vo.ExpressFormatItemVO;
import com.kpluswebup.web.vo.ExpressFormatVO;
import com.kpluswebup.web.vo.ExpressVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;

@Controller
@RequestMapping("/admin/system")
public class ExpressController extends BaseController {

	@Autowired
	private ExpressService expressService;

	@Autowired
	private ExpressFormatService expressFormatService;
	@Autowired
	private ExpressFormatItemService expressFormatItemService;

	/**
	 * @date 2014年11月20日
	 * @author zhuhp
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("expressList")
	public ModelAndView expressList() {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/system/express_list");
		List<ExpressVO> list = expressService.findALlExpress();
		modelAndView.addObject("list", list);
		return modelAndView;
	}

	/**
	 * @date 2014年11月20日
	 * @author zhuhp
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("addExpressPage")
	public ModelAndView addExpressPage() {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/system/express_add");
		return modelAndView;
	}

	/**
	 * @date 2014年11月20日
	 * @author zhuhp
	 * @param name
	 * @param contactPerson
	 * @param mobile
	 * @param code
	 * @param isDefault
	 * @param description
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("addExpress")
	public ModelAndView addExpress(String name, String contactPerson,
			String mobile, String code, String isDefault, String description) {

		expressService.addExpress(name, contactPerson, mobile, code, isDefault,
				description,getCurrentOperator());
		return new ModelAndView("redirect:expressList.htm");
	}

	/**
	 * @date 2014年11月20日
	 * @author zhuhp
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("/deleteExpress")
	public @ResponseBody
	JsonResult deleteExpress(String mainID) {
		Boolean isSuccess = expressService.deleteExpressByMainID(mainID);
		if (isSuccess) {
			return JsonResult.create();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	/**
	 * @date 2014年11月20日
	 * @author zhuhp
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("/editExpressPage")
	public ModelAndView editExpressPage(String mainID) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/system/express_edit");
		ExpressVO expressVO = expressService.findExpressByMainID(mainID);
		modelAndView.addObject("expressVO", expressVO);
		return modelAndView;
	}

	@RequestMapping("editExpress")
	public ModelAndView editExpress(String mainID, String name,
			String contactPerson, String mobile, String code, String isDefault,
			String description) {

		expressService.editExpress(mainID, name, contactPerson, mobile, code,
				isDefault, description,getCurrentOperator());
		return new ModelAndView("redirect:expressList.htm");
	}

	/**
	 * 物流模板
	 * 
	 * @date 2014年11月22日
	 * @author zhuhp
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("addExpressFormatPage")
	public ModelAndView addExpressFormatPage() {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/system/expressformat_add");
		List<ExpressVO> expresslist = expressService.findALlExpress();
		modelAndView.addObject("expresslist", expresslist);
		return modelAndView;
	}

	/**
	 * @date 2014年11月22日
	 * @author zhuhp
	 * @param name
	 * @param expressID
	 * @param picURL
	 * @param width
	 * @param height
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("addExpressFormat")
	public ModelAndView addExpressFormat(String name, String expressID,
			String isDefault, String picURL, String width, String height) {
		String mainID=expressFormatService.addExpressFormat(name, expressID, picURL, width,
				height, isDefault);
		return new ModelAndView("redirect:editExpressFormatItemPage.htm?formatID=" +mainID);
	}

	/**
	 * @date 2014年11月22日
	 * @author zhuhp
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("expressFormat")
	public ModelAndView findExpressFormat() {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/system/expressformat_list");
		List<ExpressFormatVO> expressFormatList = expressFormatService
				.findALLExpressFormat();
		modelAndView.addObject("expressFormatList", expressFormatList);
		return modelAndView;
	}

	/**
	 * @date 2014年11月22日
	 * @author zhuhp
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("deleteExpressFormat")
	public @ResponseBody
	JsonResult deleteExpressFormat(String mainID) {
		Boolean isSuccess = expressFormatService
				.deleteExpressFormatByMainID(mainID);
		if (isSuccess) {
			return JsonResult.create();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	/**
	 * @date 2014年11月22日
	 * @author zhuhp
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("editExpressFormatPage")
	public ModelAndView editExpressFormatPage(String mainID) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/system/expressformat_edit");
		ExpressFormatVO expressFormatVO = expressFormatService
				.findExpressFormatByMainID(mainID);
		modelAndView.addObject("expressFormatVO", expressFormatVO);
		List<ExpressVO> expresslist = expressService.findALlExpress();
		modelAndView.addObject("expresslist", expresslist);
		return modelAndView;
	}

	/**
	 * @date 2014年11月22日
	 * @author zhuhp
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("editExpressFormat")
	public ModelAndView editExpressFormat(String mainID, String name,
			String expressID, String isDefault, String picURL, String width,
			String height) {
		expressFormatService.editExpressFormat(mainID, name, expressID,
				isDefault, picURL, width, height,getCurrentOperator());
		return new ModelAndView(
				"redirect:editExpressFormatItemPage.htm?formatID=" + mainID);

	}

	@RequestMapping("editExpressFormatItemPage")
	public ModelAndView editExpressFormatItemPage(String formatID) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/system/expressformatitem_edit");
		List<ExpressFormatItemVO> list = expressFormatItemService
				.findExpressFormatItemByFormatID(formatID);
		modelAndView.addObject("expressFormatItemList", list);
		ExpressFormatVO expressFormatVO = expressFormatService
				.findExpressFormatByMainID(formatID);
		modelAndView.addObject("expressFormatVO", expressFormatVO);
		return modelAndView;
	}

	@RequestMapping("editExpressFormatItem")
	public ModelAndView editExpressFormatItem(HttpServletRequest request, String formatID) {
		String[] formatItems = request.getParameterValues("formatItems");
		expressFormatItemService.updateExpressFormatItem(
				formatID, formatItems,getCurrentOperator());
		return new ModelAndView("redirect:expressFormat.htm");
	}

}
