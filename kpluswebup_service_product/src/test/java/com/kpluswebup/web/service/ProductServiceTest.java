package com.kpluswebup.web.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import com.kpluswebup.web.product.dao.ProductDAO;
import com.kpluswebup.web.service.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
	
	
	@Mock
	private ProductDAO productDAO;
	
	private ProductService productService;
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
		
    }
    @Before
    public void setUp() throws Exception
    {
//    	productService = new ProductServiceImpl();
    	productService = new ProductServiceImpl(productDAO);
    }
    
    @After
    public void tearDown() throws Exception
    {
    	
    } 
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    	
    }	
    
    @Test
    public void findDBDate()
    {
    	String expected = "2016-01-07";
    	when(productDAO.findDBDate()).thenReturn(expected); 
    	String actual = productService.findDBDate();
    	Assert.assertSame(expected, actual);
    }
}
