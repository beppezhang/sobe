package com.kpluswebup.web.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.kpuswebup.common.vo.BaseVO;

/**
 * @author Administrator 售后单据
 */
@Alias("salesOrderAftersalesVO")
public class SalesOrderAftersalesVO extends BaseVO {

    private Long    id;

    private String  mainID;

    private String  salesApplyID;

    private String  customerID;

    private String  salesOrderID;

    private String  productID;

    private String  itemID;

    private Integer salesType;   // 退换货类型1：退货2：售后

    private Integer count;
    
    private String numberIMEI;    //串号

    private String  memo;

    private Integer status;        // 售后状态(0：申请,1：拒绝,2：处理中,3：完成)
    
    private String  expressID;

    private String  expressNumber;

    private Integer isDelete;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    private String  productName;

    private String  itemName;

    private Date    applyTime;

    private String  description;   // 备注

    public String getMainID() {
        return mainID;
    }

    public void setMainID(String mainID) {
        this.mainID = mainID == null ? null : mainID.trim();
    }

    public String getSalesApplyID() {
        return salesApplyID;
    }

    public void setSalesApplyID(String salesApplyID) {
        this.salesApplyID = salesApplyID == null ? null : salesApplyID.trim();
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

    public Integer getSalesType() {
        return salesType;
    }

    public void setSalesType(Integer salesType) {
        this.salesType = salesType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNumberIMEI() {
		return numberIMEI;
	}

	public void setNumberIMEI(String numberIMEI) {
		this.numberIMEI = numberIMEI;
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

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public String getExpressID() {
		return expressID;
	}

	public void setExpressID(String expressID) {
		this.expressID = expressID;
	}

	public String getExpressNumber() {
		return expressNumber;
	}

	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

}
