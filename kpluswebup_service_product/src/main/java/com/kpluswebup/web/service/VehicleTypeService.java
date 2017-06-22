package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.vo.VehicleTypeVO;

public interface VehicleTypeService {
	public List<VehicleTypeVO> findByPagination(VehicleTypeVO vehicleTypeVO);
	
	public VehicleTypeVO findByVin(String vin);
	
	public VehicleTypeVO findByMainID(String mainID);
	
	public List<String> findProductIdsByOEM(String mainID);
	
	
	/**
	 * 根据配件产品查询适用车型
	 * @param productId
	 * @return
	 */	
	public List<VehicleTypeVO> findVehicleTypeByProductId(String productId);
	
	/**
	 * 根据车系加载所有车型
	 * @param vehicleTypeVO
	 * @return
	 */
	public List<VehicleTypeVO> findVehicleByCategoryId(VehicleTypeVO vehicleTypeVO);	
}
