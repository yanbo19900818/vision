package com.vision.dao.config.loader;

import com.vision.dao.config.database.DataBaseColumnConfig;
import com.vision.dao.config.database.DataBaseConfig;
import com.vision.dao.config.database.DataBaseTableConfig;
import com.vision.dao.manager.DatasourceManager;
import com.vision.exception.DaoException;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenchen on 15/9/13.
 */
public class DataBaseConfigLoader {

    private DatasourceManager datasourceManager = DatasourceManager.getInstance();

    public DataBaseConfig loadDatabase(String databaseName) {
        DataSource dataSource = datasourceManager.getDataSourceByDatabaseName(databaseName);
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
            DataBaseConfig dataBaseConfig = new DataBaseConfig();
            dataBaseConfig.setDataBaseName(databaseName);
            Map<String, DataBaseTableConfig> dataBaseTableConfigMap = new HashMap<>();
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                if (StringUtils.isBlank(tableName)) {
                    continue;
                }
                DataBaseTableConfig dataBaseTableConfig = loadTable(tableName, databaseMetaData);
                if (dataBaseTableConfig == null) {
                    throw new DaoException("load table failed,databaseName=" + databaseName + ",tableName=" + tableName);
                }
                dataBaseTableConfigMap.put(tableName, dataBaseTableConfig);
            }
            dataBaseConfig.setDataBaseTableConfigMap(dataBaseTableConfigMap);
            return dataBaseConfig;
        } catch (SQLException e) {
            throw new DaoException("load DB failed,databaseName=" + databaseName + "failed", e);
        }

    }

    private String loadPrimaryKey(String tableName, DatabaseMetaData databaseMetaData) {
        try {

            ResultSet rs = databaseMetaData.getPrimaryKeys(null, null, tableName);
            String primaryKeyColumnName = null;
            while (rs.next()) {
                primaryKeyColumnName = rs.getString("PKCOLUMN_NAME");
            }
            rs.close();
            return primaryKeyColumnName;
        } catch (SQLException e) {
            throw new DaoException("load DB Table" + tableName + "primary key failed", e);
        }
    }

    private DataBaseTableConfig loadTable(String tableName, DatabaseMetaData databaseMetaData) {
        try {
            String primaryKeyColumnName = loadPrimaryKey(tableName, databaseMetaData);
            if (StringUtils.isBlank(primaryKeyColumnName)) {
                throw new DaoException("load DB Table" + tableName + "failed,no primary key");
            }
            Map<String, DataBaseColumnConfig> dataBaseColumnConfigMap = new HashMap<>();
            ResultSet rs = databaseMetaData.getColumns(null, null, tableName, null);
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");// 列名
                String dataType = rs.getString("DATA_TYPE");// 字段数据类型(对应java.sql.Types中的常量)
                String typeName = rs.getString("TYPE_NAME");// 字段类型名称(例如：VACHAR2)
                DataBaseColumnConfig dataBaseColumnConfig = new DataBaseColumnConfig();
                dataBaseColumnConfig.setColumnName(columnName);
                dataBaseColumnConfig.setDataType(dataType);
                dataBaseColumnConfig.setTypeName(typeName);
                if (columnName.equals(primaryKeyColumnName)) {
                    dataBaseColumnConfig.setPrimaryKey(true);
                } else
                    dataBaseColumnConfig.setPrimaryKey(false);
                dataBaseColumnConfigMap.put(columnName, dataBaseColumnConfig);
            }
            DataBaseTableConfig dataBaseTableConfig = new DataBaseTableConfig();
            dataBaseTableConfig.setDataBaseColumnConfigMap(dataBaseColumnConfigMap);
            dataBaseTableConfig.setTableName(tableName);
            return dataBaseTableConfig;
        } catch (SQLException e) {
            throw new DaoException("load DB Table" + tableName + "failed", e);
        }
    }
}
