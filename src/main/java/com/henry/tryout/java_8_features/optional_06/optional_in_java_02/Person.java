package com.henry.tryout.java_8_features.optional_06.optional_in_java_02;

import java.util.Optional;

public class Person {
    private Optional<Car> car; // 人可能没有车

    public Optional<Car> getCar() {
        return car;
    }
}
