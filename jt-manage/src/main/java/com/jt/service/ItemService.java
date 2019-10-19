package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;

public interface ItemService {

	EasyUITable<Item> findItemByPage(Integer page, Integer rows);

	int saveObject(Item entity,ItemDesc itemDesc);

	int updateObject(Item entity,ItemDesc itemDesc);

	int deleteObjectsByIds(Long[] ids);

	int updateItemInstock(Long[] ids);

	int updateItemReshelf(Long[] ids);

	ItemDesc findItemDescByItemId(Long itemId);

	Item findItemById(Long id);
	
}
