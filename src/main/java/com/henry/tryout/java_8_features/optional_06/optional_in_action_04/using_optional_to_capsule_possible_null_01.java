package com.henry.tryout.java_8_features.optional_06.optional_in_action_04;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class using_optional_to_capsule_possible_null_01 {
    public static void main(String[] args) {
        // JDK接口旧的设计策略 - 通过返回一个null 来表示 所需要的值的缺失
        // 例子： map.get(<not_existed_key>) get()方法会返回一个null

        capsulePossibleNullDemo();
    }

    private static void capsulePossibleNullDemo() {
        Map<String, Object> nameToAgeMap = new HashMap<>();

        nameToAgeMap.put("henry", 28);
        nameToAgeMap.put("alicia", 28);
        nameToAgeMap.put("Bob", 30);

        Object ageOfCris = nameToAgeMap.get("Cris");
        System.out.println("Cris's age is: " + ageOfCris);

        // 使用optional对象 对可能的null进行封装
        Optional<Object> optCrisAge = Optional.ofNullable(nameToAgeMap.get("Cris"));
        // 在 空的optional对象上调用 get()会引起报错
        System.out.println("the age in optional is: " + optCrisAge); // Optional.empty

    }
}
