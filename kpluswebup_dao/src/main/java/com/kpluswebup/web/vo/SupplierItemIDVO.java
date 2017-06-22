package com.kpluswebup.web.vo;

import org.apache.ibatis.type.Alias;

@Alias("supplierItemIDVO")
public class SupplierItemIDVO {

    private String  mainID;

    private String  carrierNoteLineDetailID;

    private String  supplierID;

    private String  supplierName;

    private String  orderID;

    private String  orderLineId;

    private String  serialiseNO;

    private String  itemName;

    private Integer status;                  // 是否已取货 0未取货1已取货2已发货

    private Integer storeStatus;             // 入库状态 0未入库1已入库

    private String  salesOrderLineID;       // 运送商品id

    public String getMainID() {
        return mainID;
    }

    public void setMainID(String mainID) {
        this.mainID = mainID;
    }

    public String getCarrierNoteLineDetailID() {
        return carrierNoteLineDetailID;
    }

    public void setCarrierNoteLineDetailID(String carrierNoteLineDetailID) {
        this.carrierNoteLineDetailID = carrierNoteLineDetailID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSerialiseNO() {
        return serialiseNO;
    }

    public void setSerialiseNO(String serialiseNO) {
        this.serialiseNO = serialiseNO;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(Integer storeStatus) {
        this.storeStatus = storeStatus;
    }

    public String getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(String orderLineId) {
        this.orderLineId = orderLineId;
    }

    public String getSalesOrderLineID() {
        return salesOrderLineID;
    }

    public void setSalesOrderLineID(String salesOrderLineID) {
        this.salesOrderLineID = salesOrderLineID;
    }

}
