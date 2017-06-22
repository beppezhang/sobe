package com.kpluswebup.web.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @author Administrator 购物车
 */
@Alias("shoppingCartVO")
public class ShoppingCartVO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long    id;

    private String  customerID;

    private String  productID;

    private String  itemID;

    private Integer itemCount;

    private Integer isPromotion;   // 是否赠品

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    private Integer type;          // 1全现金 2全积分 3现金加积分

    private String  itemName;

    private String  itemPicUrl;

    private String  itemProp;

    private Double  standrardPrice;

    private Double  salesPrice;

    private Integer score;         // 全积分价格

    private Double  scorePrice;    // 金额加积分的现金部分

    private Integer salesScore;    // 金额加积分的积分部分

    private Integer allscore;

    private Double  allamount;

    private String  tempCartID;
    
    private Integer stock;
    
    private String supplierID;//商户id
    
    private String supplierName;//商户名称
    
    private String   linkQQ;//联系QQ
    
    private String   linkWangWang;//联系旺旺
    
    private double  freight;
    
    
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPicUrl() {
        return itemPicUrl;
    }

    public void setItemPicUrl(String itemPicUrl) {
        this.itemPicUrl = itemPicUrl;
    }

    public String getItemProp() {
        return itemProp;
    }

    public void setItemProp(String itemProp) {
        this.itemProp = itemProp;
    }

    public Double getStandrardPrice() {
        return standrardPrice;
    }

    public void setStandrardPrice(Double standrardPrice) {
        this.standrardPrice = standrardPrice;
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

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Integer getAllscore() {
        return allscore;
    }

    public void setAllscore(Integer allscore) {
        this.allscore = allscore;
    }

    public Double getAllamount() {
        return allamount;
    }

    public void setAllamount(Double allamount) {
        this.allamount = allamount;
    }

    public String getTempCartID() {
        return tempCartID;
    }

    public void setTempCartID(String tempCartID) {
        this.tempCartID = tempCartID;
    }

    
    public Integer getStock() {
        return stock;
    }

    
    public void setStock(Integer stock) {
        this.stock = stock;
    }

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}


	public double getFreight() {
		return freight;
	}


	public void setFreight(double freight) {
		this.freight = freight;
	}
    

}
