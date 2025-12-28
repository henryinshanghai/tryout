package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.reference_type_01.weak_reference_03;


import com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.reference_type_01.House;

import java.util.WeakHashMap;

// 验证：WeakHashMap中的 key的引用类型 是 弱引用；
// 原理：对于 WeakHashMap来说，如果 某个entry的key 所指向的对象上 只存在弱引用，则：该entry 就会 被自动回收掉
// 手段：把 某个entry的key 设置为 仅存在弱引用，然后 打印map的size
public class WeakHashMapDemo {
    public static void main(String[] args) {
        House seller1 = new House("1号卖家房源");
        SellerInfo sellerInfo1 = new SellerInfo();

        House seller2 = new House("2号卖家房源");
        SellerInfo sellerInfo2 = new SellerInfo();

        // 这里如果换成 HashMap，则： Key是 对House对象的强引用
        // 特征：WeakHashMap中的Key, 是 对对象的弱引用 - 如果有且只有弱引用指向对象，则：对象就会在YGC时被回收掉
        WeakHashMap<House, SellerInfo> weakHashMap =
                new WeakHashMap<>(); // 方案1：使用 WeakHashMap - 特征：👆

        // 建立 房子 -> 房主信息的关系
        weakHashMap.put(seller1, sellerInfo1);
        weakHashMap.put(seller2, sellerInfo2);

        // 判断map的元素有没有被回收掉 - 手段：size()
        System.out.println("weakHashMap before null, size = " + weakHashMap.size()); // 2

        // 把seller1对象的强引用 移除 - 这样就只剩下弱引用指向House对象
        // 由于弱引用的特性，这里的 匿名对象会被GC回收 - 弱引用对象本身会不会被回收掉呢？ YES
        seller1 = null;

        // 催促回收器 进行垃圾回收 - 有且只有弱引用指向的对象,会在 YGC时被回收掉
        System.gc();
        System.runFinalization();

        // 如果使用HashMap，则： size会仍旧等于2
        // 按照预期，这里WeakHashMap的大小会变成1 - 因为key1-value1已经被回收了
        System.out.println("weakHashMap after null, size = " + weakHashMap.size()); //  1
        System.out.println(weakHashMap);
    }
}

class SellerInfo {

}
/*
    如果使用HashMap的话，把key1设置为null，不会影响 map的size；
    使用 WeakHashMap，把 key1指向的对象 设置为null后，由于 仅存的引用 是虚引用，因此会被YGC回收。
    注：这里回收的不仅仅是 真实对象, 也包括 弱引用对象本身。

    WeakHashMap应用：
        缓存不敏感的临时信息。
        比如用户登录系统后的浏览路径，会在关闭浏览器的时候自动清空。
    应用2：
        ThreadLocal。
        BUT，弱引用的设计方式 使得 ThreadLocal与Thread体系的理解难度陡增。

    高层次作用：
        除强引用外的其他三种引用类型，能够减少 对象生命周期中所占用内存的大小。
    特征：
        1 如果使用了这些个引用，就应该像示例中的 seller一样。把 强引用指向的对象 设置为null - 以此来 “避免强引用挟持”。
        2 这3种引用的使用成本比较大，所以开发者更应该做的是 - 考虑怎么做不会造成内存泄漏，并使方法执行后能够形成自然地回收。
        3 由于语义差别很细微，并且效果不容易验证。如果使用不当，可能会有风险
 */
