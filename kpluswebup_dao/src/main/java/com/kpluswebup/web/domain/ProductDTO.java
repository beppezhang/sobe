package com.kpluswebup.web.domain;

import java.util.Date;
import java.util.List;

public class ProductDTO extends BaseDTO {

    private String  customerID;
    private Long    id;

    private String  mainID;

    private String  name;

    private Integer virtual;

    private Integer catalog;

    private String  picURL;

    private String  unit;

    private String  keyword;

    private String  description;

    private String  productTypeID;

    private String  brandID;

    private String  brandName;
    private String  title;

    private String  metaKeywords;

    private String  metaDescription;

    private Integer status;         //0：下架    1：审核通过  2:未审核 3：审核不通过

    private Integer isDelete;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    private Integer isRecommend;

    private String  qrcodeurl;

    private String  catID;

    private String  pTypePPropIDs;

    private String  subTitle;       // 副标题

    private String  saleService;    // 售后服务

    private String  startSalePrice;

    private String  endSalePrice;

    private String  productProp;

    private Integer productType;    // 产品设置 1市场2代理

    private Integer isBuy;          // 是否值得买

    private Integer isLowPrice;     // 是否天天低价

    private Integer isSales;        // 是否今日热买
    
    private String supplierID;      //店铺ID
    
    private String supplierCategoryID;      //店铺分类ID
    
    
    private String standPrice;//市场价
    
    private String freightTemplateID;
    
    //*****************************sxc
    private String vehicleTypeId;//车型 mainID
    private String partsCategoryId;//配件分类mainID
    private List<String> partsCategoryMainIDList;//配件分类mainID集合
    private List<String> mainIDList;//配件产品id集合
    private String searchModel;//查询模式 product vin oem
    private int pageNoCountForLuncene = 10;
    
    
    
    public String getFreightTemplateID() {
		return freightTemplateID;
	}


	public void setFreightTemplateID(String freightTemplateID) {
		this.freightTemplateID = freightTemplateID;
	}


	public int getPageNoCountForLuncene() {
		return pageNoCountForLuncene;
	}


	public void setPageNoCountForLuncene(int pageNoCountForLuncene) {
		this.pageNoCountForLuncene = pageNoCountForLuncene;
	}


	public String getSearchModel() {
		return searchModel;
	}


	public void setSearchModel(String searchModel) {
		this.searchModel = searchModel;
	}


	public List<String> getMainIDList() {
		return mainIDList;
	}


	public void setMainIDList(List<String> mainIDList) {
		this.mainIDList = mainIDList;
	}


	public String getVehicleTypeId() {
		return vehicleTypeId;
	}


	public List<String> getPartsCategoryMainIDList() {
		return partsCategoryMainIDList;
	}


	public void setPartsCategoryMainIDList(List<String> partsCategoryMainIDList) {
		this.partsCategoryMainIDList = partsCategoryMainIDList;
	}


	public void setVehicleTypeId(String vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}


	public String getPartsCategoryId() {
		return partsCategoryId;
	}


	public void setPartsCategoryId(String partsCategoryId) {
		this.partsCategoryId = partsCategoryId;
	}



	public String getStandPrice() {
		return standPrice;
	}


	public void setStandPrice(String standPrice) {
		this.standPrice = standPrice;
	}


	public String getSupplierID() {
        return supplierID;
    }

    
    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductTypeID() {
        return productTypeID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setProductTypeID(String productTypeID) {
        this.productTypeID = productTypeID;
    }

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

    public Integer getVirtual() {
        return virtual;
    }

    public void setVirtual(Integer virtual) {
        this.virtual = virtual;
    }

    public Integer getCatalog() {
        return catalog;
    }

    public void setCatalog(Integer catalog) {
        this.catalog = catalog;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getQrcodeurl() {
        return qrcodeurl;
    }

    public void setQrcodeurl(String qrcodeurl) {
        this.qrcodeurl = qrcodeurl;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getpTypePPropIDs() {
        return pTypePPropIDs;
    }

    public void setpTypePPropIDs(String pTypePPropIDs) {
        this.pTypePPropIDs = pTypePPropIDs;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSaleService() {
        return saleService;
    }

    public void setSaleService(String saleService) {
        this.saleService = saleService;
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

    public String getProductProp() {
        return productProp;
    }

    public void setProductProp(String productProp) {
        this.productProp = productProp;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(Integer isBuy) {
        this.isBuy = isBuy;
    }

    public Integer getIsLowPrice() {
        return isLowPrice;
    }

    public void setIsLowPrice(Integer isLowPrice) {
        this.isLowPrice = isLowPrice;
    }

    public Integer getIsSales() {
        return isSales;
    }

    public void setIsSales(Integer isSales) {
        this.isSales = isSales;
    }


    
    public String getSupplierCategoryID() {
        return supplierCategoryID;
    }


    
    public void setSupplierCategoryID(String supplierCategoryID) {
        this.supplierCategoryID = supplierCategoryID;
    }



}
