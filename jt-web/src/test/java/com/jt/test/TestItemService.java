package com.jt.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jt.web.service.ItemService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestItemService {
	@Autowired
	private ItemService itemService;
	@Test
	public void testFindItem() {
		System.out.println(itemService.findItemById(860275L));
	}
}
