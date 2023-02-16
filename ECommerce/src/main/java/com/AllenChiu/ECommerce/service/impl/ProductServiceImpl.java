package com.AllenChiu.ECommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.AllenChiu.ECommerce.dao.ProductDao;
import com.AllenChiu.ECommerce.dto.ProductRequest;
import com.AllenChiu.ECommerce.model.Product;
import com.AllenChiu.ECommerce.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService{
	
	//注入ProductDao將其方法拿來用
	@Autowired
	private ProductDao productDao;
	
	//實作ProductService的抽象方法
	public Product getProductById(Integer productId) {
		return productDao.getProductById(productId);
	};
	
	public Integer createProduct(ProductRequest productRequest) {
		return productDao.createProduct(productRequest);
	};
	
	public void updateProduct(Integer productId, ProductRequest productRequest) {
		productDao.updateProduct(productId, productRequest);
	};
}
