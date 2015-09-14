package com.vision.dao.manager;

import com.vision.dao.config.entity.EntityConfig;

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


    private Map<String, EntityConfig> entityConfigMap;


}
