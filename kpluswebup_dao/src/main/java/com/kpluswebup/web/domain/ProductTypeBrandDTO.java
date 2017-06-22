package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("productTypeBrandDTO")
public class ProductTypeBrandDTO extends BaseDTO {

    private Long   id;

    private String productTypeID;

    private String brandID;

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

    public String getproductTypeID() {
        return productTypeID;
    }

    public void setproductTypeID(String productTypeID) {
        this.productTypeID = productTypeID == null ? null : productTypeID.trim();
    }

    public String getbrandID() {
        return brandID;
    }

    public void setbrandID(String brandID) {
        this.brandID = brandID == null ? null : brandID.trim();
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
