package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.reference_type_01;

import java.util.ArrayList;
import java.util.List;

public class SoftReferenceHouse {
    public static void main(String[] args) {
        List<House> houses = new ArrayList<>();
//        List<SoftReference> houses = new ArrayList<>();

        // 剧情反转注释
        int i = 0;
        while (true) {
             houses.add(new House());

            // 剧情反转注释处
            // 创建软引用的语法: new SoftReference(匿名对象)
//            SoftReference<House> buyer2 = new SoftReference<House>(new House());

            // 剧情反转注释处
//            houses.add(buyer2);

            System.out.println("i=" + (++i));
        }
    }
}

class House {
    public static final Integer DOOR_NUMBER = 2000;
    public Door[] doors = new Door[DOOR_NUMBER];

    private String info;

    public House() {

    }

    public House(String s) {
        this.info = s;
    }

    class Door {
    }

}
/*
运行一段时间后，JVM的堆内存空间会到达 要耗尽的临界状态：
    House$Door超过10MB，内存占比到达80%。
    软引用的特性在数秒钟之后产生了价值 - House对象的数量从 几千 快速降低到几百。
    原因：软引用的特性 - 在OOM之前，软引用会被回收掉。以此保证内存容量充足，程序可以正常运行

原理：
    1 SoftReference的父类中的 private T referent; 属性，指向了 new House()匿名对象；
    2 SoftReference中的get()方法，也是调用 super.get()来 访问父类的这个私有属性的；
    结果： 大量的House对象 在内存即将耗尽之前，被一次次地清理掉。

buyer2本身是一个引用类型；
buyer2被集合 ArrayList的强引用所劫持；

不断循环执行 houses.add()，在 i=347219 时，终于产生了OOM。

软引用、弱引用、虚引用都有 带有队列的构造方法：
    public SoftReference(T referent, ReferenceQueue<? super T> q){...}
作用：能够在队列中检查具体哪一个软引用的对象被回收了 - 进而把失去对象的软引用给回收掉。

现在，使用强引用 替换掉 弱引用；
结果：在 i=2343时，就发生了OOM。

demo的作用：证明了软引用在内存紧张时的回收能力。
应用：软引用一般用于 在同一服务器内缓存中间结果；
    如果命中缓存，则：直接提取缓存结果；否则，重新计算或者获取。
note：软引用不能用来缓存高频数据 - 因为一旦服务器重启或者内存紧张触发了对软引用的回收，则：缓存就会失效。

追问：如果内存没有到达OOM，软引用会被回收吗？
Demo: SoftReferenceWhenIdle

 */