package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_pool_04.basic_usage_01;

import java.util.concurrent.*;

public class UsingExecutorsStaticMethods_02 {
    public static void main(String[] args) {
        // 怎么查看类的继承关系？
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 3, 20, TimeUnit.SECONDS,
                null, null, null);

        // 作用：创建 拥有足够线程的线程池；
        // 特征：1 JDK8引入； 2：支持给定的并行度； 3 通过使用多个队列来减少竞争
        ExecutorService executorService = Executors.newWorkStealingPool(4);

        // 特征：1 maximumPoolSize最大可以达到 Integer.MAX_VALUE; 2 keepAliveTime默认时间为60s； 3 线程空闲，则：回收线程。任务增加，则：创建线程。
        ExecutorService executorService1 = Executors.newCachedThreadPool();

        // 特征：1 maximumPoolSize最大可以达到 Integer.MAX_VALUE(存在OOM风险)； 2 支持定时/周期性地任务执行； 3 不会回收工作线程
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        // 作用：创建一个单线程的线程池； 特征：1 单线程串行执行所有任务； 2 任务会按照提交顺序被依次执行。
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        // 特征：1 传入的参数就是 线程池中的固定线程数量； 2 不存在空闲线程 - 因此 keepAliveTime等于0.
        ExecutorService executorService3 = Executors.newFixedThreadPool(10);

    }

}
/*
总结：
    除了 newWorkStealingPool之外，其他4个方法创建线程池时都存在资源被耗尽(OOM)的风险 - 因为底层使用了无界队列。
 */
