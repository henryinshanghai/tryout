package com.henry.tryout.java_8_features.stream_02;

public class Dish {
    private final String name;
    private final int calories;
    private final boolean vegetarian;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.calories = calories;
        this.vegetarian = vegetarian;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                '}';
    }

    public enum Type {
        MEAT, FISH, OTHER
    }
}

