package com.hongpro.demo.spring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/7/10
 * @Version:
 */
@ComponentScan("com.hongpro")
public class Config {
    @Bean
    public User user() {
        return new User();
    }
}
