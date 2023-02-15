package com.AllenChiu.ECommerce.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.AllenChiu.ECommerce.model.Product;

public class ProductRowMapper implements RowMapper<Product>{

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		//將resultSet取得的數據儲存在此product變數裡面
		Product product = new Product();
		
		product.setProductId(rs.getInt("product_id"));
		product.setProductName(rs.getString("product_name"));
		product.setCategory(rs.getString("category"));
		product.setImageUrl(rs.getString("image_Url"));
		product.setPrice(rs.getInt("price"));
		product.setStock(rs.getInt("stock"));
		product.setDescription(rs.getString("description"));
		product.setCreatedDate(rs.getTimestamp("created_Date"));
		product.setLastModifiedDate(rs.getTimestamp("last_modified_date"));
		
		return product;
	}
	
}
