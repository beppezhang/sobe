package com.kpluswebup.web.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("freightTemplatePriceVO")
public class FreightTemplatePriceVO{

    private Long    id;

    private String  mainID;

    private String freightTemplateID;
    
    private Integer firstCondition;
    
    private Double firstPrice;
    
    private Integer addUnit;
    
    private Double addPrice;

    private Integer isDelete;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;
    
    private List<FreightTemplatePriceAreaSetVO> priceareasetList=new ArrayList<FreightTemplatePriceAreaSetVO>();

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

	public String getFreightTemplateID() {
		return freightTemplateID;
	}

	public void setFreightTemplateID(String freightTemplateID) {
		this.freightTemplateID = freightTemplateID;
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

	public List<FreightTemplatePriceAreaSetVO> getPriceareasetList() {
		return priceareasetList;
	}

	public void setPriceareasetList(
			List<FreightTemplatePriceAreaSetVO> priceareasetList) {
		this.priceareasetList = priceareasetList;
	}
}
