package com.AllenChiu.ECommerce.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.AllenChiu.ECommerce.constant.ProductCategory;
import com.AllenChiu.ECommerce.model.Product;

public class ProductRowMapper implements RowMapper<Product>{

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		//將resultSet取得的數據儲存在此product變數裡面
		Product product = new Product();
		
		product.setProductId(rs.getInt("product_id"));
		product.setProductName(rs.getString("product_name"));
		
		//建立變數接住從資料庫取出來的字串
		String categoryStr = rs.getString("category");
		//使用ENUM類型的valueOf方法, 去找尋對應中ProductCategory的固定值.並將該字串轉換為ENUM類型
		ProductCategory category = ProductCategory.valueOf(categoryStr);
		product.setCategory(category);
		
//		product.setCategory(ProductCategory.valueOf(rs.getString("category")));
		
		product.setImageUrl(rs.getString("image_Url"));
		product.setPrice(rs.getInt("price"));
		product.setStock(rs.getInt("stock"));
		product.setDescription(rs.getString("description"));
		product.setCreatedDate(rs.getTimestamp("created_Date"));
		product.setLastModifiedDate(rs.getTimestamp("last_modified_date"));
		
		return product;
	}
	
}
