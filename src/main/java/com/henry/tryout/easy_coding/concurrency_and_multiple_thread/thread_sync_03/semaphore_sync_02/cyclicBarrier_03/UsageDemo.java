package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_sync_03.semaphore_sync_02.cyclicBarrier_03;

import java.util.concurrent.CyclicBarrier;

public class UsageDemo {
    private static final int THREAD_COUNT = 3;
    private static int[] partialSums = new int[THREAD_COUNT];
    private static volatile int totalSum = 0;

    public static void main(String[] args) {
        // 屏障动作：当所有线程到达时，自动执行汇总
        CyclicBarrier barrier =
                new CyclicBarrier(THREAD_COUNT,
                        () -> {
                            totalSum = partialSums[0] + partialSums[1] + partialSums[2];
                            System.out.println("✅ 所有线程计算完成，总和 = " + totalSum);
                        }
                );

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int index = i;
            new Thread(() -> {
                // 1. 并行计算
                int sum = 0;
                for (int j = 0; j < 1000; j++) {
                    sum += j;
                }
                partialSums[index] = sum;
                System.out.println(Thread.currentThread().getName() + " 计算完成: " + sum);

                try {
                    // 2. 等待其他线程
                    barrier.await(); // 到达屏障点
//                    System.out.println(Thread.currentThread().getName() + " 到达屏障点，等待其他线程到达 ");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // 3. 所有线程一起继续（例如：打印结果）
                System.out.println(Thread.currentThread().getName() + " 继续执行后续任务...");
            }).start();
        }
    }
}
