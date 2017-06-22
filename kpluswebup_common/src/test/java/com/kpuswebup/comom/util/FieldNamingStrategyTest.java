package com.kpuswebup.comom.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class FieldNamingStrategyTest {

	class Demo{
		@JsonExclusion(ExclusionType.PC)
		private String name = "lby";

		@JsonExclusion(ExclusionType.MOBILE)
		private int age = 22;
		
		@JsonExclusion(ExclusionType.PC_MOBILE)
		private String sex = "male";

		public Demo(String name,int age,String sex){
			this.name = name;
			this.age = age;
			this.sex = sex;
		}
		public Demo(){
			
		}

		@Override
		public String toString() {
			return String.format("(name=%s,age=%d,sex=%s)",name,age,sex);
		}
	}

	@Test
	public void testJsonFieldNamingStrategy(){
		//Object转Json
		Map<String,String> exclusionFieldsMap = new HashMap<String,String>();
		exclusionFieldsMap.put("name", "NaMe");
		exclusionFieldsMap.put("age", "aGe");
		exclusionFieldsMap.put("sex", "SeX");
		
		String json = GsonUtil.toJson(new Demo("binye",23,"female"), new GsonUtil.JsonFieldNamingStrategy(exclusionFieldsMap));
		System.out.println(json);
		
		//GsonUtil 将 Json 回转 Object
		Demo demo = GsonUtil.fromJson(json, Demo.class);
		System.out.println(demo);
		
		//传入新策略后回转
		Demo demo2 = GsonUtil.fromJson(json, Demo.class, new GsonUtil.JsonFieldNamingStrategy(exclusionFieldsMap), null);
		System.out.println(demo2);
	}
	
}
