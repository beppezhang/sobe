package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("settlementDTO")
public class SettlementDTO extends BaseDTO{

	private Long id;
	
	private String mainID;
	
	private Date settlementTime;
	
	private String settlementPeople;
	
	private Double settlementAmount;
	
	private Integer status;
	
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
	public Date getSettlementTime() {
		return settlementTime;
	}
	public void setSettlementTime(Date settlementTime) {
		this.settlementTime = settlementTime;
	}
	public String getSettlementPeople() {
		return settlementPeople;
	}
	public void setSettlementPeople(String settlementPeople) {
		this.settlementPeople = settlementPeople;
	}
	public Double getSettlementAmount() {
		return settlementAmount;
	}
	public void setSettlementAmount(Double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
