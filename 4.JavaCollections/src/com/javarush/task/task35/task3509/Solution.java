package com.javarush.task.task35.task3509;

import java.util.*;

/*
Collections & Generics
*/
public class Solution {

    public static void main(String[] args) {
        ArrayList<Integer> listI = new ArrayList<>();
        ArrayList<String> listS = new ArrayList<>();
        for (int i = 0; i < 10; i++) listI.add(i);
        for (int i = 0; i < 10; i++) listS.add(i + "a");
        HashMap<Integer, String> map = newHashMap(listI, listS);
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < elements.length; i++) {
            list.add(elements[i]);
        }
        return list;
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        HashSet<T> set = new HashSet();
        for (int i = 0; i < elements.length; i++) {
            set.add(elements[i]);
        }
        return set;
    }

    public static <K, V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        //напишите тут ваш код
        HashMap<K, V> map = new HashMap();
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }
}
