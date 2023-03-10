package com.AllenChiu.ECommerce.service.impl;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import com.AllenChiu.ECommerce.dao.UserDao;
import com.AllenChiu.ECommerce.dto.UserLoginRequest;
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
		}
		
			//使用MD5生成密碼的雜湊值,將想生成雜湊值的資料填進參數
			//還需加上getBytes,才能將該字串轉換成byte類型
			String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
			//寫如下行,user的密碼就會被我們替換成經過hash雜湊演算的雜湊值存入資料庫
			userRegisterRequest.setPassword(hashedPassword);
			//創建帳號
			return userDao.createUser(userRegisterRequest);
		}
	

	@Override
	public User getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}

	@Override
	public User login(UserLoginRequest userLoginRequest) {
		User user = userDao.getUserByEmail(userLoginRequest.getEmail());
		
		//檢查user是否存在
		if(user == null) {
			log.warn("該email {}尚未註冊", userLoginRequest.getEmail());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		//使用MD5生成密碼的雜湊值
		String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());
		
		//比較密碼
		if(user.getPassword().equals(hashedPassword)) {
			return user;
		}else {
			log.warn("email {}的密碼不正確", userLoginRequest.getEmail());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
}
