package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_sync_03.semaphore_sync_03;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// 使用 countDownLatch 来 同步多个线程
// 场景：主线程需要等待 其他多个线程执行结束，再继续执行 - 手段：countDownLatch
public class CountDownLatchUsage {
    public static void main(String[] args) throws InterruptedException {
        // #1 准备一个 countDownLatch对象
        CountDownLatch count = new CountDownLatch(3);

        // #2 把latch对象 作为参数传入自定义线程的构造器中
        Thread thread1 = new TranslateThread("1st content", count);
        Thread thread2 = new TranslateThread("2nd content", count);
        Thread thread3 = new TranslateThread("3rd content", count);

        // 模板代码:先处理子线程异常的情况
        thread1.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println(t.getName() + ": " + e.getMessage());
        });
        thread2.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println(t.getName() + ": " + e.getMessage());
        });
        thread3.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.out.println(t.getName() + ": " + e.getMessage());
        });

        // #3 依次启动三个线程 - 在线程的run()方法中，操作 CountDownLatch对象
        thread1.start();
        thread2.start();
        thread3.start();

        // #4 调用 countDownLatch对象的await()方法 - 以等待 所有使用 countDownLatch对象定义的的线程执行完成
        count.await(10, TimeUnit.SECONDS);
        System.out.println("所有线程执行完成");
        // 给调用方返回翻译的结果
    }
}

// 自定义线程类 - extends Thread
class TranslateThread extends Thread {
    private String content;
    // #2 子线程中 持有 倒计数门闩变量
    private final CountDownLatch count; // "成员变量的初始化"应该交给使用者 - via 构造方法

    public TranslateThread(String content, CountDownLatch count) {
        this.content = content;
        this.count = count;
    }

    @Override
    public void run() {
        // 在某些情况下，调用翻译引擎时，抛出了异常
        /*
            这里抛出的异常 没有被主线程 try-catch到 - 这会导致 当前线程没能执行到 countDown()方法
            解决手段： 子线程中发生的异常 可以通过线程方法 setUncaughtExceptionHandler()方法来捕获
         */
        if (Math.random() > 0.5) {
            throw new RuntimeException("原文中存在非法字符");
        }

        System.out.println(content + "的翻译已经完成，译文是... ");
        // #3 子线程中，调用countDown() - 每次运行run()的时候，count就-1. 当count=0时，latch will open
        count.countDown();
    }
}
