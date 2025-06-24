package cn.mario.mybatis.test;


import cn.mario.mybatis.binding.MapperProxyFactory;
import cn.mario.mybatis.test.dao.IUserDao;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 单元测试
 * @author: mario
 * @date: 2025/6/24
 */
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_MapperProxyFactory() {
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);

        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("cn.mario.mybatis.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中的sql语句，操作：查询用户名称");

        IUserDao userDao = factory.newInstance(sqlSession);
        String result = userDao.queryUserName("1");
        logger.info("测试结果：{}", JSON.toJSONString(result));
    }

    @Test
    public void test_proxy_class() {
        IUserDao userDao = (IUserDao) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{IUserDao.class},
                (((proxy, method, args) -> "你被代理了！"))
        );
        String result = userDao.queryUserName("1");
        logger.info("测试结果：{}", JSON.toJSONString(result));

    }


}
