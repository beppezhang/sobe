package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * @author Administrator 满送活动
 */
@Alias("promotionDTO")
public class PromotionDTO extends BaseDTO {

    private Long    id;

    private String  mainID;

    private String  name;

    /*
     * type业务含义： 1、满额减额 2、满额赠品 3、满额免邮 4、满额打折 5、满额赠券 6、注册赠券 7、满件送件 8、满件免邮 9、买就减额 10、买就赠品 11、买就免邮 12、买就打折 13、买就赠券 ……
     */
    private Integer type;

    private String  description;

    private Date    fromDate;

    private Date    endDate;

    private Integer promotionCondition; // 满足条件:金额或件数

    private String  promotionValue;    // 优惠内容,可以是：金额、折扣、产品件数、产品ID、赠券ID

    private String  customerGrade;     // 等级ID串，不填表示全部

    private String  customerGroup;     // 针对特定的客户分组使用

    private Integer isDelete;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    private String  productCatID;      // 产品类目

    // no db
    private String  validDate;

    private String  startTime;
    private String  endTime;

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

    public Integer getPromotionCondition() {
        return promotionCondition;
    }

    public void setPromotionCondition(Integer promotionCondition) {
        this.promotionCondition = promotionCondition;
    }

    public String getPromotionValue() {
        return promotionValue;
    }

    public void setPromotionValue(String promotionValue) {
        this.promotionValue = promotionValue;
    }

    public String getCustomerGrade() {
        return customerGrade;
    }

    public void setCustomerGrade(String customerGrade) {
        this.customerGrade = customerGrade;
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
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

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getProductCatID() {
        return productCatID;
    }

    public void setProductCatID(String productCatID) {
        this.productCatID = productCatID;
    }

}
