package com.kpluswebup.web.vo;

import org.apache.ibatis.type.Alias;

@Alias("productStatisticsVO")
public class ProductStatisticsVO {

    private String mainID;

    private String name;

    private String picURL;

    private String salesCount;

    private String orderAmount;

    public String getMainID() {
        return mainID;
    }

    public void setMainID(String mainID) {
        this.mainID = mainID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public String getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(String salesCount) {
        this.salesCount = salesCount;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }
}
