package com.kpluswebup.web.controller.promotion;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.FlashSaleDTO;
import com.kpluswebup.web.member.service.CustomerGradeSerivce;
import com.kpluswebup.web.member.service.CustomerGroupSerivce;
import com.kpluswebup.web.service.FlashSaleService;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.PromotionService;
import com.kpluswebup.web.vo.CustomerGradeVO;
import com.kpluswebup.web.vo.CustomerGroupVO;
import com.kpluswebup.web.vo.FlashSaleVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/promotion")
public class FlashSaleController extends BaseController {

    @Autowired
    private FlashSaleService     flashSaleService;
    @Autowired
    private CustomerGradeSerivce customerGradeSerivce;
    @Autowired
    private CustomerGroupSerivce customerGroupSerivce;
    @Autowired
    private PromotionService     promotionService;
    @Autowired
    private ItemService          itemService;

    @RequestMapping("flashSaleList")
    public ModelAndView flashSaleList(FlashSaleDTO flashSaleDTO, String searchItemID, String searchItemName,
                                      String searchFromDate, String searchEndDate) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/flashsale_list");
        if (StringUtil.isNotEmpty(searchItemID)) {
            flashSaleDTO.setItemID(searchItemID);
        }
        if (StringUtil.isNotEmpty(searchItemName)) {
            flashSaleDTO.setItemName(searchItemName);
        }
        if (StringUtil.isNotEmpty(searchFromDate)) {
            try {
                flashSaleDTO.setFromDate(DateUtil.strintToDatetimeYMDHMS(searchFromDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(searchEndDate)) {
            try {
                flashSaleDTO.setEndDate(DateUtil.strintToDatetimeYMDHMS(searchEndDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        flashSaleDTO.setType(0);
        List<FlashSaleVO> list = flashSaleService.findFlashSaleByPagination(flashSaleDTO);
        modelAndView.addObject("flashSaleList", list);
        modelAndView.addObject("flashSaleDTO", flashSaleDTO);
        return modelAndView;
    }

    @RequestMapping("addFlashSalePage")
    public ModelAndView addFlashSalePage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/flashsale_add");
        addGradesAndGroups(modelAndView);
        return modelAndView;
    }

    private void addGradesAndGroups(ModelAndView modelAndView) {
        List<CustomerGradeVO> customerGradeList = customerGradeSerivce.findAllCustomerGrade();
        List<CustomerGroupVO> customerGroupList = customerGroupSerivce.findAllCustomerGroup();
        modelAndView.addObject("customerGradeList", customerGradeList);
        modelAndView.addObject("customerGroupList", customerGroupList);
    }

    @RequestMapping("addFlashSale")
    public ModelAndView addFlashSale(String productID, String itemID, String salesPrice, String totality,
                                     String limitCount, String customerGrade, String customerGroup, String fromDate,
                                     String endDate, String description, String score, String scorePrice,
                                     String salesScore, String picUrl) {
        FlashSaleDTO flashSaleDTO = new FlashSaleDTO();
        if (StringUtil.isNotEmpty(productID)) {
            flashSaleDTO.setProductID(productID);
        }
        if (StringUtil.isNotEmpty(itemID)) {
            flashSaleDTO.setItemID(itemID);
        }
        if (StringUtil.isNotEmpty(salesPrice)) {
            flashSaleDTO.setSalesPrice(Double.valueOf(salesPrice));
        }
        if (StringUtil.isNotEmpty(totality)) {
            flashSaleDTO.setTotality(Integer.valueOf(totality));
        }
        if (StringUtil.isNotEmpty(limitCount)) {
            flashSaleDTO.setLimitCount(Integer.valueOf(limitCount));
        }
        if (StringUtil.isNotEmpty(customerGrade)) {
            flashSaleDTO.setCustomerGrade(customerGrade);
        }
        if (StringUtil.isNotEmpty(customerGroup)) {
            flashSaleDTO.setCustomerGroup(customerGroup);
        }
        if (StringUtil.isNotEmpty(fromDate)) {
            try {
                flashSaleDTO.setFromDate(DateUtil.strintToDatetimeYMDHMS(fromDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(endDate)) {
            try {
                flashSaleDTO.setEndDate(DateUtil.strintToDatetimeYMDHMS(endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(description)) {
            flashSaleDTO.setDescription(description);
        }
        if (StringUtil.isNotEmpty(score)) {
            flashSaleDTO.setScore(Integer.valueOf(score));
        }
        if (StringUtil.isNotEmpty(scorePrice)) {
            flashSaleDTO.setScorePrice(Double.valueOf(scorePrice));
        }
        if (StringUtil.isNotEmpty(salesScore)) {
            flashSaleDTO.setSalesScore(Integer.valueOf(salesScore));
        }
        if (StringUtil.isNotEmpty(picUrl)) {
            flashSaleDTO.setPicUrl(picUrl);
        }
        flashSaleDTO.setMainID(UUIDUtil.getUUID());
        flashSaleDTO.setCreator(getCurrentOperator());
        flashSaleService.addFlashSale(flashSaleDTO);
        return new ModelAndView("redirect:flashSaleList.htm");
    }

    @RequestMapping("deleteFlashSale")
    public @ResponseBody
    JsonResult deleteFlashSale(String mainIds) {
        Boolean isSuccess = flashSaleService.deleteFlashSale(mainIds);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("editFlashSalePage")
    public ModelAndView editFlashSalePage(String mainId) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/flashsale_edit");
        FlashSaleVO flashSaleVO = flashSaleService.findFlashSaleByMainID(mainId);
        modelAndView.addObject("flashSaleVO", flashSaleVO);
        ItemVO itemVO = itemService.findItemByMainID(flashSaleVO.getItemID());
        modelAndView.addObject("itemVO", itemVO);
        addGradesAndGroups(modelAndView);
        return modelAndView;
    }

    @RequestMapping("editFlashSale")
    public ModelAndView editFlashSale(String mainID, String productID, String itemID, String salesPrice,
                                      String totality, String limitCount, String customerGrade, String customerGroup,
                                      String fromDate, String endDate, String description, String score,
                                      String scorePrice, String salesScore, String picUrl) {
        FlashSaleDTO flashSaleDTO = new FlashSaleDTO();
        if (StringUtil.isNotEmpty(productID)) {
            flashSaleDTO.setProductID(productID);
        }
        if (StringUtil.isNotEmpty(itemID)) {
            flashSaleDTO.setItemID(itemID);
        }
        if (StringUtil.isNotEmpty(salesPrice)) {
            flashSaleDTO.setSalesPrice(Double.valueOf(salesPrice));
        }
        if (StringUtil.isNotEmpty(totality)) {
            flashSaleDTO.setTotality(Integer.valueOf(totality));
        }
        if (StringUtil.isNotEmpty(limitCount)) {
            flashSaleDTO.setLimitCount(Integer.valueOf(limitCount));
        }
        if (StringUtil.isNotEmpty(customerGrade)) {
            flashSaleDTO.setCustomerGrade(customerGrade);
        }
        if (StringUtil.isNotEmpty(customerGroup)) {
            flashSaleDTO.setCustomerGroup(customerGroup);
        }
        if (StringUtil.isNotEmpty(fromDate)) {
            try {
                flashSaleDTO.setFromDate(DateUtil.strintToDatetimeYMDHMS(fromDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(endDate)) {
            try {
                flashSaleDTO.setEndDate(DateUtil.strintToDatetimeYMDHMS(endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(description)) {
            flashSaleDTO.setDescription(description);
        }
        if (StringUtil.isNotEmpty(score)) {
            flashSaleDTO.setScore(Integer.valueOf(score));
        }
        if (StringUtil.isNotEmpty(scorePrice)) {
            flashSaleDTO.setScorePrice(Double.valueOf(scorePrice));
        }
        if (StringUtil.isNotEmpty(salesScore)) {
            flashSaleDTO.setSalesScore(Integer.valueOf(salesScore));
        }
        if (StringUtil.isNotEmpty(picUrl)) {
            flashSaleDTO.setPicUrl(picUrl);
        }
        flashSaleDTO.setMainID(mainID);
        flashSaleDTO.setModifier(getCurrentOperator());
        flashSaleService.updateFlashSale(flashSaleDTO);
        return new ModelAndView("redirect:flashSaleList.htm");
    }

}
