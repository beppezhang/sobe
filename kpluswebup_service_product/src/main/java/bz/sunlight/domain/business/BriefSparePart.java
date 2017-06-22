package bz.sunlight.domain.business;

import com.kpuswebup.comom.util.PropertiesUtil;

// 存放备件的简要信息
public class BriefSparePart {

	private String partId;		// 备件 Id
	private String partCode;	// 备件编号
	private String partName;	// 备件名称
	private String icon;		// 备件图标的绝对地址
	
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartCode() {
		return partCode;
	}
	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = getPicUrlPrefix() + icon;
	}
	
	//仅供内部调用的用于拼图片url的方法
	private String getPicUrlPrefix(){
		return PropertiesUtil.getInstanse().getString("mallStatic") + "/img/products/";
	}

}
