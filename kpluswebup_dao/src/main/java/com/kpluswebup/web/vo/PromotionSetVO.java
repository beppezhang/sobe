package com.kpluswebup.web.vo;

import org.apache.ibatis.type.Alias;

/**
 * @author Administrator 满送促销条件
 */
@Alias("promotionSetVO")
public class PromotionSetVO {

    private Long    id;

    private String  name;

    private Integer setType; // 条件类型 1：产品类型2：产品类目3：产品选择4：商品选择

    private String  objID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSetType() {
        return setType;
    }

    public void setSetType(Integer setType) {
        this.setType = setType;
    }

    public String getObjID() {
        return objID;
    }

    public void setObjID(String objID) {
        this.objID = objID;
    }

}
