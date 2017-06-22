package com.kpluswebup.web.vo;

import org.apache.ibatis.type.Alias;

@Alias("UserOrderStstusVO")
public class UserOrderStstusVO {

	String orderTotalCount;// 全部订单

	String orderPanymentCount;// 待付款

	String orderDorupCount;// 待发货

	String orderReceingCount;// 待收货

	String orderEvaluateCount;// 待评价

	String orderCancenlCount;// 已取消

	String orderFinishCount;// 已完成
	
	String orderApplyCount;//售后申请

	public String getOrderApplyCount() {
		return orderApplyCount;
	}

	public void setOrderApplyCount(String orderApplyCount) {
		this.orderApplyCount = orderApplyCount;
	}

	public String getOrderTotalCount() {
		return orderTotalCount;
	}

	public void setOrderTotalCount(String orderTotalCount) {
		this.orderTotalCount = orderTotalCount;
	}

	public String getOrderPanymentCount() {
		return orderPanymentCount;
	}

	public void setOrderPanymentCount(String orderPanymentCount) {
		this.orderPanymentCount = orderPanymentCount;
	}

	public String getOrderDorupCount() {
		return orderDorupCount;
	}

	public void setOrderDorupCount(String orderDorupCount) {
		this.orderDorupCount = orderDorupCount;
	}

	public String getOrderReceingCount() {
		return orderReceingCount;
	}

	public void setOrderReceingCount(String orderReceingCount) {
		this.orderReceingCount = orderReceingCount;
	}

	public String getOrderEvaluateCount() {
		return orderEvaluateCount;
	}

	public void setOrderEvaluateCount(String orderEvaluateCount) {
		this.orderEvaluateCount = orderEvaluateCount;
	}

	public String getOrderCancenlCount() {
		return orderCancenlCount;
	}

	public void setOrderCancenlCount(String orderCancenlCount) {
		this.orderCancenlCount = orderCancenlCount;
	}

	public String getOrderFinishCount() {
		return orderFinishCount;
	}

	public void setOrderFinishCount(String orderFinishCount) {
		this.orderFinishCount = orderFinishCount;
	}

}
