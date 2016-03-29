package com.vision.dao.manager;

import com.vision.dao.config.database.DataBaseColumnConfig;
import com.vision.dao.config.database.DataBaseConfig;
import com.vision.dao.config.database.DataBaseTableConfig;
import com.vision.dao.config.entity.EntityConfig;
import com.vision.dao.config.entity.EntityFieldConfig;
import com.vision.dao.config.loader.EntityAnnotationConfigLoader;
import com.vision.dao.util.ColumnNameConvertor;
import com.vision.exception.DaoException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class EntityConfigManager {
    DataBaseConfigManager dataBaseConfigManager;

    EntityAnnotationConfigLoader entityAnnotationConfigLoader = new EntityAnnotationConfigLoader();

    private Map<String, EntityConfig> entityConfigMap = new HashMap<>();

    public EntityConfig getEntityConfig(String entityClassName) {
        EntityConfig entityConfig = entityConfigMap.get(entityClassName);
        if (entityConfig != null) {
            return entityConfig;
        }
        entityConfigMap.put(entityClassName, buildEntityCOnfig(entityClassName));
        return entityConfig;
    }

    private EntityConfig buildEntityCOnfig(String entityClassName) {
        EntityConfig entityConfig = entityAnnotationConfigLoader.loadEntity(entityClassName);
        DataBaseConfig dataBaseConfig = dataBaseConfigManager.getDataBaseTableConfig(entityConfig.getDatabaseName());
        Map<String, EntityFieldConfig> entityFieldConfigMap = entityConfig.getEntityFieldConfigMap();
        DataBaseTableConfig dataBaseTableConfig = dataBaseConfig.getDataBaseTableConfigMap().get(entityConfig.getTableName());
        for (Map.Entry<String, EntityFieldConfig> entry : entityFieldConfigMap.entrySet()) {
            EntityFieldConfig entityFieldConfig = entry.getValue();
            String databaseColumnName = ColumnNameConvertor.convertToDatabaseColumnName(entityFieldConfig.getEntityColumnName());
            if (!StringUtils.isBlank(entityFieldConfig.getDatabaseColumnName())) {
                databaseColumnName = entityFieldConfig.getDatabaseColumnName();
            }
            DataBaseColumnConfig dataBaseColumnConfig =
                    dataBaseTableConfig.getDataBaseColumnConfigMap().get(databaseColumnName);
            if (dataBaseColumnConfig == null) {
                throw new DaoException("load entityField failed,no this field,databaseColumName=" + databaseColumnName);
            }
            if (StringUtils.isBlank(entityFieldConfig.getDatabaseColumnType())) {
                entityFieldConfig.setDatabaseColumnType(dataBaseColumnConfig.getDataType());
            }
        }
        return entityConfig;
    }

}
