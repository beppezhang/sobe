package com.kpluswebup.web.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("itemDetailVO")
public class ItemDetailVO {

    private Long   id;

    private String itemID;

    private String itemPropID;

    private String pTypeIPropID;

    private String itemPropValue;

    private String creator;

    private Date   createTime;

    private String modifier;

    private Date   modifyTime;
    
    private String itemPropName;
    
    private String[] values;        //临时字段 规格值数组
    private String name;
    private Integer            stock;
    private Double             salesPrice;
    private String skuCode;
    private String propTypeID;
    
    private String weight;
    private String cubage;

    public String getPropTypeID() {
		return propTypeID;
	}

	public void setPropTypeID(String propTypeID) {
		this.propTypeID = propTypeID;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemPropID() {
        return itemPropID;
    }

    public void setItemPropID(String itemPropID) {
        this.itemPropID = itemPropID;
    }

    public String getpTypeIPropID() {
        return pTypeIPropID;
    }

    public void setpTypeIPropID(String pTypeIPropID) {
        this.pTypeIPropID = pTypeIPropID;
    }

    public String getItemPropValue() {
        return itemPropValue;
    }

    public void setItemPropValue(String itemPropValue) {
        this.itemPropValue = itemPropValue;
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

	public String getItemPropName() {
		return itemPropName;
	}

	public void setItemPropName(String itemPropName) {
		this.itemPropName = itemPropName;
	}

    
    public String[] getValues() {
        return values;
    }

    
    public void setValues(String[] values) {
        this.values = values;
    }

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getCubage() {
		return cubage;
	}

	public void setCubage(String cubage) {
		this.cubage = cubage;
	}
	
	

}
