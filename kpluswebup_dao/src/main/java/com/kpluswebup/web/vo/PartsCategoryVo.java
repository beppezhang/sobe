package com.kpluswebup.web.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;

@Alias("partsCategoryVo")
public class PartsCategoryVo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2638517956664420188L;
	
	private Long id;
	private String mainID;
	private String ancestorID;
	private Integer status;
	private String code;
	private String name;
	private Integer flevel;
	private Integer flndex;
	
    private List<PartsCategoryVo> levelTwo;

    private List<PartsCategoryVo> levelThree;

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

	public String getAncestorID() {
		return ancestorID;
	}

	public void setAncestorID(String ancestorID) {
		this.ancestorID = ancestorID;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public List<PartsCategoryVo> getLevelTwo() {
		return levelTwo;
	}

	public void setLevelTwo(List<PartsCategoryVo> levelTwo) {
		this.levelTwo = levelTwo;
	}

	public List<PartsCategoryVo> getLevelThree() {
		return levelThree;
	}

	public void setLevelThree(List<PartsCategoryVo> levelThree) {
		this.levelThree = levelThree;
	}	

}
