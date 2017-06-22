package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("productDetailDTO")
public class ProductDetailDTO extends BaseDTO {

    private Long   id;

    private String productID;

    private String productPropID;

    private String pTypePPropID;

    private String productPropValue;

    private String creator;

    private Date   createTime;

    private String modifier;

    private Date   modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getproductID() {
        return productID;
    }

    public void setproductID(String productID) {
        this.productID = productID == null ? null : productID.trim();
    }

    public String getproductPropID() {
        return productPropID;
    }

    public void setproductPropID(String productPropID) {
        this.productPropID = productPropID == null ? null : productPropID.trim();
    }

    public String getpTypePPropID() {
        return pTypePPropID;
    }

    public void setpTypePPropID(String pTypePPropID) {
        this.pTypePPropID = pTypePPropID == null ? null : pTypePPropID.trim();
    }

    public String getproductPropValue() {
        return productPropValue;
    }

    public void setproductPropValue(String productPropValue) {
        this.productPropValue = productPropValue == null ? null : productPropValue.trim();
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
