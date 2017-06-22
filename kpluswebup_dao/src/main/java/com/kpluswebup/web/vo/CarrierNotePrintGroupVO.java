package com.kpluswebup.web.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("carrierNotePrintGroupVO")
public class CarrierNotePrintGroupVO {

	private String mainID;//取货单编号
	
	private Double totalAmount;
	
	private Integer count;
	
	private List<CarrierNotePrintGroupLineVO> groupLineList;
	
	private List<CarrierNoteLineVO> carrierNoteLineList;

	public String getMainID() {
		return mainID;
	}

	public void setMainID(String mainID) {
		this.mainID = mainID;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<CarrierNotePrintGroupLineVO> getGroupLineList() {
		return groupLineList;
	}

	public void setGroupLineList(List<CarrierNotePrintGroupLineVO> groupLineList) {
		this.groupLineList = groupLineList;
	}

	public List<CarrierNoteLineVO> getCarrierNoteLineList() {
		return carrierNoteLineList;
	}

	public void setCarrierNoteLineList(List<CarrierNoteLineVO> carrierNoteLineList) {
		this.carrierNoteLineList = carrierNoteLineList;
	}

	
	
	
}
