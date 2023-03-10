package com.AllenChiu.ECommerce.service;

import com.AllenChiu.ECommerce.dto.UserRegisterRequest;
import com.AllenChiu.ECommerce.model.User;

public interface UserService {
	
	Integer register(UserRegisterRequest userRegisterRequest);
	
	User getUserById(Integer userId);
}
