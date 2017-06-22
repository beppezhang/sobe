package com.kpluswebup.web.supplier.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.kpluswebup.web.domain.SupplierDTO;
import com.kpluswebup.web.vo.SupplierVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-service.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestSupplierService {
	
		@Autowired
		SupplierService supplierService;
		
	 	@BeforeClass
	    public static void setUpBeforeClass() throws Exception
	    {
	 		
	    }
	    @Before
	    public void setUp() throws Exception
	    {
	    	
	    }
	    
	    @Test
	    public void testUpdateSupplierPwd()
	    {
	    	SupplierDTO supplierDTO = new SupplierDTO();
	    	supplierDTO.setPassWord("123456");
	    	supplierDTO.setMainID("b3e19f7c2507471da97b85d39cc26fca");
	    	supplierService.updateSupplierPwd(supplierDTO);
	    	SupplierVO supplierVO = supplierService.findSupplierByID("b3e19f7c2507471da97b85d39cc26fca");
	    	assertEquals("123456",supplierVO.getPassWord());
	    }

	    @Test
	    public void testFindSupplier()
	    {
	    	SupplierVO supplierVO = supplierService.findSupplierByID("b3e19f7c2507471da97b85d39cc26fca");
	    	supplierVO = null;
	    	assertNotNull(supplierVO);
	    }	    
	    
	    
	    @After
	    public void tearDown() throws Exception
	    {
	    	
	    } 
	    
	    @AfterClass
	    public static void tearDownAfterClass() throws Exception
	    {
	    	
	    }






}
