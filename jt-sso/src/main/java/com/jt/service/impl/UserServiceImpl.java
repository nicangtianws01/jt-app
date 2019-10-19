package com.jt.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper UserMapper;
	
	@Override
	public boolean checkUser(String param, Integer type) {
		Map<Integer,String>	typeMap = new HashMap<Integer, String>();
		typeMap.put(1, "username");
		typeMap.put(2, "phone");
		typeMap.put(3, "email");
		String column = typeMap.get(type);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq(column, param);
		User user = UserMapper.selectOne(queryWrapper);
		return user == null ? false : true;
	}

}
