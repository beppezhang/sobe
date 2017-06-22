package bz.sunlight.web.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bz.sunlight.domain.business.KeyValue;
import bz.sunlight.domain.business.KeywordsSearchResultBO;
import bz.sunlight.domain.business.OEMSearchResultBO;
import bz.sunlight.domain.business.SparePartBO;
import bz.sunlight.domain.business.VINSearchResultBO;
import bz.sunlight.domain.business.VehicleModelsBO;
import bz.sunlight.domain.business.VehicleModelsBO.GroupedParam;
import bz.sunlight.web.service.SearchService;

import com.kpluswebup.web.customer.dao.FocusDAO;
import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.partscategory.dao.PartsCategoryDAO;
import com.kpluswebup.web.product.dao.ProductCategoryDAO;
import com.kpluswebup.web.product.dao.ProductDAO;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.vehicle.dao.VehicleTypeDAO;
import com.kpluswebup.web.vo.FocusEntity;
import com.kpluswebup.web.vo.PartsCategoryVo;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.VehicleTypeVO;
import com.kpuswebup.comom.util.GsonUtil;
import com.kpuswebup.comom.util.PropertiesUtil;
import com.kpuswebup.comom.util.StringUtil;

@Service
public class SearchServiceImpl implements SearchService {

	//搜索方式
	private static final String KEYWORDS = "keywords";
	private static final String VIN = "vin";
	private static final String OEM  = "oem";

	//历史搜索指定场景
	private static final String HOME = "home";
	private static final String VEHICLEBRANDS  = "vehicleBrands";

	@Autowired
    private ProductService productService;

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private PartsCategoryDAO partsCategoryDAO;
	
	@Autowired
	private VehicleTypeDAO vehicleTypeDAO;
	
	@Autowired
	private ProductCategoryDAO productCategoryDAO;
	
	@Autowired
	private FocusDAO focusDAO;
	
	@Override
	public String cleanSearchHistory(String scenario, String type) {
		
		return null;
	}

	@Override
	public String findSearchHistory(String customerId, String scenario, String type) {
		// TODO 现阶段存在相关数据，暂时从“我的关注”模块拿数据
		FocusEntity focusEntity = new FocusEntity();
		focusEntity.setCustomerID(customerId);
		focusEntity.setLimitNum(10);

		List<String> searchHistoryResults = new ArrayList<String>();
		//HOME拿oem,vin,keywords(假数据)，VEHICLEBRANDS拿车型搜索历史
		if (scenario.equals(HOME)) {
			//OEM搜索历史
			if (type.equals(OEM)){
				focusEntity.setFocusType(3);
				List<FocusEntity> OEMSearchList = focusDAO.findFocusByCustomerID(focusEntity);
				
				for (FocusEntity focus : OEMSearchList) {
					searchHistoryResults.add(focus.getReferenceID());
				}
			}
			
			//VIN搜索历史
			if (type.equals(VIN)){
				focusEntity.setFocusType(2);
				List<FocusEntity> OEMSearchList = focusDAO.findFocusByCustomerID(focusEntity);
				
				for (FocusEntity focus : OEMSearchList) {
					searchHistoryResults.add(focus.getMyFocusInfo());
				}
			}
			
			//keywords搜索历史
			if (type.equals(KEYWORDS)){
				searchHistoryResults.add("螺母");
				searchHistoryResults.add("58");
				searchHistoryResults.add("塞子");
				searchHistoryResults.add("马达");
				searchHistoryResults.add("挡板");
				searchHistoryResults.add("用户手册");
				searchHistoryResults.add("盖罩");
				searchHistoryResults.add("气缸");
				searchHistoryResults.add("石棉");
				searchHistoryResults.add("制动器");
			}
		} else if (scenario.equals(VEHICLEBRANDS)) {
			//车型搜索历史
			focusEntity.setFocusType(1);
			List<FocusEntity> vehicleTypeSearchList = focusDAO.findFocusByCustomerID(focusEntity);
			
			for (FocusEntity focus : vehicleTypeSearchList) {
				searchHistoryResults.add(focus.getMyFocusInfo());
			}
		} else {
			return GsonUtil.toJson(null);
		}
		
		return GsonUtil.toJson(searchHistoryResults);
	}

