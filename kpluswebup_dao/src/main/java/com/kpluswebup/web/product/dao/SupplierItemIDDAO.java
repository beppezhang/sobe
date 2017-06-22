package com.kpluswebup.web.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kpluswebup.web.domain.SupplierItemIDDTO;
import com.kpluswebup.web.vo.SupplierItemIDVO;

public interface SupplierItemIDDAO {
	
	/**
	 * 
	 * @date 2015年5月20日
	 * @author moo
	 * @param supplierItemIDDTO
	 * @return void
	 * @since JDK 1.6
	 * @Description
	 */
	public void addSupplierItemID(SupplierItemIDDTO supplierItemIDDTO);
	
	/**
	 * 
	 * @date 2015年5月20日
	 * @author moo
	 * @param supplierItemIDDTO
	 * @return void
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateSupplierItemID(SupplierItemIDDTO supplierItemIDDTO);
	
	/**
	 * 
	 * @date 2015年5月21日
	 * @author moo
	 * @param orderId
	 * @return
	 * @return List<SupplierItemIDVO>
	 * @since JDK 1.6
	 * @Description 查询订单下所有单个商品
	 */
	public List<SupplierItemIDVO> findSupplierItemIDByOrderID(String orderId);

	/**
	 * 
	 * @date 2015年5月21日
	 * @author moo
	 * @param mainID
	 * @param split
	 * @return void
	 * @since JDK 1.6
	 * @Description 取货SupplierItemID状态更新
	 */
	public void updateSupplierItemIDPickUp(
			@Param(value="supplierID") String mainID, 
			@Param("carrierNoteIDs") String[] split);

	/**
	 * 
	 * @date 2015年6月5日
	 * @author moo
	 * @param serialiseNo
	 * @return
	 * @return SupplierItemIDVO
	 * @since JDK 1.6
	 * @Description
	 */
	public SupplierItemIDVO findSupplierItemIDBySerialiseNo(String serialiseNo);

	/**
	 * 
	 * @date 2015年6月16日
	 * @author moo
	 * @param orderLineId
	 * @return
	 * @return List<SupplierItemIDVO>
	 * @since JDK 1.6
	 * @Description
	 */
	public List<SupplierItemIDVO> findSupplierItemIDByOrderLineID(String orderLineId);
	
	/**
	 * 查询运送商品
	 * @date 2015年6月24日
	 * @author wanghehua
	 * @param salesOrderLineId
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public void deleteSupplierItemIDBySalesOrderLineID(String salesOrderLineId);

	/**
	 * 
	 * @date 2015年6月29日
	 * @author moo
	 * @param carrierNoteID
	 * @return void
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateSupplierItemIDStatus(String carrierNoteID);
}
