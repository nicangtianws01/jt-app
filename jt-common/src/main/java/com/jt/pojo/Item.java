package com.jt.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("tb_item")
@Data
public class Item implements Serializable{
	private static final long serialVersionUID = 7285118348625098359L;
	@TableId(type = IdType.AUTO)//自动回填主键信息
	private Long id;
	private String title;
	private String sellPoint;
	private Double price;
	private Integer num;
	private String barcode;
	private String image;
	private Integer cid;
	private Integer status;
	private Date created;
	private Date updated;
}
