package com.henry.tryout.java_8_features.do_collection_with_stream_03.toMap_05;

import com.henry.tryout.java_8_features.do_collection_with_stream_03.delete_04.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class toMapDemo {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        users.add(new User("henry", 28, "male"));
        users.add(new User("alicia", 28, "female"));
        users.add(new User("jane", 25, "male"));
        users.add(new User("cris", 27, "male"));
        // users.add(new User("jingjing", null, "male"));

        Map<String, Integer> nameToAgeMap =  generateNameToAgeMap(users);

        for (String name : nameToAgeMap.keySet()) {
            System.out.println("name " + name + " -> " + nameToAgeMap.get(name));
        }
    }

    private static Map<String, Integer> generateNameToAgeMap(List<User> users) {
        return users.stream()
                .collect(Collectors.toMap(User::getName, User::getAge));
    }

}
/*
    启发： toMap()方法的确要求 键值对中的value不能为null
    原理： Objects.requireNonNull(value);
    原因：
 */
