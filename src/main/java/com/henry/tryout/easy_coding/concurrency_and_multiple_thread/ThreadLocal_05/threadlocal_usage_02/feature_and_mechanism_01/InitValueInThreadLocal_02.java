package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.threadlocal_usage_02.feature_and_mechanism_01;

// ThreadLocal无法解决 共享变量的更新问题 - 证明代码👇
// 特征：1 这里 ThreadLocal所封装的是一个 可变对象（StringBuilder）；  2 在线程中对共享变量做了更新操作
public class InitValueInThreadLocal_02 {
    public static final StringBuilder INIT_VALUE =
            new StringBuilder("init");

    // 创建 ThreadLocal变量 - 覆写 initialValue()方法，并返回可变对象 StringBuilder
    /*
    private static final ThreadLocal<StringBuilder> builder
            = new ThreadLocal<StringBuilder>(){

        @Override
        protected StringBuilder initialValue() {
            return INIT_VALUE; // 1 ThreadLocal中封装的是一个 可变变量
        }
    }; */
    private static final StringBuilder builder = new StringBuilder("init");

    // 定义线程类，重写run()方法
    private static class AppendStringThread extends Thread {
        /*
            在线程中使用的变量：
                builder ThreadLocal变量
                inThread 局部变量
         */
        @Override
        public void run() {
            // 获取到Threadlocal变量中的对象副本 - 手段：threadLocal变量.get()
            StringBuilder inThread = builder; // .get()
            // 2 在线程中,更新 共享的可变变量
            for (int i = 0; i < 10; i++) {
                inThread.append("-" + i);
            }
            System.out.println(this.getName() + " " + inThread.toString());
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
Thread-0 init-0-1-2-3-4-5-6-7-8-9
Thread-1 init-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9
Thread-4 init-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9
...
这是预期的行为吗？

特征：第二个线程执行 run()方法时，inThread的值是第一个线程执行后的结果。
    这么看来，builder这个变量看起来并不是 线程私有的呀。否则，线程2 与 线程1看到的初始值为什么会是不一样的呢？

手段：把这里的builder从 ThreadLocal 替换成为 StringBuilder类型；
结果：控制台打印的结果 和 使用ThreadLocal变量时一致。

结论：在线程中操作共享对象时，仍旧会需要同步操作???

 */