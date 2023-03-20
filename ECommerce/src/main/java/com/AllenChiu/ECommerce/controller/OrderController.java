package com.AllenChiu.ECommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AllenChiu.ECommerce.dto.CreateOrderRequest;
import com.AllenChiu.ECommerce.model.Order;
import com.AllenChiu.ECommerce.service.OrderService;

import jakarta.validation.Valid;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/users/{userId}/orders")
	public ResponseEntity<?> createOrder(@PathVariable Integer userId,
										 @RequestBody @Valid CreateOrderRequest createOrderRequest){
		//此orderId就是資料庫中所創建的orderId
		Integer orderId = orderService.createOrder(userId, createOrderRequest);
		
		//除了可以將數據去存入資料庫之外, 同時下列的方法也能將這整筆訂單的資訊去回傳給前端
		Order order = orderService.getOrderById(orderId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
		
		
	};
	
	
	
	
	
	
}
