package com.kpluswebup.web.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kpluswebup.web.domain.CmsContentDTO;
import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.ItemDetailDTO;
import com.kpluswebup.web.domain.PreSaleDTO;
import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.domain.SupplierItemDTO;
import com.kpluswebup.web.vo.CmsContentVO;
import com.kpluswebup.web.vo.ItemDetailVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.PreSaleVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;

public interface ItemDAO {

    /**
     * @date 2014年11月21日
     * @author zhuhp
     * @param itemDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer insertItem(ItemDTO itemDTO);
    /**
     * 通过产品Id查询商品Id
     * @author zhoulei
     * @param productID
     * @return
     */
    public List<ItemVO> findItemByProductMainID(String productID);
    /**
     * @date 2014年11月21日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteItemByMainID(String mainID);
    public Long finItemsByItemDtoCount(ItemDTO ItemDto);

    /**
     * @date 2014年11月21日
     * @author zhuhp
     * @param itemDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateItemByMainID(ItemDTO itemDTO);

    /**
     * 不分页条件查询
     * 
     * @date 2014年11月21日
     * @author zhuhp
     * @param itemDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemVO> findItem(ItemDTO itemDTO);

    /**
     * @date 2014年11月22日
     * @author zhuhp
     * @param itemDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer addItemDetail(ItemDetailDTO itemDetailDTO);

    /**
     * 根据itemID删除
     * 
     * @date 2014年11月22日
     * @author zhuhp
     * @param itemID
     * @since JDK 1.6
     * @Description
     */

    public void deleteItemDetailByItemID(String itemID);

    /**
     * @date 2014年11月27日
     * @author zhuhp
     * @param productID
     * @since JDK 1.6
     * @Description
     */
    public void deleteItemByProductID(String productID);

    /**
     * 根据主键删除
     * 
     * @date 2014年11月22日
     * @author zhuhp
     * @param id
     * @since JDK 1.6
     * @Description
     */
    public void deleteItemDetailByID(Long id);

    /**
     * 不分页查找
     * 
     * @date 2014年11月22日
     * @author zhuhp
     * @param itemDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemDetailVO> findItemDetail(ItemDetailDTO itemDetailDTO);

    /**
     * 根据商品id查找商品规格
     * 
     * @date 2014年11月25日
     * @author wanghehua
     * @param itemID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemDetailVO> findItemDetailByItemID(String itemID);

    /**
     * 根据商品id查找选中的商品规格
     * 
     * @date 2015年7月12日
     * @author zhoulei
     * @param itemID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemDetailVO> findItemDetailSByItemID(String itemID);
    public List<ItemDetailVO> findItemDetailSByItemID1(String itemID);
    /**
     * 产品模糊搜索
     * 
     * @date 2014年12月19日
     * @author zhuhp
     * @param itemID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemVO> searchItemByProduct(ProductDTO productDTO);

    /**
     * 根据商品id查找商品属性
     * 
     * @date 2014年12月29日
     * @author liudanqi
     * @param itemID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemDetailVO> findItemProps(String itemID);

    /**
     * 根据产品id查询产品下所有商品规格
     * 
     * @date 2014年12月30日
     * @author liudanqi
     * @param ids
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemDetailVO> findproductProps(String productID);

    /**
     * 根据产品id查询同类型的商品
     * 
     * @date 2014年12月30日
     * @author liudanqi
     * @param productID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemVO> finditems(String productID);

    /**
     * 根据产品id取得所有该产品的商品
     * 
     * @date 2014年12月31日
     * @author liudanqi
     * @param productID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemVO> finditemsByProductID(String productID);
    
    /**
     * 商品详情页 根据产品ID与商品ID查找商品
     * @date 2015年7月9日
     * @author yuanyuan
     * @param ItemDto
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ItemVO findItemDetailByProductID(ItemDTO ItemDto);
    
    public List<ItemVO> finItemsByItemDto(ItemDTO ItemDto);

    /**
     * 根据商品规格值和产品id查询商品
     * 
     * @date 2014年12月31日
     * @author liudanqi
     * @param itemDetailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ItemVO findItemByValues(ItemDetailDTO itemDetailDTO);

    /**
     * 根据id查找商品
     * 
     * @date 2015年1月3日
     * @author liudanqi
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ItemVO findItemById(String mainID);
    
    public ItemVO findItemByIds(String mainID);
    
    public ItemVO findSupplierItemById(String mainID);

    /**
     * 根据品牌找商品
     * 
     * @date 2015年1月4日
     * @author yuanyuan
     * @param brandID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemVO> finditemsByProductIDs(String brandID);

    /**
     * 新品预售列表
     * 
     * @date 2015年1月5日
     * @author yuanyuan
     * @param itemDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<PreSaleVO> findPreSaleList(PreSaleDTO preSaleDTO);

    /**
     * 根据订单ID找商品
     * 
     * @date 2015年1月9日
     * @author yuanyuan
     * @param orderID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SalesOrderLineVO> findOrderItemByOrderID(String orderID);

    /**
     * 查询所有商品
     * 
     * @date 2015年4月27日
     * @author Administrator
     * @param itemDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemVO> findItemByPagination(ItemDTO itemDTO);

    /**
     * 查询总条数
     * 
     * @date 2015年4月27日
     * @author Administrator
     * @param itemDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findItemCount(ItemDTO itemDTO);
    
    /**
     * 
     * @date 2015年5月15日
     * @author moo
     * @param itemDTO
     * @return
     * @return long
     * @since JDK 1.6
     * @Description 查询类目下所有商品数量
     */
    public long getItemCountByCategoryId(ItemDTO itemDTO);
    
