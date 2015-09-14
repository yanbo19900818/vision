package com.vision.dao.config.database;

import java.util.Map;

public class DataBaseTableConfig {
    private String tableName;
    private Map<String, DataBaseColumnConfig> dataBaseColumnConfigMap;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, DataBaseColumnConfig> getDataBaseColumnConfigMap() {
        return dataBaseColumnConfigMap;
    }

    public void setDataBaseColumnConfigMap(Map<String, DataBaseColumnConfig> dataBaseColumnConfigMap) {
        this.dataBaseColumnConfigMap = dataBaseColumnConfigMap;
    }
}
