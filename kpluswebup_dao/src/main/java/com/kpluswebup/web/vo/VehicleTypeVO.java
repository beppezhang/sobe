package com.kpluswebup.web.vo;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.kpuswebup.common.vo.BaseVO;

@Alias("vehicleTypeVO")
public class VehicleTypeVO extends BaseVO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6387286316162949887L;

	private Long id;
	
	private String mainID;
	

	private String name;
	private String description;
	private String modelYear;
	private String displacement;
	private String productCategoryId;//整车分类 mainId
	
	private String productCategoryParentId;

	/*-----lby-----*/
	private String productId;
	
	public String getProductCategoryParentId() {
		return productCategoryParentId;
	}
	public void setProductCategoryParentId(String productCategoryParentId) {
		this.productCategoryParentId = productCategoryParentId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getModelYear() {
		return modelYear;
	}
	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}
	public String getDisplacement() {
		return displacement;
	}
	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}
	public String getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productID) {
		this.productId = productID;
	}
	
	
}
