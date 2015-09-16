package com.vision.dao.manager;

import com.vision.dao.config.database.DataBaseConfig;
import com.vision.dao.config.entity.EntityConfig;
import com.vision.dao.config.entity.EntityFieldConfig;
import com.vision.dao.config.loader.EntityAnnotationConfigLoader;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class EntityManager {
    private static EntityManager instance = new EntityManager();

    private EntityManager() {
    }

    public static EntityManager getInstance() {
        return instance;
    }

    DataBaseConfigManager dataBaseConfigManager = DataBaseConfigManager.getInstance();
    EntityAnnotationConfigLoader entityAnnotationConfigLoader = new EntityAnnotationConfigLoader();

    private Map<String, EntityConfig> entityConfigMap = new HashMap<>();

    public EntityConfig getEntityConfig(String entityClassName) {
        if (entityConfigMap.containsKey(entityClassName)) {
            return entityConfigMap.get(entityClassName);
        }
        return null;
    }

    private EntityConfig buildEntityCOnfig(String entityClassName) {
        EntityConfig entityConfig = entityAnnotationConfigLoader.loadEntity(entityClassName);
        DataBaseConfig dataBaseConfig = dataBaseConfigManager.getDataBaseTableConfig(entityConfig.getDatabaseName());
        for (Map.Entry<String, EntityFieldConfig> entry : entityConfig.getEntityFieldConfigMap().entrySet()) {
        }
    }

}
