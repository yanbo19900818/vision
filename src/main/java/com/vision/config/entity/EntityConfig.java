package com.vision.config.entity;

import lombok.Data;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import java.lang.reflect.Field;
import java.util.Map;

@Data
public class EntityConfig {
    private String databaseName;
    private String tableName;
    private boolean isSharding;
    private String shardingScheme;
    private FastClass entityClass;
    /**
     * 主键
     */
    private PrimaryKeyFiledConfig primaryKeyFiledConfig;
    /**
     * 字段
     */
    Map<String, EntityFieldConfig> entityFieldConfigMap;

}
