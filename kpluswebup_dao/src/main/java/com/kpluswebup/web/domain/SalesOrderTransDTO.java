package com.kpluswebup.web.domain;

public class SalesOrderTransDTO extends BaseDTO {

	private String orderNO;//订单号
	private String orderCode;//物流号
	
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderNO() {
		return orderNO;
	}

	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}

}
