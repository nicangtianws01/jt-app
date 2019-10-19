package com.jt.web.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;

public interface ItemService {

	Item findItemById(Long id);

	ItemDesc findItemDescById(Long itemId);

}
