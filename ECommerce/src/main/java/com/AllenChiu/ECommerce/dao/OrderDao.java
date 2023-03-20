package com.AllenChiu.ECommerce.dao;

import java.util.List;

import com.AllenChiu.ECommerce.model.Order;
import com.AllenChiu.ECommerce.model.OrderItem;

public interface OrderDao {
	
	public Integer createOrder(Integer userId, Integer totalAmount);
	
	public void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
	
	public Order getOrderById(Integer orderId);
	
	List<OrderItem> getOrderItemsByOrderId(Integer orderId);
}
