package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.reference_type_01;

import java.lang.ref.SoftReference;

public class SoftReferenceWhenIdle_02 {
    public static void main(String[] args) {
        House seller = new House();

        // 第一处 - 只创建一个对象，并使用软引用指向此对象
        SoftReference<House> buyer2 = new SoftReference<>(seller); // 方案1
        seller = null; // 把seller强引用指向别处

        while (true) {
            // 建议 JVM进行垃圾回收 方案1
            // 手段1：调用System.gc() - 具体时机仍然由JVM控制
            // 手段2：强制调用已经失去引用的对象的finalize()方法
            System.gc();
            System.runFinalization();

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
验证特性：软引用在 JVM内存空间没有达到 临界点时会不会被回收？
手段：
    只创建一个对象，并使用软引用指向此对象。
    然后尝试手动触发 GC，查看 软引用指向的对象会不会被回收掉；
    注：为了补偿GC被触发的时间因素，这里添加一个while(true)的循环。
参考：方案1
结果：在相同的JVM环境下，buyer2 still here会一直输出。
结论：buyer2一直持有 new House()的有效引用。即便 对象本身已经被置为null了
 */