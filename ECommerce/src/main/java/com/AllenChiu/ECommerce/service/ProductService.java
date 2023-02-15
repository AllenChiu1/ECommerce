package com.AllenChiu.ECommerce.service;

import com.AllenChiu.ECommerce.model.Product;

public interface ProductService {
	
	//表示此service可以使用此方法,根據Id查詢到產品的相關數據
	Product getProductById(Integer productId);
}