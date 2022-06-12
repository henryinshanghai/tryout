package com.henry.tryout.java_basic.copy;

import lombok.Data;

@Data
public class Person {
    private String name;
    private int age;

    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person copyTo(Person person) {
        person.name = this.name;
        person.age = this.age;

        return person;
    }
}
