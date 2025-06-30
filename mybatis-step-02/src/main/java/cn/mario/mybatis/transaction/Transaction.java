package cn.mario.mybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author mario
 * @description: 事务接口
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;

}
