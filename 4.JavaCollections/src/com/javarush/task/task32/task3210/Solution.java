package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];
        byte[] data = new byte[text.length()];

        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.seek(number);
        raf.read(data, 0, text.length());

        raf.seek(raf.length());

        if (new String(data).equals(text)) {
            raf.write("true".getBytes());
        } else {
            raf.write("false".getBytes());
        }

        raf.close();
    }
}
