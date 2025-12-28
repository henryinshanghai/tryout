package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_pool_04.basic_usage_01.constructor_param_01;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

// 验证：JDK中的线程池(threadPoolExecutor) 其构造方法参数 有7个之多 👇
// ① 核心线程数量；② 最大线程数量；③ 线程最大空闲时间；④ 时间单位；⑤ 等待队列；⑥ 线程工厂；⑦ 拒绝策略
public class ThreadPoolParams {
    // 线程池的7个成员变量
    // 线程池中的 常驻 核心线程数量 - 特征：任务完成后，核心线程 会被保留。
    int corePoolSize;
    // 线程池 所能容纳的 最大线程数量 - 这个数字必须≥1
    int maximumPoolSize;
    // 线程池中 线程 所能允许的 最大空闲时间 - 如果 线程闲置的时间 更长，则：该线程就会 被销毁
    long keepAliveTime;
    // 时间单位
    TimeUnit unit;
    // *缓冲/等待队列 - 如果 请求的线程数量 > maximumPoolSize，则：新的任务 会进入 Blocking Queue阻塞队列
    BlockingQueue<Runnable> workQueue;
    // *线程工厂 - 用于生产一组 完成相同任务的线程。 特征：能够 给线程命名 - 从而在分析虚拟机栈时，就能够知晓 线程任务是由哪个线程工厂产生的
    ThreadFactory threadFactory;
    // *执行拒绝策略的对象 - 随着任务逐渐增多，连 阻塞队列/任务缓存区 也到达了最大上线。对于新的请求，线程池就会 使用此策略 处理请求
    /*
        常见的拒绝策略：
        1 保存到数据库，以此实现 削峰添谷 - 在空闲的时候，再提取出来执行
        2 转向某个提示页面；
        3 打印日志。
     */
    RejectedExecutionHandler handler;

    // 在构造器中判断传入参数，并抛出异常
    public ThreadPoolParams(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
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

