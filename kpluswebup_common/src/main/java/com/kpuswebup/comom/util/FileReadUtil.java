package com.kpuswebup.comom.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class FileReadUtil {

	public static void main(String[] args) {
		FileReadUtil file=new FileReadUtil();
		file.readTxt();
		//file.mapTest();
	}
	
	//读取txt格式文件里的数据
	public void readTxt() {
		try {
			File file=new File("D:/fileTest/test.csv");
			LineIterator it;
			it = FileUtils.lineIterator(file);
			List<Map<String,String>> list;
			Map<String,String> map=null;
				if(it.hasNext()){
					String headerLine=it.nextLine();
					String[] header=headerLine.split("\\s+");
					//System.out.println(header[1]);
					while(it.hasNext()){
						String bodyLine=it.nextLine();
						String[] body=bodyLine.split("\\s+");
						map=new HashMap<String, String>();
						for(int i=0;i<header.length;i++){
							map.put(header[i], body[i]);
						}
						list=new ArrayList<Map<String,String>>();
						list.add(map);	
						System.out.println(list);
					}
					
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
