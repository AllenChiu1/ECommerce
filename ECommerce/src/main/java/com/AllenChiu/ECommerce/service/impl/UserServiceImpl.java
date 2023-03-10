package com.AllenChiu.ECommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.AllenChiu.ECommerce.dao.UserDao;
import com.AllenChiu.ECommerce.dto.UserRegisterRequest;
import com.AllenChiu.ECommerce.model.User;
import com.AllenChiu.ECommerce.service.UserService;

@Component
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public Integer register(UserRegisterRequest userRegisterRequest) {
		return userDao.createUser(userRegisterRequest);
	}

	@Override
	public User getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}
	
	
}
