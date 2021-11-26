package com.hongpro.demo.spring;

import com.hongpro.demo.spring.bean.Config;
import com.hongpro.demo.spring.bean.ShuaiFactoryBean;
import com.hongpro.demo.spring.bean.User;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description: bean初始化相关操作
 * @Author: zhangzihong
 * @CreateTime: 2021/7/10
 * @Version:
 */
public class Main {
    public static void main(String[] args) {
        //bean标签
        //ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        //注解
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        //Bean definition定义bean
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        //beanDefinition.setBeanClass(User.class);
        applicationContext.registerBeanDefinition("user", beanDefinition);

        //通过FactoryBean方式定义
//        applicationContext.registerBean(User.class, new Supplier<User>() {
//            @Override
//            public User get() {
//                User user = new User();
//                user.setNeme("XXX");
//                return user;
//            }
//        });
        beanDefinition.setBeanClass(ShuaiFactoryBean.class);
        applicationContext.registerBeanDefinition("user", beanDefinition);
       // applicationContext.refresh();
        System.out.println(applicationContext.getEnvironment().getSystemEnvironment());
        System.out.println(applicationContext.getEnvironment().getSystemProperties());

        //事件发布
        //applicationContext.publishEvent();

        //资源配置获取
       // Resource resource = applicationContext.getResource("Xxx");
       // System.out.println(resource);
        //国际化
      //  applicationContext.getMessage("test", null, Locale.CHINA);

        User user = applicationContext.getBean("user", User.class);
    }
}
