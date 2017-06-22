package com.kpluswebup.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.service.VehicleTypeService;
import com.kpluswebup.web.vehicle.dao.VehicleTypeDAO;
import com.kpluswebup.web.vo.VehicleTypeVO;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

	@Autowired
	private VehicleTypeDAO vehicleTypeDAO;
	
	@Override
	public List<VehicleTypeVO> findByPagination(VehicleTypeVO vehicleTypeVO) {
		vehicleTypeVO.doPage(vehicleTypeDAO.findVehicleByCategoryIdCount(vehicleTypeVO), vehicleTypeVO.getPageNo(), vehicleTypeVO.getPageSize());
		List<VehicleTypeVO> vehicleTypes = vehicleTypeDAO.findVehiclePaginationByCategoryId(vehicleTypeVO);
		return vehicleTypes;
	}
	/**
	 * 根据车系加载所有车型
	 * @param vehicleTypeVO
	 * @return
	 */	
	@Override
	public List<VehicleTypeVO> findVehicleByCategoryId(
			VehicleTypeVO vehicleTypeVO) {
		return vehicleTypeDAO.findVehiclePaginationByCategoryId(vehicleTypeVO);
	}
	
	@Override
	public VehicleTypeVO findByVin(String vin) {
		// TODO Auto-generated method stub
		List<VehicleTypeVO> vehicleTypes = vehicleTypeDAO.findByVin(vin);
		if(vehicleTypes!=null && vehicleTypes.size() >= 1)
		{
			return vehicleTypes.get(0);
		}
		return null;
	}
	
	@Override
	public VehicleTypeVO findByMainID(String mainID) {
		return vehicleTypeDAO.findByMainID(mainID);
	}
	
	@Override
	public List<String> findProductIdsByOEM(String mainID) {
		return vehicleTypeDAO.findProductIdsByOEM(mainID);
	}
	
	/**
	 * 根据配件产品查询适用车型
	 * @param productId
	 * @return
	 */		
	@Override
	public List<VehicleTypeVO> findVehicleTypeByProductId(String productId) {
		return vehicleTypeDAO.findVehicleTypeByProductId(productId);
	}
}
