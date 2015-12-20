package com.vision.dao.manager;

import com.vision.exception.DaoException;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

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
        return null;
    }
}
