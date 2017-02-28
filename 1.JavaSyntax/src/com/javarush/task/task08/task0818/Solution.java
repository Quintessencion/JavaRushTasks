package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Map;

/* 
Только для богачей

Создать словарь (Map<String, Integer>) и занести в него десять записей по принципу: «фамилия» — «зарплата».
Удалить из словаря всех людей, у которых зарплата ниже 500.
*/

public class Solution {

    public static HashMap<String, Integer> createMap() {
        //напишите тут ваш код
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Быков", 1000);
        map.put("Раков", 400);
        map.put("Бяков", 5000);
        map.put("Тихов", 100);
        map.put("Громов", 1500);
        map.put("Беркут", 1700);
        map.put("Майка", 200);
        map.put("Кепкин", 150);
        map.put("Рыбкин", 7000);
        map.put("Крюков", 33000);

        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        //напишите тут ваш код
        HashMap<String, Integer> clone = new HashMap<>(map);
        for (Map.Entry<String, Integer> entry : clone.entrySet()) {
            if (entry.getValue() < 500) {
                map.remove(entry.getKey());
            }
        }

    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = createMap();


        removeItemFromMap(map);


    }
}