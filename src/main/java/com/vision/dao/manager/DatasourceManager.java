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
    private static DatasourceManager instance = new DatasourceManager();

    private DatasourceManager() {
    }

    public static DatasourceManager getInstance() {
        return instance;
    }

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

    public DataSource loadDataSource(String databaseName, EnumDataSourceType enumDataSourceType) {
        //TODO 根据类型的不同,加载DataSource
        return null;
    }
}
