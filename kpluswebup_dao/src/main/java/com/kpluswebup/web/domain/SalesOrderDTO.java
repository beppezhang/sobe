package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @author Administrator 订单列表
 */
@Alias("salesOrderDTO")
public class SalesOrderDTO extends BaseDTO {

    private Long    id;

    private String  mainID;

    private String  customerID;       // 会员编号

    private String  supplierID;       // 供应商ID
    
    private String  supplierName;       // 供应商名

    private Integer orderType;        // 订单类型 0：普通订单1：预售2：团购3：抢购4：积分礼品5：换货

    private String  changeOrderID;    // 换货单编号

    private Integer paymentType;      // 支付方式1：余额支付2：货到付款3：在线支付4：积分礼品5：线下汇款

    private String  interfaceConfigID; // 支付接口编号

    private Double  productAmount;    // 产品总金额

    private Double  expressFee;       // 运费

    private Double  totalAmount;      // 订单总额

    private Double  discountAmount;   // 优惠金额

    private Double  payableAmount;    // 应付金额

    private String  expressID;        // 物流公司

    private String  expressNumber;    // 物流单号

    private Double  weight;
    
    private Integer	packageNum;       //包裹件数

    private String  memo;             // 备注

    private Integer paymentStatus;    // 支付状态 0：未支付1：已支付2：部分支付

    private Integer orderStatus;      // 订单状态 0：已取消1：待确认2：待出库3：待发货4：已发货5：已收货6：已完成 7:退货申请中 8:已退货9：售后申请中 10:已售后 11:申请已拒绝

    private Integer printStatus;      // 订单打印

    private Integer invoiceStatus;    // 出库单打印

    private Integer carriageStatus;   // 物流单打印

    private Integer isDelete;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    private Integer invoiceType;      // 发票类型 0不需要 1个人 2公司

    private String  invoiceTitle;     // 发票抬头

    private String  description;      // 后台备注

    private String  couponID;

    private Integer scoreTotal;       // 积分总额

    private Date    receivableTime;   // 收款时间
    
    private Date    shipmentsTime;   // 发货时间

    // no db
    private String  itemID;

    private String  itemName;

    private String  customerUserName;

    private String  fromDate;

    private String  endDate;

    private Date    startTime;

    private Date    endTime;

    private Date    startModifyTime;

    private Date    endModifyTime;
    
    private String  provinceID;
    
    private String  cityID;
    
    private String  districtID;
    
    private String   proofURL;//交易凭证
    
    private String   sendOutURL; //发货凭证
    
    
   

	

	public String getSendOutURL() {
        return sendOutURL;
    }

    public void setSendOutURL(String sendOutURL) {
        this.sendOutURL = sendOutURL;
    }

    public String getProofURL() {
        return proofURL;
    }

    
    public void setProofURL(String proofURL) {
        this.proofURL = proofURL;
    }

    public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getMainID() {
        return mainID;
    }

    public void setMainID(String mainID) {
        this.mainID = mainID == null ? null : mainID.trim();
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID == null ? null : customerID.trim();
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getChangeOrderID() {
        return changeOrderID;
    }

    public void setChangeOrderID(String changeOrderID) {
        this.changeOrderID = changeOrderID == null ? null : changeOrderID.trim();
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getInterfaceConfigID() {
        return interfaceConfigID;
    }

    public void setInterfaceConfigID(String interfaceConfigID) {
        this.interfaceConfigID = interfaceConfigID == null ? null : interfaceConfigID.trim();
    }

    public Double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Double productAmount) {
        this.productAmount = productAmount;
    }

    public Double getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(Double expressFee) {
        this.expressFee = expressFee;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(Double payableAmount) {
        this.payableAmount = payableAmount;
    }

    public String getExpressID() {
        return expressID;
    }

    public void setExpressID(String expressID) {
        this.expressID = expressID == null ? null : expressID.trim();
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber == null ? null : expressNumber.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPrintStatus() {
        return printStatus;
    }

    public void setPrintStatus(Integer printStatus) {
        this.printStatus = printStatus;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Integer getCarriageStatus() {
        return carriageStatus;
    }

    public void setCarriageStatus(Integer carriageStatus) {
        this.carriageStatus = carriageStatus;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCouponID() {
        return couponID;
    }

    public void setCouponID(String couponID) {
        this.couponID = couponID;
    }

    public Integer getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Integer scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Date getReceivableTime() {
        return receivableTime;
    }

    public void setReceivableTime(Date receivableTime) {
        this.receivableTime = receivableTime;
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

    public Date getStartModifyTime() {
        return startModifyTime;
    }

    public void setStartModifyTime(Date startModifyTime) {
        this.startModifyTime = startModifyTime;
    }

    public Date getEndModifyTime() {
        return endModifyTime;
    }

    public void setEndModifyTime(Date endModifyTime) {
        this.endModifyTime = endModifyTime;
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

	public Integer getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(Integer packageNum) {
		this.packageNum = packageNum;
	}

	public Date getShipmentsTime() {
		return shipmentsTime;
	}

	public void setShipmentsTime(Date shipmentsTime) {
		this.shipmentsTime = shipmentsTime;
	}

}
