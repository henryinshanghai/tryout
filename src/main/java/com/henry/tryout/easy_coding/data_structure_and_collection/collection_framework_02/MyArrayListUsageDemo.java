package com.henry.tryout.easy_coding.data_structure_and_collection.collection_framework_02;

public class MyArrayListUsageDemo {
    public static void main(String[] args) {
        MyArrayList<String> names = new MyArrayList<>();

        names.add("henry");
        System.out.println("===");
        names.add("jane");
        System.out.println("===");
        names.add("alicia");
        System.out.println("===");
        names.add("sherlock");
        System.out.println("===");
        names.add("rachel");
        System.out.println("===");
        names.add("monica");
        System.out.println("===");
        names.add("pheobe");
        System.out.println("===");
        names.add("Joey");

        System.out.println("~~~");
        System.out.println("names size is: " + names.size());
    }
}
