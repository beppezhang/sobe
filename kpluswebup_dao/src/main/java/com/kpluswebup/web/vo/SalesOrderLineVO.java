package com.kpluswebup.web.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.kpuswebup.common.vo.BaseVO;

/**
 * @author Administrator 订单明细
 */
@Alias("salesOrderLineVO")
public class SalesOrderLineVO extends BaseVO {

    private Long               id;
    
    private String 			   mainID;

    private String             orderID;                                       // 订单编号

    private String             productID;                                     // 产品编号

    private String             itemID;                                        // 商品编号

    private Double             standrardPrice;                                // 市场价

    private Double             salesPrice;                                    // 销售价

    private Double             transactionPrice;                              // 成交价,基于产品的优惠或改价后实际成交价格

    private Integer            itemCount;                                     // 商品数量

    private Integer            isPromotion;                                   // 是否赠品，默认 0

    private String             returnApplyID;                                 // 退换货申请单编号

    private String             productName;

    private String             itemName;

    private String             nick;

    private Double             productPrice;                                  // 金额

    private List<ItemDetailVO> itemDetailList = new ArrayList<ItemDetailVO>(); // 商品规格

    private Integer            type;                                          // 1全现金 2全积分 3现金加积分

    private Integer            salesScore;                                    // 销售积分

    private String             itemPicUrl;

    private Integer            allscore;

    private Double             allamount;

    private Integer            score;                                         // 全积分价格

    private Double             scorePrice;                                    // 金额加积分的现金部分

    private String             objID;                                         // 预售\抢购id

    private Date               preEndDate;                                    // 预售结束时间

    private String             imei;                                          // 串号
    
    private String 			   supplierID;
    
    private String 			   supplierName;

    private Integer 		   iscarriered; //是否打印过取货单
    
    private Integer			   isReturned;
    
    private Double 			   totalAmount;
    
    private Integer            status;          // 1：正常 2：退换货申请 3：已退货 4：已退款 5：已换货
    
    private Integer  		   isSubmit;        //是否有提交过评价  0无记录，1有过评价记录
    private String 			   content;			//评价内容
    private Integer  		   score_;			//评价积分
    public Integer getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(Integer isSubmit) {
		this.isSubmit = isSubmit;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getScore_() {
		return score_;
	}

	public void setScore_(Integer score_) {
		this.score_ = score_;
	}


	private List<SupplierItemIDVO> supplierItemIDList;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public List<ItemDetailVO> getItemDetailList() {
        return itemDetailList;
    }

    public void setItemDetailList(List<ItemDetailVO> itemDetailList) {
        this.itemDetailList = itemDetailList;
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

    public String getItemPicUrl() {
        return itemPicUrl;
    }

    public void setItemPicUrl(String itemPicUrl) {
        this.itemPicUrl = itemPicUrl;
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

    public String getObjID() {
        return objID;
    }

    public void setObjID(String objID) {
        this.objID = objID;
    }

    public Date getPreEndDate() {
        return preEndDate;
    }

    public void setPreEndDate(Date preEndDate) {
        this.preEndDate = preEndDate;
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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getIsReturned() {
		return isReturned;
	}

	public void setIsReturned(Integer isReturned) {
		this.isReturned = isReturned;
	}

	public List<SupplierItemIDVO> getSupplierItemIDList() {
		return supplierItemIDList;
	}

	public void setSupplierItemIDList(List<SupplierItemIDVO> supplierItemIDList) {
		this.supplierItemIDList = supplierItemIDList;
	}

	public String getMainID() {
		return mainID;
	}

	public void setMainID(String mainID) {
		this.mainID = mainID;
	}

    
    public Integer getStatus() {
        return status;
    }

    
    public void setStatus(Integer status) {
        this.status = status;
    }



}
