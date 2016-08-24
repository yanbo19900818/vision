package com.vision.config.database;

import lombok.Data;

import java.util.Map;

/**
 * Created by chenchen on 15/9/13.
 */
@Data
public class DataBaseConfig {

    private String dataBaseName;
    private Map<String, DataBaseTableConfig> dataBaseTableConfigMap;

}
