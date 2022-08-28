package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_pool_04.basic_usage_01;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

// 自定义线程工厂
public class UserThreadFactory_03 implements ThreadFactory { // #1 implements ThreadFactory

    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger();

    // 定义线程组的名称； 作用：在使用jstack来排查线程问题时，有意义的线程名称会很有帮助
    UserThreadFactory_03(String whatFeatureOfGroup) {
        namePrefix = "UserThreadFactory's " + whatFeatureOfGroup + "-Worker-";
    }

    // #2 创建线程任务 - 手段：重写 newThread()方法，传入 Runnable参数,返回 Thread对象
    @Override
    public Thread newThread(Runnable task) {
        // 为线程指定一个有意义的名字
        String name = namePrefix + nextId.getAndIncrement();
        // 使用这个名字来 构造出线程对象
        Thread thread = new Thread(null, task, name, 0); // 5个参数的构造方法取消了？
        // ① 打印 当前线程的名称
        System.out.println(thread.getName());
        return thread;
    }

    public static void main(String[] args) {
        // 创建线程工厂时,传入 "线程组的名称"
        UserThreadFactory_03 userThread = new UserThreadFactory_03("test");
        // 创建线程时，传入 自定义的任务
        Thread thread = userThread.newThread(new Task());
        // 启动线程
        thread.start();
    }
}

// 定义任务本身 - 使用线程的run()方法来表示任务
class Task implements Runnable {
    // 线程安全的Long - 作用：多线程并发读写时，能够保证数据的正确性
    private final AtomicLong count = new AtomicLong(0L);

    // 调用线程的start()方法时，真正执行的方法是 run()
    @Override
    public void run() {
        // ② 在自定义的任务中, 打印出当前任务线程中的count值
        System.out.println("running_" + count.getAndIncrement());
    }
}
/*
UserThreadFactory's test-Worker-0 // test是user传入的参数,用作线程组的组名； 0是线程的id(用原子类实现)
running_0

 */