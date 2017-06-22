package com.kpluswebup.web.service.test;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.ProductVO;

public class TestItemService extends TestCase
{
    private static final Log log = LogFactory.getLog(TestItemService.class);


    ProductVO productVO = null;
    public static ApplicationContext context = null;
    protected void setUp() throws Exception
    {
        log.info("Framework TestStorage");
        productVO = new ProductVO();
        context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
    }
    
    public void testItemService()
    {
    	ItemService service =  (ItemService)context.getBean("itemServiceImpl");
    	ItemVO itemVO = service.findItemById("4e8934df-9a64-11e5-ad3d-005056af50a8");
    	assertNotNull(productVO);
    }
    
    public void testSxc()
    {
    	assertNotNull(productVO);
    }

    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(TestItemService.class);
    }
    
}
