package com.henry.tryout.async_program.via_threadAndPool_01.viaPool_02;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncExample_all_in_threadpool_02 {


    public static void doSomethingA() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingA---");
    }

    public static void doSomethingB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingB---");

    }

    // 0自定义线程池
    private final static int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private final static ThreadPoolExecutor POOL_EXECUTOR =
            new ThreadPoolExecutor(
                    AVAILABLE_PROCESSORS,
                    AVAILABLE_PROCESSORS * 2,
                    1,
                    TimeUnit.MINUTES,
                    new LinkedBlockingQueue<>(5),
                    new ThreadPoolExecutor.CallerRunsPolicy()
            );

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        // 1.开启异步单元执行任务A
        POOL_EXECUTOR.execute(() -> {
            try {
                doSomethingA();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 2.执行任务B
        POOL_EXECUTOR.execute(() -> {
            try {
                doSomethingB();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 3.同步等待线程A运行结束
        System.out.println(System.currentTimeMillis() - start);

        // 4.挂起当前线程
        Thread.currentThread().join();
    }
}
/*
这里存在有三个线程：main线程、用于运行任务A的线程、用于运行任务B的线程；

由于任务A与任务B都不会阻塞main线程，所以：
    sout语句先被打印；
由于任务A与任务B都被丢到线程池中执行（任务A先于任务B），所以：
    --- doSomethingA---
    --- doSomethingB---
 */
