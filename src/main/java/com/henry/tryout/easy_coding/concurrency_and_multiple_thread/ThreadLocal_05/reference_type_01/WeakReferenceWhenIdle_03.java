package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.reference_type_01;

import java.lang.ref.WeakReference;

// 验证： 如果某个对象 只被弱引用所指向，则：下一次YGC（新生代垃圾回收）时会回收此对象
public class WeakReferenceWhenIdle_03 {
    public static void main(String[] args) {
        // seller强引用 指向 house实例
        House seller = new House();
        // buyer3引用 指向 弱引用对象;   而弱引用对象 封装了 seller强引用所指向的House对象
        WeakReference<House> buyer3 = new WeakReference<>(seller);
        // 去除house对象上的强引用
        seller = null;

        long start = System.nanoTime();
        int count = 0;
        while (true) {
            /* 这里去掉 催促垃圾回收的代码 - 以便使对象本身存活更长的时间。 */

            // 如果 buyer3引用所指向的对象(真实对象)被回收...
            if (buyer3.get() == null) {
                long duration = (System.nanoTime() - start) / (1000 * 1000);
                // 则：打印👇
                System.out.println("house is null and exited time = " + duration + "ms");
                break;
            } else { // 如果 buyer3引用所指向的对象还没有被回收,
                // 则：打印👇
                System.out.println("still here, count = " + (count++));
            }
        }
    }
}
/*
预期：希望在 实际对象被置为null 时，引用能够自动感知，并主动断开 引用所指向的对象。
手段：弱引用 WeakReference。
用法：使用 WeakReference就可以实现弱引用。
预期：观察 GC触发的情况；
手段：在JVM启动参数中添加 -XX:+PrintGCDetails;
结果：
    house is null and exited time = 32ms
结论：
    一段时间后，有且仅有 buyer3引用 指向的house实例对象被回收掉了。 -> 这时候使用 buyer3.get()得到的值为null

弱引用的应用：
    WeakHashMap
类比：
    1 卖家的房子在中介处 挂牌出售，中介需要记录 房子的相关资料信息；
    2 如果卖家的房源已经售出，则：中介就不需要保存房源的信息了 - 回收这些信息占用的空间。
Demo: WeakHashMapTest
 */
