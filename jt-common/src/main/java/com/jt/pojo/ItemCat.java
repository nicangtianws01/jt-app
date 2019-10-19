package com.jt.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("tb_item_cat")
@Data
public class ItemCat implements Serializable{
	private static final long serialVersionUID = 3829679118397624169L;
	@TableId
	private Integer id;
	private Integer parentId;
	private String name;
	private Integer status;
	private Integer sortOrder;
	private Integer isParent;
	private Date created;
	private Date updated;
}
