package com.hongpro.demo.spring.source;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/10/26
 * @Version:
 */
public class SourceMain {
    public static void main(String[] args) throws ClassNotFoundException {
        MyApplicationContext context = new MyApplicationContext(AppConfig.class);
        Object userService = context.getBean("userService");

    }
}
