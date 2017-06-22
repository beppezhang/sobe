package com.kpluswebup.web.controller.content;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.content.service.AdvertService;
import com.kpluswebup.web.content.service.CmsCategoryService;
import com.kpluswebup.web.domain.CmsAdvertClickDTO;
import com.kpluswebup.web.domain.CmsAdvertDTO;
import com.kpluswebup.web.domain.CmsCategoryDTO;
import com.kpluswebup.web.service.BrandService;
import com.kpluswebup.web.service.ProductCategoryService;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.service.ProductTypeService;
import com.kpluswebup.web.vo.BrandVO;
import com.kpluswebup.web.vo.CmsAdvertLinkVO;
import com.kpluswebup.web.vo.CmsAdvertClickVO;
import com.kpluswebup.web.vo.CmsAdvertVO;
import com.kpluswebup.web.vo.CmsCategoryVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.ProductTypeVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.SystemAdvertPosionDTO;
import com.kpluswebup.web.vo.SystemAdvertPosionVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/content")
public class AdvertController extends BaseController {

    @Autowired
    private AdvertService          advertService;
    @Autowired
    private CmsCategoryService     cmsCategoryService;
    @Autowired
    private BrandService           brandService;
    @Autowired
    private ProductTypeService     productTypeService;
    @Autowired
    private ProductService         productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("advertList")
    public ModelAndView advertList(CmsAdvertDTO cmsAdvertDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/content/advert_list");
        List<CmsAdvertVO> list = advertService.findAdvertByPagination(cmsAdvertDTO);
        modelAndView.addObject("advertList", list);
        modelAndView.addObject("cmsAdvertDTO", cmsAdvertDTO);
        return modelAndView;
    }

