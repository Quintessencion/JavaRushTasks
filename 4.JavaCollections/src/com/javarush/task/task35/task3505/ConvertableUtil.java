package com.javarush.task.task35.task3505;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <T> Map convert(List<? extends Convertable<?>> list) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<Object, Convertable> result = new HashMap();

//        for (T t : list) {
//            result.put(t.getClass().getDeclaredMethod("getKey").invoke(t), t);
//        }

        for (Convertable<?> value : list) {
            result.put(value.getKey(), value);
        }
        return result;
    }
}
