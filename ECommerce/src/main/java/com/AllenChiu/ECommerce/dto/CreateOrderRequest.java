package com.AllenChiu.ECommerce.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

//此class將會對應到前端傳過來的JSON Object
public class CreateOrderRequest {
	
	//因為在此buyItemList中, 它存放的是另一個JSON OBJECT,
	//所以必須再創建一個Java Class去對應到該JSON OBJECT的格式
	
	@NotEmpty
	private List<BuyItem> buyItemList;

	public List<BuyItem> getBuyItemList() {
		return buyItemList;
	}

	public void setBuyItemList(List<BuyItem> buyItemList) {
		this.buyItemList = buyItemList;
	}
}
