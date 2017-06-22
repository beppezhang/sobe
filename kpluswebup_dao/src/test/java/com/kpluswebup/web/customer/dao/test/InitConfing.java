package com.kpluswebup.web.customer.dao.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitConfing {

    public static ApplicationContext context = null;

    static {
        context = new ClassPathXmlApplicationContext("spring-context-adminsystem.xml","spring-mysql-db.xml");
    }
    
   
}
