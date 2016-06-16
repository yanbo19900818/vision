package com.vision.manager;

import com.vision.config.database.DataBaseConfig;
import com.vision.config.loader.DataBaseConfigLoader;

import java.util.HashMap;
import java.util.Map;

public class DataBaseConfigManager {

    DataBaseConfigLoader dataBaseConfigLoader = new DataBaseConfigLoader();
    private Map<String, DataBaseConfig> dataBaseConfigMap = new HashMap<>();

    public DataBaseConfig getDataBaseTableConfig(String databaseName) {
        DataBaseConfig dataBaseConfig = dataBaseConfigMap.get(databaseName);
        if (dataBaseConfig != null) {
            return dataBaseConfigMap.get(databaseName);
        }
        dataBaseConfig = dataBaseConfigLoader.loadDatabase(databaseName);
        dataBaseConfigMap.put(databaseName, dataBaseConfig);
        return dataBaseConfig;
    }
}
