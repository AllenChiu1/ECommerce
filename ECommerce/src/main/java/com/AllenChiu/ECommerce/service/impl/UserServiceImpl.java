package com.AllenChiu.ECommerce.service.impl;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.AllenChiu.ECommerce.dao.UserDao;
import com.AllenChiu.ECommerce.dto.UserRegisterRequest;
import com.AllenChiu.ECommerce.model.User;
import com.AllenChiu.ECommerce.service.UserService;

@Component
public class UserServiceImpl implements UserService{
	
	//Log寫法是固定的,基本上修改裡面參數即可
	private final static Logger log = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;

	@Override
	public Integer register(UserRegisterRequest userRegisterRequest) {
		//檢查註冊的Email
		User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
		
		if(user != null) {
			//丟出此Exception,前端這一次的請求就會在此被迫停止,且Spring Boot
			//程式就會去回傳一個錯誤碼給前端,表示該請求參數有問題
			//BAD_REQUEST為400,表示前端參數有問題
			//log的{}裡面的值就會是後面的變數
			log.warn("該email{}已經被註冊", userRegisterRequest.getEmail());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}else {
			//創建帳號
			return userDao.createUser(userRegisterRequest);
		}
	}

	@Override
	public User getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}
	
	
}
