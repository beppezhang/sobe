package bz.sunlight.domain.business;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpuswebup.comom.util.PropertiesUtil;

public class VehicleBrandsAllBO {

	public List<Map<String, String>> hot;
	
	public List<Map<String, String>> all;

	public List<Map<String, String>> getHot() {
		return hot;
	}

	public void setHot(List<Map<String, String>> hot) {
		this.hot = hot;
	}
	
	
	
	public List<Map<String, String>> getAll() {
		return all;
	}

	public void setAll(List<Map<String, String>> all) {
		this.all = all;
	}

	public void bulidHot(List<ProductCategoryVO> list) {
		VehicleBrandsAllBO vehicleBrandsAllBO = new VehicleBrandsAllBO();
		hot = Lists.newArrayList();
		Map<String, String> brandModels;
		for (ProductCategoryVO productCategoryVO : list) {
			brandModels = Maps.newHashMap();
			brandModels.put("brandId", productCategoryVO.getMainID());
			brandModels.put("icon",
					getPicUrlPrefix() + productCategoryVO.getPicUrl());
			brandModels.put("brandName", productCategoryVO.getName());
			hot.add(brandModels);
			
		}
		vehicleBrandsAllBO.setHot(hot);

	}
	
	public void bulidAll(List<ProductCategoryVO> list) {
		VehicleBrandsAllBO vehicleBrandsAllBO = new VehicleBrandsAllBO();
		all = Lists.newArrayList();
		Map<String, String> brandModels;
		for (ProductCategoryVO productCategoryVO : list) {
			brandModels = Maps.newHashMap();
			brandModels.put("brandId", productCategoryVO.getMainID());
			brandModels.put("icon",
					getPicUrlPrefix() + productCategoryVO.getPicUrl());
			brandModels.put("brandName", productCategoryVO.getName());
			all.add(brandModels);
			
		}
		vehicleBrandsAllBO.setAll(all);

	}
	
	// 用于拼接图片的绝对地址
	private String getPicUrlPrefix() {
		return PropertiesUtil.getInstanse().getString("mallStatic")
				+ "/img/logo/";
	}
}
