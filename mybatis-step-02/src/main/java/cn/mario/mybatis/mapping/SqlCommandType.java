package cn.mario.mybatis.mapping;

/**
 * @description: SQL命令类型枚举
 */
public enum SqlCommandType {

    /**
     * 未知
     */
    UNKNOWN,
    /**
     * 插入
     */
    INSERT,
    /**
     * 更新
     */
    UPDATE,
    /**
     * 删除
     */
    DELETE,
    /**
     * 查找
     */
    SELECT;

}
