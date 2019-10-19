package com.jt.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.util.ObjectMapperUtil;
import com.jt.web.service.ItemService;
import com.jt.web.util.HttpClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	private HttpClientService httpService;
	@Override
	public Item findItemById(Long itemId) {
		Map<String,String> params = new HashMap<>();
		params.put("id", String.valueOf(itemId));
		String url = "http://manage.jt.com/web/item/findItemById";
		String result = httpService.doGet(url, params);
		log.info(result);
		return ObjectMapperUtil.toObject(result, Item.class);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		Map<String,String> params = new HashMap<>();
		params.put("id", String.valueOf(itemId));
		String url = "http://manage.jt.com/web/item/findItemDescById";
		String result = httpService.doGet(url, params);
		log.info(result);
		return ObjectMapperUtil.toObject(result, ItemDesc.class);
	}

}
