package com.henry.tryout.easy_coding.data_structure_and_collection.collection_framework_02.arraylist_01;

public class MyArrayListUsageDemo {
    public static void main(String[] args) {
        MyArrayList_01<String> names = new MyArrayList_01<>();

        names.add("henry"); // 添加第一个元素，elementData会被扩容到默认容量：2
        System.out.println("===");
        names.add("jane");
        System.out.println("===");
        names.add("alicia"); // 添加 alicia时，容量不足 再次扩容 - 新容量 = 旧容量+2 aka 4
        System.out.println("===");
        names.add("sherlock");
        System.out.println("===");
        names.add("rachel"); // 添加 rachel时，容量不足 再次扩容 - 新容量 = 6， 已经大于 警戒容量。因此新容量会被设置成为最大容量7
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
