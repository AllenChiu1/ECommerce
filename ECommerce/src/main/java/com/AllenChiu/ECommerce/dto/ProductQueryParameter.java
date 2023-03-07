package com.AllenChiu.ECommerce.dto;

import com.AllenChiu.ECommerce.constant.ProductCategory;

public class ProductQueryParameter {
	private ProductCategory category;
	private String search;
	private String orderBy;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	private String sort;

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
}
