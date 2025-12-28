package com.henry.tryout.easy_coding.data_structure_and_collection_06.map_08.hashMap_03.why_lost_data.in_JDK8;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

// 验证：Java8中的HashMap 也存在 多线程条件下数据丢失 的问题
// CountDownLatch的用法：设置一个 有N道门闩的大门，阻塞 主线程的运行。
public class HashMapDataLossDemo {
    public static void main(String[] args) throws InterruptedException {
        int threadCount = 10;
        int perThread = 1000;
        HashMap<Integer, Integer> sharedMap = new HashMap<>();
        // “主线程等 N 个子任务全部完成后再继续” —— 这就是 CountDownLatch 最典型的用途。
        // 相当于 有N道门闩的一个大门，这个大门 会阻塞主线程的运行
        CountDownLatch latchAmount = new CountDownLatch(threadCount);

        for (int currentThreadNo = 0; currentThreadNo < threadCount; currentThreadNo++) {
            final int tid = currentThreadNo;

            // 创建一个线程
            new Thread(() -> {
                // 执行所需要的任务
                for (int j = 0; j < perThread; j++) {
                    sharedMap.put(tid * perThread + j, j); // 不同 key
                }

                // 任务执行完成后，把计数器-1
                // 🐖 每次调用 countDown()，会打开一个门闩
                latchAmount.countDown();
            }).start();
        }

        // 阻塞主线程，直到 计数器归零(所有门闩都被拔掉)
        latchAmount.await();

        System.out.println("Expected size: " + (threadCount * perThread)); // 10000
        System.out.println("Actual size:   " + sharedMap.size()); // 9800 ← 数据丢失！
    }
}