package com.henry.tryout.easy_coding.data_structure_and_collection.collection_framework_02.arraylist_01;

public class MyArrayListDrillDemo {
    public static void main(String[] args) {
        MyArrayListDrill<String> list = new MyArrayListDrill<>();

        /*
            默认容量 = 5
            扩容的规则 = 旧容量+3
            警戒容量 = 10
            最大容量 = 15
         */
        list.add("1"); // 首次添加元素，elementData被设置默认容量为 5
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        System.out.println("============");
        list.add("6"); // 添加f时，elementData容量不足。开始第一次扩容 容量变成8
        list.add("7");
        list.add("8");
        System.out.println("*************");
        list.add("9"); // 添加9时， elementData容量不足。新的预期容量为11 已经大于警戒容量,因此扩容后容量为警戒容量10
        list.add("10");
        System.out.println("^^^^^^^^^^^^^");
        list.add("11"); // 添加11时， elementData容量不足。needCapacity > MAX_ARRAY_SIZE, 扩容到最大容量 15
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");
        System.out.println("###############");
        list.add("16"); // 超出最大容量，抛出异常
    }
}
