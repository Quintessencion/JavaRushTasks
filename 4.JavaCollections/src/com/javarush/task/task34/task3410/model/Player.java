package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {

    //Constructor
    public Player(int x, int y) {
        super(x, y);
    }

    //Functions
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);

        graphics.drawOval(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
        graphics.fillOval(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
    }

    @Override
    public void move(int x, int y) {
        this.setX(getX() + x);
        this.setY(getY() + y);
    }
}