    /**
     * 
     * @date 2015年5月13日
     * @author moo
     * @param itemDTO
     * @return
     * @return List<ItemVO>
     * @since JDK 1.6
     * @Description 查询类目下所有商品
     */
	public List<ItemVO> findItemByCategoryId(ItemDTO itemDTO);
	
	/**
	 * 
	 * @date 2015年5月15日
	 * @author moo
	 * @param itemDTO
	 * @return
	 * @return long
	 * @since JDK 1.6
	 * @Description 查询供应商未审核的商品数量
	 */
	
	public long getSupplierItemWaititngCount(ItemDTO itemDTO);
	
	/**
	 * 
	 * @date 2015年5月15日
	 * @author moo
	 * @param itemDTO
	 * @return
	 * @return List<ItemVO>
	 * @since JDK 1.6
	 * @Description 查询供应商未审核的商品
	 */
	public List<ItemVO> findSupplierItemWaiting(ItemDTO itemDTO);
	
	/**
	 * 
	 * @date 2015年5月15日
	 * @author moo
	 * @param itemDTO
	 * @return
	 * @return long
	 * @since JDK 1.6
	 * @Description 查询供应商审核通过商品数量
	 */
	
	public long getSupplierItemPassCount(ItemDTO itemDTO);
	
	/**
	 * 
	 * @date 2015年5月15日
	 * @author moo
	 * @param itemDTO
	 * @return
	 * @return List<ItemVO>
	 * @since JDK 1.6
	 * @Description 查询供应商审核通过商品
	 */
	public List<ItemVO> findSupplierItemPass(ItemDTO itemDTO);

	 /**
     * 一键订货商品
     * @date 2015年5月21日
     * @author wanghehua
     * @param productID
     * @param name
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemVO> findItemsByProductID(ItemDTO itemDTO);
    
    /**
     * 
     * @date 2015年5月22日
     * @author moo
     * @param itemMainID
     * @param supplierID
     * @return
     * @return ItemVO
     * @since JDK 1.6
     * @Description 查询商家改过价格和库存的商品
     */
	public ItemVO findItemSupplieredByItemMainIDAndSupplierID(
			@Param(value="itemMainID") String itemMainID, 
			@Param(value="supplierID") String supplierID);

	public List<ItemDetailVO> findItemDetailPropValue(ItemDetailDTO itemDetailDTO);
	
	public List<ItemVO> findAllItem(ItemDTO itemDTO);

	/*****************************************************/
    /**
     * 分页条件查询
     * 
     * @date 2015年10月19日
     * @author lby
     * @param itemDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemVO> findItemsByProductIDNew(ItemDTO itemDTO);
    
    public Long findItemsCountByProductID(ItemDTO itemDTO);

    /**
     * 一个新的添加商品方法（主要增加了supplierCategoryID的数据供给）
     * @date 2015年11月4日
     * @author lby
     * @param itemDTO
     */
    public Integer insertItemNew(ItemDTO itemDTO);

    /**
     * kplus_zy_supplier_item 数据插入
     * @date 2015年11月4日
     * @author lby
     * @param itemDTO
     */
    public Integer insertSupplierItem(SupplierItemDTO supplierItemDTO);

    /**
     * 查已通过审核的商品数量
     * @date 2015年11月13日
     * @author lby
     * @param itemDTO
     */
    public long getSupplierPassItemsCount(ItemDTO itemDTO);

	/**
	 * 
	 * @date 2015年11月13日
	 * @author lby
	 * @param itemDTO
	 * @return
	 * @return List<ItemVO>
	 * @since JDK 1.6
	 * @Description 查询供应商审核通过商品
	 */
	public List<ItemVO> findSupplierPassItems(ItemDTO itemDTO);

    /**
     * @date 2015年11月9日
     * @author lby
     * @param itemDTO
     * @return
     * @since JDK 1.6
     * @Description 更新商品（在原来的基础上添加status的更新）
     */
    public Integer updateItemByMainIDNew(ItemDTO itemDTO);
    
    /**
     * 商品详情页 根据产品ID与商品ID查找商品
     * @date 2015年11月16日
     * @author sxc
     * @param ItemDto
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ItemVO findItemDetailByProductIDTparts(ItemDTO ItemDto);    
    
    /**
     * 测试事务支持
     * @param ItemId
     * @param picURL
     * @return
     */      
    public void editItemTest(ItemVO itemVo);
}
