package com.kpuswebup.common.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.springframework.util.StopWatch;

import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.TpartsUtils;

public class LuceneSearch {
	
	static StopWatch sw  = new StopWatch();
	
	public static enum IndexType {
		PRODUCT, PARTSCATEGORY_PRODUCT, VEHICLETYPE_PRODUCT, VEHICLETYPE_PARTSCATEGORY_PRODUCT
	}

	private static Sort sorter;
	static
	{
		sorter = new Sort(); 
		sorter.setSort(new SortField(LuceneField.FIELD_FPOSITION, SortField.Type.STRING, false));		
	}
	
	public static BooleanQuery.Builder buildBooleanQuery(String[] fields,
			String[] values, Analyzer analyzer) {
		BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
		TermQuery tq = null;
		Query query = null;
		try {
			for (int i = 0; i < fields.length; i++) {
				if (i == 0) {
					if(!values[i].equals(LuceneField.FIELD_NAME_NONE))
					{
						query = new QueryParser(fields[i], analyzer)
						.parse(values[i]);

						booleanQuery.add(query, BooleanClause.Occur.MUST);						
					}
				} else {
					tq = new TermQuery(new Term(fields[i], values[i]));
					booleanQuery.add(new BooleanClause(tq,
							BooleanClause.Occur.MUST));

				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return booleanQuery;
	}

	public static TopDocs query(IndexSearcher searcher, String[] fields,
			String[] values, Analyzer analyzer, int hitsPerPage) {
		try {
			return searcher.search(buildBooleanQuery(fields, values, analyzer)
					.build(), hitsPerPage,sorter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static LuceneResult query(IndexType indexType, String[] fields,
			String[] values, Analyzer analyzer, int hitsPerPage,long startRow,long endRow,String resultKey) {
		LuceneResult rssult = new LuceneResult();
		StopWatch stopWatchLucene = new StopWatch();
		stopWatchLucene.start("beging get IndexSearcher");		
		try {
			IndexSearcher searcher = null;
			switch (indexType) {
			case PRODUCT:
				searcher = LuceneWriter.getInstance().getSearcherByProduct();
				break;
			case PARTSCATEGORY_PRODUCT:
				searcher = LuceneWriter.getInstance().getSearcherByPT();
				break;
			case VEHICLETYPE_PRODUCT:
				searcher = LuceneWriter.getInstance().getSearcherByVT();
				break;
			case VEHICLETYPE_PARTSCATEGORY_PRODUCT:
				searcher = LuceneWriter.getInstance().getSearcherByVPT();
				break;
			default:
				break;
			}			
			TpartsUtils.stopWatchStopRunning(stopWatchLucene);  
			stopWatchLucene.start("beging query TopDocs");
			TopDocs docs = searcher.search(buildBooleanQuery(fields, values, analyzer)
					.build(), hitsPerPage,sorter);//,sorter
			ScoreDoc[] hits = docs.scoreDocs;    
			Document d = null;
			long _hitsPerPage = hits.length;
			List<String> mainIDList = new ArrayList<String>();
			List<String> mainIDListNoFPosition = new ArrayList<String>();
//			productDTO.doPage(_hitsPerPage, productDTO.getPageNo(), productDTO.getPageSize());
			System.out.println(startRow);
			System.out.println(endRow);
			TpartsUtils.stopWatchStopRunning(stopWatchLucene);  
			stopWatchLucene.start("ScoreDoc[] hits = docs.scoreDocs for mainIDs");
//			for (int i = 0; i < hits.length; ++i) {
//				if(startRow <= i && i<endRow)
//				{
//					int docId = hits[i].doc;
//					d = searcher.doc(docId);
//					System.out.println(d.toString()); 
////					if(StringUtil.isEmpty(d.get(LuceneField.FIELD_FPOSITION)))
////					{
////						mainIDListNoFPosition.add(d.get(resultKey));
////					}else
////					{
////						mainIDList.add(d.get(resultKey));	
////					}
//					mainIDList.add(d.get(resultKey));	
//				}
//				if(i == endRow)
//				{
//					break;
//				}
//
//			}			
			/*实现无位置数据全部转移到最后*/
			sw.start("  256 -->256   ");
			List<String> partsIds = sort(hits,searcher);
			sw.stop();
			System.out.println(sw.prettyPrint());
			TpartsUtils.stopWatchStopRunning(sw);
			for (int i = 0; i < partsIds.size(); ++i) {
				if(startRow <= i && i<endRow)
				{
					mainIDList.add(partsIds.get(i));	
				}
				if(i == endRow)
				{
					break;
				}				
			}
			
			//无位置信息的数据排在后面
//			mainIDList.addAll(mainIDListNoFPosition);
			rssult.setHitsPerPage(_hitsPerPage);
			rssult.setMainIDList(mainIDList);
			TpartsUtils.stopWatchStopRunning(stopWatchLucene);  
			System.out.println(stopWatchLucene.prettyPrint());	
			
			return rssult;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}	
	
	private static List<String> sort(ScoreDoc[] hits,IndexSearcher searcher) throws IOException 
	{
		List<String> partsIds = new ArrayList<String>();
		List<String> _partsIds = new ArrayList<String>();
		Document d = null;
		for (int i = 0; i < hits.length; ++i) {
			d = searcher.doc(hits[i].doc);
			System.out.println(d.toString()); 
			if(StringUtil.isEmpty(d.get(LuceneField.FIELD_FPOSITION)))
			{
				_partsIds.add(d.get(LuceneField.FIELD_PRODUCTID));
			}else
			{
				partsIds.add(d.get(LuceneField.FIELD_PRODUCTID));
			}
		}
		partsIds.addAll(_partsIds);
		return partsIds;
	}
	
}
