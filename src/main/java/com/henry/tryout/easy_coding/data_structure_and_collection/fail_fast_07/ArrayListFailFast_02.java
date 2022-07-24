package com.henry.tryout.easy_coding.data_structure_and_collection.fail_fast_07;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListFailFast_02 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();

        list.add("one");
        list.add("two");
        list.add("three");

        // 在遍历操作时，删除列表中的元素
//        for (String s : list) {
//            if ("two".equals(s)) {
//                list.remove(s);
//            }
//        }

        List<String> cowList = new CopyOnWriteArrayList<>();
        Iterator<String> cowIterator = cowList.iterator();

        // 等价代码
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("two".equals(item)) {
                list.remove(item);
            }
        }
        System.out.println(list);
    }
}
/*
为什么没有抛出 ConcurrentModificationException异常呢？
原理：
    #1 遍历集合时，会使用一个cursor游标 来标识当前扫描到的位置；
    #2 当cursor == size的时候，就会退出循环；
    #3 在remove two元素之后，会把所有元素往前拷贝 - size = size -1 = 2.此时 cursor也等于2
    #4 基于#3，在执行 hasNext()时，返回值为false，从而退出循环体。不会执行到 next()方法，也就不会 checkForComodification()
结论：
    #1 可以使用Iterator机制 来进行遍历时的删除； - 如果时多线程并发，还需要在 Iterator遍历时加锁
    #2 也可以使用 CopyOnWriteArrayList 来代替 ArrayList;
        原理：这个类型中，对 Iterator进行了加锁操作。
        COW: copy on write.
        思路：读写分离；
        原理：如果是写操作，则：
            恢复至一个新的集合，在新的集合中做添加/删除元素的操作。
            等一切修改完成后，在把原始集合的引用 指向 新的集合。
        特征：
            1 能够 高并发地对COW进行 读与遍历的操作，而不需要加锁操作 - 因为当前集合不会添加任何元素；
            2 如果原始集合占用的内存比较大，拷贝动作可能会引起 GC，从而降低服务器性能。
        用法：
            #1 尽量设置合理的容量初始值，因为COW的扩容代价比较大；
            #2 使用批量添加/删除的方法 - 如addAll、removeAll操作
                在高并发请求下，可以先赞一下 要添加或者删除的元素，避免频繁的 集合拷贝步骤。
        demo: COWCopy_03
 */