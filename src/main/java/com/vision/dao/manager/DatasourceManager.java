package com.vision.dao.manager;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.vision.dao.util.PropertiesLoader;
import com.vision.exception.DaoException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 数据源
 * Created by chenchen on 15/9/13.
 */
public class DatasourceManager {
    public enum EnumDataSourceType {
        JNDI, JDBC;
    }

    private Map<String, DataSource> dataSourceMap = new HashMap<>();

    public DataSource getDataSourceByDatabaseName(String databaseName) {
        if (dataSourceMap.containsKey(databaseName)) {
            return dataSourceMap.get(databaseName);
        }
        //TODO 加载datasource
        DataSource dataSource = loadDataSource(databaseName, EnumDataSourceType.JDBC);
        if (dataSource == null) throw new DaoException("load database failed,databaseName=" + databaseName);
        dataSourceMap.put(databaseName, dataSource);
        return dataSource;
    }

    /**
     * 加载数据源
     *
     * @param databaseName
     * @param enumDataSourceType
     * @return
     */
    public DataSource loadDataSource(String databaseName, EnumDataSourceType enumDataSourceType) {
        //TODO 根据类型的不同,加载DataSource,JNDI从spring中读取,jdbc从配置文件中读取
        if (enumDataSourceType == enumDataSourceType.JNDI) {
            try {
                Context ic = new InitialContext();
                DataSource source = (DataSource) ic.lookup("java:comp/env/jdbc/" + databaseName);
                return source;
            } catch (Exception e) {
                throw new DaoException("can't load jndi datasource,name=" + databaseName, e);
            }
        }
        //JDBC读取配置文件
        if (enumDataSourceType == EnumDataSourceType.JDBC) {
            //jdbc默认从config.properties中读取配置项,如:db.test.url,db.test.pwd,db.test.user
            String url = PropertiesLoader.getPropertyValue("db." + databaseName + ".url");
            String password = PropertiesLoader.getPropertyValue("db." + databaseName + ".pwd");
            String user = PropertiesLoader.getPropertyValue("db." + databaseName + ".user");
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource
                    .setURL(url);
            mysqlDataSource.setUser(password);
            mysqlDataSource.setPassword(user);
            return
                    mysqlDataSource;
        }
        throw new DaoException("unknow datasourceType");
    }
}
