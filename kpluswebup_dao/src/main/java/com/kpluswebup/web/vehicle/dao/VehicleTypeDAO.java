package com.kpluswebup.web.vehicle.dao;

import java.util.List;

import com.kpluswebup.web.vo.VehicleTypeVO;

public interface VehicleTypeDAO {

	/**
	 * 根据整车分类 查询 整车车型
	 * @param mainID
	 * @return
	 */
	public List<VehicleTypeVO> findVehiclePaginationByCategoryId(VehicleTypeVO vehicleTypeVO);
	
	public Long findVehicleByCategoryIdCount(VehicleTypeVO vehicleTypeVO);
	
	public List<VehicleTypeVO> findByVin(String vin);
	
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

	/*
	 * 根据备件 id 和页码 ，查带适用车型
	 * */
	public Long findVehicleTypesCountForMobile(VehicleTypeVO vehicleTypeVO);
	public List<VehicleTypeVO> findVehicleTypesForMobile(VehicleTypeVO vehicleTypeVO);
	
}
