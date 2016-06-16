package com.vision.wrapper;


import net.sf.cglib.beans.BeanMap;
import net.sf.cglib.reflect.FastClass;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenchen on 16/5/24.
 */
public class ResultWrapper {
    public <T> T wrap(Map<String, Object> map, Class<T> clazz) {
        try {
            FastClass fc = FastClass.create(clazz);
            T result = (T) fc.newInstance();
            return result;
        } catch (InvocationTargetException e) {
            throw new RuntimeException("生成对象失败", e);
        }
    }
}
