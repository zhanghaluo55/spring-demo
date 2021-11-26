package com.hongpro.demo.spring.processor;

import com.hongpro.demo.spring.bean.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Description:  后置处理器  针对所有bean
 * @Author: zhangzihong
 * @CreateTime: 2021/9/27
 * @Version:
 */
//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("user")) {
            System.out.println(beanName);
            return new User();
        }
        return bean;
    }
}
