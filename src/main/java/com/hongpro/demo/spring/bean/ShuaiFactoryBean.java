package com.hongpro.demo.spring.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/7/10
 * @Version:
 */
public class ShuaiFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        User user = new User();
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
