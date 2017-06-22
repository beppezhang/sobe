package com.kpluswebup.web.service;

import com.kpluswebup.web.vo.SupplierSalesOrderStatisticsVO;

public interface SupplierOrderStatisticsService {

	/**
	 * 
	 * @date 2015年6月3日
	 * @author moo
	 * @return
	 * @return int
	 * @since JDK 1.6
	 * @Description 统计当天订单
	 */
	int generateSupplierOrderStatistics();

	/**
	 * 
	 * @date 2015年6月4日
	 * @author moo
	 * @return
	 * @return SupplierSalesOrderStatisticsVO
	 * @since JDK 1.6
	 * @Description 查询昨日订单统计
	 */
	SupplierSalesOrderStatisticsVO findYesterdayStatistics(String supplierID);

	/**
	 * 
	 * @date 2015年6月4日
	 * @author moo
	 * @return
	 * @return SupplierSalesOrderStatisticsVO
	 * @since JDK 1.6
	 * @Description 查询上月订单统计
	 */
	SupplierSalesOrderStatisticsVO findLastMonthStatistics(String supplierID);

}
