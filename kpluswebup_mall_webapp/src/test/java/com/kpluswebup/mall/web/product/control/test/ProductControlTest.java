package com.kpluswebup.mall.web.product.control.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.kpluswebup.mall.web.product.control.ProductControl;
import com.kpluswebup.web.member.service.FocusService;
import com.kpluswebup.web.service.VehicleTypeService;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.FocusEntity;
import com.kpluswebup.web.vo.VehicleTypeVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductControlTest {
	
	@Mock
	private VehicleTypeService vehicleTypeService;
	@Mock
	private FocusService focusService;	
	
	private ProductControl productControl;

	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
		
    }
    @Before
    public void setUp() throws Exception
    {
    	productControl = new ProductControl(vehicleTypeService, focusService);
    }
    
    @After
    public void tearDown() throws Exception
    {
    	
    } 
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    	
    }
    
    @Test(expected=Exception.class )
    public void ajaxAddFocus()
    {
    	JsonResult expectedJsonResult = new JsonResult(ResultCode.NORMAL);
    	VehicleTypeVO expected = new VehicleTypeVO();
    	expected.setName("测试车型");
    	expected.setMainID("123456789");
    	productControl = spy(productControl);
    	//when(productControl.findUserInfo()).thenReturn(new CustomerVO());
    	doReturn(new CustomerVO()).when(productControl).findUserInfo();
    	//Mockito.stub(productControl.findUserInfo()).toReturn(new CustomerVO());
    	when(vehicleTypeService.findByMainID("123456")).thenReturn(expected);
    	when(focusService.save(anyString(), anyString(), any(CustomerVO.class), anyInt())).thenReturn(new FocusEntity());
    	JsonResult actual = productControl.ajaxAddFocus("123456");
    	Assert.assertEquals(expectedJsonResult.getCode(), actual.getCode());
    	when(vehicleTypeService.findByMainID("123321")).thenThrow(new Exception());
    	actual = productControl.ajaxAddFocus("123321");
   	
    }
    
//	@RequestMapping("addFocusByvehicleType")
//  public @ResponseBody JsonResult ajaxAddFocus(String vehicleTypeId) {
//      try {
//  		if (StringUtil.isNotEmpty(vehicleTypeId)) {
//              /**--Tparts--**/
//              VehicleTypeVO vehicleTypeVO = vehicleTypeService.findByMainID(vehicleTypeId);
//              /*记录vin查询历史用于我的关注的显示*/
//          	if(focusService!=null)
//          	{
//          		Integer focusType = 1;	//记录关注类型:vehicleType
//              	focusService.save(vehicleTypeVO.getName(),vehicleTypeVO.getMainID(), this.findUserInfo(), focusType);        		
//          	}            
//          }             
//          return new JsonResult(ResultCode.NORMAL);
//      } catch (Exception e) {
//          return new JsonResult(ResultCode.ERROR_SYSTEM);
//      }
//  }    
}
