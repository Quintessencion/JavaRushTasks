package com.javarush.task.task40.task4009;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/* 
Buon Compleanno!

Реализуй метод weekDayOfBirthday.
Метод должен возвращать день недели на итальянском языке, в который будет (или был) день рождения в определенном году.
Пример формата дат смотри в методе main.

Пример:
1) Для «30.05.1984» и «2015» метод должен вернуть: sabato
2) Для «1.12.2015» и «2016» метод должен вернуть: gioved?

Выполни задание, используя Java 8 DateTime API.
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(weekDayOfBirthday("30.05.1984", "2015"));
    }

    public static String weekDayOfBirthday(String birthday, String year) {
        //напишите тут ваш код
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        LocalDate localDate = LocalDate.parse(birthday, formatter);
        Year y = Year.parse(year);
        YearMonth ym = y.atMonth(localDate.getMonth());
        LocalDate ld = ym.atDay(localDate.getDayOfMonth());
        String result = ld.format(DateTimeFormatter.ofPattern("eeee", Locale.ITALIAN));

//        return Year.parse(year).atMonth(localDate.getMonth()).atDay(localDate.getDayOfMonth()).format(DateTimeFormatter.ofPattern("eeee", Locale.ITALIAN));

        return result;
    }
}
