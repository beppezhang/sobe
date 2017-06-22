package bz.sunlight.domain.business;

public class PartCategoryBO {

	private String categoryId;				// 备件分类 Id
	private String categoryCode;			// 备件分类编号
	private String categoryName;			// 备件分类名称
	private String icon; 					// 备件分类图标的绝对地址
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
