package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @author Administrator 退货收货地址
 */
@Alias("salesOrderReturnShippingAddressDTO")
public class SalesOrderReturnShippingAddressDTO extends BaseDTO {

    private Long   id;

    private String returnID;

    private String name;

    private String countryID;

    private String provinceID;

    private String cityID;

    private String disctrictID;

    private String address;

    private String zip;

    private String telephone;

    private String mobile;

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

    public String getReturnID() {
        return returnID;
    }

    public void setReturnID(String returnID) {
        this.returnID = returnID == null ? null : returnID.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID == null ? null : countryID.trim();
    }

    public String getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID == null ? null : provinceID.trim();
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID == null ? null : cityID.trim();
    }

    public String getDisctrictID() {
        return disctrictID;
    }

    public void setDisctrictID(String disctrictID) {
        this.disctrictID = disctrictID == null ? null : disctrictID.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
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
