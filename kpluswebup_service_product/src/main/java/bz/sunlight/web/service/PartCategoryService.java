package bz.sunlight.web.service;

public interface PartCategoryService {

	//查询指定车型备件分类
	public String findPartCategoriesByVehicleModel(String vehicleModelId);
	
	//查询指定备件分类的详情
	public String findPartCategroyInfo(String partCategoryId,String vehicleModelId);
}
