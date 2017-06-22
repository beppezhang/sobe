package com.kpluswebup.web.vo;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("finalstatementVO")
public class FinalstatementVO {

    private Long    id;

    private String  mainID;
    
    private String customerID;
    
    private String supplierID;
    
    private String supplierName;
    
    private String orderID;
    
    private Double totalAmount;
    
    private Integer status;
    
    private String creator;
    
    private Date createTime;
    
    private String modifier;
    
    private Date modifyTime;
    
    
    private List<SalesOrderVO> salesOrderList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMainID() {
		return mainID;
	}

	public void setMainID(String mainID) {
		this.mainID = mainID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public List<SalesOrderVO> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesOrderVO> salesOrderList) {
		this.salesOrderList = salesOrderList;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	

}
