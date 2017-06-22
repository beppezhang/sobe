package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("accountDetailDTO")
public class AccountDetailDTO extends BaseDTO {

    private Long    id;

    private String  mainID;

    private String  customerID;

    private Integer accountType; // 账户类型(1：金额 2：积分)

    private Integer detailType;  // 收支类型(1：充值2：订单消费3：退款4：订单奖励5：积分奖励6：积分扣减7:扫码送积分8取消订单退积分)

    private String  objID;

    private Integer paymentType; // 支付方式(1：余额支付2：货到付款3：在线支付4：积分礼品5：线下汇款6：线下POS)

    private String  interfaceID;

    private String  serialNumber;

    private Double  amount;

    private Double  blance;

    private String  description;

    private Integer status;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;
    
    private Date startTime;
    
    private Date endTime;
    
    private Date startModifyTime;
    
    private Date endModifyTime;
    
    private String customerName;
    
    private String itemName;
    
    private Integer type; // 1：采购商 2：供应商
    
   

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getDetailType() {
        return detailType;
    }

    public void setDetailType(Integer detailType) {
        this.detailType = detailType;
    }

    public String getObjID() {
        return objID;
    }

    public void setObjID(String objID) {
        this.objID = objID;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getInterfaceID() {
        return interfaceID;
    }

    public void setInterfaceID(String interfaceID) {
        this.interfaceID = interfaceID;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBlance() {
        return blance;
    }

    public void setBlance(Double blance) {
        this.blance = blance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    
    public String getCustomerName() {
        return customerName;
    }

    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    
    public String getItemName() {
        return itemName;
    }

    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
