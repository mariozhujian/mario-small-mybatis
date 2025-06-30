package cn.mario.mybatis.builder;


import cn.mario.mybatis.session.Configuration;
import cn.mario.mybatis.type.TypeAliasRegistry;
import lombok.Data;

/**
 * @description: 构建器的基类，建造者模式
 * @author: mario
 * @date: 2025/6/26
 */
@Data
public abstract class BaseBuilder {

    protected final Configuration configuration;
    protected final TypeAliasRegistry typeAliasRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = configuration.getTypeAliasRegistry();
    }

}
