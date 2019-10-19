package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;

@RequestMapping("/item/cat/")
@RestController
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	@RequestMapping("queryItemName")
	public String findItemCatById(Integer itemCatId) {
		return itemCatService.findItemCatById(itemCatId).getName();
	}
	
	@RequestMapping("list")
	public List<EasyUITree> findItemCatByParentId(@RequestParam(value = "id",defaultValue = "0")Integer parentId) {
//		return itemCatService.findItemCatCache(parentId);
		return itemCatService.findItemCatByParentId(parentId);
	}
}
