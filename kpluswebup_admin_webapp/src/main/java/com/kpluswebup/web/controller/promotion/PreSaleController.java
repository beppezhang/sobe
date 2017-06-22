package com.kpluswebup.web.controller.promotion;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.PreSaleDTO;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.PreSaleService;
import com.kpluswebup.web.service.PromotionService;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.PreSaleVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/promotion")
public class PreSaleController extends BaseController {

    @Autowired
    private PreSaleService   preSaleService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private ItemService      itemService;

    /**
     * 商品预售列表
     * 
     * @date 2014年11月12日
     * @author lupeng
     * @param preSaleDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("preSaleList.htm")
    public ModelAndView preSaleList(PreSaleDTO preSaleDTO, String searchItemID, String searchItemName,
                                    String searchFromDate, String searchEndDate) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/presale_list");
        if (StringUtil.isNotEmpty(searchItemID)) {
            preSaleDTO.setItemID(searchItemID);
        }
        if (StringUtil.isNotEmpty(searchItemName)) {
            preSaleDTO.setItemName(searchItemName);
        }
        if (StringUtil.isNotEmpty(searchFromDate)) {
            try {
                preSaleDTO.setFromDate(DateUtil.strintToDatetimeYMDHMS(searchFromDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(searchEndDate)) {
            try {
                preSaleDTO.setEndDate(DateUtil.strintToDatetimeYMDHMS(searchEndDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<PreSaleVO> preSaleList = preSaleService.findPreSaleList(preSaleDTO);
        modelAndView.addObject("preSaleList", preSaleList);
        modelAndView.addObject("preSaleDTO", preSaleDTO);
        return modelAndView;
    }

    /**
     * 新增商品预售
     * 
     * @date 2014年11月12日
     * @author lupeng
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("addPreSale.htm")
    public ModelAndView addPreSale() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/presale_add");
        modelAndView.addObject("catalog", 2);
        return modelAndView;
    }

    /**
     * 保存添加的预售商品
     * 
     * @date 2014年11月13日
     * @author lupeng
     * @param preSale
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("saveAddPreSale")
    public ModelAndView saveAddPreSale(PreSaleVO preSale) {
        preSale.setCreator(getCurrentOperator());
        preSaleService.addPreSale(preSale);
        return new ModelAndView("redirect:preSaleList.htm");
    }

    /**
     * 删除预售商品
     * 
     * @date 2014年11月13日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("deletePreSale")
    public @ResponseBody
    JsonResult deletePreSale(String mainIds) {
        Boolean isSuccess = preSaleService.deletePreSale(mainIds);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * 编辑预售商品
     * 
     * @date 2014年11月13日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("editPreSale.htm")
    public ModelAndView editPreSale(String mainId) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/promotion/presale_edit");
        PreSaleVO preSaleVO = preSaleService.findPreSale(mainId);
        modelAndView.addObject("preSale", preSaleVO);
        ItemVO itemVO = itemService.findItemByMainID(preSaleVO.getItemID());
        modelAndView.addObject("itemVO", itemVO);
        modelAndView.addObject("catalog", 2);
        return modelAndView;
    }

    /**
     * 更新预售商品
     * 
     * @date 2014年11月13日
     * @author lupeng
     * @param preSale
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("updatePreSale")
    public ModelAndView updatePreSale(PreSaleVO preSale) {
        preSale.setModifier(getCurrentOperator());
        preSaleService.updatePreSale(preSale);
        return new ModelAndView("redirect:preSaleList.htm");
    }

}
