package com.kpluswebup.web.vo;

import org.apache.ibatis.type.Alias;

@Alias("carrierNoteLineDetailVO")
public class CarrierNoteLineDetailVO {

	private String mainID;
	
	private String carrierNoteLineID;
	
	private String itemID;
	
	private Double salesPrice;
	
	private Integer count;
	
	private Double totalAmount;

	public String getMainID() {
		return mainID;
	}

	public void setMainID(String mainID) {
		this.mainID = mainID;
	}

	public String getCarrierNoteLineID() {
		return carrierNoteLineID;
	}

	public void setCarrierNoteLineID(String carrierNoteLineID) {
		this.carrierNoteLineID = carrierNoteLineID;
	}


	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	
	
}
