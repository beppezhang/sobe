package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("cmsWechatMenuDTO")
public class CmsWechatMenuDTO extends BaseDTO {

    private Long    id;
    
    private String mainID;
    
    private String parentID;
    
    private String name;

    private String  interfaceConfigID;
    
    private Integer  menuType;

    private String  title;

    private String content;

    private String picURL;
    
    private Integer linkType;
    
    private String menuLink;

    private Integer sortOrder;

    private Integer isDelete;

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

    
    public String getParentID() {
        return parentID;
    }

    
    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getInterfaceConfigID() {
        return interfaceConfigID;
    }

    
    public void setInterfaceConfigID(String interfaceConfigID) {
        this.interfaceConfigID = interfaceConfigID;
    }

    
    public Integer getMenuType() {
        return menuType;
    }

    
    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
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

    
    public String getMenuLink() {
        return menuLink;
    }

    
    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
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
}
