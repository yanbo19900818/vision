package com.vision.config.entity;

import com.vision.anotation.EntityField;

public class EntityFieldConfig {
    private String databaseColumnName;
    private String databaseColumnType;
    private String entityColumnName;
    private String entityColumnType;

    public String getDatabaseColumnName() {
        return databaseColumnName;
    }

    public void setDatabaseColumnName(String databaseColumnName) {
        this.databaseColumnName = databaseColumnName;
    }

    public String getDatabaseColumnType() {
        return databaseColumnType;
    }

    public void setDatabaseColumnType(String databaseColumnType) {
        this.databaseColumnType = databaseColumnType;
    }

    public String getEntityColumnName() {
        return entityColumnName;
    }

    public void setEntityColumnName(String entityColumnName) {
        this.entityColumnName = entityColumnName;
    }

    public String getEntityColumnType() {
        return entityColumnType;
    }

    public void setEntityColumnType(String entityColumnType) {
        this.entityColumnType = entityColumnType;
    }
}
