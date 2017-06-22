package com.kpluswebup.web.service;

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

import bz.sunlight.domain.business.VehicleBrandsHotBO;
import bz.sunlight.web.service.VehicleBrandsService;
import bz.sunlight.web.service.impl.VehicleBrandsServiceImpl;

import com.kpluswebup.web.product.dao.ProductCategoryDAO;
import com.kpluswebup.web.service.impl.PartsCategoryNormalServiceImpl;
import com.kpluswebup.web.vo.PartsCategoryVo;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpuswebup.comom.util.GsonUtil;
import com.kpuswebup.comom.util.PropertiesUtil;

import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VehicleBrandsServiceTest {

	//mock出一个ProductCategoryDAO对象
	@Mock
	private ProductCategoryDAO productCategoryDAO;
	
	private VehicleBrandsService vehicleBrandsService;
	
	/*@Mock
	private ProductCategoryVO expected;*/
	
	@Mock
	private VehicleBrandsHotBO vehicleBrandsHotBO;
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
		
    }
	
	//初始化，设置测试结果的期望值
	 @Before
    public void setUp() throws Exception
    {
		 //实例化VehicleBrandsServiceImpl对象
		 vehicleBrandsService=new VehicleBrandsServiceImpl(productCategoryDAO);
		 vehicleBrandsHotBO=new VehicleBrandsHotBO();
		 //expected.setName("beppe");
         
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
    public void findHotBrands(){
    	List<ProductCategoryVO> list=new ArrayList<ProductCategoryVO>();
    	ProductCategoryVO productCategoryVO=new ProductCategoryVO();
    	productCategoryVO.setName("beppe");
    	list.add(productCategoryVO);
    	when(productCategoryDAO.findProductOneLevelTparts()).thenReturn(list);
    	
    	String expected=GsonUtil.toJson(list.get(0).getName());
    	String actual=vehicleBrandsService.findHotBrands();
    	System.out.println(expected);
    	
    	
    	
    	
    	//Assert.assertEquals(expected, actual);
    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
