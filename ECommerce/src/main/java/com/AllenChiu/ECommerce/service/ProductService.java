package com.AllenChiu.ECommerce.service;

import java.util.List;

import com.AllenChiu.ECommerce.dto.ProductQueryParameter;
import com.AllenChiu.ECommerce.dto.ProductRequest;
import com.AllenChiu.ECommerce.model.Product;

public interface ProductService {
	
	//此寫法是OK的,但是不好維護,如果同時有十幾個查詢條件的話,代表要填入超多參數,容錯率低
//	List<Product> getProducts(ProductCategory category, String search);
	
	List<Product> getProducts(ProductQueryParameter productQueryParameter);
	
	//表示此service可以使用此方法,根據Id查詢到產品的相關數據
	Product getProductById(Integer productId);
	
	Integer createProduct(ProductRequest productRequest);
	
	void updateProduct(Integer productId, ProductRequest productRequest);
	
	void deleteProductById(Integer productId);
	
	Integer countProduct(ProductQueryParameter productQueryParameter);
}
