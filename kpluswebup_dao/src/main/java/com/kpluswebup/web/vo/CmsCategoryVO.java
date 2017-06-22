package com.kpluswebup.web.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("cmsCategoryVO")
public class CmsCategoryVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9185031551221823726L;

	private Long    id;

    private String  mainID;

    private Integer categoryType; // 1、新闻，2、公告 3、广告 4 帮助 5、其他

    private String  posionID;

    private String  name;

    private Integer sortOrder;

    private String  description;

    private Integer isDelete;

    private String  creator;

    private Date    createTime;

    private String  modifier;

    private Date    modifyTime;
    
    private List<CmsHelpCenterVO> cmsHelpCenterVO = new ArrayList<CmsHelpCenterVO>();

    public List<CmsHelpCenterVO> getCmsHelpCenterVO() {
		return cmsHelpCenterVO;
	}

	public void setCmsHelpCenterVO(List<CmsHelpCenterVO> cmsHelpCenterVO) {
		this.cmsHelpCenterVO = cmsHelpCenterVO;
	}

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

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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

    public String getPosionID() {
        return posionID;
    }

    public void setPosionID(String posionID) {
        this.posionID = posionID;
    }

}
