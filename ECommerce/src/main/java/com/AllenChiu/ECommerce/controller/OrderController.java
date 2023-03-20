package com.AllenChiu.ECommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AllenChiu.ECommerce.dto.CreateOrderRequest;
import com.AllenChiu.ECommerce.dto.OrderQueryParams;
import com.AllenChiu.ECommerce.model.Order;
import com.AllenChiu.ECommerce.service.OrderService;
import com.AllenChiu.ECommerce.util.Page;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	//此為實作查詢訂單列表的功能
	//此URL路徑用來表達帳號與訂單之間的關係
	@GetMapping("/users/{userId}/orders")
	public ResponseEntity<Page<Order>> getOrders(
			@PathVariable Integer userId,
			@RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
			@RequestParam(defaultValue = "0") @Min(0) Integer offset
			
	){
		OrderQueryParams orderQueryParams = new OrderQueryParams();
		orderQueryParams.setUserId(userId);
		orderQueryParams.setLimit(limit);
		orderQueryParams.setOffset(offset);
		
		//取得order List
		List<Order> orderList = orderService.getOrders(orderQueryParams);
		
		//取得order總數
		Integer count = orderService.countOrder(orderQueryParams);
		
		//分頁
		Page<Order> page = new Page<>();
		page.setLimit(limit);
		page.setOffset(offset);
		page.setTotal(count);
		page.setResults(orderList);
		
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	
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
