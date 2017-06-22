package com.kpluswebup.web.vo;

import org.apache.ibatis.type.Alias;

@Alias("carrierNotePrintGroupLineVO")
public class CarrierNotePrintGroupLineVO {

	private String itemName;
	
	private Double salesPrice;
	
	private Double itemTotalAmount;
	
	private Integer itemTotalCount;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public Double getItemTotalAmount() {
		return itemTotalAmount;
	}

	public void setItemTotalAmount(Double itemTotalAmount) {
		this.itemTotalAmount = itemTotalAmount;
	}

	public Integer getItemTotalCount() {
		return itemTotalCount;
	}

	public void setItemTotalCount(Integer itemTotalCount) {
		this.itemTotalCount = itemTotalCount;
	}
	

}
