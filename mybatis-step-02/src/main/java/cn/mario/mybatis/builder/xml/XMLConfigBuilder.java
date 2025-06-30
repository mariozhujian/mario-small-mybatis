package cn.mario.mybatis.builder.xml;


import cn.mario.mybatis.builder.BaseBuilder;
import cn.mario.mybatis.dataSource.DataSourceFactory;
import cn.mario.mybatis.mapping.BoundSql;
import cn.mario.mybatis.mapping.Environment;
import cn.mario.mybatis.mapping.MappedStatement;
import cn.mario.mybatis.mapping.SqlCommandType;
import cn.mario.mybatis.session.Configuration;
import cn.mario.mybatis.transaction.TransactionFactory;
import org.apache.ibatis.io.Resources;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.sql.DataSource;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: TODO
 * @author: mario
 * @date: 2025/6/26
 */
public class XMLConfigBuilder extends BaseBuilder {

    private Element root;

    public XMLConfigBuilder(Reader reader) {
        // 1.调用父类初始化 Configuration
        super(new Configuration());
        // 2. dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            throw new RuntimeException("解析xml文件异常", e);
        }
    }

    /**
     * 解析配置：；类型别名、插件、对象工厂、对象包装工厂、设置、环境、类型转换、映射器
     * @return
     */
    public Configuration parse() {
        try {
            // 解析映射器
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }

    private void environmentsElement(Element context) throws InstantiationException, IllegalAccessException {
        String defaultValue = context.attributeValue("default");
        // 解析环境
        List<Element> environmentList = context.elements("environment");
        for (Element environment : environmentList) {
            String id = environment.attributeValue("id");
            if (!defaultValue.equals(id)) {
                continue;
            }

            // 获取事务管理器
            Element transactionManager = environment.element("transactionManager");
            String transactionManagerType = transactionManager.attributeValue("type");
            TransactionFactory transactionFactory = (TransactionFactory) this.typeAliasRegistry.resolveAlias(transactionManagerType).newInstance();

            // 获取数据源
            Element dataSourceElement = environment.element("dataSource");
            String dataSourceType = dataSourceElement.attributeValue("type");
            DataSourceFactory dataSourceFactory = (DataSourceFactory) this.typeAliasRegistry.resolveAlias(dataSourceType).newInstance();
            List<Element> propertyList = dataSourceElement.elements("property");
            // 设置数据源属性
            Properties properties = new Properties();
            for (Element property : propertyList) {
                String name = property.attributeValue("name");
                String value = property.attributeValue("value");
                properties.setProperty(name, value);
            }
            dataSourceFactory.setProps(properties);
            DataSource dataSource = dataSourceFactory.getDataSource();

            // 设置环境
            Environment.Builder environmentBuilder = new Environment.Builder().id(id).transactionFactory(transactionFactory).dataSource(dataSource);
            configuration.setEnvironment(environmentBuilder.build());
        }
    }


    /**
     * 解析mapper标签，注册mapper文件
     * @param mappers mappers文件
     * @throws Exception 异常
     */
    private void mapperElement(Element mappers) throws Exception {
        List<Element> mapperList = mappers.elements("mappers");
        for (Element e : mapperList) {
            String resource = e.attributeValue("resource");
            Reader reader = Resources.getResourceAsReader(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));
            Element rootElement = document.getRootElement();
            // 命名空间
            String namespace = root.attributeValue("namespace");

            // SELECT
            List<Element> selectNodes = root.elements("select");
            for (Element node : selectNodes) {
                String id = node.attributeValue("id");
                String parameterType = node.attributeValue("parameterType");
                String resultType = node.attributeValue("resultType");
                String sql = node.getText();

                // ？匹配
                Map<Integer, String> parameterMap = new HashMap<>();
                Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                for (int i = 1; matcher.find() ; i++) {
                    String g1 = matcher.group(1);
                    String g2 = matcher.group(2);
                    parameterMap.put(i, g2);
                    sql = sql.replace(g1, "?");
                }

                String msId = namespace + "." + id;
                String nodeName = node.getName();
                SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase());
                BoundSql boundSql = new BoundSql(sql, parameterType, resultType, parameterMap);
                MappedStatement mappedStatement = new MappedStatement.Builder(configuration, msId, sqlCommandType, boundSql).build();
                // 添加解析sql
                configuration.addMappedStatement(mappedStatement);
            }
            // 注册Mapper映射器
            configuration.addMapper(Resources.classForName(namespace));
        }
    }
}
