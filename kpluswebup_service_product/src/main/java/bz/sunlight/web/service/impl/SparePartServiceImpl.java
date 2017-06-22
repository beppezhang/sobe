package bz.sunlight.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kpluswebup.web.customer.dao.FocusDAO;
import com.kpluswebup.web.partscategory.dao.PartsCategoryDAO;
import com.kpluswebup.web.product.dao.ProductCategoryDAO;
import com.kpluswebup.web.product.dao.ProductDAO;
import com.kpluswebup.web.vehicle.dao.VehicleTypeDAO;
import com.kpluswebup.web.vo.FocusEntity;
import com.kpluswebup.web.vo.PartsCategoryVo;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.VehicleTypeVO;
import com.kpuswebup.comom.util.GsonUtil;

import bz.sunlight.domain.business.KeyValue;
import bz.sunlight.domain.business.SparePartBO;
import bz.sunlight.web.service.SparePartService;

@Service
public class SparePartServiceImpl implements SparePartService {
	
	@Autowired
	private PartsCategoryDAO partsCategoryDAO;

	@Autowired
    private ProductDAO productDAO;
	
	@Autowired
	private VehicleTypeDAO vehicleTypeDAO;
	
	@Autowired
	private ProductCategoryDAO productCategoryDAO; 
	
	@Autowired
	private FocusDAO focusDAO;
	
	private List<Map<String,String>> applicableVehicleModels;	//适用车型待转目标

	private List<Map<String,String>> lastVistedSpareParts;
	
	@Override
	public String findSparePartByMainID(String productID) {
		ProductVO productVO = productDAO.findProductByMainIDNew(productID);
		
		//如果找不到相关备件，流程不再继续，GsonUtil会将null转成{}
		if (productVO == null){
			return null;
		}

		SparePartBO sparePartBO = new SparePartBO();
		
		sparePartBO.setPartId(productID);
		sparePartBO.setPartCode(productVO.getCode());
		sparePartBO.setPartName(productVO.getName());
		sparePartBO.setBrandName(productVO.getBrandName());
		sparePartBO.setGeneric(true);			//???
		
		// TODO 因为数据上的原因，这里暂时第三层分类只取了一个（从数据库中查出来对应的有两个第三层备件类目）
		//备件分类
		List<PartsCategoryVo> partsCategoryList = partsCategoryDAO.findPartsCategoryByProductID(productID);
		sparePartBO.buildBelongTo(partsCategoryList);
		
		//备件图片（暂时利用备件的picUrl构造一个List<String>传入相关方法对备件图片赋值）
		List<String> urls = new ArrayList<String>();
		urls.add(productVO.getPicURL());
		sparePartBO.buildImages(urls);

		// TODO parameters 暂时给到车型信息
		//车型信息（这块信息可等后面能直接调DAO查出相关信息后，用SparePartBO的setParameter方法对相应字段进行赋值）
		List<KeyValue> parameters = new ArrayList<KeyValue>();
		KeyValue parameter1 = new KeyValue();
		parameter1.setKey("功率");
		parameter1.setValue("55");
		parameters.add(parameter1);
		KeyValue parameter2 = new KeyValue();
		parameter2.setKey("气缸");
		parameter2.setValue("4");
		parameters.add(parameter2);
		sparePartBO.setParameters(parameters);
		
		//相关备件
		ProductVO _productVO = new ProductVO();
		_productVO.setCode(productVO.getCode());
		
		/* 原厂件 0 1  <--> 副厂件 2
		 * 
		 * */
		if(productVO.getType() == 2)
			_productVO.setType(1);
		else
			_productVO.setType(2);	
			
		List<ProductVO> productOEMOther = productDAO.findProductByOEM(_productVO);
		sparePartBO.buildRelated(productOEMOther);

		//oem号 (如果是原厂件直接直接赋为备件编号，如果是副厂件则拼接其对应所有原厂件编号再赋值)
		if (productVO.getType() == 1){
			sparePartBO.setOeCode(productVO.getCode());
		}
		else{
			String oeCode = "";
			for (int i = 1 ; i <= productOEMOther.size() ; i++){
				if (i == productOEMOther.size()){
					oeCode = oeCode + productOEMOther.get(i - 1).getCode();
					break;
				}
				oeCode = oeCode + productOEMOther.get(i - 1).getCode() + ",";
			}
			sparePartBO.setOeCode(oeCode);
		}

		return GsonUtil.toJson(sparePartBO,true);
	}

