package com.javarush.task.task39.task3904;

import java.util.Arrays;

/* 
Лестница

Ребенок бежит по лестнице состоящей из N ступенек, за 1 шаг он может пройти одну, две или три ступеньки.

Реализуй метод countPossibleRunups(int n), который вернет количество способов которыми
ребенок может пробежать всю лестницу состоящую из n ступенек.

P.S. Если лестница состоит из 0 ступенек — метод должен возвращать 1. Для n < 0 верни 0.
*/
public class Solution {
    private static int n = 70;

    public static void main(String[] args) {
        System.out.println("Number of possible runups for " + n + " stairs is: " + countPossibleRunups(n));
    }

    public static long countPossibleRunups(int n) {
        long l = 0;
        if (n < 0) return 0;
        else if (n == 0) return 1;
        else if (n == 1) return 2;
        else if (n == 2) return 3;
        else if (n == 3) return 4;

        long[] arr = new long[n];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 4;
        for (int i = 3; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
        }

        return arr[n - 1];
    }
}

