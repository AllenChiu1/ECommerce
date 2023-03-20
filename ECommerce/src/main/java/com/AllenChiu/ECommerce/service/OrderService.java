package com.AllenChiu.ECommerce.service;


import com.AllenChiu.ECommerce.dto.CreateOrderRequest;
import com.AllenChiu.ECommerce.model.Order;

public interface OrderService {
	
	public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
	
	public Order getOrderById(Integer userId);
}