	@Override
	public String findSearchResult(String type, String str, int pageIndex,
			int pageSize) {
		//分类搜索
		if (type.equals(KEYWORDS)) {
			List<ProductVO> parts = searchPartsByKeywords(str, pageIndex, pageSize);
			
			//如果查不到相应备件立即返回,GsonUtil会将null转为{}，将空的容器转为[]
			if (parts == null || parts.size() == 0){
				return GsonUtil.toJson(parts);
			}
			
			//构造结果集
			KeywordsSearchResultBO searchResult = new KeywordsSearchResultBO();
			searchResult.buildResult(parts);
			
			return GsonUtil.toJson(searchResult, true);
		} else if (type.equals(VIN)) {
			VehicleModelsBO VehicleModelsBO = searchVehicleModelByVIN(str);

			//查不到相应备件直接返回,GsonUtil会将null转为{}，将空的容器转为[]
			if (VehicleModelsBO == null){
				return GsonUtil.toJson(VehicleModelsBO);
			}
			
			//构造结果集
			VINSearchResultBO searchResult = new VINSearchResultBO();
			searchResult.setResult(VehicleModelsBO);
			
			return GsonUtil.toJson(searchResult, true);
		} else if (type.equals(OEM)) {
			SparePartBO sparePartBO = searchSparePartByOEM(str);

			//查不到相应备件直接返回,GsonUtil会将null转为{}，将空的容器转为[]
			if (sparePartBO == null){
				return GsonUtil.toJson(sparePartBO);
			}
			
			//构造结果集
			OEMSearchResultBO searchResult = new OEMSearchResultBO();
			searchResult.setResult(sparePartBO);
			
			return GsonUtil.toJson(searchResult, true);
		} else {
			return GsonUtil.toJson(null);
		}
		
	}

	public VehicleModelsBO searchVehicleModelByVIN(String vin){
		//根据vin码查出对应车型
		List<VehicleTypeVO> vehicleTypes = vehicleTypeDAO.findByVin(vin);
		VehicleTypeVO vehicleTypeVO = null;
		if(vehicleTypes == null || vehicleTypes.size() == 0) {
			return null;
		}else {
			vehicleTypeVO = vehicleTypes.get(0);
		}
		
		//查出对应分类(这里实际要用到的是上一层分类)
		ProductCategoryVO productCategoryVOChild = productCategoryDAO.findProductCategoryById(vehicleTypeVO.getProductCategoryId());
		ProductCategoryVO productCategoryVO = productCategoryDAO.findProductCategoryById(productCategoryVOChild.getParentID());
	    
		VehicleModelsBO mobileVehicleModelsBO = new VehicleModelsBO();
		
		List<GroupedParam> vtList=new ArrayList<GroupedParam>();
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
			VehicleModelsBO.GroupedParam groupedParam1=mobileVehicleModelsBO.new GroupedParam();
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
			VehicleModelsBO.GroupedParam groupedParam2=mobileVehicleModelsBO.new GroupedParam();
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
			VehicleModelsBO.GroupedParam groupedParam3=mobileVehicleModelsBO.new GroupedParam();
			groupedParam3.setParameters(list3);
			groupedParam3.setGroupName("技术数据");
			
			vtList.add(groupedParam1);
			vtList.add(groupedParam2);
			vtList.add(groupedParam3);
			VehicleModelsBO.Image image = mobileVehicleModelsBO.new Image();
			image.setUrl(mobileVehicleModelsBO.getPicUrlPrefix() + "/images/u622.jpg");
		    List<VehicleModelsBO.Image> urlList=new ArrayList<VehicleModelsBO.Image>();
		    urlList.add(image);
		    mobileVehicleModelsBO.setGroupedParams(vtList);
		    mobileVehicleModelsBO.setImages(urlList);
		    mobileVehicleModelsBO.setModelName(vehicleTypeVO.getName());
		    mobileVehicleModelsBO.setModelId(vehicleTypeVO.getMainID());
		}
		
