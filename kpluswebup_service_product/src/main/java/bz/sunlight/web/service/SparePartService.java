package bz.sunlight.web.service;

public interface SparePartService {

	//查询指定备件的详情
	public String findSparePartByMainID(String productID);
	
	//查询指定备件适用车型
	public String findApplicableVehicleModel(String id, int pageIndex, int pageSize);

	//查询最近浏览的备件
	public String findLastVisited();
}
