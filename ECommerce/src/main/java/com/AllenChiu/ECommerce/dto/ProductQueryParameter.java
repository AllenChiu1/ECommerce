package com.AllenChiu.ECommerce.dto;

import com.AllenChiu.ECommerce.constant.ProductCategory;

public class ProductQueryParameter {
	private ProductCategory category;
	public ProductCategory getCategory() {
		
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	private String search;
}
