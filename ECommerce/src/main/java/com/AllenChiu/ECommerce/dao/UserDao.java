package com.AllenChiu.ECommerce.dao;

import com.AllenChiu.ECommerce.dto.UserRegisterRequest;
import com.AllenChiu.ECommerce.model.User;

public interface UserDao {
	
	Integer createUser(UserRegisterRequest userRegisterRequest);

	User getUserById(Integer userId);
	
	User getUserByEmail(String email);
	
	
}
