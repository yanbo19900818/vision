package com.vision.config.database;

import java.util.Map;

/**
 * Created by chenchen on 15/9/13.
 */
public class DataBaseConfig {

    private String dataBaseName;
    private Map<String, DataBaseTableConfig> dataBaseTableConfigMap;

    public Map<String, DataBaseTableConfig> getDataBaseTableConfigMap() {
        return dataBaseTableConfigMap;
    }

    public void setDataBaseTableConfigMap(Map<String, DataBaseTableConfig> dataBaseTableConfigMap) {
        this.dataBaseTableConfigMap = dataBaseTableConfigMap;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }
}
