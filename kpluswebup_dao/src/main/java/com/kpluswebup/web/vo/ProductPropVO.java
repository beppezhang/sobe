package com.kpluswebup.web.vo;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("productPropVO")
public class ProductPropVO {

    private Long                     id;

    private String                   mainID;

    private String                   name;

    private Integer                  propType; //1:单选  2：复选

    private List<ProductPropValueVO> productPropValue;

    private String                   productPropValues;

    private Integer                  isDelete;

    private String                   creator;

    private Date                     createTime;

    private String                   modifier;

    private Date                     modifyTime;

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

    public Integer getPropType() {
        return propType;
    }

    public void setPropType(Integer propType) {
        this.propType = propType;
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

    public List<ProductPropValueVO> getProductPropValue() {
        return productPropValue;
    }

    public void setProductPropValue(List<ProductPropValueVO> productPropValue) {
        this.productPropValue = productPropValue;
    }

    public String getProductPropValues() {
        return productPropValues;
    }

    public void setProductPropValues(List<ProductPropValueVO> productPropValue) {
        StringBuilder str = new StringBuilder();
        if (productPropValue != null) {
            for (ProductPropValueVO productPropValueVO : productPropValue) {                      
                str.append("<span style='margin-right:4px;margin-left:15px;'><input type='checkbox' name='productPropValue' value='"+productPropValueVO.getName()+"_"+productPropValueVO.getmainID()+"'></span>");  
                str.append(productPropValueVO.getName());
            }
        }

        this.productPropValues = str.toString();
    }

}
