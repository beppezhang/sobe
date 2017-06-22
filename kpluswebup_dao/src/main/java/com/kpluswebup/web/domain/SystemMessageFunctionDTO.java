package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("systemMessageFunctionDTO")
public class SystemMessageFunctionDTO extends BaseDTO {

    private Long    id;

    private String  mainID;
    
    private String name;

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

	public String getMainID() {
		return mainID;
	}

	public void setMainID(String mainID) {
		this.mainID = mainID;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
