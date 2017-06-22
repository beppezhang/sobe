package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @author Administrator 站内信
 */
@Alias("messageDTO")
public class MessageDTO extends BaseDTO {

    private Long    id;

    private String  mainID;

    private String  sendee;            // 接收人

    private String  sender;            // 发件人

    private String  title;

    private String  content;

    private Integer isReaded;          // 已读 0：未读 1：已读

    private Integer sendeeDeleted;     // 收件人删除 0:未删除,1:已删除

    private Integer senderDeleted;     // 发件人删除 0:未删除,1:已删除

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    // no db
    private String  searchUserName;

    private String  searchCustomerName;

    private String  searchOperatorName;

    public String getMainID() {
        return mainID;
    }

    public void setMainID(String mainID) {
        this.mainID = mainID;
    }

    public String getSendee() {
        return sendee;
    }

    public void setSendee(String sendee) {
        this.sendee = sendee;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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

    public Integer getIsReaded() {
        return isReaded;
    }

    public void setIsReaded(Integer isReaded) {
        this.isReaded = isReaded;
    }

    public Integer getSendeeDeleted() {
        return sendeeDeleted;
    }

    public void setSendeeDeleted(Integer sendeeDeleted) {
        this.sendeeDeleted = sendeeDeleted;
    }

    public Integer getSenderDeleted() {
        return senderDeleted;
    }

    public void setSenderDeleted(Integer senderDeleted) {
        this.senderDeleted = senderDeleted;
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

    
    public String getSearchOperatorName() {
        return searchOperatorName;
    }

    
    public void setSearchOperatorName(String searchOperatorName) {
        this.searchOperatorName = searchOperatorName;
    }

}
