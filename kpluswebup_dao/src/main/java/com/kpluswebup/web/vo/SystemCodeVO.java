package com.kpluswebup.web.vo;

import org.apache.ibatis.type.Alias;

@Alias("systemCodeVO")
public class SystemCodeVO {

	private Long id;
	
	private String mainID;
	
	private String name;
	
	private String defaultValue;
	
	private Integer configActive;//0：不可配置，系统默认； 1：可配置，编号可以在后台配置
	
	private Long codeId;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Integer getConfigActive() {
		return configActive;
	}

	public void setConfigActive(Integer configActive) {
		this.configActive = configActive;
	}

	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

}
