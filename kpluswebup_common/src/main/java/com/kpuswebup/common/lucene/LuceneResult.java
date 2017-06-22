package com.kpuswebup.common.lucene;

import java.util.List;

public class LuceneResult {
	private long hitsPerPage;
	private List<String> mainIDList;
	public long getHitsPerPage() {
		return hitsPerPage;
	}
	public void setHitsPerPage(long hitsPerPage) {
		this.hitsPerPage = hitsPerPage;
	}
	public List<String> getMainIDList() {
		return mainIDList;
	}
	public void setMainIDList(List<String> mainIDList) {
		this.mainIDList = mainIDList;
	}
}
