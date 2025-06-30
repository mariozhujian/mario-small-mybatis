package cn.mario.mybatis.binding;


import cn.mario.mybatis.mapping.MappedStatement;
import cn.mario.mybatis.mapping.SqlCommandType;
import cn.mario.mybatis.session.Configuration;
import cn.mario.mybatis.session.SqlSession;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @description: 映射器方法
 * @author: mario
 * @date: 2025/6/27
 */
public class MappedMethod {

    private SqlCommand sqlCommand;

    public MappedMethod(Configuration configuration, Class<?> mapperClass, Method method) {
        this.sqlCommand = new SqlCommand(configuration, mapperClass, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result = null;
        switch (this.sqlCommand.getType()) {
            case INSERT:
                break;
            case UPDATE:
                break;
            case DELETE:
                break;
            case SELECT:
                result = sqlSession.selectOne(this.sqlCommand.getName(), args);
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + this.sqlCommand.getName());
        }

        return result;
    }

    @Data
    public static class SqlCommand {
        private String name;
        private SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MappedStatement mappedStatement = configuration.getMappedStatement(statementName);
            name = mappedStatement.getId();
            type = mappedStatement.getSqlCommandType();
        }
    }

}
