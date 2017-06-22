package com.kpluswebup.web.controller.promotion;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.ItemScoreDTO;
import com.kpluswebup.web.domain.ItemScoreDetailDTO;
import com.kpluswebup.web.service.ItemScoreService;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.vo.ItemScoreDetailVO;
import com.kpluswebup.web.vo.ItemScoreVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/promotion")
public class ItemScoreController extends BaseController {

    @Autowired
    private ItemService      itemService;
    @Autowired
    private ItemScoreService itemScoreService;

    @RequestMapping("itemScoreList")
    public ModelAndView itemScoreList(ItemScoreDTO itemScoreDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/itemscore_list");
        List<ItemScoreVO> list = itemScoreService.findItemScoreByPagination(itemScoreDTO);
        modelAndView.addObject("itemScoreList", list);
        modelAndView.addObject("itemScoreDTO", itemScoreDTO);
        return modelAndView;
    }

    @RequestMapping("addItemScorePage")
    public ModelAndView addItemScorePage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/itemscore_add");
        return modelAndView;
    }

    @RequestMapping("addItemScore")
    public ModelAndView addItemScore(String itemID, String score, String num, String endTime) {
        ItemScoreDTO itemScoreDTO = new ItemScoreDTO();
        if (StringUtil.isNotEmpty(itemID)) {
            itemScoreDTO.setItemID(itemID);
        }
        if (StringUtil.isNotEmpty(score)) {
            itemScoreDTO.setScore(Integer.valueOf(score));
        }
        if (StringUtil.isNotEmpty(num)) {
            itemScoreDTO.setNum(Integer.valueOf(num));
        }
        if (StringUtil.isNotEmpty(endTime)) {
            try {
                itemScoreDTO.setEndTime(DateUtil.strintToDatetimeYMDHMS(endTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        itemScoreDTO.setMainID(UUIDUtil.getUUID());
        itemScoreDTO.setCreator(getCurrentOperator());
        itemScoreService.addItemScore(itemScoreDTO);
        return new ModelAndView("redirect:itemScoreList.htm");
    }

    @RequestMapping("deleteItemScore")
    public @ResponseBody
    JsonResult deleteItemScore(String mainIds) {
        Boolean isSuccess = itemScoreService.deleteItemScore(mainIds);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("editItemScorePage")
    public ModelAndView editItemScorePage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/itemscore_edit");
        ItemScoreVO itemScoreVO = itemScoreService.findItemScoreByMainID(mainID);
        modelAndView.addObject("itemScoreVO", itemScoreVO);
        ItemVO itemVO = itemService.findItemByMainID(itemScoreVO.getItemID());
        modelAndView.addObject("itemVO", itemVO);
        return modelAndView;
    }

    @RequestMapping("editItemScore")
    public ModelAndView editItemScore(String mainID, String itemID, String score, String num, String endTime) {
        ItemScoreDTO itemScoreDTO = new ItemScoreDTO();
        if (StringUtil.isNotEmpty(itemID)) {
            itemScoreDTO.setItemID(itemID);
        }
        if (StringUtil.isNotEmpty(score)) {
            itemScoreDTO.setScore(Integer.valueOf(score));
        }
        if (StringUtil.isNotEmpty(num)) {
            itemScoreDTO.setNum(Integer.valueOf(num));
        }
        if (StringUtil.isNotEmpty(endTime)) {
            try {
                itemScoreDTO.setEndTime(DateUtil.strintToDatetimeYMDHMS(endTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        itemScoreDTO.setMainID(mainID);
        itemScoreDTO.setModifier(getCurrentOperator());
        itemScoreService.updateItemScore(itemScoreDTO);
        return new ModelAndView("redirect:itemScoreList.htm");
    }

    @RequestMapping("itemScoreDetailList")
    public ModelAndView itemScoreDetailList(ItemScoreDetailDTO itemScoreDetailDTO,String itemScoreID,String detailID) {
    	ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/itemscore_detail_list");
        itemScoreDetailDTO.setItemScoreID(itemScoreID);
        itemScoreDetailDTO.setMainID(detailID);
        List<ItemScoreDetailVO> list = itemScoreService.findItemScoreDetailByPatination(itemScoreDetailDTO);
        modelAndView.addObject("itemScoreDetailList", list);
        modelAndView.addObject("itemScoreDetailDTO", itemScoreDetailDTO);
        modelAndView.addObject("itemScoreID", itemScoreID);
        return modelAndView;
    }
    
    @RequestMapping("exportItemScoreDetail")
    public void exportItemScoreDetail(HttpServletResponse response,String itemScoreID) {
        try {
            itemScoreService.exportItemScoreDetail(response,itemScoreID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
