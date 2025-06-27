package cn.mario.mybatis.session.defaults;


import cn.mario.mybatis.binding.MapperRegistry;
import cn.mario.mybatis.session.SqlSession;
import cn.mario.mybatis.session.SqlSessionFactory;

/**
 * @description: TODO
 * @author: mario
 * @date: 2025/6/25
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }


    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
