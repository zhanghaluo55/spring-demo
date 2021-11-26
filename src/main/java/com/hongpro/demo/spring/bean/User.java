package com.hongpro.demo.spring.bean;

import org.springframework.beans.factory.Aware;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/7/10
 * @Version:
 */
@Component("user")
public class User implements Aware {
    @Shuai("shuai")
    private String neme;

    public String getNeme() {
        return neme;
    }

    public void setNeme(String neme) {
        this.neme = neme;
    }

    public void setBeanName(String s) {
        System.out.println("我子啊改是");
    }


}
