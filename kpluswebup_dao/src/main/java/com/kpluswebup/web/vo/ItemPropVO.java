package com.kpluswebup.web.vo;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("itemPropVO")
public class ItemPropVO {

    private Long                  id;

    private String                mainID;

    private String                name;

    private String                description;

    private List<ItemPropValueVO> itemPropValues;

    private String                itemPropValue;

    private Integer               isDelete;

    private String                creator;

    private Date                  createTime;

    private String                modifier;

    private Date                  modifyTime;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<ItemPropValueVO> getItemPropValues() {
        return itemPropValues;
    }

    public void setItemPropValues(List<ItemPropValueVO> itemPropValues) {
        this.itemPropValues = itemPropValues;
    }

    public String getItemPropValue() {
        return itemPropValue;
    }

    public void setItemPropValue(List<ItemPropValueVO> itemPropValues) {
        StringBuilder str = new StringBuilder();
        if (itemPropValues != null) {
            for (ItemPropValueVO itemPropValueVO : itemPropValues) {
                str.append("<span style='margin-right:4px;margin-left:15px;'><input type='checkbox' name='itemPropValue' value='"
                           + itemPropValueVO.getName()+"_"+itemPropValueVO.getmainID() + "'></span>");
                str.append(itemPropValueVO.getName());
            }
        }

        this.itemPropValue = str.toString();
    }

}
