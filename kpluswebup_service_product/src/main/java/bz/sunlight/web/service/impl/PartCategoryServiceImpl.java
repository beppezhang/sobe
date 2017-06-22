package bz.sunlight.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bz.sunlight.domain.business.PartCategoryBO;
import bz.sunlight.domain.business.PartCategoryToMultipleBO;
import bz.sunlight.domain.business.PartCategoryToPartBO;
import bz.sunlight.domain.business.PartCategoryToSingleBO;
import bz.sunlight.web.service.PartCategoryService;

import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.partscategory.dao.PartsCategoryDAO;
import com.kpluswebup.web.partscategory.dao.VehicletypePartscategoryDAO;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.vo.PartsCategoryVo;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.VehicletypePartscategoryEntity;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.GsonUtil;
import com.kpuswebup.comom.util.PropertiesUtil;

@Service
public class PartCategoryServiceImpl implements PartCategoryService {

	@Autowired
	private VehicletypePartscategoryDAO vehicletypePartCategoryDAO;

	@Autowired
	private PartsCategoryDAO partsCategoryDAO;

	@Autowired
    private ProductService productService;
	
	@Override
	public String findPartCategoriesByVehicleModel(String vehicleModelId) {
		//根据车型id找到第一层备件分类
		VehicletypePartscategoryEntity vpEntity = new VehicletypePartscategoryEntity();        
        vpEntity.setVehicletypeId(vehicleModelId);
        vpEntity.setFlevel(1);
        List<VehicletypePartscategoryEntity> partsCategoryRoots = 
        		vehicletypePartCategoryDAO.findByPartscategorysByVehicletype(vpEntity);

    	//如果找不到对应的备件分类直接返回空
		if (partsCategoryRoots == null || partsCategoryRoots.size() == 0){
			return null;
		}

		//将数据给到待转目标 partCategories
		List<PartCategoryBO> partCategories = new ArrayList<PartCategoryBO>();
        for (VehicletypePartscategoryEntity partCategory : partsCategoryRoots){
        	PartCategoryBO partCategoryBO = new PartCategoryBO();
        	partCategoryBO.setCategoryId(partCategory.getPartsCategoryId());
        	partCategoryBO.setCategoryCode(partCategory.getCode());
        	partCategoryBO.setCategoryName(partCategory.getName());
        	
        	// TODO 暂时没有相应备件分类图标,这里先不进行赋值
        	//partCategoryBO.setIcon();
        	
        	partCategories.add(partCategoryBO);
        }
        
        return GsonUtil.toJson(partCategories,true);
	}

