package cn.mario.mybatis.mapping;


import lombok.Data;

import java.util.Map;

/**
 * @description: 绑定的SQL,是从SqlSource而来，将动态内容都处理完成得到的SQL语句字符串，其中包括?,还有绑定的参数
 * @author: mario
 * @date: 2025/6/30
 */
@Data
public class BoundSql {

    private String sql;

    private String parameterType;

    private String resultType;

    private Map<Integer, String> parameterMappings;

    public BoundSql(String sql, String parameterType, String resultType, Map<Integer, String> parameterMappings) {
        this.sql = sql;
        this.parameterType = parameterType;
        this.resultType = resultType;
        this.parameterMappings = parameterMappings;
    }

}