		return mobileVehicleModelsBO;
	}
	
	public SparePartBO searchSparePartByOEM(String oem){
    	ProductDTO productDTO = new ProductDTO(); 
    	List<ProductVO> parts = null;
    	
    	if(StringUtil.isNotEmpty(oem))
    	{
            productDTO.setName(oem);

            productDTO.setSearchModel("oem");
            Integer lucene = Integer.parseInt(PropertiesUtil.getInstanse().getString("lucene.flag"));
            productDTO.setPageNoCountForLuncene(Integer.parseInt(PropertiesUtil.getInstanse().getString("lucene.pageno.normal")));	//lucene默认查出20条
            
            if(lucene==0)
            {
            	parts = productService.findProductListByLucene(productDTO);
            }else
            {
            	parts = productService.findProductListTparts(productDTO);	
            }
    	}else{
    		return null;
    	}
    	
    	//找到与备件编号与 oem 一致的备件
    	String productId = null;
    	if(parts != null && parts.size() > 0)
    	{
        	for (ProductVO productVO : parts) {
    			if(productVO.getCode().equals(oem))
    			{
    				productId = productVO.getMainID();
    				break;
    			}
    		}    	
    	}


    	//以下是 SparePartService 中的 findSparePartByMainID 方法的实现代码，由于参数上的限制和service之间尽量减少相互依赖，这里选择直接复制代码
		ProductVO productVO = productDAO.findProductByMainIDNew(productId);
		
		//如果找不到相关备件，流程不再继续，GsonUtil会将null转成{}
		if (productVO == null){
			return null;
		}

		SparePartBO sparePartBO = new SparePartBO();
		
		sparePartBO.setPartId(productId);
		sparePartBO.setPartCode(productVO.getCode());
		sparePartBO.setPartName(productVO.getName());
		sparePartBO.setBrandName(productVO.getBrandName());
		sparePartBO.setGeneric(true);			//???
		
		// TODO 因为数据上的原因，这里暂时第三层分类只取了一个（从数据库中查出来对应的有两个第三层备件类目）
		//备件分类
		List<PartsCategoryVo> partsCategoryList = partsCategoryDAO.findPartsCategoryByProductID(productId);
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

		return sparePartBO;
	}
	
	public List<ProductVO> searchPartsByKeywords(String keywords, int pageIndex,
			int pageSize){
        ProductDTO productDTO = new ProductDTO();
        
        //关键字较验
		if (StringUtil.isNotEmpty(keywords)) {
			try {
				if (StringUtil.isNotEmpty(keywords)
						&& keywords.equals(new String(keywords.getBytes("iso-8859-1"), "iso-8859-1"))) {
					keywords = new String(keywords.getBytes("iso-8859-1"),
							"utf-8");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			productDTO.setName(keywords);
		}

		//如果页码小于或等于0统一设成第一页
		if(pageIndex <= 0){
			productDTO.setPageNo(1l);
		}else{
			productDTO.setPageNo((long)pageIndex);
		}
		
		//只录入大于0的数目
		if (pageSize > 0){
			productDTO.setPageSize((long)pageSize);
		}

        productDTO.setSearchModel("product");
        Integer lucene = Integer.parseInt(PropertiesUtil.getInstanse().getString("lucene.flag"));
        productDTO.setPageNoCountForLuncene(Integer.parseInt(PropertiesUtil.getInstanse().getString("lucene.pageno.normal")));	//lucene默认查出20条
        
        List<ProductVO> parts = null;
        if(lucene==0)
        {
        	parts = productService.findProductListByLucene(productDTO);
        }else
        {
        	parts = productService.findProductListTparts(productDTO);	
        }

        return parts;
	}
}
