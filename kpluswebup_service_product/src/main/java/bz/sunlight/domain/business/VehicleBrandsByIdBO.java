package bz.sunlight.domain.business;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kpluswebup.web.vo.PartsCategoryVo;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpuswebup.comom.util.PropertiesUtil;

public class VehicleBrandsByIdBO {

	/*
	 * { "brandName": [string], // 汽车品牌名称 "icon": [string], // 汽车品牌图标的绝对地址
	 * "children": [ { "groupName": [string], // 车系分组名称 "series": [ {
	 * "seriesId": [string], // 车系 Id "seriesName": [string], // 车系名称 } ] } ] }
	 * 
	 * }
	 */
	/*
	 * @parameter: brandName:汽车品牌名称 icon: 汽车品牌图标的绝对地址 children:子类
	 */
	private String brandId;
	private String brandName;
	private String icon;
	private List<Children> children;

	public void buildBrand(ProductCategoryVO productCategoryVO) {
		brandId=productCategoryVO.getMainID();
		brandName = productCategoryVO.getName();
		icon = getPicUrlPrefix() + productCategoryVO.getPicUrl();
	}

	public List<Children> getChildren() {
		return children;
	}

	public void setChildren(List<Children> children) {
		this.children = children;
	}
	
	
	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public class Children {

		private String groupName;// 目前没有相应的业务，暂时写死
		private List<Series> series;

		public String getGroupName() {
			return groupName;
		}

		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}

		public List<Series> getSeries() {
			return series;
		}

		public void setSeries(List<Series> series) {
			this.series = series;
		}

	}

	public class Series {
		private String seriesId;
		private String seriesName;

		public String getSeriesId() {
			return seriesId;
		}

		public void setSeriesId(String seriesId) {
			this.seriesId = seriesId;
		}

		public String getSeriesName() {
			return seriesName;
		}

		public void setSeriesName(String seriesName) {
			this.seriesName = seriesName;
		}

	}

	// 用于拼接图片的绝对地址
	private String getPicUrlPrefix() {
		return PropertiesUtil.getInstanse().getString("mallStatic")
				+ "/img/logo/";
	}

}
