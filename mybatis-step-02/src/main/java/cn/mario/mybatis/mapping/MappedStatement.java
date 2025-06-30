package cn.mario.mybatis.mapping;


import cn.mario.mybatis.session.Configuration;
import lombok.Data;

/**
 * @description: 映射语句类
 * @author: mario
 * @date: 2025/6/27
 */
@Data
public class MappedStatement {

    private Configuration configuration;
    private String id;
    private SqlCommandType sqlCommandType;

    private BoundSql boundSql;

    MappedStatement() {

    }

    /**
     * 建造者
     */
    public static class Builder {
        private final MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, BoundSql boundSql) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.boundSql = boundSql;
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            return mappedStatement;
        }
    }

}
