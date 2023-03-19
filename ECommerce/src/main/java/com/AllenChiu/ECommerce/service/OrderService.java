package com.AllenChiu.ECommerce.service;


import com.AllenChiu.ECommerce.dto.CreateOrderRequest;

public interface OrderService {
	
	public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
	
}
