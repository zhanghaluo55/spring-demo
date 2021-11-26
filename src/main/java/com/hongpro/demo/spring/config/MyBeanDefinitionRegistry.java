package com.hongpro.demo.spring.config;

import com.hongpro.demo.spring.bean.MyScan;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Map;

/**
 * @Description: beanDefinition注册类
 * @Author: zhangzihong
 * @CreateTime: 2021/10/12
 * @Version:
 */
public class MyBeanDefinitionRegistry implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //String path = "com.hongpro.demo.spring.mapper";
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MyScan.class.getName());
        String path = (String) annotationAttributes.get("value");

        MyMapperScanner myMapperScanner = new MyMapperScanner(registry);
        myMapperScanner.addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;
            }
        });
        myMapperScanner.scan(path);


/*        AbstractBeanDefinition beanDefinition2 = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition2.setBeanClass(UserMapperFactoryBean.class);
        beanDefinition2.getConstructorArgumentValues().addGenericArgumentValue(OrderMapper.class);
        registry.registerBeanDefinition("userMapper", beanDefinition2);*/
    }

}
