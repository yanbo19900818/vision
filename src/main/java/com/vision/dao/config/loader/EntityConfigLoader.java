package com.vision.dao.config.loader;

import com.vision.dao.anotation.Entity;
import com.vision.dao.anotation.EntityField;
import com.vision.dao.config.entity.EntityConfig;
import com.vision.dao.config.entity.EntityFieldConfig;
import com.vision.exception.DaoException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanbo on 15/9/13.
 */
public class EntityConfigLoader {

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
        if (StringUtils.isBlank(entity.DBName()))
            throw new DaoException("load entity " + ClassName + "faile, db Name is Null");
        if (StringUtils.isBlank(entity.tableName()))
            throw new DaoException("load entity " + ClassName + "faile, table Name is Null");
        if (entity.isSharding()) {
            if (StringUtils.isBlank(entity.shardingScheme())) {
                throw new DaoException("load entity " + ClassName + "faile, shardingScheme is Null");
            }
        }
        entityConfig.setDBName(entity.DBName());
        entityConfig.setTableName(entity.tableName());
        if (entity.isSharding()) {
            entityConfig.setSharding(true);
            entityConfig.setShardingScheme(entity.shardingScheme());
        }
        Field[] fields = c.getDeclaredFields();
        if (fields == null || fields.length <= 0)
            throw new DaoException("load entity " + ClassName + "faile, no field");
        List<EntityFieldConfig> entityFieldConfigs = new ArrayList<>(fields.length);
        for (Field field : fields) {
            EntityField entityField = field.getAnnotation(EntityField.class);
            if (entityField == null) {

            }
        }
        entityConfig.setEntityFieldConfigs(entityFieldConfigs);
        return entityConfig;
    }


    private EntityFieldConfig loadEntityField(EntityField entityField,) {

    }
}
