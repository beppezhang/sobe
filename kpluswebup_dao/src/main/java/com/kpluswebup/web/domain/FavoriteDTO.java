package com.kpluswebup.web.domain;

import java.util.Date;

/**
 * @author Administrator 收藏
 */
public class FavoriteDTO extends BaseDTO {

    private Long   id;

    private String customerID;

    private String productID;

    private String itemID;

    private String creator;

    private Date   createTime;

    private String modifier;

    private Date   modifyTime;
    
    private Integer isDelete;//1：删除 0：正常

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
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

    
    public Integer getIsDelete() {
        return isDelete;
    }

    
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    
    

}
