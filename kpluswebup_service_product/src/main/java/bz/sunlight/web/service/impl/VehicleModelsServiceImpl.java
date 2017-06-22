package bz.sunlight.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bz.sunlight.domain.business.KeyValue;
import bz.sunlight.domain.business.VehicleModelsBO;
import bz.sunlight.domain.business.VehicleModelsBO.GroupedParam;
import bz.sunlight.web.service.VehicleModelsService;

import com.kpluswebup.web.customer.dao.FocusDAO;
import com.kpluswebup.web.product.dao.ProductCategoryDAO;
import com.kpluswebup.web.service.PartsCategoryService;
import com.kpluswebup.web.service.VehicleTypeService;
import com.kpluswebup.web.vehicle.dao.VehicleTypeDAO;
import com.kpluswebup.web.vo.FocusEntity;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.VehicleTypeVO;
import com.kpuswebup.comom.util.GsonUtil;

// TODO  目前数据库中没有相应参数的数据，除了品牌和车型其余参数都是写死，，以后实现从数据库查找数据;
@Service
public class VehicleModelsServiceImpl implements VehicleModelsService {

	@Autowired
	private ProductCategoryDAO productCategoryDAO;
	
	@Autowired
	private VehicleTypeDAO vehicleTypeDAO;
	
	@Autowired
	private VehicleTypeService vehicleTypeService;
	
	@Autowired
    private PartsCategoryService partsCategoryService;
	
	@Autowired 
	private FocusDAO  focusDAO;
	
	
	
	@Override
	public String findVehicleModelsByMainId(String mainID) {
		    VehicleModelsBO vehicleModelsBO=new VehicleModelsBO();
			List<GroupedParam> vtList=new ArrayList<GroupedParam>();
			ProductCategoryVO productCategoryVO=productCategoryDAO.findProductCategoryByVehicleTypeId(mainID);
			VehicleTypeVO vehicleTypeVO=vehicleTypeDAO.findByMainID(mainID);
			if(productCategoryVO!=null && vehicleTypeVO!=null){
				KeyValue keyValue1=new KeyValue();
				KeyValue keyValue2=new KeyValue();
				KeyValue keyValue3=new KeyValue();
				KeyValue keyValue4=new KeyValue();
				keyValue1.setKey("品牌");
				keyValue1.setValue(productCategoryVO.getName());
				keyValue2.setKey("车型");
				keyValue2.setValue(vehicleTypeVO.getName());
				keyValue3.setKey("生产开始年月");
				keyValue3.setValue("2012/07");
				keyValue4.setKey("生产结束年份");
				keyValue4.setValue("2015/07");
				List<KeyValue> list1=new ArrayList<KeyValue>();
				list1.add(keyValue1);
				list1.add(keyValue2);
				list1.add(keyValue3);
				list1.add(keyValue4);
				VehicleModelsBO.GroupedParam groupedParam1=vehicleModelsBO.new GroupedParam();
				groupedParam1.setParameters(list1);
				groupedParam1.setGroupName("基本信息");
				
				KeyValue keyValue5=new KeyValue();
				KeyValue keyValue6=new KeyValue();
				
				keyValue5.setKey("车身类型");
				keyValue5.setValue("Couple");
				keyValue6.setKey("驱动方式");
				keyValue6.setValue("Front Wheel Drive");
				List<KeyValue> list2=new ArrayList<KeyValue>();
				list2.add(keyValue5);
				list2.add(keyValue6);
				VehicleModelsBO.GroupedParam groupedParam2=vehicleModelsBO.new GroupedParam();
				groupedParam2.setParameters(list2);
				groupedParam2.setGroupName("车身信息");
				
				KeyValue keyValue7=new KeyValue();
				KeyValue keyValue8=new KeyValue();
				KeyValue keyValue9=new KeyValue();
				KeyValue keyValue10=new KeyValue();
				
				keyValue7.setKey("功率(千瓦)");
				keyValue7.setValue("51");
				keyValue8.setKey("功率(马力)");
				keyValue8.setValue("70");
				keyValue9.setKey("气缸");
				keyValue9.setValue("4");
				keyValue10.setKey("制动系统");
				keyValue10.setValue("51");
				List<KeyValue> list3=new ArrayList<KeyValue>();
				list3.add(keyValue7);
				list3.add(keyValue8);
				list3.add(keyValue9);
				list3.add(keyValue10);
				VehicleModelsBO.GroupedParam groupedParam3=vehicleModelsBO.new GroupedParam();
				groupedParam3.setParameters(list3);
				groupedParam3.setGroupName("技术数据");
				
				vtList.add(groupedParam1);
				vtList.add(groupedParam2);
				vtList.add(groupedParam3);
				VehicleModelsBO.Image image =vehicleModelsBO.new Image();
				
				String picUrlPrefix=vehicleModelsBO.getPicUrlPrefix();
				String url=picUrlPrefix+"/images/u622.jpg";
				image.setUrl(url);
			    List<VehicleModelsBO.Image> urlList=new ArrayList<VehicleModelsBO.Image>();
			    urlList.add(image);
			    vehicleModelsBO.setGroupedParams(vtList);
			    vehicleModelsBO.setImages(urlList);
			    vehicleModelsBO.setModelName(vehicleTypeVO.getName());
			    vehicleModelsBO.setModelId(vehicleTypeVO.getMainID());
			    String json=GsonUtil.toJson(vehicleModelsBO,true);
			    return json;
			}else{
				return null;
			}
			

       
	}
	// TODO 最近浏览车型,用户的id暂时写死
	public String findLastVisited(){
		FocusEntity focusEntity=new FocusEntity();
		focusEntity.setCustomerID("0010590487899");    //暂定的用户ID写死；
		focusEntity.setLimitNum(5);                    //暂时显示5条的浏览车型
		
		//查找关注车型
		focusEntity.setFocusType(1);                   // 1表示关注类型为车型
		List<FocusEntity> typeSearchList=focusDAO.findFocusByCustomerID(focusEntity);
		if(typeSearchList == null || typeSearchList.size() == 0){
			return GsonUtil.toJson(typeSearchList);
		}
		
		List<Map<String,String>> lastVisitedVehicleTypes=getLastVisitedVehicleTypes(typeSearchList);
		
		return GsonUtil.toJson(lastVisitedVehicleTypes, true);
			
	}
	
	public List<Map<String,String>> getLastVisitedVehicleTypes(List<FocusEntity> typeSearchList){
		List<Map<String,String>> lastVisitedVehicleTypes=new ArrayList<Map<String,String>>();
		Map<String,String> vehicleType=null;
		for(FocusEntity focusEntity : typeSearchList){
			//根据referenceID查找车型
			VehicleTypeVO vehicleTypeVO = vehicleTypeDAO.findByMainID(focusEntity.getReferenceID());
			vehicleType=new HashMap<String, String>();
			vehicleType.put("modelId", vehicleTypeVO.getMainID());
			vehicleType.put("modelName", vehicleTypeVO.getName());
			lastVisitedVehicleTypes.add(vehicleType);
		}
		
		return lastVisitedVehicleTypes;
		
	}
	

}
