package com.kpluswebup.web.customer.dao.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kpluswebup.web.order.dao.SalesOrderDAO;
import com.kpluswebup.web.order.dao.SalesOrderLineDAO;


public class OrderTest{
    @Autowired
    private SalesOrderDAO salesOrderDAO;
    @Autowired
    private SalesOrderLineDAO salesOrderLineDAO;
    
    @Test
    public void test_01() {
//        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-context-adminsystem.xml","spring-mysql-db.xml"});
//        SalesOrderDAO salesOrderDAO = (SalesOrderDAO) context.getBean("salesOrderDAO");
//        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
//        List<SalesOrderVO> list = salesOrderDAO.findSalesOrderByPagination(salesOrderDTO);
//        if(list != null){
//            System.out.println(list.size());
//        }
    }
    
    @Test
    public void test_02() {
        
    }
}
