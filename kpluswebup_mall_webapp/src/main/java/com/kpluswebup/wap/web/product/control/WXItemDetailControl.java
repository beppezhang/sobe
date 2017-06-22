package com.kpluswebup.wap.web.product.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.member.service.FavoriteService;
import com.kpluswebup.web.service.FlashSaleService;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.PreSaleService;
import com.kpluswebup.web.service.ProductPictureService;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.ItemDetailVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.ProductPictureVO;
import com.kpluswebup.web.vo.ProductVO;

@Controller
@RequestMapping("/weixin")
public class WXItemDetailControl extends BaseController {
    
    @Autowired
    private PreSaleService preSaleService;
    
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private FlashSaleService flashSaleService;
    
    @Autowired
    private FavoriteService favoriteService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductPictureService productPictureService;
    
    @RequestMapping("itemDetail")
    public ModelAndView itemDetail(String itemID){
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("wap/screen/item_detail");
        CustomerVO customer = this.findWXUserInfo();
        ItemVO item = itemService.findItemByMainID(itemID);
        modelAndView.addObject("item", item);
        
        if(customer != null){
            boolean isfavorite = favoriteService.isFavorite(customer.getMainID(), item.getProductID());
            modelAndView.addObject("favorite", isfavorite);
        }
        
        List<ItemDetailVO> itemProps = itemService.findItemProps(itemID);
        modelAndView.addObject("itemProps", itemProps);
        
        ProductVO product = productService.findProductByMainID(item.getProductID());
        modelAndView.addObject("product", product);
        
        List<ProductPictureVO> pictures = productPictureService.findProductPictureByProductID(item.getProductID(),null);
        modelAndView.addObject("pictures", pictures);
        return modelAndView;
        
    }
    

}
