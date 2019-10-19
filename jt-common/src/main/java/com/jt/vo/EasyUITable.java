package com.jt.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class EasyUITable<T> implements Serializable{
	private static final long serialVersionUID = -5887797255188646623L;
	private Integer total;
	private List<T> rows;
}
