package com.kpluswebup.web.controller.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.content.service.CmsCategoryService;
import com.kpluswebup.web.domain.CmsCategoryDTO;
import com.kpluswebup.web.vo.CmsCategoryVO;
import com.kpluswebup.web.vo.SystemAdvertPosionVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/content")
public class CmsCategoryController extends BaseController {

    @Autowired
    private CmsCategoryService cmsCategoryService;

    @RequestMapping("cmscategoryList")
    public ModelAndView cmscategoryList(CmsCategoryDTO cmsCategoryDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/content/cmscategory_list");
        List<CmsCategoryVO> list = cmsCategoryService.findCmsCategoryByPagination(cmsCategoryDTO);
        modelAndView.addObject("cmscategoryList", list);
        modelAndView.addObject("cmsCategoryDTO", cmsCategoryDTO);
        return modelAndView;
    }

    @RequestMapping("/addCmsCategoryPage")
    public ModelAndView addCmsCategoryPage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/content/cmscategory_add");
        return modelAndView;
    }

    @RequestMapping("addCmsCategory")
    public ModelAndView addCmsCategory(String name, Integer type, Integer sortorder, String description, String posionID) {
        CmsCategoryDTO cmsCategoryDTO = new CmsCategoryDTO();
        if (StringUtil.isNotEmpty(name)) {
            cmsCategoryDTO.setName(name);
        }
        if (type != null) {
            cmsCategoryDTO.setCategoryType(type);
        }
        if (sortorder != null) {
            cmsCategoryDTO.setSortOrder(sortorder);
        }
        if (StringUtil.isNotEmpty(description)) {
            cmsCategoryDTO.setDescription(description);
        }
        if (StringUtil.isEmpty(posionID)) {
            cmsCategoryDTO.setPosionID(posionID);
        }

        cmsCategoryDTO.setMainID(UUIDUtil.getUUID());
        cmsCategoryDTO.setCreator(getCurrentOperator());
        cmsCategoryService.addCmsCategory(cmsCategoryDTO);
        return new ModelAndView("redirect:cmscategoryList.htm");
    }

    @RequestMapping("/editCmsCategoryPage")
    public ModelAndView editCmsCategoryPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/content/cmscategory_edit");
        CmsCategoryVO cmsCategoryVO = cmsCategoryService.findCmsCategoryByMainID(mainID);
        modelAndView.addObject("cmsCategoryVO", cmsCategoryVO);
        modelAndView.addObject("mainID", mainID);
        return modelAndView;
    }

    @RequestMapping("editCmsCategory")
    public ModelAndView editCmsCategory(String mainID, String name, Integer type, Integer sortorder, String description) {
        CmsCategoryDTO cmsCategoryDTO = new CmsCategoryDTO();
        if (StringUtil.isNotEmpty(name)) {
            cmsCategoryDTO.setName(name);
        }
        if (type != null) {
            cmsCategoryDTO.setCategoryType(type);
        }
        if (sortorder != null) {
            cmsCategoryDTO.setSortOrder(sortorder);
        }
        if (StringUtil.isNotEmpty(description)) {
            cmsCategoryDTO.setDescription(description);
        }
        cmsCategoryDTO.setMainID(mainID);
        cmsCategoryDTO.setModifier(getCurrentOperator());
        cmsCategoryService.editCmsCategory(cmsCategoryDTO);
        return new ModelAndView("redirect:cmscategoryList.htm");
    }

    @RequestMapping("deleteCmsCategory")
    public @ResponseBody
    JsonResult deleteCmsCategory(String mainID) {
        Boolean isSuccess = cmsCategoryService.deleteCmsCategoryByMainID(mainID);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

}
