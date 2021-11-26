package com.hongpro.demo.spring.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/10/18
 * @Version:
 */
@Aspect
@Component
public class AServiceAspect {
    @Before("excution(public void com.hongpro.demo.spring.service.AService.test())")
    public void aServiceBefore() {
        System.out.println("aop");
    }
}
