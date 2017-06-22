package com.kpluswebup.web.order.dao;

import org.apache.ibatis.annotations.Param;

import com.kpluswebup.web.vo.SupplierSalesOrderStatisticsVO;


public interface SupplierOrderStatisticsDAO {



	/**
	 * 
	 * @date 2015年6月4日
	 * @author moo
	 * @return
	 * @return void
	 * @since JDK 1.6
	 * @Description 商家昨日订单统计
	 */
	void generateYesterdaySupplierOrderStatistics();

	/**
	 * 
	 * @date 2015年6月4日
	 * @author moo
	 * @param supplierID
	 * @return
	 * @return SupplierSalesOrderStatisticsVO
	 * @since JDK 1.6
	 * @Description 昨日订单统计
	 */
	SupplierSalesOrderStatisticsVO findYesterdayStatistics(String supplierID);

	/**
	 * 
	 * @date 2015年6月4日
	 * @author moo
	 * @param supplierID
	 * @param month 0 当月  1上月 2上上月...
	 * @return
	 * @return SupplierSalesOrderStatisticsVO
	 * @since JDK 1.6
	 * @Description 月订单统计
	 */
	SupplierSalesOrderStatisticsVO findStatisticsByMonth(
			@Param(value="supplierID") String supplierID, 
			@Param(value="month") Integer month);

	

}
