package com.hongpro.demo.spring.bean;

import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/7/10
 * @Version:
 */
@Component
public class User {
    @Shuai("shuai")
    private String neme;

    public String getNeme() {
        return neme;
    }

    public void setNeme(String neme) {
        this.neme = neme;
    }
}
