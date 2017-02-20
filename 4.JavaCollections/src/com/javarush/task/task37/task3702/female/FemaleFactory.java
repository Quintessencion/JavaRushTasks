package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class FemaleFactory implements AbstractFactory {

    public Human getPerson(int age) {
        Human human = null;
        if (age > 19) {
            human = new Woman();
        } else if (age > 12 && age < 20) {
            human = new TeenGirl();
        } else if (age < 13) {
            human = new KidGirl();
        }
        return human;
    }
}
