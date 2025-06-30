package cn.mario.mybatis.transaction;


import org.apache.ibatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author mario
 * @description: 事务工厂接口
 */
public interface TransactionFactory {

    /**
     * 根据 Connection 创建 Transaction
     * @param connection Existing database connection
     * @return Transaction
     */
    Transaction newTransaction(Connection connection);


    /**
     * 根据数据源和事务隔离级别创建 Transaction
     * @param dataSource DataSource to take the connection from
     * @param level Desired isolation level
     * @param autoCommit Desired autocommit
     * @return Transaction
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);

}
