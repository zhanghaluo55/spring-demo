package com.hongpro.demo.spring.processor;

import com.hongpro.demo.spring.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/9/27
 * @Version:
 */
//@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    //bean实例化前
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanName.equals("userService")) {
            System.out.println("实例化前");
        }
        return new UserService();
    }

    //false 则实例化后逻辑终止，属性填充、初始化逻辑被腰斩
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("userService")) {
            System.out.println("实例化后");
        }
        //return false;
        return true;
    }

    //初始化前执行，返回null则@PostConstruct相关逻辑不执行
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("userService")) {
            System.out.println("初始化之前");

            for (Method method : bean.getClass().getMethods()) {
                if (method.isAnnotationPresent(PostConstruct.class)) {
                    try {
                        method.invoke(bean);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return bean;
    }

    //初始化后, 模拟aop
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("userService")) {
            System.out.println("初始化后");
            Object o = Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    method.invoke(bean, args);
                    System.out.println("代理逻辑");
                    return null;
                }
            });
            return o;
        }
        return bean;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }
}
