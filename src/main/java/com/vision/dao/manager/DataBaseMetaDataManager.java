package com.vision.dao.manager;

import com.vision.dao.config.database.DataBaseConfig;
import com.vision.dao.config.database.DataBaseTableConfig;
import com.vision.dao.config.loader.DataBaseConfigLoader;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataBaseMetaDataManager {
    DataBaseConfigLoader dataBaseConfigLoader = new DataBaseConfigLoader();
    private Map<String, DataBaseTableConfig> dataBaseTableConfigMap = new HashMap<>();

    public DataBaseTableConfig getDBTableConfig(String tableName, String databaseName) {
        if (dataBaseTableConfigMap.containsKey(databaseName)) {
            return dataBaseTableConfigMap.get(databaseName);
        }



    }
}
