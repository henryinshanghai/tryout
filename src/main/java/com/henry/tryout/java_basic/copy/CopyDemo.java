package com.henry.tryout.java_basic.copy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CopyDemo {
    List<Person> people = new ArrayList<>();

    private void initPeople() {
        people.add(new Person("henry", 28));
        people.add(new Person("jane", 28));
        people.add(new Person("quinta", 33));
    }

    @Test
    public void copyTest() {
        initPeople();

        List<Person> old_people = people;

        List<Person> new_people = people.stream()
                .map(person -> person.copyTo(new Person()))
                .collect(Collectors.toList());

        boolean result = old_people == new_people;
        System.out.println("更新item之后， 列表的内存地址是否发生变化？ " + result);
    }
}
