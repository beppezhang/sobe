package com.test;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitConf {

    public static ApplicationContext context=null;
    
    static {
        context = new ClassPathXmlApplicationContext("spring-mysql-db.xml","spring-context-adminsystem.xml");
        
    }
}
