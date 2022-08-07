package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_pool_04.basic_usage_01;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

// 自定义拒绝策略
public class UserRejectHandler_04 implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
        System.out.println("task rejected. " + executor.toString());
    }
}
