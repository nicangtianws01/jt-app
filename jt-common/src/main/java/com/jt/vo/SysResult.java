package com.jt.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class SysResult implements Serializable{
	private static final long serialVersionUID = -4297934490303822767L;
	private Integer status = 200;
	private String msg = "ok";
	private Object data;
	
	public SysResult(Throwable t) {
		super();
		this.status = 500;
		this.msg = t.getMessage();
	}
	public static SysResult success(Object data) {
		SysResult result = new SysResult();
		result.setData(data);
		return result;
	}
	public static SysResult success() {
		SysResult result = new SysResult();
		return result;
	}
	public static SysResult failed() {
		SysResult result = new SysResult();
		result.setMsg("failed");
		return result;
	}
	public SysResult() {
		super();
	}
	
	
}
