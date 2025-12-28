package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_sync_03.semaphore_sync_02.countdownLatch_01;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// 验证：可以使用 CountDownLatch 来 对 多个线程的执行 进行同步
// 同步的效果：主线程 等待 多个子线程 都执行完成后，才继续执行。
// 场景：主线程需要等待 其他多个线程执行结束，再继续执行 - 手段：countDownLatch
public class CountDownLatchUsage {
    public static void main(String[] args) throws InterruptedException {
        // #1 准备一个 countDownLatch对象 传入门闩数量3
        CountDownLatch count = new CountDownLatch(3);

        // #2 把 latch对象 作为参数传入 自定义线程的构造器 中
        Thread thread1 = new TranslateThread("1st content", count);
        Thread thread2 = new TranslateThread("2nd content", count);
        Thread thread3 = new TranslateThread("3rd content", count);

        // 模板代码: 先处理 子线程异常的情况
        // 手段：调用线程对象的 setUncaughtExceptionHandler()方法
        thread1.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println(t.getName() + ": " + e.getMessage());
        });
        thread2.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println(t.getName() + ": " + e.getMessage());
        });
        thread3.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println(t.getName() + ": " + e.getMessage());
        });

        // #3 依次启动 三个线程 - 在线程的run()方法中，操作 CountDownLatch对象
        thread1.start();
        thread2.start();
        thread3.start();

        // #4 调用 countDownLatch对象的await()方法
        // 作用：等待 所有 子线程 执行完成
        count.await(10, TimeUnit.SECONDS);
        System.out.println("所有线程执行完成");
        // 给调用方 返回 翻译的结果
    }
}

// 自定义线程类 - extends Thread
class TranslateThread extends Thread {
    private String content;
    // #2 子线程中 持有 门闩对象
    private final CountDownLatch count; // "成员变量的初始化"应该交给使用者 - via 构造方法

    public TranslateThread(String content, CountDownLatch count) {
        this.content = content;
        this.count = count;
    }

    @Override
    public void run() {
        // 在某些情况下，调用翻译引擎时，抛出了异常
        /*
            这里抛出的异常 没有 被主线程 try-catch到 - 这会导致 当前线程 没能执行到 countDown()方法
            解决手段：子线程中发生的异常 可以通过 线程方法setUncaughtExceptionHandler()方法 来 捕获
         */
        if (Math.random() > 0.5) {
            throw new RuntimeException("原文中存在非法字符");
        }

        System.out.println(content + "的翻译已经完成，译文是... ");
        // #3 在子线程中，调用countDown() 把门闩数量-1
        // 作用：每次 任务执行完成（运行run()）后，count就-1。当 count=0 时，门就会打开 主线程就能继续运行
        count.countDown();
    }
}
