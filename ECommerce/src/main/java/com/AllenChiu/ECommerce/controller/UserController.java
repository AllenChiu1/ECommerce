package com.AllenChiu.ECommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.AllenChiu.ECommerce.dto.UserRegisterRequest;
import com.AllenChiu.ECommerce.model.User;
import com.AllenChiu.ECommerce.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/users/register")
	//@requestBody表示我們要去接住前端傳過來的requestBody
	//@Valid表示要去驗證這個POST請求的requestBody參數
	public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
		//register方法執行成功後會返回資料庫生成的id回來
		Integer userId =userService.register(userRegisterRequest);
		//創建成功後即可使用該userId去查詢資料
		User user = userService.getUserById(userId);
		//再把創建出來的user回傳給前端
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	};
	
	
}
