package com.kpluswebup.web.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("productTypeProductPropVO")
public class ProductTypeProductPropVO {

    private Long                     id;

    private String                   mainID;

    private String                   productTypeID;

    private String                   productPropID;

    private Integer                  propType;            // '属性类型 1:单选 2：多选',

    private String                   name;

    private String                   productPropValue;

    private String                   productPropValueID;

    private List<String>             propValues;

    private Integer                  sortOrder;

    private String                   creator;

    private Date                     createTime;

    private String                   modifier;

    private Date                     modifyTime;

    private List<PropValueVO>        propValueList;

    private List<ProductPropValueVO> productPropValueList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmainID() {
        return mainID;
    }

    public void setmainID(String mainID) {
        this.mainID = mainID == null ? null : mainID.trim();
    }

    public String getproductTypeID() {
        return productTypeID;
    }

    public void setproductTypeID(String productTypeID) {
        this.productTypeID = productTypeID == null ? null : productTypeID.trim();
    }

    public String getproductPropID() {
        return productPropID;
    }

    public void setproductPropID(String productPropID) {
        this.productPropID = productPropID == null ? null : productPropID.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getproductPropValue() {
        return productPropValue;
    }

    public void setproductPropValue(String productPropValue) {
        this.productPropValue = productPropValue == null ? null : productPropValue.trim();
    }

    public String getProductPropValueID() {
        return productPropValueID;
    }

    public void setProductPropValueID(String productPropValueID) {
        this.productPropValueID = productPropValueID;
    }

    public Integer getPropType() {
        return propType;
    }

    public List<String> getPropValue() {
        String[] productPropValues = productPropValue.split(",");
        String[] productPropValueIDs = productPropValueID.split(",");

        List<String> newPropValue = new ArrayList<String>();

        if (propType == 1) {
            for (int i = 0; i < productPropValueIDs.length; i++) {
                StringBuilder str = new StringBuilder();
                str.append("<span style='margin-right:4px;margin-left:15px;'><input type='radio' name='productPropValue_"
                           + mainID
                           + "' id='productPropValue_"
                           + productPropValueIDs[i]
                           + "' value='"
                           + productPropValueIDs[i] + "_" + productPropValues[i] + "_" + mainID + "'></span>");
                str.append(productPropValues[i]);
                newPropValue.add(str.toString());
            }
        }

        if (propType == 2) {
            for (int i = 0; i < productPropValueIDs.length; i++) {
                StringBuilder str = new StringBuilder();
                str.append("<span style='margin-right:4px;margin-left:15px;'><input type='checkbox' name='productPropValue_"
                           + mainID
                           + "' id='productPropValue_"
                           + productPropValueIDs[i]
                           + "' value='"
                           + productPropValueIDs[i] + "_" + productPropValues[i] + "_" + mainID + "'></span>");
                str.append(productPropValues[i]);
                newPropValue.add(str.toString());
            }
        }

        return newPropValue;
    }

    public void setPropValues(List<ProductDetailVO> detail) {
        String[] productPropValues = productPropValue.split(",");
        String[] productPropValueIDs = productPropValueID.split(",");

        List<String> newPropValue = new ArrayList<String>();

        if (propType == 1) {
            for (int i = 0; i < productPropValueIDs.length; i++) {
                String isChecked = "";
                for (ProductDetailVO productDetailVO : detail) {
                    if (productPropValueIDs[i].equals(productDetailVO.getpTypePPropID())) {
                        isChecked = " checked='checked' ";
                        break;
                    }
                }
                StringBuilder str = new StringBuilder();
                str.append("<span style='margin-right:4px;margin-left:15px;'><input " + isChecked
                           + " type='radio' name='productPropValue_" + mainID + "' id='productPropValue_"
                           + productPropValueIDs[i] + "' value='" + productPropValueIDs[i] + "_" + productPropValues[i]
                           + "_" + mainID + "'></span>");
                str.append(productPropValues[i]);
                newPropValue.add(str.toString());
                isChecked = "";

            }
        }

        if (propType == 2) {
            for (int i = 0; i < productPropValueIDs.length; i++) {
                String isChecked = "";
                for (ProductDetailVO productDetailVO : detail) {
                    if (productPropValueIDs[i].equals(productDetailVO.getpTypePPropID())) {
                        isChecked = " checked='checked' ";
                        break;
                    }
                }
                StringBuilder str = new StringBuilder();
                str.append("<span style='margin-right:4px;margin-left:15px;'><input " + isChecked
                           + " type='checkbox' name='productPropValue_" + mainID + "' id='productPropValue_"
                           + productPropValueIDs[i] + "' value='" + productPropValueIDs[i] + "_" + productPropValues[i]
                           + "_" + mainID + "'></span>");
                str.append(productPropValues[i]);
                newPropValue.add(str.toString());

                isChecked = "";
            }
        }

        this.propValues = newPropValue;
    }

    public List<String> getPropValues() {
        return propValues;
    }

    public void setPropType(Integer propType) {
        this.propType = propType;
    }

    public Integer getsortOrder() {
        return sortOrder;
    }

    public void setsortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getcreateTime() {
        return createTime;
    }

    public void setcreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getmodifyTime() {
        return modifyTime;
    }

    public void setmodifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<PropValueVO> getPropValueList() {
        return propValueList;
    }

    public void setPropValueList(List<PropValueVO> propValueList) {
        this.propValueList = propValueList;
    }

    public List<ProductPropValueVO> getProductPropValueList() {
        return productPropValueList;
    }

    public void setProductPropValueList(List<ProductPropValueVO> productPropValueList) {
        this.productPropValueList = productPropValueList;
    }

}
