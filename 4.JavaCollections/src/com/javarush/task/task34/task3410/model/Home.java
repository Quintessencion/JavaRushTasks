package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Home extends GameObject {

    //Constructor
    public Home(int x, int y) {
        super(x, y);
        setWidth(2);
        setHeight(2);
    }


    //Functions
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);

        graphics.drawOval(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth() + 5, getHeight() + 5);
    }
}
