package com.kpluswebup.mall.web.product.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.SalesOrderLineDTO;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.PartsCategoryService;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.PartsCategoryVo;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpluswebup.web.vo.VehicleTypeVO;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.TpartsUtils;

@Controller
@RequestMapping("/mall/item")
public class ItemControl extends BaseController {
	
	@Autowired
	private ItemService 	   itemService;

	@Autowired
    private ProductService     productService;

	@Autowired
	private PartsCategoryService partsCategoryService;

	@RequestMapping("list")
	public ModelAndView getItemList(String productId,String pageNo)
	{
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setProductID(productId);
		if(StringUtil.isEmpty(pageNo))
		{
			itemDTO.setPageNo(1l);
		}else
		{
			if (StringUtil.isNumberic(pageNo)) {
	            itemDTO.setPageNo(Long.parseLong(pageNo));
	        }			
		}	
		
		
		ArrayList<String> promotions = new ArrayList<String>();
		promotions.add("10店铺周年庆,本店全场85折！");
		promotions.add("老顾客回馈,该商品买1送1！");
		promotions.add("该商品，满300折50元!");
		promotions.add("新款爆品,限时一折抢购!");
		promotions.add("本店商品免邮费!");
		promotions.add("购买送大礼包!");
		promotions.add("当前商品买1送1!");
		promotions.add("当前商品，清空处理，只要998！");
		promotions.add("商品不参与促销！！");
		promotions.add("原场配件，购买后保修1年！");

		itemDTO.setPageSize(10l);
		List<ItemVO> items = itemService.findItemsByProductIDTparts(itemDTO);
		if(items.size()<=0)
		{
			productId = "01D5B540-FB86-41A8-92AA-FF5E79E72343";
			itemDTO.setProductID(productId);
			items = itemService.findItemsByProductIDTparts(itemDTO);
		}
		
		//Map<String,ItemVO> itemMap = new HashMap<String, ItemVO>();
		for(int i=0;i<items.size();i++){
			//itemMap.put(promotions.get(i), items.get(i));
			items.get(i).setPromotion(promotions.get(i));
        }
		
		ProductVO productVO = productService.findProductByMainIDNew(productId);
		
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.addObject("items",items);
		//modelAndView.addObject("itemMap",itemMap);
		modelAndView.addObject("productVO",productVO);
		modelAndView.addObject("itemDTO",itemDTO);

		//查找配件类目（暂未添加至商品列表）
		List<PartsCategoryVo> partsCategoryList = partsCategoryService.findPartsCategoryByProductID(productId);
		for (PartsCategoryVo partsCategoryVo : partsCategoryList) {
			if(partsCategoryVo.getFlevel()==1)
				modelAndView.addObject("partsCategoryLevel", partsCategoryVo);
			if(partsCategoryVo.getFlevel()==2)
				modelAndView.addObject("partsCategoryLevel2", partsCategoryVo);			
		}
        //适用车型
		StopWatch sw = new StopWatch();
		sw.start("findSuitVehicle 适用车型");
		Map<ProductCategoryVO, List<VehicleTypeVO>> vehicleTypeMap = itemService.findSuitVehicle(productVO);
		sw.stop();
		TpartsUtils.stopWatchStopRunning(sw);
		System.out.println(sw.prettyPrint());
		modelAndView.addObject("vehicleTypeMap",vehicleTypeMap);
		
		//原厂件/副厂件
		ProductVO _productVO = new ProductVO();
		_productVO.setCode(productVO.getCode());
		
		/* 原厂件 0 1  <--> 副厂件 2
		 * 
		 * */
		if(productVO.getType() == 2)
			_productVO.setType(1);
		else
			_productVO.setType(2);	
			
		List<ProductVO> productOEMOther = productService.findProductByOEM(_productVO);
		modelAndView.addObject("productOEMOther",productOEMOther);
		
		modelAndView.setViewName("screen/product/product_detail_items");
		return modelAndView;
	}

	@RequestMapping("productInfo")
	public ModelAndView productInfo(String productId,String searchModel,String oem)
	{
		if(productId == null){
			productId = "01D5B540-FB86-41A8-92AA-FF5E79E72343";
		}
		ProductVO productVO = productService.findProductByMainIDNew(productId);

		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.addObject("productVO",productVO);

		//查找配件类目（暂未添加至商品列表）
		List<PartsCategoryVo> partsCategoryList = partsCategoryService.findPartsCategoryByProductID(productId);
		for (PartsCategoryVo partsCategoryVo : partsCategoryList) {
			if(partsCategoryVo.getFlevel()==1)
				modelAndView.addObject("partsCategoryLevel", partsCategoryVo);
			if(partsCategoryVo.getFlevel()==2)
				modelAndView.addObject("partsCategoryLevel2", partsCategoryVo);			
		}
        //适用车型
		StopWatch sw = new StopWatch();
		sw.start("findSuitVehicle 适用车型");
		Map<ProductCategoryVO, List<VehicleTypeVO>> vehicleTypeMap = itemService.findSuitVehicle(productVO);
		sw.stop();
		TpartsUtils.stopWatchStopRunning(sw);
		System.out.println(sw.prettyPrint());
		modelAndView.addObject("vehicleTypeMap",vehicleTypeMap);
		
		//原厂件/副厂件
		ProductVO _productVO = new ProductVO();
		_productVO.setCode(productVO.getCode());
		
		/* 原厂件 0 1  <--> 副厂件 2
		 * 
		 * */
		if(productVO.getType() == 2)
			_productVO.setType(1);
		else
			_productVO.setType(2);	
			
		List<ProductVO> productOEMOther = productService.findProductByOEM(_productVO);
		modelAndView.addObject("productOEMOther",productOEMOther);
		if(StringUtil.isNotEmpty(searchModel))
		{
			modelAndView.addObject("searchModel",searchModel);
			modelAndView.addObject("pname",oem);
		}
		modelAndView.setViewName("screen/product/productInfo");
		return modelAndView;
	}
	
	
	@RequestMapping("editItemTest")
	public ModelAndView editItemTest()
	{
		ItemVO itemVo = new ItemVO();
		itemVo.setMainID("0003359650784");
		itemVo.setPicURL("sxc.png");
		try {
			itemService.editItemTest(itemVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
