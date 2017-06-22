package com.kpluswebup.mall.web.common.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kpluswebup.web.account.service.FinalstatementService;
import com.kpluswebup.web.service.SalesOrderService;
/**
 * 
 * @author moo
 *
 */
@Component
public class FinalStatementTask {
	
	@Autowired
	private SalesOrderService salesOrderService;
	
	@Autowired
	private FinalstatementService finalstatementService;
	
	//@Scheduled(cron = "0 43/3 10 * * ?")//每天0点跟中午12点自动结算
	public void generateYesterdayFinalStatement(){
		
		System.out.println("---------------结算单任务开始-----start---------------");
		int result = finalstatementService.generateYesterdayFinalStatement();
		System.out.println("---------------本次共生成结算单 　"+result+"　条---------------");
		System.out.println("---------------结算单任务结束----- end ---------------");
		
		
	}
	
}
