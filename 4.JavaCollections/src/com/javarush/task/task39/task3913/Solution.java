package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
//        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
//        System.out.println(logParser.getUniqueIPs(null, null));
//        System.out.println(logParser.getIPsForUser("Eduard Petrovich Morozko", null, null));
//        System.out.println(logParser.getIPsForEvent(Event.DONE_TASK, null, null));
//        System.out.println(logParser.getIPsForStatus(Status.OK, null, null));
//        System.out.println();
//        System.out.println(logParser.getAllUsers());
//        System.out.println(logParser.getNumberOfUsers(null, null));
//        System.out.println(logParser.getNumberOfUserEvents("Eduard Petrovich Morozko", null, null));
//        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Eduard Petrovich Morozko", null, null));
//        System.out.println(logParser.getNumberOfAttemptToSolveTask(1, null, null));
//        System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(15, null, null));
        System.out.println(logParser.execute("get ip"));
        System.out.println(logParser.execute("get user"));
        System.out.println(logParser.execute("get date"));
        System.out.println(logParser.execute("get event"));
        System.out.println(logParser.execute("get status"));
        System.out.println(logParser.execute("get ip for user = \"Vasya Pupkin\""));
        System.out.println(logParser.execute("get user for event = \"DONE_TASK\""));
        System.out.println(logParser.execute("get event for date = \"03.01.2014 03:45:23\""));
        System.out.println(logParser.execute("get date for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
    }
}