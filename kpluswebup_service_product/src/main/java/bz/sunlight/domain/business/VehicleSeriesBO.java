package bz.sunlight.domain.business;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.VehicleTypeVO;
//TODO 目前数据库中engineCapacity 和 modelYear 字段没有数据；
public class VehicleSeriesBO {

	private String seriesId;
	private String seriesName;
	private List<Children> children;
	
	public void buildChildren(List<VehicleTypeVO> vehicleSeries){
		
		children=Lists.newArrayList();
		for (VehicleTypeVO vehicleTypeVO : vehicleSeries) {
			Children children1=new Children();
			children1.setModelId(vehicleTypeVO.getMainID());
			children1.setModelName(vehicleTypeVO.getName());
			children1.setEngineCapacity(vehicleTypeVO.getModelYear());
			children1.setModelYear(vehicleTypeVO.getDisplacement());
			children.add(children1);
		}
	}
	public String getSeriesName() {
		return seriesName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	
	public String getSeriesId() {
		return seriesId;
	}
	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}
	
	public List<Children> getChildren() {
		return children;
	}
	public void setChildren(List<Children> children) {
		this.children = children;
	}


    
	public class Children{
		private String modelId;
		private String modelName;
		private String modelYear;
		private String engineCapacity;
		public String getModelId() {
			return modelId;
		}
		public void setModelId(String modelId) {
			this.modelId = modelId;
		}
		public String getModelName() {
			return modelName;
		}
		public void setModelName(String modelName) {
			this.modelName = modelName;
		}
		public String getModelYear() {
			return modelYear;
		}
		public void setModelYear(String modelYear) {
			this.modelYear = modelYear;
		}
		public String getEngineCapacity() {
			return engineCapacity;
		}
		public void setEngineCapacity(String engineCapacity) {
			this.engineCapacity = engineCapacity;
		}
		
		
	}
}
