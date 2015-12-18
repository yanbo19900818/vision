package com.vision.dao.config.loader;

import com.vision.dao.anotation.Entity;
import com.vision.dao.anotation.EntityField;
import com.vision.dao.config.entity.EntityConfig;
import com.vision.dao.config.entity.EntityFieldConfig;
import com.vision.exception.DaoException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yanbo on 15/9/13.
 */
public class EntityAnnotationConfigLoader {

    public EntityConfig loadEntity(String ClassName) {
        try {
            Class<?> c = Class.forName(ClassName);
            EntityConfig entityConfig = new EntityConfig();
            Entity entity = c.getAnnotation(Entity.class);
            //checkAnnotation
            if (StringUtils.isBlank(entity.databaseName()))
                throw new DaoException("load entity " + ClassName + "faile, db Name is Null");
            if (StringUtils.isBlank(entity.tableName()))
                throw new DaoException("load entity " + ClassName + "faile, table Name is Null");
            if (entity.isSharding()) {
                if (StringUtils.isBlank(entity.shardingScheme())) {
                    throw new DaoException("load entity " + ClassName + "faile, shardingScheme is Null");
                }
            }
            entityConfig.setDatabaseName(entity.databaseName());
            entityConfig.setTableName(entity.tableName());
            if (entity.isSharding()) {
                entityConfig.setSharding(true);
                entityConfig.setShardingScheme(entity.shardingScheme());
            }
            List<Field> fields = Arrays.asList(c.getDeclaredFields());
            if (fields == null)
                throw new DaoException("load entity " + ClassName + "faile, no field");
            Map<String, EntityFieldConfig> entityFieldConfigMap = new HashMap<>();
            fields.stream().collect(Collectors.toMap(Field::getName, e -> {
                EntityField entityField = e.getAnnotation(EntityField.class);
                EntityFieldConfig entityFieldConfig = new EntityFieldConfig();
                if (entityField == null) {
                    entityFieldConfig.setEntityColumnName(e.getName());
                    entityFieldConfig.setDatabaseColumnType(e.getType().getName());
                } else {
                    entityFieldConfig.setDatabaseColumnName(entityField.databaseColumnName());
                    entityFieldConfig.setDatabaseColumnType(entityField.databaseColumnType());
                }
                return entityConfig;
            }));
            entityConfig.setEntityFieldConfigMap(entityFieldConfigMap);
            return entityConfig;
        } catch (ClassNotFoundException e) {
            throw new DaoException("load entity " + ClassName + "faile, class not found", e);
        }
    }
}
