package com.hongpro.demo.spring.source;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description
 * @date 2021/11/16 12:36
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (beanName.equals("myService1")) {
            return Proxy.newProxyInstance(MyBeanPostProcessor.class.getClassLoader(), new Class[]{bean.getClass()}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("代理逻辑");
                    return method.invoke(bean, args);
                }
            });
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
