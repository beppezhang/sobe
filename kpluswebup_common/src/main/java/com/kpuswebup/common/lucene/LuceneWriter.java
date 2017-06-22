package com.kpuswebup.common.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.core.io.ClassPathResource;

import com.kpuswebup.comom.util.PropertiesUtil;
import com.kpuswebup.comom.util.StringUtil;

public class LuceneWriter {
	
	// 整车车型+配件分类+产品
	public static String INDEX_PATH_VEHICLETYPE_PARTSCATEGORY_PRODUCT = System.getProperties().getProperty("os.name").toLowerCase().contains("windows") ? "D:\\lucene_index\\vehicletype_partscategory_product" : "/home/ubuntu/lucene_index/vehicletype_partscategory_product";
	
	private static Directory directory_vehicletype_partscategory_product = null;
	private static DirectoryReader reader_vehicletype_partscategory_product = null;

	// 整车车型+产品
	public static String INDEX_PATH_VEHICLETYPE_PRODUCT = System.getProperties().getProperty("os.name").toLowerCase().contains("windows") ? "D:\\lucene_index\\vehicletype_product" : "/home/ubuntu/lucene_index/vehicletype_product";
	
	private static Directory directory_vehicletype_product = null;
	private static DirectoryReader reader_vehicletype_product = null;

	// 配件分类+产品
	public static String INDEX_PATH_PARTSCATEGORY_PRODUCT = System.getProperties().getProperty("os.name").toLowerCase().contains("windows") ? "D:\\lucene_index\\partscategory_product" : "/home/ubuntu/lucene_index/partscategory_product";

	private static Directory directory_partscategory_product = null;
	private static DirectoryReader reader_partscategory_product = null;
	
	// 仅产品
	public static String INDEX_PATH_PRODUCT = System.getProperties().getProperty("os.name").toLowerCase().contains("windows") ? "D:\\lucene_index\\product" : "/home/ubuntu/lucene_index/product";
	
	private static Directory directory_product = null;
	private static DirectoryReader reader_product = null;
	
	
	private LuceneWriter() {
		try {
			
			setPath();
			
			directory_vehicletype_partscategory_product = FSDirectory.open(new File(INDEX_PATH_VEHICLETYPE_PARTSCATEGORY_PRODUCT).toPath());
			directory_vehicletype_product = FSDirectory.open(new File(INDEX_PATH_VEHICLETYPE_PRODUCT).toPath());
			directory_partscategory_product = FSDirectory.open(new File(INDEX_PATH_PARTSCATEGORY_PRODUCT).toPath());
			directory_product = FSDirectory.open(new File(INDEX_PATH_PRODUCT).toPath());
			
			
			reader_vehicletype_partscategory_product = DirectoryReader.open(directory_vehicletype_partscategory_product);
			reader_vehicletype_product = DirectoryReader.open(directory_vehicletype_product);
			reader_partscategory_product = DirectoryReader.open(directory_partscategory_product);
			reader_product = DirectoryReader.open(directory_product);
			
	        
//            System.out.println(PropertiesUtil.getInstanse().getString("lucene.vehicletype_partscategory_product"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(" FSDirectory can not open");
		}
	}
	
	public static void setPath()
	{
		if(StringUtil.isNotEmpty(PropertiesUtil.getInstanse().getString("lucene.vehicletype_partscategory_product")))
		{
			INDEX_PATH_VEHICLETYPE_PARTSCATEGORY_PRODUCT = PropertiesUtil.getInstanse().getString("lucene.vehicletype_partscategory_product"); 
		}
		if(StringUtil.isNotEmpty(PropertiesUtil.getInstanse().getString("lucene.vehicletype_product")))
		{
			INDEX_PATH_VEHICLETYPE_PRODUCT = PropertiesUtil.getInstanse().getString("lucene.vehicletype_product"); 
		}
		if(StringUtil.isNotEmpty(PropertiesUtil.getInstanse().getString("lucene.partscategory_product")))
		{
			INDEX_PATH_PARTSCATEGORY_PRODUCT = PropertiesUtil.getInstanse().getString("lucene.partscategory_product"); 
		}
		if(StringUtil.isNotEmpty(PropertiesUtil.getInstanse().getString("lucene.product")))
		{
			INDEX_PATH_PRODUCT = PropertiesUtil.getInstanse().getString("lucene.product"); 
		}		
		
	}

	public static void destory() throws IOException
	{
		System.out.println("close DirectoryReader begin");
		if(reader_vehicletype_partscategory_product!=null)
			reader_vehicletype_partscategory_product.close();
		if(reader_vehicletype_product!=null)
			reader_vehicletype_product.close();
		if(reader_partscategory_product!=null)
			reader_partscategory_product.close();
		if(reader_product!=null)
			reader_product.close();
		System.out.println("close DirectoryReader end");
		
		System.out.println("close Directory begin");
		if(directory_vehicletype_partscategory_product!=null)
			directory_vehicletype_partscategory_product.close();
		if(directory_vehicletype_product!=null)
			directory_vehicletype_product.close();
		if(directory_partscategory_product!=null)
			directory_partscategory_product.close();
		if(directory_product!=null)
			directory_product.close();		
		System.out.println("close Directory end");
		
	}
	
	private static class LuceneWriterSingletonHolder {
		static LuceneWriter instance = new LuceneWriter();
	}

	public static LuceneWriter getInstance() {
		return LuceneWriterSingletonHolder.instance;
	}
	
	/**
	 * 返回 整车车型+配件分类+产品
	 * @return
	 */
	public IndexSearcher getSearcherByVPT()
	{
		return new IndexSearcher(reader_vehicletype_partscategory_product);
	}
	/**
	 * 返回 整车车型+配件分类+产品
	 * @return
	 */	
	public DirectoryReader getReaderByVPT()
	{
		return reader_vehicletype_partscategory_product;
	}
	/**
	 * 返回整车车型+产品
	 * @return
	 */
	public IndexSearcher getSearcherByVT()
	{
		return new IndexSearcher(reader_vehicletype_product);
	}
	/**
	 * 返回整车车型+产品
	 * @return
	 */	
	public DirectoryReader getReaderByVT()
	{
		return reader_vehicletype_product;
	}	
	/**
	 * 返回 配件分类+产品
	 * @return
	 */
	public IndexSearcher getSearcherByPT()
	{
		return new IndexSearcher(reader_partscategory_product);
	}
	/**
	 * 返回 配件分类+产品
	 * @return
	 */	
	public DirectoryReader getReaderByPT()
	{
		return reader_partscategory_product;
	}	
	/**
	 * 返回 产品
	 * @return
	 */
	public IndexSearcher getSearcherByProduct()
	{
		return new IndexSearcher(reader_product);
	}	
	/**
	 * 返回 产品
	 * @return
	 */	
	public DirectoryReader getReaderByProduct()
	{
		return reader_product;
	}		
}
