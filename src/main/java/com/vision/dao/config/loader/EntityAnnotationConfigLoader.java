package com.vision.dao.config.loader;

import com.vision.dao.anotation.Entity;
import com.vision.dao.anotation.EntityField;
import com.vision.dao.config.entity.EntityConfig;
import com.vision.dao.config.entity.EntityFieldConfig;
import com.vision.exception.DaoException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanbo on 15/9/13.
 */
public class EntityAnnotationConfigLoader {

    public EntityConfig loadEntity(String ClassName) {
        Class<?> c = null;
        try {
            c = Class.forName(ClassName);
        } catch (ClassNotFoundException e) {
            throw new DaoException("load entity " + ClassName + "faile, class not found", e);
        }
        EntityConfig entityConfig = new EntityConfig();
        Entity entity = c.getAnnotation(Entity.class);
        //checkAnnotation
        if (StringUtils.isBlank(entity.DatabaseName()))
            throw new DaoException("load entity " + ClassName + "faile, db Name is Null");
        if (StringUtils.isBlank(entity.tableName()))
            throw new DaoException("load entity " + ClassName + "faile, table Name is Null");
        if (entity.isSharding()) {
            if (StringUtils.isBlank(entity.shardingScheme())) {
                throw new DaoException("load entity " + ClassName + "faile, shardingScheme is Null");
            }
        }
        entityConfig.setDatabaseName(entity.DatabaseName());
        entityConfig.setTableName(entity.tableName());
        if (entity.isSharding()) {
            entityConfig.setSharding(true);
            entityConfig.setShardingScheme(entity.shardingScheme());
        }
        Field[] fields = c.getDeclaredFields();
        if (fields == null || fields.length <= 0)
            throw new DaoException("load entity " + ClassName + "faile, no field");
        Map<String, EntityFieldConfig> entityFieldConfigMap = new HashMap<>();
        for (Field field : fields) {
            EntityField entityField = field.getAnnotation(EntityField.class);
            EntityFieldConfig entityFieldConfig = new EntityFieldConfig();
            if (entityField == null) {
                entityFieldConfig.setEntityColumnName(field.getName());
                entityFieldConfig.setDatabaseColumnType(field.getType().getName());
            } else {
                entityFieldConfig.setDatabaseColumnName(entityField.databaseColumnName());
                entityFieldConfig.setDatabaseColumnType(entityField.databaseColumnType());
            }
            entityFieldConfigMap.put(field.getName(), entityFieldConfig);
        }
        entityConfig.setEntityFieldConfigMap(entityFieldConfigMap);
        return entityConfig;
    }

}
