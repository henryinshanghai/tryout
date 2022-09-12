package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_pool_04.basic_usage_01;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 使用线程池对象 来 处理任务
public class UserThreadPool_05 {
    public static void main(String[] args) {
        // 设置缓存队列的长度为2 - 目的：快速地触发 rejectHandler
        BlockingQueue queue = new LinkedBlockingQueue<>(2);

        // 指定名称的线程工厂：用于生产一组 完成相同任务的线程
        UserThreadFactory_03 f1 = new UserThreadFactory_03("第1机房");
        UserThreadFactory_03 f2 = new UserThreadFactory_03("第2机房");

        // 拒绝策略；
        UserRejectHandler_04 handler = new UserRejectHandler_04();

        // 线程池对象1； 核心线程为1，最大线程为2 - 这样能够触发 rejectHandler
        ThreadPoolExecutor threadPoolFirst =
                new ThreadPoolExecutor(1, 2, 60,
                        TimeUnit.SECONDS, queue, f1, handler);

        /* 使用线程池对象 来 执行线程任务； 手段：调用线程池对象的execute()方法，传入待执行的任务 👇 */
        Runnable task = new Task();
        for (int i = 0; i < 5; i++) {
            // 线程池的最大线程数为2 & 阻塞队列的大小为2 - 如果交给线程池5个任务/线程去执行，就会引发 拒绝策略
            threadPoolFirst.execute(task);

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
 */
