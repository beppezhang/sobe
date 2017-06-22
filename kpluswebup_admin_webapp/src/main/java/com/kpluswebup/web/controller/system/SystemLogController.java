package com.kpluswebup.web.controller.system;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kpluswebup.web.admin.system.service.SystemLogService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.SystemLogDTO;
import com.kpluswebup.web.vo.SystemLogVO;

@Controller
@RequestMapping("/admin/system")
public class SystemLogController extends BaseController {

	@Autowired
	private SystemLogService systemLogService;

	@RequestMapping("systemLogList")
	public ModelAndView systemLogList(SystemLogDTO systemLogDTO) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("/screen/system/systemlog_list");
		List<SystemLogVO> list = systemLogService
				.findSystemLogByPagination(systemLogDTO);
		modelAndView.addObject("systemLogList", list);
		modelAndView.addObject("systemLogDTO", systemLogDTO);
		return modelAndView;
	}
}
