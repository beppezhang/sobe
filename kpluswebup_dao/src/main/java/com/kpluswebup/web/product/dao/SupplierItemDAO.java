package com.kpluswebup.web.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.domain.SupplierItemDTO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.SupplierItemVO;


public interface SupplierItemDAO {
	
	/**
	 * 
	 * @date 2015年5月14日
	 * @author moo
	 * @param supplierItemDTO
	 * @return
	 * @return SupplierItemVO
	 * @since JDK 1.6
	 * @Description 根据商品id,供应商id查询SupplierItemVO
	 */
	SupplierItemVO findSupplierItemByItemIDAndSupplierID(
			SupplierItemDTO supplierItemDTO);
	
	/**
	 * 
	 * @date 2015年5月14日
	 * @author moo
	 * @param supplierItemDTO
	 * @return
	 * @return int
	 * @since JDK 1.6
	 * @Description
	 */
	int insertSupplierItem(SupplierItemDTO supplierItemDTO);
	
	/**
	 * 
	 * @date 2015年5月14日
	 * @author moo
	 * @param supplierItemDTO
	 * @return
	 * @return int
	 * @since JDK 1.6
	 * @Description
	 */
	int updateSupplierItem(SupplierItemDTO supplierItemDTO);
	
	/**
	 * 
	 * @date 2015年5月18日
	 * @author moo
	 * @param mainID
	 * @param type -1全部 0未审核1审核通过2审核失败
	 * @return
	 * @return int
	 * @since JDK 1.6
	 * @Description
	 */

	int findItemBySupplierAdnType(@Param(value="supplierID")String mainID, @Param(value="type")int type);

	/**
	 * 
	 * @date 2015年5月28日
	 * @author moo
	 * @param supplierItemDTO
	 * @return void
	 * @since JDK 1.6
	 * @Description 下单修改库存
	 */
	void updateSupplierItemStockAndSaleCount(SupplierItemDTO supplierItemDTO);
	/**
	 * 供应商 商品列表
	 * @param supplierItemDTO
	 * @return
	 */
	public List<SupplierItemVO> findSupplierItemPagination(SupplierItemDTO supplierItemDTO);
	
	public Integer updateSupplierItemStatus(SupplierItemDTO supplierItemDTO);
	
	 public Long findSupplierItemCount(SupplierItemDTO supplierItemDTO);

	 /**
	  * 
	  * @date 2015年6月2日
	  * @author moo
	  * @param supplierID
	  * @return
	  * @return List<ItemVO>
	  * @since JDK 1.6
	  * @Description 销量前三商品
	  */
	List<ItemVO> findTopThreeSaleItem(String supplierID);

	/**
	 * 
	 * @date 2015年6月8日
	 * @author moo
	 * @param supplierID
	 * @return void
	 * @since JDK 1.6
	 * @Description
	 */
	void updateSupplierItemStatusWhichStockLess20(String supplierID);

	/**
	 * 
	 * @date 2015年6月17日
	 * @author moo
	 * @param itemIds
	 * @return void
	 * @since JDK 1.6
	 * @Description
	 */
	void batchConfirmSupplierItem(@Param(value="itemIds") String[] itemIds);
	

    /**
     * 删除供应商商品
     * @date 2015年6月18日
     * @author wanghehua
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public void deleteSupplierItemBymainID(String mainID);
    
    public List<SupplierItemVO> findSupplierItem(SupplierItemDTO supplierItemDTO);
    
	/**
	 * 
	 * @date 2015年11月3日
	 * @author sxc
	 * @param supplierItemDTO
	 * @return
	 * @return SupplierItemVO
	 * @since JDK 1.6
	 * @Description 根据商品id,供应商id查询SupplierItemVO
	 */
	SupplierItemVO findSupplierItemByItemIDAndSupplierIDTparts(
			SupplierItemDTO supplierItemDTO);    

	/**
	 * 后台商品列表查询
	 * @date 2015年11月18日
	 * @author lby
	 * @param supplierItemDTO
	 * @since JDK 1.6
	 * @Description 
	 */
	public List<SupplierItemVO> findSupplierItemPaginationTparts(SupplierItemDTO supplierItemDTO);
	
	public Long findSupplierItemCountTparts(SupplierItemDTO supplierItemDTO);
}
