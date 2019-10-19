package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;

@RequestMapping("/item/")
@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("query")
	public EasyUITable<Item> findItemByPage(Integer page,Integer rows){
		return itemService.findItemByPage(page,rows);
	}
	@RequestMapping("query/item/desc/{itemId}")
	public SysResult findItemDescByItemId(@PathVariable("itemId")Long itemId) {
		return SysResult.success(itemService.findItemDescByItemId(itemId));
	}
	@RequestMapping("save")
	public SysResult saveObject(Item entity,ItemDesc itemDesc) {
		itemService.saveObject(entity,itemDesc);
		return SysResult.success();
	}
	@RequestMapping("update")
	public SysResult updateObject(Item entity,ItemDesc itemDesc) {
		itemService.updateObject(entity,itemDesc);
		return SysResult.success();
	}
	@RequestMapping("delete")
	public SysResult deleteObjectsByIds(Long... ids) {
		itemService.deleteObjectsByIds(ids);
		return SysResult.success();
		
	}
	@RequestMapping("instock")
	public SysResult updateItemInstock(Long... ids) {
		itemService.updateItemInstock(ids);
		return SysResult.success();
	}
	@RequestMapping("reshelf")
	public SysResult updateItemReshelf(Long... ids) {
		itemService.updateItemReshelf(ids);
		return SysResult.success();
	}
}
