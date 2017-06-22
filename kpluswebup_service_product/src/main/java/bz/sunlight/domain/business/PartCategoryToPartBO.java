package bz.sunlight.domain.business;

import java.util.List;

import com.google.common.collect.Lists;
import com.kpluswebup.web.vo.ProductVO;

//下属为备件
public class PartCategoryToPartBO {

	private String categoryName;				 	// 备件分类名称
	private final String childType = "SparePart";	// 子节点类型
	private List<BriefSparePart> children;			// 子备件
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<BriefSparePart> getChildren() {
		return children;
	}
	public void setChildren(List<BriefSparePart> children) {
		this.children = children;
	}
	public String getChildType() {
		return childType;
	}

	public void buildChildren(List<ProductVO> parts){
		if (parts == null || parts.size() == 0)
			return;
		children = Lists.newArrayList();
		
		for (ProductVO part : parts){
			BriefSparePart briefPart = new BriefSparePart();
			briefPart.setPartName(part.getName());
			briefPart.setPartId(part.getMainID());
			briefPart.setPartCode(part.getCode());
			briefPart.setIcon(part.getPicURL());
			
			children.add(briefPart);
		}

	}
}
