package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии

В программе случайным образом генерируются два целых числа A и В.
Нужно вывести все целые числа от A до B включительно, в порядке возрастания,
если A меньше B, или в порядке убывания в противном случае.

Задача реализована с использованием рекурсии.
Иногда в результате работы программы получаем Exception in thread «main« java.lang.StackOverflowError.

Твоя задача: перепиши код без использования рекурсии.
Метод recursion переименуй на getAllNumbersBetween.
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        String result = "";
        if (a < b) {
            for (int i = a; i <= b; i++) {
                result += i + " ";
            }
        } else {
            if (a == b) {
                return String.valueOf(a);
            }
            for (int i = a; i >= b; i--) {
                result += i + " ";
            }
        }
        return result.trim();
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt() % 1_000;
        numberB = random.nextInt() % 10_000;
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}