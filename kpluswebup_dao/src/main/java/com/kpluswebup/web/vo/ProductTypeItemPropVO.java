package com.kpluswebup.web.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("productTypeItemPropVO")
public class ProductTypeItemPropVO {

    private Long                  id;

    private String                mainID;

    private String                productTypeID;

    private String                itemPropID;

    private String                name;

    private String                itemPropValue;

    private String                itemPropValueID;

    private Integer               sortOrder;

    private String                creator;

    private Date                  createTime;

    private String                modifier;

    private Date                  modifyTime;

    private List<ItemPropValueVO> itemPropValueVO;

    private List<ItemPropValueVO> itemPropValueList;

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

    public String getProductTypeID() {
        return productTypeID;
    }

    public void setProductTypeID(String productTypeID) {
        this.productTypeID = productTypeID;
    }

    public String getItemPropID() {
        return itemPropID;
    }

    public void setItemPropID(String itemPropID) {
        this.itemPropID = itemPropID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemPropValue() {
        return itemPropValue;
    }

    public void setItemPropValue(String itemPropValue) {
        this.itemPropValue = itemPropValue;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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

    public List<ItemPropValueVO> getItemPropValueVO() {
        return itemPropValueVO;
    }

    public void setItemPropValueVO(String itemPropID, String itemPropValue) {
        if (itemPropID == null || itemPropValue == null) {

        }
        String[] ids = itemPropID.split(",");
        String[] names = itemPropValue.split(",");
        List<ItemPropValueVO> itemPropValueList = new ArrayList<ItemPropValueVO>();
        for (int i = 0; i < ids.length; i++) {
            ItemPropValueVO itemPropValueVO = new ItemPropValueVO();
            itemPropValueVO.setmainID(ids[i]);
            itemPropValueVO.setName(names[i]);
            itemPropValueList.add(itemPropValueVO);
        }
        this.itemPropValueVO = itemPropValueList;
    }

    public String getItemPropValueID() {
        return itemPropValueID;
    }

    public void setItemPropValueID(String itemPropValueID) {
        this.itemPropValueID = itemPropValueID;
    }

    public List<ItemPropValueVO> getItemPropValueList() {
        return itemPropValueList;
    }

    public void setItemPropValueList(List<ItemPropValueVO> itemPropValueList) {
        this.itemPropValueList = itemPropValueList;
    }

}
