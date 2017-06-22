package com.kpluswebup.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.order.dao.SupplierOrderStatisticsDAO;
import com.kpluswebup.web.service.SupplierOrderStatisticsService;
import com.kpluswebup.web.vo.SupplierSalesOrderStatisticsVO;

@Service
public class SupplierOrderStatisticsServiceImpl implements
		SupplierOrderStatisticsService {

	@Autowired
	private SupplierOrderStatisticsDAO supplierOrderStatisticsDAO;
	
	
	@Override
	public int generateSupplierOrderStatistics() {
		supplierOrderStatisticsDAO.generateYesterdaySupplierOrderStatistics();
		return 0;
	}


	@Override
	public SupplierSalesOrderStatisticsVO findYesterdayStatistics(String supplierID) {
		
		return supplierOrderStatisticsDAO.findYesterdayStatistics(supplierID);
	}


	@Override
	public SupplierSalesOrderStatisticsVO findLastMonthStatistics(String supplierID) {
		
		return supplierOrderStatisticsDAO.findStatisticsByMonth(supplierID, 0);
	}

}
