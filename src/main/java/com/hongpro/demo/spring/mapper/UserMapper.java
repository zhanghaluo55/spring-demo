package com.hongpro.demo.spring.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/9/29
 * @Version:
 */
public interface UserMapper {
    @Select("select 'user'")
    String selectById();
}
