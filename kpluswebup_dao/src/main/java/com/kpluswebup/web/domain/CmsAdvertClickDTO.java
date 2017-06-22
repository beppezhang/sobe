package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("cmsAdvertClickDTO")
public class CmsAdvertClickDTO extends BaseDTO {

	private Long id;

	private String advertID;

	private String IP;
	
	private Boolean isCustomer;
	
	private String customerID;
	
	private Date clickTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdvertID() {
		return advertID;
	}

	public void setAdvertID(String advertID) {
		this.advertID = advertID;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public Boolean getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(Boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public Date getClickTime() {
		return clickTime;
	}

	public void setClickTime(Date clickTime) {
		this.clickTime = clickTime;
	}
	
}
