package com.AllenChiu.ECommerce.dao;

import com.AllenChiu.ECommerce.dto.ProductRequest;
import com.AllenChiu.ECommerce.model.Product;

public interface ProductDao {
	
	Product getProductById(Integer productId);
	
	Integer createProduct(ProductRequest productRequest);
}
