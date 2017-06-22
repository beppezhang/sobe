package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @author Administrator 购物车
 */
@Alias("shoppingCartDTO")
public class ShoppingCartDTO extends BaseDTO {

    private Long    id;

    private String  customerID;

    private String  productID;

    private String  itemID;

    private Integer itemCount;

    private Integer isPromotion; // 是否赠品

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    private Integer type;       // 1全现金 2全积分 3现金加积分
    
    private String supplierID;//商户id
    
    private String supplierName;//商户名称
    
    private String   linkQQ;//联系QQ
    
    private String   linkWangWang;//联系旺旺
    
    
    
    public String getLinkQQ() {
		return linkQQ;
	}


	public void setLinkQQ(String linkQQ) {
		this.linkQQ = linkQQ;
	}


	public String getLinkWangWang() {
		return linkWangWang;
	}


	public void setLinkWangWang(String linkWangWang) {
		this.linkWangWang = linkWangWang;
	}


	public String getSupplierName() {
        return supplierName;
    }

    
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Integer getIsPromotion() {
        return isPromotion;
    }

    public void setIsPromotion(Integer isPromotion) {
        this.isPromotion = isPromotion;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
