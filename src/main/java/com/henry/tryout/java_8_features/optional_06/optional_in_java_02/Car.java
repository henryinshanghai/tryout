package com.henry.tryout.java_8_features.optional_06.optional_in_java_02;

import java.util.Optional;

public class Car {
    private Optional<Insurance> insurance; // 车可能没有上保险

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
