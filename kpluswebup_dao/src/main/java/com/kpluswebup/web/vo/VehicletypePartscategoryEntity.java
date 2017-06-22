package com.kpluswebup.web.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("vehicletypePartscategoryEntity")
public class VehicletypePartscategoryEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8651593953321583035L;
	private String vehicletypeId;
	private String partsCategoryId;
	private String mainID;
	private String ancestorID;
	private String code;
	private String name;
	private Integer flevel;
	private Integer flndex;
    private Integer isDelete;
    private Date createDate;
	
    private List<VehicletypePartscategoryEntity> levelTwo;

    private List<VehicletypePartscategoryEntity> levelThree;

    
	public String getMainID() {
		return partsCategoryId;
	}
	public String getVehicletypeId() {
		return vehicletypeId;
	}

	public void setVehicletypeId(String vehicletypeId) {
		this.vehicletypeId = vehicletypeId;
	}

	public String getPartsCategoryId() {
		return partsCategoryId;
	}

	public void setPartsCategoryId(String partsCategoryId) {
		this.partsCategoryId = partsCategoryId;
	}

	public String getAncestorID() {
		return ancestorID;
	}

	public void setAncestorID(String ancestorID) {
		this.ancestorID = ancestorID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFlevel() {
		return flevel;
	}

	public void setFlevel(Integer flevel) {
		this.flevel = flevel;
	}

	public Integer getFlndex() {
		return flndex;
	}

	public void setFlndex(Integer flndex) {
		this.flndex = flndex;
	}

	public List<VehicletypePartscategoryEntity> getLevelTwo() {
		return levelTwo;
	}

	public void setLevelTwo(List<VehicletypePartscategoryEntity> levelTwo) {
		this.levelTwo = levelTwo;
	}

	public List<VehicletypePartscategoryEntity> getLevelThree() {
		return levelThree;
	}

	public void setLevelThree(List<VehicletypePartscategoryEntity> levelThree) {
		this.levelThree = levelThree;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}	
	
}
