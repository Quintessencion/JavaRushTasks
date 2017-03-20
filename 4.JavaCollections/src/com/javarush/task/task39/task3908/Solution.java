package com.javarush.task.task39.task3908;

import java.util.*;

/*
Возможен ли палиндром?

Реализуй метод isPalindromePermutation(String s) который будет возвращать true,
если из всех символов строки s можно составить палиндром. Иначе — false.

Символы в анализируемой строке ограничены кодировкой ASCII.
Регистр букв не учитывается.
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("abbca"));
    }

    public static boolean isPalindromePermutation(String s) {
        if (s == null || s.isEmpty()) return false;
        String line = s.toLowerCase();

        Character[] ch = new Character[line.length()];
        for (int i = 0; i < line.length(); i++) {
            ch[i] = line.charAt(i);
        }

        List<Character> list = Arrays.asList(ch);
        int i = 0;
        for (Character c : list) {
            if (Collections.frequency(list, c) % 2 != 0) i++;
        }

        return i > 1 ? false : true;
    }
}
