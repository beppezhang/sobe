package com.kpluswebup.web.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="SalesOrderTransVO")
public class SalesOrderTransVO {
	public SalesOrderTransVO(){
		 super();
	}
	
	public SalesOrderTransVO(String operators, String operatorDate,
			String orderCode, String orderNo, String status, String scanSite,
			String station, String ctrName, String content, String remark,
			Date reciveTime) {
		super();
		this.operators = operators;
		this.operatorDate = operatorDate;
		this.orderCode = orderCode;
		this.orderNo = orderNo;
		this.status = status;
		this.scanSite = scanSite;
		this.station = station;
		this.ctrName = ctrName;
		this.content = content;
		this.remark = remark;
		this.reciveTime = reciveTime;
	}
	private String tmsServiceCode ;//快递公司编号
	private String operators ;           //操作人
	private String operatorDate ;       //操作时间 格式yyyy-mm-dd hh:mm:ss
	private String orderCode ;          //运单号码
    private String orderNo ;          //订单号
    private String status ;             //扫描类型
    private String scanSite;          //当前站点
    private String station ;            //（到件时为上一站）（出货为下一站）         
    private String ctrName ;             //签收人
    private String content ;            //疑难件原因
    private String remark ;
    private Date reciveTime;// 接收时间
	public String getOperators() {
		return operators;
	}
	public void setOperators(String operators) {
		this.operators = operators;
	}
	public String getOperatorDate() {
		return operatorDate;
	}
	public void setOperatorDate(String operatorDate) {
		this.operatorDate = operatorDate;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getScanSite() {
		return scanSite;
	}
	public void setScanSite(String scanSite) {
		this.scanSite = scanSite;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getCtrName() {
		return ctrName;
	}
	public void setCtrName(String ctrName) {
		this.ctrName = ctrName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getReciveTime() {
		return reciveTime;
	}
	public void setReciveTime(Date reciveTime) {
		this.reciveTime = reciveTime;
	}

	public String getTmsServiceCode() {
		return tmsServiceCode;
	}

	public void setTmsServiceCode(String tmsServiceCode) {
		this.tmsServiceCode = tmsServiceCode;
	}
    
   

}
