package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

public class FactoryProducer {

    public static enum HumanFactoryType {
        FEMALE, MALE;
    }

    public static AbstractFactory getFactory(HumanFactoryType type) {
        if (type.equals(HumanFactoryType.MALE)) {
            return new MaleFactory();
        } if (type.equals(HumanFactoryType.FEMALE)) {
            return new FemaleFactory();
        }
        return null;
    }
}
