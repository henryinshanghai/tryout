package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.qianwen;

public class ThreadLocalExample {
    // 定义 ThreadLocal<String>类型的 成员变量
    private static ThreadLocal<String> context = ThreadLocal.withInitial(() -> "DEFAULT");

    public static void main(String[] args) throws InterruptedException {
        // 定义一个 Runnable类型的实例
        Runnable task = () -> {
            // 获取到 当前线程的名称
            String threadName = Thread.currentThread().getName();
            // 把 当前线程的名称 set到 ThreadLocal变量(当前线程专有的变量)中
            context.set("User-" + threadName);

            // 从ThreadLocal对象中 获取封装的对象，并打印
            System.out.println(threadName + " set context: " + context.get());

            // 模拟业务逻辑
            doSomething();

            // 清理！[重要]
            context.remove();
        };

        // 创建线程对象，传入runnable对象 作为参数
        Thread t1 = new Thread(task, "T1");
        Thread t2 = new Thread(task, "T2");

        // 分别启动线程
        t1.start();
        t2.start();

        // ???
        t1.join();
        t2.join();
    }

    private static void doSomething() {
        System.out.println("In doSomething, context = " + context.get());
    }
}
