package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_sync_03.semaphore_sync_03;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        // 准备一个 countDownLatch对象
        CountDownLatch count = new CountDownLatch(3);

        // 作为参数传入自定义线程的构造器中
        Thread thread1 = new TranslateThread("1st content", count);
        Thread thread2 = new TranslateThread("2nd content", count);
        Thread thread3 = new TranslateThread("3rd content", count);

        // 依次启动三个线程 - 在线程的run()方法中，操作 CountDownLatch对象
        thread1.start();
        thread2.start();
        thread3.start();

        // 调用 countDownLatch对象的await()方法 - 以等待所有的线程执行完成
        count.await(10, TimeUnit.SECONDS);
        // 给调用方返回翻译的结果
    }
}

class TranslateThread extends Thread {
    private String content;
    private final CountDownLatch count; // 成员变量的初始化应该交给使用者 - 通过构造方法

    public TranslateThread(String content, CountDownLatch count) {
        this.content = content;
        this.count = count;
    }

    @Override
    public void run() {
        // 在某些情况下，调用翻译引擎时，抛出了异常
        /*
            这里抛出的异常没有被主线程 try-catch到 - 这会导致当前线程没能执行到 countDown()方法
            解决手段： 子线程中发生的异常 可以通过线程方法 setUncaughtExceptionHandler()方法来捕获
         */
        if (Math.random() > 0.5) {
            throw new RuntimeException("原文中存在非法字符");
        }

        System.out.println(content + "的翻译已经完成，译文是... ");
        count.countDown();
    }
}
