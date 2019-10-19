package com.jt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.annotation.RequiredCache;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.EasyUITree;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.ShardedJedis;

//@Slf4j
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private ItemCatMapper itemCatMapper;
//	@Autowired
//	private ShardedJedis jedis;
	@Override
	public ItemCat findItemCatById(Integer itemCatId) {
		return itemCatMapper.selectById(itemCatId);
	}

	@RequiredCache
	@Override
	public List<EasyUITree> findItemCatByParentId(Integer parentId) {
		//1.获取itemcat集合
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("parent_id", parentId);
		List<ItemCat> list = itemCatMapper.selectList(queryWrapper);
		//2.获取itemcat的id和name，赋值给easyuitree对象,并设置它的状态
		List<EasyUITree> result = new ArrayList<>();
		for (ItemCat itemCat : list) {
			EasyUITree tree = new EasyUITree();
			tree.setId(itemCat.getId());
			tree.setText(itemCat.getName());
			Integer isParent = itemCat.getIsParent();
			tree.setState(isParent == 1?"closed":"open");
			result.add(tree);
		}
		//3.返回结果
		return result;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<EasyUITree> findItemCatCache(Integer parentId) {
//		String key = "ITEM_CAT_" + parentId;
//		String result = jedis.get(key);
//		List<EasyUITree> tree = new ArrayList<>();
//		if(StringUtils.isEmpty(result)) {
//			//首次查询,数据不存在
//			tree = findItemCatByParentId(parentId);
//			String value = ObjectMapperUtil.toJSON(tree);
//			jedis.set(key, value);
//			log.info("首次查询");
//		}else {
//			//将查到的数据转化为目标对象
//			tree = ObjectMapperUtil.toObject(result, tree.getClass());
//			log.info("非首次查询");
//		}
//		return tree;
//	}
}
