package com.hongpro.demo.spring.bean;

import com.hongpro.demo.spring.config.MyBeanDefinitionRegistry;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/10/15
 * @Version:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyBeanDefinitionRegistry.class)
public @interface MyScan {
    String value();
}
