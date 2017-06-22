package com.kpluswebup.web.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.ItemReviewDTO;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.ItemReviewVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/member")
public class MemberItemReviewController extends BaseController {

    @Autowired
    private MemberSerivce memberSerivce;

    @RequestMapping("memberreviewList")
    public ModelAndView memberreviewList(ItemReviewDTO itemReviewDTO, String customerID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/memberreview_list");
        itemReviewDTO.setCustomerID(customerID);
        List<ItemReviewVO> list = memberSerivce.findCustomerReview(itemReviewDTO);
        modelAndView.addObject("reviewList", list);
        CustomerVO customerVO = new CustomerVO();
        customerVO.setMainID(customerID);
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

    @RequestMapping("replyMemberReviewPage")
    public ModelAndView replyMemberReviewPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/memberreview_reply");
        ItemReviewVO itemReviewVO = memberSerivce.findReviewByMainID(mainID);
        modelAndView.addObject("itemReviewVO", itemReviewVO);
        CustomerVO customerVO = new CustomerVO();
        customerVO.setMainID(itemReviewVO.getCustomerID());
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

    @RequestMapping("replyMemberReview")
    public ModelAndView replyMemberReview(String mainID, String customerID, String reply) {
        ItemReviewDTO itemReviewDTO = new ItemReviewDTO();
        if (StringUtil.isNotEmpty(reply)) {
            itemReviewDTO.setReply(reply);
        }
        itemReviewDTO.setMainID(mainID);
        itemReviewDTO.setReplier(getCurrentOperator());
        itemReviewDTO.setModifier(getCurrentOperator());
        memberSerivce.replyReview(itemReviewDTO);
        return new ModelAndView("redirect:memberreviewList.do?customerID=" + customerID);
    }

    @RequestMapping("reviewList")
    public ModelAndView itemreviewList(ItemReviewDTO itemReviewDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/review_list");
        List<ItemReviewVO> list = memberSerivce.findCustomerReview(itemReviewDTO);
        modelAndView.addObject("itemreviewList", list);
        return modelAndView;
    }

    @RequestMapping("replyReviewPage")
    public ModelAndView replyReviewPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/review_reply");
        ItemReviewVO itemReviewVO = memberSerivce.findReviewByMainID(mainID);
        modelAndView.addObject("itemReviewVO", itemReviewVO);
        return modelAndView;
    }

    @RequestMapping("replyReview")
    public ModelAndView replyReview(String mainID, String reply) {
        ItemReviewDTO itemReviewDTO = new ItemReviewDTO();
        if (StringUtil.isNotEmpty(reply)) {
            itemReviewDTO.setReply(reply);
        }
        itemReviewDTO.setMainID(mainID);
        itemReviewDTO.setReplier(getCurrentOperator());
        itemReviewDTO.setModifier(getCurrentOperator());
        memberSerivce.replyReview(itemReviewDTO);
        return new ModelAndView("redirect:reviewList.htm");
    }

    @RequestMapping("checkReview")
    public @ResponseBody
    JsonResult checkReview(String mainIds) {
        Boolean isSuccess = memberSerivce.checkReview(mainIds,getCurrentOperator());
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

}
