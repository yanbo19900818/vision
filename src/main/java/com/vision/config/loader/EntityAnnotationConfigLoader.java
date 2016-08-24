package com.vision.config.loader;

import com.vision.anotation.Entity;
import com.vision.anotation.EntityField;
import com.vision.config.entity.EntityConfig;
import com.vision.config.entity.EntityFieldConfig;
import com.vision.exception.DaoException;
import com.vision.type.DefaultTypeMapping;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yanbo on 15/9/13.
 */
public class EntityAnnotationConfigLoader {

    public EntityConfig loadEntity(String entityClassName) {
        try {
            Class<?> clazz = Class.forName(entityClassName);
            EntityConfig entityConfig = new EntityConfig();
            Entity entity = clazz.getAnnotation(Entity.class);
            //checkAnnotation
            if (StringUtils.isBlank(entity.databaseName()))
                throw new DaoException("load entity " + entityClassName + "faile, db Name is Null");
            if (StringUtils.isBlank(entity.tableName()))
                throw new DaoException("load entity " + entityClassName + "faile, table Name is Null");
            if (entity.isSharding()) {
                if (StringUtils.isBlank(entity.shardingScheme())) {
                    throw new DaoException("load entity " + entityClassName + "faile, shardingScheme is Null");
                }
            }
            FastClass fastClass = FastClass.create(clazz);
            entityConfig.setEntityClass(fastClass);
            entityConfig.setDatabaseName(entity.databaseName());
            entityConfig.setTableName(entity.tableName());
            if (entity.isSharding()) {
                entityConfig.setSharding(true);
                entityConfig.setShardingScheme(entity.shardingScheme());
            }

            List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
            if (CollectionUtils.isEmpty(fields))
                throw new DaoException("load entity " + entityClassName + "faile, no field");
            Map<String, EntityFieldConfig> entityFieldConfigMap = new HashMap<>();
            for (Field field : fields) {
                //生成fieldconfig
                EntityField entityField = field.getAnnotation(EntityField.class);
                EntityFieldConfig entityFieldConfig = new EntityFieldConfig();
                if (entityField == null) {
                    entityFieldConfig.setField(field);
                    entityFieldConfig.setDatabaseColumnType(DefaultTypeMapping.getJDBCType(field.getType()));
                } else {
                    entityFieldConfig.setDatabaseColumnName(entityField.databaseColumnName());
                    entityFieldConfig.setDatabaseColumnType(entityField.databaseColumnType());
                    if (entityField.databaseColumnType() != null) {
                        entityFieldConfig.setDatabaseColumnType(entityField.databaseColumnType());
                    }
                }
                entityFieldConfig.setGetMethod(fastClass.getMethod(createGetMethodName(field.getName()), new Class[]{}));
                entityFieldConfig.setSetMethod(fastClass.getMethod(createSetMethodName(field.getName()), new Class[]{field.getType()}));
                entityFieldConfigMap.put(field.getName(), entityFieldConfig);
            }
            entityConfig.setEntityFieldConfigMap(entityFieldConfigMap);
            return entityConfig;
        } catch (ClassNotFoundException e) {
            throw new DaoException("load entity " + entityClassName + "faile, class not found", e);
        }
    }

    private String createGetMethodName(String key) {
        return "get" + key.substring(0, 1).toUpperCase() + key.substring(1, key.length());
    }

    private String createSetMethodName(String key) {
        return "set" + key.substring(0, 1).toUpperCase() + key.substring(1, key.length());
    }
}
