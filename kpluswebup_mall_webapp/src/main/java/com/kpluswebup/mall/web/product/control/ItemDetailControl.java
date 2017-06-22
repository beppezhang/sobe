package com.kpluswebup.mall.web.product.control;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.content.service.AdvertService;
import com.kpluswebup.web.domain.CmsAdvertDTO;
import com.kpluswebup.web.domain.FavoriteDTO;
import com.kpluswebup.web.domain.ItemConsultingDTO;
import com.kpluswebup.web.domain.ItemReviewDTO;
import com.kpluswebup.web.domain.PreSaleDTO;
import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.member.service.FavoriteService;
import com.kpluswebup.web.member.service.ItemConsultingService;
import com.kpluswebup.web.member.service.ItemReviewService;
import com.kpluswebup.web.service.BrandService;
import com.kpluswebup.web.service.FlashSaleService;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.PreSaleService;
import com.kpluswebup.web.service.ProductPictureService;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.vo.BrandVO;
import com.kpluswebup.web.vo.CmsAdvertVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.FlashSaleVO;
import com.kpluswebup.web.vo.ItemConsultingVO;
import com.kpluswebup.web.vo.ItemDetailVO;
import com.kpluswebup.web.vo.ItemReviewVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.PreSaleVO;
import com.kpluswebup.web.vo.ProductDetailVO;
import com.kpluswebup.web.vo.ProductPictureVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/mall/item")
public class ItemDetailControl extends BaseController {

    @Autowired
    private PreSaleService        preSaleService;

    @Autowired
    private ItemService           itemService;

    @Autowired
    private FlashSaleService      flashSaleService;

    @Autowired
    private BrandService          brandService;

    @Autowired
    private ProductService        productService;

    @Autowired
    private ProductPictureService productPictureService;

    @Autowired
    private ItemReviewService     itemReviewService;

    @Autowired
    private ItemConsultingService itemConsultingService;

    @Autowired
    private FavoriteService       favoriteService;

    @Autowired
    private CachedClient          cachedClient;
    @Autowired
    private AdvertService         advertService;

