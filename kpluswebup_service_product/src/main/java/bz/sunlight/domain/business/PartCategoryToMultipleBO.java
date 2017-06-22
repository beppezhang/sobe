package bz.sunlight.domain.business;

import java.util.List;

import com.google.common.collect.Lists;
import com.kpluswebup.web.vo.VehicletypePartscategoryEntity;

//下属为子分类
public class PartCategoryToMultipleBO {
	
	private String categoryName;					// 备件分类名称
	private final String childType = "PartCategory";// 子节点类型
	private List<PartCategoryBO> children;			// 子分类
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getChildType() {
		return childType;
	}
	public List<PartCategoryBO> getChildren() {
		return children;
	}
	public void setChildren(List<PartCategoryBO> children) {
		this.children = children;
	}
	
	public void buildChildren(List<VehicletypePartscategoryEntity> list){
		if (list == null || list.size() == 0)
			return;
		children = Lists.newArrayList();

		for (VehicletypePartscategoryEntity entity : list){
			PartCategoryBO partCategoryBO = new PartCategoryBO();
			partCategoryBO.setCategoryCode(entity.getCode());
			partCategoryBO.setCategoryId(entity.getPartsCategoryId());
			partCategoryBO.setCategoryName(entity.getName());

        	// TODO 暂时没有相应备件分类图标,这里先不进行赋值
        	//partCategoryBO.setIcon();
			
			children.add(partCategoryBO);
		}
	}
}
