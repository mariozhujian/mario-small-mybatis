package cn.mario.mybatis.binding;



import cn.mario.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * 映射器代理工厂
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

//    public T newInstance(Map<String, String> sqlSession) {
//        MapperProxy<T> mapperProxy = new MapperProxy<>(mapperInterface, sqlSession);
//        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
//    }

    public T newInstance(SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }

}
