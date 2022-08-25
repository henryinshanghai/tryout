package com.henry.tryout.easy_coding.data_structure_and_collection.map_08.treeMap_02;

import java.util.TreeMap;

public class TreeMapRepeat_01 {
    public static void main(String[] args) {
        // 使用TreeMap来创建 map对象
        TreeMap map = new TreeMap(); // 2 - 说明 TreeMap是通过 Comparable或者Comparator来实现去重的
//        HashMap map = new HashMap<>(); // 1 - 说明 HashMap是通过 hashCode 与 equals来实现去重的

        // 每次使用new，在堆内存中就会产生一个新的对象 - 至于对象是不是会被视为相等，则：需要看compareTo()方法
        map.put(new Key(), "value one");
        map.put(new Key(), "value one"); // 虽然value值一样，但是 这里是两个item

        System.out.println(map.size());
    }
}

// TreeMap中Key的定义 - 判断元素是否是重复元素的手段 - 两个元素是否可以视为相等
class Key implements Comparable<Key> {

    @Override
    public int compareTo(Key o) {
        // 不管当前对象与任何传入的参数相比，都认为当前对象更小 - 这种方法实现最终决定了 map的size=2
        return -1;
    }

    @Override
    public int hashCode() {
        return 1; // 所有对象的hash值都为1
    }

    @Override
    public boolean equals(Object obj) {
        return true; // equals()比较的结果也总是相等的
    }
}
/*
启示：
1 TreeMap是通过 Comparable或者Comparator来实现去重的；
2 HashMap是通过 hashCode 与 equals来实现去重的；
3 TreeMap对Key进行排序所使用的方法：
    final int compare(Object k1, Object k2) {
        return comparator==null ? ((Comparable<? super K>)k1).compareTo((K)k2)
            : comparator.compare((K)k1, (K)k2);
    }
    三目运算符的含义：
        如果comparator不为null，则：有限使用比较器的compare()方法；
        如果为null，则使用 Key中所实现的 Comparable接口中的compareTo()方法。
        而如果两者都无法满足？？？则：抛出ClassCastException异常
 */