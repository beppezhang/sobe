package com.kpluswebup.web.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("areaVO")
public class AreaVO {

    private Long    id;

    private String  mainID;

    private String  name;

    private String  parentID;

    private Integer depth;        // 深度（级别）1大洲区域2洲3国家4国家地区5省6市7区/县

    private String  provinceName;

    private String  cityName;

    private String  disctrictName;

    private String  disctrictID;

    private String  cityID;

    private String  provinceID;
    
    private List<AreaVO> cityList=new ArrayList<AreaVO>();

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

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDisctrictName() {
        return disctrictName;
    }

    public void setDisctrictName(String disctrictName) {
        this.disctrictName = disctrictName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisctrictID() {
        return disctrictID;
    }

    public void setDisctrictID(String disctrictID) {
        this.disctrictID = disctrictID;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

	public List<AreaVO> getCityList() {
		return cityList;
	}

	public void setCityList(List<AreaVO> cityList) {
		this.cityList = cityList;
	}

}
