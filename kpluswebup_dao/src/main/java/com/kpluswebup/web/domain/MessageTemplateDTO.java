package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("messageTemplateDTO")
public class MessageTemplateDTO extends BaseDTO {

    private Long    id;

    private String  messageFunctionID;

    private Integer  templateType;

    private String templateContent;

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

	public String getMessageFunctionID() {
		return messageFunctionID;
	}

	public void setMessageFunctionID(String messageFunctionID) {
		this.messageFunctionID = messageFunctionID;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
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
