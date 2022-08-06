package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_sync_03;

public class CopyOnWriteArrayListClip {
    // 数组成员变量 - 用于真正地存储元素
    private transient volatile Object[] array;

    final void setArray(Object[] a) {
        array = a; // 把写操作所产生的新集合对象 绑定到原来的引用变量上
    }
}
