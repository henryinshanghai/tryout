package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_pool_04.basic_usage_01.self_defined_params_03;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

// *线程池的构造器参数1 - 自定义线程工厂
// 特征：能够 为 线程池中的线程 配置一个 有意义的名字 - 方便后期排查
public class ParamThreadFactory implements ThreadFactory { // #1 implements ThreadFactory

    private final String namePrefix; // #1 持有线程组的名称
    private final AtomicInteger nextId = new AtomicInteger(); // #2 持有线程id的名称

    // 定义线程组的名称； 作用：在使用jstack来排查线程问题时，有意义的线程名称会很有帮助
    public ParamThreadFactory(String whatFeatureOfGroup) {
        namePrefix = "UserThreadFactory's " + whatFeatureOfGroup + "-Worker-";
    }

    // #3 根据构造出的 有意义的名称 + 传入的task 来 创建出线程对象
    @Override
    public Thread newThread(Runnable task) {
        // 为线程指定一个有意义的名字
        String name = namePrefix + nextId.getAndIncrement();
        // 使用这个名字(组名 + 线程id) 来 构造出线程对象
        Thread thread = new Thread(null, task, name, 0);
        // ① 打印 当前线程的名称
        System.out.println(thread.getName());
        return thread;
    }

    public static void main(String[] args) {
        // 创建线程工厂时,传入 "线程组的名称"
        ParamThreadFactory userThread = new ParamThreadFactory("test");
        // 创建线程时，传入 自定义的任务
        Thread thread = userThread.newThread(new Task());
        // 启动线程
        thread.start();
    }
}

// 定义 任务本身 - 使用 线程的run()方法 来 表示 具体任务
class Task implements Runnable {
    // 线程安全的Long - 作用：多线程 并发读写 时，能够 保证 数据的正确性
    private final AtomicLong count = new AtomicLong(0L);

    // 调用 线程的start()方法 时，会触发执行 run()
    @Override
    public void run() {
        // ② 在 自定义的任 务中, 打印出 当前任务线程中的count值
        System.out.println("running_" + count.getAndIncrement());
    }
}
/*
UserThreadFactory's test-Worker-0 // test是user传入的参数,用作线程组的组名； 0是线程的id(用原子类实现)
running_0

 */