    @RequestMapping("/addAdvertPage")
    public ModelAndView addAdvertPage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/content/advert_add");
        List<CmsCategoryVO> list = cmsCategoryService.findCmsCategoryByType(3);
        modelAndView.addObject("cmsCategoryList", list);
        List<SystemAdvertPosionVO> advertPosionList = cmsCategoryService.findAllSystemAdvertPosion();
        modelAndView.addObject("advertPosionList", advertPosionList);
        List<ProductCategoryVO> pList = productCategoryService.findProductCatOneLevel();
        modelAndView.addObject("pList", pList);
        List<CmsAdvertVO> advertList = advertService.findAdvertIndex();
        modelAndView.addObject("parentList", advertList);
        return modelAndView;
    }
    /**
     *  @author zhoulei 
     * 保存位置
     * @return
     */
    @RequestMapping("/addAdvertLocationCategory")
    public ModelAndView addAdvertLocationCategory(String name, String description,String mainID) {
    	SystemAdvertPosionDTO systemAdvertPosionDTO = new SystemAdvertPosionDTO();
        if (StringUtil.isNotEmpty(name)) {
        	systemAdvertPosionDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(description)) {
        	systemAdvertPosionDTO.setDescription(description);
        }
        systemAdvertPosionDTO.setMainID(mainID);
        systemAdvertPosionDTO.setCreator(getCurrentOperator());
        cmsCategoryService.addSystemAdvertPosion(systemAdvertPosionDTO);
    	 return new ModelAndView("redirect:advertList.htm");
    }
    /**
     *  @author zhoulei 
     * 修改位置
     * @return
     */
    @RequestMapping("/editAdvertLocationCategory")
    public ModelAndView editAdvertLocationCategory(String name, String description,String mainID) {
    	 ModelAndView modelAndView = this.newModelAndView();
    	  modelAndView.setViewName("screen/content/advert_add");
    	  SystemAdvertPosionDTO systemAdvertPosionDTO = new SystemAdvertPosionDTO();
        if (StringUtil.isNotEmpty(name)) {
        	systemAdvertPosionDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(description)) {
        	systemAdvertPosionDTO.setDescription(description);
        }
        systemAdvertPosionDTO.setMainID(mainID);
        systemAdvertPosionDTO.setCreator(getCurrentOperator());
        cmsCategoryService.updateSystemAdvertPosion(systemAdvertPosionDTO);
    	 return modelAndView;
    }
    /**
     * @author zhoulei 
     * @TODO  新增广告位置
     * @param mainID
     * @return
     */
    @RequestMapping("/addAdvertLocationPage")
    public ModelAndView addAdvertLocationPage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/content/advert_location_add");
        return modelAndView;
    }
    

    @RequestMapping("addAdvert")
    public ModelAndView addAdvert(String name, String categoryID, String posionID, String picUrl, String activeTime,
                                  String endTime, String linkType, String advertLink, String linkMainID,
                                  String description, Integer sortOrder, String productCategoryID, String advertParentID) {
        CmsAdvertDTO cmsAdvertDTO = new CmsAdvertDTO();
        if (StringUtil.isNotEmpty(name)) {
            cmsAdvertDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(categoryID)) {
            cmsAdvertDTO.setCategoryID(categoryID);
        }
        if (StringUtil.isNotEmpty(posionID)) {
            cmsAdvertDTO.setPosionID(posionID);
        }
        if (StringUtil.isNotEmpty(activeTime)) {
            try {
                cmsAdvertDTO.setActiveTime(DateUtil.strintToDatetimeYMD(activeTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(endTime)) {
            try {
                cmsAdvertDTO.setEndTime(DateUtil.strintToDatetimeYMD(endTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(linkType)) {
            cmsAdvertDTO.setLinkType(Integer.valueOf(linkType));
        }
        if (linkType.equals("1")) {
            if (StringUtil.isNotEmpty(advertLink)) {
                cmsAdvertDTO.setAdvertLink(advertLink);
            }
        } else {
            if (StringUtil.isNotEmpty(linkMainID)) {
                cmsAdvertDTO.setAdvertLink(linkMainID);
            }
        }
        if (StringUtil.isNotEmpty(picUrl)) {
            cmsAdvertDTO.setPicURL(picUrl);
        }
        if (StringUtil.isNotEmpty(description)) {
            cmsAdvertDTO.setDescription(description);
        }
        if (sortOrder != null) {
            cmsAdvertDTO.setSortOrder(sortOrder);
        }
        if (posionID.equals("index_category")) {
            if (StringUtil.isNotEmpty(productCategoryID)) {
                cmsAdvertDTO.setProductCategoryID(productCategoryID);
            }
        }
        if (posionID.equals("index_right")) {
            if (StringUtil.isNotEmpty(advertParentID)) {
                cmsAdvertDTO.setParentID(advertParentID);
            }
        }
        cmsAdvertDTO.setMainID(UUIDUtil.getUUID());
        cmsAdvertDTO.setCreator(getCurrentOperator());
        advertService.addAdvert(cmsAdvertDTO);
        return new ModelAndView("redirect:advertList.htm");
    }

    @RequestMapping("/editAdvertPage")
    public ModelAndView editAdvertPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/content/advert_edit");
        CmsAdvertVO cmsAdvertVO = advertService.findAdvertByMainID(mainID);
        String linkID = cmsAdvertVO.getAdvertLink();
        if (cmsAdvertVO.getLinkType() == 2) {
            ProductTypeVO productTypeVO = productTypeService.findProductTypeByMainID(linkID);
            if (productTypeVO != null) {
                cmsAdvertVO.setLinkName(productTypeVO.getName());
            }
        } else if (cmsAdvertVO.getLinkType() == 3) {
            BrandVO brandVO = brandService.findBrandByMainID(linkID);
            if (brandVO != null) {
                cmsAdvertVO.setLinkName(brandVO.getName());
            }
        } else if (cmsAdvertVO.getLinkType() == 4) {
            ProductVO productVO = productService.findProductByMainID(linkID);
            if (productVO != null) {
                cmsAdvertVO.setLinkName(productVO.getName());
            }
        } else if (cmsAdvertVO.getLinkType() == 5) {
            ItemVO itemVO = advertService.findItemByMainID(linkID);
            if (itemVO != null) {
                cmsAdvertVO.setLinkName(itemVO.getName());
            }
        }
        modelAndView.addObject("cmsAdvertVO", cmsAdvertVO);
        modelAndView.addObject("mainID", mainID);
        List<CmsCategoryVO> list = cmsCategoryService.findCmsCategoryByType(3);
        modelAndView.addObject("cmsCategoryList", list);
        List<SystemAdvertPosionVO> advertPosionList = cmsCategoryService.findAllSystemAdvertPosion();
        modelAndView.addObject("advertPosionList", advertPosionList);
        List<ProductCategoryVO> pList = productCategoryService.findProductCatOneLevel();
        modelAndView.addObject("pList", pList);
        List<CmsAdvertVO> advertList = advertService.findAdvertIndex();
        modelAndView.addObject("parentList", advertList);
        return modelAndView;
    }
/**
 * @author zhoulei 
 * @TODO  编辑广告位置
 * @param mainID
 * @return
 */
    @RequestMapping("/editAdvertLocationPage")
    public ModelAndView editAdvertLocationPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/content/advert_edit");
        CmsAdvertVO cmsAdvertVO = advertService.findAdvertByMainID(mainID);
        String linkID = cmsAdvertVO.getAdvertLink();
        if (cmsAdvertVO.getLinkType() == 2) {
            ProductTypeVO productTypeVO = productTypeService.findProductTypeByMainID(linkID);
            if (productTypeVO != null) {
                cmsAdvertVO.setLinkName(productTypeVO.getName());
            }
        } else if (cmsAdvertVO.getLinkType() == 3) {
            BrandVO brandVO = brandService.findBrandByMainID(linkID);
            if (brandVO != null) {
                cmsAdvertVO.setLinkName(brandVO.getName());
            }
        } else if (cmsAdvertVO.getLinkType() == 4) {
            ProductVO productVO = productService.findProductByMainID(linkID);
            if (productVO != null) {
                cmsAdvertVO.setLinkName(productVO.getName());
            }
        } else if (cmsAdvertVO.getLinkType() == 5) {
            ItemVO itemVO = advertService.findItemByMainID(linkID);
            if (itemVO != null) {
                cmsAdvertVO.setLinkName(itemVO.getName());
            }
        }
        modelAndView.addObject("cmsAdvertVO", cmsAdvertVO);
        modelAndView.addObject("mainID", mainID);
        List<CmsCategoryVO> list = cmsCategoryService.findCmsCategoryByType(3);
        modelAndView.addObject("cmsCategoryList", list);
        List<SystemAdvertPosionVO> advertPosionList = cmsCategoryService.findAllSystemAdvertPosion();
        modelAndView.addObject("advertPosionList", advertPosionList);
        List<ProductCategoryVO> pList = productCategoryService.findProductCatOneLevel();
        modelAndView.addObject("pList", pList);
        List<CmsAdvertVO> advertList = advertService.findAdvertIndex();
        modelAndView.addObject("parentList", advertList);
        return modelAndView;
    }
    
    @RequestMapping("editAdvert")
    public ModelAndView editAdvert(String mainID, String categoryID, String posionID, String name, String picUrl,
                                   String activeTime, String endTime, String linkType, String advertLink,
                                   String linkMainID, String description, Integer sortOrder, String productCategoryID,
                                   String advertParentID) {
        CmsAdvertDTO cmsAdvertDTO = new CmsAdvertDTO();
        if (StringUtil.isNotEmpty(name)) {
            cmsAdvertDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(categoryID)) {
            cmsAdvertDTO.setCategoryID(categoryID);
        }
        if (StringUtil.isNotEmpty(posionID)) {
            cmsAdvertDTO.setPosionID(posionID);
        }
        if (StringUtil.isNotEmpty(activeTime)) {
            try {
                cmsAdvertDTO.setActiveTime(DateUtil.strintToDatetimeYMD(activeTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(endTime)) {
            try {
                cmsAdvertDTO.setEndTime(DateUtil.strintToDatetimeYMD(endTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtil.isNotEmpty(linkType)) {
            cmsAdvertDTO.setLinkType(Integer.valueOf(linkType));
        }
        if (linkType.equals("1")) {
            if (StringUtil.isNotEmpty(advertLink)) {
                cmsAdvertDTO.setAdvertLink(advertLink);
            }
        } else {
            if (StringUtil.isNotEmpty(linkMainID)) {
                cmsAdvertDTO.setAdvertLink(linkMainID);
            }
        }
        if (StringUtil.isNotEmpty(picUrl)) {
            cmsAdvertDTO.setPicURL(picUrl);
        }
        if (StringUtil.isNotEmpty(description)) {
            cmsAdvertDTO.setDescription(description);
        }
        if (sortOrder != null) {
            cmsAdvertDTO.setSortOrder(sortOrder);
        }
        if (posionID.equals("index_category")) {
            if (StringUtil.isNotEmpty(productCategoryID)) {
                cmsAdvertDTO.setProductCategoryID(productCategoryID);
            }
        }
        if (posionID.equals("index_right")) {
            if (StringUtil.isNotEmpty(advertParentID)) {
                cmsAdvertDTO.setParentID(advertParentID);
            }
        }
        cmsAdvertDTO.setMainID(mainID);
        cmsAdvertDTO.setModifier(getCurrentOperator());
        advertService.editAdvert(cmsAdvertDTO);
        return new ModelAndView("redirect:advertList.htm");
    }

    @RequestMapping("deleteAdvert")
    public @ResponseBody
    JsonResult deleteAdvert(String mainID) {
        Boolean isSuccess = advertService.deleteAdvertByMainID(mainID);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("advertclickList")
    public ModelAndView advertclickList(CmsAdvertClickDTO cmsAdvertClickDTO, String advertID) {
        cmsAdvertClickDTO.setAdvertID(advertID);
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/content/advertclick_list");
        List<CmsAdvertClickVO> list = advertService.findAdvertClickByPagination(cmsAdvertClickDTO);
        modelAndView.addObject("advertclickList", list);
        modelAndView.addObject("cmsAdvertClickDTO", cmsAdvertClickDTO);
        return modelAndView;
    }

    @RequestMapping("getAdvertLinkByName")
    public @ResponseBody
    JsonResult getAdvertLinkByName(String name, Integer type) {
        try {
            List<CmsAdvertLinkVO> list = advertService.findAdvertLinkByName(name, type);
            if (list != null && list.size() > 0) {
                return new JsonResult(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
        return JsonResult.create();
    }

    @RequestMapping("exportAdvertClick")
    public void exportAdvertClick(HttpServletResponse response, String advertID) {
        try {
            advertService.exportAdvertClick(response, advertID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
