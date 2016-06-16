package com.vision.config.entity;

import java.util.Map;

public class EntityConfig {
    private String databaseName;
    private String tableName;
    private boolean isSharding;
    private String shardingScheme;
    /**
     * 主键
     */
    private PrimaryKeyFiledConfig primaryKeyFiledConfig;
    /**
     * 字段
     */
    Map<String, EntityFieldConfig> entityFieldConfigMap;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isSharding() {
        return isSharding;
    }

    public void setSharding(boolean isSharding) {
        this.isSharding = isSharding;
    }

    public String getShardingScheme() {
        return shardingScheme;
    }

    public void setShardingScheme(String shardingScheme) {
        this.shardingScheme = shardingScheme;
    }

    public PrimaryKeyFiledConfig getPrimaryKeyFiledConfig() {
        return primaryKeyFiledConfig;
    }

    public void setPrimaryKeyFiledConfig(PrimaryKeyFiledConfig primaryKeyFiledConfig) {
        this.primaryKeyFiledConfig = primaryKeyFiledConfig;
    }

    public Map<String, EntityFieldConfig> getEntityFieldConfigMap() {
        return entityFieldConfigMap;
    }

    public void setEntityFieldConfigMap(Map<String, EntityFieldConfig> entityFieldConfigMap) {
        this.entityFieldConfigMap = entityFieldConfigMap;
    }
}
