package com.kpluswebup.mall.web.supplier.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.admin.system.service.ExpressFormatItemService;
import com.kpluswebup.web.admin.system.service.ExpressFormatService;
import com.kpluswebup.web.admin.system.service.ExpressService;
import com.kpluswebup.web.admin.system.service.FreightTemplateService;
import com.kpluswebup.web.domain.ExpressDTO;
import com.kpluswebup.web.domain.FreightTemplateDTO;
import com.kpluswebup.web.vo.ExpressFormatItemVO;
import com.kpluswebup.web.vo.ExpressFormatVO;
import com.kpluswebup.web.vo.ExpressVO;
import com.kpluswebup.web.vo.FreightTemplateVO;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/mall/seller")
public class ExpressController extends BaseController {

	@Autowired
	private ExpressService expressService;

	@Autowired
	private ExpressFormatService expressFormatService;
	@Autowired
	private ExpressFormatItemService expressFormatItemService;
	
	@Autowired
	private FreightTemplateService freightTemplateService;

	/**
	 * @date 2014年11月20日
	 * @author zhuhp
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("expressList")
	public ModelAndView expressList(String pageNO) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/supplier/express_list");
		SupplierVO supplierVO = findSupplierInfo();
		ExpressDTO expressDTO = new ExpressDTO();
		expressDTO.setPageSize(10L);
		if(StringUtil.isNotEmpty(pageNO)){
			expressDTO.setPageNo(Long.parseLong(pageNO));
		}
		expressDTO.setSupplierID(supplierVO.getMainID());
		List<ExpressVO> list = expressService.findExpressPageBySupplierID(expressDTO);
		modelAndView.addObject("list", list);
		modelAndView.addObject("expressDTO", expressDTO);
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
		modelAndView.setViewName("screen/supplier/express_add");
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

		SupplierVO supplierVO = findSupplierInfo();
		expressService.addExpress(name, contactPerson, mobile, code, isDefault,
				description,getCurrentOperator(), supplierVO);
		return new ModelAndView("redirect:expressList.htm");
	}

	/**
	 * 
	 * @param name
	 * @param contactPerson
	 * @param mobile
	 * @param code
	 * @param isDefault
	 * @param description
	 * @return
	 */
	@RequestMapping("findExpress")
	public ExpressVO findExpress(String expressName, String expressID) {
		SupplierVO supplierVO = findSupplierInfo();
		ExpressDTO expressDTO = new ExpressDTO();
		expressDTO.setName(expressName);
		expressDTO.setSupplierID(supplierVO.getMainID());
		expressDTO.setMainID(expressID);
		ExpressVO expressVO = expressService.findExpressByName(expressDTO);
		return expressVO;
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
		List<FreightTemplateVO> list = freightTemplateService.findFreightTemplateByExpressID(mainID);
		if(list.size() > 0){
			return new JsonResult(ResultCode.ERROR_RESOURCE_RELATED_USE);
		}
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
		modelAndView.setViewName("screen/supplier/express_edit");
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
		modelAndView.setViewName("screen/supplier/expressformat_add");
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
		modelAndView.setViewName("screen/supplier/expressformat_list");
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
		modelAndView.setViewName("screen/supplier/expressformat_edit");
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
		modelAndView.setViewName("/screen/supplier/expressformatitem_edit");
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
