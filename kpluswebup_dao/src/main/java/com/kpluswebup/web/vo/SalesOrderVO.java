package com.kpluswebup.web.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.kpuswebup.common.vo.BaseVO;

@Alias("salesOrderVO")
public class SalesOrderVO extends BaseVO implements Serializable {

    /**
	 * 
	 */
    private static final long           serialVersionUID      = 1L;

    private Long                        id;

    private String                      mainID;

    private String                      customerID;                                                  // 会员编号

    private Integer                     orderType;                                                   // 订单类型
                                                                                                      // 0：普通订单1：预售2：团购3：抢购4：积分礼品5：换货

    private String                      changeOrderID;                                               // 换货单编号

    private Integer                     paymentType;                                                 // 支付方式1：线下汇款2：货到付款3：自提 4：积分礼品 10 在线支付 11 支付宝支付
                                                                                                      // 1 支付宝 2 银联3积分支付
                                                                                                      // 4 微信支付)

    private String                      interfaceConfigID;                                           // 支付接口编号

    private Double                      productAmount;                                               // 产品总金额

    private Double                      expressFee;                                                  // 运费

    private Double                      totalAmount;                                                 // 订单总额

    private Double                      discountAmount;                                              // 优惠金额

    private Double                      payableAmount;                                               // 应付金额

    private String                      expressID;                                                   // 物流公司

    private String                      expressNumber;                                               // 物流单号

    private String                      memo;                                                        // 备注

    private Integer                     paymentStatus;                                               // 支付状态
                                                                                                      // 0：未支付1：已支付2：部分支付

    private Integer                     orderStatus;                                                 // 订单状态
                                                                                                      // 0：已取消1：待确认2：已确认3：待发货4：已发货5：已收货6：已完成 7申请售后中 8 售后处理中 9 已售后 10 售后已取消 11 售后拒绝

    private Integer                     printStatus;                                                 // 订单打印

    private Integer                     invoiceStatus;                                               // 出库单打印

    private Integer                     carriageStatus;                                              // 物流单打印

    private Integer                     isDelete;

    private Integer                     invoiceType;                                                 // 发票类型 0不需要 1个人
                                                                                                      // 2公司

    private Double                      weight;
    
    private Integer						packageNum;

    private String                      invoiceTitle;                                                // 发票抬头

    private String                      description;                                                 // 后台备注

    private String                      couponID;                                                    // 优惠券

    private Integer                     itemCount;

    private List<SalesOrderLineVO>      salesOrderLineList    = new ArrayList<SalesOrderLineVO>();

    private Integer                     scoreTotal;                                                  // 积分总额

    private PreSaleVO                   preSaleVO;

    private Date                        receivableTime;                                               // 收款时间
    
    private Date                        shipmentsTime;                                               // 发货时间

    private Date                        createTime;                                               // 创建时间

    /**
     * 会员信息
     */
    private String                      customerUserName;                                            // 会员用户名

    private String                      realName;                                                    // 真实姓名

    /**
     * 订单收货人信息
     */
    private String                      dvName;

    private String                      dvMobile;

    private String                      dvAddress;

    private String                      dvTelephone;

    private String                      dvZip;

    private String                      dvProvinceID;

    private String                      dvCityID;

    private String                      dvDisctrictID;

    private String                      dvProvinceName;

    private String                      dvCityName;

    private String                      dvDistrictName;

    private SalesOrderDeliveryAddressVO salesOrderDeliveryAddressVO;

    /**
     * 订单发货人信息
     */
    private String                      spName;

    private String                      spMobile;

    private String                      spAddress;

    private String                      spTelephone;

    private String                      spZip;

    private String                      spProvinceID;

    private String                      spCityID;

    private String                      spDisctrictID;

    private String                      spProvinceName;

    private String                      spCityName;

    private String                      spDistrictName;

    private SalesOrderShippingAddressVO salesOrderShippingAddressVO;

    /**
     * 区域信息
     */
    private AreaVO                      dvAreaVO;                                                    // 送货区域信息

    private AreaVO                      spAreaVO;                                                    // 发货区域信息