    /**
     * 进入商品详情
     * 
     * @date 2014年12月26日
     * @author liudanqi
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("itemDetail")
    public ModelAndView itemDetail(String itemID, String preID, String flashID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/item_detail");
        CustomerVO customer = this.findUserInfo();

        if (StringUtil.isNotEmpty(preID)) {
            PreSaleVO pre = preSaleService.findPreSale(preID);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            pre.setModifyTime(calendar.getTime());
            modelAndView.addObject("sale", pre);
            modelAndView.addObject("orderType", 1);
        } else if (StringUtil.isNotEmpty(flashID)) {
            FlashSaleVO flash = flashSaleService.findFlashSaleByMainID(flashID);
            flash.setMaxmumQty(flash.getTotality());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            flash.setModifyTime(calendar.getTime());
            modelAndView.addObject("sale", flash);
            modelAndView.addObject("orderType", 3);
        } else {
            modelAndView.addObject("orderType", 0);
        }
        ItemVO item = itemService.findItemByMainID(itemID);
        modelAndView.addObject("item", item);

        if (customer != null) {
            boolean isfavorite = favoriteService.isFavorite(customer.getMainID(), item.getProductID());
            modelAndView.addObject("favorite", isfavorite);
        }

        List<ItemDetailVO> itemProps = itemService.findItemProps(itemID);
        modelAndView.addObject("itemProps", itemProps);

        ProductVO product = productService.findProductByMainID(item.getProductID());
        if (StringUtil.isNotEmpty(product.getDescription())) {
            product.setDescription(product.getDescription().replaceAll("<img src=\"/", "<img src=\"" + imgurl + "/"));
        }
        if (StringUtil.isNotEmpty(product.getProductProp())) {
            product.setProductProp(product.getProductProp().replaceAll("<img src=\"/", "<img src=\"" + imgurl + "/"));
        }
        if (StringUtil.isNotEmpty(product.getSaleService())) {
            product.setSaleService(product.getSaleService().replaceAll("<img src=\"/", "<img src=\"" + imgurl + "/"));
        }
        modelAndView.addObject("product", product);

        List<ProductDetailVO> productProps = productService.findProductDetailList(item.getProductID());
        modelAndView.addObject("productProps", productProps);

        BrandVO brand = brandService.findBrandByMainID(product.getBrandID());
        modelAndView.addObject("brand", brand);

        List<ProductPictureVO> pictures = productPictureService.findProductPictureByProductID(item.getMainID(),null);
        modelAndView.addObject("pictures", pictures);

        ItemReviewDTO dto = new ItemReviewDTO();
        dto.setProductID(item.getProductID());
        dto.setStatus(2);
        List<ItemReviewVO> reviews = itemReviewService.findReviews(dto);
        modelAndView.addObject("reviews", reviews);
        Integer all = reviews.size();
        Integer good = 0; // 好评
        Integer middle = 0; // 中评
        Integer bad = 0; // 差评
        Integer allScore = 0; // 总分
        Double allReviewScore = 5d; // 总评分
        Double goodDegree = 1d; // 好评度
        Double middleDegree = 1d; // 中评度
        Double badDegree = 1d; // 差评度
        if (reviews != null && reviews.size() > 0) {
            for (ItemReviewVO re : reviews) {
                if (re.getScore() > 3) {
                    good = good + 1;
                } else if (re.getScore() < 2) {
                    bad = bad + 1;
                } else {
                    middle = middle + 1;
                }
                allScore = allScore + re.getScore();
            }
        }
        modelAndView.addObject("all", all);
        modelAndView.addObject("good", good);
        modelAndView.addObject("middle", middle);
        modelAndView.addObject("bad", bad);
        if (all != 0) {
            allReviewScore = (double) Math.round(Double.valueOf(allScore) / all);
        }
        modelAndView.addObject("allReviewScore", allReviewScore);
        if (all != 0) {
            goodDegree = Double.valueOf(good) / all;
            if (goodDegree.toString().split("\\.")[1].length() == 2) {
                Float goods = (float) (Math.round(goodDegree * 10)) / 10;
                goodDegree = goods.doubleValue();
            }
            middleDegree = Double.valueOf(middle) / all;
            badDegree = Double.valueOf(bad) / all;
        }
        modelAndView.addObject("goodDegree", goodDegree * 100);
        modelAndView.addObject("middleDegree", middleDegree * 100);
        modelAndView.addObject("badDegree", badDegree * 100);

        ItemConsultingDTO idto = new ItemConsultingDTO();
        idto.setProductID(item.getProductID());
        List<ItemConsultingVO> consultings = itemConsultingService.findConsults(idto);
        modelAndView.addObject("consultings", consultings);

        FavoriteDTO favoriteDTO = new FavoriteDTO();
        favoriteDTO.setProductID(item.getProductID());
        Long fCount = favoriteService.findCountByitemIdOrProductId(favoriteDTO);
        modelAndView.addObject("fCount", fCount);

        List<ItemVO> items = itemService.finditems(item.getProductID());
        modelAndView.addObject("items", items);

        CmsAdvertDTO cms = new CmsAdvertDTO();
        cms.setPosionID("product_detail");
        List<CmsAdvertVO> advertList = advertService.findAdvertByPagination(cms);
        modelAndView.addObject("advertList", advertList);

        return modelAndView;
    }

    @RequestMapping("ajaxZixun")
    public @ResponseBody
    JsonResult ajaxZixun(String content, String itemID, String productID) {
        try {
            CustomerVO customerVO = findUserInfo();
            if (customerVO == null) {
                cachedClient.set(Constant.BEFORE_LOGIN_URL + randomUUID(), Constant.EXP,
                                 "/mall/product/ajaxZixun.do?itemID=" + itemID + "&productID=" + productID
                                         + "&content=" + content);
                return new JsonResult(ResultCode.ERROR_LOGIN);
            }
            ItemConsultingDTO dto = new ItemConsultingDTO();
            dto.setMainID(UUIDUtil.getUUID());
            dto.setContent(content);
            dto.setItemID(itemID);
            dto.setProductID(productID);
            dto.setCustomerID(customerVO.getMainID());
            itemConsultingService.addItemConsulting(dto);
            return new JsonResult(ResultCode.NORMAL);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping("ajaxFavorite")
    public @ResponseBody
    JsonResult ajaxFavorite(String itemID, String productID) {
        try {
            CustomerVO customerVO = this.findUserInfo();
            if (customerVO == null) {
                cachedClient.set(Constant.BEFORE_LOGIN_URL + randomUUID(), Constant.EXP,
                                 "/mall/product/ajaxFavorite.do?itemID=" + itemID + "&productID=" + productID);
                return new JsonResult(ResultCode.ERROR_LOGIN);
            }
            FavoriteDTO dto = new FavoriteDTO();
            dto.setProductID(productID);
            dto.setItemID(itemID);
            dto.setCustomerID(customerVO.getMainID());
            favoriteService.insertFavorite(dto);
            return new JsonResult(ResultCode.NORMAL);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }

    }

    @RequestMapping("ajaxReview")
    public @ResponseBody
    JsonResult ajaxReview(String content, String itemID, String productID, String score) {
        try {
            CustomerVO customerVO = this.findUserInfo();
            if (customerVO == null) {
                cachedClient.set(Constant.BEFORE_LOGIN_URL + randomUUID(), Constant.EXP,
                                 "/mall/product/ajaxReview.do?itemID=" + itemID + "&productID=" + productID
                                         + "&content=" + content + "&score=" + score);
                return new JsonResult(ResultCode.ERROR_LOGIN);
            }
            ItemReviewDTO dto = new ItemReviewDTO();
            dto.setContent(content);
            dto.setItemID(itemID);
            dto.setProductID(productID);
            dto.setCustomerID(customerVO.getMainID());
            dto.setScore(Integer.parseInt(score));
            dto.setStatus(1);
            dto.setMainID(UUIDUtil.getUUID());
            itemReviewService.addtReview(dto);
            return new JsonResult(ResultCode.NORMAL);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }

    /**
     * 品牌故事
     * 
     * @date 2015年1月4日
     * @author yuanyuan
     * @param brandId
     * @param salePrice
     * @param pageNo
     * @param salesVolume
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("brandHistory")
    public ModelAndView brandHistory(String brandId, String salePrice, String pageNo, String salesVolume,
                                     String creatTime) {
        // brandId="768";
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/brand");
        ProductDTO productDTO = new ProductDTO();
        if (StringUtil.isNotEmpty(brandId)) {
            productDTO.setBrandID(brandId);
        }
        if (StringUtil.isNumberic(pageNo)) {
            productDTO.setPageNo(Long.parseLong(pageNo));
        }
        if (StringUtil.isNotEmpty(salePrice)) {
            productDTO.setOrderByClause(" order by minSalesPrice asc");
        }
        if (StringUtil.isNotEmpty(salesVolume)) {
            productDTO.setOrderByClause(" ORDER BY maxSalesVolume desc ");
        }
        if (StringUtil.isNotEmpty(creatTime)) {
            productDTO.setOrderByClause(" order by p.createTime desc ");
        }
        productDTO.setStatus(Constant.product_status_top);
        productDTO.setPageSize(20l);
        List<ProductVO> list = productService.searchProducItemCat(productDTO);
        modelAndView.addObject("list", list);
        modelAndView.addObject("product", productDTO);
        BrandVO brandVo = brandService.findBrandByMainID(brandId);
        modelAndView.addObject("brandVo", brandVo);

        return modelAndView;
    }

    /**
     * 新品预售
     * 
     * @date 2015年1月5日
     * @author yuanyuan
     * @param pageNo
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("preSaleList")
    public ModelAndView preSaleList(String pageNo) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/product/new_arrival");
        PreSaleDTO preSaleDTO = new PreSaleDTO();
        if (StringUtil.isNumberic(pageNo)) {
            preSaleDTO.setPageNo(Long.parseLong(pageNo));
        }
        preSaleDTO.setPageSize(16l);
        preSaleDTO.setProductStatus(1);
        preSaleDTO.setOrderByClause("ORDER BY ps.id DESC");
        List<PreSaleVO> list = itemService.findPreSaleList(preSaleDTO);
        modelAndView.addObject("list", list);
        modelAndView.addObject("preSaleDTO", preSaleDTO);
        return modelAndView;
    }
}
