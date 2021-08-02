package com.hongpro.demo.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/7/24
 * @Version:
 */
@Component
public class ShuaiBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Shuai.class)) {
                Shuai annotation = field.getAnnotation(Shuai.class);
                String value = annotation.value();
                System.out.println(value);
            }
        }
        return new User();
    }
}