    private String                      expressName;

    private String                      expressPicURL;

    private List<ExpressFormatItemVO>   expressFormatItemList = new ArrayList<ExpressFormatItemVO>();

    private Date                        nowDate;

    /**
     * 订单明细
     */
    private String                      productID;                                                   // 产品编号

    private String                      itemID;                                                      // 商品编号

    private Double                      standrardPrice;                                              // 市场价

    private Double                      salesPrice;                                                  // 销售价

    private Double                      transactionPrice;                                            // 成交价,基于产品的优惠或改价后实际成交价格

    private String                      productName;

    private String                      itemName;

    private String                      nick;

    private String                      itemPicUrl;

    private String                      objID;                                                       // 预售\抢购id

    private Date                        preEndDate;                                                  // 预售结束时间

    private Date                        preFromDate;                                                 // 预售开始时间
    
    private  List<SalesOrderTransVO>   salesOrderTrans;												//物流信息
    
    private String logisticsPic;
    
    private String supplierID;
    
    private String supplierName;
    
    private String linkQQ;
    
    private String linkWangWang;
    
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

	public String getLogisticsPic() {
		return logisticsPic;
	}

	public void setLogisticsPic(String logisticsPic) {
		this.logisticsPic = logisticsPic;
	}

	public List<SalesOrderTransVO> getSalesOrderTrans() {
		return salesOrderTrans;
	}

	public void setSalesOrderTrans(List<SalesOrderTransVO> salesOrderTrans) {
		this.salesOrderTrans = salesOrderTrans;
	}

	public String getMainID() {
        return mainID;
    }

