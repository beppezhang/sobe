package com.kpluswebup.web.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.ItemDetailDTO;
import com.kpluswebup.web.domain.PreSaleDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.vo.ItemDetailVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.PreSaleVO;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.StatisticsInfoVO;
import com.kpluswebup.web.vo.VehicleTypeVO;

public interface ItemService {

    /**
     * @date 2014年11月21日
     * @author zhuhp
     * @param productID
     * @param name
     * @param oldNumber
     * @param picURL
     * @param costPrice
     * @param standrardPrice
     * @param distributionPrice
     * @param retailPrice
     * @param salesPrice
     * @param purchasePrice
     * @param minimumQty
     * @param weight
     * @param cubage
     * @param quantity
     * @param stock
     * @param saftyStock
     * @param sortOrder
     * @param description
     * @since JDK 1.6
     * @Description
     */
    public void insertItem(String productID, String name, String oldNumber, String picURL, String costPrice,
                           String standrardPrice, String distributionPrice, String retailPrice, String salesPrice,
                           String purchasePrice, String minimumQty, String weight, String cubage, String quantity,
                           String stock, String saftyStock, String sortOrder, String description);

    /**
     * @date 2014年11月21日
     * @author zhuhp
     * @param mianID
     * @param productID
     * @param name
     * @param oldNumber
     * @param picURL
     * @param costPrice
     * @param standrardPrice
     * @param distributionPrice
     * @param retailPrice
     * @param salesPrice
     * @param purchasePrice
     * @param minimumQty
     * @param weight
     * @param cubage
     * @param quantity
     * @param stock
     * @param saftyStock
     * @param sortOrder
     * @param description
     * @since JDK 1.6
     * @Description
     */
    public void editItem(String mianID, String productID, String name, String oldNumber, String picURL,
                         String costPrice, String standrardPrice, String distributionPrice, String retailPrice,
                         String salesPrice, String purchasePrice, String minimumQty, String weight, String cubage,
                         String quantity, String stock, String saftyStock, String sortOrder, String description);

    /**
     * @date 2014年11月21日
     * @author zhuhp
     * @param productID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemVO> findItemByProductID(String productID);
    
    /**
     * 判断商品是否存在
     * @param itemDTO
     * @return
     */
    public ItemVO  seeItem(ItemDTO itemDTO);
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
     * @date 2014年11月21日
     * @author zhuhp
     * @param productID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ItemVO findItemByMainID(String mainID);

    /**
     * @date 2014年11月21日
     * @author zhuhp
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public void deleteItemByMainID(String mainID);

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
     * 批量添加或者修改修改
     * 
     * @date 2014年11月25日
     * @author zhuhp
     * @param productID
     * @param productTypeMainID
     * @param items
     * @since JDK 1.6
     * @Description
     */
    public void batchAddOrEditItem(String productID, String productTypeMainID, String[] items, String currentOperator,String[] picsURL);

    /**
     * 搜索
     * 
     * @date 2014年12月19日
     * @author zhuhp
     * @param name
     * @param type
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemVO> searchItem(String name, String type,String catalog);
    
    /**
     * 根据商品id查找商品属性
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
     * 
     * @date 2015年5月13日
     * @author moo
     * @param itemDTO
     * @return
     * @return List<ItemVO>
     * @since JDK 1.6
     * @Description 查询类目下的所有商品
     */
	public List<ItemVO> findItemByCategoryId(ItemDTO itemDTO);
	
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
	 * @return List<ItemVO>
	 * @since JDK 1.6
	 * @Description 查询供应商审核通过商品
	 */
	public List<ItemVO> findSupplierItemPass(ItemDTO itemDTO);
	
	/**
	 * 
	 * @date 2015年5月18日
	 * @author moo
	 * @param itemDTO
	 * @return
	 * @return int
	 * @since JDK 1.6
	 * @Description 查询供应商审核通过商品数量
	 */
	public int getSupplierItemPassCount(ItemDTO itemDTO);
	
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
    public List<ItemVO> findItemsByProductID(String productID,String name);
    
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
	public ItemVO findItemSupplieredByItemMainIDAndSupplierID(String itemMainID, String supplierID);
	
	/**
	 * 导出销售报表
	 * @date 2015年6月5日
	 * @author wanghehua
	 * @param response
	 * @since JDK 1.6
	 * @Description
	 */
	public void exportItem(HttpServletResponse response,ItemDTO itemDTO);
	
	/**
	 * 导出财务报表
	 * @date 2015年6月5日
	 * @author wanghehua
	 * @param response
	 * @since JDK 1.6
	 * @Description
	 */
	public void exportPurchaseItem(HttpServletResponse response,ItemDTO itemDTO);
	
	/**
	 * 查询商品销售属性 
	 * @date 2015年6月9日
	 * @author wanghehua
	 * @param productID
	 * @param pTypeIPropID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<ItemDetailVO> findItemDetailPropValue(String productID,String pTypeIPropID);


	/***************************************/
   /**
	* @date 2015年10月19日
    * @author lby
    * @param productID
    * @return
    * @since JDK 1.6
    * @Description
    */
   public List<ItemVO> findItemsByProductIDTparts(ItemDTO itemDTO);

	/**
	 * @Description 新增或更新商品
	 * @date 2015年11月3日
	 * @author lby
	 * @param ...
	 * @return
	 * @since JDK 1.6
	 */
   public void addOrUpdateItem(String mainID,String productID,String supplierCategoryID, String name, String standrardPrice,String salesPrice,
    		String weight,String cubage,String description,String picURL,String supplierName,String supplierID,String currentOperator,String freightTemplateID);
   
   
	/**
	 * @Description 更新商品状态
	 * @date 2015年11月9日
	 * @author lby
	 * @param ...
	 * @return
	 * @since JDK 1.6
	 */
  public Integer updateItemStatus(String mainIDs, String status);

	/**
	 * 
	 * @date 2015年11月13日
	 * @author lby
	 * @param itemDTO
	 * @return
	 * @return List<ItemVO>
	 * @since JDK 1.6
	 * @Description 查询供应商审核通过的商品
	 */
	public List<ItemVO> findSupplierPassItems(ItemDTO itemDTO);
	
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
    
    
    
    public Map<ProductCategoryVO,List<VehicleTypeVO>> findSuitVehicle(ProductVO productVo);
    
    /******************************************/
    /**
     * 测试事务支持
     * @param ItemId
     * @param picURL
     * @return
     * @throws Exception 
     */
    public void editItemTest(ItemVO itemVo) throws Exception;

    /**
     * 查当月销量
     * @date 2015年12月9日
     * @author lby
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public StatisticsInfoVO countMonthSales(SalesOrderDTO salesOrderDTO);

    /**
     * 查累计销量
     * @date 2015年12月9日
     * @author lby
     * @param salesOrderDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public StatisticsInfoVO countTotalSales(SalesOrderDTO salesOrderDTO);
}
