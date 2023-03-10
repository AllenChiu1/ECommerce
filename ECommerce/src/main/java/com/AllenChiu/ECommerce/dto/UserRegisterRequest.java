package com.AllenChiu.ECommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRegisterRequest {
	
	//NotBlank意思是除了該筆資料不能為null,也不能為空白的字串
	//可加上@Email,代表只有當前端傳過來的值是Email的格式時,才會被驗證成功
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
