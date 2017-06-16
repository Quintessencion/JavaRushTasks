package com.javarush.task.task34.task3410.controller;

import com.javarush.task.task34.task3410.model.Direction;

public interface EventListener {

    void move(Direction direction);//передвинуть объект в определенном направлении.

    void restart();//начать заново текущий уровень.

    void startNextLevel();//начать следующий уровень.

    void levelCompleted(int level);//уровень с номером level завершён.
}
