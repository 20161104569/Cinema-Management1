package com.imnu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.qrsx.day05_spring.pojo.User;

/*
 * update （insert update delete）更改
 * queryForObject() 一个对象
 * query()  List
 */
public class UserDaoImp implements UserDao{
	private JdbcTemplate jdbcTemplate;
	@Override
	public void insertUser() {
		String sql="insert into user(name,password,email,birthday) values(?,?,?,?)";
		jdbcTemplate.update(sql, "John","Jon123","hon123@qq.com",new Date());
	}

	@Override
	public void deleteUser() {
		String sql="delete from user where id=?";
		jdbcTemplate.update(sql, 33);
		
	}

	@Override
	public void updateUser() {
		String sql="update user set password=?,email=? where id=?";
		jdbcTemplate.update(sql,"John123", "John123@qq.com",34);
		
	}

	@Override
	public User findUserById() {
		String sql="select * from user where id=34";
		return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
			//封装对象
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user=new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setBirthday(rs.getDate("birthday"));
				return user;
			}
			
		});
	}

	@Override
	public List<User> findUserByName() {
		String sql="select * from user where name like ?";
		return jdbcTemplate.query(sql, new MyRowMapper(), "%m%");
	}

	@Override
	public int findUserCount() {
		String sql="select count(id) from user";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}

class MyRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user=new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setBirthday(rs.getDate("birthday"));
		return user;
	}
	
}
