package com.hongpro.demo.spring.source;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/10/26
 * @Version:
 */
public class MyApplicationContext {
    private Class configClass;

    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public MyApplicationContext(Class configClass) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.configClass = configClass;
        scan(configClass);

        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if (beanDefinition.getScope().equals("singleton")) {
                singletonObjects.put(beanName, createBean(beanName, beanDefinition));
            }
        }
    }

    private void scan(Class configClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        ComponentScan componentScan = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String[] values = componentScan.value();
        System.out.println(Arrays.toString(values));

        //解析字符串获取文件路径
        /*for (String value : values) {

        }*/
        ClassLoader classLoader = MyApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource("/com/hongpro/demo/spring/service");
        File file = new File(resource.getFile());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileTemp : files) {
                System.out.println(fileTemp);
                Class<?> aClass = classLoader.loadClass("com.hongpro.demo.spring.service.UserService");
                if (aClass.isAnnotationPresent(Component.class)) {
                    if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
                        BeanPostProcessor beanPostProcessor = (BeanPostProcessor) aClass.getDeclaredConstructor().newInstance();
                        beanPostProcessorList.add(beanPostProcessor);
                    }

                    //是一个bean 判断当前对象是单例还是原型bean
                    Component declaredAnnotation = aClass.getDeclaredAnnotation(Component.class);
                    String beanName = declaredAnnotation.value();

                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setaClass(aClass);
                    if (aClass.isAnnotationPresent(Scope.class)) {
                        Scope scopeAnnotation = aClass.getDeclaredAnnotation(Scope.class);
                        beanDefinition.setScope(scopeAnnotation.value());
                    } else {
                        beanDefinition.setScope("singleton");
                    }
                    beanDefinitionMap.put(beanName, beanDefinition);
                }
            }
        }
    }

    public Object getBean(String beanName) {
        if (beanDefinitionMap.containsKey(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals("singleton")) {
                return singletonObjects.get(beanName);
            } else {
                return createBean(beanName, beanDefinition);
            }
        } else {
            throw new NullPointerException();
        }
    }

    public Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class aClass = beanDefinition.getaClass();
        try {
            Object o = aClass.getDeclaredConstructor().newInstance();
            for (Field field : aClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    Object bean = getBean(field.getName());
                    field.set(o, bean);
                }
            }

            //aware回调
            if (o instanceof BeanNameAware) {
                ((BeanNameAware) o).setBeanName(beanName);
            }

            //初始化前
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                o = beanPostProcessor.postProcessBeforeInitialization(o, beanName);
            }

            //初始化
            if (o instanceof InitializingBean) {
                ((InitializingBean) o).afterPropertiesSet();
            }

            //初始化后
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                o = beanPostProcessor.postProcessAfterInitialization(o, beanName);
            }

            return o;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
