package com.henry.tryout.easy_coding.data_structure_and_collection_06.array_and_collection_04;

import java.util.ArrayList;
import java.util.List;

// 验证：调用 list.toArray(arr)时，对于 不同大小的arr，方法执行的耗时 是不相同的 - 因为 所执行的代码 是不一样的
// 手段：对于 同一个list对象，调用toArray()时 传入不同大小的arr参数，并 计算执行耗时
public class ListToArrayDifferentCapacityDemo_04 {
    public static final int COUNT = 100 * 100 * 100;

    public static void main(String[] args) {
        // 已知：当 数组容量不足 时，会调用 return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        List<Double> list = new ArrayList<>(COUNT);
        // 向集合中添加 100万个元素
        for (int i = 0; i < COUNT; i++) {
            list.add(i * 1.0);
        }

        long start = System.nanoTime();

        // 使用 容量 不足够的数组
        Double[] notEnoughArray = new Double[COUNT - 1];
        list.toArray(notEnoughArray);

        // 使用 容量 与list‘s size相等的数组
        long middle1 = System.nanoTime();
        Double[] equalsArray = new Double[COUNT];
        list.toArray(equalsArray);

        // 使用 容量 是list size两倍的数组
        long middle2 = System.nanoTime();
        Double[] doubleArray = new Double[COUNT * 2];
        list.toArray(doubleArray);
        long end = System.nanoTime();

        // 比较 不同条件下的耗时 - 手段: nanoTime()的时间差 / (1000 * 1000) ms
        long notEnoughArrayTimeSpan = middle1 - start;
        long equalsArrayTimeSpan = middle2 - middle1;
        long doubleArrayTimeSpan = end - middle2;

        System.out.println("数组容量 小于 集合大小时，耗时： " +
                notEnoughArrayTimeSpan / (1000.0 * 1000.0) + " ms"); // 29.3124 ms

        System.out.println("数组容量 等于 集合大小时，耗时：" +
                equalsArrayTimeSpan / (1000.0 * 1000.0) + " ms"); // 4.6907 ms

        System.out.println("数组容量 大于 集合大小时，耗时： " +
                doubleArrayTimeSpan / (1000.0 * 1000.0) + " ms"); // 5.9113 ms

    }
}

/*
启示：
    使用 list.toArray(array) 获取数组的时候，传入的数组：
        1 大小应该 与列表的size相同；
        2 数组的类型 应该与 列表item类型 相同。

结论：当数组容量 = list's size时， toArray(array01)的耗时最小
 */
