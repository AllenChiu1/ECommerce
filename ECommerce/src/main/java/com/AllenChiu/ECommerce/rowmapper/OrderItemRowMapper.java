package com.AllenChiu.ECommerce.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.AllenChiu.ECommerce.model.OrderItem;

public class OrderItemRowMapper implements RowMapper<OrderItem>{

	@Override
	public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		//取得資料庫的數據再分別將它塞進order裡面
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderItemId(rs.getInt("order_item_id"));
		orderItem.setOrderId(rs.getInt("order_id"));
		orderItem.setProductId(rs.getInt("product_id"));
		orderItem.setQuantity(rs.getInt("quantity"));
		orderItem.setAmount(rs.getInt("amount"));
		
		orderItem.setProductName(rs.getString("product_name"));
		orderItem.setImageUrl(rs.getString("image_url"));
		
		return orderItem;
	}

}
