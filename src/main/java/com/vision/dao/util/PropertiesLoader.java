package com.vision.dao.util;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenchen on 15/12/20.
 */
public class PropertiesLoader {
    private static Logger logger = Logger.getLogger(PropertiesLoader.class);
    private static final String defaultFileName = "config.properties";
    private static Map<String, Properties> propertiesMap = new ConcurrentHashMap<>();

    public static String getPropertyValue(String fileName, String key) {
        Properties properties = propertiesMap.get(fileName);
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(PropertiesLoader.class.getResourceAsStream(fileName));
                propertiesMap.put(fileName, properties);
            } catch (Exception e) {
                logger.warn("load property failed,fileName=" + fileName + ",key=" + key, e);
            }
        }
        return properties.getProperty(key);
    }

    public static String getPropertyValue(String key) {
        return getPropertyValue(defaultFileName, key);
    }

}
