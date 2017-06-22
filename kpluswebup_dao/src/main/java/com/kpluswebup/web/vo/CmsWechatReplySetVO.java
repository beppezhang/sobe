package com.kpluswebup.web.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("cmsWechatReplySetVO")
public class CmsWechatReplySetVO {

    private Long    id;
    
    private String mainID;

    private Integer  setType;

    private String  interfaceConfigID;

    private String keyword;

    private Integer replyType;

    private String  title;

    private String content;

    private String picURL;
    
    private Integer linkType;
    
    private String replyLink;

    private Integer sortOrder;

    private Integer isDelete;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;
    
    private String wechatName;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    public Integer getSetType() {
        return setType;
    }

    
    public void setSetType(Integer setType) {
        this.setType = setType;
    }

    public String getInterfaceConfigID() {
        return interfaceConfigID;
    }

    
    public void setInterfaceConfigID(String interfaceConfigID) {
        this.interfaceConfigID = interfaceConfigID;
    }

    
    public String getKeyword() {
        return keyword;
    }

    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    
    public Integer getReplyType() {
        return replyType;
    }

    
    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
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

    
    public String getPicURL() {
        return picURL;
    }

    
    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    
    public Integer getLinkType() {
        return linkType;
    }

    
    public void setLinkType(Integer linkType) {
        this.linkType = linkType;
    }

    
    public String getReplyLink() {
        return replyLink;
    }

    
    public void setReplyLink(String replyLink) {
        this.replyLink = replyLink;
    }

    
    public Integer getSortOrder() {
        return sortOrder;
    }

    
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    
    public Integer getIsDelete() {
        return isDelete;
    }

    
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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

    
    public String getWechatName() {
        return wechatName;
    }

    
    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    
    public String getMainID() {
        return mainID;
    }

    
    public void setMainID(String mainID) {
        this.mainID = mainID;
    }
}
