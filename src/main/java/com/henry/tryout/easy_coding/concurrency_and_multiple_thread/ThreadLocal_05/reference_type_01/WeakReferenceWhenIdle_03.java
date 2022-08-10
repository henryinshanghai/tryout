package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.reference_type_01;

import com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.reference_type_01.House;

import java.lang.ref.WeakReference;

public class WeakReferenceWhenIdle_03 {
    public static void main(String[] args) {
        // seller强引用 指向 new House()对对象
        House seller = new House();
        // 虚引用 buyer3 指向 new House()对象
        WeakReference<House> buyer3 = new WeakReference<>(seller);
        // 去除强引用
        seller = null;

        long start = System.nanoTime();
        int count = 0;
        while (true) {
            // 如果 buyer3引用指向的对象被回收...
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
结论：
    一段时间后，只有 buyer3引用指向的对象 new House()被回收掉了。
    证据： house is null and exited time = 547ms

    弱引用的应用：
        WeakHashMap???
    类比：
        卖家的房子对应着一系列的房源资料，如果卖家的房源已经售出，则：中介就不需要保存房源的信息了 - 回收这些信息占用的空间。
    Demo: WeakHashMapTest
 */
