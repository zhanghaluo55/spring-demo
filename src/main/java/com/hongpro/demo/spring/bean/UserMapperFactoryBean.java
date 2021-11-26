package com.hongpro.demo.spring.bean;

import com.hongpro.demo.spring.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/10/11
 * @Version:
 */
public class UserMapperFactoryBean implements FactoryBean {

    private Class aClass;

    private SqlSession sqlSession;

    public UserMapperFactoryBean(Class aClass) {
        this.aClass = aClass;
    }

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        sqlSessionFactory.getConfiguration().addMapper(aClass);
        this.sqlSession = sqlSessionFactory.openSession();
    }

    @Override
    public Object getObject() throws Exception {
        //userMapper代理对象
       /* Object o = Proxy.newProxyInstance(UserMapperFactoryBean.class.getClassLoader(), new Class[]{aClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });*/
        //mybatis动态代理对象
        Object o = sqlSession.getMapper(aClass);
        return o;
    }

    @Override
    public Class<?> getObjectType() {
        return UserMapper.class;
    }
}
