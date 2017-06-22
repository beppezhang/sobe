package com.kpuswebup.comom.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.util.StopWatch;

public class TpartsUtils {
	public static void stopWatchStopRunning(StopWatch sw) {
		if (sw.isRunning())
			sw.stop();
	}
	
	//拆成单个汉字或字符
	public static String[] splitStr(String source){
		int strLength = source.length();
        String[] splitStr = new String[strLength];
        for (int i = 0; i < strLength; i++) {  
        	splitStr[i] = source.substring(i, i + 1);  
        }  
		return splitStr;
	}
	
	//将目标中的关键字加入红色字体标签
	public static String addRedFont(String[] TargetArray,String[] keyArray){
        String result = "";//保存最后的拼接结果
        outer: for (int i = 0; i < TargetArray.length; i++){
        	for (int j = 0; j < keyArray.length; j++){
        		if (keyArray[j].equals(TargetArray[i])){
        			result += "<font color='red'>" + TargetArray[i] + "</font>";
        			continue outer;//如果匹配到关键字直接开始下一个目标字符的检验
        		}
        	}
        	result += TargetArray[i];
        }
		return result;
	}
	
	public static boolean verifyFullMatch(String source,String target)
	{
		//boolean flag = target.contains(source) ? true : false; //底层也是调的indexOf方法
		boolean flag = target.indexOf(source) == -1 ? false : true;
		return flag;
	}
	
	//提取目标文件每行的关键数据并去除单引号
	public static void fileTransform(String targetFilePath,String resultFilePath){
		File f1 = new File(targetFilePath);
		File f2 = new File(resultFilePath);

		String line = "";
		try {
			FileReader reader = new FileReader(f1);
			FileWriter writer = new FileWriter(f2);
			BufferedReader br = new BufferedReader(reader);
			BufferedWriter bw = new BufferedWriter(writer);
			while ((line = br.readLine()) != null) {
				int i_values=line.indexOf("VALUES");
				String tempStr = line.substring(line.indexOf("(",i_values)+1, line.lastIndexOf(")"));
				String target = tempStr.replaceAll("'", "").replaceAll(" ", "");//去单引号和空格

				//String target = line.substring(line.indexOf("(",i_values)+1, line.lastIndexOf(")"));
				bw.write(target);
				bw.newLine();
				bw.flush();
			}
			reader.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("transformFinished");
	}
	
	public static void readLines(String targetFilePath){
		File file = new File(targetFilePath);
		List<String> lines = null;
		try {
			lines = FileUtils.readLines(file, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.out.println(lines.size());
	}

	//采用随机流从后往前逐行转化给定数量的数据（速度慢）
	public static void transformFromBack(String targetFilePath,String resultFilePath,int length){
		try {
			// 下面定位文件末行, 一行一行向上读取
			RandomAccessFile raf = new RandomAccessFile(targetFilePath, "r"); // 该类可以定位文件,
			FileWriter writer = new FileWriter(new File(resultFilePath));
			BufferedWriter bw = new BufferedWriter(writer);

			long len = raf.length(); // 获得文件的长度,以便定位末尾
			if (len == 0) { // 判断文件是否为空
				System.out.println("the flie is NULL!");
				raf.close(); // 关闭
				writer.close();
				return;
			}
			
			long pos = len - 1; // 定位文件尾
			long readLineCount = 0; //文件读取到的行数
			while (pos >= 0) { // 判断文件是否到达头
				raf.seek(pos); // 定位文件指针所指的位置
				--pos; // 一个字符一个字符的向前移动指针
								
				// 如果是换行符或者第一个字符,就可以读取该行了
				if (raf.readByte() == '\n' || pos == -1) { 
					String line = raf.readLine();
					if (line != null){
//						int i_values = line.indexOf("VALUES");
//						String tempStr = line.substring(line.indexOf("(",i_values)+1, line.lastIndexOf(")"));
						String tempStr = line.substring(line.lastIndexOf("(")+1, line.lastIndexOf(")"));
						String target = tempStr.replaceAll("'", "");//去单引号
						target = target.replaceAll(" ", "");	

						//String target = line.substring(line.indexOf("(",i_values)+1, line.lastIndexOf(")"));
						bw.write(target);
						bw.newLine();
						bw.flush();
						
						++readLineCount;
						if (readLineCount == length)
							break;
					}
				}
			}
			raf.close(); // 关闭
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("transformFinished");
		return;
	}
}
