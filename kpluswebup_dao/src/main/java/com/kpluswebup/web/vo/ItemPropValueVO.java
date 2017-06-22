package com.kpluswebup.web.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("itemPropValueVO")
public class ItemPropValueVO {

    private Long    id;

    private String  mainID;

    private String  itemPropID;

    private String  name;

    private String  picURL;

    private Integer isDelete;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;

    private Integer flag;

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

    public String getitemPropID() {
        return itemPropID;
    }

    public void setitemPropID(String itemPropID) {
        this.itemPropID = itemPropID == null ? null : itemPropID.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getpicURL() {
        return picURL;
    }

    public void setpicURL(String picURL) {
        this.picURL = picURL == null ? null : picURL.trim();
    }

    public Integer getisDelete() {
        return isDelete;
    }

    public void setisDelete(Integer isDelete) {
        this.isDelete = isDelete;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
