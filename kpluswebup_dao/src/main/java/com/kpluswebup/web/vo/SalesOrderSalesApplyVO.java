package com.kpluswebup.web.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.kpuswebup.common.vo.BaseVO;

/**
 * @author Administrator 售后申请
 */
@Alias("salesOrderSalesApplyVO")
public class SalesOrderSalesApplyVO extends BaseVO {

	private Long    id;

    private String  mainID;

    private String  customerID;
    
    private String supplierID;

    private String  salesOrderID;

    private String  productID;

    private String  itemID;

    private Integer salesType;  // 售后类型(1：维修,2：换机)

    private Integer count;
    
    private String numberIMEI;   //串号
    
    private String  memo;

    private Integer status;      // 状态(0：申请，1：拒绝，2：处理中，3：完成)

    private String  description;

    private Integer isDelete;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;
    
    private List<SalesOrderLineVO>      salesOrderLineList    = new ArrayList<SalesOrderLineVO>();
    

    // no db
    private String                            salesID;

    private Integer                           confirmSalesType;  // 确认类型

    private String                            productName;

    private String                            itemName;

    private AreaVO                            areaVO;
    
    private SalesOrderAfterSalesAddressVO     salesAddressVO;

    private String                            customerName;

    private Double                            salesPrice;         // 购买金额

    private Integer                           itemCount;

    private Integer                           confirmCount;

    private Double                            confirmSalesAmount;

    private String                            confirmDescription;

    private String                            confirmStatus;

    private String                            returnSalesOrderID;

    private Integer                           orderStatus;

    private String                            accountMainID;

    private Double                            amount;

    private Integer                           accountStatus;

    private String                            itemPicURL;
    
    private String                            supplierName;
    

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

	public String getNumberIMEI() {
		return numberIMEI;
	}

	public void setNumberIMEI(String numberIMEI) {
		this.numberIMEI = numberIMEI;
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

    public Integer getConfirmSalesType() {
        return confirmSalesType;
    }

    public void setConfirmSalesType(Integer confirmSalesType) {
        this.confirmSalesType = confirmSalesType;
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

    public Integer getConfirmCount() {
        return confirmCount;
    }

    public void setConfirmCount(Integer confirmCount) {
        this.confirmCount = confirmCount;
    }

    public Double getConfirmSalesAmount() {
        return confirmSalesAmount;
    }

    public void setConfirmSalesAmount(Double confirmSalesAmount) {
        this.confirmSalesAmount = confirmSalesAmount;
    }

    public String getSalesID() {
        return salesID;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public SalesOrderAfterSalesAddressVO getSalesAddressVO() {
        return salesAddressVO;
    }

    public void setReturnAddressVO(SalesOrderAfterSalesAddressVO salesAddressVO) {
        this.salesAddressVO = salesAddressVO;
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

    public String getItemPicURL() {
        return itemPicURL;
    }

    public void setItemPicURL(String itemPicURL) {
        this.itemPicURL = itemPicURL;
    }

	public List<SalesOrderLineVO> getSalesOrderLineList() {
		return salesOrderLineList;
	}

	public void setSalesOrderLineList(List<SalesOrderLineVO> salesOrderLineList) {
		this.salesOrderLineList = salesOrderLineList;
	}

	public void setSalesAddressVO(SalesOrderAfterSalesAddressVO salesAddressVO) {
		this.salesAddressVO = salesAddressVO;
	}

}
