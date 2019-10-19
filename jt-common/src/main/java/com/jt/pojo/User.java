package com.jt.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
@Accessors(chain = true)
public class User {
	@TableId(type = IdType.AUTO)
	private Long id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private Date created;
	private Date updated;
}
