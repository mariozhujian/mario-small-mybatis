package cn.mario.mybatis.session.defaults;


import cn.mario.mybatis.session.Configuration;
import cn.mario.mybatis.session.SqlSession;

/**
 * @description: TODO
 * @author: mario
 * @date: 2025/6/25
 */
public class DefaultSqlSession implements SqlSession {

    /**
     * 映射器注册机
     */
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + "方法：" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

}
