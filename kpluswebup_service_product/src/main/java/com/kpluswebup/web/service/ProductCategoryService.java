package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.domain.ProductCategoryDTO;
import com.kpluswebup.web.service.impl.BrandModel;
import com.kpluswebup.web.vo.FirtSecondThirdCategoryVO;
import com.kpluswebup.web.vo.ProductCategoryVO;

public interface ProductCategoryService {

    /**
     * @date 2014年11月10日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductCategoryVO> findAllProductCategory();

    /**
     * 一级类目
     * 
     * @date 2014年11月10日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductCategoryVO> findProductOneLevel();
    
    /**
     * 一级类目
     * 
     * @date 2015年9月20日
     * @author sxc
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<BrandModel> findProductOneLevelTparts();    
    

    /**
     * @date 2014年11月10日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean isDeleteProductCategory(String mainID);

    /**
     * @date 2014年11月11日
     * @author zhuhp
     * @param productCategoryDTO
     * @since JDK 1.6
     * @Description
     */
    public void addProductCategory(ProductCategoryDTO productCategoryDTO);

    /**
     * 根据mainID查询下级类目
     * 
     * @date 2014年11月11日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductCategoryVO> findProductCatByParentID(String mainID);

    /**
     * 根据mainID查询单个类目信息
     * 
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
    public void editProductCategory(ProductCategoryDTO productCategoryDTO);
    
    /**
     * 获取一级分类
     * @date 2015年1月20日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductCategoryVO> findProductCatOneLevel();
    
    /**
     * 
     * @date 2015年5月13日
     * @author moo
     * @param parentMainID
     * @return
     * @return List<ProductCategoryVO>
     * @since JDK 1.6
     * @Description 查询子类目
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
	 * @Description 查询产品一级二级三级类目名
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
    public List<ProductCategoryVO> findProductCategoryOneLevel();

    /**
     * 后台整车类目查询
     * 
     * @date 2015年11月18日
     * @author lby
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductCategoryVO> findProductOneLevelTpartsAdmin();
    
    public ProductCategoryVO findProductCategoryById(String mainID);
}
