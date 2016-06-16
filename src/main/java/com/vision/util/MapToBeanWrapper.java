package com.vision.util;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import sun.util.resources.cldr.wae.LocaleNames_wae;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenchen on 16/6/17.
 */
public class MapToBeanWrapper {
    public static ConcurrentHashMap<String, FastClass> fastClassMap = new ConcurrentHashMap<>();

    public static <T> T wrap(Map<String, Object> map, Class<T> clazz) {
        FastClass fc = fastClassMap.get(clazz.getName());
        if (fc == null) {
            fc = FastClass.create(clazz);
            fastClassMap.put(clazz.getName(), fc);
        }
        try {
            T bean = (T) fc.newInstance();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                try {
                    FastMethod method = fc.getMethod(createMethodName(entry.getKey(), entry.getValue()), new Class[]{entry.getValue().getClass()});
                    method.invoke(bean, new Object[]{entry.getValue()});
                } catch (NoSuchMethodError e) {
                    //忽略没有的set方法
                    continue;
                }
            }
            return bean;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(clazz.getName() + "wrap failed", e);
        }
    }

    private static String createMethodName(String key, Object value) {
        return "set" + key.substring(0, 1).toUpperCase() + key.substring(1, key.length());
    }
}
