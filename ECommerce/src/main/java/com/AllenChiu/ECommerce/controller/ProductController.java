package com.AllenChiu.ECommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.AllenChiu.ECommerce.model.Product;
import com.AllenChiu.ECommerce.service.ProductService;

//加此註解表示此為Controller層的bean
@RestController
public class ProductController {
	
	//為了要取得productService裡面的方法, 故需將其注入.
	@Autowired
	private ProductService productService;
	
	
	//根據RestFul API的設計原則, 如果想取得某一筆商品的數據, 會是使用Get方法來請求.
	//此註解表示要去取得某一商品的數據.
	@GetMapping("/products/{productId}")
	
	//@pathVariable註解表示這個productId的值是從URL路徑裡面給傳進來的.
	public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
		
		//當前端來請求此URL路徑, 就會透過productService的getProductById方法去資料庫查詢
		//該筆資料出來.
		Product product = productService.getProductById(productId);
		
		//將資料庫查詢出來的數據填入body方法裡面並傳回給前端.
		if(product != null) {
			return ResponseEntity.status(HttpStatus.OK).body(product);
		}else {
			//如資料庫找不到該筆資料的話, 回傳這個ResponseEntity給前端.
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	};
	
	
	
	
	
	
	
}