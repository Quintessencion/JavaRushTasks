package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet

Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортируй буквы по алфавиту и выведи на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то выведи их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрой потоки.

Пример 1 данных входного файла:
zBk yaz b-kN

Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB

Пример 2 вывода:
abc
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<String> treeSet = new TreeSet<>();

        BufferedReader bf = new BufferedReader(new FileReader(args[0]));
        String s = "";
        while (bf.ready()) {
            s += (char) bf.read();
        }
        bf.close();

        String[] split = s.toLowerCase().split("");
        for (String st : split) {
            if (st.matches("[a-z]")) {
                treeSet.add(st);
            }
        }

        Iterator iterator = treeSet.iterator();
        if (treeSet.size() < 5) {
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } else {
            for (int i = 0; i < 5; i++) {
                System.out.print(iterator.next());
            }
        }
    }
}
