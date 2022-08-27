package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_sync_03;

public class VolatileNotAtomic_02 {
    // #1 volatile修饰的变量 - 作用：保证这个变量的更新 对多个线程都是立马可见的
    private static volatile long count = 0L;
    public static final int NUMBER = 10000;

    public static void main(String[] args) {
        Thread subtractThread = new SubtractThread();
        // 触发 自定义线程run()方法的执行 - 手段：线程对象.start()
        subtractThread.start();

        // 在主线程中，把count的值 + 10000
        for (int i = 0; i < NUMBER; i++) {
            // #2 为了避免多个线程 对count写操作的相互覆盖，这里使用 synchronized关键字 来 保证线程之间操作count的互斥性
            synchronized (VolatileNotAtomic_02.class) {
                count++;
            }
        }

        // 等待减法线程结束
        while(subtractThread.isAlive()){}

        System.out.println("count变量最终的值为： " + count); // 这里count的值不是一个固定的结果
    }

    // 自定义的线程类 - 手段： extends Thread
    private static class SubtractThread extends Thread {

        // 在run()方法中，把count的值 - 10000
        public void run() {
            for (int i = 0; i < NUMBER; i++) {
                // 定义临界区 - 手段：使用 synchronized关键字 来 修饰临界区
                synchronized (VolatileNotAtomic_02.class) {
                    count--;
                }
            }
        }
    }
}
/*
    如果count++ 与 count-- 都是原子操作的话，那count最终的结果一定为0；
    但是 count++与count--并不是原子操作；
    怎么才能得到 count为0的结果呢？
    手段：为 count++ 与 count-- 的代码加锁，保证线程的互斥性。

count++的字节码步骤：
    // 读取count的值，并压入操作栈中
    GETSTATIC
    // 把常量1 压入操作栈顶
    ICONST_1
    // 取出最顶部的两个元素相加
    IADD
    // 把加和得到的结果 赋值给count变量
    PUTSTATIC
结果：在这个过程中，其他线程有足够的时间 来 覆盖变量的值

Java8中引入的支持 count++为原子操作的类型：
    AtomicLong, LongAdder(推荐使用)

结论：
    1 volatile并不是 轻量级的"同步方式"。而是 轻量级的 "线程操作可见方式"。
    2 volatile变量在 多线程并发写的场景中，会出现 线程安全问题。
    3 volatile变量 在一写多读的多线程场景中，不会有线程安全问题。
        应用： CopyOnWriteArrayList
            特征：在修改数据的时候，会：
                1 把整个集合的数据全部复制出来；
                2 然后对写操作进行加锁；
                3 写操作完成后，再使用 setArray()方法 把array变量指向新的集合。
            volatile的作用：
                1 使读线程能够直接看到array的变化 - 因为写操作完成后，立刻变量的值立刻对读线程可见；
                2 避免指令重排。
 */

