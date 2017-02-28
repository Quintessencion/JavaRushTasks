package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/* 
Палиндром?

Объяви и реализуй логику приватного статического метода Set<Integer> getRadix(String number),
в котором нужно определить, в каких системах счисления (от 2 до 36 включительно) представление числа number
(передается в десятичной системе счисления) является палиндромом и добавить индекс таких систем в результат.
Если переданное число некорректно — возвращай пустой HashSet.
В системах счисления с основанием большим 10 в качестве цифр используются латинские буквы. К примеру,
числу 35 в десятичной системе соответствует число «Z» в системе с основанием 36.

Метод main не принимает участие в тестировании.

P.S.: В методе getRadix перехватывай NumberFormatException. Стек-трейс выводить не нужно.
*/

public class Solution {
    private static TreeMap<Integer, Character> alphabet = new TreeMap<>();

    static {
        alphabet.put(10, 'A');
        alphabet.put(11, 'B');
        alphabet.put(12, 'C');
        alphabet.put(13, 'D');
        alphabet.put(14, 'E');
        alphabet.put(15, 'F');
        alphabet.put(16, 'G');
        alphabet.put(17, 'H');
        alphabet.put(18, 'I');
        alphabet.put(19, 'J');
        alphabet.put(20, 'K');
        alphabet.put(21, 'L');
        alphabet.put(22, 'M');
        alphabet.put(23, 'N');
        alphabet.put(24, 'O');
        alphabet.put(25, 'P');
        alphabet.put(26, 'Q');
        alphabet.put(27, 'R');
        alphabet.put(28, 'S');
        alphabet.put(29, 'T');
        alphabet.put(30, 'U');
        alphabet.put(31, 'V');
        alphabet.put(32, 'W');
        alphabet.put(33, 'X');
        alphabet.put(34, 'Y');
        alphabet.put(35, 'Z');
    }

    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        HashSet<Integer> answ = new HashSet<>();
        int numb;

        for (int i = 2; i <= 36; i++) {

            try {
                numb = Integer.parseInt(number, 10);
            } catch (NumberFormatException e) {
                return answ;
            }
            String newNumb = from10toX(numb, i);
            if (isPolindromic(newNumb)) answ.add(i);
        }

        return answ;
    }

    private static String from10toX(int number, int x) {

        int value = number;
        String result = "";

        while (value >= x) {

            int p = value / x;
            int q = value % x;

            if (q > 9) result = String.valueOf(alphabet.get(q)) + result;
            else result = String.valueOf(q) + result;

            value = p;
        }

        if (value > 9) result = String.valueOf(alphabet.get(value)) + result;
        else result = String.valueOf(value) + result;

        return result;
    }

    private static boolean isPolindromic(String newNumb) {
        int center = newNumb.length() / 2;
        for (int i = 0; i < center; i++) {
            if (newNumb.charAt(i) != newNumb.charAt(newNumb.length() - (i + 1))) {
                return false;
            }
        }
        return true;
    }
}