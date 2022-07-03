package com.henry.tryout.easy_coding.data_structure_and_collection;

import java.util.ArrayList;
import java.util.List;

public class ListToArraySpeedTest_03 {
    public static final int COUNT = 100 * 100 * 100;

    public static void main(String[] args) {
        List<Double> list = new ArrayList<>();

        // 准备100万个元素的测试集合
        for (int i = 0; i < COUNT; i++) {
            list.add(i * 1.0);
        }

        long start = System.nanoTime();

        // 集合转数组时,数组的容量不够
        Double[] notEnoughArray = new Double[COUNT - 1];
        list.toArray(notEnoughArray);

        long middle1 = System.nanoTime();

        // 集合转数组时,数组的容量 与 集合元素数量相等
        Double[] equalArray = new Double[COUNT];
        list.toArray(equalArray);

        long middle2 = System.nanoTime();

        // 集合转数组时,数组的容量 大于 集合元素数量
        Double[] doubleArray = new Double[COUNT * 2];
        list.toArray(doubleArray);
        long end = System.nanoTime();

        long notEnoughArrayTime = middle1 - start;
        long equalArrayTime = middle2 - middle1;
        long doubleArrayTime = end - middle2;

        System.out.println("数组容量小于集合大小时：notEnoughArrayTime - " + notEnoughArrayTime / (1000.0 * 1000.0) + " ms");
        System.out.println("数组容量等于集合大小时：equalArrayTime - " + equalArrayTime / (1000.0 * 1000.0) + " ms");
        System.out.println("数组容量大于集合大小时：doubleArrayTime - " + doubleArrayTime / (1000.0 * 1000.0) + " ms");

    }
}
/*
为什么这里执行得到的结果和书上是不一样的呢？
预期： 数组容量等于集合大小时，运行应该最快；
实际： 以上情况下，运行反而是最慢的. 多次运行结果都是一样的
 */
