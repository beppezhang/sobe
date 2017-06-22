package com.kpluswebup.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.kpluswebup.web.vo.ProductCategoryVO;

public class BrandModel {
	private String code;
	private List<ProductCategoryVO> brands = new ArrayList<ProductCategoryVO>();
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<ProductCategoryVO> getBrands() {
		return brands;
	}
	public void setBrands(List<ProductCategoryVO> brands) {
		this.brands = brands;
	}
	
}
