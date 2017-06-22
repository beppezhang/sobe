package com.kpluswebup.web.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("transConfigVO")
public class TransConfigVO {

    private Long    id;

    private String  mainID;

    private String  decimalPlace;

    private String  productPicURL;

    private Integer orderShowDay;

    private Integer orderCancelDay;

    private Integer orderCloseDay;

    private Integer deliveryProcessActive; // 是否需要出库流程 0不需要 1需要

    private Integer ipLimitedActive;      // 是否限制ip访问 0不限制1限制

    private Integer balancePaymentActive; // 是否支持余额支付 0不支持 1支持

    private String  balancePaymentURL;

    private Integer codActive;            // 是否支持货到付款 0不支持1支持

    private String  codURL;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    private Double  scorePrice;           // 积分价格

    private Integer isRegister;           // 是否开放注册功能0是1否

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

    public String getDecimalPlace() {
        return decimalPlace;
    }

    public void setDecimalPlace(String decimalPlace) {
        this.decimalPlace = decimalPlace;
    }

    public String getProductPicURL() {
        return productPicURL;
    }

    public void setProductPicURL(String productPicURL) {
        this.productPicURL = productPicURL;
    }

    public Integer getOrderShowDay() {
        return orderShowDay;
    }

    public void setOrderShowDay(Integer orderShowDay) {
        this.orderShowDay = orderShowDay;
    }

    public Integer getOrderCancelDay() {
        return orderCancelDay;
    }

    public void setOrderCancelDay(Integer orderCancelDay) {
        this.orderCancelDay = orderCancelDay;
    }

    public Integer getOrderCloseDay() {
        return orderCloseDay;
    }

    public void setOrderCloseDay(Integer orderCloseDay) {
        this.orderCloseDay = orderCloseDay;
    }

    public Integer getDeliveryProcessActive() {
        return deliveryProcessActive;
    }

    public void setDeliveryProcessActive(Integer deliveryProcessActive) {
        this.deliveryProcessActive = deliveryProcessActive;
    }

    public Integer getIpLimitedActive() {
        return ipLimitedActive;
    }

    public void setIpLimitedActive(Integer ipLimitedActive) {
        this.ipLimitedActive = ipLimitedActive;
    }

    public Integer getBalancePaymentActive() {
        return balancePaymentActive;
    }

    public void setBalancePaymentActive(Integer balancePaymentActive) {
        this.balancePaymentActive = balancePaymentActive;
    }

    public String getBalancePaymentURL() {
        return balancePaymentURL;
    }

    public void setBalancePaymentURL(String balancePaymentURL) {
        this.balancePaymentURL = balancePaymentURL;
    }

    public Integer getCodActive() {
        return codActive;
    }

    public void setCodActive(Integer codActive) {
        this.codActive = codActive;
    }

    public String getCodURL() {
        return codURL;
    }

    public void setCodURL(String codURL) {
        this.codURL = codURL;
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

    public Double getScorePrice() {
        return scorePrice;
    }

    public void setScorePrice(Double scorePrice) {
        this.scorePrice = scorePrice;
    }

    public Integer getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(Integer isRegister) {
        this.isRegister = isRegister;
    }
}
