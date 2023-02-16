package com.AllenChiu.ECommerce.model;

import java.util.Date;

import com.AllenChiu.ECommerce.constant.ProductCategory;

public class Product {

	private Integer productId;
	private String productName;
	//下圖ProductCategory為ENUM類別, 提升可讀性. 返回給前端也可保證
	//此product的category一定是雙方都定義好的值
	private ProductCategory category;
	private String imageUrl;
	private Integer price;
	private Integer stock;
	private String description;
	//Date類型預設使用英國格林威治時區(GMT+0)來返回
	//所以商品的創建時間就會比資料庫的時間早了8個小時
	//如要設定時區為(GMT+8), 需去application.properties設定.
	private Date createdDate;
	private Date LastModifiedDate;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return LastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}

}
