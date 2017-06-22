package com.kpluswebup.web.domain;

import org.apache.ibatis.type.Alias;

@Alias("supplierItemIDDTO")
public class SupplierItemIDDTO extends BaseDTO {

    private String  mainID;

    private String  carrierNoteLineDetailID;

    private String  supplierID;

    private String  orderID;

    private String  orderLineId;

    private String  serialiseNO;

    private String  itemName;

    private Integer status;                  // 是否已取货 0未取货1已取货2已发货

    private String  salesOrderLineID;        // 运送商品id

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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
