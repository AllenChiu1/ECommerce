package com.AllenChiu.ECommerce.service;


import java.util.List;

import com.AllenChiu.ECommerce.dto.CreateOrderRequest;
import com.AllenChiu.ECommerce.dto.OrderQueryParams;
import com.AllenChiu.ECommerce.model.Order;

public interface OrderService {
	
	public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
	
	public Order getOrderById(Integer userId);
	
	Integer countOrder(OrderQueryParams orderQueryParams);
	
	List<Order> getOrders(OrderQueryParams orderQueryParams);
}
