package com.kpluswebup.web.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("generalVO")
public class GeneralVO {

	private Long id;

	private String name;

	private String picUrl;

	private String domain;

	private String pageFoot;
	
	private String userAgreement;
	
	private String serviceConfig;
	
	private String title;
	
	private String metaKeywords;
	
	private String metaDescription;

	private String creator;

	private Date createTime;

	private String modifier;

	private Date modifyTime;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPageFoot() {
		return pageFoot;
	}

	public void setPageFoot(String pageFoot) {
		this.pageFoot = pageFoot;
	}

	public String getUserAgreement() {
		return userAgreement;
	}

	public void setUserAgreement(String userAgreement) {
		this.userAgreement = userAgreement;
	}

	public String getServiceConfig() {
		return serviceConfig;
	}

	public void setServiceConfig(String serviceConfig) {
		this.serviceConfig = serviceConfig;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
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
