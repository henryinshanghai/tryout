package com.henry.tryout.java_basic;

import java.util.HashMap;
import java.util.Map;

public class TraverseMapItems {
    public static void main(String[] args) {

        Map<String, Integer> people = new HashMap<>();

        people.put("henry", 26);
        people.put("andy", 26);
        people.put("annie", 26);
        people.put("leo", 26);

        // loop through the map items
        for (Map.Entry<String, Integer> entry : people.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
