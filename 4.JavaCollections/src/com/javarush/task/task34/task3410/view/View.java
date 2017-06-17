package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.Controller;
import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.GameObjects;
import com.javarush.task.task34.task3410.model.Model;

import javax.swing.*;

public class View extends JFrame {
    //Fields
    private Controller controller;
    private Field field;
    private Model model;

    //Constructor
    public View(Controller controller, Model model) {
        this.controller = controller;
        this.model = model;
    }

    //Functions
    public void init() {
        field = new Field(this, model);
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);
    }

    public void setEventListener(EventListener eventListener) {
        this.field.setEventListener(eventListener);
    }

    public void update() {
        field.repaint();
    }

    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }

    public void completed(int level) {
        update();
        JOptionPane.showMessageDialog(null, level + " Completed", "Level", JOptionPane.INFORMATION_MESSAGE);
        controller.startNextLevel();
    }
}
