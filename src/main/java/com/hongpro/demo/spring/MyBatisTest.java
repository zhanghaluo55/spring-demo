package com.hongpro.demo.spring;

import com.hongpro.demo.spring.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @Author: zhangzihong
 * @CreateTime: 2021/10/12
 * @Version:
 */
public class MyBatisTest {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        sqlSession.commit();
        sqlSession.flushStatements();
        sqlSession.close();

    }
}
