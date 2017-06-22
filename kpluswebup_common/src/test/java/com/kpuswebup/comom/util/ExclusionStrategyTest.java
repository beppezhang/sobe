package com.kpuswebup.comom.util;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kpuswebup.comom.util.ExclusionType;
import com.kpuswebup.comom.util.GsonUtil;
import com.kpuswebup.comom.util.GsonUtil.JsonExclusionStrategy;
import com.kpuswebup.comom.util.GsonUtil.JsonExclusionStrategy.TransformType;
import com.kpuswebup.comom.util.JsonExclusion;


public class ExclusionStrategyTest {

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
	public void testJsonExclusionStrategy(){
		//Gson原生方式转json（成功）
        Gson gson = new GsonBuilder().setExclusionStrategies(new GsonUtil.JsonExclusionStrategy(TransformType.MOBILE)).create();    
        String result = gson.toJson(new Demo());
        System.out.println(result);
        
        //原生方式json回转对象（成功）
        Demo demo = gson.fromJson(result, Demo.class);
        System.out.println(demo);
        
        //调用GsonUtil工具类，互转也能成功，因为不涉及字段名的变更
        String json = GsonUtil.toJson(new Demo(), new GsonUtil.JsonExclusionStrategy(TransformType.PC));
        System.out.println(json);
        
        Demo demo2 = GsonUtil.fromJson(json, Demo.class);
        System.out.println(demo2);
	}
}
