package com.kpluswebup.mall.web.common.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kpluswebup.web.account.service.FinalstatementService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.service.SupplierOrderStatisticsService;
/**
 * 
 * @author moo
 *
 */
@Component
public class SupplierOrderStatisticsTask {
	
	@Autowired
	private SupplierOrderStatisticsService supplierOrderStatisticsService;
	
	//@Scheduled(cron = "0 1 1 * * ?")
	public void generateSupplierOrderStatistics(){
		
		System.out.println("---------------当日商家订单统计任务开始-----start---------------");
		supplierOrderStatisticsService.generateSupplierOrderStatistics();
		
		System.out.println("---------------当日商家订单统计任务开始----- end ---------------");
		
		
	}
}
