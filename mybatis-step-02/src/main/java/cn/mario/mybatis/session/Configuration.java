package cn.mario.mybatis.session;


import cn.mario.mybatis.binding.MapperRegistry;
import cn.mario.mybatis.mapping.Environment;
import cn.mario.mybatis.mapping.MappedStatement;
import cn.mario.mybatis.type.TypeAliasRegistry;
import lombok.Data;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 配置项
 * @author: mario
 * @date: 2025/6/27
 */
@Data
public class Configuration {

    /**
     * 环境
     */
    private Environment environment;

    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    public Configuration() {
        this.typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
//        this.typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        this.typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        this.typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

}
