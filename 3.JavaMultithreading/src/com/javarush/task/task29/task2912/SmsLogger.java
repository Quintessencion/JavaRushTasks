package com.javarush.task.task29.task2912;

public class SmsLogger extends AbstractLogger {

    public SmsLogger(int level) {
        this.level = level;
    }

    @Override
    public void info(String message) {
        super.info("Send sms to CEO: " + message);
    }
}