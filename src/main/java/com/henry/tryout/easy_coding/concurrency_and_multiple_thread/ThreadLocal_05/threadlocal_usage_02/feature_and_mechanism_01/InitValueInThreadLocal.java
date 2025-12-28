package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.threadlocal_usage_02.feature_and_mechanism_01;

// 验证：ThreadLocal 无法解决 共享变量的更新问题
// 手段：① 使用ThreadLocal 来 封装一个 可变对象（StringBuilder）；② 在线程中 对 共享变量 做更新操作； ③ 创建 多个线程，并 启动
// 结论：虽然 变量builder 是 ThreadLocal类型，但是 每个线程 对builder的更新 仍没能 被隔离开来
public class InitValueInThreadLocal {
    public static final StringBuilder INIT_VALUE =
            new StringBuilder("init");

    // 定义一个 ThreadLocal类型的变量，并 初始化它
    // 初始化手段：覆写 ThreadLocal类的initialValue()方法，并 返回可变对象 StringBuilder
    private static final ThreadLocal<StringBuilder> builder
            = new ThreadLocal<StringBuilder>() {
                @Override
                protected StringBuilder initialValue() {
                    return INIT_VALUE; // 1 ThreadLocal中 封装的是 一个 StringBuilder对象(可变变量)
                }
            };

//    private static final StringBuilder builder = new StringBuilder("init");

    // 定义 线程类，重写run()方法
    private static class AppendStringThread extends Thread {
        /*
            在线程中使用的变量：
                builder: ThreadLocal变量
                inThread: 局部变量
         */
        @Override
        public void run() {
            // 获取到 Threadlocal变量中的 对象副本
            // 手段：threadLocal变量.get()
            StringBuilder inThread = builder.get();

            // 2 更新 共享的可变变量的值
            for (int i = 0; i < 10; i++) {
                inThread.append("-" + i);
            }

            System.out.println(this.getName() + " " + inThread.toString());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            // 每次循环 时，创建 一个线程对象 并 执行其run()方法
            new AppendStringThread().start();
        }
    }
}
/*
从 打印结果上看，各个线程 的确是 把 1-9 添加到了 原始的StringBuilder对象 上了。
Thread-0 init-0-1-2-3-4-5-6-7-8-9
Thread-1 init-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9
Thread-4 init-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9-0-1-2-3-4-5-6-7-8-9
...
这是 预期的行为 吗？

特征：第二个线程执行 run()方法时，inThread的值 是 第一个线程执行后的结果。
    这么看来，builder这个变量 看起来并不是 线程私有的呀。否则，线程2 与 线程1 看到的初始值 为什么会是不一样的呢？

手段：把这里的builder从 ThreadLocal 替换成为 StringBuilder类型；
结果：数据结果乱序不可控???
结论：如果编码时 使用某个引用 来 操作共享对象时，仍旧需要进行线程同步的操作？？？
 */