package com.kpuswebup.comom.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TpartsUtilsTest {

	static String source = null;
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
		source = "测试B字符A23";
		source = "板 灯";
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
	public void splitStr()
	{
		String [] results = TpartsUtils.splitStr(source);
		//String [] actuals ={"测","试","B","字","符","A","2","yyu"};
		String[] actuals = {"板"," ","灯"};
		Assert.assertArrayEquals("拆分字符串错误",results, actuals);
	}
	
	@Test
	public void addRedFont()
	{
		String[] TargetArray = {"测","试","B","字","符","A","2","3"};
		String[] keyArray = {"B","A"};
		String actual = "测试<font color='red'>B</font>字符<font color='red'>A</font>23";
		String result = TpartsUtils.addRedFont(TargetArray, keyArray);
		Assert.assertEquals("添加红色字体标签错误", result, actual);
	}
	
	@Test
	public void verifyFullMatch()
	{
		String source = "全部";
		String target = "全部匹配";
		boolean actual = TpartsUtils.verifyFullMatch(source, target);
		Assert.assertTrue(actual);
		source = "全部都";
		actual = TpartsUtils.verifyFullMatch(source, target);
		Assert.assertFalse(actual);
	}
	
	@Test
	public void fileTransform(){
		String targetFilePath = "D:/partscategory_kplus_product.sql";
		String resultFilePath = "D:/test01.csv";

//		String targetFilePath = "D:/vehicletype_kplus_product_all.sql";
//		String resultFilePath = "D:/test03.cvs";
		
		TpartsUtils.fileTransform(targetFilePath,resultFilePath);
		
//		String targetFilePath = "D:/partscategory_kplus_product.sql";
//		String resultFilePath = "D:/partscategory_kplus_product";
//		TpartsUtils.fileTransform(targetFilePath, resultFilePath);
//		
//		File file = new File("D:/partscategory_kplus_product.sql");
//		List<String> lines = null;
//		try {
//			lines = FileUtils.readLines(file, "UTF-8");
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
//		System.out.println(lines.size());
/*		
		File targetFile = FileUtils.getFile("D:/insert-into-to-load-data-infile.txt");
		
		LineIterator iter = null;
		try {
			iter = FileUtils.lineIterator(targetFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//逐行遍历
		while (iter.hasNext()){
			System.out.println(iter.next());
		}
*/
	}
	
	@Test
	public void transformFromBack(){
//		String targetFilePath = "D:/partscategory_kplus_product.sql";
//		String resultFilePath = "D:/test02.csv";
//		int searchLength = 200;
		
//		String targetFilePath = "D:/vehicletype_kplus_product_all.sql";
//		String resultFilePath = "D:/vehicletype_kplus_product_all.cvs";
//		int searchLength = 3000000;
//	
//		TpartsUtils.transformFromBack(targetFilePath, resultFilePath, searchLength);
	}

//	@Test
	public void testSeek(){
		try {
			// 下面定位文件末行, 一行一行向上读取
			RandomAccessFile raf = new RandomAccessFile("D:/partscategory_kplus_product.sql", "r"); // 该类可以定位文件,
			
			long len = raf.length(); // 获得文件的长度,以便定位末尾

			long pos = len - 1; // 定位文件尾
			while (pos >= 0) { // 判断文件是否到达头
				raf.seek(pos); // 定位文件指针所指的位置
				--pos; // 一个字符一个字符的向前移动指针
				if (raf.readByte() == '\n' || pos == -1) { // 如果是换行符或者第一个字符,就可以读取该行了
					System.out.println(raf.readLine());
				}
			}

			//raf.seek(pos); // 最后还需要读取第一行(改版后的循环已经可以读到第一行了)
			//System.out.println(raf.readLine());
			raf.close(); // 关闭
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	
}
