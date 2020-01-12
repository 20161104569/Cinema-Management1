package com.imnu.dao;

import java.util.List;

import com.qrsx.day05_spring.pojo.User;

public interface UserDao {
	void insertUser();
	void deleteUser();
	void updateUser();
	
	//根据id查找用户
	User findUserById();
	
	//模糊查询用户
	List<User> findUserByName();
	
	//查找符合条件的个数
	int  findUserCount();

}
