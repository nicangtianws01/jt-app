package com.jt.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EasyUITree implements Serializable{
	private static final long serialVersionUID = -6026585273875121549L;
	private Integer id;
	private String text;//节点文本信息
	private String state;//节点状态信息
}
