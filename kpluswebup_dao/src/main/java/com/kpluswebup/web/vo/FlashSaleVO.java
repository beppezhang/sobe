package com.kpluswebup.web.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @author Administrator 限时抢购
 */
@Alias("flashSaleVO")
public class FlashSaleVO {

    private Long    id;

    private String  mainID;

    private String  productID;

    private String  itemID;

    private Integer totality;

    private Double  salesPrice;

    private Integer limitCount;

    private String  customerGrade; // 等级ID串

    private String  customerGroup; // 分组ID串

    private String  description;

    private Date    fromDate;

    private Date    endDate;

    private Integer isDelete;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    private String  productName;

    private String  itemName;

    private String  createName;

    private Integer score;        // 全积分价格

    private Double  scorePrice;   // 金额加积分的现金部分

    private Integer salesScore;   // 金额加积分的积分部分

    private Integer maxmumQty;    // 临时字段

    private String  picUrl;        // 图片
    
    private Integer peopleRush;   //抢购人数
    
    private Integer discountValue; //打的折数

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

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public Integer getTotality() {
        return totality;
    }

    public void setTotality(Integer totality) {
        this.totality = totality;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }

    public String getCustomerGrade() {
        return customerGrade;
    }

    public void setCustomerGrade(String customerGrade) {
        this.customerGrade = customerGrade;
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Double getScorePrice() {
        return scorePrice;
    }

    public void setScorePrice(Double scorePrice) {
        this.scorePrice = scorePrice;
    }

    public Integer getSalesScore() {
        return salesScore;
    }

    public void setSalesScore(Integer salesScore) {
        this.salesScore = salesScore;
    }

    public Integer getMaxmumQty() {
        return maxmumQty;
    }

    public void setMaxmumQty(Integer maxmumQty) {
        this.maxmumQty = maxmumQty;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    
    public Integer getPeopleRush() {
        return peopleRush;
    }

    
    public void setPeopleRush(Integer peopleRush) {
        this.peopleRush = peopleRush;
    }

    
    public Integer getDiscountValue() {
        return discountValue;
    }

    
    public void setDiscountValue(Integer discountValue) {
        this.discountValue = discountValue;
    }

    
}
