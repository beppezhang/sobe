package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("itemDTO")
public class ItemDTO extends BaseDTO {

    private String  mainID;

    private String  productID;

    private String  name;

    private String  oldNumber;

    private Integer status;           // 产品的上下架

    private String  picURL;

    private Double  costPrice;

    private Double  standrardPrice;

    private Double  distributionPrice;

    private Double  retailPrice;

    private Double  salesPrice;

    private Double  purchasePrice;

    private Integer minimumQty;

    private Double  weight;

    private Integer cubage;

    private Integer quantity;

    private Integer stock;

    private Integer saftyStock;

    private Integer sortOrder;

    private String  description;

    private Integer isDelete;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    private Integer score;            // 全积分价格

    private Double  scorePrice;       // 金额加积分的现金部分

    private Integer salesScore;       // 金额加积分的积分部分

    private Integer salesVolume;      // 销量

    private String  supplierID;       // 供应商
    
    private String  supplierCategoryID;       //店铺分类

    private String  categoryID;

    private String  startSalePrice;
    private String  endSalePrice;
    private String  brandName;
    private String  brandID;
    private Integer productType;
    private String  productTypeID;
    private Date    startTime;
    private Date    endTime;
    private String  provinceID;
    private String  cityID;
    private String  districtID;
    private String skuCode;
    
    private String supplierName;
    private String freightTemplateID;    

    public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}

	public String getCityID() {
		return cityID;
	}

	public void setCityID(String cityID) {
		this.cityID = cityID;
	}

	public String getDistrictID() {
		return districtID;
	}

	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getStartSalePrice() {
        return startSalePrice;
    }

    public void setStartSalePrice(String startSalePrice) {
        this.startSalePrice = startSalePrice;
    }

    public String getEndSalePrice() {
        return endSalePrice;
    }

    public void setEndSalePrice(String endSalePrice) {
        this.endSalePrice = endSalePrice;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    // no db
    private Integer catalog;

    public String getMainID() {
        return mainID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMainID(String mainID) {
        this.mainID = mainID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldNumber() {
        return oldNumber;
    }

    public void setOldNumber(String oldNumber) {
        this.oldNumber = oldNumber;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getStandrardPrice() {
        return standrardPrice;
    }

    public void setStandrardPrice(Double standrardPrice) {
        this.standrardPrice = standrardPrice;
    }

    public Double getDistributionPrice() {
        return distributionPrice;
    }

    public void setDistributionPrice(Double distributionPrice) {
        this.distributionPrice = distributionPrice;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getMinimumQty() {
        return minimumQty;
    }

    public void setMinimumQty(Integer minimumQty) {
        this.minimumQty = minimumQty;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getCubage() {
        return cubage;
    }

    public void setCubage(Integer cubage) {
        this.cubage = cubage;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSaftyStock() {
        return saftyStock;
    }

    public void setSaftyStock(Integer saftyStock) {
        this.saftyStock = saftyStock;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Double getScorePrice() {
        return scorePrice;
    }

    public void setScorePrice(Double scorePrice) {
        this.scorePrice = scorePrice;
    }

    public Integer getSalesScore() {
        return salesScore;
    }

    public void setSalesScore(Integer salesScore) {
        this.salesScore = salesScore;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Integer getCatalog() {
        return catalog;
    }

    public void setCatalog(Integer catalog) {
        this.catalog = catalog;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getProductTypeID() {
        return productTypeID;
    }

    public void setProductTypeID(String productTypeID) {
        this.productTypeID = productTypeID;
    }

    
    public String getSupplierCategoryID() {
        return supplierCategoryID;
    }

    
    public void setSupplierCategoryID(String supplierCategoryID) {
        this.supplierCategoryID = supplierCategoryID;
    }

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getFreightTemplateID() {
		return freightTemplateID;
	}

	public void setFreightTemplateID(String freightTemplateID) {
		this.freightTemplateID = freightTemplateID;
	}

    
    
    
}
