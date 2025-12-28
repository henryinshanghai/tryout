package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.reference_type_01.soft_reference_02;

import com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.reference_type_01.House;

import java.lang.ref.SoftReference;

// 验证：当 堆内存空间 不紧张的时候，软引用 所指向的对象 不会 被垃圾回收掉
// 手段：创建一个 只有软引用指向的对象，然后 建议垃圾回收器 进行GC，最后 查看 该软引用指向的对象 有没有被回收掉
// 结果：此软引用 所指向的对象 没有被回收
public class WhenSpaceIsPlenty {
    public static void main(String[] args) {
        // 创建一个house对象 使用 强引用seller 指向它
        House seller = new House();

        // 第一处 - 使用 强引用seller 来 创建出一个软引用，指向 house对象
        SoftReference<House> buyer2 = new SoftReference<>(seller);
        // 把 seller强引用 指向null；  作用：使得 house对象 就只存在有 一个引用，且 此引用 为 软引用
        seller = null;

        // 因为我们只能建议JVM进行垃圾回收，控制不了具体时机。所以使用while循环 持续查看 对象有没有被回收
        while (true) {
            // 建议JVM 进行 垃圾回收
            // 手段1：调用System.gc() - 具体时机 仍然 由JVM控制
            // 手段2：强制调用 已经失去引用的对象 的finalize()方法
            System.gc();
            System.runFinalization();

            // 获取到 软引用 所指向的对象 - 手段: 软引用.get()
            // 如果 获取到的对象为null，说明 其所指向的对象 已经被回收
            if (buyer2.get() == null) {
                // 则：打印 对象被回收了
                System.out.println("house对象 已经被垃圾回收了");
                break;
            } else {
                System.out.println("house对象 仍旧存在");
            }
        }
    }
}