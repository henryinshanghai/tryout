package com.henry.tryout.java_8_features.optional_06.optional_in_java_02;

import java.util.Optional;

// 验证：A对象持有B对象的Optional封装时会提供额外的语义 - A可能没有B
public class Car {
    private Optional<Insurance> insurance; // Optional所提供的语义 - 车可能没有上保险

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
