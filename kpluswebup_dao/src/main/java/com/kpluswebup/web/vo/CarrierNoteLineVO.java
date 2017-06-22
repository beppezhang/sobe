package com.kpluswebup.web.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("carrierNoteLineVO")
public class CarrierNoteLineVO {
	
	private String mainID;
	
	private String carrierNoteID;
	
	private String salesOrderID;
	
	private List<CarrierNoteLineDetailVO> carrierNoteLineDetailList;

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

	public List<CarrierNoteLineDetailVO> getCarrierNoteLineDetailList() {
		return carrierNoteLineDetailList;
	}

	public void setCarrierNoteLineDetailList(
			List<CarrierNoteLineDetailVO> carrierNoteLineDetailList) {
		this.carrierNoteLineDetailList = carrierNoteLineDetailList;
	}
	
	
}
