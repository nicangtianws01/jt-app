package com.jt.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@TableName("tb_item_desc")
@Data
public class ItemDesc implements Serializable{
	private static final long serialVersionUID = 2446751443808650797L;
	@TableId
	private Long itemId;
	private String itemDesc;
	private Date created;
	private Date updated;
}
