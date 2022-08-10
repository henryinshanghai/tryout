package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.threadlocal_usage_02;

// ThreadLocal无法解决 共享变量的更新问题 -
// 特征：1 这里 ThreadLocal所封装的是一个 可变对象； 2 在线程中对共享变量做了更新操作
public class InitValueInThreadLocal_02 {
    public static final StringBuilder INIT_VALUE =
            new StringBuilder("init");

    // 创建 ThreadLocal变量 - 覆写 initialValue()方法，并返回可变对象 StringBuilder
    private static final ThreadLocal<StringBuilder> builder
            = new ThreadLocal<StringBuilder>(){

        @Override
        protected StringBuilder initialValue() {
            return INIT_VALUE;
        }
    };

    // 定义线程类
    private static class AppendStringThread extends Thread {
        /*
            在线程中使用的变量：
                builder ThreadLocal变量
                inThread 局部变量
         */
        @Override
        public void run() {
            // 获取到Threadlocal变量中的对象副本
            StringBuilder inThread = builder.get();
            for (int i = 0; i < 10; i++) {
                inThread.append("-" + i);
            }
            System.out.println(inThread.toString());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            // 每次循环时，创建一个线程对象 并执行其run()方法
            new AppendStringThread().start();
        }
    }
}
/*
从打印结果上看，各个线程的确是把1-9添加到了 原始的StringBuilder对象上了。
没毛病呀~

init-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9

结论：在线程中操作共享对象时，仍旧会需要同步操作???

 */