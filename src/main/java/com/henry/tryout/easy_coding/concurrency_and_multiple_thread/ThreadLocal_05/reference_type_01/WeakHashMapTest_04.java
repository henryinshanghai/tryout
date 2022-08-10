package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.reference_type_01;

import java.util.WeakHashMap;

public class WeakHashMapTest_04 {
    public static void main(String[] args) {
        House seller1 = new House("1号卖家房源");
        SellerInfo sellerInfo1 = new SellerInfo();

        House seller2 = new House("2号卖家房源");
        SellerInfo sellerInfo2 = new SellerInfo();

        // 这里如果换成 HashMap，则： Key是对 House对象的强引用
        // WeakHashMap中的Key, 是对对象的弱引用 - 一旦引用指向的对象成为null,则自动断开指向
        WeakHashMap<House, SellerInfo> weakHashMap =
                new WeakHashMap<>();

        weakHashMap.put(seller1, sellerInfo1);
        weakHashMap.put(seller2, sellerInfo2);

        System.out.println("weakHashMap before null, size = " + weakHashMap.size());

        // 把key1的强引用移除，只有弱引用指向 new House("xxx")
        seller1 = null;

        // 催出回收器 进行垃圾回收
        System.gc();
        System.runFinalization();

        // 如果使用HashMap，则： size会仍旧等于2
        // 按照预期，这里WeakHashMap的大小会变成1 - 因为key1-value1已经被回收了
        System.out.println("weakHashMap after null, size = " + weakHashMap.size());
        System.out.println(weakHashMap);
    }
}

class SellerInfo {

}
/*
    如果使用HashMap的话，把key1设置为null，不会影响 map的size；
    使用 WeakHashMap，把key1指向的对象设置为null后，由于仅存的引用是虚引用，因此会被GC回收。map的size也变成了1
    WeakHashMap应用：
        缓存不敏感的临时信息。
        比如用户登录系统后的浏览路径，会在关闭浏览器的时候自动清空。
    应用2：
        ThreadLocal。
        BUT，弱引用的设计方式 使得 ThreadLocal与Thread体系的理解难度陡增。

    高层次作用：
        除强引用外的其他三种引用类型，能够减少 对象生命周期中占用内存的大小。
    特征：
        1 如果使用了这些个引用，就应该像示例中的 seller一样。把 强引用指向的对象设置为null - 以此来避免 强引用挟持。
        2 这3种引用的使用成本比较大，所以开发者更应该做的是 - 考虑怎么做不会造成内存泄漏，并使方法执行后能够形成自然地回收。
        3 由于语义差别很细微，并且效果不容易验证。如果使用不当，可能会有风险

 */
