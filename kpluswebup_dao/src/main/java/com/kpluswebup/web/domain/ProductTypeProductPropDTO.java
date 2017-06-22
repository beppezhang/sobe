package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("productTypeProductPropDTO")
public class ProductTypeProductPropDTO extends BaseDTO {

    private Long         id;

    private String       mainID;

    private String       productTypeID;

    private String       productPropID;

    private String       name;

    private String       productPropValue;

    private String       productPropValueID;

    private Integer      sortOrder;

    private String       creator;

    private Date         createTime;

    private String       modifier;

    private Date         modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmainID() {
        return mainID;
    }

    public void setmainID(String mainID) {
        this.mainID = mainID == null ? null : mainID.trim();
    }

    public String getproductTypeID() {
        return productTypeID;
    }

    public void setproductTypeID(String productTypeID) {
        this.productTypeID = productTypeID == null ? null : productTypeID.trim();
    }

    public String getproductPropID() {
        return productPropID;
    }

    public void setproductPropID(String productPropID) {
        this.productPropID = productPropID == null ? null : productPropID.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getproductPropValue() {
        return productPropValue;
    }

    public void setproductPropValue(String productPropValue) {
        this.productPropValue = productPropValue == null ? null : productPropValue.trim();
    }

    public String getProductPropValueID() {
        return productPropValueID;
    }

    public void setProductPropValueID(String productPropValueID) {
        this.productPropValueID = productPropValueID;
    }

    public Integer getsortOrder() {
        return sortOrder;
    }

    public void setsortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getcreateTime() {
        return createTime;
    }

    public void setcreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getmodifyTime() {
        return modifyTime;
    }

    public void setmodifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
