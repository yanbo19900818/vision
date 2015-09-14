package com.vision.dao.config.loader;

import com.vision.dao.config.database.DataBaseColumnConfig;
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


    public void loadDatabase(String database) {

    }

    private String loadPrimaryKey(String tableName, Connection connection) {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet rs = databaseMetaData.getPrimaryKeys(null, null, tableName);
            String primaryKeyColumnName = null;
            while (rs.next()) {
                primaryKeyColumnName = rs.getString("PKCOLUMN_NAME");
            }
            rs.close();
            return primaryKeyColumnName;
        } catch (SQLException e) {
            throw new DaoException("load DB Table" + tableName + "failed", e);
        }
    }

    private DataBaseTableConfig loadTable(String tableName, String databaseName) {
        DataSource dataSource = datasourceManager.getDataSourceByDatabaseName(databaseName);
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            String primaryKeyColumnName = loadPrimaryKey(tableName, connection);
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
