package com.imnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qrsx.springmvc.mapper.UserMapper;
import com.qrsx.springmvc.pojo.User;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	private UserMapper userMapper;

	public User findUserById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}

}
