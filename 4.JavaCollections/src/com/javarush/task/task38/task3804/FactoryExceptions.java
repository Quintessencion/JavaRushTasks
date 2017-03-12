package com.javarush.task.task38.task3804;

public class FactoryExceptions {

    public static Throwable getException(Enum e) {
        if (e != null) {
            String message = e.name().charAt(0) + e.name().substring(1).toLowerCase().replaceAll("_", " ");

            if (e instanceof ExceptionApplicationMessage) {
                return new Exception(message);
            } else if (e instanceof ExceptionDBMessage) {
                return new RuntimeException(message);
            } else if (e instanceof ExceptionUserMessage) {
                return new Error(message);
            }
        }
        return new IllegalArgumentException();
    }
}
