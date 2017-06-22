package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("itemScoreDetailDTO")
public class ItemScoreDetailDTO extends BaseDTO {

    private Long    id;

    private String  mainID;

    private String  qrCodeUrl;

    private Integer isQrcode;

    private String  itemScoreID;

    private String  customerID;

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

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public Integer getIsQrcode() {
        return isQrcode;
    }

    public void setIsQrcode(Integer isQrcode) {
        this.isQrcode = isQrcode;
    }

    public String getItemScoreID() {
        return itemScoreID;
    }

    public void setItemScoreID(String itemScoreID) {
        this.itemScoreID = itemScoreID;
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

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
}
