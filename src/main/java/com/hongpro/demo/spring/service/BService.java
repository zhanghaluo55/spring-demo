package com.hongpro.demo.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/10/18
 * @Version:
 */
@Component("bService")
public class BService {
    @Autowired
    private AService aService;

    public void test1() {
        System.out.println(aService);
    }

    interface HelloWorld {
          public void test2();
    }

    public void test2() {
        class CService implements HelloWorld {
            public void test2() {
                test1();
            }
        }
        HelloWorld h = new HelloWorld() {
            @Override
            public void test2() {
                test1();
            }
        };
    }

}
