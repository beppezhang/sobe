package com.kpluswebup.web.vo;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("carrierNoteVO")
public class CarrierNoteVO {

	private String mainID;//取货单编号
	
	private String supplierID;
	
	private String supplierName;
	
	private Integer itemCount;
	
	private Double totalAmount;
	
	private String pickupMan;
	
	private Date pickupTime;
	
	private Date createTime;
	
	private Integer status;//打印状态 0未打印 1已打印 
	
	private Integer storeStatus;//入库状态 0未入库1已入库
	
	
	
	private List<CarrierNoteLineVO> carrierNoteLineList;
	
	//打印用
	private String itemName;
	
	private Double salesPrice;
	
	private Double itemTotalAmount;
	
	private Integer itemTotalCount;
	
	

	public String getMainID() {
		return mainID;
	}

	public void setMainID(String mainID) {
		this.mainID = mainID;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPickupMan() {
		return pickupMan;
	}

	public void setPickupMan(String pickupMan) {
		this.pickupMan = pickupMan;
	}

	public Date getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public Integer getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(Integer storeStatus) {
		this.storeStatus = storeStatus;
	}

	public List<CarrierNoteLineVO> getCarrierNoteLineList() {
		return carrierNoteLineList;
	}

	public void setCarrierNoteLineList(List<CarrierNoteLineVO> carrierNoteLineList) {
		this.carrierNoteLineList = carrierNoteLineList;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	
}
