package com.AllenChiu.ECommerce.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.AllenChiu.ECommerce.dao.ProductDao;
import com.AllenChiu.ECommerce.dto.ProductRequest;
import com.AllenChiu.ECommerce.model.Product;
import com.AllenChiu.ECommerce.rowmapper.ProductRowMapper;

@Component
public class ProductDaoImpl implements ProductDao{
	
	//注入NamedParameterJdbcTemplate這個bean進來
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public Product getProductById(Integer productId) {
		String sql = "select * from product where product_id = :productId";
		
		Map<String,Object> map = new HashMap<>();
		map.put("productId", productId);
		
		//要新增一個變數來接住query方法的返回值
		List<Product> productList= namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
		
		if(productList.size() > 0) {
			return productList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Integer createProduct(ProductRequest productRequest) {
		String sql = "Insert into product(product_name, category, image_url, price, stock,"
				+ "description, created_date, last_modified_date)"
				+ "values(:productName, :category, :imageUrl, :price, :stock, :description,"
				+ ":createdDate, :lastModifiedDate)";
		
		Map<String,Object> map = new HashMap<>();
		map.put("productName", productRequest.getProductName());
		map.put("category", productRequest.getCategory().toString());
		map.put("imageUrl", productRequest.getImageUrl());
		map.put("price", productRequest.getPrice());
		map.put("stock", productRequest.getStock());
		map.put("description", productRequest.getDescription());
		
		//在此new了一個當下的時間, 代表該商品被創建時的時間
		Date now = new Date();
		map.put("createdDate", now);
		map.put("lastModifiedDate", now);
		
		//使用keyHolder去自動儲存資料庫自動生成的productId
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);
		
		int productId = keyHolder.getKey().intValue();
		//最後再將productId回傳出去
		return productId;
	}

	@Override
	public void updateProduct(Integer productId, ProductRequest productRequest) {
		String sql = "Update product set product_name = :productName, category = :category,image_url = :imageUrl,"
				+ "price = :price, stock = :stock, description = :description, last_modified_date = :lastModifiedDate\r\n"
				+ "where product_id = :productId";
		
		Map<String,Object> map = new HashMap<>();
		map.put("productId", productId);
		
		map.put("productName", productRequest.getProductName());
		map.put("category", productRequest.getCategory().toString());
		map.put("imageUrl", productRequest.getImageUrl());
		map.put("price", productRequest.getPrice());
		map.put("stock", productRequest.getStock());
		map.put("description", productRequest.getDescription());
		//更新最後修改時間的值
		map.put("lastModifiedDate", new Date());
		
		namedParameterJdbcTemplate.update(sql, map);
	}

	@Override
	public void deleteProductById(Integer productId) {
		String sql = "delete from product \r\n"
				+ "where product_id = :productId";
		Map<String,Object> map = new HashMap<>();
		map.put("productId", productId);
		
		namedParameterJdbcTemplate.update(sql, map);
	}
}
