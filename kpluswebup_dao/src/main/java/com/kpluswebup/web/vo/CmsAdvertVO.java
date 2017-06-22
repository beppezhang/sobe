package com.kpluswebup.web.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("cmsAdvertVO")
public class CmsAdvertVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long              id;

    private String            mainID;

    private String            categoryID;

    private String            posionID;

    private String            name;

    private String            description;

    private String            picURL;

    private Integer           linkType;             // 1URL2产品类型 3产品品牌 4产品5商品

    private String            advertLink;

    private Integer           clickCount;

    private Integer           sortOrder;

    private Date              activeTime;

    private Date              endTime;

    private Integer           status;               // 0不启用1正常

    private Integer           isDelete;

    private String            creator;

    private Date              createTime;

    private String            modifier;

    private Date              modifyTime;

    private String            categoryName;

    private String            linkName;

    private Date              serachDate;

    private String            posionName;

    private String            productCategoryID;    // 一级分类

    private String            parentID;             // 轮播图id

    private List<CmsAdvertVO> childList;            // 轮播右侧广告

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

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getPosionID() {
        return posionID;
    }

    public void setPosionID(String posionID) {
        this.posionID = posionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getAdvertLink() {
        return advertLink;
    }

    public void setAdvertLink(String advertLink) {
        this.advertLink = advertLink;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public Date getSerachDate() {
        return serachDate;
    }

    public void setSerachDate(Date serachDate) {
        this.serachDate = serachDate;
    }

    public String getPosionName() {
        return posionName;
    }

    public void setPosionName(String posionName) {
        this.posionName = posionName;
    }

    public String getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(String productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public List<CmsAdvertVO> getChildList() {
        return childList;
    }

    public void setChildList(List<CmsAdvertVO> childList) {
        this.childList = childList;
    }

}
