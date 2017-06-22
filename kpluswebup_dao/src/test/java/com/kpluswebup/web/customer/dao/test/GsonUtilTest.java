package com.kpluswebup.web.customer.dao.test;

import java.awt.Point;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.kpluswebup.web.vo.ItemVO;
import com.kpuswebup.common.vo.BaseVO;
import com.kpuswebup.comom.util.GsonUtil;



public class GsonUtilTest {

	class Demo{
		@SerializedName(value = "Name")
		private String name = "lby";

		@SerializedName(value = "name")
		private int age = 22;
		public Demo(String name,int age){
			this.name = name;
			this.age = age;
		}
		public Demo(){
			
		}

		@Override
		public String toString() {
			return String.format("(name=%s,age=%d)",name,age);
		}
		
		
	}
	
	class Person implements Serializable{

		private static final long serialVersionUID = 2307785397002015929L;

		private String name;
		private int age;
		private Date createTime;

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
	}
	
	class Adult extends Person{
		private static final long serialVersionUID = 2307785397002015930L;

		private String coupleName;

		public String getCoupleName() {
			return coupleName;
		}

		public void setCoupleName(String coupleName) {
			this.coupleName = coupleName;
		}
	}
	
	class BaseTest extends BaseVO implements Serializable {
		private static final long serialVersionUID = 2307785397002015931L;
		
		private String testName;
		private Date createTime;

		public String getTestName() {
			return testName;
		}

		public void setTestName(String testName) {
			this.testName = testName;
		}
		
		public Date getCreateTime() {
			return createTime;
		}
		
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
	}

	class BaseTest2 extends BaseVO implements Serializable {
		private Date testDate;

		public Date getTestDate() {
			return testDate;
		}

