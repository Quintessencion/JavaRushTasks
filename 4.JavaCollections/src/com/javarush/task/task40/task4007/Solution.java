package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) throws ParseException {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) throws ParseException {
        //напишите тут ваш код
        boolean day = false;
        boolean hour = false;

        Calendar calendar = Calendar.getInstance();
        Pattern p = Pattern.compile("(\\d{1,2}).(\\d{1,2}).(\\d{4})\\s(\\d{1,2}):(\\d{1,2}):(\\d{1,2})");
        Matcher m = p.matcher(date);
        Pattern p2 = Pattern.compile("(\\d{1,2}).(\\d{1,2}).(\\d{4})");
        Matcher m2 = p2.matcher(date);
        Pattern p3 = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})");
        Matcher m3 = p3.matcher(date);

        if (m.find()) {
            SimpleDateFormat sdf = new SimpleDateFormat("d.M.yyyy H:m:s");
            calendar.setTime(sdf.parse(date));
            day = true;
            hour = true;
        } else if (m2.find()) {
            SimpleDateFormat sdf = new SimpleDateFormat("d.M.yyyy");
            calendar.setTime(sdf.parse(date));
            day = true;
        } else if (m3.find()) {
            SimpleDateFormat sdf = new SimpleDateFormat("H:m:s");
            calendar.setTime(sdf.parse(date));
            hour = true;
        }

        if (day) {
            System.out.println("День: " + calendar.get(Calendar.DAY_OF_MONTH));
            System.out.println("День недели: " + ((calendar.get(Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1));
            System.out.println("День месяца: " + calendar.get(Calendar.DAY_OF_MONTH));
            System.out.println("День года: " + calendar.get(Calendar.DAY_OF_YEAR));
            System.out.println("Неделя месяца: " + calendar.get(Calendar.WEEK_OF_MONTH));
            System.out.println("Неделя года: " + calendar.get(Calendar.WEEK_OF_YEAR));
            System.out.println("Месяц: " + (calendar.get(Calendar.MONTH) + 1));
            System.out.println("Год: " + calendar.get(Calendar.YEAR));
        }
        if (hour) {
            System.out.println("AM или PM: " + (calendar.get(Calendar.HOUR_OF_DAY) > 12 ? "PM" : "AM"));
            System.out.println("Часы: " + calendar.get(Calendar.HOUR));
            System.out.println("Часы дня: " + calendar.get(Calendar.HOUR_OF_DAY));
            System.out.println("Минуты: " + calendar.get(Calendar.MINUTE));
            System.out.println("Секунды: " + calendar.get(Calendar.SECOND));
        }
    }
}
