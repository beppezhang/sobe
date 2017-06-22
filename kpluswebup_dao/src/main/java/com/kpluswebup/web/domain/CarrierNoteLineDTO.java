package com.kpluswebup.web.domain;

import org.apache.ibatis.type.Alias;

@Alias("carrierNoteLineDTO")
public class CarrierNoteLineDTO {
	
	private Long id;

	private String mainID;
	
	private String carrierNoteID;
	
	private String salesOrderID;

	public String getMainID() {
		return mainID;
	}

	public void setMainID(String mainID) {
		this.mainID = mainID;
	}

	public String getCarrierNoteID() {
		return carrierNoteID;
	}

	public void setCarrierNoteID(String carrierNoteID) {
		this.carrierNoteID = carrierNoteID;
	}

	public String getSalesOrderID() {
		return salesOrderID;
	}

	public void setSalesOrderID(String salesOrderID) {
		this.salesOrderID = salesOrderID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
