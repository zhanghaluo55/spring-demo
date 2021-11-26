package com.hongpro.demo.spring.processor;

import com.hongpro.demo.spring.bean.Shuai;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/7/24
 * @Version:
 */
//@Component
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

                field.setAccessible(true);
                try {
                    field.set(bean, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }
}
