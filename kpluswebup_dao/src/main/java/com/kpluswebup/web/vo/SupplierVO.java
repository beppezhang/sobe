package com.kpluswebup.web.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("supplierVO")
public class SupplierVO implements Serializable {

    private static final long serialVersionUID = -1510425069669614367L;

    private Long              id;

    private String            mainID;

    private String            mobile;

    private String            companyName;

    private String            linkMan;

    private String            openingBank;

    private String            bankAccount;

    private String            address;

    private String            userName;

    private String            passWord;

    private Integer           isDelete;

    private String            name;

    private Date              lastloginTime;

    private String            lastLoginIP;

    private String            creator;

    private Date              createTime;

    private String            modifier;

    private Date              modifyTime;
    
    private Double           cashDep;
    
    private String            picURL;//店铺标志
    
    private String           shopPicURL;//店招图片
    
    private String           linkQQ;//联系QQ
    
    private String           linkWangWang;//联系旺旺
    
    private String           ShopProfile;//店铺简介
    
    private String           postalCode;//邮政编码
    
    
    
    public String getPicURL() {
        return picURL;
    }



    
    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }



    
    public String getShopPicURL() {
        return shopPicURL;
    }



    
    public void setShopPicURL(String shopPicURL) {
        this.shopPicURL = shopPicURL;
    }



    public String getLinkQQ() {
        return linkQQ;
    }


    
    public void setLinkQQ(String linkQQ) {
        this.linkQQ = linkQQ;
    }


    
    public String getLinkWangWang() {
        return linkWangWang;
    }


    
    public void setLinkWangWang(String linkWangWang) {
        this.linkWangWang = linkWangWang;
    }


    
    public String getShopProfile() {
        return ShopProfile;
    }


    
    public void setShopProfile(String shopProfile) {
        ShopProfile = shopProfile;
    }


    
    public String getPostalCode() {
        return postalCode;
    }


    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    

    
    public Double getCashDep() {
        return cashDep;
    }

    
    public void setCashDep(Double cashDep) {
        this.cashDep = cashDep;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getMincreateTime() {
        return mincreateTime;
    }

    public void setMincreateTime(Date mincreateTime) {
        this.mincreateTime = mincreateTime;
    }

    public Date getMaxcreateTime() {
        return maxcreateTime;
    }

    public void setMaxcreateTime(Date maxcreateTime) {
        this.maxcreateTime = maxcreateTime;
    }

    private Date mincreateTime;

    private Date maxcreateTime;

    public Date getLastloginTime() {
        return lastloginTime;
    }

    public void setLastloginTime(Date lastloginTime) {
        this.lastloginTime = lastloginTime;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Long getId() {
        return id;
    }

    public String getMainID() {
        return mainID;
    }

    public void setMainID(String mainID) {
        this.mainID = mainID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getOpeningBank() {
        return openingBank;
    }

    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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
