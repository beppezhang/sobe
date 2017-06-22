package com.kpluswebup.web.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("codeConfigVO")
public class CodeConfigVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6978606821728287690L;

    private Long   id;

    private String codeID;

    private String codeEx;

    private String creator;

    private Date   createTime;

    private String modifier;

    private Date   modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeID() {
        return codeID;
    }

    public void setCodeID(String codeID) {
        this.codeID = codeID;
    }

    public String getCodeEx() {
        return codeEx;
    }

    public void setCodeEx(String codeEx) {
        this.codeEx = codeEx;
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

}
