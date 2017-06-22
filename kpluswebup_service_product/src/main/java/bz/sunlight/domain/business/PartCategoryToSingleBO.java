package bz.sunlight.domain.business;

import java.util.List;

import com.google.common.collect.Lists;
import com.kpluswebup.web.vo.PartsCategoryVo;
import com.kpluswebup.web.vo.ProductVO;

//下属仅包含一个子分类
public class PartCategoryToSingleBO {
	
	private String categoryName;					// 备件分类名称
	private final String childType = "PartCategory";// 子节点类型
	private List<ChildCategory> children; 			// 子分类
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<ChildCategory> getChildren() {
		return children;
	}
	public void setChildren(List<ChildCategory> children) {
		this.children = children;
	}
	public String getChildType() {
		return childType;
	}

	public void buildChildren(PartsCategoryVo partCategory,List<ProductVO> parts){
		if (partCategory == null)
			return;
		children = Lists.newArrayList();
		
		//为了保持返回数据结构上的一致性和后期可能做出的延展，这里尽管只有一个子分类数据传入，仍将子分类做成一个list
		ChildCategory child = new ChildCategory();
		child.setCategoryName(partCategory.getName());
		child.setCategoryCode(partCategory.getCode());
		child.setCategoryId(partCategory.getMainID());

		/* TODO 由于对备件的查询暂时引用的之前系统中productService中的相应方法，
		而ChildCategory内部是没办法调用service中的方法的，但由于数据上的原因暂时这样做也没问题*/
		child.buildChildren(parts);
		
//		TODO 暂时没有相应备件分类图标,这里先不进行赋值
//		child.setIcon(icon);
		
		children.add(child);
	}
	
	public class ChildCategory
	{
		private String categoryId;						// 子分类 Id
		private String categoryCode;					// 子分类编号
		private String categoryName;					// 子分类名称
		private String icon;							// 子分类图标的绝对地址
		private final String childType = "SparePart";	// 子节点类型
		private List<BriefSparePart> children;			// 子备件
		
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
		public List<BriefSparePart> getChildren() {
			return children;
		}
		public void setChildren(List<BriefSparePart> children) {
			this.children = children;
		}
		public String getChildType() {
			return childType;
		}
		
	}
}
