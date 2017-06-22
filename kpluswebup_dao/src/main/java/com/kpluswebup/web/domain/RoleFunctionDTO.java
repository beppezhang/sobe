package com.kpluswebup.web.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("roleFunctionDTO")
public class RoleFunctionDTO extends BaseDTO {

    private Long    id;

    private String  roleID;

    private String  functionID;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;
    
    //no db
    private Integer depth;

    private String  parentID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getFunctionID() {
        return functionID;
    }

    public void setFunctionID(String functionID) {
        this.functionID = functionID;
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

    
    public Integer getDepth() {
        return depth;
    }

    
    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    
    public String getParentID() {
        return parentID;
    }

    
    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

}
