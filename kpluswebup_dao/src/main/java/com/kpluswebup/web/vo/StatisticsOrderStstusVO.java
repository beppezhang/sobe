package com.kpluswebup.web.vo;

import org.apache.ibatis.type.Alias;

@Alias("statisticsOrderStstusVO")
public class StatisticsOrderStstusVO {

    private String orderConfirmCount;       // 待确认订单

    private String orderLeavesCount;        // 待出库订单

    private String orderConsignmentCount;   // 待发货订单

    private String returnapplyConfirmCount; // 待确认退换货

    private String returnapplyReceivesCount; // 待收货

    private String returnReceivesCount;     // 待退款

    private String accountReturnCount;      // 待换货

    private String accountConfirmCount;     // *待确认退款*/
    
    private String salesOrderSalesApplyCount; //售后记录
    
    private String salesApplyType1Count;//维修
    
    private String salesApplyType2Count; //换机

    public String getOrderConfirmCount() {
        return orderConfirmCount;
    }

    public void setOrderConfirmCount(String orderConfirmCount) {
        this.orderConfirmCount = orderConfirmCount;
    }

    public String getOrderLeavesCount() {
        return orderLeavesCount;
    }

    public void setOrderLeavesCount(String orderLeavesCount) {
        this.orderLeavesCount = orderLeavesCount;
    }

    public String getOrderConsignmentCount() {
        return orderConsignmentCount;
    }

    public void setOrderConsignmentCount(String orderConsignmentCount) {
        this.orderConsignmentCount = orderConsignmentCount;
    }

    public String getReturnapplyConfirmCount() {
        return returnapplyConfirmCount;
    }

    public void setReturnapplyConfirmCount(String returnapplyConfirmCount) {
        this.returnapplyConfirmCount = returnapplyConfirmCount;
    }

    public String getReturnapplyReceivesCount() {
        return returnapplyReceivesCount;
    }

    public void setReturnapplyReceivesCount(String returnapplyReceivesCount) {
        this.returnapplyReceivesCount = returnapplyReceivesCount;
    }

    public String getReturnReceivesCount() {
        return returnReceivesCount;
    }

    public void setReturnReceivesCount(String returnReceivesCount) {
        this.returnReceivesCount = returnReceivesCount;
    }

    public String getAccountReturnCount() {
        return accountReturnCount;
    }

    public void setAccountReturnCount(String accountReturnCount) {
        this.accountReturnCount = accountReturnCount;
    }

    public String getAccountConfirmCount() {
        return accountConfirmCount;
    }

    public void setAccountConfirmCount(String accountConfirmCount) {
        this.accountConfirmCount = accountConfirmCount;
    }

	public String getSalesOrderSalesApplyCount() {
		return salesOrderSalesApplyCount;
	}

	public void setSalesOrderSalesApplyCount(String salesOrderSalesApplyCount) {
		this.salesOrderSalesApplyCount = salesOrderSalesApplyCount;
	}

	public String getSalesApplyType1Count() {
		return salesApplyType1Count;
	}

	public void setSalesApplyType1Count(String salesApplyType1Count) {
		this.salesApplyType1Count = salesApplyType1Count;
	}

	public String getSalesApplyType2Count() {
		return salesApplyType2Count;
	}

	public void setSalesApplyType2Count(String salesApplyType2Count) {
		this.salesApplyType2Count = salesApplyType2Count;
	}
    
    
}
