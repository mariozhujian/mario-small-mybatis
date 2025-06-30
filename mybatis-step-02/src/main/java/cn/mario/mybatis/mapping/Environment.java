package cn.mario.mybatis.mapping;


import cn.mario.mybatis.transaction.TransactionFactory;
import lombok.Data;

import javax.sql.DataSource;

/**
 * @description: 环境
 * @author: mario
 * @date: 2025/6/30
 */
@Data
public class Environment {

    private final String id;
    private final TransactionFactory transactionFactory;
    private final DataSource dataSource;

    public Environment(String id, TransactionFactory transactionFactory, DataSource dataSource) {
        this.id = id;
        this.transactionFactory = transactionFactory;
        this.dataSource = dataSource;
    }

    public static class Builder {
        private String id;
        private TransactionFactory transactionFactory;
        private DataSource dataSource;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder transactionFactory(TransactionFactory transactionFactory) {
            this.transactionFactory = transactionFactory;
            return this;
        }

        public Builder dataSource(DataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public Environment build() {
            return new Environment(id, transactionFactory, dataSource);
        }
    }
}
