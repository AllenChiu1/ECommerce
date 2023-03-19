package com.AllenChiu.ECommerce.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.AllenChiu.ECommerce.model.OrderItem;

public interface OrderDao {
	
	public Integer createOrder(Integer userId, Integer totalAmount);
	
	public void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
