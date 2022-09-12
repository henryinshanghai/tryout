package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_pool_04.basic_usage_01;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

// 线程池构造方法的参数* - 自定义拒绝策略的类
// 作用：指定当任务过多(工作线程 + 阻塞队列都处理不了)时，怎样来拒绝任务
public class UserRejectHandler_04 implements RejectedExecutionHandler { // #1 implements RejectedExecutionHandler 拒绝的执行处理器

    @Override
    public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
        System.out.println("task rejected. " + executor.toString());
    }
}
