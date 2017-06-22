package com.kpluswebup.web.vo;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("itemVO")
public class ItemVO {

    private String             mainID;

    private String             sMainID;          // 商家货号

    private String             productID;

    private String             name;

    private String             oldNumber;

    private String             picURL;

    private Double             costPrice;

    private Double             standrardPrice;

    private Double             distributionPrice;

    private Double             retailPrice;

    private Double             salesPrice;

    private Double             sSalesPrice;      // 商家售价

    private Double             purchasePrice;

    private Integer            minimumQty;

    private Double             weight;

    private Integer            cubage;

    private Integer            quantity;

    private Integer            stock;

    private Integer            saftyStock;

    private Integer            sortOrder;

    private String             description;

    private Integer            isDelete;

    private String             creator;

    private Date               createTime;

    private String             modifier;

    private Date               modifyTime;

    private List<ItemDetailVO> itemDetailList;

    private String             itemDetailValue;

    private Integer            score;            // 全积分价格

    private Double             scorePrice;       // 金额加积分的现金部分

    private Integer            salesScore;       // 金额加积分的积分部分

    private Integer            salesVolume;      // 销量

    private Integer            type;             // 购买方式

    private String             supplierID;       // 供应商

    private Double             allsalesPrice;    // 总价

    private String             productCat;       // 产品分类

    private String             supplierName;     // 供应商名称
    private String             brandID;
    private String             productType;

    private Double             minSalesPrice;
    private Double             minStandrardPrice;
    private Integer            countSalesVolume;

    private String             productTypeID;
    private String			   supplierCategoryID;
    private String 		       promotion;
    //运费模板
    private String freightTemplateID;
    
    private String skuCode;
    
    public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getFreightTemplateID() {
		return freightTemplateID;
	}

	public void setFreightTemplateID(String freightTemplateID) {
		this.freightTemplateID = freightTemplateID;
	}

	public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getMainID() {
        return mainID;
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

    public List<ItemDetailVO> getItemDetailList() {
        return itemDetailList;
    }

    public void setItemDetailList(List<ItemDetailVO> itemDetailList) {
        StringBuilder str = new StringBuilder();
        // for (ItemDetailVO itemDetailVO : itemDetailList) {
        // str.append(itemDetailVO.getItemPropValue());
        // str.append(":");
        // str.append(itemDetailVO.getItemPropID());
        // str.append(":");
        // str.append(itemDetailVO.getpTypeIPropID());
        // str.append("-");
        // }

        for (Iterator<ItemDetailVO> iterator = itemDetailList.iterator(); iterator.hasNext();) {
            ItemDetailVO itemDetailVO = (ItemDetailVO) iterator.next();
            str.append(itemDetailVO.getItemPropValue());
            str.append(":");
            str.append(itemDetailVO.getItemPropID());
            str.append(":");
            str.append(itemDetailVO.getpTypeIPropID());
            if (iterator.hasNext()) {
                str.append("-");
            }
        }
        setItemDetailValue(str.toString());
        this.itemDetailList = itemDetailList;
    }

    public String getItemDetailValue() {
        return itemDetailValue;
    }

    public void setItemDetailValue(String itemDetailValue) {
        this.itemDetailValue = itemDetailValue;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public Double getAllsalesPrice() {
        return allsalesPrice;
    }

    public void setAllsalesPrice(Double allsalesPrice) {
        this.allsalesPrice = allsalesPrice;
    }

    public String getProductCat() {
        return productCat;
    }

    public void setProductCat(String productCat) {
        this.productCat = productCat;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getsMainID() {
        return sMainID;
    }

    public void setsMainID(String sMainID) {
        this.sMainID = sMainID;
    }

    public Double getsSalesPrice() {
        return sSalesPrice;
    }

    public void setsSalesPrice(Double sSalesPrice) {
        this.sSalesPrice = sSalesPrice;
    }

    public Double getMinSalesPrice() {
        return minSalesPrice;
    }

    public void setMinSalesPrice(Double minSalesPrice) {
        this.minSalesPrice = minSalesPrice;
    }

    public Double getMinStandrardPrice() {
        return minStandrardPrice;
    }

    public void setMinStandrardPrice(Double minStandrardPrice) {
        this.minStandrardPrice = minStandrardPrice;
    }

    public Integer getCountSalesVolume() {
        return countSalesVolume;
    }

    public void setCountSalesVolume(Integer countSalesVolume) {
        this.countSalesVolume = countSalesVolume;
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

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

}
