package com.kpluswebup.web.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.kpuswebup.common.vo.BaseVO;

@Alias("productVO")
public class ProductVO extends BaseVO implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private String mainID;

	private String name;

	private Integer virtual;

	private Integer catalog;
	  private String            address;
	private String picURL;
	private String           linkWangWang;//联系旺旺
	private String            companyName;
    private String           ShopProfile;//店铺简介
	private String unit;

	private String keyword;

	private String description;

	private String productTypeID;

	private String productTypeName;

	private String brandID;

	private String brandName;

	private String title;

	private String metaKeywords;

	private String metaDescription;

	private Integer status;

	private Integer isDelete;

	private String creator;

	private Date createTime;

	private String modifier;

	private Date modifyTime;

	private Double minSalesPrice; // 最小销售价

	private Double minStandrardPrice; // 最小市场价

	private Integer isRecommend; // 是否推荐

	private String qrcodeurl; // 二维码url

	private String subTitle; // 副标题

	private String saleService; // 售后服务

	private Integer countSupplier;//店铺产品数量
	
	private Integer maxSalesVolume; // 最大销量
	private Double standPrice;// 市场价
	private String productProp;
	private String productPropValue; // 属性

	private Integer productType; // 产品设置 1市场2代理

	private Integer isBuy; // 是否值得买

	private Integer isLowPrice; // 是否天天低价

	private Integer isSales; // 是否今日热买

    private String         supplierID;                      //店铺ID
    
    private String           supplierCategoryID;      //店铺分类ID
    
    private String           itemID;//商品ID
    
    private String supplierName;//商户名称
    
    private String salesPrice;
    
    /**
     * sxc
     */
    private Long id;
    private Integer type; //0 -- 主机号 ,  1 -- OEM , 2 -- 附厂件
    private String code;
    private String freightTemplateID;
    private String brandPicURL;//配件品牌logo地址
    private String brandTitle;//配件名称
    private List<String> mainIDList;//配件产品id集合

    
	private String parameter;

	/*
	 * 关键字搜索结果页中的数据拼接
	 * */
	private String partsCategoryLevel;
	private String partsCategoryLevel2;
	private String vehicleTypeInfo;
	private String brandTitleInfoOfOtherOEM;
	
	public String getBrandTitleInfoOfOtherOEM() {
		return brandTitleInfoOfOtherOEM;
	}




	public void setBrandTitleInfoOfOtherOEM(String brandTitleInfoOfOtherOEM) {
		this.brandTitleInfoOfOtherOEM = brandTitleInfoOfOtherOEM;
	}




	public String getVehicleTypeInfo() {
		return vehicleTypeInfo;
	}




	public void setVehicleTypeInfo(String vehicleTypeInfo) {
		this.vehicleTypeInfo = vehicleTypeInfo;
	}




	public String getPartsCategoryLevel() {
		return partsCategoryLevel;
	}




	public void setPartsCategoryLevel(String partsCategoryLevel) {
		this.partsCategoryLevel = partsCategoryLevel;
	}




	public String getPartsCategoryLevel2() {
		return partsCategoryLevel2;
	}




	public void setPartsCategoryLevel2(String partsCategoryLevel2) {
		this.partsCategoryLevel2 = partsCategoryLevel2;
	}




	public String getParameter() {
		return parameter;
	}




	public void setParameter(String parameter) {
		this.parameter = parameter;
	}    
    
    public String getBrandTitle() {
		return brandTitle;
	}




	public void setBrandTitle(String brandTitle) {
		this.brandTitle = brandTitle;
	}




	public String getBrandPicURL() {
		return brandPicURL;
	}




	public void setBrandPicURL(String brandPicURL) {
		this.brandPicURL = brandPicURL;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Integer getType() {
		return type;
	}




	public void setType(Integer type) {
		this.type = type;
	}




	public String getCode() {
		return code;
	}




	public void setCode(String code) {
		this.code = code;
	}







	public String getSalesPrice() {
		return salesPrice;
	}




	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getLinkWangWang() {
		return linkWangWang;
	}




	public void setLinkWangWang(String linkWangWang) {
		this.linkWangWang = linkWangWang;
	}




	public String getCompanyName() {
		return companyName;
	}




	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}




	public String getShopProfile() {
		return ShopProfile;
	}




	public void setShopProfile(String shopProfile) {
		ShopProfile = shopProfile;
	}




	public Integer getCountSupplier() {
		return countSupplier;
	}




	public void setCountSupplier(Integer countSupplier) {
		this.countSupplier = countSupplier;
	}




	public String getSupplierName() {
        return supplierName;
    }



    
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }



    public String getItemID() {
        return itemID;
    }


    
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }


	public Double getStandPrice() {
		return standPrice;
	}

	public void setStandPrice(Double standPrice) {
		this.standPrice = standPrice;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
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

	public String getProductPropValue() {
		return productPropValue;
	}

	public void setProductPropValue(String productPropValue) {
		this.productPropValue = productPropValue;
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

	public String getProductTypeID() {
		return productTypeID;
	}

	public void setProductTypeID(String productTypeID) {
		this.productTypeID = productTypeID;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBrandID() {
		return brandID;
	}

	public void setBrandID(String brandID) {
		this.brandID = brandID;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
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

	public Integer getMaxSalesVolume() {
		return maxSalesVolume;
	}

	public void setMaxSalesVolume(Integer maxSalesVolume) {
		this.maxSalesVolume = maxSalesVolume;
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




	public String getFreightTemplateID() {
		return freightTemplateID;
	}




	public void setFreightTemplateID(String freightTemplateID) {
		this.freightTemplateID = freightTemplateID;
	}




	public List<String> getMainIDList() {
		return mainIDList;
	}




	public void setMainIDList(List<String> mainIDList) {
		this.mainIDList = mainIDList;
	}

}
