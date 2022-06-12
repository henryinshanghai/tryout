package com.henry.tryout.java_8_features.do_collection_with_stream_03.delete_04;

import lombok.Data;

@Data
public class User {
    String name;
    Integer age;
    String gender;

    public User() {
    }

    public User(String name, Integer age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
