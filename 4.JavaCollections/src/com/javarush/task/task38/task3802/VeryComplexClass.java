package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.FileInputStream;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        FileInputStream fis = null;
        int k;
        fis = new FileInputStream("B:/file.txt");
        while ((k = fis.read()) != -1) {
            System.out.println((char) k);
        }
    }

    public static void main(String[] args) throws Exception {
        new VeryComplexClass().veryComplexMethod();
    }
}
