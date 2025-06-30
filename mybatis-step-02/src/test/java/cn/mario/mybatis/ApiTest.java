package cn.mario.mybatis;


import cn.mario.mybatis.dao.IUserDao;
import cn.mario.mybatis.po.SystemUser;
import cn.mario.mybatis.session.SqlSession;
import cn.mario.mybatis.session.SqlSessionFactory;
import cn.mario.mybatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * @description: 单元测试
 * @author: mario
 * @date: 2025/6/24
 */
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);


    @Test
    public void test_sqlSessionFactory() throws IOException {
        // 1. 从 SqlSessionFactoryBuilder 构建 SqlSessionFactory
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 执行方法
        SystemUser result = userDao.queryUserInfoById(1);
        logger.info(result.getUserName());
    }


}
