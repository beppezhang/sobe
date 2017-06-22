package com.kpluswebup.web.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("wechatMessageVO")
public class WechatMessageVO {

    private Long    id;

    private String  mainID;

    private String  wechatMessageID;

    private String  customerID;

    private String  openID;

    private String  content;

    private String  interfaceConfigID;

    private Integer sendType;

    private Integer status;

    private Integer isDeleted;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    private String  customerName;

    private String  wechatNick;
    
    private String wechatName;

    public String getMainID() {
        return mainID;
    }

    public void setMainID(String mainID) {
        this.mainID = mainID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getWechatMessageID() {
        return wechatMessageID;
    }

    public void setWechatMessageID(String wechatMessageID) {
        this.wechatMessageID = wechatMessageID;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getInterfaceConfigID() {
        return interfaceConfigID;
    }

    public void setInterfaceConfigID(String interfaceConfigID) {
        this.interfaceConfigID = interfaceConfigID;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getWechatNick() {
        return wechatNick;
    }

    public void setWechatNick(String wechatNick) {
        this.wechatNick = wechatNick;
    }

    
    public String getWechatName() {
        return wechatName;
    }

    
    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

}
