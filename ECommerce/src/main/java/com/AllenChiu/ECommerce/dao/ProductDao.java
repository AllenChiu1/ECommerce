package com.AllenChiu.ECommerce.dao;

import java.util.List;

import com.AllenChiu.ECommerce.dto.ProductRequest;
import com.AllenChiu.ECommerce.model.Product;

public interface ProductDao {
	
	List<Product> getProducts();
	
	Product getProductById(Integer productId);
	
	Integer createProduct(ProductRequest productRequest);
	
	void updateProduct(Integer productId, ProductRequest productRequest);
	
	void deleteProductById(Integer productId);
}
