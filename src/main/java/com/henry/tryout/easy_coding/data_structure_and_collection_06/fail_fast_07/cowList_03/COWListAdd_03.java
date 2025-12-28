package com.henry.tryout.easy_coding.data_structure_and_collection_06.fail_fast_07.cowList_03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// 验证：对 COW类型的list 执行 写操作 会很耗时 - 因为 每次写操作时, 都需要 拷贝出一个 当前list的副本
// 手段：构造一个场景，不断地 向COWList中 写入新元素
public class COWListAdd_03 {
    public static void main(String[] args) {
//        List<Long> list = new CopyOnWriteArrayList<>(); // 总耗时： 16 s
        List<Long> list = new ArrayList<>(); // 20 ms

        long start = System.nanoTime(); // nanoTime() - 用于测量时间间隔
        for (int i = 0; i < 20 * 10000; i++) { // 20万次
            // 执行插入操作 - 对于COWList，每次add 都会引起 原始集合的拷贝动作
            list.add(System.nanoTime());
        }
        long end = System.nanoTime();

        System.out.println("总耗时： " + (end - start) / (1000 * 1000) + " ms");
    }
}
