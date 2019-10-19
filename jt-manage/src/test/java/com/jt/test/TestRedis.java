package com.jt.test;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemDesc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
	private final static ObjectMapper MAPPER = new ObjectMapper();
	@Test
	public void toJSON() throws IOException {
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(100L)
				.setItemDesc("商品详情")
				.setCreated(new Date())
				.setUpdated(itemDesc.getCreated());
		String json = MAPPER.writeValueAsString(itemDesc);
		System.out.println(json);
		
		//将json转化为对象
		ItemDesc desc = MAPPER.readValue(json, ItemDesc.class);
		System.out.println(desc.toString());
		
	}
}
