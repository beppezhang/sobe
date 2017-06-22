package com.kpluswebup.web.controller.promotion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.GroupSaleDTO;
import com.kpluswebup.web.member.service.CustomerGradeSerivce;
import com.kpluswebup.web.member.service.CustomerGroupSerivce;
import com.kpluswebup.web.service.GroupSaleService;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.PromotionService;
import com.kpluswebup.web.vo.CustomerGradeVO;
import com.kpluswebup.web.vo.CustomerGroupVO;
import com.kpluswebup.web.vo.GroupSaleVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;

@Controller
@RequestMapping("/admin/promotion")
public class GroupSaleController extends BaseController {

	@Autowired
	private GroupSaleService groupSaleService;
	@Autowired
	private CustomerGradeSerivce customerGradeSerivce;
	@Autowired
	private CustomerGroupSerivce customerGroupSerivce;
	@Autowired
	private PromotionService promotionService;
	@Autowired
	private ItemService itemService;

	/**
	 * 团购列表
	 * 
	 * @date 2014年11月13日
	 * @author lupeng
	 * @param groupSaleDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("groupSaleList.htm")
	public ModelAndView groupSaleList(GroupSaleDTO groupSaleDTO) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/promotion/groupsale_list");
		List<GroupSaleVO> groupSaleList = groupSaleService
				.findGroupSaleList(groupSaleDTO);
		modelAndView.addObject("groupSaleList", groupSaleList);
		modelAndView.addObject("groupSaleDTO", groupSaleDTO);
		return modelAndView;
	}

	/**
	 * 添加团购
	 * 
	 * @date 2014年11月13日
	 * @author lupeng
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("addGroupSale.htm")
	public ModelAndView addGroupSale() {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/promotion/groupsale_add");
		addGradesAndGroups(modelAndView);
		return modelAndView;
	}

	private void addGradesAndGroups(ModelAndView modelAndView) {
		List<CustomerGradeVO> customerGradeList = customerGradeSerivce
				.findAllCustomerGrade();
		List<CustomerGroupVO> customerGroupList = customerGroupSerivce
				.findAllCustomerGroup();
		modelAndView.addObject("customerGradeList", customerGradeList);
		modelAndView.addObject("customerGroupList", customerGroupList);
	}

	/**
	 * 保存添加的团购
	 * 
	 * @date 2014年11月14日
	 * @author lupeng
	 * @param groupSale
	 * @param gradeIds
	 * @param groupIds
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("saveAddGroupSale")
	public ModelAndView saveAddGroupSale(GroupSaleVO groupSale,
			String gradeIds, String groupIds) {
		groupSaleService.addGroupSale(groupSale, gradeIds, groupIds,
				getCurrentOperator());
		return new ModelAndView("redirect:groupSaleList.htm");
	}

	/**
	 * 删除团购
	 * 
	 * @date 2014年11月14日
	 * @author lupeng
	 * @param mainId
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("deleteGroupSale")
	public @ResponseBody
	JsonResult deleteGroupSale(String mainIds) {
		Boolean isSuccess = groupSaleService.deleteGroupSale(mainIds);
		if (isSuccess) {
			return JsonResult.create();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	/**
	 * 编辑团购
	 * 
	 * @date 2014年11月14日
	 * @author lupeng
	 * @param mainId
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("editGroupSale.htm")
	public ModelAndView editGroupSale(String mainId) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/promotion/groupsale_edit");
		GroupSaleVO groupSaleVO = groupSaleService.findGroupSale(mainId);
		modelAndView.addObject("groupSale", groupSaleVO);
		ItemVO itemVO = itemService.findItemByMainID(groupSaleVO.getItemID());
		modelAndView.addObject("itemVO", itemVO);
		addGradesAndGroups(modelAndView);
		return modelAndView;
	}

	/**
	 * 保存编辑后的团购
	 * 
	 * @date 2014年11月14日
	 * @author lupeng
	 * @param groupSale
	 * @param gradeIds
	 * @param groupIds
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("saveEditGroupSale")
	public ModelAndView saveEditGroupSale(GroupSaleVO groupSale,
			String gradeIds, String groupIds) {
		groupSaleService.updateGroupSale(groupSale, gradeIds, groupIds,
				getCurrentOperator());
		return new ModelAndView("redirect:groupSaleList.htm");
	}

}
