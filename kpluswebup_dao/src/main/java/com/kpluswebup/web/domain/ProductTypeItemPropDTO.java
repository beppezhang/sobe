package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("productTypeItemPropDTO")
public class ProductTypeItemPropDTO extends BaseDTO {

    private Long    id;

    private String  mainID;

    private String  productTypeID;

    private String  itemPropID;

    private String  name;

    private String  itemPropValue;

    private String  itemPropValueID;

    private Integer sortOrder;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

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

    public String getProductTypeID() {
        return productTypeID;
    }

    public void setProductTypeID(String productTypeID) {
        this.productTypeID = productTypeID;
    }

    public String getItemPropID() {
        return itemPropID;
    }

    public void setItemPropID(String itemPropID) {
        this.itemPropID = itemPropID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemPropValueID() {
        return itemPropValueID;
    }

    public void setItemPropValueID(String itemPropValueID) {
        this.itemPropValueID = itemPropValueID;
    }

    public String getItemPropValue() {
        return itemPropValue;
    }

    public void setItemPropValue(String itemPropValue) {
        this.itemPropValue = itemPropValue;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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

}