    public void setMainID(String mainID) {
        this.mainID = mainID == null ? null : mainID.trim();
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
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

    public List<SalesOrderLineVO> getSalesOrderLineList() {
        return salesOrderLineList;
    }

    public void setSalesOrderLineList(List<SalesOrderLineVO> salesOrderLineList) {
        this.salesOrderLineList = salesOrderLineList;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getChangeOrderID() {
        return changeOrderID;
    }

    public void setChangeOrderID(String changeOrderID) {
        this.changeOrderID = changeOrderID;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public String getDvName() {
        return dvName;
    }

    public void setDvName(String dvName) {
        this.dvName = dvName;
    }

    public String getDvMobile() {
        return dvMobile;
    }

    public void setDvMobile(String dvMobile) {
        this.dvMobile = dvMobile;
    }

    public String getDvAddress() {
        return dvAddress;
    }

    public void setDvAddress(String dvAddress) {
        this.dvAddress = dvAddress;
    }

    public String getDvTelephone() {
        return dvTelephone;
    }

    public void setDvTelephone(String dvTelephone) {
        this.dvTelephone = dvTelephone;
    }

    public String getDvZip() {
        return dvZip;
    }

    public void setDvZip(String dvZip) {
        this.dvZip = dvZip;
    }

    public String getDvProvinceID() {
        return dvProvinceID;
    }

    public void setDvProvinceID(String dvProvinceID) {
        this.dvProvinceID = dvProvinceID;
    }

    public String getDvCityID() {
        return dvCityID;
    }

    public void setDvCityID(String dvCityID) {
        this.dvCityID = dvCityID;
    }

    public String getDvDisctrictID() {
        return dvDisctrictID;
    }

    public void setDvDisctrictID(String dvDisctrictID) {
        this.dvDisctrictID = dvDisctrictID;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getSpMobile() {
        return spMobile;
    }

    public void setSpMobile(String spMobile) {
        this.spMobile = spMobile;
    }

    public String getSpAddress() {
        return spAddress;
    }

    public void setSpAddress(String spAddress) {
        this.spAddress = spAddress;
    }

    public String getSpTelephone() {
        return spTelephone;
    }

    public void setSpTelephone(String spTelephone) {
        this.spTelephone = spTelephone;
    }

    public String getSpZip() {
        return spZip;
    }

    public void setSpZip(String spZip) {
        this.spZip = spZip;
    }

    public String getSpProvinceID() {
        return spProvinceID;
    }

    public void setSpProvinceID(String spProvinceID) {
        this.spProvinceID = spProvinceID;
    }

    public String getSpCityID() {
        return spCityID;
    }

    public void setSpCityID(String spCityID) {
        this.spCityID = spCityID;
    }

    public String getSpDisctrictID() {
        return spDisctrictID;
    }

    public void setSpDisctrictID(String spDisctrictID) {
        this.spDisctrictID = spDisctrictID;
    }

    public AreaVO getDvAreaVO() {
        return dvAreaVO;
    }

    public void setDvAreaVO(AreaVO dvAreaVO) {
        this.dvAreaVO = dvAreaVO;
    }

    public AreaVO getSpAreaVO() {
        return spAreaVO;
    }

    public void setSpAreaVO(AreaVO spAreaVO) {
        this.spAreaVO = spAreaVO;
    }

    public SalesOrderDeliveryAddressVO getSalesOrderDeliveryAddressVO() {
        return salesOrderDeliveryAddressVO;
    }

    public void setSalesOrderDeliveryAddressVO(SalesOrderDeliveryAddressVO salesOrderDeliveryAddressVO) {
        this.salesOrderDeliveryAddressVO = salesOrderDeliveryAddressVO;
    }

    public SalesOrderShippingAddressVO getSalesOrderShippingAddressVO() {
        return salesOrderShippingAddressVO;
    }

    public void setSalesOrderShippingAddressVO(SalesOrderShippingAddressVO salesOrderShippingAddressVO) {
        this.salesOrderShippingAddressVO = salesOrderShippingAddressVO;
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

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getDvProvinceName() {
        return dvProvinceName;
    }

    public void setDvProvinceName(String dvProvinceName) {
        this.dvProvinceName = dvProvinceName;
    }

    public String getSpProvinceName() {
        return spProvinceName;
    }

    public void setSpProvinceName(String spProvinceName) {
        this.spProvinceName = spProvinceName;
    }

    public String getDvCityName() {
        return dvCityName;
    }

    public void setDvCityName(String dvCityName) {
        this.dvCityName = dvCityName;
    }

    public String getDvDistrictName() {
        return dvDistrictName;
    }

    public void setDvDistrictName(String dvDistrictName) {
        this.dvDistrictName = dvDistrictName;
    }

    public String getSpCityName() {
        return spCityName;
    }

    public void setSpCityName(String spCityName) {
        this.spCityName = spCityName;
    }

    public String getSpDistrictName() {
        return spDistrictName;
    }

    public void setSpDistrictName(String spDistrictName) {
        this.spDistrictName = spDistrictName;
    }

    public String getExpressPicURL() {
        return expressPicURL;
    }

    public void setExpressPicURL(String expressPicURL) {
        this.expressPicURL = expressPicURL;
    }

    public List<ExpressFormatItemVO> getExpressFormatItemList() {
        return expressFormatItemList;
    }

    public void setExpressFormatItemList(List<ExpressFormatItemVO> expressFormatItemList) {
        this.expressFormatItemList = expressFormatItemList;
    }

    public Date getNowDate() {
        return nowDate;
    }

    public void setNowDate(Date nowDate) {
        this.nowDate = nowDate;
    }

    public Integer getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Integer scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public PreSaleVO getPreSaleVO() {
        return preSaleVO;
    }

    public void setPreSaleVO(PreSaleVO preSaleVO) {
        this.preSaleVO = preSaleVO;
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

    public String getItemPicUrl() {
        return itemPicUrl;
    }

    public void setItemPicUrl(String itemPicUrl) {
        this.itemPicUrl = itemPicUrl;
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

    public Date getPreFromDate() {
        return preFromDate;
    }

    public void setPreFromDate(Date preFromDate) {
        this.preFromDate = preFromDate;
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

	public Integer getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(Integer packageNum) {
		this.packageNum = packageNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getShipmentsTime() {
		return shipmentsTime;
	}

	public void setShipmentsTime(Date shipmentsTime) {
		this.shipmentsTime = shipmentsTime;
	}

    
    public Date getCreateTime() {
        return createTime;
    }

    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
