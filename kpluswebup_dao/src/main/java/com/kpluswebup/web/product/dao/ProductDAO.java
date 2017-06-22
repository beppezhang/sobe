package com.kpluswebup.web.product.dao;

import java.util.List;

import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.ItemDetailDTO;
import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.domain.ProductDetailDTO;
import com.kpluswebup.web.vo.ItemDetailVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.ProductDetailVO;
import com.kpluswebup.web.vo.ProductVO;

public interface ProductDAO {

    /**
     * @date 2014年11月8日
     * @author zhuhp
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteProductByMainID(String mainID);

    /**
     * @date 2014年11月8日
     * @author zhuhp
     * @param record
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer insertProduct(ProductDTO productDTO);

    /**
     * @date 2014年11月8日
     * @author zhuhp
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ProductVO findProductByMainID(String mainID);

    /**
     * 根据id获取产品详情
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
     * 分页查询
     * 
     * @date 2014年11月11日
     * @author zhuhp
     * @param productDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductVO> findProducByPagination(ProductDTO productDTO);

    /**
     * 浏览历史记录
     * 
     * @param productDTO
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
     * 总条数
     * 
     * @date 2014年11月11日
     * @author zhuhp
     * @param productDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findProducCount(ProductDTO productDTO);

    /**
     * 浏览记录总条数
     * 
     * @param productDTO
     * @return
     */
    public Long findHistoryProducCount(ProductDTO productDTO);

    /**
     * 产品、商品关联、类目联合查询 查询产品信息，和searchProducItemCatCount分页
     * 
     * @date 2014年12月24日
     * @author zhuhp
     * @param productDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductVO> searchProducItemCat(ProductDTO productDTO);

    /**
     * @date 2014年12月24日
     * @author zhuhp
     * @param productDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long searchProducItemCatCount(ProductDTO productDTO);

    /**
     * @date 2014年11月14日
     * @author zhuhp
     * @param productDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateProductByMainID(ProductDTO productDTO);

    /**
     * @date 2015年1月7日
     * @author yuanyuan
     * @return
     * @since JDK 1.6
     * @Description
     */
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

    public List<ProductDetailVO> findProductDetailPropValue(ProductDetailDTO productDetailDTO);

    /**
     * 
     * @date 2015年6月19日
     * @author moo
     * @param productDTO
     * @return
     * @return List<ProductVO>
     * @since JDK 1.6
     * @Description
     */
	public List<ProductVO> findProductSalesOnCat(ProductDTO productDTO);
	
	 
    /**
     * 首页今日热买
     * @date 2015年6月21日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductVO> findProductIsSales();
    
    //**************************************************sxc
    
    public List<ProductVO> findProductByPaginationTparts(ProductDTO productDTO);
    public Long findProductCountTparts(ProductDTO productDTO);
    /**
     * 仅整车车型
     * @param productDTO
     * @return
     */
    public List<ProductVO> findProductByPaginationVehicleTypeTparts(ProductDTO productDTO);
    public Long findProductOnlyVehicleTypeCountTparts(ProductDTO productDTO);
    /**
     * 仅配件
     * @param productDTO
     * @return
     */    
    public List<ProductVO> findProductByPaginationCategoryTparts(ProductDTO productDTO);
    
    /**
     * 仅关键字
     */
    public List<ProductVO> findProductByPaginationByNameTparts(ProductDTO productDTO);
    
    /**
     * 仅OEM
     */    
    public List<ProductVO> findProductByPaginationByOEMTparts(ProductDTO productDTO);


    /********************************************/
    /**
     * @date 2015年10月22日
     * @author lby
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ProductVO findProductByMainIDNew(String mainID);
    
    
    public List<ProductVO> findProductByMainIDs(ProductDTO productDTO);
    
    
    /********************************************/
    /**
     * @date 2015年10月22日
     * @author lby
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ProductVO findProductByCode(String code);
    
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
    public Long findProductCountBySupplierItem(ItemDTO itemDTO);
    
    public List<ProductVO> findProductBySupplierItem(ItemDTO itemDTO);
    
    /**
     * 总条数
     * 
     * @date 2014年11月16日
     * @author lby
     * @param productDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findProductCountTpartsAdmin(ProductVO productVO);

    /**
     * 分页查询
     * 
     * @date 2014年11月16日
     * @author lby
     * @param productDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ProductVO> findProductByPaginationTpartsAdmin(ProductVO productVO);

    /**
     * 这里对产品状态的更新是对isDelete的修改
     * @date 2015年11月17日
     * @author lby
     * @param productDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateProductStatusByMainID(ProductDTO productDTO);
    
    /**
     * 根据prodcutVo.type
     * 1根据原厂件对应的副厂件数据
     * 2根据副厂件对应的数据原厂件
     * @param productVo
     * @return
     */
    public List<ProductVO> findProductByOEM(ProductVO productVo);
}
