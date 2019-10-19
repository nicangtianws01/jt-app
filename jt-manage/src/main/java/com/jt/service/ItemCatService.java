package com.jt.service;

import java.util.List;

import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;

public interface ItemCatService {

	ItemCat findItemCatById(Integer itemCatId);

	List<EasyUITree> findItemCatByParentId(Integer parentId);
	//已使用aop切面替换
//	List<EasyUITree> findItemCatCache(Integer parentId);

}
