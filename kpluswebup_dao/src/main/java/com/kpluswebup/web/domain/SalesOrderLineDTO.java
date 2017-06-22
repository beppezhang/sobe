package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @author Administrator 订单明细
 */
@Alias("salesOrderLineDTO")
public class SalesOrderLineDTO extends BaseDTO {

    private Long    id;
    
    private String  mainID;

    private String  orderID;         // 订单编号

    private String  productID;       // 产品编号

    private String  itemID;          // 商品编号

    private Double  standrardPrice;  // 市场价

    private Double  salesPrice;      // 销售价

    private Double  transactionPrice; // 成交价,基于产品的优惠或改价后实际成交价格

    private Integer itemCount;       // 商品数量

    private Integer isPromotion;     // 是否赠品，默认 0

    private Integer status;          // 1：正常 2：退换货申请 3：已退货 4：已退款 5：已换货

    private String  returnApplyID;   // 退换货申请单编号

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    private Integer type;            // 1全现金 2全积分 3现金加积分

    private Integer salesScore;      // 销售积分

    private String  objID;           // 预售\抢购id

    private String  imei;            // 串号
    
    private String supplierID;
    
    private Integer iscarriered;  //是否打印取货单
    
    private Double totalAmount;
    
    private Integer			   isReturned;

    // no db
    private String  itemName;
    
    private String  itemPicUrl;

    public String getItemPicUrl() {
		return itemPicUrl;
	}

	public void setItemPicUrl(String itemPicUrl) {
		this.itemPicUrl = itemPicUrl;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID == null ? null : orderID.trim();
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID == null ? null : productID.trim();
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID == null ? null : itemID.trim();
    }

    public Double getStandrardPrice() {
        return standrardPrice;
    }

    public void setStandrardPrice(Double standrardPrice) {
        this.standrardPrice = standrardPrice;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Double getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(Double transactionPrice) {
        this.transactionPrice = transactionPrice;
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

    public String getReturnApplyID() {
        return returnApplyID;
    }

    public void setReturnApplyID(String returnApplyID) {
        this.returnApplyID = returnApplyID == null ? null : returnApplyID.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSalesScore() {
        return salesScore;
    }

    public void setSalesScore(Integer salesScore) {
        this.salesScore = salesScore;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getObjID() {
        return objID;
    }

    public void setObjID(String objID) {
        this.objID = objID;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public Integer getIscarriered() {
		return iscarriered;
	}

	public void setIscarriered(Integer iscarriered) {
		this.iscarriered = iscarriered;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getIsReturned() {
		return isReturned;
	}

	public void setIsReturned(Integer isReturned) {
		this.isReturned = isReturned;
	}

	public String getMainID() {
		return mainID;
	}

	public void setMainID(String mainID) {
		this.mainID = mainID;
	}

}
