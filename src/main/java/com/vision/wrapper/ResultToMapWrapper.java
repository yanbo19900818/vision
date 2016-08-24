package com.vision.wrapper;

import com.vision.config.entity.EntityConfig;
import com.vision.config.entity.EntityFieldConfig;
import com.vision.exception.DaoException;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * Created by chenchen on 16/6/18.
 */
public class ResultToMapWrapper {
    public Map<String, Object> wrap(ResultSet resultSet, EntityConfig entityConfig) {
        try {
            if (!resultSet.next()) {
                return Collections.emptyMap();
            }
            for (EntityFieldConfig entityFieldConfig : entityConfig.getEntityFieldConfigMap().values()) {

            }
        } catch (SQLException e) {
            throw new DaoException("wrap ResultSet failed", e);
        }
    }

    private Object getProperty(ResultSet resultSet, JDBCType jdbcType) {

    }
}
