package com.AllenChiu.ECommerce.dto;

import jakarta.validation.constraints.NotNull;

public class BuyItem {
	
	//此兩個變數分別去對應前端傳過來的兩個KEY.
	@NotNull
	private Integer productId;
	 
	@NotNull
	private Integer quantity;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