	@Override
	public String findApplicableVehicleModel(String id, int pageIndex,
			int pageSize) {
		VehicleTypeVO _vehicleTypeVO = new VehicleTypeVO();
		_vehicleTypeVO.setProductId(id);

		//如果页码小于或等于0统一设成第一页
		if(pageIndex <= 0){
			_vehicleTypeVO.setPageNo(1l);
		}else{
			_vehicleTypeVO.setPageNo((long)pageIndex);
		}
		
		//只录入大于0的数目
		if (pageSize > 0){
			_vehicleTypeVO.setPageSize((long)pageSize);
		}

		//适用车型查询
		_vehicleTypeVO.doPage(vehicleTypeDAO.findVehicleTypesCountForMobile(_vehicleTypeVO),
				_vehicleTypeVO.getPageNo(), _vehicleTypeVO.getPageSize());
    	List<VehicleTypeVO> vehicleTypes = vehicleTypeDAO.findVehicleTypesForMobile(_vehicleTypeVO);

    	//如果找不到相应的适用车型，GsonUtil会将null转为{}，将空的容器转为[]
		if (vehicleTypes == null || vehicleTypes.size() == 0){
			return GsonUtil.toJson(vehicleTypes);
		}

		buildApplicableVehicleModels(vehicleTypes); //构造待转对象applicableVehicleModels
    	
		return GsonUtil.toJson(applicableVehicleModels,true);
	}

	// TODO	最近浏览备件暂时给的首页“我的关注”模块中最近搜索的oem信息
	@Override
	public String findLastVisited() {
		FocusEntity focusEntity = new FocusEntity();
		focusEntity.setCustomerID("0010590487899");		//因为暂时没有参数传进来，而这块信息又暂时决定从“我的关注”模块去命拿，所以这里先给个死的
		focusEntity.setLimitNum(5);						//暂时设定返回数据为5条
		
		//查关注的oem
		focusEntity.setFocusType(3);
		List<FocusEntity> OEMSearchList = focusDAO.findFocusByCustomerID(focusEntity);
    	
		//如果找不到相应的oem号，GsonUtil会将null转为{}，将空的容器转为[]
		if (OEMSearchList == null || OEMSearchList.size() == 0){
			return GsonUtil.toJson(OEMSearchList);
		}
		
		buildLastVisitedSpareParts(OEMSearchList);		//构造待转对象
		
		return GsonUtil.toJson(lastVistedSpareParts,true);
	}

	public void buildApplicableVehicleModels(List<VehicleTypeVO> vehicleTypes)
	{
		applicableVehicleModels = Lists.newArrayList();
		Map<String,String> vehicleModel = null;
		for (VehicleTypeVO vehicleTypeVO : vehicleTypes){
			ProductCategoryVO productCategoryVO = productCategoryDAO.findProductCategoryByMainID(vehicleTypeVO.getProductCategoryParentId());
			vehicleModel = Maps.newHashMap();
			vehicleModel.put("modelName", vehicleTypeVO.getName());
			vehicleModel.put("brandName", productCategoryVO.getName());
			applicableVehicleModels.add(vehicleModel);
		}
	}
	
	public void buildLastVisitedSpareParts(List<FocusEntity> OEMSearchList){
		lastVistedSpareParts = Lists.newArrayList();
		Map<String,String> sparePart = null;
		for (FocusEntity focus : OEMSearchList){
			//根据我关注的oem号查出相应备件信息
			ProductVO product = productDAO.findProductByCode(focus.getReferenceID());
			
			sparePart = Maps.newHashMap();
			sparePart.put("partId", product.getMainID());
			sparePart.put("partCode", product.getCode());
			sparePart.put("partName", product.getName());
			
			lastVistedSpareParts.add(sparePart);
		}
	}
	
}
