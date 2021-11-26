package com.hongpro.demo.spring.source;

import org.springframework.stereotype.Component;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description
 * @date 2021/11/12 12:46
 */
@Component("myService1")
public class MyService1 implements BeanNameAware,InitializingBean {
    @Autowired
    private MyService2 myService2;

    private String beanName;


    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    public void test() {
        System.out.println(myService2);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Xxxx");
    }
}
