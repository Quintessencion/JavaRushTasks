package com.javarush.task.task37.task3702.male;

import com.javarush.task.task37.task3702.Human;

public class MaleFactory {

    public Human getPerson(int age) {
        Human human = null;
        if (age > 19) {
            human = new Man();
        } else if (age > 12 && age < 20) {
            human = new TeenBoy();
        } else if (age < 13) {
            human = new KidBoy();
        }
        return human;
    }
}
