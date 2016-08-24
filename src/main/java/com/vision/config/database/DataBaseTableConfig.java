package com.vision.config.database;

import lombok.Data;

import java.util.Map;

@Data
public class DataBaseTableConfig {
    private String tableName;
    private Map<String, DataBaseColumnConfig> dataBaseColumnConfigMap;
}
