package cn.mario.mybatis.binding;



import cn.mario.mybatis.session.Configuration;
import cn.mario.mybatis.session.SqlSession;
import cn.mario.mybatis.utils.ClassScanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description: 映射器注册机
 * @author: mario
 * @date: 2025/6/25
 */
public class MapperRegistry {

    private final Map<Class<?>, MapperProxyFactory<?>> knowMappers = new HashMap<>();

    private Configuration configuration;

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) this.knowMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public <T> void addMapper(Class<T> type) {
        /* Mapper 必须是接口才会注册 */
        if (type.isInterface()) {
            if (hasMapper(type)) {
                // 如果重复添加了，报错
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
            // 注册映射器代理工厂
            knowMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knowMappers.containsKey(type);
    }

    /**
     * 根据包路径，扫描包
     * @param packageName
     */
    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }


}
