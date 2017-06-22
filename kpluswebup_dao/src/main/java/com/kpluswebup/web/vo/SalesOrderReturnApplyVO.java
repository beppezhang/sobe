package com.kpluswebup.web.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.kpuswebup.common.vo.BaseVO;

/**
 * @author Administrator 退换货申请
 */
@Alias("salesOrderReturnApplyVO")
public class SalesOrderReturnApplyVO extends BaseVO {

    private Long                              id;

    private String                            mainID;

    private String                            customerID;

    private String                            salesOrderID;

    private String                            productID;

    private String                            itemID;

    private Integer                           returnType;         // 退换货类型 1：退货2：换货

    private Integer                           count;

    private Double                            returnAmount;

    private Integer                           returnScore;        // 退积分

    private String                            memo;

    private Integer                           status;             // 0：待确认1：已确认2：拒绝3：已退货4：已收货5：退款中6：换货中7：已退款8：已换货9：完成

    private String                            description;

    private Integer                           isDelete;

    private String                            creator;

    private Date                              createTime;

    private String                            modifier;

    private Date                              modifyTime;

    private Integer                           type;               // 1全现金 2全积分 3现金加积分
    

    // no db
    private String                            returnID;

    private Integer                           confirmReturnType;  // 确认类型

    private String                            productName;

    private String                            itemName;

    private AreaVO                            areaVO;

    private ShippingAddressVO                 shippingAddressVO;

    private SalesOrderReturnShippingAddressVO returnAddressVO;

    private String                            customerName;

    private Double                            salesPrice;         // 购买金额

    private Integer                           salesScore;         // 销售积分

    private Integer                           itemCount;

    private Integer                           confirmCount;

    private Double                            confirmReturnAmount;

    private String                            confirmDescription;

    private String                            confirmStatus;

    private String                            returnSalesOrderID;

    private Integer                           orderStatus;

    private String                            accountMainID;

    private Double                            amount;

    private Integer                           accountStatus;

    private String                            itemPicURL;


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

    public String getSalesOrderID() {
        return salesOrderID;
    }

    public void setSalesOrderID(String salesOrderID) {
        this.salesOrderID = salesOrderID == null ? null : salesOrderID.trim();
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

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(Double returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public Integer getConfirmReturnType() {
        return confirmReturnType;
    }

    public void setConfirmReturnType(Integer confirmReturnType) {
        this.confirmReturnType = confirmReturnType;
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

    public AreaVO getAreaVO() {
        return areaVO;
    }

    public void setAreaVO(AreaVO areaVO) {
        this.areaVO = areaVO;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public ShippingAddressVO getShippingAddressVO() {
        return shippingAddressVO;
    }

    public void setShippingAddressVO(ShippingAddressVO shippingAddressVO) {
        this.shippingAddressVO = shippingAddressVO;
    }

    public Integer getConfirmCount() {
        return confirmCount;
    }

    public void setConfirmCount(Integer confirmCount) {
        this.confirmCount = confirmCount;
    }

    public Double getConfirmReturnAmount() {
        return confirmReturnAmount;
    }

    public void setConfirmReturnAmount(Double confirmReturnAmount) {
        this.confirmReturnAmount = confirmReturnAmount;
    }

    public String getReturnID() {
        return returnID;
    }

    public void setReturnID(String returnID) {
        this.returnID = returnID;
    }

    public SalesOrderReturnShippingAddressVO getReturnAddressVO() {
        return returnAddressVO;
    }

    public void setReturnAddressVO(SalesOrderReturnShippingAddressVO returnAddressVO) {
        this.returnAddressVO = returnAddressVO;
    }

    public String getConfirmDescription() {
        return confirmDescription;
    }

    public void setConfirmDescription(String confirmDescription) {
        this.confirmDescription = confirmDescription;
    }

    public String getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public String getReturnSalesOrderID() {
        return returnSalesOrderID;
    }

    public void setReturnSalesOrderID(String returnSalesOrderID) {
        this.returnSalesOrderID = returnSalesOrderID;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAccountMainID() {
        return accountMainID;
    }

    public void setAccountMainID(String accountMainID) {
        this.accountMainID = accountMainID;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
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

    public String getItemPicURL() {
        return itemPicURL;
    }

    public void setItemPicURL(String itemPicURL) {
        this.itemPicURL = itemPicURL;
    }

    public Integer getReturnScore() {
        return returnScore;
    }

    public void setReturnScore(Integer returnScore) {
        this.returnScore = returnScore;
    }

}
