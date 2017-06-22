package com.kpluswebup.web.service;

import static org.mockito.Mockito.when;

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

import com.kpluswebup.web.partscategory.dao.PartsCategoryNormalDAO;
import com.kpluswebup.web.service.impl.PartsCategoryNormalServiceImpl;
import com.kpluswebup.web.vo.PartsCategoryVo;

@RunWith(MockitoJUnitRunner.class)
public class PartsCategoryNormalServiceTest {

	
	//mock   PartsCategoryNormalDAO对象
	@Mock
	private PartsCategoryNormalDAO partsCategoryNormalDAO;
	
	@Mock 
	private PartsCategoryVo expected;
	
	private PartsCategoryNormalService partsCategoryNormalService;
	
	private String mainID=null;
	
	private String ancestorID=null;
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
		
    }
	
	//初始化，设置测试结果的期望值
	 @Before
    public void setUp() throws Exception
    {
		 //实例化PartsCategoryNormalService对象
         partsCategoryNormalService=new PartsCategoryNormalServiceImpl(partsCategoryNormalDAO);
         //需要测试  findPartsCategoryNormalByMainID的方法，故需要对方法的运行环境进行加载
         mainID="2222";
         ancestorID="3333";
         expected = new PartsCategoryVo();
         expected.setAncestorID("1111");
         expected.setName("发动机");
		 
    }
	 @After
    public void tearDown() throws Exception
    {
    	
    } 
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    	
    }
    //对方法进行单元测试
    @Test
    public void findPartsCategoryNormalByMainID(){
    	//返回的的是一个集合
    	when(partsCategoryNormalDAO.findPartsCategoryNormalByMainId(mainID)).thenAnswer(new Answer<PartsCategoryVo>()
    		{  
            @Override  
	         public PartsCategoryVo answer(InvocationOnMock invocation) throws Throwable {  
		         //设置自己期待的返回值    
            	PartsCategoryVo partsCategoryVo=new PartsCategoryVo();
            	partsCategoryVo.setAncestorID("1111");
		         return partsCategoryVo;  
	         }  
         });
    	//得到的实际值
    	PartsCategoryVo actual=partsCategoryNormalService.findPartsCategoryNormalByMainID(mainID);
    	Assert.assertEquals(expected.getAncestorID(), actual.getAncestorID());
     }
    
    @Test
    public void findPartsCategoryNormalByAncestorID(){
    	when(partsCategoryNormalDAO.findPartsCategoryNormalByAncestorID(ancestorID)).thenAnswer(new Answer<List<PartsCategoryVo>>()
    			{

					@Override
					public List<PartsCategoryVo> answer(InvocationOnMock invocation)
							throws Throwable {
						//使用回调生成我们期待的返回值
						List<PartsCategoryVo> list=new ArrayList<PartsCategoryVo>();
						PartsCategoryVo partsCategoryVo=new PartsCategoryVo();
						partsCategoryVo.setName("发动机");
						partsCategoryVo.setFlevel(1);
						list.add(partsCategoryVo);
						return list;
					}
    		
    		   });
    	//得到的实际值
    	List<PartsCategoryVo> actualList = partsCategoryNormalService.findPartsCategoryNormalByAncestorID(ancestorID);
    	Assert.assertEquals(expected.getName(), actualList.get(0).getName());
    }

	
}
