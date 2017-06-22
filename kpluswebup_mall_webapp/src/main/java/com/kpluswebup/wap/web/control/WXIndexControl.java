package com.kpluswebup.wap.web.control;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.content.service.AdvertService;
import com.kpluswebup.web.content.service.NewsService;
import com.kpluswebup.web.domain.CmsAdvertDTO;
import com.kpluswebup.web.domain.CmsContentDTO;
import com.kpluswebup.web.member.service.ShoppingCartSerivce;
import com.kpluswebup.web.service.FlashSaleService;
import com.kpluswebup.web.service.PreSaleService;
import com.kpluswebup.web.service.ProductCategoryService;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.vo.CmsAdvertVO;
import com.kpluswebup.web.vo.CmsContentVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.ShoppingCartVO;

@Controller
@RequestMapping("/weixin")
public class WXIndexControl extends BaseController {
    
    @Autowired
    ProductCategoryService productCategoryService;
    
    @Autowired
    private PreSaleService         preSaleService;
    
    @Autowired
    private FlashSaleService       flashSaleService;
    
    @Autowired
    private ProductService         productService;
    
    @Autowired
    private AdvertService          advertService;
    @Autowired
    private NewsService newsService;
    
    @Autowired
    private ShoppingCartSerivce         shoppingCartSerivce;
    
    /**
     * 进入首页
     * @date 2015年1月9日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("index")
    public ModelAndView index(){
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/index");
        //首页轮播广告
        CmsAdvertDTO cmsAdvertDTO = new CmsAdvertDTO();
        cmsAdvertDTO.setPosionID("weixin_index");
        cmsAdvertDTO.setSerachDate(new Date());
        List<CmsAdvertVO> cmsAdvertList = advertService.findAdvertByPagination(cmsAdvertDTO);
        modelAndView.addObject("indexAdvert", cmsAdvertList);
        
        CmsAdvertDTO advertDTO = new CmsAdvertDTO();
        advertDTO.setPosionID("weixin_index_category");
        cmsAdvertDTO.setSerachDate(new Date());
        List<CmsAdvertVO> catAdvertList = advertService.findAdvertByPagination(advertDTO);
        modelAndView.addObject("indexCategoryAdvert", catAdvertList);
        
        List<ProductCategoryVO> productCategoryList = productCategoryService.findProductCategoryOneLevel();
        modelAndView.addObject("productCategoryRecommendList", productCategoryList);
        
        CustomerVO customer = this.findWXUserInfo();
        if(customer!=null){
        	List<ShoppingCartVO> list = shoppingCartSerivce.findShoppingCart(customer.getMainID());
        	customer.setShoppingcartCount(list.size());
        	modelAndView.addObject("user", customer);
        }
        return modelAndView;
    }
    
    @RequestMapping("allClass")
    public ModelAndView allClass(){
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/all_class");
        //产品类目
        List<ProductCategoryVO> productCategoryList = productCategoryService.findProductOneLevel();
        modelAndView.addObject("productCategoryList", productCategoryList);
        return modelAndView;
    }
    
    /**
     * 赛诺资讯
     * @date 2015年5月26日
     * @author wanghehua
     * @param pageNo
     * @param pageSize
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("news")
    public ModelAndView news(){
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/news");
        CmsContentDTO cmsContentDTO = new CmsContentDTO();
        cmsContentDTO.setContentType(1);
        List<CmsContentVO> newsList = newsService.findNewsByPagination(cmsContentDTO);
        modelAndView.addObject("newsList", newsList);
        return modelAndView;
    }
    
    /*@RequestMapping("newsDetail")
    public ModelAndView newsDetail(String mainID){
    	ModelAndView modelAndView = this.newModelAndView();
    	modelAndView.setViewName("wap/screen/newsdetail");
    	CmsContentDTO cmsContentDTO = new CmsContentDTO();
    	cmsContentDTO.setMainID(mainID);
    	CmsContentVO cmsContentVO =newsService.findNewsByMainID(mainID);
    	modelAndView.addObject("cmsContentVO", cmsContentVO);
    	return modelAndView;
    }*/
    
    @RequestMapping("newsDetail")
    public ModelAndView newsDetail(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/newsdetail");
        CmsContentVO cmsContentVO = newsService.findNewsByMainID(mainID);
        modelAndView.addObject("cmsContentVO", cmsContentVO);
        CmsContentVO prev = newsService.findNewsPrev(cmsContentVO.getId());
        CmsContentVO next = newsService.findNewsNext(cmsContentVO.getId());
        modelAndView.addObject("prev", prev);
        modelAndView.addObject("next", next);
        return modelAndView;
    }
    
}
