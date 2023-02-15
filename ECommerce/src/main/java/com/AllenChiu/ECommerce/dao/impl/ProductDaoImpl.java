package com.AllenChiu.ECommerce.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.AllenChiu.ECommerce.dao.ProductDao;
import com.AllenChiu.ECommerce.model.Product;
import com.AllenChiu.ECommerce.rowmapper.ProductRowMapper;

@Component
public class ProductDaoImpl implements ProductDao{
	
	//注入NamedParameterJdbcTemplate這個bean進來
	@Autowired
	private NamedParameterJdbcTemplate nameJdbcTemplate;
	
	@Override
	public Product getProductById(Integer productId) {
		String sql = "select * from product where product_id = :productId";
		
		Map<String,Object> map = new HashMap<>();
		map.put("productId", productId);
		
		//要新增一個變數來接住query方法的返回值
		List<Product> productList= nameJdbcTemplate.query(sql, map, new ProductRowMapper());
		
		if(productList.size() > 0) {
			return productList.get(0);
		}else {
			return null;
		}
	}
}
