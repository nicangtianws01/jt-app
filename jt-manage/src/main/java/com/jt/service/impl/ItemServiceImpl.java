package com.jt.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.exception.ServiceException;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUITable;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public EasyUITable<Item> findItemByPage(Integer page, Integer rows) {
		//获取总行数
		int tatol = itemMapper.selectCount(null);
		//计算当前页起始行数
		int pageSize = 20;
		int start = (page - 1) * pageSize;
		//获取分页数据
		List<Item> list = itemMapper.findItemByPage(start,pageSize);
		EasyUITable<Item> table = new EasyUITable<>();
		table.setTotal(tatol);
		table.setRows(list);
		
		return table;
	}

	@Override
	public int saveObject(Item entity,ItemDesc itemDesc) {
		entity.setStatus(1);
		entity.setCreated(new Date());
		entity.setUpdated(new Date());
		checkEntity(entity);
		int rows = itemMapper.insertObject(entity);
		if(rows == 0) {
			throw new ServiceException("添加失败！");
		}
		itemDesc.setItemId(entity.getId());
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return rows;
	}

	@Override
	public int updateObject(Item entity,ItemDesc itemDesc) {
		entity.setUpdated(new Date());
		checkEntity(entity);
		int rows = itemMapper.updateById(entity);
		if(rows == 0) {
			throw new ServiceException("更新失败！");
		}
		itemDesc.setItemId(entity.getId());
		itemDesc.setUpdated(new Date());
		itemDescMapper.updateById(itemDesc);
		return rows;
	}
	
	private void checkEntity(Item entity) {
		if(entity == null) {
			throw new IllegalArgumentException("参数错误：entity不能为空！");
		}
		if(StringUtils.isEmpty(entity.getTitle())) {
			throw new IllegalArgumentException("参数错误：标题不能为空！");
		}
		if(StringUtils.isEmpty(entity.getCid())) {
			throw new IllegalArgumentException("参数错误：类目不能为空！");
		}
		if(StringUtils.isEmpty(entity.getPrice())) {
			throw new IllegalArgumentException("参数错误：价格不能为空！");
		}
		if(StringUtils.isEmpty(entity.getNum())) {
			throw new IllegalArgumentException("参数错误：库存不能为空！");
		}
	}

	@Override
	public int deleteObjectsByIds(Long[] ids) {
		List<Long> idList = Arrays.asList(ids);
		int rows = itemMapper.deleteBatchIds(idList);
		if(rows == 0) {
			throw new ServiceException("删除失败！");
		}
		itemDescMapper.deleteBatchIds(idList);
		return rows;
	}

	@Override
	public int updateItemInstock(Long[] ids) {
		int count = 0;
		for (Long id : ids) {
			Item item = new Item();
			item.setStatus(2);
			item.setId(id);
			item.setUpdated(new Date());
			int rows = itemMapper.updateById(item);
			count += rows;
		}

		return count;
	}

	@Override
	public int updateItemReshelf(Long[] ids) {
		int count = 0;
		for (Long id : ids) {
			Item item = new Item();
			item.setStatus(1);
			item.setId(id);
			item.setUpdated(new Date());
			int rows = itemMapper.updateById(item);
			count += rows;
		}

		return count;
	}

	@Override
	public ItemDesc findItemDescByItemId(Long itemId) {
		if(itemId == null) {
			throw new IllegalArgumentException("参数错误：itemId="+itemId);
		}
		return itemDescMapper.selectById(itemId);
	}

	@Override
	public Item findItemById(Long id) {
		return itemMapper.selectById(id);
	}
	
	
	
	
	
}
