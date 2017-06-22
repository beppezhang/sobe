package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @author Administrator 邮件
 */
@Alias("emailDTO")
public class EmailDTO extends BaseDTO {

    private Long    id;

    private String  mainID;

    private String  customerID;

    private String  email;

    private String  title;

    private String  content;

    private Integer status;            // 状态 1：成功 2：失败

    private Integer isDeleted;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    // no db
    private String  searchUserName;

    private String  searchCustomerName;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getSearchUserName() {
        return searchUserName;
    }

    public void setSearchUserName(String searchUserName) {
        this.searchUserName = searchUserName;
    }

    public String getSearchCustomerName() {
        return searchCustomerName;
    }

    public void setSearchCustomerName(String searchCustomerName) {
        this.searchCustomerName = searchCustomerName;
    }

}
