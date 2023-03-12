package com.henry.tryout.java_8_features.optional_06.avoidNPEInTrainWreck_05;

public class Person {
    private String name;
    private Pet pet;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
