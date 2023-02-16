package com.AllenChiu.ECommerce.dto;

import com.AllenChiu.ECommerce.constant.ProductCategory;

import jakarta.validation.constraints.NotNull;

public class ProductRequest {
	
	//刪掉了productId, 因為是自動生成, 所以不須前端傳回來
	//創建時間設定也交給了Spring Boot程式去設定, 所以也不需前端傳回來
	
	@NotNull
	private String productName;
	
	@NotNull
	private ProductCategory category;
	
	@NotNull
	private String imageUrl;
	
	@NotNull
	private Integer price;
	
	@NotNull
	private Integer stock;
	private String description;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
