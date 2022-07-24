package com.henry.tryout.easy_coding.data_structure_and_collection.fail_fast_07;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class COW_copy_03 {
    public static void main(String[] args) {
//        List<Long> copy = new CopyOnWriteArrayList<>(); // 总耗时： 16 s
        List<Long> copy = new ArrayList<>(); // 20 ms

        long start = System.nanoTime();
        for (int i = 0; i < 20 * 10000; i++) { // 20万次
            // 执行插入操作 - 每次操作都会引起 原始集合的拷贝动作
            copy.add(System.nanoTime());
        }
        long end = System.nanoTime();

        System.out.println("总耗时： " + (end - start) / (1000 * 1000) + " ms");
    }
}
/*
启示：
    1 初始化COW集合时，先把数据填充到 ArrayList中，然后把 ArrayList集合作为COW的参数 - 从而实现批量添加；
    2 COW适用于读多 写极少的场景；
    3 COW是fail-safe机制的 - 所有并发包中的集合都是使用这种机制实现的；
        原理：fail-safe是在一个安全的副本上进行遍历操作的。
        集合的修改 与 集合的遍历是在两个独立的集合上进行的。
    4 COW没有办法读取到最新的数据 - CAP中，C(consistency)与A(availability)的矛盾

 */