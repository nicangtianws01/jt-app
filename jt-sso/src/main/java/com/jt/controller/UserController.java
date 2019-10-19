package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.service.UserService;
import com.jt.vo.SysResult;

@RequestMapping("/user/")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("check/{param}/{type}")
	public JSONPObject checkUser(
			@PathVariable String param,
			@PathVariable Integer type,
			String callback) {
		System.out.println(param+String.valueOf(type));
		boolean data = userService.checkUser(param,type);
		System.out.println(new JSONPObject(callback, SysResult.success(data)).getValue());
		return new JSONPObject(callback, SysResult.success(data));
		
	}
}
