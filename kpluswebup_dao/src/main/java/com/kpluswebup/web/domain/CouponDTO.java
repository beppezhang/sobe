package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @author Administrator 优惠券
 */
@Alias("couponDTO")
public class CouponDTO extends BaseDTO {

    private Long    id;

    private String  mainID;

    private String  batchID;

    private String  password;

    private Double  amount;

    private String  customerID;

    private String  salesOrderID;

    private Date    useTime;

    private Integer status;       // 0：未发放 1：已发放 2：已使用 3：中止 4：过期

    private Date    fromDate;

    private Date    endDate;

    private Integer isDelete;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;
    
    private Integer couponDay;

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

    public String getBatchID() {
        return batchID;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getSalesOrderID() {
        return salesOrderID;
    }

    public void setSalesOrderID(String salesOrderID) {
        this.salesOrderID = salesOrderID;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    
    public Integer getCouponDay() {
        return couponDay;
    }

    
    public void setCouponDay(Integer couponDay) {
        this.couponDay = couponDay;
    }

}
