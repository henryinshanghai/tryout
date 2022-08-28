package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_pool_04.basic_usage_01;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

// 自定义拒绝策略的类
public class UserRejectHandler_04 implements RejectedExecutionHandler { // #1 implements RejectedExecutionHandler 拒绝的执行处理器

    @Override
    public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
        System.out.println("task rejected. " + executor.toString());
    }
}
