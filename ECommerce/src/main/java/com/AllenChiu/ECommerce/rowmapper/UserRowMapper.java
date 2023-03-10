package com.AllenChiu.ECommerce.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.AllenChiu.ECommerce.model.User;

public class UserRowMapper implements RowMapper<User>{
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		//userRowMapper就是負責將資料庫的結果去轉換成是一個User Object
		//將resultSet取得的數據儲存在此user變數裡面
		User user = new User();
		user.setUserId(rs.getInt("user_id"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setCreatedDate(rs.getTimestamp("created_Date"));
		user.setLastModifiedDate(rs.getTimestamp("last_modified_date"));
		
		return user;
	}
}
