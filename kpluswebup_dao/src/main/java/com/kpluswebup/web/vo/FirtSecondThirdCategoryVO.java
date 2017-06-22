package com.kpluswebup.web.vo;

import org.apache.ibatis.type.Alias;

@Alias("firstSecondThirdCategoryVO")
public class FirtSecondThirdCategoryVO {
	
	private String fistName;
	
	private String secondName;
	
	private String thirdName;
	
	private String firstID;
	
	private String secondID;
	
	private String thirdID;

	public String getFistName() {
		return fistName;
	}

	public void setFistName(String fistName) {
		this.fistName = fistName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getThirdName() {
		return thirdName;
	}

	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}

	public String getFirstID() {
		return firstID;
	}

	public void setFirstID(String firstID) {
		this.firstID = firstID;
	}

	public String getSecondID() {
		return secondID;
	}

	public void setSecondID(String secondID) {
		this.secondID = secondID;
	}

	public String getThirdID() {
		return thirdID;
	}

	public void setThirdID(String thirdID) {
		this.thirdID = thirdID;
	}
	
	
	
	
}
