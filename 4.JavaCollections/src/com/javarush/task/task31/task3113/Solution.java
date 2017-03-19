package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?

Напиши программу, которая будет считать подробную информацию о папке и выводить ее на консоль.

Первым делом считай путь к папке с консоли.
Если введенный путь не является директорией — выведи «[полный путь] — не папка» и заверши работу.
Затем посчитай и выведи следующую информацию:

Всего папок — [количество папок в директории]
Всего файлов — [количество файлов в директории и поддиректориях]
Общий размер — [общее количество байт, которое хранится в директории]

Используй только классы и методы из пакета java.nio.
*/
public class Solution {

    //Fields
    static int countDir = -1;
    static int countFiles = 0;
    static long countBytes = 0;

    //Functions
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        Path pathFile = Paths.get(scan.readLine());
        scan.close();

        if (!Files.isDirectory(pathFile)) {
            System.out.println(pathFile.toAbsolutePath() + " - не папка");
            return;
        } else {
            Files.walkFileTree(pathFile, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    countFiles++;
                    countBytes += Files.size(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    countDir++;
                    return FileVisitResult.CONTINUE;
                }
            });
        }

        System.out.println("Всего папок - " + countDir);
        System.out.println("Всего файлов - " + countFiles);
        System.out.println("Общий размер - " + countBytes);
    }
}