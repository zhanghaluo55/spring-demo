package com.hongpro.demo.spring.source;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description
 * @date 2021/11/16 12:34
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName);

    Object postProcessAfterInitialization(Object bean, String beanName);

}
