package com.kpluswebup.web.vo;

import org.apache.ibatis.type.Alias;

@Alias("productItemVO")
public class ProductItemVO {

	private String mainID;

	private String name;

	private String oldNumber;

	public String getMainID() {
		return mainID;
	}

	public void setMainID(String mainID) {
		this.mainID = mainID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOldNumber() {
		return oldNumber;
	}

	public void setOldNumber(String oldNumber) {
		this.oldNumber = oldNumber;
	}

}
