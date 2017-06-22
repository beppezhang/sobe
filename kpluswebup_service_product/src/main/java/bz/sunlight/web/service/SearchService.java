package bz.sunlight.web.service;

public interface SearchService {

	//根据输入内容检索
	public String findSearchResult(String type, String str, int pageIndex, int pageSize);
	
	//获取指定场景的历史搜索项
	public String findSearchHistory(String customerId, String scenario, String type);

	//清空指定场景的历史搜索项
	public String cleanSearchHistory(String scenario, String type);
}
