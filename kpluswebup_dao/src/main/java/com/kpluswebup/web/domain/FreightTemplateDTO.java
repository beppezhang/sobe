package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("freightTemplateDTO")
public class FreightTemplateDTO extends BaseDTO {

    private Long    id;

    private String  mainID;

    private String  name;
    
    private Integer priceType;
    
    private String expressID;
    
    private String formatID;
    
    private Integer firstCondition;
    
    private Double firstPrice;
    
    private Integer addUnit;
    
    private Double addPrice;
    
    private String description;
    
    private Integer isDefault;

    private Integer isDelete;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;
    
    private String  supplierID;

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

	public Integer getPriceType() {
		return priceType;
	}

	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}

	public String getExpressID() {
		return expressID;
	}

	public void setExpressID(String expressID) {
		this.expressID = expressID;
	}

	public String getFormatID() {
		return formatID;
	}

	public void setFormatID(String formatID) {
		this.formatID = formatID;
	}

	public Integer getFirstCondition() {
		return firstCondition;
	}

	public void setFirstCondition(Integer firstCondition) {
		this.firstCondition = firstCondition;
	}

	public Double getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(Double firstPrice) {
		this.firstPrice = firstPrice;
	}

	public Integer getAddUnit() {
		return addUnit;
	}

	public void setAddUnit(Integer addUnit) {
		this.addUnit = addUnit;
	}

	public Double getAddPrice() {
		return addPrice;
	}

	public void setAddPrice(Double addPrice) {
		this.addPrice = addPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
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

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

}
