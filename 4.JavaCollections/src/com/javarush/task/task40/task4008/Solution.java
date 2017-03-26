package com.javarush.task.task40.task4008;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

/* 
Работа с Java 8 DateTime API
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

        DateTimeFormatter dtf = null;
        LocalDate localDate = null;
        LocalTime localTime = null;

        if (date.contains(".") && date.contains(":")) {
            dtf = DateTimeFormatter.ofPattern("d.M.yyyy H:m:s");
            day = true;
            hour = true;
        } else if (date.contains(".")) {
            dtf = DateTimeFormatter.ofPattern("d.M.yyyy");
            day = true;
        } else if (date.contains(":")) {
            dtf = DateTimeFormatter.ofPattern("H:m:s");
            hour = true;
        }

        if (day) {
            localDate = LocalDate.parse(date, dtf);
            System.out.println("День: " + localDate.getDayOfMonth());
            System.out.println("День недели: " + localDate.getDayOfWeek().getValue());
            System.out.println("День месяца: " + localDate.getDayOfMonth());
            System.out.println("День года: " + localDate.getDayOfYear());
            System.out.println("Неделя месяца: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfMonth()));
            System.out.println("Неделя года: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfYear()));
            System.out.println("Месяц: " + localDate.getMonth().getValue());
            System.out.println("Год: " + localDate.getYear());
        }
        if (hour) {
            localTime = LocalTime.parse(date, dtf);
            if (localTime.get(ChronoField.AMPM_OF_DAY) == 1)
                System.out.println("AM или PM: PM");
            else
                System.out.println("AM или PM: AM");
            System.out.println("Часы: " + localTime.get(ChronoField.CLOCK_HOUR_OF_AMPM));
            System.out.println("Часы дня: " + localTime.getHour());
            System.out.println("Минуты: " + localTime.getMinute());
            System.out.println("Секунды: " + localTime.getSecond());
        }
    }
}
