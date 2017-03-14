package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций

В классе Solution необходимо реализовать простейшую обработку аннотаций.

В методы printFullyQualifiedNames и printValues приходит класс. Если этот класс отмечен аннотацией PrepareMyTest,
необходимо вывести на экран fullyQualifiedNames и values в соответствующих методах и вернуть true.
Если же аннотация PrepareMyTest отсутствует — вернуть false.

Для вывода на экран классов из массива value используй сокращенное имя (getSimpleName).
*/

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        if(c.isAnnotationPresent(PrepareMyTest.class)){
            PrepareMyTest pmt = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            for(String s: pmt.fullyQualifiedNames()){
                System.out.println(s);
            }
        } else return false;
        return true;
    }

    public static boolean printValues(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest pmt = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            for (Class clazz : pmt.value()) {
                System.out.println(clazz.getSimpleName());
            }
        } else return false;
        return true;
    }
}
