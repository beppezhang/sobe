package bz.sunlight.web.service;

public interface VehicleBrandsService {

	// 查找热门品牌
	public String findHotBrands();

	// 查找所有品牌
	public String findAllBrands();
	
	// 根据mainID查找品牌；
	public String findBrandById(String id);

}
