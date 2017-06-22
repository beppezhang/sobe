package bz.sunlight.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.product.dao.ProductCategoryDAO;
import com.kpluswebup.web.vehicle.dao.VehicleTypeDAO;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.VehicleTypeVO;
import com.kpuswebup.comom.util.GsonUtil;
import com.kpuswebup.comom.util.StringUtil;

import bz.sunlight.domain.business.VehicleSeriesBO;
import bz.sunlight.web.service.VehicleSeriesService;

@Service
public class VehicleSeriesServiceImpl implements VehicleSeriesService {
	
	@Autowired 
	VehicleTypeDAO vehicleTypeDAO;
	
	@Autowired 
	ProductCategoryDAO productCategoryDAO;

	@Override
	public String findVehicleSeriesById(VehicleTypeVO vehicleTypeVO) {
		//查找车系:children
		List<VehicleTypeVO> vehicleSeries=vehicleTypeDAO.findVehiclePaginationByCategoryId(vehicleTypeVO);
		VehicleSeriesBO vehicleSeriesBO=new VehicleSeriesBO();
		if(vehicleSeries!=null && vehicleSeries.size()>0){
			vehicleSeriesBO.buildChildren(vehicleSeries);
			//设置seriesName&seriesId的值
			ProductCategoryVO  productCategoryVO= productCategoryDAO.findProductCategoryByMainID(vehicleTypeVO.getProductCategoryId());
			vehicleSeriesBO.setSeriesName(productCategoryVO.getName());
			vehicleSeriesBO.setSeriesId(productCategoryVO.getMainID());
			String vehicleSeriesJson=GsonUtil.toJson(vehicleSeriesBO,true);
			return vehicleSeriesJson;
		}else{
			return null;
		}
		
	}
	
	
	
}
