package com.hongpro.demo.spring.source;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description
 * @date 2021/11/12 12:51
 */
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;

}
