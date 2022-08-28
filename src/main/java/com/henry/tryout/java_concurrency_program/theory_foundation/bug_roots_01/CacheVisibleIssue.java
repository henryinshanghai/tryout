package com.henry.tryout.java_concurrency_program.theory_foundation.bug_roots_01;

// 用两个线程 来 操作一个共享变量 - 由于缓存导致的 "变量可见性"问题，共享变量得不到预期的值
public class CacheVisibleIssue {
    private static long count = 0;

    private void add10K() {
        int idx = 0;
        while(idx++ < 10000) {
            count += 1;
        }
    }

    public static long calc() throws InterruptedException {
        final CacheVisibleIssue test = new CacheVisibleIssue();

        // 创建两个线程，执行add()操作
        Thread th1 = new Thread(()->{
            test.add10K();
        });
        Thread th2 = new Thread(()->{
            test.add10K();
        });

        // 启动两个线程
        th1.start();
        th2.start();

        // 等待两个线程执行结束
        th1.join();
        th2.join();
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        long calc = calc();
        System.out.println("计算结果为： " + calc); // 预期：20000 实际：14331
    }
}
