package com.javarush.task.task38.task3804;

/* 
Фабрика исключений

Создай класс — фабрику исключений, который содержит один статический метод, возвращающий нужное исключение.
Этот метод должен принимать один параметр — энум.
Если передан энум:
* ExceptionApplicationMessage, верни исключение Exception
* ExceptionDBMessage, верни исключение RuntimeException
* ExceptionUserMessage, верни Error иначе верните IllegalArgumentException без сообщения.

В качестве сообщения (message) для каждого возвращаемого объекта используйте элементы энума, в которых все знаки подчеркивания замените на пробелы.
Сообщение должно быть в нижнем регистре за исключением первого символа.
Например, сообщение для ExceptionApplicationMessage.SOCKET_IS_CLOSED — «Socket is closed».

Верните класс созданной фабрики в методе Solution.getFactoryClass.

Энумы не меняй.
*/

public class Solution {
    public static Class getFactoryClass() {
        return FactoryExceptions.class;
    }

    public static void main(String[] args) {
        System.out.println(Solution.getFactoryClass());

    }
}