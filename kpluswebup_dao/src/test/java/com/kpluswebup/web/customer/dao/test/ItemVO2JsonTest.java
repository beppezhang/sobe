package com.kpluswebup.web.customer.dao.test;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.ItemDetailDTO;
import com.kpluswebup.web.product.dao.ItemDAO;
import com.kpluswebup.web.vehicle.dao.VehicleTypeDAO;
import com.kpluswebup.web.vo.ItemDetailVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.VehicleTypeVO;
import com.kpuswebup.comom.util.GsonUtil;

public class ItemVO2JsonTest {

	public static ApplicationContext context = null;
	
	private ItemDAO itemDAO;	

	private VehicleTypeDAO vehicleTypeDAO;
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
		context = new ClassPathXmlApplicationContext("spring-context-adminsystem.xml","spring-mysql-db.xml");
    }
    @Before
    public void setUp() throws Exception
    {
		itemDAO = (ItemDAO)context.getBean("itemDAO");
		vehicleTypeDAO = (VehicleTypeDAO)context.getBean("vehicleTypeDAO");
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
    public void testvehicleTypeByGsonUtil(){
    	List<VehicleTypeVO> vehicleTypes = 
    			vehicleTypeDAO.findVehicleTypeByProductId("3323D2AC-11EF-4D6E-ADF0-75E812335B13");
    	String name = vehicleTypes.get(0).getName();
    	char[] nameArray = name.toCharArray();
    	//看GsonUtil是不是不能转单引号之类的特殊字符
    	for (char c : nameArray){
    		if (c == '\'')
    			System.out.println("发现目标");
    		System.out.println(c);
    	}
    	String json = GsonUtil.toJson(vehicleTypes);
    	String json2 = GsonUtil.toJson(name);
    	String json3 = GsonUtil.toJson(nameArray);
    	System.out.println(json);
    	System.out.println(json2);
    	System.out.println(json3);
    }
    
    @Test 
    public void testPrettyPrintingByGson(){
    	GsonBuilder gsonBuilder = new GsonBuilder();
    	
    	//优化打印格式同时转化特殊字符
    	Gson prettyGson = gsonBuilder.setPrettyPrinting()
    			.disableHtmlEscaping()
    			.create();
    	
    	List<VehicleTypeVO> vehicleTypes = 
    			vehicleTypeDAO.findVehicleTypeByProductId("3323D2AC-11EF-4D6E-ADF0-75E812335B13");

    	String json = prettyGson.toJson(vehicleTypes);
    	System.out.println(json);
    	
    	//转回
        Type typeOfT = new TypeToken<List<VehicleTypeVO>>(){}.getType();
    	List<VehicleTypeVO> thransBackResult = prettyGson.fromJson(json, typeOfT);

    	//测试新的Gson对象（没有做给GsonBuilder加条件的），结果也可以转成功
    	Gson gson = new Gson();
    	List<VehicleTypeVO> thransBackResult2 = gson.fromJson(json, typeOfT);

    	System.out.println(thransBackResult.get(0).getName());
    	System.out.println(thransBackResult2.get(0).getName());
    }
    
    @Test
    public void testItemVO2JsonByGson()
    {
		Gson gson = new Gson();

		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setProductID("01D5B540-FB86-41A8-92AA-FF5E79E72343");
		List<ItemVO> list = itemDAO.findItem(itemDTO);
		for (ItemVO itemVO : list) {
			ItemDetailDTO itemDetailDTO = new ItemDetailDTO();
			itemDetailDTO.setItemID(itemVO.getMainID());
			
			//嵌套入list也能正常转化
			itemVO.setItemDetailList(itemDAO.findItemDetail(itemDetailDTO));
			
			//itemVO to json
			String json = gson.toJson(itemVO);
			System.out.println(json);
			
			//json to itemVO
			ItemVO item = gson.fromJson(json, ItemVO.class);
			for (ItemDetailVO itemDetail : item.getItemDetailList()){
				System.out.println(itemDetail.getItemPropValue());
			}
		}
		
		//List of ItemVO to json
		String jsonItemVOs = gson.toJson(list);
		System.out.println(jsonItemVOs);
		
		//json to itemVOList
        Type typeOfT = new TypeToken<List<ItemVO>>(){}.getType();
		List<ItemVO> itemJsonList = gson.fromJson(jsonItemVOs, typeOfT);
		for (ItemVO item : itemJsonList){
			System.out.println(item.getName());
		}
    }	   
    
    @Test
    public void testItemVO2JsonByGsonUtil()
    {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setProductID("01D5B540-FB86-41A8-92AA-FF5E79E72343");
		List<ItemVO> list = itemDAO.findItem(itemDTO);
		for (ItemVO itemVO : list) {
			ItemDetailDTO itemDetailDTO = new ItemDetailDTO();
			itemDetailDTO.setItemID(itemVO.getMainID());
			
			//嵌套入list也能正常转化
			itemVO.setItemDetailList(itemDAO.findItemDetail(itemDetailDTO));
			
			//itemVO to json
			String json = GsonUtil.toJson(itemVO,"yyyy-MM-dd HH:mm:ss");
			System.out.println(json);
			
			//json to itemVO
			ItemVO item = GsonUtil.fromJson(json, ItemVO.class);
			for (ItemDetailVO itemDetail : item.getItemDetailList()){
				System.out.println(itemDetail.getItemPropValue());
			}
		}
		
		//List of ItemVO to json
		String jsonItemVOs = GsonUtil.toJson(list);
		System.out.println(jsonItemVOs);
		
		//json to itemVOList
		List<ItemVO> itemJsonList = GsonUtil.fromJson(jsonItemVOs, new TypeToken<List<ItemVO>>(){});
		for (ItemVO item : itemJsonList){
			System.out.println(item.getName());
		}
    }	
}
