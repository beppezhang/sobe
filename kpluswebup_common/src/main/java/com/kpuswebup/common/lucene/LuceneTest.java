package com.kpuswebup.common.lucene;

import java.io.IOException;
import java.util.Properties;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class LuceneTest {
	public void testProduct() throws IOException
	{
		IndexSearcher searcher = LuceneWriter.getInstance()
				.getSearcherByProduct();
		
//		String[] fields = new String[] { "name","productID", "brandID"};
//		String[] values = new String[] { "安装","8EF25208-65C1-4A69-8309-A2E88B6B8CEB","CE90A9ED-224C-4466-86D7-37B692DA8D29" };
		String[] fields = new String[] { "name"};
		String[] values = new String[] { "说明"};		
		TopDocs docs = LuceneSearch.query(searcher, fields, values,
				new StandardAnalyzer(), 1000000);
		ScoreDoc[] hits = docs.scoreDocs;

		System.out.println("Found " + hits.length + " hits.");
//		for (int i = 0; i < hits.length; ++i) {
//			int docId = hits[i].doc;
//			Document d = searcher.doc(docId);
//			System.out.println((i + 1) + ". " + d.get("name") + "\t"
//					+ d.get("productID") + " \t\t\t\t" + d.get("brandID"));
//			// System.out.println((i + 1) + ". " + d.get("title") + "\t"
//			// + d.get("isbn") + " \t\t\t\t" + d.get("code"));
//		}
		System.out.println("Found " + hits.length + " hits.");		
	}
	public void testProductAndPartscategory() throws IOException
	{
		IndexSearcher searcher = LuceneWriter.getInstance()
				.getSearcherByPT();
//		String[] fields = new String[] { "name","productID", "brandID"};
//		String[] values = new String[] { "安装","8EF25208-65C1-4A69-8309-A2E88B6B8CEB","CE90A9ED-224C-4466-86D7-37B692DA8D29" };
		String[] fields = new String[] { "name","partsCategoryID"};
		String[] values = new String[] { "说明","B6084BFF-DDC0-4DD5-86D9-159AAA50483D"};		
		TopDocs docs = LuceneSearch.query(searcher, fields, values,
				new StandardAnalyzer(), 1000000);
		ScoreDoc[] hits = docs.scoreDocs;

		System.out.println("Found " + hits.length + " hits.");
		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			System.out.println((i + 1) + ". " + d.get("name") + "\t"
					+ d.get("productID") + " \t\t\t\t" + d.get("brandID")+ " \t\t\t\t" + d.get("partsCategoryID"));
			// System.out.println((i + 1) + ". " + d.get("title") + "\t"
			// + d.get("isbn") + " \t\t\t\t" + d.get("code"));
		}
		System.out.println("Found " + hits.length + " hits.");		
	}
	
	public static void main(String[] args) throws IOException {
		Properties props=System.getProperties(); //获得系统属性集    
		String osName = props.getProperty("os.name"); //操作系统名称    
		String osArch = props.getProperty("os.arch"); //操作系统构架    
		String osVersion = props.getProperty("os.version"); //操作系统版本 
		System.out.println(osName);
		System.out.println(osArch);
		System.out.println(osVersion);
		String st = "Windows 7";
		System.out.println(st.toLowerCase().contains("windows"));
//		IndexSearcher searcher = LuceneWriter.getInstance()
//				.getSearcherByProduct();
		// 531772 合并后数据  正确应该是  2919
		new LuceneTest().testProduct();
	}	
}
