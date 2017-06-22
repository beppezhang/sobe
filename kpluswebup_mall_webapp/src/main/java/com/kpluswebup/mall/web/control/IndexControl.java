package com.kpluswebup.mall.web.control;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.TransConfigService;
import com.kpluswebup.web.content.service.AdvertService;
import com.kpluswebup.web.content.service.NewsService;
import com.kpluswebup.web.content.service.NoticeService;
import com.kpluswebup.web.domain.CmsAdvertDTO;
import com.kpluswebup.web.domain.CmsContentDTO;
import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.member.service.FocusService;
import com.kpluswebup.web.service.FlashSaleService;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.PreSaleService;
import com.kpluswebup.web.service.ProductCategoryService;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.service.impl.BrandModel;
import com.kpluswebup.web.vo.CmsAdvertVO;
import com.kpluswebup.web.vo.CmsContentVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.FocusEntity;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.DateUtil;

@Controller
@RequestMapping("/")
public class IndexControl extends BaseController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private PreSaleService         preSaleService;

    @Autowired
    private AdvertService          advertService;

    @Autowired
    private NoticeService          noticeService;

    @Autowired
    private NewsService            newsService;

    @Autowired
    private FlashSaleService       flashSaleService;

    @Autowired
    private ProductService         productService;

    @Autowired
    private TransConfigService     transConfigService;
    @Autowired
    private ItemService            itemService;
    @Autowired
    private CachedClient           cachedClient;

    @Autowired
    private FocusService            focusService;

    /**
     * 商城首页
     * 
     * @date 2014年12月22日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    // TODO首页需要进行缓存处理
    @RequestMapping("index-old.htm")
    public ModelAndView index() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/index");
        String today = DateUtil.formatDate(new Date(), "yyyyMMdd");

        // 轮播图
        List<CmsAdvertVO> cmsAdvertList = cachedClient.get(Constant.CMSADVERTLIST + today);
        cachedClient.delete(Constant.CMSADVERTLIST + today);
        if (cmsAdvertList == null || cmsAdvertList.size() == 0) {
            CmsAdvertDTO cmsAdvertDTO = new CmsAdvertDTO();
            cmsAdvertDTO.setPosionID("index");
            cmsAdvertDTO.setSerachDate(new Date());
            cmsAdvertDTO.setPageSize(5l);
            cmsAdvertList = advertService.findAdvertByPagination(cmsAdvertDTO);
            cachedClient.set(Constant.CMSADVERTLIST + today, Constant.EXP, cmsAdvertList);
        }
        modelAndView.addObject("cmsAdvertList", cmsAdvertList);

        // 今日热买
        List<ProductVO> list = cachedClient.get(Constant.PRODUCTLIST + today);
        cachedClient.delete(Constant.PRODUCTLIST + today);
        if (list == null || list.size() == 0) {
            list = productService.findProductIsSales();
            cachedClient.set(Constant.PRODUCTLIST + today, Constant.EXP, list);
        }
        modelAndView.addObject("productList", list);
        // 中间广告
        List<CmsAdvertVO> middleList = cachedClient.get(Constant.MIDDLELIST + today);
        cachedClient.delete(Constant.MIDDLELIST + today);
       // if (list == null || list.size() == 0) {
        CmsAdvertDTO cms = new CmsAdvertDTO();
        cms.setPosionID("index_middle");
        cms.setPageSize(3L);
        middleList = advertService.findAdvertByPagination(cms);
        cachedClient.set(Constant.MIDDLELIST + today, Constant.EXP, list);
      //  }
        modelAndView.addObject("middleList", middleList);

        // 产品类目
        List<ProductCategoryVO> productCategoryList = cachedClient.get(Constant.PRODUCTCATEGORYLIST + today);
        cachedClient.delete(Constant.PRODUCTCATEGORYLIST + today);
        if (productCategoryList == null || productCategoryList.size() == 0) {
            productCategoryList = productCategoryService.findProductOneLevel();
            // 分类广告产品
            if (productCategoryList != null && productCategoryList.size() > 0) {
                for (ProductCategoryVO productCatVO : productCategoryList) {
                    ProductDTO product = new ProductDTO();
                    product.setCatID(productCatVO.getMainID());
                    product.setPageSize(5L);
                    product.setStatus(Constant.product_status_top);
                  //List<ProductVO> products = productService.findProductSales(product);//人气排行
                  //productCatVO.setProducts(products);
                    CmsAdvertDTO advertDTO = new CmsAdvertDTO();
                    advertDTO.setPosionID("index_category");
                    advertDTO.setProductCategoryID(productCatVO.getMainID());
                    advertDTO.setPageSize(8L);
                    List<CmsAdvertVO> catAdvertList = advertService.findAdvertByPagination(advertDTO);
                    productCatVO.setProductAdvertList(catAdvertList);
                }
                cachedClient.set(Constant.PRODUCTCATEGORYLIST + today, Constant.EXP, productCategoryList);
            }
        }
        modelAndView.addObject("productCategoryList", productCategoryList);
        // 值得买标号
        modelAndView.addObject("buyFlag", productCategoryList.size() + 1);

        // 资讯
        List<CmsContentVO> newsList = cachedClient.get(Constant.INDEXCMS);
        cachedClient.delete(Constant.INDEXCMS + today);
        if (newsList == null || newsList.size() == 0) {
            newsList = newsService.findIndexNewsByNum(9);
            cachedClient.add(Constant.INDEXCMS, Constant.EXP, newsList);
        }
        modelAndView.addObject("newsList", newsList);
        // 值得买广告
        List<CmsAdvertVO> buyList = cachedClient.get(Constant.BUYLIST);
        cachedClient.delete(Constant.BUYLIST + today);
        if (buyList == null || buyList.size() == 0) {
            CmsAdvertDTO buyDTO = new CmsAdvertDTO();
            buyDTO.setPosionID("index_buy");
            buyDTO.setSerachDate(new Date());
            buyDTO.setPageSize(1l);
            buyList = advertService.findAdvertByPagination(buyDTO);
            cachedClient.add(Constant.BUYLIST, Constant.EXP, buyList);
        }
        modelAndView.addObject("buyList", buyList);

        // 值得买商品
        List<ProductVO> productbuyList = cachedClient.get(Constant.PRODUCTBUYLIST + today);
        cachedClient.delete(Constant.PRODUCTBUYLIST + today);
        if (productbuyList == null || productbuyList.size() == 0) {
            productbuyList = productService.findProductBuy();
            cachedClient.set(Constant.PRODUCTBUYLIST + today, Constant.EXP, productbuyList);
        }
        modelAndView.addObject("productbuyList", productbuyList);

        // 天天低价产品
        List<ProductVO> productList = cachedClient.get(Constant.PRODUCTLOWLIST + today);
        cachedClient.delete(Constant.PRODUCTLOWLIST + today);
        if (productList == null || productList.size() == 0) {
            productList = productService.findProductLowPrice();
            cachedClient.set(Constant.PRODUCTLOWLIST + today, Constant.EXP, productList);
        }
        modelAndView.addObject("productlowList", productList);
        return modelAndView;
    }

    
    /**
     * 商城首页
     * 
     * @date 2014年12月22日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    // TODO首页需要进行缓存处理
    @RequestMapping("index-tparts.htm")
    public ModelAndView index_n() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/index_tparts");
        String today = DateUtil.formatDate(new Date(), "yyyyMMdd");

        // 轮播图
        List<CmsAdvertVO> cmsAdvertList = cachedClient.get(Constant.CMSADVERTLIST + today);
        cachedClient.delete(Constant.CMSADVERTLIST + today);
      //  if (cmsAdvertList == null || cmsAdvertList.size() == 0) {
            CmsAdvertDTO cmsAdvertDTO = new CmsAdvertDTO();
            cmsAdvertDTO.setPosionID("index");
            cmsAdvertDTO.setSerachDate(new Date());
            cmsAdvertDTO.setPageSize(5l);
            cmsAdvertList = advertService.findAdvertByPagination(cmsAdvertDTO);
            cachedClient.set(Constant.CMSADVERTLIST + today, Constant.EXP, cmsAdvertList);
      //  }
        modelAndView.addObject("cmsAdvertList", cmsAdvertList);

        // 今日热买
        List<ProductVO> list = cachedClient.get(Constant.PRODUCTLIST + today);
        cachedClient.delete(Constant.PRODUCTLIST + today);
        if (list == null || list.size() == 0) {
            list = productService.findProductIsSales();
            cachedClient.set(Constant.PRODUCTLIST + today, Constant.EXP, list);
        }
        modelAndView.addObject("productList", list);
        // 中间广告
        List<CmsAdvertVO> middleList = cachedClient.get(Constant.MIDDLELIST + today);
        cachedClient.delete(Constant.MIDDLELIST + today);
       // if (list == null || list.size() == 0) {
        CmsAdvertDTO cms = new CmsAdvertDTO();
        cms.setPosionID("index_middle");
        cms.setPageSize(3L);
        middleList = advertService.findAdvertByPagination(cms);
        cachedClient.set(Constant.MIDDLELIST + today, Constant.EXP, list);
      //  }
        modelAndView.addObject("middleList", middleList);

        // 产品类目
        List<ProductCategoryVO> productCategoryList = cachedClient.get(Constant.PRODUCTCATEGORYLIST + today);
        cachedClient.delete(Constant.PRODUCTCATEGORYLIST + today);
        if (productCategoryList == null || productCategoryList.size() == 0) {
            productCategoryList = productCategoryService.findProductOneLevel();
            // 分类广告产品
            if (productCategoryList != null && productCategoryList.size() > 0) {
                for (ProductCategoryVO productCatVO : productCategoryList) {
                    ProductDTO product = new ProductDTO();
                    product.setCatID(productCatVO.getMainID());
                    product.setPageSize(5L);
                    product.setStatus(Constant.product_status_top);
                  //List<ProductVO> products = productService.findProductSales(product);//人气排行
                  //productCatVO.setProducts(products);
                    CmsAdvertDTO advertDTO = new CmsAdvertDTO();
                    advertDTO.setPosionID("index_category");
                    advertDTO.setProductCategoryID(productCatVO.getMainID());
                    advertDTO.setPageSize(8L);
                    List<CmsAdvertVO> catAdvertList = advertService.findAdvertByPagination(advertDTO);
                    productCatVO.setProductAdvertList(catAdvertList);
                }
                cachedClient.set(Constant.PRODUCTCATEGORYLIST + today, Constant.EXP, productCategoryList);
            }
        }
        //modelAndView.addObject("productCategoryList", productCategoryList);
        modelAndView.addObject("productCategoryList", null);
        // 值得买标号
        //modelAndView.addObject("buyFlag", productCategoryList.size() + 1);
        modelAndView.addObject("buyFlag",1);

        // 资讯
        List<CmsContentVO> newsList = cachedClient.get(Constant.INDEXCMS);
        cachedClient.delete(Constant.INDEXCMS + today);
        if (newsList == null || newsList.size() == 0) {
            newsList = newsService.findIndexNewsByNum(9);
            cachedClient.add(Constant.INDEXCMS, Constant.EXP, newsList);
        }
        modelAndView.addObject("newsList", newsList);
        // 值得买广告
        List<CmsAdvertVO> buyList = cachedClient.get(Constant.BUYLIST);
        cachedClient.delete(Constant.BUYLIST + today);
        if (buyList == null || buyList.size() == 0) {
            CmsAdvertDTO buyDTO = new CmsAdvertDTO();
            buyDTO.setPosionID("index_buy");
            buyDTO.setSerachDate(new Date());
            buyDTO.setPageSize(1l);
            buyList = advertService.findAdvertByPagination(buyDTO);
            cachedClient.add(Constant.BUYLIST, Constant.EXP, buyList);
        }
        modelAndView.addObject("buyList", buyList);

        // 值得买商品
        List<ProductVO> productbuyList = cachedClient.get(Constant.PRODUCTBUYLIST + today);
        cachedClient.delete(Constant.PRODUCTBUYLIST + today);
        if (productbuyList == null || productbuyList.size() == 0) {
            productbuyList = productService.findProductBuy();
            cachedClient.set(Constant.PRODUCTBUYLIST + today, Constant.EXP, productbuyList);
        }
        modelAndView.addObject("productbuyList", productbuyList);

        // 天天低价产品
        List<ProductVO> productList = cachedClient.get(Constant.PRODUCTLOWLIST + today);
        cachedClient.delete(Constant.PRODUCTLOWLIST + today);
        if (productList == null || productList.size() == 0) {
            productList = productService.findProductLowPrice();
            cachedClient.set(Constant.PRODUCTLOWLIST + today, Constant.EXP, productList);
        }
        modelAndView.addObject("productlowList", productList);
        return modelAndView;
    }    
    
    
    
    /**
     * 新闻列表
     * 
     * @date 2015年1月20日
     * @author wanghehua
     * @param cmsContentDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("newsList")
    public ModelAndView newsList(CmsContentDTO cmsContentDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/news/news_list");
        cmsContentDTO.setContentType(1);
        cmsContentDTO.setPageSize(5l);
        List<CmsContentVO> newsList = newsService.findNewsByPagination(cmsContentDTO);
        modelAndView.addObject("newsList", newsList);
        modelAndView.addObject("cmsContentDTO", cmsContentDTO);
        List<CmsContentVO> hotNews = newsService.findHotNews(cmsContentDTO);
        modelAndView.addObject("hotNews", hotNews);
        return modelAndView;
    }

    /**
     * 新闻详情
     * 
     * @date 2015年1月20日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("newsDetail")
    public ModelAndView newsDetail(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/news/news_detail");
        CmsContentVO cmsContentVO = newsService.findNewsByMainID(mainID);
        modelAndView.addObject("cmsContentVO", cmsContentVO);
        CmsContentVO prev = newsService.findNewsPrev(cmsContentVO.getId());
        CmsContentVO next = newsService.findNewsNext(cmsContentVO.getId());
        modelAndView.addObject("prev", prev);
        modelAndView.addObject("next", next);
        return modelAndView;
    }

    /**
     * 搜索首页
     * 
     * @date 2015年12月29日
     * @author lby
     * @return
     * @since JDK 1.6
     * @Description
     */
    // TODO首页需要进行缓存处理
    @RequestMapping("index.htm")
    public ModelAndView index_new() {
		List<BrandModel> brands = productCategoryService.findProductOneLevelTparts();
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.addObject("brandsModel", brands);
		
		//登录用户“我的关注”
		CustomerVO customerVO = this.findUserInfo();
		if (customerVO != null){
			FocusEntity focusEntity = new FocusEntity();
			focusEntity.setCustomerID(customerVO.getMainID());
			focusEntity.setLimitNum(10);
			
			//车型搜索历史
			focusEntity.setFocusType(1);
			List<FocusEntity> vehicleTypeSearchList = focusService.findByFocusEntity(focusEntity);
			modelAndView.addObject("vehicleTypeSearchList", vehicleTypeSearchList);

			//VIN搜索历史
			focusEntity.setFocusType(2);
			List<FocusEntity> VINSearchList = focusService.findByFocusEntity(focusEntity);
			modelAndView.addObject("VINSearchList", VINSearchList);
		
			//OEM搜索历史
			focusEntity.setFocusType(3);
			List<FocusEntity> OEMSearchList = focusService.findByFocusEntity(focusEntity);
			modelAndView.addObject("OEMSearchList", OEMSearchList);
		}
		
		modelAndView.setViewName("screen/index");
        return modelAndView;
    }
    
}
