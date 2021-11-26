package com.hongpro.demo.spring.config;

import com.hongpro.demo.spring.bean.UserMapperFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import java.util.Objects;
import java.util.Set;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/10/12
 * @Version:
 */
public class MyMapperScanner extends ClassPathBeanDefinitionScanner {
    public MyMapperScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public MyMapperScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    public MyMapperScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment) {
        super(registry, useDefaultFilters, environment);
    }

    public MyMapperScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment, ResourceLoader resourceLoader) {
        super(registry, useDefaultFilters, environment, resourceLoader);
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            BeanDefinition beanDefinition = beanDefinitionHolder.getBeanDefinition();
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(Objects.requireNonNull(beanDefinition.getBeanClassName()));
            beanDefinition.setBeanClassName(UserMapperFactoryBean.class.getName());
        }
        return beanDefinitionHolders;
    }
}
