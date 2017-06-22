package bz.sunlight.web.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bz.sunlight.domain.business.VehicleBrandsAllBO;
import bz.sunlight.domain.business.VehicleBrandsByIdBO;
import bz.sunlight.domain.business.VehicleBrandsByIdBO.Children;
import bz.sunlight.domain.business.VehicleBrandsByIdBO.Series;
import bz.sunlight.domain.business.VehicleBrandsHotBO;
import bz.sunlight.web.service.VehicleBrandsService;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.kpluswebup.web.product.dao.ProductCategoryDAO;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpuswebup.comom.util.GsonUtil;
import com.kpuswebup.comom.util.PropertiesUtil;

@Service
public class VehicleBrandsServiceImpl implements VehicleBrandsService {

	@Autowired
	private ProductCategoryDAO productCategoryDAO;
	
	
	
	public VehicleBrandsServiceImpl() {
		super();
	}

	public VehicleBrandsServiceImpl(ProductCategoryDAO productCategoryDAO) {
		super();
		this.productCategoryDAO = productCategoryDAO;
		
	}
	
	VehicleBrandsByIdBO vehicleBrandsBO = null;

	// TODO 目前没有这个功能，暂时获得所有汽车的品牌信息；
	@Override
	public String findHotBrands() {
		
		List<ProductCategoryVO> list = productCategoryDAO
				.findProductOneLevelTparts();
		VehicleBrandsHotBO vehicleBrandsHotBO= new VehicleBrandsHotBO();
		vehicleBrandsHotBO.bulidHot(list);
		String hotModel = GsonUtil.toJson(vehicleBrandsHotBO,true);
		return hotModel;

	}
    
	@Override
	public String findAllBrands() {
		
		List<ProductCategoryVO> list = productCategoryDAO
				.findProductOneLevelTparts();
		VehicleBrandsAllBO vehicleBrandsAllBO=new VehicleBrandsAllBO();
		vehicleBrandsAllBO.bulidAll(list);
		vehicleBrandsAllBO.bulidHot(list);
		String allBrand = GsonUtil.toJson(vehicleBrandsAllBO,true);
		return allBrand;

	}

	/*
	 * 
	 * 根据mainID查找指定的汽车品牌 id:汽车品牌id
	 */
	@Override
	public String findBrandById(String id) {
		VehicleBrandsByIdBO vehicleBrandsBO = new VehicleBrandsByIdBO();
		ProductCategoryVO productCategoryVO = productCategoryDAO
				.findProductCategoryByMainID(id);
		if(productCategoryVO == null)
			return null;
		vehicleBrandsBO.buildBrand(productCategoryVO);
		// 实例化内部类：Children
		List<ProductCategoryVO> list = productCategoryDAO
				.findProductCatByParentID(id);
		final String ONE_MODEL = "1系",TWO_MODEL = "2系",THREE_MODEL = "3系",FOUR_MODEL = "4系",FIVE_MODEL = "5系",
				SIX_MODEL = "6系",SEEVEN_MODEL = "7系",OTHER_MODEL = "其它";
		VehicleBrandsByIdBO.Children children = null;
		List<Children> childrenList = new ArrayList<Children>();
		Multimap<String,List<Series>> groupMultimap = ArrayListMultimap.create(); 
		for (ProductCategoryVO productCategoryVO2 : list) {
			children = new VehicleBrandsByIdBO().new Children();
			if (productCategoryVO2.getName().contains("1'")) {
				buildChilds(vehicleBrandsBO, children, productCategoryVO2);
				groupMultimap.put(ONE_MODEL, children.getSeries());
			}else if (productCategoryVO2.getName().contains("2'")) {
				buildChilds(vehicleBrandsBO, children, productCategoryVO2);
				groupMultimap.put(TWO_MODEL, children.getSeries());
			}else if (productCategoryVO2.getName().contains("3'")) {
				buildChilds(vehicleBrandsBO, children, productCategoryVO2);
				groupMultimap.put(THREE_MODEL, children.getSeries());
			} else if (productCategoryVO2.getName().contains("4'")) {
				buildChilds(vehicleBrandsBO, children, productCategoryVO2);
				groupMultimap.put(FOUR_MODEL, children.getSeries());
			} else if (productCategoryVO2.getName().contains("5'")) {
				buildChilds(vehicleBrandsBO, children, productCategoryVO2);
				groupMultimap.put(FIVE_MODEL, children.getSeries());
			} else if (productCategoryVO2.getName().contains("6'")) {
				buildChilds(vehicleBrandsBO, children, productCategoryVO2);
				groupMultimap.put(SIX_MODEL, children.getSeries());
			} else if (productCategoryVO2.getName().contains("7'")) {
				buildChilds(vehicleBrandsBO, children, productCategoryVO2);
				groupMultimap.put(SEEVEN_MODEL, children.getSeries());
			} else {
				buildChilds(vehicleBrandsBO, children, productCategoryVO2);
				groupMultimap.put(OTHER_MODEL, children.getSeries());
			}
		}
	    Set<String> s = groupMultimap.keySet();
	    List<String> keyList = Lists.newArrayList();
	    keyList.addAll(s);
	    Collections.sort(keyList);
	    List<Series> seriesFinals = null;
	    for (String k : keyList) {
	    	children = new VehicleBrandsByIdBO().new Children();
	    	Collection<List<Series>> c = groupMultimap.get(k);
	    	seriesFinals = Lists.newArrayList();
	    	for (List<Series> seriesCollection : c) {
	    		seriesFinals.addAll(seriesCollection);
			}
	    	children.setSeries(seriesFinals);
	    	children.setGroupName(k);
	    	childrenList.add(children);
		}
		vehicleBrandsBO.setChildren(childrenList);

		String json = GsonUtil.toJson(vehicleBrandsBO,true);
		return json;
	}

	private void buildChilds(VehicleBrandsByIdBO vehicleBrandsBO,
			VehicleBrandsByIdBO.Children children,
			ProductCategoryVO productCategoryVO2) {
		List<Series> seriesList;
		VehicleBrandsByIdBO.Series series;
		series = vehicleBrandsBO.new Series();
		series.setSeriesId(productCategoryVO2.getMainID());
		series.setSeriesName(productCategoryVO2.getName());
		seriesList = new ArrayList<Series>();
		seriesList.add(series);
		children.setSeries(seriesList);
	}

}
