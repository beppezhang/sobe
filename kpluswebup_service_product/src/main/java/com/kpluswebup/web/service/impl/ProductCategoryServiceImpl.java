package com.kpluswebup.web.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.domain.ProductCategoryDTO;
import com.kpluswebup.web.product.dao.ProductCategoryDAO;
import com.kpluswebup.web.service.ProductCategoryService;
import com.kpluswebup.web.vo.FirtSecondThirdCategoryVO;
import com.kpluswebup.web.vo.ProductCategoryVO;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    public List<ProductCategoryVO> findAllProductCategory() {

        return productCategoryDAO.findALLProductCategory();
    }

    //TODO缓存
    public List<ProductCategoryVO> findProductOneLevel() {
        List<ProductCategoryVO> list = productCategoryDAO.findProductOneLevel();
        for (ProductCategoryVO productCategoryVO : list) {
            List<ProductCategoryVO> list2 = productCategoryDAO.findProductCatByParentID(productCategoryVO.getMainID());
            productCategoryVO.setLevelTwo(list2);
            for (ProductCategoryVO productCategoryVO2 : list2) {
              /*  Map<String, List<ProductCategoryVO>> levelThree=new HashMap<String, List<ProductCategoryVO>>();
                List<ProductCategoryVO> list3 = productCategoryDAO.findProductCatByParentID(productCategoryVO2.getMainID());
                levelThree.put(productCategoryVO2.getMainID(), list3);
                productCategoryVO2.setLevelThree(levelThree);*/
                List<ProductCategoryVO> list3 = productCategoryDAO.findProductCatByParentID(productCategoryVO2.getMainID());
                productCategoryVO2.setLevelThre(list3);
           }
        }
        return list;
    }
    /**
     * sxc
     */
    @Override
    public List<BrandModel> findProductOneLevelTparts() {
        List<ProductCategoryVO> list = productCategoryDAO.findProductOneLevelTparts();
        List<BrandModel> brandModels = this.buildBrandModel();
        for (ProductCategoryVO productCategoryVO : list) {
            List<ProductCategoryVO> list2 = productCategoryDAO.findProductCatByParentIDTparts(productCategoryVO.getMainID());
            productCategoryVO.setLevelTwo(list2);
//            for (ProductCategoryVO productCategoryVO2 : list2) {
//              /*  Map<String, List<ProductCategoryVO>> levelThree=new HashMap<String, List<ProductCategoryVO>>();
//                List<ProductCategoryVO> list3 = productCategoryDAO.findProductCatByParentID(productCategoryVO2.getMainID());
//                levelThree.put(productCategoryVO2.getMainID(), list3);
//                productCategoryVO2.setLevelThree(levelThree);*/
//                List<ProductCategoryVO> list3 = productCategoryDAO.findProductCatByParentIDTparts(productCategoryVO2.getMainID());
//                productCategoryVO2.setLevelThre(list3);
//           }
            
        }
        
        for (BrandModel brandModel : brandModels) {
        	for (ProductCategoryVO productCategoryVO : list) {
    			if(brandModel.getCode().equals(productCategoryVO.getPinyin()))
    			{
    				brandModel.getBrands().add(productCategoryVO);
    			}        		
        	}
		}        
        
        return brandModels;
    }
    private List<BrandModel> buildBrandModel()
    {
 
    	List<String> letters = productCategoryDAO.findBrandPinyin();
    	List<BrandModel> brandModels = new ArrayList<BrandModel>();
    	BrandModel brandModel = null;
    	for (String str : letters) {
    		brandModel = new BrandModel();
    		brandModel.setCode(str);
    		brandModels.add(brandModel);
		}
    	
    	return brandModels;
    }

    public Boolean isDeleteProductCategory(String mainID) {
        try {
            productCategoryDAO.deleteProductCategoryByMainID(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addProductCategory(ProductCategoryDTO productCategoryDTO) {
        productCategoryDAO.insertProductCategory(productCategoryDTO);

    }

    public List<ProductCategoryVO> findProductCatByParentID(String mainID) {

        return productCategoryDAO.findProductCatByParentID(mainID);
    }

    public ProductCategoryVO findProductCategoryByMainID(String mainID) {
        notNull(mainID, "mainID is null");
        return productCategoryDAO.findProductCategoryByMainID(mainID);
    }

    public void editProductCategory(ProductCategoryDTO productCategoryDTO) {
        notNull(productCategoryDTO, "productCategoryDTO is null");
        notNull(productCategoryDTO.getMainID(), "productCategoryDTO.getMainID() is null");
        productCategoryDAO.updateProductCategoryByMainID(productCategoryDTO);
    }

    @Override
    public List<ProductCategoryVO> findProductCatOneLevel() {
        return productCategoryDAO.findProductOneLevel();
    }

	@Override
	public List<ProductCategoryVO> findProductCatByParentIDForSupplier(
			String parentMainID) {
		return productCategoryDAO.findProductCatByParentIDForSupplier(parentMainID);
	}

	@Override
	public List<FirtSecondThirdCategoryVO> findFirtSecondThirdCategoryVOByProductID(
			String productID) {
		return productCategoryDAO.findFirtSecondThirdCategoryVOByProductID(productID);
	}

    @Override
    public List<ProductCategoryVO> findProductCategoryOneLevel() {
        return productCategoryDAO.findProductCatOneLevel();
    }

	@Override
	public List<ProductCategoryVO> findProductOneLevelTpartsAdmin() {
        List<ProductCategoryVO> list = productCategoryDAO.findProductOneLevelTparts();
        List<BrandModel> brandModels = this.buildBrandModel();
        for (ProductCategoryVO productCategoryVO : list) {
            List<ProductCategoryVO> list2 = productCategoryDAO.findProductCatByParentIDTparts(productCategoryVO.getMainID());
            productCategoryVO.setLevelTwo(list2);
//            for (ProductCategoryVO productCategoryVO2 : list2) {
//              /*  Map<String, List<ProductCategoryVO>> levelThree=new HashMap<String, List<ProductCategoryVO>>();
//                List<ProductCategoryVO> list3 = productCategoryDAO.findProductCatByParentID(productCategoryVO2.getMainID());
//                levelThree.put(productCategoryVO2.getMainID(), list3);
//                productCategoryVO2.setLevelThree(levelThree);*/
//                List<ProductCategoryVO> list3 = productCategoryDAO.findProductCatByParentIDTparts(productCategoryVO2.getMainID());
//                productCategoryVO2.setLevelThre(list3);
//           }
        }
		return list;
	}
	
	@Override
	public ProductCategoryVO findProductCategoryById(String mainID) {
		return productCategoryDAO.findProductCategoryById(mainID);
	}
}