		public void setTestDate(Date testDate) {
			this.testDate = testDate;
		}
	}
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {

    }
    @Before
    public void setUp() throws Exception
    {
    
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
    public void toJsonOfGsonUtil() {
    	//简单类转化（成功）
		Demo demo = new Demo();
    	String result1 = GsonUtil.toJson(demo,Demo.class);
    	System.out.println(result1);
		
		//带复杂属性Date的类的转化（成功）
		Person person = new Person();
		person.setAge(22);
		person.setName("bruce");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//当前日期
		String formatDateStr= dateFormat.format(new Date());
		try {
			Date formatDate = dateFormat.parse(formatDateStr);
			person.setCreateTime(formatDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result2 = GsonUtil.toJson(person, "yyyy-MM-dd HH:mm:ss");//对person中的日期格式也能成功转化
		System.out.println(result2);

		//继承类转化（成功）
		Adult adult = new Adult();
		adult.setAge(25);
		adult.setName("bruce");
		adult.setCoupleName("lw");
		adult.setCreateTime(new Date());
		String result3 = GsonUtil.toJson(adult,"yyyy-MM-dd HH:mm");
		System.out.println(result3);
		
		//系统中的简单类itemVO的转化（成功，同时省略了值为空的属性显示）
		ItemVO itemVO = new ItemVO();
		itemVO.setName("bruce");
		String result4 = GsonUtil.toJson(itemVO);
		System.out.println(result4);

		//容器转化（成功）
	    Collection collection = new ArrayList();
	    collection.add("hello");
	    collection.add(5);
	    collection.add(new Demo("lby",22));
	    String result5 = GsonUtil.toJson(collection);
	    System.out.println("Using GsonUtil.toJson() on a raw collection: " + result5);
	    
	    //传空返回{}而直接调gson.toJson返回的依然是null
	    String result6 = GsonUtil.toJson(null);
	    System.out.println(result6);
	    
	    //空的list转返回[]
	    Collection<String> list = new ArrayList<String>();
	    String result7 = GsonUtil.toJson(list);
	    System.out.println(result7);
	}

	@Test
	public void toJsonOfGson()
	{
		Gson gson = new Gson();

		//简单类转化（成功）
		Demo demo = new Demo();
		String result2 = gson.toJson(demo);
		System.out.println(result2);

		Date date = new Date();
		String result3 = gson.toJson(date);
		System.out.println(result3);
		
		//带复杂属性Date的类的转化（成功）
		Person person = new Person();
		person.setAge(22);
		person.setName("bruce");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//当前日期
		String formatDateStr= dateFormat.format(new Date());
		try {
			Date formatDate = dateFormat.parse(formatDateStr);
			person.setCreateTime(formatDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result4 = gson.toJson(person);//对person中的日期无法进行格式化转化
		System.out.println(result4);
/*
		//带重复日期的类的转化（抛 declares multiple JSON fields named createTime异常）
		BaseTest baseTest = new BaseTest();
		baseTest.setTestName("baseTest");
		baseTest.setCreateTime(person.getCreateTime());
		String result5 = gson.toJson(baseTest);
		System.out.println(result5);

*/
		//不带重复日期的类的转化（成功）
		BaseTest2 baseTest2 = new BaseTest2();
		baseTest2.setTestDate(person.getCreateTime());
		String result6 = gson.toJson(baseTest2);
		System.out.println(result6);
		
		//继承类转化（成功）
		Adult adult = new Adult();
		adult.setAge(25);
		adult.setName("bruce");
		adult.setCoupleName("lw");
		adult.setCreateTime(new Date());
		String result7 = gson.toJson(adult);
		System.out.println(result7);

		//系统中的简单类itemVO的转化（成功，同时省略了值为空的属性显示）
		ItemVO itemVO = new ItemVO();
		itemVO.setName("bruce");
		String result8 = gson.toJson(itemVO);
		System.out.println(result8);
				
		//容器转化（成功）
	    Collection collection = new ArrayList();
	    collection.add("hello");
	    collection.add(5);
	    collection.add(new Demo("lby",22));
	    String json = gson.toJson(collection);
	    System.out.println("Using Gson.toJson() on a raw collection: " + json);
	}
	
	
	@Test
	public void fromJsonOfGson(){
		Gson gson = new Gson();

		//一般数组转化
        int[] numbers = {1, 1, 2, 3, 5, 8, 13};
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        String numbersJson = gson.toJson(numbers);
        String daysJson = gson.toJson(days);

        System.out.println("numbersJson = " + numbersJson);
        System.out.println("daysJson = " + daysJson);

        int[] fibonacci = gson.fromJson(numbersJson, int[].class);
        for (int i = 0; i < fibonacci.length; i++) {
            System.out.print(fibonacci[i] + " ");
        }
        System.out.println("");

        String[] weekDays = gson.fromJson(daysJson, String[].class);
        for (int i = 0; i < weekDays.length; i++) {
            System.out.print(weekDays[i] + " ");
        }
        System.out.println("");
		
        //常规list转化
        List<Demo> demos = new ArrayList<Demo>();
        demos.add(new Demo("binye",21));
        demos.add(new Demo("lby",22));
        demos.add(new Demo("wht",23));
        String jsonDemos = gson.toJson(demos);
        System.out.println("jsonDemos = " + jsonDemos);
        
        //这里没办法直接像String和int一样传String[].class进去改用Type（注意正确导包）
        Type typeOfT = new TypeToken<List<Demo>>(){}.getType();
        List<Demo> demoList = gson.fromJson(jsonDemos, typeOfT);
        for (Demo demo : demoList){
        	System.out.println(demo);
        }
        	
		//容器转化
	    Collection collection = new ArrayList();
	    collection.add("hello");
	    collection.add(5);
	    collection.add(new Demo("lby",22));
	    String json = gson.toJson(collection);

	    JsonParser parser = new JsonParser();
	    JsonArray array = parser.parse(json).getAsJsonArray();
	    String message = gson.fromJson(array.get(0), String.class);
	    int number = gson.fromJson(array.get(1), int.class);
	    Demo demoResult = gson.fromJson(array.get(2), Demo.class);
	    System.out.printf("Using Gson.fromJson() to get: %s, %d, %s", message, number, demoResult);
	}
	
	@Test
	public void fromJsonOfGsonUtil(){
		//一般数组转化
        int[] numbers = {1, 1, 2, 3, 5, 8, 13};
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        String numbersJson = GsonUtil.toJson(numbers);
        String daysJson = GsonUtil.toJson(days);

        System.out.println("numbersJson = " + numbersJson);
        System.out.println("daysJson = " + daysJson);

        int[] fibonacci = GsonUtil.fromJson(numbersJson, int[].class);
        for (int i = 0; i < fibonacci.length; i++) {
            System.out.print(fibonacci[i] + " ");
        }
        System.out.println("");

        String[] weekDays = GsonUtil.fromJson(daysJson, String[].class);
        for (int i = 0; i < weekDays.length; i++) {
            System.out.print(weekDays[i] + " ");
        }
        System.out.println("");
		
        //常规list转化
        List<Demo> demos = new ArrayList<Demo>();
        demos.add(new Demo("binye",21));
        demos.add(new Demo("lby",22));
        demos.add(new Demo("wht",23));
        String jsonDemos = GsonUtil.toJson(demos);
        System.out.println("jsonDemos = " + jsonDemos);
        
        //这里没办法直接像String和int一样传String[].class进去改用Type（注意正确导包）
        List<Demo> demoList = GsonUtil.fromJson(jsonDemos, new TypeToken<List<Demo>>(){});
        for (Demo demo : demoList){
        	System.out.println(demo);
        }
        
        //关键字为复杂类型的Map的互转(底层用enableComplexMapKeySerialization 对 GsonBuilder进行了配置)
        Map<Point, String> map1 = new LinkedHashMap<Point, String>();// 使用LinkedHashMap将结果按先进先出顺序排列  
        map1.put(new Point(5, 6), "a");  
        map1.put(new Point(8, 8), "b");  
        String s = GsonUtil.toJson(map1);  
        System.out.println(s);// 结果:[[{"x":5,"y":6},"a"],[{"x":8,"y":8},"b"]]  
  
        Map<Point, String> retMap = GsonUtil.fromJson(s,  
                new TypeToken<Map<Point, String>>(){});  
        for (Point p : retMap.keySet()) {  
            System.out.println("key:" + p + " values:" + retMap.get(p));  
        }  
        System.out.println(retMap);  

        //key不为复杂类型的Map跟一般类型转json没区别虽然在封装的时候同上统一让GsonBuilder 调了 enableComplexMapKeySerialization
        System.out.println("----------------------------------");  
        Map<String, Point> map2 = new LinkedHashMap<String, Point>();  
        map2.put("a", new Point(3, 4));  
        map2.put("b", new Point(5, 6));  
        String s2 = GsonUtil.toJson(map2);  
        System.out.println(s2);  
  
        Map<String, Point> retMap2 = GsonUtil.fromJson(s2,  
                new TypeToken<Map<String, Point>>(){});  
        for (String key : retMap2.keySet()) {  
            System.out.println("key:" + key + " values:" + retMap2.get(key));  
        }  
        
		//容器转化(GsonUtil 暂不支持，如特别需要还是直接调下面更底层的方法)
	    Collection collection = new ArrayList();
	    collection.add("hello");
	    collection.add(5);
	    collection.add(new Demo("lby",22));
	    String json = GsonUtil.toJson(collection);

	    JsonParser parser = new JsonParser();
	    JsonArray array = parser.parse(json).getAsJsonArray();
	    
	    Gson gson = new Gson();
	    String message = gson.fromJson(array.get(0), String.class);
	    int number = gson.fromJson(array.get(1), int.class);
	    Demo demoResult = gson.fromJson(array.get(2), Demo.class);
	    System.out.printf("Using Gson.fromJson() to get: %s, %d, %s", message, number, demoResult);
	}
	
	@Test
	public void enableComplexMapKeySerializationTest(){
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization()  
                .create();  
        Gson gson2 = new GsonBuilder().create();  
  
        Map<Point, String> map1 = new LinkedHashMap<Point, String>();// 使用LinkedHashMap将结果按先进先出顺序排列  
        map1.put(new Point(5, 6), "a");  
        map1.put(new Point(8, 8), "b");  
        String s = gson.toJson(map1);  
        System.out.println(s);// 结果:[[{"x":5,"y":6},"a"],[{"x":8,"y":8},"b"]]  

        //用gson2转key为复杂类的map，串行化失败
        String ss = gson2.toJson(map1);  
        System.out.println(ss); //{"java.awt.Point[x\u003d5,y\u003d6]":"a","java.awt.Point[x\u003d8,y\u003d8]":"b"}
 
        //说明反串行化无须对GsonBuilder进行enableComplexMapKeySerialization的申明
        Map<Point, String> retMap = gson2.fromJson(s,  
                new TypeToken<Map<Point, String>>() {  
                }.getType());  
        for (Point p : retMap.keySet()) {  
            System.out.println("key:" + p + " values:" + retMap.get(p));  
        }  
        System.out.println(retMap);  
  
        System.out.println("----------------------------------");  
        Map<String, Point> map2 = new LinkedHashMap<String, Point>();  
        map2.put("a", new Point(3, 4));  
        map2.put("b", new Point(5, 6));  
        String s2 = gson.toJson(map2);  
        System.out.println(s2);  
 
        //用gson2转value为类的map
        String sss = gson2.toJson(map2);  
        System.out.println(sss);   //{"a":{"x":3,"y":4},"b":{"x":5,"y":6}}
        
        Map<String, Point> retMap2 = gson.fromJson(s2,  
                new TypeToken<Map<String, Point>>() {  
                }.getType());  
        for (String key : retMap2.keySet()) {  
            System.out.println("key:" + key + " values:" + retMap2.get(key));  
        }  		
        for (Map.Entry<String, Point> entry : retMap2.entrySet()) {  
            System.out.println("key:" + entry.getKey() + " values:" + entry.getValue());  
        }  		
	}
	
	@Test
	public void capitalizeTest(){
		//把字段首字母大写,注:对于字段上使用了@SerializedName注解的不会生效.  
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)  
                .create();  
        Gson gson2 = new GsonBuilder().create();  
        
		Demo demo = new Demo();
		String json = gson.toJson(demo);
		System.out.println(json);
		Demo demoFromJson = gson.fromJson(json, Demo.class);
		System.out.println(demoFromJson);

		String json2 = gson2.toJson(demo);
		System.out.println(json2);
		
		String json3 = GsonUtil.toJson(demo, FieldNamingPolicy.UPPER_CAMEL_CASE);
		System.out.println(json3);

		String json4 = GsonUtil.toJson(demo, demo.getClass(), false, FieldNamingPolicy.UPPER_CAMEL_CASE);
		System.out.println(json4);
	}

	class StringConverter implements JsonSerializer<String>,
			JsonDeserializer<String> {
		public JsonElement serialize(String src, Type typeOfSrc, 
    JsonSerializationContext context) { 
    if ( src == null ) { 
        return new JsonPrimitive(""); 
    } else { 
        return new JsonPrimitive(src.toString());
    }
    }
		public String deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			return json.getAsJsonPrimitive().getAsString();
		}
	}

	@Test
	public void gsonRegisterAdapterTest(){
		GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(String.class, new StringConverter());
		GsonBuilder Builder = new GsonBuilder();

        Map<String,String> map = new HashMap<String,String>();  
        map.put("111", "ddd");  
        map.put("222", null);  
        map.put("333", null);
        
//        if (map instanceof Map)
//        	gsonBuilder.enableComplexMapKeySerialization();

//		Demo demo = new Demo(null,22);

		gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
//        Builder.serializeNulls();
        Gson gson2 = Builder.create();
//        String json = gson.toJson(demo);
        String json = gson.toJson(map);
        System.out.println(json);
        
        //默认情况下gson是不会将值为null的map中的成员转出来的（会省略掉key）
        String json2 = gson2.toJson(map);
        System.out.println(json2);
	}
}
