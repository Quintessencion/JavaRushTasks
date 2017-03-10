package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getUniqueIPs(null, null));
        System.out.println(logParser.getIPsForUser("Eduard Petrovich Morozko", null, null));
        System.out.println(logParser.getIPsForEvent(Event.DONE_TASK, null, null));
        System.out.println(logParser.getIPsForStatus(Status.OK, null, null));
        System.out.println();
        System.out.println(logParser.getAllUsers());
        System.out.println(logParser.getNumberOfUsers(null, null));
        System.out.println(logParser.getNumberOfUserEvents("Eduard Petrovich Morozko", null, null));
        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Eduard Petrovich Morozko", null, null));
        System.out.println(logParser.getNumberOfAttemptToSolveTask(1, null, null));
        System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(15, null, null));
    }
}