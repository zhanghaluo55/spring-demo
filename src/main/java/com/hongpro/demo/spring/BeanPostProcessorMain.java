package com.hongpro.demo.spring;

import com.hongpro.demo.spring.bean.Config;
import com.hongpro.demo.spring.service.UserInterface;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description: BeanPostProcessor相关操作
 * @Author: zhangzihong
 * @CreateTime: 2021/9/27
 * @Version:
 */
public class BeanPostProcessorMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        //User user = applicationContext.getBean("userService", User.class);
        UserInterface userInterface = (UserInterface) applicationContext.getBean("userService");
        System.out.println(userInterface);
        userInterface.test();
    }
}
