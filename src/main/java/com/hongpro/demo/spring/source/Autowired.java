package com.hongpro.demo.spring.source;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description
 * @date 2021/11/12 12:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Autowired {
}
