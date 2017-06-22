package com.kpluswebup.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.TransConfigService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.TransConfigDTO;
import com.kpluswebup.web.vo.TransConfigVO;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/system")
public class TransConfigController extends BaseController {

    @Autowired
    private TransConfigService transConfigService;

    @RequestMapping("transconfigList")
    public ModelAndView transconfigList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("screen/system/transconfig_list");
        TransConfigVO transConfigVO = transConfigService.findTransConfig();
        modelAndView.addObject("transConfigVO", transConfigVO);
        return modelAndView;
    }

    @RequestMapping("addTransConfig")
    public ModelAndView addTransConfig(String decimalPlace, String productPicURL, Integer orderShowDay,
                                       Integer orderCancelDay, Integer orderCloseDay, Integer deliveryProcessActive,
                                       Integer ipLimitedActive, Integer balancePaymentActive, String balancePaymentURL,
                                       Integer codActive, String codURL, String scorePrice, String isRegister) {
        TransConfigDTO transConfigDTO = new TransConfigDTO();
        TransConfigVO transConfigVO = transConfigService.findTransConfig();
        if (transConfigVO == null) {
            transConfigDTO.setDecimalPlace(decimalPlace);
            transConfigDTO.setProductPicURL(productPicURL);
            transConfigDTO.setOrderShowDay(orderShowDay);
            transConfigDTO.setOrderCancelDay(orderCancelDay);
            transConfigDTO.setOrderCloseDay(orderCloseDay);
            transConfigDTO.setDeliveryProcessActive(deliveryProcessActive);
            transConfigDTO.setIpLimitedActive(ipLimitedActive);
            transConfigDTO.setBalancePaymentActive(balancePaymentActive);
            transConfigDTO.setBalancePaymentURL(balancePaymentURL);
            transConfigDTO.setCodActive(codActive);
            transConfigDTO.setCodURL(codURL);
            transConfigDTO.setScorePrice(Double.valueOf(scorePrice));
            transConfigDTO.setIsRegister(Integer.valueOf(isRegister));
            transConfigDTO.setMainID(UUIDUtil.getUUID());
            transConfigDTO.setCreator(getCurrentOperator());
            transConfigService.addTransConfig(transConfigDTO);
        } else {
            transConfigDTO.setDecimalPlace(decimalPlace);
            transConfigDTO.setProductPicURL(productPicURL);
            transConfigDTO.setOrderShowDay(orderShowDay);
            transConfigDTO.setOrderCancelDay(orderCancelDay);
            transConfigDTO.setOrderCloseDay(orderCloseDay);
            transConfigDTO.setDeliveryProcessActive(deliveryProcessActive);
            transConfigDTO.setIpLimitedActive(ipLimitedActive);
            transConfigDTO.setBalancePaymentActive(balancePaymentActive);
            transConfigDTO.setBalancePaymentURL(balancePaymentURL);
            transConfigDTO.setCodActive(codActive);
            transConfigDTO.setCodURL(codURL);
            transConfigDTO.setScorePrice(Double.valueOf(scorePrice));
            transConfigDTO.setIsRegister(Integer.valueOf(isRegister));
            transConfigDTO.setMainID(transConfigVO.getMainID());
            transConfigDTO.setModifier(getCurrentOperator());
            transConfigService.updateTransConfig(transConfigDTO);
        }
        return new ModelAndView("redirect:transconfigList.htm");
    }

}
