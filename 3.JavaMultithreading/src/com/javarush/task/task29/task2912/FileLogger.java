package com.javarush.task.task29.task2912;

public class FileLogger extends AbstractLogger {

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    public void info(String message) {
        super.info("Logging to file: " + message);
    }
}