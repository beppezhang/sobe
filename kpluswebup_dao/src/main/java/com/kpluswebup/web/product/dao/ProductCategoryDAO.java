package com.kpluswebup.web.product.dao;

import java.util.List;

import com.kpluswebup.web.domain.ProductCategoryDTO;
import com.kpluswebup.web.vo.FirtSecondThirdCategoryVO;
import com.kpluswebup.web.vo.ProductCategoryVO;

public interface ProductCategoryDAO {

    /**
     * @date 2014年11月10日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductCategoryVO> findALLProductCategory();

    /**
     * @date 2014年11月10日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductCategoryVO> findProductOneLevel();


    
    /**
     * 根据MaiID查询下级
     * 
     * @date 2014年11月10日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductCategoryVO> findProductCatByParentID(String mainID);    
    

    /**
     * @date 2014年11月10日
     * @author zhuhp
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public void deleteProductCategoryByMainID(String mainID);

    /**
     * @date 2014年11月11日
     * @author zhuhp
     * @param productCategoryDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertProductCategory(ProductCategoryDTO productCategoryDTO);

    /**
     * @date 2014年11月11日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ProductCategoryVO findProductCategoryByMainID(String mainID);

    /**
     * @date 2014年11月11日
     * @author zhuhp
     * @param productCategoryDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateProductCategoryByMainID(ProductCategoryDTO productCategoryDTO);
    
    /**
     * 
     * @date 2015年5月13日
     * @author moo
     * @param parentMainID
     * @return
     * @return List<ProductCategoryVO>
     * @since JDK 1.6
     * @Description
     */
	public List<ProductCategoryVO> findProductCatByParentIDForSupplier(
			String parentMainID);
	
	/**
	 * 
	 * @date 2015年5月14日
	 * @author moo
	 * @param productID
	 * @return
	 * @return List<FirtSecondThirdCategoryVO>
	 * @since JDK 1.6
	 * @Description 查询产品所属的一级二级三级类目名
	 */
	public List<FirtSecondThirdCategoryVO> findFirtSecondThirdCategoryVOByProductID(
			String productID);
	
	/**
	 * 查询推荐导航
	 * @date 2015年5月19日
	 * @author Administrator
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<ProductCategoryVO> findProductCatOneLevel();
	
	
	//*****************************************************sxc
	
    /**
     * @date 2015年9月22日
     * @author sxc
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductCategoryVO> findProductOneLevelTparts();    
    
    
    /**
     * @date 2015年9月22日
     * @author sxc
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductCategoryVO> findProductCatByParentIDTparts(String mainID);	
    
	
    public List<String> findBrandPinyin();
	
    
    public ProductCategoryVO findProductCategoryById(String mainID);
	
	public ProductCategoryVO findProductCategoryByVehicleTypeId(String vehicleTypeId);
}
