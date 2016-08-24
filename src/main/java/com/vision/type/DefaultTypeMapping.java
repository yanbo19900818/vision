package com.vision.type;

import lombok.Data;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenchen on 16/6/19.
 */
public class DefaultTypeMapping {
    private static final Map<JDBCType, Class<?>> JDBCMap = new HashMap<JDBCType, Class<?>>() {
        {
            put(JDBCType.BIGINT, Long.class);
            put(JDBCType.INTEGER, Integer.class);
            put(JDBCType.BOOLEAN, Boolean.class);
            put(JDBCType.CHAR, String.class);
            put(JDBCType.VARCHAR, String.class);
            put(JDBCType.DOUBLE, Double.class);
            put(JDBCType.TINYINT, Integer.class);
            put(JDBCType.DATE, Date.class);
            put(JDBCType.TIMESTAMP, Timestamp.class);
            put(JDBCType.FLOAT, Float.class);
            put(JDBCType.SMALLINT, Integer.class);
        }
    };
    private static final Map<Class<?>, JDBCType> javaMap = new HashMap<Class<?>, JDBCType>() {
        {
            put(Long.class, JDBCType.BIGINT);
            put(Integer.class, JDBCType.INTEGER);
            put(Boolean.class, JDBCType.BOOLEAN);
            put(String.class, JDBCType.CHAR);
            put(String.class, JDBCType.VARCHAR);
            put(Double.class, JDBCType.DOUBLE);
            put(Integer.class, JDBCType.TINYINT);
            put(Date.class, JDBCType.DATE);
            put(Timestamp.class, JDBCType.TIMESTAMP);
            put(Float.class, JDBCType.FLOAT);
            put(Integer.class, JDBCType.SMALLINT);
        }
    };

    public static JDBCType getJDBCType(Class clazz) {
        return javaMap.get(clazz);
    }

    public static Class getJavaType(JDBCType jdbcType) {
        return JDBCMap.get(jdbcType);
    }

}
