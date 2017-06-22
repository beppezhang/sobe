package com.kpluswebup.web.vo;

import org.apache.ibatis.type.Alias;


@Alias("statisticsInfoVO")
public class StatisticsInfoVO {

    private String customerCount;         // 会员总数

    private String productTotal;          // 产品总数

    private String productSaleTotal;      // 在售产品总数

    private String productUnSaleTotal;    // 下架产品总数

    private String orderTotal;            // 订单总数

    private String orderTotalAmount;      // 订单总额

    private String orderReturnTotal;      // 退款总数

    private String orderReturnTotalAmount; // 退款总额
    
    private String monthSaleCount;		   //当月销量
    
    private String totalSaleCount;		   //累计销量
    
    public String getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(String customerCount) {
        this.customerCount = customerCount;
    }

    public String getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(String productTotal) {
        this.productTotal = productTotal;
    }

    public String getProductSaleTotal() {
        return productSaleTotal;
    }

    public void setProductSaleTotal(String productSaleTotal) {
        this.productSaleTotal = productSaleTotal;
    }

    public String getProductUnSaleTotal() {
        return productUnSaleTotal;
    }

    public void setProductUnSaleTotal(String productUnSaleTotal) {
        this.productUnSaleTotal = productUnSaleTotal;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(String orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getOrderReturnTotal() {
        return orderReturnTotal;
    }

    public void setOrderReturnTotal(String orderReturnTotal) {
        this.orderReturnTotal = orderReturnTotal;
    }

    public String getOrderReturnTotalAmount() {
        return orderReturnTotalAmount;
    }

    public void setOrderReturnTotalAmount(String orderReturnTotalAmount) {
        this.orderReturnTotalAmount = orderReturnTotalAmount;
    }

	public String getMonthSaleCount() {
		return monthSaleCount;
	}

	public void setMonthSaleCount(String monthSaleCount) {
		this.monthSaleCount = monthSaleCount;
	}

	public String getTotalSaleCount() {
		return totalSaleCount;
	}

	public void setTotalSaleCount(String totalSaleCount) {
		this.totalSaleCount = totalSaleCount;
	}

}
