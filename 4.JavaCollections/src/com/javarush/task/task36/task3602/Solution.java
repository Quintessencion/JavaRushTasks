package com.javarush.task.task36.task3602;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    //    public static Class getExpectedClass() {
//        Class result = null;
//        ArrayList<Class> listClasses = new ArrayList<>();
//        Class[] classes = Collections.class.getDeclaredClasses();
//        for (Class c : classes) {
//            if (Modifier.isStatic(c.getModifiers()) && Modifier.isPrivate(c.getModifiers())) {
//                if (c.getSuperclass() != null) {
//                    for (Class i : c.getSuperclass().getInterfaces()) {
//                        if (i.equals(List.class)) {
//                            listClasses.add(c);
//                        }
//                    }
//                }
//            }
//        }
//        for (Class c : listClasses) {
//            Constructor constructor;
//            try {
//                constructor = c.getDeclaredConstructor();
//                constructor.setAccessible(true);
//                List list = (List) constructor.newInstance();
//                list.get(1);
//            } catch (IndexOutOfBoundsException e) {
//                result = c;
//            } catch (NoSuchMethodException e) {
//                continue;
//            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return result;
//    }
    public static Class getExpectedClass() {
        for (Class c : Collections.class.getDeclaredClasses()) {
            if (List.class.isAssignableFrom(c) && Modifier.isPrivate(c.getModifiers()) && Modifier.isStatic(c.getModifiers())) {
                try {
                    Class clazz = Solution.class.getClassLoader().loadClass(c.getName());
                    Constructor constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    List list = (List) constructor.newInstance();
                    list.get(1);
                } catch (IndexOutOfBoundsException e) {
                    return c;
                } catch (NoSuchMethodException e) {
                    continue;
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
