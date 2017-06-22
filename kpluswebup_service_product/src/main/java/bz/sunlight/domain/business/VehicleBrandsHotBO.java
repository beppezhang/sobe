package bz.sunlight.domain.business;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpuswebup.comom.util.PropertiesUtil;

public class VehicleBrandsHotBO {

	public List<Map<String, String>> hot;

	public List<Map<String, String>> getHot() {
		return hot;
	}

	public void setHot(List<Map<String, String>> hot) {
		this.hot = hot;
	}
	
	public void bulidHot(List<ProductCategoryVO> list) {
		VehicleBrandsHotBO vehicleBrandsHotBO = new VehicleBrandsHotBO();
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
		vehicleBrandsHotBO.setHot(hot);

	}
	
	// 用于拼接图片的绝对地址
	private String getPicUrlPrefix() {
		return PropertiesUtil.getInstanse().getString("mallStatic")
				+ "/img/logo/";
	}
}
