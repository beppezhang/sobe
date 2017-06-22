package com.kpluswebup.web.vo;

import org.apache.ibatis.type.Alias;

@Alias("freightTemplatePriceAreaSetVO")
public class FreightTemplatePriceAreaSetVO {

    private Long    id;

    private String  fTPriceID;

    private Integer areaDepth;
    
    private String areaID;
    
    private String areaName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getfTPriceID() {
		return fTPriceID;
	}

	public void setfTPriceID(String fTPriceID) {
		this.fTPriceID = fTPriceID;
	}

	public Integer getAreaDepth() {
		return areaDepth;
	}

	public void setAreaDepth(Integer areaDepth) {
		this.areaDepth = areaDepth;
	}

	public String getAreaID() {
		return areaID;
	}

	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
