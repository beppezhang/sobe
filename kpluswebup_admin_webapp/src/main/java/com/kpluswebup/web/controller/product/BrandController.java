package com.kpluswebup.web.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.BrandDTO;
import com.kpluswebup.web.service.BrandService;
import com.kpluswebup.web.vo.BrandVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/product")
public class BrandController extends BaseController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("/brandlist")
    public ModelAndView findBrandList(String pageNo, String pageSize) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/brand_list");
        BrandDTO brandDTO = new BrandDTO();
        if (StringUtil.isNotEmpty(pageNo)) {
            brandDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(pageSize)) {
            brandDTO.setPageSize(Long.parseLong(pageSize));
        }
        brandDTO.setOrderByClause("order by  createTime desc");
        List<BrandVO> list = brandService.findBrandList(brandDTO);
        modelAndView.addObject("list", list);
        modelAndView.addObject("brandDTO", brandDTO);
        return modelAndView;
    }

    /**
     * @date 2014年11月24日
     * @author zhuhp
     * @param name
     * @param pageNo
     * @param pageSize
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("serachBrand")
    public @ResponseBody
    JsonResult serachBrand(String name, String pageNo, String pageSize) {
        BrandDTO brandDTO = new BrandDTO();
        if (StringUtil.isNotEmpty(pageNo)) {
            brandDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(pageSize)) {
            brandDTO.setPageSize(Long.parseLong(pageSize));
        }
        brandDTO.setName(name);
        brandDTO.setOrderByClause("order by  modifytime desc");
        List<BrandVO> list = brandService.findBrandList(brandDTO);
        JsonResult jsonResult = new JsonResult(ResultCode.NORMAL);
        jsonResult.setResult(list);
        return jsonResult;
    }

    @RequestMapping("/addBrandPage")
    public ModelAndView addBrandPage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/brand_add");
        return modelAndView;
    }

    @RequestMapping("/editBrandPage")
    public ModelAndView editBrandPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/brand_edit");
        BrandVO brandVO = brandService.findBrandByMainID(mainID);
        modelAndView.addObject("brandVO", brandVO);
        modelAndView.addObject("mainID", mainID);
        return modelAndView;
    }

    @RequestMapping("deleteBrand")
    public @ResponseBody
    JsonResult deleteItemtPropVale(String mainID) {
        Boolean isSuccess = brandService.deleteBrandByMainID(mainID);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("addBrand")
    public ModelAndView addBrand(String name, String picURL, String domain, String description, String title,
                                 String metaKeywords, String metaDescription,String sortOrder) {
        BrandDTO brandDTO = new BrandDTO();
        if (StringUtil.isNotEmpty(domain)) {
            brandDTO.setDomain(domain);
        }
        if (StringUtil.isNotEmpty(title)) {
            brandDTO.setTitle(title);
        }
        if (StringUtil.isNotEmpty(name)) {
            brandDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(picURL)) {
            brandDTO.setPicURL(picURL);
        }
        if (StringUtil.isNotEmpty(metaKeywords)) {
            brandDTO.setMetaKeywords(metaKeywords);
        }
        if (StringUtil.isNotEmpty(metaDescription)) {
            brandDTO.setMetaDescription(metaDescription);
        }
        if (StringUtil.isNotEmpty(description)) {
            brandDTO.setDescription(description);
        }
        if(StringUtil.isNotEmpty(sortOrder)){
            brandDTO.setSortOrder(Integer.valueOf(sortOrder));
        }
        brandDTO.setCreator(getCurrentOperator());
        brandService.addBrand(brandDTO);
        return new ModelAndView("redirect:brandlist.htm");
    }

    @RequestMapping("editBrand")
    public ModelAndView editBrand(String mainID, String name, String picURL, String domain, String description,
                                  String title, String metaKeywords, String metaDescription,String sortOrder) {
        BrandDTO brandDTO = new BrandDTO();
        if (StringUtil.isNotEmpty(domain)) {
            brandDTO.setDomain(domain);
        }
        if (StringUtil.isNotEmpty(title)) {
            brandDTO.setTitle(title);
        }
        if (StringUtil.isNotEmpty(name)) {
            brandDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(picURL)) {
            brandDTO.setPicURL(picURL);
        }
        if (StringUtil.isNotEmpty(metaKeywords)) {
            brandDTO.setMetaKeywords(metaKeywords);
        }
        if (StringUtil.isNotEmpty(metaDescription)) {
            brandDTO.setMetaDescription(metaDescription);
        }
        if (StringUtil.isNotEmpty(description)) {
            brandDTO.setDescription(description);
        }
        if(StringUtil.isNotEmpty(sortOrder)){
            brandDTO.setSortOrder(Integer.valueOf(sortOrder));
        }
        brandDTO.setMainID(mainID);
        brandDTO.setModifier(getCurrentOperator());
        brandService.editBrand(brandDTO);
        return new ModelAndView("redirect:brandlist.htm");
    }
}
