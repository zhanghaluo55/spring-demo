package com.hongpro.demo.spring;

import com.hongpro.demo.spring.bean.MyScan;
import com.hongpro.demo.spring.bean.UserMapperFactoryBean;
import com.hongpro.demo.spring.mapper.UserMapper;
import com.hongpro.demo.spring.service.AService;
import com.hongpro.demo.spring.service.UserInterface;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/9/29
 * @Version:
 */
@ComponentScan("com.hongpro")
@MyScan("com.hongpro.demo.spring.mapper")
@EnableAspectJAutoProxy
public class MyApplication {
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyApplication.class);

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(UserMapperFactoryBean.class);
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(UserMapper.class);
        applicationContext.registerBeanDefinition("userMapper", beanDefinition);

        System.out.println(applicationContext.getBean("userMapper"));
        System.out.println(applicationContext.getBean("&userMapper"));

        UserInterface userInterface = (UserInterface) applicationContext.getBean("userService");
        System.out.println(userInterface);
        userInterface.test();

        // spring代理对象创建逻辑
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(AService.class);
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] objects, Object o) throws Throwable {
                System.out.println("执行目标方法之前");
            }
        });

        Object proxy = proxyFactory.getProxy();

    }
}
