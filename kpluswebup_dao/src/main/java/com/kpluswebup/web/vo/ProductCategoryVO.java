package com.kpluswebup.web.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;

@Alias("productCategoryVO")
public class ProductCategoryVO implements Serializable {

    private static final long                    serialVersionUID = 6757138216890951881L;

    private String                               mainID;

    private String                               name;

    private String                               parentID;

    private String                               productTypeID;

    private String                               productName;

    private String                               description;

    private Integer                              sortOrder;

    private String                               title;

    private String                               metaKeywords;

    private String                               metaDescription;

    private Integer                              isDelete;

    private String                               creator;

    private Date                                 createTime;

    private String                               modifier;

    private Date                                 modifyTime;

    private List<ProductCategoryVO>              levelTwo;

    private List<ProductCategoryVO>              levelThre;

    private Map<String, List<ProductCategoryVO>> levelThree;

    private List<ProductVO>                      products;

    private String                               picUrl;                                 // 分类图标

    private List<CmsAdvertVO>                    productAdvertList;                      // 产品广告

    private Integer                              isRecommend;                            // 是否导航推荐
    
    
    
    /**
     * 新增列
     * @author sxc
     */
    private String 								 pinyin;
    
    private String 								 code;

    private Long id;
    
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getProductTypeID() {
        return productTypeID;
    }

    public void setProductTypeID(String productTypeID) {
        this.productTypeID = productTypeID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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

    public List<ProductCategoryVO> getLevelTwo() {
        return levelTwo;
    }

    public void setLevelTwo(List<ProductCategoryVO> levelTwo) {
        this.levelTwo = levelTwo;
    }

    public Map<String, List<ProductCategoryVO>> getLevelThree() {
        return levelThree;
    }

    public void setLevelThree(Map<String, List<ProductCategoryVO>> levelThree) {

        this.levelThree = levelThree;
    }

    public List<ProductVO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductVO> products) {
        this.products = products;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public List<CmsAdvertVO> getProductAdvertList() {
        return productAdvertList;
    }

    public void setProductAdvertList(List<CmsAdvertVO> productAdvertList) {
        this.productAdvertList = productAdvertList;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public List<ProductCategoryVO> getLevelThre() {
        return levelThre;
    }

    public void setLevelThre(List<ProductCategoryVO> levelThre) {
        this.levelThre = levelThre;
    }
}
