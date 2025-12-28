package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_pool_04.basic_usage_01.uasge_04;

import com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_pool_04.basic_usage_01.self_defined_params_03.ParamRejectHandler;
import com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_pool_04.basic_usage_01.self_defined_params_03.ParamThreadFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 使用 线程池对象 来 处理任务
public class ThreadPoolUsage {
    public static void main(String[] args) {
        // ⑤ 设置 缓存队列 的长度为2 - 目的：快速地触发 rejectHandler
        BlockingQueue queue = new LinkedBlockingQueue<>(2);

        // ⑥ 指定名称的线程工厂：生产一组 用于完成相同任务的 线程
        ParamThreadFactory f1 = new ParamThreadFactory("第1机房");
        ParamThreadFactory f2 = new ParamThreadFactory("第2机房");

        // ⑦ 拒绝策略；
        ParamRejectHandler handler = new ParamRejectHandler();

        // 创建线程池对象1；
        // 池对象的参数：核心线程为1，最大线程为2 - 这样能够触发 rejectHandler
        ThreadPoolExecutor threadPoolFirst =
                new ThreadPoolExecutor(1,
                        2,
                        60,
                        TimeUnit.SECONDS,
                        queue,
                        f1,
                        handler);

        /* 使用 线程池对象 来 执行 线程任务；
        手段：调用 线程池对象的execute()方法，传入 待执行的任务 👇 */
        Runnable task = new Task();
        for (int i = 0; i < 5; i++) {
            // 线程池的 最大线程数 为2 & 阻塞队列 的大小为2
            // 推论：如果 交给线程池 5个任务/线程 去执行，就会引发 拒绝策略
            threadPoolFirst.execute(task);

        }
    }
}

class Task implements Runnable {

    @Override
    public void run() {
        System.out.println("执行run()");
    }
}
/*
当 任务 被拒绝 的时候，拒绝策略 会打印出 当前线程池的大小 已经达到了 maximumPoolSize=2,
而且 缓冲队列 已经满了，完成的任务数 提示已经有一个(最后一行)。

什么情况下，线程池 会执行 拒绝策略呢？
① 线程池的最大线程数 已经被占用了；
② 任务的阻塞队列 已经被占满了；
③ 这时候，仍旧 再添加任务 时，就会引起 线程池的拒绝策略
 */
