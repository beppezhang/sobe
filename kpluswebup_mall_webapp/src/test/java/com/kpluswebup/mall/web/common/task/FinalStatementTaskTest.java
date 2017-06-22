package com.kpluswebup.mall.web.common.task;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kpluswebup.web.account.service.FinalstatementService;
import com.kpluswebup.web.service.SalesOrderService;
/**
 * 
 * @author moo
 *
 */

public class FinalStatementTaskTest {
	
	
	
	private FinalstatementService finalstatementService;
	
    public static ApplicationContext context = null;

    static {
        context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
    }
    
    //@Before
    public void init(){
    	finalstatementService = (FinalstatementService) context.getBean("finalstatementService");
    }
    
	//@Test
	public void generateYesterdayFinalStatement(){
		
		System.out.println("---------------结算单任务开始-----start---------------");
		int result = finalstatementService.generateYesterdayFinalStatement();
		System.out.println("---------------本次共生成结算单 　"+result+"　条---------------");
		System.out.println("---------------结算单任务结束----- end ---------------");
		
		
	}
}
