package com.AllenChiu.ECommerce.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.AllenChiu.ECommerce.dao.UserDao;
import com.AllenChiu.ECommerce.dto.UserRegisterRequest;
import com.AllenChiu.ECommerce.model.User;
import com.AllenChiu.ECommerce.rowmapper.UserRowMapper;

@Component
public class UserDaoIml implements UserDao{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Integer createUser(UserRegisterRequest userRegisterRequest) {
		
		String sql = "INSERT INTO user(email, password, created_date, last_modified_date)" +
				"VALUES (:email, :password, :createdDate, :lastModifiedDate)";
		
		Map<String,Object> map = new HashMap<>();
		map.put("email", userRegisterRequest.getEmail());
		map.put("password", userRegisterRequest.getPassword());
		
		Date now = new Date();
		map.put("createdDate", now);
		map.put("lastModifiedDate", now);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder );
		
		int userId = keyHolder.getKey().intValue();
		
		return userId;
	}

	@Override
	public User getUserById(Integer userId) {
		String sql = "SELECT * FROM user where user_id = :userId;";
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		
		//等SQL執行完後,再使用userRowMapper將資料庫的結果轉換成是一個userList
		List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
		
		if(userList.size() > 0) {
			return userList.get(0);
		}else{
			return null;
		}
	}
}
