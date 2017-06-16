package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;

public class Model {
    //Fields
    private EventListener eventListener;
    public static final int FIELD_CELL_SIZE = 20;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("E:\\Java\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3410\\res\\levels.txt"));

    //Functions
    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        if (checkWallCollision(getGameObjects().getPlayer(), direction)) return;
        if (checkBoxCollisionAndMoveIfAvaliable(direction)) return;

        switch (direction) {
            case DOWN:
                getGameObjects().getPlayer().move(0, FIELD_CELL_SIZE);
                break;
            case UP:
                getGameObjects().getPlayer().move(0, -FIELD_CELL_SIZE);
                break;
            case LEFT:
                getGameObjects().getPlayer().move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                getGameObjects().getPlayer().move(FIELD_CELL_SIZE, 0);
                break;
        }

        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {

        for (Box box : gameObjects.getBoxes()) {
            if (gameObjects.getPlayer().isCollision(box, direction)) {

                if (checkWallCollision(box, direction)) {
                    return true;
                }
                for (Box box1 : gameObjects.getBoxes()) {
                    if (box.isCollision(box1, direction)) {
                        return true;
                    }
                }

                switch (direction) {
                    case DOWN:
                        box.move(0, FIELD_CELL_SIZE);
                        break;
                    case UP:
                        box.move(0, -FIELD_CELL_SIZE);
                        break;
                    case LEFT:
                        box.move(-FIELD_CELL_SIZE, 0);
                        break;
                    case RIGHT:
                        box.move(FIELD_CELL_SIZE, 0);
                        break;
                }
            }
        }
        return false;
    }

    public void checkCompletion() {
        int coincidence = 0;

        for (Box box : gameObjects.getBoxes()) {
            for (Home home : gameObjects.getHomes()) {
                if (box.getX() == home.getX() && box.getY() == home.getY()) {
                    coincidence++;
                    continue;
                }
            }
        }

        if (coincidence == gameObjects.getBoxes().size()) {
            eventListener.levelCompleted(currentLevel);
        }
    }
}
