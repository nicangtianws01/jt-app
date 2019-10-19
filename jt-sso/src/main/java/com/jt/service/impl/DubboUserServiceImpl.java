package com.jt.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.dubbo.service.DubboUserService;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@Service
public class DubboUserServiceImpl implements DubboUserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public int saveUser(User user) {
		//防止email为空报错
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setEmail(user.getPhone())
			.setCreated(new Date())
			.setUpdated(new Date())
			.setPassword(md5Pass);
		return userMapper.insert(user);
	}
}
