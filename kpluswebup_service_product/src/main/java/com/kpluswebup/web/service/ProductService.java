package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.vo.ItemDetailVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.ProductDetailVO;
import com.kpluswebup.web.vo.ProductVO;

public interface ProductService {

    public String addProduct(String mainID, String name, String virtual, String catalog, String picURL, String unit,
                             String description, String productTypeID, String brandID, String title,
                             String metaKeywords, String metaDescription, String currentOperator, String productDetail,
                             String isRecommend, String subTitle, String saleService, String productProp,
                             String productType, String isBuy, String isLowPrice,String isSales,String supplierID,String supplierCategoryID,String productStandPrice,Integer status);

    /**
     * @date 2014年11月13日
     * @author zhuhp
     * @param productDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductVO> findProductList(ProductDTO productDTO);

    /**
     * 历史记录
     * 
     * @return
     */
    public List<ProductVO> findHistoryProductsByPagination(ProductDTO productDTO);

    /**
     * 销售排行
     * 
     * @return
     */
    public List<ProductVO> getVolumeProducts();

    /**
     * @date 2014年11月13日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteProduct(String mainID);

    /**
     * @date 2014年11月13日
     * @author zhuhp
     * @param productID
     * @param productPropID
     * @param pTypePPropID
     * @param productPropValue
     * @since JDK 1.6
     * @Description
     */
    public void addProductDetail(String productID, String productPropID, String pTypePPropID,
                                 String[] productPropValue, String currentOperator);

    /**
     * 根据id查找产品
     * 
     * @date 2014年11月13日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ProductVO findProductByMainID(String mainID);

    /**
     * 根据id查询产品
     * 
     * @date 2015年1月9日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ProductVO findProductByMainIDAll(String mainID);

    /**
     * @date 2014年11月14日
     * @author zhuhp
     * @param mainID
     * @param name
     * @param virtual
     * @param catalog
     * @param picURL
     * @param unit
     * @param description
     * @param productTypeID
     * @param brandID
     * @param title
     * @param metaKeywords
     * @param metaDescription
     * @return
     * @since JDK 1.6
     * @Description
     */
    public String updateProduct(String mainID, String name, String virtual, String catalog, String picURL, String unit,
                                String description, String productTypeID, String brandID, String title,
                                String metaKeywords, String metaDescription, String currentOperator,
                                String productDetail, String isRecommend, String subTitle, String saleService,
                                String productProp, String productType, String isBuy, String isLowPrice,String isSales,String supplierID,String supplierCategoryID,String productStandPrice,Integer status);

    /**
     * @date 2014年11月24日
     * @author zhuhp
     * @param productMainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductDetailVO> findProductDetailList(String productMainID);

    /**
     * 修改状态
     * 
     * @date 2014年12月4日
     * @author zhuhp
     * @param mainID
     * @param status
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateProductStatus(String mainIDs, String status);

    /**
     * 产品、商品关联、类目联合搜索
     * 
     * @date 2014年12月24日
     * @author zhuhp
     * @param productDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductVO> searchProducItemCat(ProductDTO productDTO);

    public List<ProductVO> searchProductByIsRemmond();

    /**
     * 猜你喜欢
     * 
     * @date 2015年1月7日
     * @author yuanyuan
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductVO> findProductYourLove();

    /**
     * 产品、商品关联、类目联合查询 查询产品人气排行
     * 
     * @date 2015年4月29日
     * @author Administrator
     * @param productDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductVO> findProductSales(ProductDTO productDTO);

    /**
     * 天天低价
     * 
     * @date 2015年4月29日
     * @author Administrator
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductVO> findProductLowPrice();

    /**
     * 首页值得买
     * 
     * @date 2015年4月29日
     * @author Administrator
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductVO> findProductBuy();
    
    /**
     * 查询产品属性
     * @date 2015年6月11日
     * @author wanghehua
     * @param productID
     * @param pTypeIPropID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductDetailVO> findProductDetailPropValue(String productID,String pTypeIPropID);
    
    /**
     * 首页今日热买
     * @date 2015年6月21日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductVO> findProductIsSales();
    
    
    //******************************************************
    public List<ProductVO> findProductListTparts(ProductDTO productDTO);
    public List<ProductVO> findProductListByLucene(ProductDTO productDTO);


    /**
     * 根据id查找产品
     * 
     * @date 2015年10月22日
     * @author lby
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ProductVO findProductByMainIDNew(String mainID);
    
    /**
     * 根据Code产品编码查找产品
     * 
     * @date 2015年10月22日
     * @author lby
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ProductVO findProductByCode(String productCode);
    
    /**
     * 获取数据库日期
     * @return
     */    
    public String findDBDate();

    /**
     * 根据itemDTO中的supplierID,itemID及status查产品
     * 
     * @date 2015年11月6日
     * @author lby
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductVO> findProductBySupplierItem(ItemDTO itemDTO);

    /**
     * @date 2014年11月16日
     * @author lby
     * @param productDTO
     * @return
     * @since JDK 1.6
     * @Description 后台产品列表查询
     */
    public List<ProductVO> findProductListTpartsAdmin(ProductVO productVO);

    /**
     * 修改产品状态（改isDelete）
     * 
     * @date 2015年11月17日
     * @author lby
     * @param mainID
     * @param status
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateProductStatusTparts(String mainIDs, String status);

	public String updateProduct(String mainID, String name, String virtual, String catalog, String picURL, String unit,
            String description, String productTypeID, String brandID, String title,
            String metaKeywords, String metaDescription, String currentOperator,
            String productDetail, String isRecommend, String subTitle, String saleService,
            String productProp, String productType, String isBuy, String isLowPrice,String isSales,String supplierID,String supplierCategoryID,String productStandPrice,Integer status, String freightTemplateID);

    public String addProduct(String mainID, String name, String virtual, String catalog, String picURL, String unit,
            String description, String productTypeID, String brandID, String title,
            String metaKeywords, String metaDescription, String currentOperator, String productDetail,
            String isRecommend, String subTitle, String saleService, String productProp,
            String productType, String isBuy, String isLowPrice,String isSales,String supplierID,String supplierCategoryID,String productStandPrice,Integer status, String freightTemplateID);
    /**
     * 根据prodcutVo.type
     * 1根据原厂件对应的副厂件数据
     * 2根据副厂件对应的数据原厂件
     * @param productVo
     * @return
     */
    public List<ProductVO> findProductByOEM(ProductVO productVo);
}
