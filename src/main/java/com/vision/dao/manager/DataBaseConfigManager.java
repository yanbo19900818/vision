package com.vision.dao.manager;

import com.vision.dao.config.database.DataBaseConfig;
import com.vision.dao.config.database.DataBaseTableConfig;
import com.vision.dao.config.loader.DataBaseConfigLoader;

import java.util.HashMap;
import java.util.Map;

public class DataBaseConfigManager {
    private static DataBaseConfigManager instance = new DataBaseConfigManager();

    private DataBaseConfigManager() {
    }

    public static DataBaseConfigManager getInstance() {
        return instance;
    }

    DataBaseConfigLoader dataBaseConfigLoader = new DataBaseConfigLoader();
    private Map<String, DataBaseConfig> dataBaseConfigMap = new HashMap<>();

    public DataBaseConfig getDataBaseTableConfig(String databaseName) {
        if (dataBaseConfigMap.containsKey(databaseName)) {
            return dataBaseConfigMap.get(databaseName);
        }
        DataBaseConfig dataBaseConfig = dataBaseConfigLoader.loadDatabase(databaseName);
        dataBaseConfigMap.put(databaseName, dataBaseConfig);
        return dataBaseConfig;
    }
}
