package com.jt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.User;

@Mapper
public interface UserMapper extends BaseMapper<User>{

}
