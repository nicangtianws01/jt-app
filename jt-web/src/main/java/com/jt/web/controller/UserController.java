package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.dubbo.service.DubboUserService;
import com.jt.pojo.User;
import com.jt.vo.SysResult;

@RequestMapping("/user/")
@Controller
public class UserController {
	@Reference(timeout = 30,check = false)
	private DubboUserService userService;
	
	@RequestMapping("{model}")
	public String check(@PathVariable String model) {
		return model;
	}
	@RequestMapping("doRegister")
	public SysResult saveUser(User user) {
		userService.saveUser(user);
		return SysResult.success();
	}
}
