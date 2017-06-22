package com.kpluswebup.web.controller.promotion;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.PromotionDTO;
import com.kpluswebup.web.member.service.CustomerGradeSerivce;
import com.kpluswebup.web.member.service.CustomerGroupSerivce;
import com.kpluswebup.web.service.CouponBatchService;
import com.kpluswebup.web.service.ProductCategoryService;
import com.kpluswebup.web.service.PromotionService;
import com.kpluswebup.web.vo.CmsAdvertLinkVO;
import com.kpluswebup.web.vo.CouponPromotionVO;
import com.kpluswebup.web.vo.CustomerGradeVO;
import com.kpluswebup.web.vo.CustomerGroupVO;
import com.kpluswebup.web.vo.ProductItemVO;
import com.kpluswebup.web.vo.PromotionItemVO;
import com.kpluswebup.web.vo.PromotionSetVO;
import com.kpluswebup.web.vo.PromotionVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/promotion")
public class PromotionController extends BaseController {

    @Autowired
    private PromotionService     promotionService;
    @Autowired
    private CustomerGradeSerivce customerGradeSerivce;
    @Autowired
    private CustomerGroupSerivce customerGroupSerivce;
    @Autowired
    private CouponBatchService   couponBatchService;
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 促销活动列表
     * 
     * @date 2014年11月10日
     * @author lupeng
     * @param promotionDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("promotionList.htm")
    public ModelAndView promotionList(String pageNo, String pageSize, String searchName, String type,
                                      String searchStartTime, String searchEndTime) {
        PromotionDTO promotionDTO = new PromotionDTO();
        if (StringUtils.isNotEmpty(searchName)) {
            promotionDTO.setName(searchName);
        }
        if (StringUtils.isNotEmpty(type)) {
            promotionDTO.setType(Integer.valueOf(type));
        }
        if (StringUtils.isNotEmpty(searchStartTime)) {
            try {
                promotionDTO.setFromDate(DateUtil.strintToDatetimeYMD(searchStartTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotEmpty(searchEndTime)) {
            try {
                promotionDTO.setEndDate(DateUtil.strintToDatetimeYMD(searchEndTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNumberic(pageSize)) {
            promotionDTO.setPageSize(Long.parseLong(pageSize));
        }
        if (StringUtil.isNumberic(pageNo)) {
            promotionDTO.setPageNo(Long.parseLong(pageNo));
        }
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/promotion_list");
        List<PromotionVO> promotionList = promotionService.findPromotionList(promotionDTO);
        List<Integer> typeList = promotionService.findAllPromotionType();
        modelAndView.addObject("promotionList", promotionList);
        modelAndView.addObject("typeList", typeList);
        modelAndView.addObject("promotionDTO", promotionDTO);
        return modelAndView;
    }

    /**
     * 添加促销活动
     * 
     * @date 2014年11月10日
     * @author lupeng
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("addPromotion.htm")
    public ModelAndView addPromotion() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/promotion_add");
        addGradesAndGroups(modelAndView);
        return modelAndView;
    }

    private void addGradesAndGroups(ModelAndView modelAndView) {
        List<CustomerGradeVO> customerGradeList = customerGradeSerivce.findAllCustomerGrade();
        List<CustomerGroupVO> customerGroupList = customerGroupSerivce.findAllCustomerGroup();
        modelAndView.addObject("customerGradeList", customerGradeList);
        modelAndView.addObject("customerGroupList", customerGroupList);
    }

    /**
     * 保存新增的促销活动
     * 
     * @date 2014年11月11日
     * @author lupeng
     * @param promotion
     * @param gradeIds
     * @param groupIds
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("saveAddPromotion")
    public ModelAndView saveAddPromotion(PromotionVO promotion, String gradeIds, String groupIds) {
        String mainId = promotionService.addPromotionSelective(promotion, gradeIds, groupIds, getCurrentOperator());
        return new ModelAndView("redirect:promotionSetList.htm?promotionID=" + mainId);
    }

    /**
     * 编辑一条促销活动
     * 
     * @date 2014年11月11日
     * @author lupeng
     * @param gradeIds
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("editPromotion.htm")
    public ModelAndView editPromotion(String mainId) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/promotion_edit");
        PromotionVO promotionVO = promotionService.findPromotionByMainID(mainId);
        PromotionItemVO promotionItemVO = promotionService.findPromotionItemByPromotionMainID(mainId);
        if (promotionItemVO != null) {
            promotionVO.setProductID(promotionItemVO.getProductID());
            promotionVO.setItemID(promotionItemVO.getItemID());
        }
        List<CouponPromotionVO> couponBatchList = couponBatchService.findAllCouponBatch();
        modelAndView.addObject("couponBatchList", couponBatchList);
        modelAndView.addObject("promotion", promotionVO);
        List<ProductItemVO> list = promotionService.findAllProductItem(null);
        modelAndView.addObject("productList", list);
        if (list != null && list.size() > 0) {
            List<ProductItemVO> itemlist = promotionService.findAllProductItem(list.get(0).getMainID());
            if (itemlist != null && itemlist.size() > 0) {
                modelAndView.addObject("itemList", itemlist);
            }
        }
        addGradesAndGroups(modelAndView);
        return modelAndView;
    }

    /**
     * 保存编辑后的促销活动
     * 
     * @date 2014年11月11日
     * @author lupeng
     * @param promotion
     * @param gradeIds
     * @param groupIds
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("saveEditPromotion")
    public ModelAndView saveEditPromotion(PromotionVO promotion, String gradeIds, String groupIds) {
        promotionService.updatePromotionSelective(promotion, gradeIds, groupIds, getCurrentOperator());
        return new ModelAndView("redirect:promotionSetList.htm?promotionID=" + promotion.getMainID());
    }

    /**
     * 删除一条促销活动
     * 
     * @date 2014年11月11日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("deletePromotion")
    public @ResponseBody
    JsonResult deletePromotion(String mainIds) {
        Boolean isSuccess = promotionService.deletePromotionByMainID(mainIds);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("promotionInfo.htm")
    public ModelAndView promotionInfo(String mainId) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/promotion_detail");
        PromotionVO promotionVO = promotionService.promotionInfo(mainId);
        modelAndView.addObject("promotion", promotionVO);
        List<ProductItemVO> list = promotionService.findAllProductItem(null);
        modelAndView.addObject("productList", list);
        if (list != null && list.size() > 0) {
            List<ProductItemVO> itemlist = promotionService.findAllProductItem(list.get(0).getMainID());
            if (itemlist != null && itemlist.size() > 0) {
                modelAndView.addObject("itemList", itemlist);
            }
        }
        addGradesAndGroups(modelAndView);
        return modelAndView;
    }

    @RequestMapping("getProductAll")
    public @ResponseBody
    JsonResult getProductAll() {
        try {
            List<ProductItemVO> list = promotionService.findAllProductItem(null);
            if (list != null && list.size() > 0) {
                return new JsonResult(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }

    @RequestMapping("getItemByProductID")
    public @ResponseBody
    JsonResult getItemByProductID(String productID) {
        try {
            List<ProductItemVO> list = promotionService.findAllProductItem(productID);
            if (list != null && list.size() > 0) {
                return new JsonResult(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }

    /**
     * 查看适用范围
     * 
     * @date 2014年11月22日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("promotionSetList")
    public ModelAndView promotionSetList(String promotionID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/promotion_set_list");
        PromotionVO promotionVO = promotionService.findPromotionByMainID(promotionID);
        modelAndView.addObject("promotionVO", promotionVO);
        List<PromotionSetVO> productTypeList = promotionService.findPromotionSetTypeByPromotionID(promotionID);
        modelAndView.addObject("productTypeList", productTypeList);
        List<PromotionSetVO> productCategoryList = promotionService.findPromotionSetCategoryByPromotionID(promotionID);
        modelAndView.addObject("productCategoryList", productCategoryList);
        List<PromotionSetVO> productlist = promotionService.findPromotionSetProductByPromotionID(promotionID);
        modelAndView.addObject("productList", productlist);
        List<PromotionSetVO> itemList = promotionService.findPromotionSetItemByPromotionID(promotionID);
        modelAndView.addObject("itemList", itemList);
        return modelAndView;
    }

    @RequestMapping("ajaxDeletePromotionSet")
    public @ResponseBody
    JsonResult deletePromotionSet(Long id) {
        Boolean isSuccess = promotionService.deletePromotionSetByID(id);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("promotionItemList")
    public @ResponseBody
    JsonResult promotionItemList(Integer promotionsetType, String promotionsetName) {
        try {
            List<CmsAdvertLinkVO> list = promotionService.findPromotionSetByName(promotionsetType, promotionsetName);
            if (list != null && list.size() > 0) {
                return new JsonResult(list);
            } else {
                return JsonResult.create();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("addPromotionSet")
    public @ResponseBody
    JsonResult addPromotionSet(Integer promotionsetType, String promotionID, String promotionsetIds) {
        try {
            promotionService.addPromotionSet(promotionsetType, promotionID, promotionsetIds, getCurrentOperator());
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * 查看适用范围详情
     * 
     * @date 2014年11月24日
     * @author wanghehua
     * @param promotionID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("promotionSetDetail")
    public ModelAndView promotionSetDetail(String promotionID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/promotion_set_detail");
        PromotionVO promotionVO = promotionService.findPromotionByMainID(promotionID);
        modelAndView.addObject("promotionVO", promotionVO);
        List<PromotionSetVO> productTypeList = promotionService.findPromotionSetTypeByPromotionID(promotionID);
        modelAndView.addObject("productTypeList", productTypeList);
        List<PromotionSetVO> productCategoryList = promotionService.findPromotionSetCategoryByPromotionID(promotionID);
        modelAndView.addObject("productCategoryList", productCategoryList);
        List<PromotionSetVO> productlist = promotionService.findPromotionSetProductByPromotionID(promotionID);
        modelAndView.addObject("productList", productlist);
        List<PromotionSetVO> itemList = promotionService.findPromotionSetItemByPromotionID(promotionID);
        modelAndView.addObject("itemList", itemList);
        return modelAndView;
    }

}
