package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_pool_04.basic_usage_01;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UserThreadPool_05 {
    public static void main(String[] args) {
        // 设置缓存队列的长度为2 - 目的：快速地触发 rejectHandler
        BlockingQueue queue = new LinkedBlockingQueue<>(2);

        // 线程工厂：准备需要执行的任务 - 任务有两个来源
        // 特征：在创建线程工厂的时候，可以为自定义的工厂指定名称
        UserThreadFactory_03 f1 = new UserThreadFactory_03("第1机房");
        UserThreadFactory_03 f2 = new UserThreadFactory_03("第2机房");

        // 拒绝策略；
        UserRejectHandler_04 handler = new UserRejectHandler_04();

        // 线程池对象1； 核心线程为1，最大线程为2 - 这样能够触发 rejectHandler
        ThreadPoolExecutor threadPoolFirst =
                new ThreadPoolExecutor(1, 2, 60,
                        TimeUnit.SECONDS, queue, f1, handler);

        // 线程池对象2： 利用第二个线程工厂 创建第二个线程池
        ThreadPoolExecutor threadPoolSecond =
                new ThreadPoolExecutor(1, 2, 60,
                        TimeUnit.SECONDS, queue, f2, handler);

        // 创建400个任务线程 - 使用线程池对象 来 执行线程任务；
        // 手段：调用线程池对象的execute()方法，传入待执行的任务
        Runnable task = new Task();
        // 特征：当循环的次数设置为5时，
        // 线程池First的 名为第1机房的线程工厂，创建了3个线程来处理 需要多次执行的任务。
        // 因此，引起了 线程池的拒绝策略 打印如下👇
        /*
            不是很清楚为什么引起了拒绝策略？！
            task rejected. java.util.concurrent.ThreadPoolExecutor@61064425[Running, pool size = 2, active threads = 0, queued tasks = 0, completed tasks = 4]
         */
        for (int i = 0; i < 5; i++) {
            // 使用线程池 来处理任务 - 两个线程池独立地处理相同的任务各200次
            // 重复的 execute()方法调用 会导致线程池的拒绝策略
            threadPoolFirst.execute(task);
//            threadPoolSecond.execute(task);
        }
    }
}
/*
当任务被拒绝的时候，拒绝策略会打印出 当前线程池的大小已经达到了 maximumPoolSize=2,
而且队列已经满了，完成的任务数 提示已经有一个(最后一行)。

什么情况下，线程池会执行拒绝策略呢？
- 线程池的最大线程数 已经被占用了；
- 任务的阻塞队列 已经被占满了；
- 这时候，仍旧再添加任务时，就会引起 线程池的拒绝策略

喝多了，不知道是啥了
 */
