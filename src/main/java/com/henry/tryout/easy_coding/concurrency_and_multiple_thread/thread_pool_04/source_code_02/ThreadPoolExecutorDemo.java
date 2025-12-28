package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_pool_04.source_code_02;

import com.henry.tryout.async_program.via_threadAndPool_01.viaPool_02.NamedThreadFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        new ThreadPoolExecutor(1,
                3,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2),
                new NamedThreadFactory("name"));
    }
}
