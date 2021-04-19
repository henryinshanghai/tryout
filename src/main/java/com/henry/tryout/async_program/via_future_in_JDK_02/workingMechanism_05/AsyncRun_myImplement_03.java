package com.henry.tryout.async_program.via_future_in_JDK_02.workingMechanism_05;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncRun_myImplement_03 {

    // 获取到当前机器的CPU数量
    private final static int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    // 创建线程池的实例
    private final static ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(
            AVAILABLE_PROCESSORS, // 线程池中的 核心线程数
            AVAILABLE_PROCESSORS * 2, // 线程池中的 最大线程数
            1, // 存活时间？？？
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(5), // 线程池的阻塞队列的大小
            new ThreadPoolExecutor.CallerRunsPolicy()); // 线程池拒绝任务的策略：线程池任务饱和时，不会丢弃新的任务，而是会 使用 调用线程 去执行


    public static CompletableFuture runAsync(Runnable runnable) {

        CompletableFuture<String> future = new CompletableFuture<String>();

        // 2. 开启线程，执行任务以计算结果。再设置计算出的结果
        POOL_EXECUTOR.execute(() -> {
            // 2.1 模拟任务的计算过程 & 计算结果
            try {
                Thread.sleep(3000);
                runnable.run();
                future.complete(null);
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        });

        return future;
    }
}