	@Override
	public String findPartCategroyInfo(String partCategoryId,
			String vehicleModelId) {
		//录入车型id
		VehicletypePartscategoryEntity vpEntity = new VehicletypePartscategoryEntity();        
		vpEntity.setVehicletypeId(vehicleModelId);
		
		//先根据分类id找到分类层级,如果无法找到对应备件分类直接返回空
		PartsCategoryVo partsCategoryVo = partsCategoryDAO.findPartsCategoryByMainID(partCategoryId);
		if (partsCategoryVo == null) {
			return null;
		}
		
		//如果给出备件大类id，则查出其下面一层备件小类进行转化
		if (partsCategoryVo.getFlevel().equals(1)){
			vpEntity.setFlevel(2);
            vpEntity.setAncestorID(partCategoryId);
            List<VehicletypePartscategoryEntity> partsCategoryChilds = 
            		vehicletypePartCategoryDAO.findByPartscategorysByVehicletype(vpEntity);
            
            //构造待转目标
            PartCategoryToMultipleBO partCategoryToMultipleBO = new PartCategoryToMultipleBO();
            partCategoryToMultipleBO.setCategoryName(partsCategoryVo.getName());
            partCategoryToMultipleBO.buildChildren(partsCategoryChilds);
            
            return GsonUtil.toJson(partCategoryToMultipleBO,true);
		}
		
		//如果给出备件小类id,可分三种情况（BMW两种，BENZ一种）
		if (partsCategoryVo.getFlevel().equals(2)){
            vpEntity.setFlevel(3);
            vpEntity.setAncestorID(partCategoryId);
            List<VehicletypePartscategoryEntity> partsCategoryThirds = 
            		vehicletypePartCategoryDAO.findByPartscategorysByVehicletype(vpEntity);
            
            //如果能继续查到第三层分类，则表明是BMW方面的数据，可分两种情况继续讨论
            if (partsCategoryThirds != null && partsCategoryThirds.size() > 0){
				if (partsCategoryThirds.size() > 1) {
					//如果能查到不止一个第三层分类则返回一个子分类集合的转化结果
		            PartCategoryToMultipleBO partCategoryToMultipleBO = new PartCategoryToMultipleBO();
		            partCategoryToMultipleBO.setCategoryName(partsCategoryVo.getName());
		            partCategoryToMultipleBO.buildChildren(partsCategoryThirds);
		            
		            return GsonUtil.toJson(partCategoryToMultipleBO,true);
				} else {
            		//如果第三层分类数量为1，直接查询选中第三层分类，显示第三层分类下面的备件
            		String partsCategoryIdThird = partsCategoryThirds.get(0).getMainID();
                    PartsCategoryVo partsCategoryVoPartsThird = partsCategoryDAO.findPartsCategoryByMainID(partsCategoryIdThird);

                    //根据第三层分类id及车型id查到对应备件
                	List<ProductVO> parts = findPartsByPartCategoryAndVehicelModel(vehicleModelId, partsCategoryIdThird);
                    
                    PartCategoryToSingleBO partCategoryToSingleBO = new PartCategoryToSingleBO();
                    partCategoryToSingleBO.setCategoryName(partsCategoryVo.getName());
                    partCategoryToSingleBO.buildChildren(partsCategoryVoPartsThird, parts);
                    
    	            return GsonUtil.toJson(partCategoryToSingleBO,true);
				}

            } else{
                //如果查不到第三层备件分类，则根据车型id和备件分类id查出相应备件
            	List<ProductVO> parts = findPartsByPartCategoryAndVehicelModel(vehicleModelId, partCategoryId);
            	
            	//根据Lucene的搜索结果构造待转目标
                PartCategoryToPartBO partCategoryToPartBO = new PartCategoryToPartBO();
                partCategoryToPartBO.setCategoryName(partsCategoryVo.getName());
                partCategoryToPartBO.buildChildren(parts);

                return GsonUtil.toJson(partCategoryToPartBO,true);
            }
		}

		//如果给出第三层分类id，则直接查出相关备件
        if (partsCategoryVo.getFlevel().equals(3)){
        	//根据车型id和备件分类id查相关备件
        	List<ProductVO> parts = findPartsByPartCategoryAndVehicelModel(vehicleModelId, partCategoryId);
        	
        	//根据Lucene的搜索结果构造待转目标
            PartCategoryToPartBO partCategoryToPartBO = new PartCategoryToPartBO();
            partCategoryToPartBO.setCategoryName(partsCategoryVo.getName());
            partCategoryToPartBO.buildChildren(parts);

            return GsonUtil.toJson(partCategoryToPartBO,true);
        }
		
		return null;
	}
	
	public List<ProductVO> findPartsByPartCategoryAndVehicelModel(String vehicleModelId,String partCategoryId){
        
		ProductDTO productDTO = new ProductDTO();
        productDTO.setVehicleTypeId(vehicleModelId);
        productDTO.setPartsCategoryId(partCategoryId);

        productDTO.setSearchModel(Constant.SEARCHMODEL_PRODUCT);	//设置搜索方式为常规搜索
        productDTO.setPageSize(10l);								//暂时设置展示10个

        Integer lucene = Integer.parseInt(PropertiesUtil.getInstanse().getString("lucene.flag"));
        productDTO.setPageNoCountForLuncene(Integer.parseInt(PropertiesUtil.getInstanse().getString("lucene.pageno.normal")));
        
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
