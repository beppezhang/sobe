package com.kpluswebup.web.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("interfaceConfigParameterVO")
public class InterfaceConfigParameterVO {

    private Long    id;

    private String  configID;

    private String interfaceID;
    
    private String parameterID;
    
    private String parameter;
    
    private String paramValue;
    
    private Integer sortOrder;
    
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

	public String getConfigID() {
		return configID;
	}

	public void setConfigID(String configID) {
		this.configID = configID;
	}

	public String getInterfaceID() {
		return interfaceID;
	}

	public void setInterfaceID(String interfaceID) {
		this.interfaceID = interfaceID;
	}

	public String getParameterID() {
		return parameterID;
	}

	public void setParameterID(String parameterID) {
		this.parameterID = parameterID;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
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
