package com.hongpro.demo.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/10/18
 * @Version:
 */
@Component("aService")
public class AService {
    @Autowired
    private BService bService;

    public void test() {
        System.out.println(bService);
    }
}
