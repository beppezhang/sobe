package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("supplierDTO")
public class SupplierDTO extends BaseDTO {

    private Long    id;

    private String  mainID;

    private String  mobile;//联系方式

    private String  companyName;//店铺名称

    private String  linkMan;//联系人姓名

    private String  openingBank;//开户行

    private String  bankAccount;//银行账号

    private String  address;//联系地址

    private String  userName;//用户名

    private String  passWord;//密码

    private Integer isDelete;//是否删除 1：删除 0：未删除

    private String  name;//

    private Date    lastloginTime;//最后一次登录时间

    private String  lastLoginIP;//最后一次登录ip

    private String  creator;//创建人

    private Date    createTime;//创建时间

    private String  modifier;//编辑人

    private Date    modifyTime;//编辑时间
    
    private Double    cashDep; //保证金
    
    private String   picURL;//店铺标志
    
    private String   shopPicURL;//店招图片
    
    private String   linkQQ;//联系QQ
    
    private String   linkWangWang;//联系旺旺
    
    private String   ShopProfile;//店铺简介
    
    private String   postalCode;//邮政编码
    
    
    
    

    
    
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
    
    
}
