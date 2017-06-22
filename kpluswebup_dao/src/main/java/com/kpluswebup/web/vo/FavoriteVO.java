package com.kpluswebup.web.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 
 * @author Administrator 收藏
 *
 */
@Alias("favoriteVO")
public class FavoriteVO {


    private Long   id;

    private String customerID;

    private String productID;

    private String itemID;

    private String creator;

    private Date   createTime;

    private String modifier;

    private Date   modifyTime;
    
    private  String productName;
    
    private Double  minSalesPrice;
    
    private Double  minStandrardPrice;
    
    private Integer status;

    private Integer isDelete;
    
    private String picUrl;
    
    private Double salePrice;
    
    private String itemName;
    
    

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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

    
    public Double getMinSalesPrice() {
        return minSalesPrice;
    }

    
    public void setMinSalesPrice(Double minSalesPrice) {
        this.minSalesPrice = minSalesPrice;
    }

    
    public Double getMinStandrardPrice() {
        return minStandrardPrice;
    }

    
    public void setMinStandrardPrice(Double minStandrardPrice) {
        this.minStandrardPrice = minStandrardPrice;
    }

    
    public Integer getStatus() {
        return status;
    }

    
    public void setStatus(Integer status) {
        this.status = status;
    }

    
    public Integer getIsDelete() {
        return isDelete;
    }

    
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    
    public String getPicUrl() {
        return picUrl;
    }

    
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    
    public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public String getItemName() {
        return itemName;
    }

    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    

    
}
