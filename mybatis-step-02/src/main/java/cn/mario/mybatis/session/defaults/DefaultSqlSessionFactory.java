package cn.mario.mybatis.session.defaults;


import cn.mario.mybatis.binding.MapperRegistry;
import cn.mario.mybatis.session.Configuration;
import cn.mario.mybatis.session.SqlSession;
import cn.mario.mybatis.session.SqlSessionFactory;

/**
 * @description: TODO
 * @author: mario
 * @date: 2025/6/25
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
