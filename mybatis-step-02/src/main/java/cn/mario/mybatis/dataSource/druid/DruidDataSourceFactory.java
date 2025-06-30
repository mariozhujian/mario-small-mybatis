package cn.mario.mybatis.dataSource.druid;


import cn.mario.mybatis.dataSource.DataSourceFactory;
import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @description: Druid 数据源工厂
 * @author: mario
 * @date: 2025/6/30
 */
public class DruidDataSourceFactory implements DataSourceFactory {
    private Properties properties;

    @Override
    public void setProps(Properties props) {
        this.properties = props;
    }

    @Override
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getProperty("driver"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));

        return dataSource;
    }
}
