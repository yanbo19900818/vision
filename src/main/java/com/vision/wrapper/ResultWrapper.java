package com.vision.wrapper;


import com.vision.config.entity.EntityConfig;
import com.vision.config.entity.EntityFieldConfig;
import com.vision.exception.DaoException;
import net.sf.cglib.beans.BeanMap;
import net.sf.cglib.reflect.FastClass;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenchen on 16/5/24.
 */
public class ResultWrapper {
    public <T> Optional<T> wrap(ResultSet resultSet, EntityConfig entityConfig) {



    }
}
