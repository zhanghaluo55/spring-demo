package com.hongpro.demo.spring.source;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description
 * @date 2021/11/10 12:49
 */
public class BeanDefinition {
    private Class aClass;
    private String scope;

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
