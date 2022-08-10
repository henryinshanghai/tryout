package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.reference_type_01;

import java.lang.ref.WeakReference;

public class SoftReferenceWhenIdle {
    public static void main(String[] args) {
        House seller = new House();

        // 第一处
//        SoftReference<House> buyer2 = new SoftReference<>(seller);
        WeakReference<House> buyer2 = new WeakReference<>(seller);
        seller = null;

        while (true) {
            // 建议 JVM进行垃圾回收
            // 手段1：调用System.gc() - 具体时机仍然由JVM控制
            // 手段2：强制调用已经失去引用的对象的finalize()方法
//            System.gc();
//            System.runFinalization();

            if (buyer2.get() == null) {
                System.out.println("house is null");
                break;
            } else {
                System.out.println("buyer2 still here");
            }
        }
    }
}
/*
    结果：在相同的JVM环境下，buyer2 still here会一直输出。
    说明 buyer2一直持有 new House()的有效引用。

预期：在对象被置为null时，引用能够自动感知，并主动断开引用所指向的对象。
手段：弱引用。

用法：把第一处的 SoftReference 改成 WeakReference就可以实现弱引用。
这里去掉 催促垃圾回收的代码，以便使对象本身存活更长的时间。

预期：观察 GC触发的情况；
手段：在JVM启动参数中添加 -XX:+PrintGCDetails;
Demo: WeakReferenceWhenIdle
 */