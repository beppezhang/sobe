package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @author Administrator 产品预售
 */
@Alias("preSaleDTO")
public class PreSaleDTO extends BaseDTO {

    private Long    id;

    private String  mainID;

    private String  productID;

    private String  itemID;

    private Double  salesPrice;

    private Long    maxmumQty;  // 最多销售数量

    private Integer limitCount; // 一个用户总购买数量

    private String  description;

    private Date    fromDate;

    private Date    endDate;

    private Integer isDelete;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    private Date    serachDate;

    private Integer score;      // 全积分价格

    private Double  scorePrice; // 金额加积分的现金部分

    private Integer salesScore; // 金额加积分的积分部分

    private String  picUrl;     // 图片

    // no db
    private String  itemName;
    
    private Integer peopleBuy;//购买人数
    
    private Integer productStatus;//产品status 

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

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Long getMaxmumQty() {
        return maxmumQty;
    }

    public void setMaxmumQty(Long maxmumQty) {
        this.maxmumQty = maxmumQty;
    }

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
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

    public Date getSerachDate() {
        return serachDate;
    }

    public void setSerachDate(Date serachDate) {
        this.serachDate = serachDate;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    
    public Integer getPeopleBuy() {
        return peopleBuy;
    }

    
    public void setPeopleBuy(Integer peopleBuy) {
        this.peopleBuy = peopleBuy;
    }

    
    public Integer getProductStatus() {
        return productStatus;
    }

    
    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    
}
