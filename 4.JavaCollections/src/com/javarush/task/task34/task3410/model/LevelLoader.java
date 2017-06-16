package com.javarush.task.task34.task3410.model;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    //Fields
    Path levels;

    //Constructor
    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    //Functions
    public GameObjects getLevel(int level) {
        if (level > 60) {
            level = level % 60;
        }

        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        int x0 = Model.FIELD_CELL_SIZE / 2;
        int y0 = Model.FIELD_CELL_SIZE / 2;

        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {

            while (true) {
                if (reader.readLine().equals("Maze: " + level)) {
                    break;
                }
            }

            reader.readLine();
            int x = Integer.parseInt(reader.readLine().split(" ")[2]);
            int y = Integer.parseInt(reader.readLine().split(" ")[2]);
            for (int i = 0; i < 3; i++) reader.readLine();

            String line;
            for (int i = 0; i < y; i++) {
                line = reader.readLine();

                for (int j = 0; j < x; j++)
                    switch (line.charAt(j)) {
                        case ' ':
                            break;
                        case 'X':
                            walls.add(new Wall(x0 + j * Model.FIELD_CELL_SIZE, y0 + i * Model.FIELD_CELL_SIZE));
                            break;
                        case '@':
                            player = new Player(x0 + j * Model.FIELD_CELL_SIZE, y0 + i * Model.FIELD_CELL_SIZE);
                            break;
                        case '*':
                            boxes.add(new Box(x0 + j * Model.FIELD_CELL_SIZE, y0 + i * Model.FIELD_CELL_SIZE));
                            break;
                        case '&':
                            boxes.add(new Box(x0 + j * Model.FIELD_CELL_SIZE, y0 + i * Model.FIELD_CELL_SIZE));
                            homes.add(new Home(x0 + j * Model.FIELD_CELL_SIZE, y0 + i * Model.FIELD_CELL_SIZE));
                            break;
                        case '.':
                            homes.add(new Home(x0 + j * Model.FIELD_CELL_SIZE, y0 + i * Model.FIELD_CELL_SIZE));
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GameObjects(walls, boxes, homes, player);
    }
}
