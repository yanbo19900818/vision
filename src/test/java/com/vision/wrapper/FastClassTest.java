package com.vision.wrapper;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenchen on 16/6/17.
 */
public class FastClassTest {
    public static void main(String[] args) throws InvocationTargetException {
        long startTime = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1l);
        map.put("name", "yanbo");
        map.put("delete", true);
        TestObject testObject = MapToBeanWrapper.wrap(map, TestObject.class);
        for (int i = 0; i < 10000; i++) {
            System.out.print(MapToBeanWrapper.wrap(map, TestObject.class));
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

    }
}
