package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_sync_03;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// 验证：在 CopyOnWriteArrayList的源码中，使用了volatile 来 保证共享变量 array 对所有线程的可见性
public class COWArrayListDemo {
    // 数组成员变量 - 用于真正地存储元素
    // 使用 volatile修饰 - 这样某个线程对array的更新，对其他线程是立即可见的
    private transient volatile Object[] array;

    final void setArray(Object[] a) {
        array = a; // 把写操作所产生的新集合对象 绑定到原来的引用变量上
    }

    public static void main(String[] args) {
        List list = new CopyOnWriteArrayList<>();
    }
}
