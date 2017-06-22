package com.kpluswebup.web.domain;


import org.apache.ibatis.type.Alias;

@Alias("supplierCategoryDTO")
public class SupplierCategoryDTO extends BaseDTO {

    private Long    id; //id

    private String  mainID; //商家分类 mainID
    
    private String  name; //商家分类名字

    private String  supplierID; //商家mainID
    
    private Integer     listOrder; //排序
    
    private String  isParent;//是否父类
    
    private String   parentID;//父类mainID
    
    private Integer isDeleted; //是否删除

    
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

    
    public String getSupplierID() {
        return supplierID;
    }

    
    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    
    public String getIsParent() {
        return isParent;
    }

    
    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    
    public String getParentID() {
        return parentID;
    }

    
    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    
    public Integer getIsDeleted() {
        return isDeleted;
    }

    
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }


    
    public Integer getListOrder() {
        return listOrder;
    }


    
    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
    }
    
    
    

}
