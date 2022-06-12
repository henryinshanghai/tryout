package com.henry.tryout.java_8_features.do_collection_with_stream_03.delete_04;

import org.h2.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DeleteDemo {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        users.add(new User("henry", 28, "male"));
        users.add(new User("alicia", 28, "female"));
        users.add(new User("jane", 25, "male"));
        users.add(new User("cris", 27, "male"));

        users.removeIf(user -> StringUtils.equals(user.getName(), "cris"));
        System.out.println(users.size());
    }
}
