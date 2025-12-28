package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.reference_type_01.soft_reference_02;

import com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.reference_type_01.House;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

// 验证：当 堆内存 空间紧张 时，仅被软引用指向的对象 会 被GC回收掉
// JVM运行参数： -Xms20m -Xmx20m
public class WhenSpaceIsLimited {
    public static void main(String[] args) {
//        List<House> houses = new ArrayList<>(); // 方案1 集合中的元素 是 强引用
        List<SoftReference> houses = new ArrayList<>(); // 集合中的元素 是 软引用

        int i = 0;
        while (true) {
            // 把 匿名对象 添加到 集合中
//             houses.add(new House()); // 方案1

            // 为 大对象 创建一个 软引用
            // 手段：new SoftReference(匿名大对象)
            SoftReference<House> buyer2 = new SoftReference<House>(new House());
            houses.add(buyer2);

            System.out.println("i=" + (++i));
        }
    }
}
/*
JVM运行参数： -Xms20m -Xmx20m
方案1：
    如果 直接使用 强引用，发生OOM时，i=2340。

方案2：
运行一段时间后，JVM的堆内存空间会到达 要耗尽的临界状态：
    House$Door超过10MB，内存占比到达80%。
    软引用的特性在数秒钟之后产生了价值 - House对象的数量从 几千 快速降低到几百。
    原因：软引用的特性 - 在OOM之前，软引用会被回收掉。

原理：
    1 SoftReference的父类中的 private T referent; 属性，指向了 new House()匿名对象；
    2 SoftReference中的get()方法，也是调用 super.get()来 访问父类的这个私有属性 referent 的；
    结果： 大量的House对象 在内存即将耗尽之前，被一次次地清理掉。

在经过GC回收后，JVM堆内存仍旧被占满的原因：
    1 buyer2本身是一个SoftReference对象；
    2 buyer2被集合 ArrayList的强引用所劫持；

结果：不断循环执行 houses.add()，在 i=347219 时，终于产生了OOM。
 */