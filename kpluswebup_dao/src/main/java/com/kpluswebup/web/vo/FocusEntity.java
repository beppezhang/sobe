package com.kpluswebup.web.vo;

import java.io.Serializable;
import java.util.Date;

import com.kpuswebup.common.vo.BaseVO;

public class FocusEntity extends BaseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2307785397002015929L;
	
	private String mainID;
	private String customerID;
	private String myFocusInfo;
	private String referenceID;
	private Integer focusType;
	private Date createTime;
	private Integer num;
	private Integer limitNum;
	
	public Integer getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getFocusType() {
		return focusType;
	}
	public void setFocusType(Integer focusType) {
		this.focusType = focusType;
	}
	private Integer isDelete;
	
	public String getMainID() {
		return mainID;
	}
	public void setMainID(String mainID) {
		this.mainID = mainID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getMyFocusInfo() {
		return myFocusInfo;
	}
	public void setMyFocusInfo(String myFocusInfo) {
		this.myFocusInfo = myFocusInfo;
	}
	public String getReferenceID() {
		return referenceID;
	}
	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
