package com.kpluswebup.web.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.kpuswebup.common.vo.BaseVO;

/**
 * @author Administrator 会员等级
 */
@Alias("customerGradeVO")
public class CustomerGradeVO extends BaseVO {

    private Long    id;

    private String  mainID;

    private String  name;

    private Double  gradeScore; // 等级积分满足点

    private String  description;

    private Integer isDeleted;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    private Integer gradeSet;   // 等级配置

    private Double  GradeAmount; // 配置金额

    private Integer sortOrder;  // 排序

    private String  picURL;     // 图片

    private Date    startTime;

    private Date    endTime;
    
    private Double  shoppingIntegral ;//购物返积分
    
    private Double  lineShoppingIntegral ;//下线购物返积分

    public Double getGradeAmount() {
        return GradeAmount;
    }

    public void setGradeAmount(Double gradeAmount) {
        GradeAmount = gradeAmount;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getGradeSet() {
        return gradeSet;
    }

    public void setGradeSet(Integer gradeSet) {
        this.gradeSet = gradeSet;
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

    public Double getGradeScore() {
        return gradeScore;
    }

    public void setGradeScore(Double gradeScore) {
        this.gradeScore = gradeScore;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    
    public Double getShoppingIntegral() {
        return shoppingIntegral;
    }

    
    public void setShoppingIntegral(Double shoppingIntegral) {
        this.shoppingIntegral = shoppingIntegral;
    }

    
    public Double getLineShoppingIntegral() {
        return lineShoppingIntegral;
    }

    
    public void setLineShoppingIntegral(Double lineShoppingIntegral) {
        this.lineShoppingIntegral = lineShoppingIntegral;
    }

}
