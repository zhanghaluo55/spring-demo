package com.hongpro.demo.spring.service;

import com.hongpro.demo.spring.mapper.UserMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/9/27
 * @Version:
 */
@Component
///@Scope("prototype") 原型bean
public class UserService implements InitializingBean, UserInterface {
    //先byType，再byName
    @Autowired
    private UserMapper userMapper;

    public UserService() {
        System.out.println("userService构造方法");
    }

    /*@Autowired
    public UserService(User user) {
        System.out.println("userService构造方法1");
    }

    @Autowired
    public UserService(User user, User user2) {
        System.out.println("userService构造方法2");
    }


    @Autowired
    public void setUserService(User user) {
        System.out.println("userService构造方法1");
    }*/
    @Override
    public void test() {
        System.out.println("TEST方法");
        System.out.println(userMapper.selectById());
    }

    //初始化前处理
    @PostConstruct
    public void xxx() {
        System.out.println("初始化前处理");
    }

    //初始化操作
    @Override
    public void afterPropertiesSet() throws Exception {
        //一般做验证操作
        System.out.println(this);
        System.out.println("初始化");
    }
}
