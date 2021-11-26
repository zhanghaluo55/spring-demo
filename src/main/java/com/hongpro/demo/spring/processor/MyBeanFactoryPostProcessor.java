package com.hongpro.demo.spring.processor;

import com.hongpro.demo.spring.bean.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/10/26
 * @Version:
 */
//@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) configurableListableBeanFactory.getBeanDefinition("user");
        beanDefinition.setBeanClass(User.class);
        configurableListableBeanFactory.registerSingleton("userxx", new User());
    }
}
