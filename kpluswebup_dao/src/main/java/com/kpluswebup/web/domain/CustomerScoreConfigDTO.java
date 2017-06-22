package com.kpluswebup.web.domain;

import java.util.Date;

/**
 * @author Administrator 积分配置表
 */
public class CustomerScoreConfigDTO extends BaseDTO {

    private Long    id;

    private String  scoreFunctionID; // 积分功能编号

    private Double  origin;          // 金额起点

    private Integer awardType;       // 奖励类型 1、比例，针对分级配置 2、数额

    private Double  awardAmount;     // 奖励数值

    private String  description;

    private Integer isDeleted;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScoreFunctionID() {
        return scoreFunctionID;
    }

    public void setScoreFunctionID(String scoreFunctionID) {
        this.scoreFunctionID = scoreFunctionID;
    }

    public Double getOrigin() {
        return origin;
    }

    public void setOrigin(Double origin) {
        this.origin = origin;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public Double getAwardAmount() {
        return awardAmount;
    }

    public void setAwardAmount(Double awardAmount) {
        this.awardAmount = awardAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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

}
