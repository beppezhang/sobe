package com.kpluswebup.web.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("roleFunctionVO")
public class RoleFunctionVO implements Serializable {

    /**
     * 
     */
    private static final long    serialVersionUID = 1L;

    private Long                 id;

    private String               roleID;

    private String               functionID;

    private String               creator;

    private Date                 createTime;

    private String               modifier;

    private Date                 modifyTime;

    private String               name;

    private String               functionURL;

    private List<RoleFunctionVO> childList = new ArrayList<RoleFunctionVO>();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunctionURL() {
        return functionURL;
    }

    public void setFunctionURL(String functionURL) {
        this.functionURL = functionURL;
    }

    public List<RoleFunctionVO> getChildList() {
        return childList;
    }

    public void setChildList(List<RoleFunctionVO> childList) {
        this.childList = childList;
    }

}
