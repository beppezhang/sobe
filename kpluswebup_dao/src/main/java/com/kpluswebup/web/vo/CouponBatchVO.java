package com.kpluswebup.web.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @author Administrator 优惠券批次
 */
@Alias("couponBatchVO")
public class CouponBatchVO {

    private Long    id;

    private String  mainID;

    private String  name;

    private Integer type;           // 1、自动发放 2、手动发放 3、导出打印 4、积分兑换

    private Double  amount;

    private Double  conditionAmount;
    
    private Integer couponCount;

    private Double  score;

    private String  description;

    private Date    fromDate;

    private Date    endDate;

    private Date    useFromDate;

    private Date    useEndDate;

    private Integer status;         // 1：正常 2：中止

    private Integer isDelete;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;
    
    private String createName;
    
    private Integer couponDay;

    public Long getId() {
        return id;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getConditionAmount() {
        return conditionAmount;
    }

    public void setConditionAmount(Double conditionAmount) {
        this.conditionAmount = conditionAmount;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getUseFromDate() {
        return useFromDate;
    }

    public void setUseFromDate(Date useFromDate) {
        this.useFromDate = useFromDate;
    }

    public Date getUseEndDate() {
        return useEndDate;
    }

    public void setUseEndDate(Date useEndDate) {
        this.useEndDate = useEndDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public void setId(Long id) {
        this.id = id;
    }

	public Integer getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}

    
    public String getCreateName() {
        return createName;
    }

    
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    
    public Integer getCouponDay() {
        return couponDay;
    }

    
    public void setCouponDay(Integer couponDay) {
        this.couponDay = couponDay;
    }

}
