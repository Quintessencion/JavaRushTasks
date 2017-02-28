package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        ArrayList<Integer> list = new ArrayList<>();
        BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            list.add(Integer.valueOf(is.readLine()));
        }

        int count = 1, maxSequence = 1;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == list.get(i - 1)) {
                count++;
                if (count > maxSequence) {
                    maxSequence = count;
                }
            } else count = 1;

        }
        System.out.println(maxSequence);
    }
}