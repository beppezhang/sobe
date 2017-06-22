package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("itemDetailDTO")
public class ItemDetailDTO extends BaseDTO {

    private Long   id;

    private String itemID;

    private String itemPropID;

    private String pTypeIPropID;

    private String itemPropValue;

    private String creator;

    private Date   createTime;

    private String modifier;

    private Date   modifyTime;
    
    private String value1;
    
    private String value2;
    
    private String productID;
    private String propTypeID;//组合
    

    public String getPropTypeID() {
		return propTypeID;
	}

	public void setPropTypeID(String propTypeID) {
		this.propTypeID = propTypeID;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemPropID() {
        return itemPropID;
    }

    public void setItemPropID(String itemPropID) {
        this.itemPropID = itemPropID;
    }

    public String getpTypeIPropID() {
        return pTypeIPropID;
    }

    public void setpTypeIPropID(String pTypeIPropID) {
        this.pTypeIPropID = pTypeIPropID;
    }

    public String getItemPropValue() {
        return itemPropValue;
    }

    public void setItemPropValue(String itemPropValue) {
        this.itemPropValue = itemPropValue;
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

    
    public String getValue1() {
        return value1;
    }

    
    public void setValue1(String value1) {
        this.value1 = value1;
    }

    
    public String getValue2() {
        return value2;
    }

    
    public void setValue2(String value2) {
        this.value2 = value2;
    }

    
    public String getProductID() {
        return productID;
    }

    
    public void setProductID(String productID) {
        this.productID = productID;
    }
    
    

}
