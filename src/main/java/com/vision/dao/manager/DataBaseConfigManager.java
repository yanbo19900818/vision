package com.vision.dao.manager;

import com.vision.dao.config.database.DataBaseTableConfig;
import com.vision.dao.config.loader.DataBaseConfigLoader;

import java.util.HashMap;
import java.util.Map;

public class DataBaseConfigManager {
    DataBaseConfigLoader dataBaseConfigLoader = new DataBaseConfigLoader();
    private Map<String, DataBaseTableConfig> dataBaseTableConfigMap = new HashMap<>();

    public DataBaseTableConfig getDBTableConfig(String tableName, String databaseName) {
        if (dataBaseTableConfigMap.containsKey(databaseName)) {
            return dataBaseTableConfigMap.get(databaseName);
        }
        
    }
}
