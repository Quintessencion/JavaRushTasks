package com.javarush.task.task31.task3112;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Загрузчик файлов

Реализуй метод downloadFile(String urlString, Path downloadDirectory),
на вход которого подается ссылка для скачивания файла и папка, куда нужно закачать файл.
Все ссылки имеют вид:
https://yastatic.net/morda-logo/i/citylogos/yandex19-logo-ru.png
http://toogle.com/files/rules.txt
https://pacemook.com/photos/image1.jpg

Метод должен создать объект URL и загрузить содержимое файла на локальный диск.
Выкачивай сначала во временную директорию, чтобы в случае неуспешной загрузки в твоей директории не оставались недокачанные файлы.
Затем перемести файл в пользовательскую директорию. Имя для файла возьми из ссылки.
Используй только классы и методы из пакета java.nio.
*/
public class Solution {

    public static void main(String[] args) throws IOException {
//        Path passwords = downloadFile("https://www.amigo.com/ship/secretPassword.txt", Paths.get("D:/MyDownloads"));
        Path passwords = downloadFile("https://yastatic.net/morda-logo/i/citylogos/yandex19-logo-ru.png", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        BufferedImage img = ImageIO.read(new URL(urlString));
        String nameImage = new File(urlString).getName();

        File downLoadFile = new File("D:/tempDir/" + nameImage);
        File tempDir = new File("D:/tempDir");
        File dest = new File(downloadDirectory + File.separator + nameImage);
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }
        ImageIO.write(img, "png", downLoadFile);

        if (!Files.exists(downloadDirectory)) {
            Files.createDirectory(downloadDirectory);
        }

        downLoadFile.renameTo(dest);
        tempDir.delete();

        return dest.toPath();

//        URL url = new URL(urlString);
//        InputStream inputStream = url.openStream();
//
//        Path tmp = Files.createTempFile("temp-", ".tmp");
//        Files.copy(inputStream, tmp);
//        String fileName = urlString.substring(urlString.lastIndexOf("/"));
//        String dir = downloadDirectory.toString();
//        Path moveTo = Paths.get(dir + fileName);
//        Files.move(tmp, moveTo);
//        return Paths.get(downloadDirectory.toString() + fileName);
    }
}
