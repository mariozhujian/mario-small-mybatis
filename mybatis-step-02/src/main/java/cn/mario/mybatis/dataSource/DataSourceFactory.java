package cn.mario.mybatis.dataSource;


import javax.sql.DataSource;
import java.util.Properties;

/**
 * @description: 数据源工厂
 * @author: mario
 * @date: 2025/6/30
 */
public interface DataSourceFactory {

    void setProps(Properties props);

    DataSource getDataSource();

}
