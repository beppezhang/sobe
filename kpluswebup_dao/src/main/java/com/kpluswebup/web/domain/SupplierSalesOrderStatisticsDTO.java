package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 
 * @author moo
 *
 */
@Alias("supplierSalesOrderStatisticsDTO")
public class SupplierSalesOrderStatisticsDTO {

	private String mainID;
	
	private String supplierID;
	
	private Integer orderCount;
	
	private Double totalAmount;
	
	private Date date;//统计日期

	public String getMainID() {
		return mainID;
	}

	public void setMainID(String mainID) {
		this.mainID = mainID;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
