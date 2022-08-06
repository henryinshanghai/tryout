package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_pool_04;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor_01 {
    // 线程池中的常驻 核心线程数量 - 任务完成后，核心线程会被保留。
    int corePoolSize;
    // 线程池所能容纳的最大线程数量 - 这个数字必须≥1
    int maximumPoolSize;
    // 线程池中 线程所能允许的最大空闲时间 - 如果线程闲置的时间更长，则：该线程就会被销毁
    long keepAliveTime;
    // 时间单位
    TimeUnit unit;
    // 缓存队列 - 如果请求的线程数量 > maximumPoolSize，则：新的任务会进入 Blocking Queue阻塞队列
    BlockingQueue<Runnable> workQueue;
    // 线程工厂 - 用于生产一组 完成相同任务的线程。 特征：能够给线程命名 - 从而在分析虚拟机栈时，就能够知晓 线程任务是由哪个线程工厂产生的
    ThreadFactory threadFactory;
    // 执行拒绝策略的对象 - 随着任务逐渐增多，连 阻塞队列/任务缓存区也到达了最大上线。对于新的请求，线程池就会使用此策略处理请求
    /*
        常见的拒绝策略：
        1 保存到数据库，以此实现 削峰添谷 - 在空闲的时候，再提取出来执行
        2 转向某个提示页面；
        3 打印日志。
     */
    RejectedExecutionHandler handler;

    public MyThreadPoolExecutor_01(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
                maximumPoolSize <= 0 ||
                maximumPoolSize < corePoolSize ||
                keepAliveTime < 0) {
            throw new IllegalArgumentException();
        }

        // 阻塞队列、线程工厂、拒绝策略这三个参数都必须被初始化，否则会抛异常
        // 但开发者很少手动编码来实现初始化 - 手段：使用 Executors(一个线程池静态工厂)来 提供默认的实现
        if (workQueue == null || threadFactory == null || handler == null) {
            throw new NullPointerException();
        }

        // 剩余代码
    }
}

