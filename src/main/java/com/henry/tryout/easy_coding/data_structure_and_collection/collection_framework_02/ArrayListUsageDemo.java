package com.henry.tryout.easy_coding.data_structure_and_collection.collection_framework_02;

import java.util.List;

public class ArrayListUsageDemo {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<Integer>();

        nums.add(10);
        nums.add(10);
        nums.add(10);

        System.out.println("nums size: " + nums.size());
    }
}
