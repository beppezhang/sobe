package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("cmsHelpCenterDTO")
public class CmsHelpCenterDTO extends BaseDTO{

	private Long id;

	private String mainID;

	private String categoryID;

	private String name;

	private String content;

	private Integer sortOrder;

	private Integer isDelete;

	private String creator;

	private Date createTime;

	private String modifier;

	private Date modifyTime;
	
	private Integer searchSize;//搜索次数

	public Integer getSearchSize() {
		return searchSize;
	}

	public void setSearchSize(Integer searchSize) {
		this.searchSize = searchSize;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
