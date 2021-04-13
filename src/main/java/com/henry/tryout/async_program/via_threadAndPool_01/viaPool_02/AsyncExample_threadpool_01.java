package com.henry.tryout.async_program.via_threadAndPool_01.viaPool_02;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncExample_threadpool_01 {


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

    /* 0自定义线程池 */
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

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        // 1.开启异步单元执行任务A
        // 手段：先线程池实例的execute()方法中 添加任务作为参数 - 以lambda表达式的方式
        POOL_EXECUTOR.execute(() -> {
            try {
                doSomethingA();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 2.执行任务B
        doSomethingB();

        // 3.同步等待线程A运行结束
        System.out.println(System.currentTimeMillis() - start);

        // 4.挂起当前线程
        Thread.currentThread().join();
    }

}
/*
把任务直接交给一个线程池（而不是某一个线程）；

为了确保交给线程池的任务被执行完成， 需要在 主线程/调用线程中 进行显式地等待 - join()方法

线程池的作用：复用线程，避免线程重复创建与销毁所引起的开销
 */