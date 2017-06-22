package com.kpluswebup.web.member.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.kpluswebup.web.customer.dao.FocusDAO;
import com.kpluswebup.web.member.service.FocusService;
import com.kpluswebup.web.member.service.impl.FocusServiceImpl;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.FocusEntity;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FocusServiceTest {
	
	@Mock
	private FocusEntity focusEntity;
	
	private static FocusService focusService;
	
	@Mock
	private FocusEntity expected;
	
	@Mock
	private FocusDAO focusDAO;
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
		
    }
    @Before
    public void setUp() throws Exception
    {
    	focusService = new FocusServiceImpl(focusDAO);
    	
    	expected = new FocusEntity();
    	expected.setMyFocusInfo("测试关注");
    	expected.setReferenceID("123456");
    	expected.setFocusType(1);
    	
    	focusEntity = new FocusEntity();
    	focusEntity.setCustomerID("123456");
    	focusEntity.setFocusType(1);
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
	public void findByFocusEntity()
	{
    	 when(focusDAO.findFocusByCustomerID(focusEntity)).thenAnswer(new Answer<List<FocusEntity>>() {  
          
	         @Override  
	         public List<FocusEntity> answer(InvocationOnMock invocation) throws Throwable {  
		         List<FocusEntity> list = new ArrayList<FocusEntity>();  
		         FocusEntity entity = new FocusEntity();  
		         entity.setMyFocusInfo("测试关注");
		         entity.setReferenceID("123456");
		         entity.setFocusType(1);         
		         list.add(entity);  
		         entity = new FocusEntity();  
		         entity.setMyFocusInfo("测试关注2");
		         entity.setReferenceID("1234562");
		         entity.setFocusType(2);         
		         list.add(entity);           
		         return list;  
	         }  
          
         }); 
    	 List<FocusEntity> actuals = focusService.findByFocusEntity(focusEntity);
    	 Assert.assertEquals(expected.getFocusType(), actuals.get(0).getFocusType());
	}
    @Test
	public void save(){
    	
    	when(focusDAO.insert(any(FocusEntity.class))).thenReturn(1);
    	String myFocusInfo = "测试关注";
    	String referenceID = "123456";
    	int focusType = 1;
    	CustomerVO customer = null;
    	FocusEntity actual = focusService.save(myFocusInfo, referenceID, customer, focusType);
    	Assert.assertNull(actual);
    	customer = new CustomerVO();
    	actual = focusService.save(myFocusInfo, referenceID, customer, focusType);   
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(actual.getIsDelete().intValue(), 0);
    	Assert.assertEquals(actual.getMainID().length(), 36);
    	myFocusInfo = null;
    	actual = focusService.save(myFocusInfo, referenceID, customer, focusType);   
    	Assert.assertNull(actual);
	}    
}
