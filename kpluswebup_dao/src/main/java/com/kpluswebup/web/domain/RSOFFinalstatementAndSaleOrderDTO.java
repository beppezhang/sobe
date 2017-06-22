package com.kpluswebup.web.domain;

import org.apache.ibatis.type.Alias;

@Alias("rSOFFinalstatementAndSaleOrderDTO")
public class RSOFFinalstatementAndSaleOrderDTO{

    private Long    id;
    
    private String finalstatementID;
    
    private String orderID;
    
    private Double totalAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFinalstatementID() {
		return finalstatementID;
	}

	public void setFinalstatementID(String finalstatementID) {
		this.finalstatementID = finalstatementID;
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
    
    
    
    

   
}
