package com.vision.wrapper;

import com.vision.config.entity.EntityConfig;
import com.vision.config.entity.EntityFieldConfig;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenchen on 16/6/17.
 */
public class MapToBeanWrapper {

    public static <T> T wrap(Map<String, Object> map, EntityConfig entityConfig) {
        try {
            T bean = (T) entityConfig.getEntityClass().newInstance();

            for (EntityFieldConfig entityFieldConfig : entityConfig.getEntityFieldConfigMap().values()) {
                Object value = map.get(entityFieldConfig.getField().getName());
                if (value == null) continue;
                try {
                    entityFieldConfig.getSetMethod().invoke(bean, new Object[]{value});
                } catch (InvocationTargetException e) {
                    //忽略没有的set方法
                    continue;
                }
            }
            return bean;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


}
