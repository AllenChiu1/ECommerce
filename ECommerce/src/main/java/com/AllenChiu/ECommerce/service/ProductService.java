package com.AllenChiu.ECommerce.service;

import java.util.List;

import com.AllenChiu.ECommerce.constant.ProductCategory;
import com.AllenChiu.ECommerce.dto.ProductRequest;
import com.AllenChiu.ECommerce.model.Product;

public interface ProductService {
	
	List<Product> getProducts(ProductCategory category, String search);
	
	//表示此service可以使用此方法,根據Id查詢到產品的相關數據
	Product getProductById(Integer productId);
	
	Integer createProduct(ProductRequest productRequest);
	
	void updateProduct(Integer productId, ProductRequest productRequest);
	
	void deleteProductById(Integer productId);
	
}
