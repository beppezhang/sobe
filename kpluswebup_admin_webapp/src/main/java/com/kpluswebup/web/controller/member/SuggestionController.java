package com.kpluswebup.web.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.content.service.SuggestionService;
import com.kpluswebup.web.domain.SuggestionDTO;
import com.kpluswebup.web.vo.SuggestionVO;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/member")
public class SuggestionController extends BaseController {

    @Autowired
    private SuggestionService suggestionService;
    
    @RequestMapping("suggestionList")
    public ModelAndView suggestionList(String pageNo, String pageSize) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/membercenter/suggestion_list");
        SuggestionDTO suggestionDTO = new SuggestionDTO();
        if (StringUtil.isNotEmpty(pageNo)) {
            suggestionDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(pageSize)) {
            suggestionDTO.setPageSize(Long.parseLong(pageSize));
        }
        List<SuggestionVO> list =suggestionService.findsuggestionByPagination(suggestionDTO);
        modelAndView.addObject("list", list);
        modelAndView.addObject("suggestionDTO", suggestionDTO);
        return modelAndView;
    }

}
