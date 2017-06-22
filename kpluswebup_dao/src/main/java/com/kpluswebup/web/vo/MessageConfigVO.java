package com.kpluswebup.web.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("messageConfigVO")
public class MessageConfigVO {

    private Long    id;

    private String  messageFunctionID;

    private Integer  messageActive;

    private Integer emailActive;

    private Integer smsActive; 

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

	public Integer getMessageActive() {
		return messageActive;
	}

	public void setMessageActive(Integer messageActive) {
		this.messageActive = messageActive;
	}

	public Integer getEmailActive() {
		return emailActive;
	}

	public void setEmailActive(Integer emailActive) {
		this.emailActive = emailActive;
	}

	public Integer getSmsActive() {
		return smsActive;
	}

	public void setSmsActive(Integer smsActive) {
		this.smsActive = smsActive;
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
