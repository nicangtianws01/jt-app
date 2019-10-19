package com.jt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.Item;

@RequestMapping("/jsonP/")
@RestController
public class TestJSONPController {
	@RequestMapping("test")
	public JSONPObject testJsonP(String callback) {
		Item item = new Item();
		item.setId(123L);
		item.setTitle("test");
		JSONPObject object = new JSONPObject(callback, item);
		return object;
	}
}
